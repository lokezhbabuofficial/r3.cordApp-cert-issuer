package com.tutorial.states;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import com.tutorial.contracts.CourseSubscriptionContract;

import net.corda.core.contracts.LinearState;
import net.corda.core.contracts.BelongsToContract;
import net.corda.core.contracts.LinearPointer;
import net.corda.core.contracts.UniqueIdentifier;
import net.corda.core.identity.AbstractParty;
import net.corda.core.identity.Party;

@BelongsToContract(CourseSubscriptionContract.class)
public class CourseSubscriptionState implements LinearState {

	private static final int SUBSCRIPTION_END_AMOUNT = 3;

	private Party subscriber;
	private Party courseOwner;
	private UniqueIdentifier linearId;
	private LinearPointer<CourseState> courseId;
	private Instant subscriptionStartDate;
	private Instant subscriptionEndDate;
	private List<AbstractParty> participants;


	public CourseSubscriptionState(Party subscriber, Party courseOwner, UniqueIdentifier linearId,
			LinearPointer<CourseState> courseId) {
		this.subscriber = subscriber;
		this.courseOwner = courseOwner;
		this.linearId = linearId;
		this.courseId = courseId;
		this.subscriptionStartDate = Instant.now();
		this.subscriptionEndDate = this.subscriptionStartDate.plus(CourseSubscriptionState.SUBSCRIPTION_END_AMOUNT, ChronoUnit.DAYS);
		this.participants = Arrays.asList(subscriber, courseOwner);
	}

	
	public List<AbstractParty> getParticipants() {
		return this.participants;
	}

	@Override
	public UniqueIdentifier getLinearId() {
		return this.linearId;
	}

	public Party getSubscriber() {
		return subscriber;
	}

	public Party getCourseOwner() {
		return courseOwner;
	}

	public LinearPointer<CourseState> getCourseId() {
		return courseId;
	}

	public Instant getSubscriptionStartDate() {
		return subscriptionStartDate;
	}

	public Instant getSubscriptionEndDate() {
		return subscriptionEndDate;
	}

	
}
