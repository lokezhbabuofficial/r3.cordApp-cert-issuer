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
    public String getAddressLineText() {
      return addressLineText;
    }
    public void setAddressLineText(String addressLineText) {
      this.addressLineText = addressLineText;
    }
    public String getCityName() {
      return cityName;
    }
    public void setCityName(String cityName) {
      this.cityName = cityName;
    }
    public int getPostalCode() {
      return postalCode;
    }
    public void setPostalCode(int postalCode) {
      this.postalCode = postalCode;
    }
    public String getStateCode() {
      return stateCode;
    }
    public void setStateCode(String stateCode) {
      this.stateCode = stateCode;
    }
  }

  @CordaSerializable
  public static class Amortization {
    public AmortizationRule amortizationRule;

    public AmortizationRule getAmortizationRule() {
      return amortizationRule;
    }

    public void setAmortizationRule(AmortizationRule amortizationRule) {
      this.amortizationRule = amortizationRule;
    }
  }

  @CordaSerializable
  public static class AmortizationRule {
    public int loanAmortizationPeriodCount;
    public String loanAmortizationPeriodType;
    public String loanAmortizationType;
    
    public int getLoanAmortizationPeriodCount() {
      return loanAmortizationPeriodCount;
    }
    public void setLoanAmortizationPeriodCount(int loanAmortizationPeriodCount) {
      this.loanAmortizationPeriodCount = loanAmortizationPeriodCount;
    }
    public String getLoanAmortizationPeriodType() {
      return loanAmortizationPeriodType;
    }
    public void setLoanAmortizationPeriodType(String loanAmortizationPeriodType) {
      this.loanAmortizationPeriodType = loanAmortizationPeriodType;
    }
    public String getLoanAmortizationType() {
      return loanAmortizationType;
    }
    public void setLoanAmortizationType(String loanAmortizationType) {
      this.loanAmortizationType = loanAmortizationType;
    }
  }

  @CordaSerializable
  public static class Appraiser {
    public AppraiserLicense appraiserLicense;

    public AppraiserLicense getAppraiserLicense() {
      return appraiserLicense;
    }

    public void setAppraiserLicense(AppraiserLicense appraiserLicense) {
      this.appraiserLicense = appraiserLicense;
    }
  }

  @CordaSerializable
  public static class AppraiserLicense {
    public String appraiserLicenseIdentifier;

    public String getAppraiserLicenseIdentifier() {
      return appraiserLicenseIdentifier;
    }

    public void setAppraiserLicenseIdentifier(String appraiserLicenseIdentifier) {
      this.appraiserLicenseIdentifier = appraiserLicenseIdentifier;
    }
  }

  @CordaSerializable
  public static class AppraiserSupervisor {
    public AppraiserLicense appraiserLicense;

    public AppraiserLicense getAppraiserLicense() {
      return appraiserLicense;
    }

    public void setAppraiserLicense(AppraiserLicense appraiserLicense) {
      this.appraiserLicense = appraiserLicense;
    }
  }

  @CordaSerializable
  public static class AutomatedUnderwriting {
    public long automatedUnderwritingCaseIdentifier;

    public long getAutomatedUnderwritingCaseIdentifier() {
      return automatedUnderwritingCaseIdentifier;
    }

    public void setAutomatedUnderwritingCaseIdentifier(long automatedUnderwritingCaseIdentifier) {
      this.automatedUnderwritingCaseIdentifier = automatedUnderwritingCaseIdentifier;
    }
  }

  @CordaSerializable
  public static class AutomatedUnderwritings {
    public AutomatedUnderwriting automatedUnderwriting;

    public AutomatedUnderwriting getAutomatedUnderwriting() {
      return automatedUnderwriting;
    }

    public void setAutomatedUnderwriting(AutomatedUnderwriting automatedUnderwriting) {
      this.automatedUnderwriting = automatedUnderwriting;
    }
  }

  @CordaSerializable
  public static class Borrower {
    public BorrowerDetail borrowerDetail;
    public CreditScores creditScores;
    public Declaration declaration;
    public Employers employers;
    public GovernmentMonitoring governmentMonitoring;
    public BorrowerDetail getBorrowerDetail() {
      return borrowerDetail;
    }
    public void setBorrowerDetail(BorrowerDetail borrowerDetail) {
      this.borrowerDetail = borrowerDetail;
    }
    public CreditScores getCreditScores() {
      return creditScores;
    }
    public void setCreditScores(CreditScores creditScores) {
      this.creditScores = creditScores;
    }
    public Declaration getDeclaration() {
      return declaration;
    }
    public void setDeclaration(Declaration declaration) {
      this.declaration = declaration;
    }
    public Employers getEmployers() {
      return employers;
    }
    public void setEmployers(Employers employers) {
      this.employers = employers;
    }
    public GovernmentMonitoring getGovernmentMonitoring() {
      return governmentMonitoring;
    }
    public void setGovernmentMonitoring(GovernmentMonitoring governmentMonitoring) {
      this.governmentMonitoring = governmentMonitoring;
    }
  }

  @CordaSerializable
  public static class BorrowerDetail {
    public int borrowerAgeAtApplicationYearsCount;
    public String borrowerBirthDate;
    public String borrowerClassificationType;
    public boolean borrowerMailToAddressSameAsPropertyIndicator;
    public int borrowerQualifyingIncomeAmount;
    public int getBorrowerAgeAtApplicationYearsCount() {
      return borrowerAgeAtApplicationYearsCount;
    }
    public void setBorrowerAgeAtApplicationYearsCount(int borrowerAgeAtApplicationYearsCount) {
      this.borrowerAgeAtApplicationYearsCount = borrowerAgeAtApplicationYearsCount;
    }
    public String getBorrowerBirthDate() {
      return borrowerBirthDate;
    }
    public void setBorrowerBirthDate(String borrowerBirthDate) {
      this.borrowerBirthDate = borrowerBirthDate;
    }
    public String getBorrowerClassificationType() {
      return borrowerClassificationType;
    }
    public void setBorrowerClassificationType(String borrowerClassificationType) {
      this.borrowerClassificationType = borrowerClassificationType;
    }
    public boolean isBorrowerMailToAddressSameAsPropertyIndicator() {
      return borrowerMailToAddressSameAsPropertyIndicator;
    }
    public void setBorrowerMailToAddressSameAsPropertyIndicator(boolean borrowerMailToAddressSameAsPropertyIndicator) {
      this.borrowerMailToAddressSameAsPropertyIndicator = borrowerMailToAddressSameAsPropertyIndicator;
    }
    public int getBorrowerQualifyingIncomeAmount() {
      return borrowerQualifyingIncomeAmount;
    }
    public void setBorrowerQualifyingIncomeAmount(int borrowerQualifyingIncomeAmount) {
      this.borrowerQualifyingIncomeAmount = borrowerQualifyingIncomeAmount;
    }
  }

  @CordaSerializable
  public static class Collateral {
    public Properties properties;

    public Properties getProperties() {
      return properties;
    }

    public void setProperties(Properties properties) {
      this.properties = properties;
    }
  }

  @CordaSerializable
  public static class Collaterals {
    public Collateral collateral;

    public Collateral getCollateral() {
      return collateral;
    }

    public void setCollateral(Collateral collateral) {
      this.collateral = collateral;
    }
  }

  @CordaSerializable
  public static class CombinedLtv {
    public int combinedLtvratioPercent;
    public int homeEquityCombinedLtvratioPercent;
    public int getCombinedLtvratioPercent() {
      return combinedLtvratioPercent;
    }
    public void setCombinedLtvratioPercent(int combinedLtvratioPercent) {
      this.combinedLtvratioPercent = combinedLtvratioPercent;
    }
    public int getHomeEquityCombinedLtvratioPercent() {
      return homeEquityCombinedLtvratioPercent;
    }
    public void setHomeEquityCombinedLtvratioPercent(int homeEquityCombinedLtvratioPercent) {
      this.homeEquityCombinedLtvratioPercent = homeEquityCombinedLtvratioPercent;
    }
  }

  @CordaSerializable
  public static class CombinedLtvs {
    public CombinedLtv combinedLtv;

    public CombinedLtv getCombinedLtv() {
      return combinedLtv;
    }

    public void setCombinedLtv(CombinedLtv combinedLtv) {
      this.combinedLtv = combinedLtv;
    }
  }

  @CordaSerializable
  public static class CreditScore {
    public CreditScoreDetail creditScoreDetail;

    public CreditScoreDetail getCreditScoreDetail() {
      return creditScoreDetail;
    }

    public void setCreditScoreDetail(CreditScoreDetail creditScoreDetail) {
      this.creditScoreDetail = creditScoreDetail;
    }
  }

  @CordaSerializable
  public static class CreditScoreDetail {
    public long creditReportIdentifier;
    public boolean creditRepositorySourceIndicator;
    public String creditRepositorySourceType;
    public int creditScoreValue;
    public long getCreditReportIdentifier() {
      return creditReportIdentifier;
    }
    public void setCreditReportIdentifier(long creditReportIdentifier) {
      this.creditReportIdentifier = creditReportIdentifier;
    }
    public boolean isCreditRepositorySourceIndicator() {
      return creditRepositorySourceIndicator;
    }
    public void setCreditRepositorySourceIndicator(boolean creditRepositorySourceIndicator) {
      this.creditRepositorySourceIndicator = creditRepositorySourceIndicator;
    }
    public String getCreditRepositorySourceType() {
      return creditRepositorySourceType;
    }
    public void setCreditRepositorySourceType(String creditRepositorySourceType) {
      this.creditRepositorySourceType = creditRepositorySourceType;
    }
    public int getCreditScoreValue() {
      return creditScoreValue;
    }
    public void setCreditScoreValue(int creditScoreValue) {
      this.creditScoreValue = creditScoreValue;
    }
  }

  @CordaSerializable
  public static class CreditScores {
    public CreditScore creditScore;

    public CreditScore getCreditScore() {
      return creditScore;
    }

    public void setCreditScore(CreditScore creditScore) {
      this.creditScore = creditScore;
    }
  }

  @CordaSerializable
  public static class Deal {
    public Collaterals collaterals;
    public Loans loans;
    public Parties parties;
    public Collaterals getCollaterals() {
      return collaterals;
    }
    public void setCollaterals(Collaterals collaterals) {
      this.collaterals = collaterals;
    }
    public Loans getLoans() {
      return loans;
    }
    public void setLoans(Loans loans) {
      this.loans = loans;
    }
    public Parties getParties() {
      return parties;
    }
    public void setParties(Parties parties) {
      this.parties = parties;
    }
  }

  @CordaSerializable
  public static class Deals {
    public Deal deal;

    public Deal getDeal() {
      return deal;
    }

    public void setDeal(Deal deal) {
      this.deal = deal;
    }
  }

  @CordaSerializable
  public static class DealSet {
    public Deals deals;

    public Deals getDeals() {
      return deals;
    }

    public void setDeals(Deals deals) {
      this.deals = deals;
    }
  }

  @BelongsToContract(MISMO3_0FannieMaeULDDContract.class)
  public static class DealSets implements LinearState {
    // private DealSet dealSet;
    // private Parties parties;

    private UniqueIdentifier linerId;
    private net.corda.core.identity.Party issuer;
    private String loanString;

    public String getLoanString() {
      return loanString;
    }

    public void setLoanString(String loanString) {
      this.loanString = loanString;
    }

    @Override
    public List<AbstractParty> getParticipants() {
      return Arrays.asList(issuer);
    }

    @Override
    public UniqueIdentifier getLinearId() {
      return linerId;
    }

    public DealSets(UniqueIdentifier linerId, net.corda.core.identity.Party issuer, String loanString) {
      this.issuer = issuer;
      this.linerId = linerId;
      this.loanString = loanString;
    }

    // public DealSets(DealSet dealSet, Parties parties, UniqueIdentifier linerId, net.corda.core.identity.Party issuer) {
    //   this.dealSet = dealSet;
    //   this.parties = parties;
    //   this.issuer = issuer;
    //   this.linerId = linerId;
    // }

    // public DealSet getDealSet() {
    //   return dealSet;
    // }

    // public void setDealSet(DealSet dealSet) {
    //   this.dealSet = dealSet;
    // }

    // public Parties getParties() {
    //   return parties;
    // }

    // public void setParties(Parties parties) {
    //   this.parties = parties;
    // }

    public UniqueIdentifier getLinerId() {
      return linerId;
    }

    public void setLinerId(UniqueIdentifier linerId) {
      this.linerId = linerId;
    }

    public net.corda.core.identity.Party getIssuer() {
      return issuer;
    }

    public void setIssuer(net.corda.core.identity.Party issuer) {
      this.issuer = issuer;
    }
  }

  @CordaSerializable
  public static class Declaration {
    public DeclarationDetail declarationDetail;

    public DeclarationDetail getDeclarationDetail() {
      return declarationDetail;
    }

    public void setDeclarationDetail(DeclarationDetail declarationDetail) {
      this.declarationDetail = declarationDetail;
    }
  }

  @CordaSerializable
  public static class DeclarationDetail {
    public boolean bankruptcyIndicator;
    public boolean borrowerFirstTimeHomebuyerIndicator;
    public String citizenshipResidencyType;
    public boolean loanForeclosureOrJudgmentIndicator;
    public boolean isBankruptcyIndicator() {
      return bankruptcyIndicator;
    }
    public void setBankruptcyIndicator(boolean bankruptcyIndicator) {
      this.bankruptcyIndicator = bankruptcyIndicator;
    }
    public boolean isBorrowerFirstTimeHomebuyerIndicator() {
      return borrowerFirstTimeHomebuyerIndicator;
    }
    public void setBorrowerFirstTimeHomebuyerIndicator(boolean borrowerFirstTimeHomebuyerIndicator) {
      this.borrowerFirstTimeHomebuyerIndicator = borrowerFirstTimeHomebuyerIndicator;
    }
    public String getCitizenshipResidencyType() {
      return citizenshipResidencyType;
    }
    public void setCitizenshipResidencyType(String citizenshipResidencyType) {
      this.citizenshipResidencyType = citizenshipResidencyType;
    }
    public boolean isLoanForeclosureOrJudgmentIndicator() {
      return loanForeclosureOrJudgmentIndicator;
    }
    public void setLoanForeclosureOrJudgmentIndicator(boolean loanForeclosureOrJudgmentIndicator) {
      this.loanForeclosureOrJudgmentIndicator = loanForeclosureOrJudgmentIndicator;
    }
  }

  @CordaSerializable
  public static class DelinquencySummary {
    public int delinquentPaymentsOverPastTwelveMonthsCount;

    public int getDelinquentPaymentsOverPastTwelveMonthsCount() {
      return delinquentPaymentsOverPastTwelveMonthsCount;
    }

    public void setDelinquentPaymentsOverPastTwelveMonthsCount(int delinquentPaymentsOverPastTwelveMonthsCount) {
      this.delinquentPaymentsOverPastTwelveMonthsCount = delinquentPaymentsOverPastTwelveMonthsCount;
    }
  }

  @CordaSerializable
  public static class Employer {
    public Employment employment;

    public Employment getEmployment() {
      return employment;
    }

    public void setEmployment(Employment employment) {
      this.employment = employment;
    }
  }

  @CordaSerializable
  public static class Employers {
    public Employer employer;

    public Employer getEmployer() {
      return employer;
    }

    public void setEmployer(Employer employer) {
      this.employer = employer;
    }
  }

  @CordaSerializable
  public static class Employment {
    public boolean employmentBorrowerSelfEmployedIndicator;

    public boolean isEmploymentBorrowerSelfEmployedIndicator() {
      return employmentBorrowerSelfEmployedIndicator;
    }

    public void setEmploymentBorrowerSelfEmployedIndicator(boolean employmentBorrowerSelfEmployedIndicator) {
      this.employmentBorrowerSelfEmployedIndicator = employmentBorrowerSelfEmployedIndicator;
    }
  }

  @CordaSerializable
  public static class Extension {
    public Other other;

    public Other getOther() {
      return other;
    }

    public void setOther(Other other) {
      this.other = other;
    }
  }

  @CordaSerializable
  public static class FloodDetermination {
    public FloodDeterminationDetail floodDeterminationDetail;

    public FloodDeterminationDetail getFloodDeterminationDetail() {
      return floodDeterminationDetail;
    }

    public void setFloodDeterminationDetail(FloodDeterminationDetail floodDeterminationDetail) {
      this.floodDeterminationDetail = floodDeterminationDetail;
    }
  }

  @CordaSerializable
  public static class FloodDeterminationDetail {
    public boolean specialFloodHazardAreaIndicator;

    public boolean isSpecialFloodHazardAreaIndicator() {
      return specialFloodHazardAreaIndicator;
    }

    public void setSpecialFloodHazardAreaIndicator(boolean specialFloodHazardAreaIndicator) {
      this.specialFloodHazardAreaIndicator = specialFloodHazardAreaIndicator;
    }
  }

  @CordaSerializable
  public static class FormSpecificContent {
    public Urla urla;

    public Urla getUrla() {
      return urla;
    }

    public void setUrla(Urla urla) {
      this.urla = urla;
    }
  }

  @CordaSerializable
  public static class FormSpecificContents {
    public FormSpecificContent formSpecificContent;

    public FormSpecificContent getFormSpecificContent() {
      return formSpecificContent;
    }

    public void setFormSpecificContent(FormSpecificContent formSpecificContent) {
      this.formSpecificContent = formSpecificContent;
    }
  }

  @CordaSerializable
  public static class GovernmentMonitoring {
    public GovernmentMonitoringDetail governmentMonitoringDetail;
    public HmdaRaces hmdaRaces;
    public Extension extension;
    public GovernmentMonitoringDetail getGovernmentMonitoringDetail() {
      return governmentMonitoringDetail;
    }
    public void setGovernmentMonitoringDetail(GovernmentMonitoringDetail governmentMonitoringDetail) {
      this.governmentMonitoringDetail = governmentMonitoringDetail;
    }
    public HmdaRaces getHmdaRaces() {
      return hmdaRaces;
    }
    public void setHmdaRaces(HmdaRaces hmdaRaces) {
      this.hmdaRaces = hmdaRaces;
    }
    public Extension getExtension() {
      return extension;
    }
    public void setExtension(Extension extension) {
      this.extension = extension;
    }
  }

  @CordaSerializable
  public static class GovernmentMonitoringDetail {
    public Extension extension;

    public Extension getExtension() {
      return extension;
    }

    public void setExtension(Extension extension) {
      this.extension = extension;
    }
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
    public boolean isHmdaethnicityCollectedBasedOnVisualObservationOrSurnameIndicator() {
      return hmdaethnicityCollectedBasedOnVisualObservationOrSurnameIndicator;
    }
    public void setHmdaethnicityCollectedBasedOnVisualObservationOrSurnameIndicator(
        boolean hmdaethnicityCollectedBasedOnVisualObservationOrSurnameIndicator) {
      this.hmdaethnicityCollectedBasedOnVisualObservationOrSurnameIndicator = hmdaethnicityCollectedBasedOnVisualObservationOrSurnameIndicator;
    }
    public boolean isHmdaethnicityRefusalIndicator() {
      return hmdaethnicityRefusalIndicator;
    }
    public void setHmdaethnicityRefusalIndicator(boolean hmdaethnicityRefusalIndicator) {
      this.hmdaethnicityRefusalIndicator = hmdaethnicityRefusalIndicator;
    }
    public boolean isHmdagenderCollectedBasedOnVisualObservationOrNameIndicator() {
      return hmdagenderCollectedBasedOnVisualObservationOrNameIndicator;
    }
    public void setHmdagenderCollectedBasedOnVisualObservationOrNameIndicator(
        boolean hmdagenderCollectedBasedOnVisualObservationOrNameIndicator) {
      this.hmdagenderCollectedBasedOnVisualObservationOrNameIndicator = hmdagenderCollectedBasedOnVisualObservationOrNameIndicator;
    }
    public boolean isHmdagenderRefusalIndicator() {
      return hmdagenderRefusalIndicator;
    }
    public void setHmdagenderRefusalIndicator(boolean hmdagenderRefusalIndicator) {
      this.hmdagenderRefusalIndicator = hmdagenderRefusalIndicator;
    }
    public String getHmdagenderType() {
      return hmdagenderType;
    }
    public void setHmdagenderType(String hmdagenderType) {
      this.hmdagenderType = hmdagenderType;
    }
    public boolean isHmdaraceCollectedBasedOnVisualObservationOrSurnameIndicator() {
      return hmdaraceCollectedBasedOnVisualObservationOrSurnameIndicator;
    }
    public void setHmdaraceCollectedBasedOnVisualObservationOrSurnameIndicator(
        boolean hmdaraceCollectedBasedOnVisualObservationOrSurnameIndicator) {
      this.hmdaraceCollectedBasedOnVisualObservationOrSurnameIndicator = hmdaraceCollectedBasedOnVisualObservationOrSurnameIndicator;
    }
    public boolean isHmdaraceRefusalIndicator() {
      return hmdaraceRefusalIndicator;
    }
    public void setHmdaraceRefusalIndicator(boolean hmdaraceRefusalIndicator) {
      this.hmdaraceRefusalIndicator = hmdaraceRefusalIndicator;
    }
  }

  @CordaSerializable
  public static class GovernmentMonitoringExtension {
    public HmdaEthnicities hmdaEthnicities;

    public HmdaEthnicities getHmdaEthnicities() {
      return hmdaEthnicities;
    }

    public void setHmdaEthnicities(HmdaEthnicities hmdaEthnicities) {
      this.hmdaEthnicities = hmdaEthnicities;
    }
  }

  @CordaSerializable
  public static class Heloc {
    public HelocOccurrences helocOccurrences;

    public HelocOccurrences getHelocOccurrences() {
      return helocOccurrences;
    }

    public void setHelocOccurrences(HelocOccurrences helocOccurrences) {
      this.helocOccurrences = helocOccurrences;
    }
  }

  @CordaSerializable
  public static class HelocOccurrence {
    public int currentHelocmaximumBalanceAmount;
    public int helocbalanceAmount;
    public int getCurrentHelocmaximumBalanceAmount() {
      return currentHelocmaximumBalanceAmount;
    }
    public void setCurrentHelocmaximumBalanceAmount(int currentHelocmaximumBalanceAmount) {
      this.currentHelocmaximumBalanceAmount = currentHelocmaximumBalanceAmount;
    }
    public int getHelocbalanceAmount() {
      return helocbalanceAmount;
    }
    public void setHelocbalanceAmount(int helocbalanceAmount) {
      this.helocbalanceAmount = helocbalanceAmount;
    }
  }

  @CordaSerializable
  public static class HelocOccurrences {
    public HelocOccurrence helocOccurrence;

    public HelocOccurrence getHelocOccurrence() {
      return helocOccurrence;
    }

    public void setHelocOccurrence(HelocOccurrence helocOccurrence) {
      this.helocOccurrence = helocOccurrence;
    }
  }

  @CordaSerializable
  public static class HmdaEthnicities {
    public HmdaEthnicity hmdaEthnicity;

    public HmdaEthnicity getHmdaEthnicity() {
      return hmdaEthnicity;
    }

    public void setHmdaEthnicity(HmdaEthnicity hmdaEthnicity) {
      this.hmdaEthnicity = hmdaEthnicity;
    }
  }

  @CordaSerializable
  public static class HmdaEthnicity {
    public String hmdaethnicityType;

    public String getHmdaethnicityType() {
      return hmdaethnicityType;
    }

    public void setHmdaethnicityType(String hmdaethnicityType) {
      this.hmdaethnicityType = hmdaethnicityType;
    }
  }

  @CordaSerializable
  public static class HmdaLoan {
    public boolean hmdaHoepaloanStatusIndicator;
    public double hmdarateSpreadPercent;
    public boolean isHmdaHoepaloanStatusIndicator() {
      return hmdaHoepaloanStatusIndicator;
    }
    public void setHmdaHoepaloanStatusIndicator(boolean hmdaHoepaloanStatusIndicator) {
      this.hmdaHoepaloanStatusIndicator = hmdaHoepaloanStatusIndicator;
    }
    public double getHmdarateSpreadPercent() {
      return hmdarateSpreadPercent;
    }
    public void setHmdarateSpreadPercent(double hmdarateSpreadPercent) {
      this.hmdarateSpreadPercent = hmdarateSpreadPercent;
    }
  }

  @CordaSerializable
  public static class HmdaRace {
    public Extension extension;

    public Extension getExtension() {
      return extension;
    }

    public void setExtension(Extension extension) {
      this.extension = extension;
    }
  }

  @CordaSerializable
  public static class HmdaRaceDesignation {
    public String hmdaraceDesignationOtherAsianDescription;
    public String hmdaraceDesignationType;
    public String getHmdaraceDesignationOtherAsianDescription() {
      return hmdaraceDesignationOtherAsianDescription;
    }
    public void setHmdaraceDesignationOtherAsianDescription(String hmdaraceDesignationOtherAsianDescription) {
      this.hmdaraceDesignationOtherAsianDescription = hmdaraceDesignationOtherAsianDescription;
    }
    public String getHmdaraceDesignationType() {
      return hmdaraceDesignationType;
    }
    public void setHmdaraceDesignationType(String hmdaraceDesignationType) {
      this.hmdaraceDesignationType = hmdaraceDesignationType;
    }
  }

  @CordaSerializable
  public static class HmdaRaceDesignations {
    public HmdaRaceDesignation hmdaRaceDesignation;

    public HmdaRaceDesignation getHmdaRaceDesignation() {
      return hmdaRaceDesignation;
    }

    public void setHmdaRaceDesignation(HmdaRaceDesignation hmdaRaceDesignation) {
      this.hmdaRaceDesignation = hmdaRaceDesignation;
    }
  }

  @CordaSerializable
  public static class HmdaRaceDetail {
    public String hmdaraceType;

    public String getHmdaraceType() {
      return hmdaraceType;
    }

    public void setHmdaraceType(String hmdaraceType) {
      this.hmdaraceType = hmdaraceType;
    }
  }

  @CordaSerializable
  public static class HmdaRaceExtension {
    public HmdaRaceDesignations hmdaRaceDesignations;
    public HmdaRaceDetail hmdaRaceDetail;
    public HmdaRaceDesignations getHmdaRaceDesignations() {
      return hmdaRaceDesignations;
    }
    public void setHmdaRaceDesignations(HmdaRaceDesignations hmdaRaceDesignations) {
      this.hmdaRaceDesignations = hmdaRaceDesignations;
    }
    public HmdaRaceDetail getHmdaRaceDetail() {
      return hmdaRaceDetail;
    }
    public void setHmdaRaceDetail(HmdaRaceDetail hmdaRaceDetail) {
      this.hmdaRaceDetail = hmdaRaceDetail;
    }
  }

  @CordaSerializable
  public static class HmdaRaces {
    public ArrayList<HmdaRace> hmdaRace;

    public ArrayList<HmdaRace> getHmdaRace() {
      return hmdaRace;
    }

    public void setHmdaRace(ArrayList<HmdaRace> hmdaRace) {
      this.hmdaRace = hmdaRace;
    }
  }

  @CordaSerializable
  public static class Individual {
    public Name name;

    public Name getName() {
      return name;
    }

    public void setName(Name name) {
      this.name = name;
    }
  }

  @CordaSerializable
  public static class InterestCalculation {
    public InterestCalculationRules interestCalculationRules;

    public InterestCalculationRules getInterestCalculationRules() {
      return interestCalculationRules;
    }

    public void setInterestCalculationRules(InterestCalculationRules interestCalculationRules) {
      this.interestCalculationRules = interestCalculationRules;
    }
  }

  @CordaSerializable
  public static class InterestCalculationRule {
    public String interestCalculationPeriodType;
    public String interestCalculationType;
    public String getInterestCalculationPeriodType() {
      return interestCalculationPeriodType;
    }
    public void setInterestCalculationPeriodType(String interestCalculationPeriodType) {
      this.interestCalculationPeriodType = interestCalculationPeriodType;
    }
    public String getInterestCalculationType() {
      return interestCalculationType;
    }
    public void setInterestCalculationType(String interestCalculationType) {
      this.interestCalculationType = interestCalculationType;
    }
  }

  @CordaSerializable
  public static class InterestCalculationRules {
    public InterestCalculationRule interestCalculationRule;

    public InterestCalculationRule getInterestCalculationRule() {
      return interestCalculationRule;
    }

    public void setInterestCalculationRule(InterestCalculationRule interestCalculationRule) {
      this.interestCalculationRule = interestCalculationRule;
    }
  }

  @CordaSerializable
  public static class InvestorLoanInformation {
    public int investorOwnershipPercent;
    public String investorRemittanceType;
    public int getInvestorOwnershipPercent() {
      return investorOwnershipPercent;
    }
    public void setInvestorOwnershipPercent(int investorOwnershipPercent) {
      this.investorOwnershipPercent = investorOwnershipPercent;
    }
    public String getInvestorRemittanceType() {
      return investorRemittanceType;
    }
    public void setInvestorRemittanceType(String investorRemittanceType) {
      this.investorRemittanceType = investorRemittanceType;
    }
  }

  @CordaSerializable
  public static class LegalEntity {
    public LegalEntityDetail legalEntityDetail;

    public LegalEntityDetail getLegalEntityDetail() {
      return legalEntityDetail;
    }

    public void setLegalEntityDetail(LegalEntityDetail legalEntityDetail) {
      this.legalEntityDetail = legalEntityDetail;
    }
  }

  @CordaSerializable
  public static class LegalEntityDetail {
    public String fullName;

    public String getFullName() {
      return fullName;
    }

    public void setFullName(String fullName) {
      this.fullName = fullName;
    }
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
    public Amortization getAmortization() {
      return amortization;
    }
    public void setAmortization(Amortization amortization) {
      this.amortization = amortization;
    }
    public FormSpecificContents getFormSpecificContents() {
      return formSpecificContents;
    }
    public void setFormSpecificContents(FormSpecificContents formSpecificContents) {
      this.formSpecificContents = formSpecificContents;
    }
    public HmdaLoan getHmdaLoan() {
      return hmdaLoan;
    }
    public void setHmdaLoan(HmdaLoan hmdaLoan) {
      this.hmdaLoan = hmdaLoan;
    }
    public InterestCalculation getInterestCalculation() {
      return interestCalculation;
    }
    public void setInterestCalculation(InterestCalculation interestCalculation) {
      this.interestCalculation = interestCalculation;
    }
    public LoanDetail getLoanDetail() {
      return loanDetail;
    }
    public void setLoanDetail(LoanDetail loanDetail) {
      this.loanDetail = loanDetail;
    }
    public LoanLevelCredit getLoanLevelCredit() {
      return loanLevelCredit;
    }
    public void setLoanLevelCredit(LoanLevelCredit loanLevelCredit) {
      this.loanLevelCredit = loanLevelCredit;
    }
    public LoanState getLoanState() {
      return loanState;
    }
    public void setLoanState(LoanState loanState) {
      this.loanState = loanState;
    }
    public Ltv getLtv() {
      return ltv;
    }
    public void setLtv(Ltv ltv) {
      this.ltv = ltv;
    }
    public Maturity getMaturity() {
      return maturity;
    }
    public void setMaturity(Maturity maturity) {
      this.maturity = maturity;
    }
    public Payment getPayment() {
      return payment;
    }
    public void setPayment(Payment payment) {
      this.payment = payment;
    }
    public Qualification getQualification() {
      return qualification;
    }
    public void setQualification(Qualification qualification) {
      this.qualification = qualification;
    }
    public SelectedLoanProduct getSelectedLoanProduct() {
      return selectedLoanProduct;
    }
    public void setSelectedLoanProduct(SelectedLoanProduct selectedLoanProduct) {
      this.selectedLoanProduct = selectedLoanProduct;
    }
    public TermsOfMortgage getTermsOfMortgage() {
      return termsOfMortgage;
    }
    public void setTermsOfMortgage(TermsOfMortgage termsOfMortgage) {
      this.termsOfMortgage = termsOfMortgage;
    }
    public Underwriting getUnderwriting() {
      return underwriting;
    }
    public void setUnderwriting(Underwriting underwriting) {
      this.underwriting = underwriting;
    }
    public InvestorLoanInformation getInvestorLoanInformation() {
      return investorLoanInformation;
    }
    public void setInvestorLoanInformation(InvestorLoanInformation investorLoanInformation) {
      this.investorLoanInformation = investorLoanInformation;
    }
    public LoanIdentifiers getLoanIdentifiers() {
      return loanIdentifiers;
    }
    public void setLoanIdentifiers(LoanIdentifiers loanIdentifiers) {
      this.loanIdentifiers = loanIdentifiers;
    }
    public MiData getMiData() {
      return miData;
    }
    public void setMiData(MiData miData) {
      this.miData = miData;
    }
    public Servicing getServicing() {
      return servicing;
    }
    public void setServicing(Servicing servicing) {
      this.servicing = servicing;
    }
    public Heloc getHeloc() {
      return heloc;
    }
    public void setHeloc(Heloc heloc) {
      this.heloc = heloc;
    }
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
    public String getApplicationReceivedDate() {
      return applicationReceivedDate;
    }
    public void setApplicationReceivedDate(String applicationReceivedDate) {
      this.applicationReceivedDate = applicationReceivedDate;
    }
    public boolean isAssumabilityIndicator() {
      return assumabilityIndicator;
    }
    public void setAssumabilityIndicator(boolean assumabilityIndicator) {
      this.assumabilityIndicator = assumabilityIndicator;
    }
    public boolean isBalloonIndicator() {
      return balloonIndicator;
    }
    public void setBalloonIndicator(boolean balloonIndicator) {
      this.balloonIndicator = balloonIndicator;
    }
    public int getBorrowerCount() {
      return borrowerCount;
    }
    public void setBorrowerCount(int borrowerCount) {
      this.borrowerCount = borrowerCount;
    }
    public boolean isBuydownTemporarySubsidyIndicator() {
      return buydownTemporarySubsidyIndicator;
    }
    public void setBuydownTemporarySubsidyIndicator(boolean buydownTemporarySubsidyIndicator) {
      this.buydownTemporarySubsidyIndicator = buydownTemporarySubsidyIndicator;
    }
    public boolean isCapitalizedLoanIndicator() {
      return capitalizedLoanIndicator;
    }
    public void setCapitalizedLoanIndicator(boolean capitalizedLoanIndicator) {
      this.capitalizedLoanIndicator = capitalizedLoanIndicator;
    }
    public boolean isConstructionLoanIndicator() {
      return constructionLoanIndicator;
    }
    public void setConstructionLoanIndicator(boolean constructionLoanIndicator) {
      this.constructionLoanIndicator = constructionLoanIndicator;
    }
    public boolean isConvertibleIndicator() {
      return convertibleIndicator;
    }
    public void setConvertibleIndicator(boolean convertibleIndicator) {
      this.convertibleIndicator = convertibleIndicator;
    }
    public boolean isEscrowIndicator() {
      return escrowIndicator;
    }
    public void setEscrowIndicator(boolean escrowIndicator) {
      this.escrowIndicator = escrowIndicator;
    }
    public boolean isInterestOnlyIndicator() {
      return interestOnlyIndicator;
    }
    public void setInterestOnlyIndicator(boolean interestOnlyIndicator) {
      this.interestOnlyIndicator = interestOnlyIndicator;
    }
    public boolean isLoanAffordableIndicator() {
      return loanAffordableIndicator;
    }
    public void setLoanAffordableIndicator(boolean loanAffordableIndicator) {
      this.loanAffordableIndicator = loanAffordableIndicator;
    }
    public boolean isPrepaymentPenaltyIndicator() {
      return prepaymentPenaltyIndicator;
    }
    public void setPrepaymentPenaltyIndicator(boolean prepaymentPenaltyIndicator) {
      this.prepaymentPenaltyIndicator = prepaymentPenaltyIndicator;
    }
    public boolean isRelocationLoanIndicator() {
      return relocationLoanIndicator;
    }
    public void setRelocationLoanIndicator(boolean relocationLoanIndicator) {
      this.relocationLoanIndicator = relocationLoanIndicator;
    }
    public boolean isSharedEquityIndicator() {
      return sharedEquityIndicator;
    }
    public void setSharedEquityIndicator(boolean sharedEquityIndicator) {
      this.sharedEquityIndicator = sharedEquityIndicator;
    }
    public int getTotalMortgagedPropertiesCount() {
      return totalMortgagedPropertiesCount;
    }
    public void setTotalMortgagedPropertiesCount(int totalMortgagedPropertiesCount) {
      this.totalMortgagedPropertiesCount = totalMortgagedPropertiesCount;
    }
    public boolean isMortgageModificationIndicator() {
      return mortgageModificationIndicator;
    }
    public void setMortgageModificationIndicator(boolean mortgageModificationIndicator) {
      this.mortgageModificationIndicator = mortgageModificationIndicator;
    }
    public boolean isWarehouseLenderIndicator() {
      return warehouseLenderIndicator;
    }
    public void setWarehouseLenderIndicator(boolean warehouseLenderIndicator) {
      this.warehouseLenderIndicator = warehouseLenderIndicator;
    }
    public boolean isHelocindicator() {
      return helocindicator;
    }
    public void setHelocindicator(boolean helocindicator) {
      this.helocindicator = helocindicator;
    }
  }

  @CordaSerializable
  public static class LoanIdentifier {
    public int investorCommitmentIdentifier;
    public long mersMinidentifier;
    public int sellerLoanIdentifier;
    public Extension extension;
    public int getInvestorCommitmentIdentifier() {
      return investorCommitmentIdentifier;
    }
    public void setInvestorCommitmentIdentifier(int investorCommitmentIdentifier) {
      this.investorCommitmentIdentifier = investorCommitmentIdentifier;
    }
    public long getMersMinidentifier() {
      return mersMinidentifier;
    }
    public void setMersMinidentifier(long mersMinidentifier) {
      this.mersMinidentifier = mersMinidentifier;
    }
    public int getSellerLoanIdentifier() {
      return sellerLoanIdentifier;
    }
    public void setSellerLoanIdentifier(int sellerLoanIdentifier) {
      this.sellerLoanIdentifier = sellerLoanIdentifier;
    }
    public Extension getExtension() {
      return extension;
    }
    public void setExtension(Extension extension) {
      this.extension = extension;
    }
  }

  @CordaSerializable
  public static class LoanIdentifierExtension {
    public String loanIdentifier;
    public String loanIdentifierType;
    public String getLoanIdentifier() {
      return loanIdentifier;
    }
    public void setLoanIdentifier(String loanIdentifier) {
      this.loanIdentifier = loanIdentifier;
    }
    public String getLoanIdentifierType() {
      return loanIdentifierType;
    }
    public void setLoanIdentifierType(String loanIdentifierType) {
      this.loanIdentifierType = loanIdentifierType;
    }
  }

  @CordaSerializable
  public static class LoanIdentifiers {
    public ArrayList<LoanIdentifier> loanIdentifier;

    public ArrayList<LoanIdentifier> getLoanIdentifier() {
      return loanIdentifier;
    }

    public void setLoanIdentifier(ArrayList<LoanIdentifier> loanIdentifier) {
      this.loanIdentifier = loanIdentifier;
    }
  }

  @CordaSerializable
  public static class LoanLevelCredit {
    public LoanLevelCreditDetail loanLevelCreditDetail;

    public LoanLevelCreditDetail getLoanLevelCreditDetail() {
      return loanLevelCreditDetail;
    }

    public void setLoanLevelCreditDetail(LoanLevelCreditDetail loanLevelCreditDetail) {
      this.loanLevelCreditDetail = loanLevelCreditDetail;
    }
  }

  @CordaSerializable
  public static class LoanLevelCreditDetail {
    public String loanLevelCreditScoreSelectionMethodType;
    public int loanLevelCreditScoreValue;
    public String getLoanLevelCreditScoreSelectionMethodType() {
      return loanLevelCreditScoreSelectionMethodType;
    }
    public void setLoanLevelCreditScoreSelectionMethodType(String loanLevelCreditScoreSelectionMethodType) {
      this.loanLevelCreditScoreSelectionMethodType = loanLevelCreditScoreSelectionMethodType;
    }
    public int getLoanLevelCreditScoreValue() {
      return loanLevelCreditScoreValue;
    }
    public void setLoanLevelCreditScoreValue(int loanLevelCreditScoreValue) {
      this.loanLevelCreditScoreValue = loanLevelCreditScoreValue;
    }
  }

  @CordaSerializable
  public static class LoanOriginator {
    public String loanOriginatorType;

    public String getLoanOriginatorType() {
      return loanOriginatorType;
    }

    public void setLoanOriginatorType(String loanOriginatorType) {
      this.loanOriginatorType = loanOriginatorType;
    }
  }

  @CordaSerializable
  public static class Loans {
    public CombinedLtvs combinedLtvs;
    public ArrayList<Loan> loan;
    public CombinedLtvs getCombinedLtvs() {
      return combinedLtvs;
    }
    public void setCombinedLtvs(CombinedLtvs combinedLtvs) {
      this.combinedLtvs = combinedLtvs;
    }
    public ArrayList<Loan> getLoan() {
      return loan;
    }
    public void setLoan(ArrayList<Loan> loan) {
      this.loan = loan;
    }
  }

  @CordaSerializable
  public static class LoanState {
    public String loanStateDate;
    public String loanStateType;
    public String getLoanStateDate() {
      return loanStateDate;
    }
    public void setLoanStateDate(String loanStateDate) {
      this.loanStateDate = loanStateDate;
    }
    public String getLoanStateType() {
      return loanStateType;
    }
    public void setLoanStateType(String loanStateType) {
      this.loanStateType = loanStateType;
    }
  }

  @CordaSerializable
  public static class Ltv {
    public int baseLtvratioPercent;
    public int ltvratioPercent;
    public int getBaseLtvratioPercent() {
      return baseLtvratioPercent;
    }
    public void setBaseLtvratioPercent(int baseLtvratioPercent) {
      this.baseLtvratioPercent = baseLtvratioPercent;
    }
    public int getLtvratioPercent() {
      return ltvratioPercent;
    }
    public void setLtvratioPercent(int ltvratioPercent) {
      this.ltvratioPercent = ltvratioPercent;
    }
  }

  @CordaSerializable
  public static class Maturity {
    public MaturityRule maturityRule;

    public MaturityRule getMaturityRule() {
      return maturityRule;
    }

    public void setMaturityRule(MaturityRule maturityRule) {
      this.maturityRule = maturityRule;
    }
  }

  @CordaSerializable
  public static class MaturityRule {
    public String loanMaturityDate;
    public int loanMaturityPeriodCount;
    public String loanMaturityPeriodType;
    public String getLoanMaturityDate() {
      return loanMaturityDate;
    }
    public void setLoanMaturityDate(String loanMaturityDate) {
      this.loanMaturityDate = loanMaturityDate;
    }
    public int getLoanMaturityPeriodCount() {
      return loanMaturityPeriodCount;
    }
    public void setLoanMaturityPeriodCount(int loanMaturityPeriodCount) {
      this.loanMaturityPeriodCount = loanMaturityPeriodCount;
    }
    public String getLoanMaturityPeriodType() {
      return loanMaturityPeriodType;
    }
    public void setLoanMaturityPeriodType(String loanMaturityPeriodType) {
      this.loanMaturityPeriodType = loanMaturityPeriodType;
    }
  }

  @CordaSerializable
  public static class MiData {
    public MiDataDetail miDataDetail;

    public MiDataDetail getMiDataDetail() {
      return miDataDetail;
    }

    public void setMiDataDetail(MiDataDetail miDataDetail) {
      this.miDataDetail = miDataDetail;
    }
  }

  @CordaSerializable
  public static class MiDataDetail {
    public String primaryMiabsenceReasonType;

    public String getPrimaryMiabsenceReasonType() {
      return primaryMiabsenceReasonType;
    }

    public void setPrimaryMiabsenceReasonType(String primaryMiabsenceReasonType) {
      this.primaryMiabsenceReasonType = primaryMiabsenceReasonType;
    }
  }

  @CordaSerializable
  public static class Name {
    public String firstName;
    public String lastName;
    public String middleName;
    public String suffixName;
    public String getFirstName() {
      return firstName;
    }
    public void setFirstName(String firstName) {
      this.firstName = firstName;
    }
    public String getLastName() {
      return lastName;
    }
    public void setLastName(String lastName) {
      this.lastName = lastName;
    }
    public String getMiddleName() {
      return middleName;
    }
    public void setMiddleName(String middleName) {
      this.middleName = middleName;
    }
    public String getSuffixName() {
      return suffixName;
    }
    public void setSuffixName(String suffixName) {
      this.suffixName = suffixName;
    }
  }

  @CordaSerializable
  public static class Other {
    public LoanIdentifierExtension loanIdentifierExtension;
    public GovernmentMonitoringDetailExtension governmentMonitoringDetailExtension;
    public HmdaRaceExtension hmdaRaceExtension;
    public GovernmentMonitoringExtension governmentMonitoringExtension;
    public LoanIdentifierExtension getLoanIdentifierExtension() {
      return loanIdentifierExtension;
    }
    public void setLoanIdentifierExtension(LoanIdentifierExtension loanIdentifierExtension) {
      this.loanIdentifierExtension = loanIdentifierExtension;
    }
    public GovernmentMonitoringDetailExtension getGovernmentMonitoringDetailExtension() {
      return governmentMonitoringDetailExtension;
    }
    public void setGovernmentMonitoringDetailExtension(
        GovernmentMonitoringDetailExtension governmentMonitoringDetailExtension) {
      this.governmentMonitoringDetailExtension = governmentMonitoringDetailExtension;
    }
    public HmdaRaceExtension getHmdaRaceExtension() {
      return hmdaRaceExtension;
    }
    public void setHmdaRaceExtension(HmdaRaceExtension hmdaRaceExtension) {
      this.hmdaRaceExtension = hmdaRaceExtension;
    }
    public GovernmentMonitoringExtension getGovernmentMonitoringExtension() {
      return governmentMonitoringExtension;
    }
    public void setGovernmentMonitoringExtension(GovernmentMonitoringExtension governmentMonitoringExtension) {
      this.governmentMonitoringExtension = governmentMonitoringExtension;
    }
  }

  @CordaSerializable
  public static class Parties {
    public ArrayList<Party> party;

    public ArrayList<Party> getParty() {
      return party;
    }

    public void setParty(ArrayList<Party> party) {
      this.party = party;
    }
  }

  @CordaSerializable
  public static class Party {
    public Roles roles;
    public Individual individual;
    public TaxpayerIdentifiers taxpayerIdentifiers;
    public LegalEntity legalEntity;
    public Roles getRoles() {
      return roles;
    }
    public void setRoles(Roles roles) {
      this.roles = roles;
    }
    public Individual getIndividual() {
      return individual;
    }
    public void setIndividual(Individual individual) {
      this.individual = individual;
    }
    public TaxpayerIdentifiers getTaxpayerIdentifiers() {
      return taxpayerIdentifiers;
    }
    public void setTaxpayerIdentifiers(TaxpayerIdentifiers taxpayerIdentifiers) {
      this.taxpayerIdentifiers = taxpayerIdentifiers;
    }
    public LegalEntity getLegalEntity() {
      return legalEntity;
    }
    public void setLegalEntity(LegalEntity legalEntity) {
      this.legalEntity = legalEntity;
    }
  }

  @CordaSerializable
  public static class PartyRoleIdentifier {
    public long partyRoleIdentifier;

    public long getPartyRoleIdentifier() {
      return partyRoleIdentifier;
    }

    public void setPartyRoleIdentifier(long partyRoleIdentifier) {
      this.partyRoleIdentifier = partyRoleIdentifier;
    }
  }

  @CordaSerializable
  public static class PartyRoleIdentifiers {
    public PartyRoleIdentifier partyRoleIdentifier;

    public PartyRoleIdentifier getPartyRoleIdentifier() {
      return partyRoleIdentifier;
    }

    public void setPartyRoleIdentifier(PartyRoleIdentifier partyRoleIdentifier) {
      this.partyRoleIdentifier = partyRoleIdentifier;
    }
  }

  @CordaSerializable
  public static class Payment {
    public PaymentRule paymentRule;
    public PaymentSummary paymentSummary;
    public PaymentRule getPaymentRule() {
      return paymentRule;
    }
    public void setPaymentRule(PaymentRule paymentRule) {
      this.paymentRule = paymentRule;
    }
    public PaymentSummary getPaymentSummary() {
      return paymentSummary;
    }
    public void setPaymentSummary(PaymentSummary paymentSummary) {
      this.paymentSummary = paymentSummary;
    }
  }

  @CordaSerializable
  public static class PaymentRule {
    public double initialPrincipalAndInterestPaymentAmount;
    public String paymentFrequencyType;
    public String scheduledFirstPaymentDate;
    public double getInitialPrincipalAndInterestPaymentAmount() {
      return initialPrincipalAndInterestPaymentAmount;
    }
    public void setInitialPrincipalAndInterestPaymentAmount(double initialPrincipalAndInterestPaymentAmount) {
      this.initialPrincipalAndInterestPaymentAmount = initialPrincipalAndInterestPaymentAmount;
    }
    public String getPaymentFrequencyType() {
      return paymentFrequencyType;
    }
    public void setPaymentFrequencyType(String paymentFrequencyType) {
      this.paymentFrequencyType = paymentFrequencyType;
    }
    public String getScheduledFirstPaymentDate() {
      return scheduledFirstPaymentDate;
    }
    public void setScheduledFirstPaymentDate(String scheduledFirstPaymentDate) {
      this.scheduledFirstPaymentDate = scheduledFirstPaymentDate;
    }
  }

  @CordaSerializable
  public static class PaymentSummary {
    public String lastPaidInstallmentDueDate;
    public int upbamount;
    public String getLastPaidInstallmentDueDate() {
      return lastPaidInstallmentDueDate;
    }
    public void setLastPaidInstallmentDueDate(String lastPaidInstallmentDueDate) {
      this.lastPaidInstallmentDueDate = lastPaidInstallmentDueDate;
    }
    public int getUpbamount() {
      return upbamount;
    }
    public void setUpbamount(int upbamount) {
      this.upbamount = upbamount;
    }
  }

  @CordaSerializable
  public static class PriceLock {
    public String priceLockDatetime;

    public String getPriceLockDatetime() {
      return priceLockDatetime;
    }

    public void setPriceLockDatetime(String priceLockDatetime) {
      this.priceLockDatetime = priceLockDatetime;
    }
  }

  @CordaSerializable
  public static class PriceLocks {
    public PriceLock priceLock;

    public PriceLock getPriceLock() {
      return priceLock;
    }

    public void setPriceLock(PriceLock priceLock) {
      this.priceLock = priceLock;
    }
  }

  @CordaSerializable
  public static class Project {
    public ProjectDetail projectDetail;

    public ProjectDetail getProjectDetail() {
      return projectDetail;
    }

    public void setProjectDetail(ProjectDetail projectDetail) {
      this.projectDetail = projectDetail;
    }
  }

  @CordaSerializable
  public static class ProjectDetail {
    public String projectClassificationIdentifier;
    public boolean pudindicator;
    public String getProjectClassificationIdentifier() {
      return projectClassificationIdentifier;
    }
    public void setProjectClassificationIdentifier(String projectClassificationIdentifier) {
      this.projectClassificationIdentifier = projectClassificationIdentifier;
    }
    public boolean isPudindicator() {
      return pudindicator;
    }
    public void setPudindicator(boolean pudindicator) {
      this.pudindicator = pudindicator;
    }
  }

  @CordaSerializable
  public static class Properties {
    public Property property;

    public Property getProperty() {
      return property;
    }

    public void setProperty(Property property) {
      this.property = property;
    }
  }

  @CordaSerializable
  public static class Property {
    public Address address;
    public FloodDetermination floodDetermination;
    public Project project;
    public PropertyDetail propertyDetail;
    public PropertyValuations propertyValuations;
    public Address getAddress() {
      return address;
    }
    public void setAddress(Address address) {
      this.address = address;
    }
    public FloodDetermination getFloodDetermination() {
      return floodDetermination;
    }
    public void setFloodDetermination(FloodDetermination floodDetermination) {
      this.floodDetermination = floodDetermination;
    }
    public Project getProject() {
      return project;
    }
    public void setProject(Project project) {
      this.project = project;
    }
    public PropertyDetail getPropertyDetail() {
      return propertyDetail;
    }
    public void setPropertyDetail(PropertyDetail propertyDetail) {
      this.propertyDetail = propertyDetail;
    }
    public PropertyValuations getPropertyValuations() {
      return propertyValuations;
    }
    public void setPropertyValuations(PropertyValuations propertyValuations) {
      this.propertyValuations = propertyValuations;
    }
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
    public String getAttachmentType() {
      return attachmentType;
    }
    public void setAttachmentType(String attachmentType) {
      this.attachmentType = attachmentType;
    }
    public String getConstructionMethodType() {
      return constructionMethodType;
    }
    public void setConstructionMethodType(String constructionMethodType) {
      this.constructionMethodType = constructionMethodType;
    }
    public int getFinancedUnitCount() {
      return financedUnitCount;
    }
    public void setFinancedUnitCount(int financedUnitCount) {
      this.financedUnitCount = financedUnitCount;
    }
    public String getPropertyEstateType() {
      return propertyEstateType;
    }
    public void setPropertyEstateType(String propertyEstateType) {
      this.propertyEstateType = propertyEstateType;
    }
    public boolean isPropertyFloodInsuranceIndicator() {
      return propertyFloodInsuranceIndicator;
    }
    public void setPropertyFloodInsuranceIndicator(boolean propertyFloodInsuranceIndicator) {
      this.propertyFloodInsuranceIndicator = propertyFloodInsuranceIndicator;
    }
    public int getPropertyStructureBuiltYear() {
      return propertyStructureBuiltYear;
    }
    public void setPropertyStructureBuiltYear(int propertyStructureBuiltYear) {
      this.propertyStructureBuiltYear = propertyStructureBuiltYear;
    }
    public String getPropertyUsageType() {
      return propertyUsageType;
    }
    public void setPropertyUsageType(String propertyUsageType) {
      this.propertyUsageType = propertyUsageType;
    }
  }

  @CordaSerializable
  public static class PropertyValuation {
    public PropertyValuationDetail propertyValuationDetail;

    public PropertyValuationDetail getPropertyValuationDetail() {
      return propertyValuationDetail;
    }

    public void setPropertyValuationDetail(PropertyValuationDetail propertyValuationDetail) {
      this.propertyValuationDetail = propertyValuationDetail;
    }
  }

  @CordaSerializable
  public static class PropertyValuationDetail {
    public int appraisalIdentifier;
    public int propertyValuationAmount;
    public String propertyValuationEffectiveDate;
    public String propertyValuationFormType;
    public String propertyValuationMethodType;
    public int getAppraisalIdentifier() {
      return appraisalIdentifier;
    }
    public void setAppraisalIdentifier(int appraisalIdentifier) {
      this.appraisalIdentifier = appraisalIdentifier;
    }
    public int getPropertyValuationAmount() {
      return propertyValuationAmount;
    }
    public void setPropertyValuationAmount(int propertyValuationAmount) {
      this.propertyValuationAmount = propertyValuationAmount;
    }
    public String getPropertyValuationEffectiveDate() {
      return propertyValuationEffectiveDate;
    }
    public void setPropertyValuationEffectiveDate(String propertyValuationEffectiveDate) {
      this.propertyValuationEffectiveDate = propertyValuationEffectiveDate;
    }
    public String getPropertyValuationFormType() {
      return propertyValuationFormType;
    }
    public void setPropertyValuationFormType(String propertyValuationFormType) {
      this.propertyValuationFormType = propertyValuationFormType;
    }
    public String getPropertyValuationMethodType() {
      return propertyValuationMethodType;
    }
    public void setPropertyValuationMethodType(String propertyValuationMethodType) {
      this.propertyValuationMethodType = propertyValuationMethodType;
    }
  }

  @CordaSerializable
  public static class PropertyValuations {
    public PropertyValuation propertyValuation;

    public PropertyValuation getPropertyValuation() {
      return propertyValuation;
    }

    public void setPropertyValuation(PropertyValuation propertyValuation) {
      this.propertyValuation = propertyValuation;
    }
  }

  @CordaSerializable
  public static class Qualification {
    public int borrowerReservesMonthlyPaymentCount;
    public int totalLiabilitiesMonthlyPaymentAmount;
    public int totalMonthlyIncomeAmount;
    public int totalMonthlyProposedHousingExpenseAmount;
    public int getBorrowerReservesMonthlyPaymentCount() {
      return borrowerReservesMonthlyPaymentCount;
    }
    public void setBorrowerReservesMonthlyPaymentCount(int borrowerReservesMonthlyPaymentCount) {
      this.borrowerReservesMonthlyPaymentCount = borrowerReservesMonthlyPaymentCount;
    }
    public int getTotalLiabilitiesMonthlyPaymentAmount() {
      return totalLiabilitiesMonthlyPaymentAmount;
    }
    public void setTotalLiabilitiesMonthlyPaymentAmount(int totalLiabilitiesMonthlyPaymentAmount) {
      this.totalLiabilitiesMonthlyPaymentAmount = totalLiabilitiesMonthlyPaymentAmount;
    }
    public int getTotalMonthlyIncomeAmount() {
      return totalMonthlyIncomeAmount;
    }
    public void setTotalMonthlyIncomeAmount(int totalMonthlyIncomeAmount) {
      this.totalMonthlyIncomeAmount = totalMonthlyIncomeAmount;
    }
    public int getTotalMonthlyProposedHousingExpenseAmount() {
      return totalMonthlyProposedHousingExpenseAmount;
    }
    public void setTotalMonthlyProposedHousingExpenseAmount(int totalMonthlyProposedHousingExpenseAmount) {
      this.totalMonthlyProposedHousingExpenseAmount = totalMonthlyProposedHousingExpenseAmount;
    }
  }

  @CordaSerializable
  public static class Role {
    public Appraiser appraiser;
    public RoleDetail roleDetail;
    public AppraiserSupervisor appraiserSupervisor;
    public Borrower borrower;
    public LoanOriginator loanOriginator;
    public Appraiser getAppraiser() {
      return appraiser;
    }
    public void setAppraiser(Appraiser appraiser) {
      this.appraiser = appraiser;
    }
    public RoleDetail getRoleDetail() {
      return roleDetail;
    }
    public void setRoleDetail(RoleDetail roleDetail) {
      this.roleDetail = roleDetail;
    }
    public AppraiserSupervisor getAppraiserSupervisor() {
      return appraiserSupervisor;
    }
    public void setAppraiserSupervisor(AppraiserSupervisor appraiserSupervisor) {
      this.appraiserSupervisor = appraiserSupervisor;
    }
    public Borrower getBorrower() {
      return borrower;
    }
    public void setBorrower(Borrower borrower) {
      this.borrower = borrower;
    }
    public LoanOriginator getLoanOriginator() {
      return loanOriginator;
    }
    public void setLoanOriginator(LoanOriginator loanOriginator) {
      this.loanOriginator = loanOriginator;
    }
  }

  @CordaSerializable
  public static class RoleDetail {
    public String partyRoleType;

    public String getPartyRoleType() {
      return partyRoleType;
    }

    public void setPartyRoleType(String partyRoleType) {
      this.partyRoleType = partyRoleType;
    }
  }

  @CordaSerializable
  public static class Roles {
    public Role role;
    public PartyRoleIdentifiers partyRoleIdentifiers;
    public Role getRole() {
      return role;
    }
    public void setRole(Role role) {
      this.role = role;
    }
    public PartyRoleIdentifiers getPartyRoleIdentifiers() {
      return partyRoleIdentifiers;
    }
    public void setPartyRoleIdentifiers(PartyRoleIdentifiers partyRoleIdentifiers) {
      this.partyRoleIdentifiers = partyRoleIdentifiers;
    }
  }

  @CordaSerializable
  public static class Root {
    public DealSets dealSets;

    public DealSets getDealSets() {
      return dealSets;
    }

    public void setDealSets(DealSets dealSets) {
      this.dealSets = dealSets;
    }
  }

  @CordaSerializable
  public static class SelectedLoanProduct {
    public PriceLocks priceLocks;

    public PriceLocks getPriceLocks() {
      return priceLocks;
    }

    public void setPriceLocks(PriceLocks priceLocks) {
      this.priceLocks = priceLocks;
    }
  }

  @CordaSerializable
  public static class Servicing {
    public DelinquencySummary delinquencySummary;

    public DelinquencySummary getDelinquencySummary() {
      return delinquencySummary;
    }

    public void setDelinquencySummary(DelinquencySummary delinquencySummary) {
      this.delinquencySummary = delinquencySummary;
    }
  }

  @CordaSerializable
  public static class TaxpayerIdentifier {
    public String taxpayerIdentifierType;
    public int taxpayerIdentifierValue;
    public String getTaxpayerIdentifierType() {
      return taxpayerIdentifierType;
    }
    public void setTaxpayerIdentifierType(String taxpayerIdentifierType) {
      this.taxpayerIdentifierType = taxpayerIdentifierType;
    }
    public int getTaxpayerIdentifierValue() {
      return taxpayerIdentifierValue;
    }
    public void setTaxpayerIdentifierValue(int taxpayerIdentifierValue) {
      this.taxpayerIdentifierValue = taxpayerIdentifierValue;
    }
  }

  @CordaSerializable
  public static class TaxpayerIdentifiers {
    public TaxpayerIdentifier taxpayerIdentifier;

    public TaxpayerIdentifier getTaxpayerIdentifier() {
      return taxpayerIdentifier;
    }

    public void setTaxpayerIdentifier(TaxpayerIdentifier taxpayerIdentifier) {
      this.taxpayerIdentifier = taxpayerIdentifier;
    }
  }

  @CordaSerializable
  public static class TermsOfMortgage {
    public String lienPriorityType;
    public String loanPurposeType;
    public String mortgageType;
    public int noteAmount;
    public String noteDate;
    public int noteRatePercent;
    public String getLienPriorityType() {
      return lienPriorityType;
    }
    public void setLienPriorityType(String lienPriorityType) {
      this.lienPriorityType = lienPriorityType;
    }
    public String getLoanPurposeType() {
      return loanPurposeType;
    }
    public void setLoanPurposeType(String loanPurposeType) {
      this.loanPurposeType = loanPurposeType;
    }
    public String getMortgageType() {
      return mortgageType;
    }
    public void setMortgageType(String mortgageType) {
      this.mortgageType = mortgageType;
    }
    public int getNoteAmount() {
      return noteAmount;
    }
    public void setNoteAmount(int noteAmount) {
      this.noteAmount = noteAmount;
    }
    public String getNoteDate() {
      return noteDate;
    }
    public void setNoteDate(String noteDate) {
      this.noteDate = noteDate;
    }
    public int getNoteRatePercent() {
      return noteRatePercent;
    }
    public void setNoteRatePercent(int noteRatePercent) {
      this.noteRatePercent = noteRatePercent;
    }
  }

  @CordaSerializable
  public static class Underwriting {
    public AutomatedUnderwritings automatedUnderwritings;
    public UnderwritingDetail underwritingDetail;
    public AutomatedUnderwritings getAutomatedUnderwritings() {
      return automatedUnderwritings;
    }
    public void setAutomatedUnderwritings(AutomatedUnderwritings automatedUnderwritings) {
      this.automatedUnderwritings = automatedUnderwritings;
    }
    public UnderwritingDetail getUnderwritingDetail() {
      return underwritingDetail;
    }
    public void setUnderwritingDetail(UnderwritingDetail underwritingDetail) {
      this.underwritingDetail = underwritingDetail;
    }
  }

  @CordaSerializable
  public static class UnderwritingDetail {
    public boolean loanManualUnderwritingIndicator;

    public boolean isLoanManualUnderwritingIndicator() {
      return loanManualUnderwritingIndicator;
    }

    public void setLoanManualUnderwritingIndicator(boolean loanManualUnderwritingIndicator) {
      this.loanManualUnderwritingIndicator = loanManualUnderwritingIndicator;
    }
  }

  @CordaSerializable
  public static class Urla {
    public UrlaDetail urlaDetail;

    public UrlaDetail getUrlaDetail() {
      return urlaDetail;
    }

    public void setUrlaDetail(UrlaDetail urlaDetail) {
      this.urlaDetail = urlaDetail;
    }
  }

  @CordaSerializable
  public static class UrlaDetail {
    public int borrowerPaidDiscountPointsTotalAmount;
    public int purchasePriceAmount;
    public int getBorrowerPaidDiscountPointsTotalAmount() {
      return borrowerPaidDiscountPointsTotalAmount;
    }
    public void setBorrowerPaidDiscountPointsTotalAmount(int borrowerPaidDiscountPointsTotalAmount) {
      this.borrowerPaidDiscountPointsTotalAmount = borrowerPaidDiscountPointsTotalAmount;
    }
    public int getPurchasePriceAmount() {
      return purchasePriceAmount;
    }
    public void setPurchasePriceAmount(int purchasePriceAmount) {
      this.purchasePriceAmount = purchasePriceAmount;
    }
  }
}
