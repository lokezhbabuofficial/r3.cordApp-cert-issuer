package com.tutorial.contracts;

import net.corda.core.contracts.CommandData;
import net.corda.core.contracts.Contract;
import net.corda.core.transactions.LedgerTransaction;

public class CourseContract implements Contract {
	private final static String ID = CourseContract.class.getName();

	@Override
	public void verify(LedgerTransaction tx) throws IllegalArgumentException {

	}

	public interface Commands extends CommandData {
		class Create implements CourseContract.Commands {
		}
		class Update implements CourseCertificationContractV2.Commands {
		}
	}

}
