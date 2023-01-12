# Certificate Issuer

***This CorDapp allows a node (examinar) to issue a course certificate to another node (student)***

## Case 1:

***Trying to achieve a basic example*** 

* Student => Asks for Course Certification by passing the score
* Creates a new output `CourseCertificateState` using the `CourseCertificateContract.Command.Issue`
* Both Signs the transaction
* `CourseCertificateState` issued to Student

## Case 2:

***Trying to achieve multiple outputs for single transactions*** 

* CordaExamiar => Selects a `List` for Students (Parties) and Provides `List` for scores
* Creates a new `List` of output `CourseCertificateState` using the `CourseCertificateContract.Command.BulkIssue`
* Initiates flow for all the output state (students) by including their `getOwningKey()`
* All the parties signs the transactions
* `CourseCertificateState` issued to each of the Students

### Flow cmd reference
On `CordaExaminar` Node execute bellow for positive flow
```shell
flow start BulkStudentIssueCourseCertificationFlow$BulkStudentIssueCourseCertificationFlowInitiator students: ["O=Lokesh,L=Delhi,C=IN", "O=Peter,L=New York,C=US"], studentScores: [80, 85]
```

## Case 3: **!!(TODO)!!**

***Trying to consume and input state and provide a new output state (to see notary in action) and using LinearPointer, StaticPointers***

IDEA
```
State
	CourseState
		courseDesc: string,
		courseId: LinearId,
		coursePassScore: int
		
	CourseSubscriptionState
		courseSubscriptionId: LinearId
		courseId: LinerPointer<CourseState>
		subscriptionStartDate: Instant
		subscriptionEndDate: Instant
		
	CourseCertificationState
		courseSubscriptionId: LinearId
		issuer: Party
		owner: Party
	
Contract
	CourseContract
	CourseSubscriptionContract
	CourseCertificationContract
	
Flow
	CreateAndIssueNewCourseFlow
		Issue CourseId
	UpdateCourseFlow
		Consume CourseId input and produce new output with CourseId
	SubscribeToCourseFlow
		Associate CourseId to create CourseSubscriptionState and assign the state to student (Use Linear or StatePointer, )
	IssueSubscriptionCourseCertificationFlow
		Consume SubscriptionId to issue CourseCertificationState
```

## Docker Deployment ( Windows )

## Prerequisite:

- Docker Desktop

## To clean and deploy nodes locally

``` gradlew clean prepareDockerNodes ```

## To start / run the flow

## Prerequisite:

- Putty ( To connect the nodes via SSH PORT )
- Node Explorer ( To connect with the node and to do transaction )
## Issue certificate

``` flow start CourseCertificationFlow$CourseCertificationFlowInitiator examiner: "O=CordaExaminar,L=Tamilnadu,C=IN", score: 50 ```

## Bulk Issue certificate ( via Corda Examiner Node )

``` flow start BulkStudentIssueCourseCertificationFlow$BulkStudentIssueCourseCertificationFlowInitiator students: ["O=Lokesh,L=Chennai,C=IN", "O=Varatharaj,L=Georgia,C=US"], studentScores: [80, 85] ```


