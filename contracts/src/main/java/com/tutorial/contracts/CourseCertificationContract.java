package com.tutorial.contracts;

import org.jetbrains.annotations.NotNull;

import com.tutorial.states.CourseCertificationState;

import net.corda.core.contracts.CommandData;
import net.corda.core.contracts.Contract;
import net.corda.core.identity.CordaX500Name;
import net.corda.core.transactions.LedgerTransaction;

import static net.corda.core.contracts.ContractsDSL.requireThat;

import java.util.List;
import java.util.stream.Collectors;

public class CourseCertificationContract implements Contract {

	private static final CordaX500Name Examinar = CordaX500Name.parse("O=CordaExaminar,L=New York,C=US");

	@Override
	public void verify(@NotNull LedgerTransaction tx) throws IllegalArgumentException {
		// Extract the command from the transaction.
		final CommandData commandData = tx.getCommands().get(0).getValue();

		// Verify the transaction according to the intention of the transaction
		if (commandData instanceof CourseCertificationContract.Commands.Issue) {
			CourseCertificationState output = tx.outputsOfType(CourseCertificationState.class).get(0);
			requireThat(require -> {
				require.using("This transaction should not have any input", tx.getInputs().isEmpty());
				require.using("This transaction should only have one CourseCertificationState state as output",
						tx.getOutputs().size() == 1);
				require.using("The score has to be above 80 and less than 100",
						output.getScore() >= 80 && output.getScore() <= 100);
				require.using("The student cannot be an examinar", !output.getStudent().getName().equals(Examinar));
				require.using("Selected examinar is not an actual examinar", output.getExaminer().getName().equals(Examinar));
				return null;
			});
		} else if (commandData instanceof CourseCertificationContract.Commands.BulkIssue) {
			List<CourseCertificationState> output = tx.outputsOfType(CourseCertificationState.class);
			requireThat(require -> {
				require.using("This transaction should not have any input", tx.getInputs().isEmpty());

				require.using("This transaction should be gte 1 CourseCertificationState state as output",
						tx.getOutputs().size() >= 1);

				require.using("All output should have the actual examinar node",
						output.stream().map(out -> out.getExaminer().getName()).collect(Collectors.toList()).stream()
								.allMatch(examinar -> examinar.equals(Examinar)));

				require.using("All output score has to be above 80 and less than 100",
						output.stream().map(out -> out.getScore()).collect(Collectors.toList()).stream()
								.allMatch(score -> score >= 80 && score <= 100));
				return null;
			});
		} else {
			throw new IllegalArgumentException("Incorrect type of CourseCertificationContract Commands");
		}
	}

	public interface Commands extends CommandData {
		class Issue implements CourseCertificationContract.Commands {
		}

		class BulkIssue implements CourseCertificationContract.Commands {
		}
	}

}
