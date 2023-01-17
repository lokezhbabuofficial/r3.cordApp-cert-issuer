package com.tutorial.states;

import java.util.Arrays;
import java.util.List;

import com.tutorial.contracts.CourseCertificationContractV2;

import net.corda.core.contracts.BelongsToContract;
import net.corda.core.contracts.LinearState;
import net.corda.core.contracts.UniqueIdentifier;
import net.corda.core.identity.AbstractParty;
import net.corda.core.identity.Party;

@BelongsToContract(CourseCertificationContractV2.class)
public class CourseCertificationStateV2 implements LinearState {

	private Party issuer;
	private Party owner;
	private Integer courseScore;
	private UniqueIdentifier linearId;
	private List<AbstractParty> participants;

	public CourseCertificationStateV2(Party issuer, Party owner, Integer courseScore, UniqueIdentifier linearId) {
		this.issuer = issuer;
		this.owner = owner;
		this.courseScore = courseScore;
		this.linearId = linearId;
		this.participants = Arrays.asList(issuer, owner);
	}

	@Override
	public List<AbstractParty> getParticipants() {
		return this.participants;
	}

	@Override
	public UniqueIdentifier getLinearId() {
		return this.linearId;
	}

	public Party getIssuer() {
		return issuer;
	}

	public Party getOwner() {
		return owner;
	}

	public Integer getCourseScore() {
		return courseScore;
	}
}
