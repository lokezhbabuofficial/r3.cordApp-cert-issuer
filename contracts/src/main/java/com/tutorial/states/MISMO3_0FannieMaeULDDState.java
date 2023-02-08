package com.tutorial.states;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.tutorial.contracts.MISMO3_0FannieMaeULDDContract;

import net.corda.core.contracts.BelongsToContract;
import net.corda.core.contracts.LinearState;
import net.corda.core.contracts.UniqueIdentifier;
import net.corda.core.identity.AbstractParty;
import net.corda.core.serialization.CordaSerializable;

@CordaSerializable
public class MISMO3_0FannieMaeULDDState {

  @CordaSerializable
  public static class Address {
    public String addressLineText;
    public String cityName;
    public int postalCode;
    public String stateCode;
  }

  @CordaSerializable
  public static class Amortization {
    public AmortizationRule amortizationRule;
  }

  @CordaSerializable
  public static class AmortizationRule {
    public int loanAmortizationPeriodCount;
    public String loanAmortizationPeriodType;
    public String loanAmortizationType;
  }

  @CordaSerializable
  public static class Appraiser {
    public AppraiserLicense appraiserLicense;
  }

  @CordaSerializable
  public static class AppraiserLicense {
    public String appraiserLicenseIdentifier;
  }

  @CordaSerializable
  public static class AppraiserSupervisor {
    public AppraiserLicense appraiserLicense;
  }

  @CordaSerializable
  public static class AutomatedUnderwriting {
    public long automatedUnderwritingCaseIdentifier;
  }

  @CordaSerializable
  public static class AutomatedUnderwritings {
    public AutomatedUnderwriting automatedUnderwriting;
  }

  @CordaSerializable
  public static class Borrower {
    public BorrowerDetail borrowerDetail;
    public CreditScores creditScores;
    public Declaration declaration;
    public Employers employers;
    public GovernmentMonitoring governmentMonitoring;
  }

  @CordaSerializable
  public static class BorrowerDetail {
    public int borrowerAgeAtApplicationYearsCount;
    public String borrowerBirthDate;
    public String borrowerClassificationType;
    public boolean borrowerMailToAddressSameAsPropertyIndicator;
    public int borrowerQualifyingIncomeAmount;
  }

  @CordaSerializable
  public static class Collateral {
    public Properties properties;
  }

  @CordaSerializable
  public static class Collaterals {
    public Collateral collateral;
  }

  @CordaSerializable
  public static class CombinedLtv {
    public int combinedLtvratioPercent;
    public int homeEquityCombinedLtvratioPercent;
  }

  @CordaSerializable
  public static class CombinedLtvs {
    public CombinedLtv combinedLtv;
  }

  @CordaSerializable
  public static class CreditScore {
    public CreditScoreDetail creditScoreDetail;
  }

  @CordaSerializable
  public static class CreditScoreDetail {
    public long creditReportIdentifier;
    public boolean creditRepositorySourceIndicator;
    public String creditRepositorySourceType;
    public int creditScoreValue;
  }

  @CordaSerializable
  public static class CreditScores {
    public CreditScore creditScore;
  }

  @CordaSerializable
  public static class Deal {
    public Collaterals collaterals;
    public Loans loans;
    public Parties parties;
  }

  @CordaSerializable
  public static class Deals {
    public Deal deal;
  }

  @CordaSerializable
  public static class DealSet {
    public Deals deals;
  }

  @BelongsToContract(MISMO3_0FannieMaeULDDContract.class)
  public static class DealSets implements LinearState {
    public DealSet dealSet;
    public Parties parties;

    private UniqueIdentifier linerId;
    private net.corda.core.identity.Party issuer;

    @Override
    public List<AbstractParty> getParticipants() {
      return Arrays.asList(issuer);
    }

    @Override
    public UniqueIdentifier getLinearId() {
      return linerId;
    }

    public DealSets(DealSet dealSet, Parties parties, UniqueIdentifier linerId, net.corda.core.identity.Party issuer) {
      this.dealSet = dealSet;
      this.parties = parties;
      this.issuer = issuer;
      this.linerId = linerId;
    }
  }

  @CordaSerializable
  public static class Declaration {
    public DeclarationDetail declarationDetail;
  }

  @CordaSerializable
  public static class DeclarationDetail {
    public boolean bankruptcyIndicator;
    public boolean borrowerFirstTimeHomebuyerIndicator;
    public String citizenshipResidencyType;
    public boolean loanForeclosureOrJudgmentIndicator;
  }

  @CordaSerializable
  public static class DelinquencySummary {
    public int delinquentPaymentsOverPastTwelveMonthsCount;
  }

  @CordaSerializable
  public static class Employer {
    public Employment employment;
  }

  @CordaSerializable
  public static class Employers {
    public Employer employer;
  }

  @CordaSerializable
  public static class Employment {
    public boolean employmentBorrowerSelfEmployedIndicator;
  }

  @CordaSerializable
  public static class Extension {
    public Other other;
  }

  @CordaSerializable
  public static class FloodDetermination {
    public FloodDeterminationDetail floodDeterminationDetail;
  }

  @CordaSerializable
  public static class FloodDeterminationDetail {
    public boolean specialFloodHazardAreaIndicator;
  }

  @CordaSerializable
  public static class FormSpecificContent {
    public Urla urla;
  }

  @CordaSerializable
  public static class FormSpecificContents {
    public FormSpecificContent formSpecificContent;
  }

  @CordaSerializable
  public static class GovernmentMonitoring {
    public GovernmentMonitoringDetail governmentMonitoringDetail;
    public HmdaRaces hmdaRaces;
    public Extension extension;
  }

  @CordaSerializable
  public static class GovernmentMonitoringDetail {
    public Extension extension;
  }

  @CordaSerializable
  public static class GovernmentMonitoringDetailExtension {
    public boolean hmdaethnicityCollectedBasedOnVisualObservationOrSurnameIndicator;
    public boolean hmdaethnicityRefusalIndicator;
    public boolean hmdagenderCollectedBasedOnVisualObservationOrNameIndicator;
    public boolean hmdagenderRefusalIndicator;
    public String hmdagenderType;
    public boolean hmdaraceCollectedBasedOnVisualObservationOrSurnameIndicator;
    public boolean hmdaraceRefusalIndicator;
  }

  @CordaSerializable
  public static class GovernmentMonitoringExtension {
    public HmdaEthnicities hmdaEthnicities;
  }

  @CordaSerializable
  public static class Heloc {
    public HelocOccurrences helocOccurrences;
  }

  @CordaSerializable
  public static class HelocOccurrence {
    public int currentHelocmaximumBalanceAmount;
    public int helocbalanceAmount;
  }

  @CordaSerializable
  public static class HelocOccurrences {
    public HelocOccurrence helocOccurrence;
  }

  @CordaSerializable
  public static class HmdaEthnicities {
    public HmdaEthnicity hmdaEthnicity;
  }

  @CordaSerializable
  public static class HmdaEthnicity {
    public String hmdaethnicityType;
  }

  @CordaSerializable
  public static class HmdaLoan {
    public boolean hmdaHoepaloanStatusIndicator;
    public double hmdarateSpreadPercent;
  }

  @CordaSerializable
  public static class HmdaRace {
    public Extension extension;
  }

  @CordaSerializable
  public static class HmdaRaceDesignation {
    public String hmdaraceDesignationOtherAsianDescription;
    public String hmdaraceDesignationType;
  }

  @CordaSerializable
  public static class HmdaRaceDesignations {
    public HmdaRaceDesignation hmdaRaceDesignation;
  }

  @CordaSerializable
  public static class HmdaRaceDetail {
    public String hmdaraceType;
  }

  @CordaSerializable
  public static class HmdaRaceExtension {
    public HmdaRaceDesignations hmdaRaceDesignations;
    public HmdaRaceDetail hmdaRaceDetail;
  }

  @CordaSerializable
  public static class HmdaRaces {
    public ArrayList<HmdaRace> hmdaRace;
  }

  @CordaSerializable
  public static class Individual {
    public Name name;
  }

  @CordaSerializable
  public static class InterestCalculation {
    public InterestCalculationRules interestCalculationRules;
  }

  @CordaSerializable
  public static class InterestCalculationRule {
    public String interestCalculationPeriodType;
    public String interestCalculationType;
  }

  @CordaSerializable
  public static class InterestCalculationRules {
    public InterestCalculationRule interestCalculationRule;
  }

  @CordaSerializable
  public static class InvestorLoanInformation {
    public int investorOwnershipPercent;
    public String investorRemittanceType;
  }

  @CordaSerializable
  public static class LegalEntity {
    public LegalEntityDetail legalEntityDetail;
  }

  @CordaSerializable
  public static class LegalEntityDetail {
    public String fullName;
  }

  @CordaSerializable
  public static class Loan {
    public Amortization amortization;
    public FormSpecificContents formSpecificContents;
    public HmdaLoan hmdaLoan;
    public InterestCalculation interestCalculation;
    public LoanDetail loanDetail;
    public LoanLevelCredit loanLevelCredit;
    public LoanState loanState;
    public Ltv ltv;
    public Maturity maturity;
    public Payment payment;
    public Qualification qualification;
    public SelectedLoanProduct selectedLoanProduct;
    public TermsOfMortgage termsOfMortgage;
    public Underwriting underwriting;
    public InvestorLoanInformation investorLoanInformation;
    public LoanIdentifiers loanIdentifiers;
    public MiData miData;
    public Servicing servicing;
    public Heloc heloc;
  }

  @CordaSerializable
  public static class LoanDetail {
    public String applicationReceivedDate;
    public boolean assumabilityIndicator;
    public boolean balloonIndicator;
    public int borrowerCount;
    public boolean buydownTemporarySubsidyIndicator;
    public boolean capitalizedLoanIndicator;
    public boolean constructionLoanIndicator;
    public boolean convertibleIndicator;
    public boolean escrowIndicator;
    public boolean interestOnlyIndicator;
    public boolean loanAffordableIndicator;
    public boolean prepaymentPenaltyIndicator;
    public boolean relocationLoanIndicator;
    public boolean sharedEquityIndicator;
    public int totalMortgagedPropertiesCount;
    public boolean mortgageModificationIndicator;
    public boolean warehouseLenderIndicator;
    public boolean helocindicator;
  }

  @CordaSerializable
  public static class LoanIdentifier {
    public int investorCommitmentIdentifier;
    public long mersMinidentifier;
    public int sellerLoanIdentifier;
    public Extension extension;
  }

  @CordaSerializable
  public static class LoanIdentifierExtension {
    public String loanIdentifier;
    public String loanIdentifierType;
  }

  @CordaSerializable
  public static class LoanIdentifiers {
    public ArrayList<LoanIdentifier> loanIdentifier;
  }

  @CordaSerializable
  public static class LoanLevelCredit {
    public LoanLevelCreditDetail loanLevelCreditDetail;
  }

  @CordaSerializable
  public static class LoanLevelCreditDetail {
    public String loanLevelCreditScoreSelectionMethodType;
    public int loanLevelCreditScoreValue;
  }

  @CordaSerializable
  public static class LoanOriginator {
    public String loanOriginatorType;
  }

  @CordaSerializable
  public static class Loans {
    public CombinedLtvs combinedLtvs;
    public ArrayList<Loan> loan;
  }

  @CordaSerializable
  public static class LoanState {
    public String loanStateDate;
    public String loanStateType;
  }

  @CordaSerializable
  public static class Ltv {
    public int baseLtvratioPercent;
    public int ltvratioPercent;
  }

  @CordaSerializable
  public static class Maturity {
    public MaturityRule maturityRule;
  }

  @CordaSerializable
  public static class MaturityRule {
    public String loanMaturityDate;
    public int loanMaturityPeriodCount;
    public String loanMaturityPeriodType;
  }

  @CordaSerializable
  public static class MiData {
    public MiDataDetail miDataDetail;
  }

  @CordaSerializable
  public static class MiDataDetail {
    public String primaryMiabsenceReasonType;
  }

  @CordaSerializable
  public static class Name {
    public String firstName;
    public String lastName;
    public String middleName;
    public String suffixName;
  }

  @CordaSerializable
  public static class Other {
    public LoanIdentifierExtension loanIdentifierExtension;
    public GovernmentMonitoringDetailExtension governmentMonitoringDetailExtension;
    public HmdaRaceExtension hmdaRaceExtension;
    public GovernmentMonitoringExtension governmentMonitoringExtension;
  }

  @CordaSerializable
  public static class Parties {
    public ArrayList<Party> party;
  }

  @CordaSerializable
  public static class Party {
    public Roles roles;
    public Individual individual;
    public TaxpayerIdentifiers taxpayerIdentifiers;
    public LegalEntity legalEntity;
  }

  @CordaSerializable
  public static class PartyRoleIdentifier {
    public long partyRoleIdentifier;
  }

  @CordaSerializable
  public static class PartyRoleIdentifiers {
    public PartyRoleIdentifier partyRoleIdentifier;
  }

  @CordaSerializable
  public static class Payment {
    public PaymentRule paymentRule;
    public PaymentSummary paymentSummary;
  }

  @CordaSerializable
  public static class PaymentRule {
    public double initialPrincipalAndInterestPaymentAmount;
    public String paymentFrequencyType;
    public String scheduledFirstPaymentDate;
  }

  @CordaSerializable
  public static class PaymentSummary {
    public String lastPaidInstallmentDueDate;
    public int upbamount;
  }

  @CordaSerializable
  public static class PriceLock {
    public String priceLockDatetime;
  }

  @CordaSerializable
  public static class PriceLocks {
    public PriceLock priceLock;
  }

  @CordaSerializable
  public static class Project {
    public ProjectDetail projectDetail;
  }

  @CordaSerializable
  public static class ProjectDetail {
    public String projectClassificationIdentifier;
    public boolean pudindicator;
  }

  @CordaSerializable
  public static class Properties {
    public Property property;
  }

  @CordaSerializable
  public static class Property {
    public Address address;
    public FloodDetermination floodDetermination;
    public Project project;
    public PropertyDetail propertyDetail;
    public PropertyValuations propertyValuations;
  }

  @CordaSerializable
  public static class PropertyDetail {
    public String attachmentType;
    public String constructionMethodType;
    public int financedUnitCount;
    public String propertyEstateType;
    public boolean propertyFloodInsuranceIndicator;
    public int propertyStructureBuiltYear;
    public String propertyUsageType;
  }

  @CordaSerializable
  public static class PropertyValuation {
    public PropertyValuationDetail propertyValuationDetail;
  }

  @CordaSerializable
  public static class PropertyValuationDetail {
    public int appraisalIdentifier;
    public int propertyValuationAmount;
    public String propertyValuationEffectiveDate;
    public String propertyValuationFormType;
    public String propertyValuationMethodType;
  }

  @CordaSerializable
  public static class PropertyValuations {
    public PropertyValuation propertyValuation;
  }

  @CordaSerializable
  public static class Qualification {
    public int borrowerReservesMonthlyPaymentCount;
    public int totalLiabilitiesMonthlyPaymentAmount;
    public int totalMonthlyIncomeAmount;
    public int totalMonthlyProposedHousingExpenseAmount;
  }

  @CordaSerializable
  public static class Role {
    public Appraiser appraiser;
    public RoleDetail roleDetail;
    public AppraiserSupervisor appraiserSupervisor;
    public Borrower borrower;
    public LoanOriginator loanOriginator;
  }

  @CordaSerializable
  public static class RoleDetail {
    public String partyRoleType;
  }

  @CordaSerializable
  public static class Roles {
    public Role role;
    public PartyRoleIdentifiers partyRoleIdentifiers;
  }

  @CordaSerializable
  public static class Root {
    public DealSets dealSets;
  }

  @CordaSerializable
  public static class SelectedLoanProduct {
    public PriceLocks priceLocks;
  }

  @CordaSerializable
  public static class Servicing {
    public DelinquencySummary delinquencySummary;
  }

  @CordaSerializable
  public static class TaxpayerIdentifier {
    public String taxpayerIdentifierType;
    public int taxpayerIdentifierValue;
  }

  @CordaSerializable
  public static class TaxpayerIdentifiers {
    public TaxpayerIdentifier taxpayerIdentifier;
  }

  @CordaSerializable
  public static class TermsOfMortgage {
    public String lienPriorityType;
    public String loanPurposeType;
    public String mortgageType;
    public int noteAmount;
    public String noteDate;
    public int noteRatePercent;
  }

  @CordaSerializable
  public static class Underwriting {
    public AutomatedUnderwritings automatedUnderwritings;
    public UnderwritingDetail underwritingDetail;
  }

  @CordaSerializable
  public static class UnderwritingDetail {
    public boolean loanManualUnderwritingIndicator;
  }

  @CordaSerializable
  public static class Urla {
    public UrlaDetail urlaDetail;
  }

  @CordaSerializable
  public static class UrlaDetail {
    public int borrowerPaidDiscountPointsTotalAmount;
    public int purchasePriceAmount;
  }
}
