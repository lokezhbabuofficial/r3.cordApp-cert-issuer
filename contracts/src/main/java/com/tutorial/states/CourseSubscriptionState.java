package com.tutorial.states;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import com.tutorial.contracts.CourseSubscriptionContract;

import net.corda.core.contracts.LinearState;
import net.corda.core.contracts.SchedulableState;
import net.corda.core.contracts.ScheduledActivity;
import net.corda.core.contracts.StateRef;
import net.corda.core.contracts.BelongsToContract;
import net.corda.core.contracts.LinearPointer;
import net.corda.core.contracts.UniqueIdentifier;
import net.corda.core.flows.FlowLogicRef;
import net.corda.core.flows.FlowLogicRefFactory;
import net.corda.core.identity.AbstractParty;
import net.corda.core.identity.Party;

@BelongsToContract(CourseSubscriptionContract.class)
public class CourseSubscriptionState implements LinearState, SchedulableState {

	private static final int SUBSCRIPTION_END_AMOUNT = 3;

	private Party subscriber;
	private Party courseOwner;
	private UniqueIdentifier linearId;
	private LinearPointer<CourseState> courseId;
	private Instant subscriptionStartDate;
	private Instant subscriptionEndDate;
	private Boolean active;
	private List<AbstractParty> participants;

	// public CourseSubscriptionState(Party subscriber, Party courseOwner,
	// UniqueIdentifier linearId,
	// LinearPointer<CourseState> courseId) {
	// this.subscriber = subscriber;
	// this.courseOwner = courseOwner;
	// this.linearId = linearId;
	// this.courseId = courseId;
	// this.subscriptionStartDate = Instant.now();
	// this.subscriptionEndDate =
	// this.subscriptionStartDate.plus(CourseSubscriptionState.SUBSCRIPTION_END_AMOUNT,
	// ChronoUnit.DAYS);
	// this.participants = Arrays.asList(subscriber, courseOwner);
	// }

	public CourseSubscriptionState(Party subscriber, Party courseOwner, UniqueIdentifier linearId,
			LinearPointer<CourseState> courseId, Instant subscriptionStartDate, Instant subscriptionEndDate, Boolean active) {
		this.subscriber = subscriber;
		this.courseOwner = courseOwner;
		this.linearId = linearId;
		this.courseId = courseId;
		this.subscriptionStartDate = subscriptionStartDate;
		this.subscriptionEndDate = subscriptionEndDate;
		this.active = active;
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

	public static int getSubscriptionEndAmount() {
		return SUBSCRIPTION_END_AMOUNT;
	}

	public Boolean getActive() {
		return active;
	}

	@Override
	public ScheduledActivity nextScheduledActivity(StateRef stateRef, FlowLogicRefFactory flowFactoryRef) {
		if (!active)
			return null;

		FlowLogicRef flowLogicRef = flowFactoryRef.create(
				"com.tutorial.flows.EndCourseSubscriptionFlow$EndCourseSubscriptionFlowInitator", linearId);

		return new ScheduledActivity(flowLogicRef, subscriptionEndDate);
	}

	public void setSubscriber(Party subscriber) {
		this.subscriber = subscriber;
	}

	public void setCourseOwner(Party courseOwner) {
		this.courseOwner = courseOwner;
	}

	public void setLinearId(UniqueIdentifier linearId) {
		this.linearId = linearId;
	}

	public void setCourseId(LinearPointer<CourseState> courseId) {
		this.courseId = courseId;
	}

	public void setSubscriptionStartDate(Instant subscriptionStartDate) {
		this.subscriptionStartDate = subscriptionStartDate;
	}

	public void setSubscriptionEndDate(Instant subscriptionEndDate) {
		this.subscriptionEndDate = subscriptionEndDate;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public void setParticipants(List<AbstractParty> participants) {
		this.participants = participants;
	}

}
