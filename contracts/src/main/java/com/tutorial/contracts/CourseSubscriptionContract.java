package com.tutorial.contracts;

import net.corda.core.contracts.CommandData;
import net.corda.core.contracts.Contract;
import net.corda.core.transactions.LedgerTransaction;

public class CourseSubscriptionContract implements Contract {
	private final static String ID = CourseSubscriptionContract.class.getName();

	@Override
	public void verify(LedgerTransaction tx) throws IllegalArgumentException {
		
	}

	public interface Commands extends CommandData {
		class Issue implements CourseSubscriptionContract.Commands {
		}
	}

}
