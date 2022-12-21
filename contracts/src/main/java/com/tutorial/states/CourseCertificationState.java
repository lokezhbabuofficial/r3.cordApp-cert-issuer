package com.tutorial.states;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.NotNull;

import com.tutorial.contracts.CourseCertificationContract;

import net.corda.core.contracts.BelongsToContract;
import net.corda.core.contracts.LinearState;
import net.corda.core.contracts.UniqueIdentifier;
import net.corda.core.identity.AbstractParty;
import net.corda.core.identity.Party;

@BelongsToContract(CourseCertificationContract.class)
public class CourseCertificationState implements LinearState {

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

	public Party getExaminer() {
		return examiner;
	}

	public Party getStudent() {
		return student;
	}

	public int getScore() {
		return score;
	}

	public UniqueIdentifier getLinerId() {
		return linerId;
	}

}
