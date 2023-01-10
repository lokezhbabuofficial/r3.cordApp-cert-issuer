package com.tutorial.states;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.NotNull;

import com.tutorial.contracts.CourseCertificationContract;

import net.corda.v5.application.identity.AbstractParty;
import net.corda.v5.application.identity.Party;
import net.corda.v5.application.utilities.JsonRepresentable;
import net.corda.v5.ledger.UniqueIdentifier;
import net.corda.v5.ledger.contracts.BelongsToContract;
import net.corda.v5.ledger.contracts.LinearState;

@BelongsToContract(CourseCertificationContract.class)
public class CourseCertificationState implements LinearState, JsonRepresentable {

	private Party student;
	private Party examiner;
	private int score;

	private List<AbstractParty> participants;
	private UniqueIdentifier linerId;

	public CourseCertificationState(Party student, Party examiner, int score, UniqueIdentifier linerId) {
		this.examiner = examiner;
		this.student = student;
		this.score = score;
		this.linerId = linerId;
		this.participants = new ArrayList<AbstractParty>();
		this.participants.add(examiner);
		this.participants.add(student);
	}

	@Override
	@NotNull
	public List<AbstractParty> getParticipants() {
		return participants;
	}

	@Override
	@NotNull
	public UniqueIdentifier getLinearId() {
		return linerId;
	}

	@NotNull
	public Party getExaminer() {
		return examiner;
	}

	@NotNull
	public Party getStudent() {
		return student;
	}

	public int getScore() {
		return score;
	}

	public UniqueIdentifier getLinerId() {
		return linerId;
	}

  @Override
  public String toJsonString() {
    return "examinar: " + examiner + ", student: " + student + ", score: " + score;
  }

}
