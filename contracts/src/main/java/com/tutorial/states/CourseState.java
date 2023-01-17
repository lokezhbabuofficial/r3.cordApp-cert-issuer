package com.tutorial.states;

import java.util.Arrays;
import java.util.List;

import com.tutorial.contracts.CourseContract;

import net.corda.core.contracts.BelongsToContract;
import net.corda.core.contracts.LinearState;
import net.corda.core.contracts.UniqueIdentifier;
import net.corda.core.identity.AbstractParty;
import net.corda.core.identity.Party;

@BelongsToContract(CourseContract.class)
public class CourseState implements LinearState {

	private Party owner;
	private UniqueIdentifier courseId;
	private String courseDec;
	private int coursePassScore;
	private List<AbstractParty> participants;

	public CourseState(Party owner, UniqueIdentifier courseId, String courseDec, int coursePassScore) {
		this.owner = owner;
		this.courseId = courseId;
		this.courseDec = courseDec;
		this.coursePassScore = coursePassScore;
		this.participants = Arrays.asList(owner);
	}

	@Override
	public List<AbstractParty> getParticipants() {
		return this.participants;
	}

	@Override
	public UniqueIdentifier getLinearId() {
		return courseId;
	}

	public Party getOwner() {
		return owner;
	}

	public void setOwner(Party owner) {
		this.owner = owner;
	}

	public UniqueIdentifier getCourseId() {
		return courseId;
	}

	public void setCourseId(UniqueIdentifier courseId) {
		this.courseId = courseId;
	}

	public String getCourseDec() {
		return courseDec;
	}

	public void setCourseDec(String courseDec) {
		this.courseDec = courseDec;
	}

	public int getCoursePassScore() {
		return coursePassScore;
	}

	public void setCoursePassScore(int coursePassScore) {
		this.coursePassScore = coursePassScore;
	}

	public void setParticipants(List<AbstractParty> participants) {
		this.participants = participants;
	}

}
