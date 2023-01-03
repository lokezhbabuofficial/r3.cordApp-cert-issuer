package com.tutorial.contracts;

import net.corda.core.contracts.CommandData;
import net.corda.core.contracts.Contract;
import net.corda.core.transactions.LedgerTransaction;

public class CourseCertificationContractV2 implements Contract {
	private final static String ID = CourseCertificationContractV2.class.getCanonicalName();

	@Override
	public void verify(LedgerTransaction tx) throws IllegalArgumentException {
		
	}

	public interface Commands extends CommandData {
		class Create implements CourseCertificationContractV2.Commands {
		}
		class Update implements CourseCertificationContractV2.Commands {
		}
	}

}
