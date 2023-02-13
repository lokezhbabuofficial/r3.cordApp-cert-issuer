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

## Case 3:

***Trying to consume and input state and provide a new output state (to see notary in action) and using LinearPointer, StaticPointers***

* CordaExaminar -> Creates a `CourseState` and distribute to all the nodes excepts himself and notary
* CordaExaminar -> Creates a new `CourseSubscriptionState` with implementing `SchedulableState` and provide a default expiration date and issue to student/subscriber
* Student -> Request for `SubscriptionCourseCertificationState` by consuming the `CourseSubscriptionState` flow provides `SubscriptionCourseCertificationState` before the subscription expired


