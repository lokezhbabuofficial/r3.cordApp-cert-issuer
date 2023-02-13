### Docker Deployment ( Windows )

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

``` flow start BulkStudentIssueCourseCertificationFlow$BulkStudentIssueCourseCertificationFlowInitiator students: ["O=Lokesh,L=Chennai,C=IN", "O=Varatharaj,L=Delhi,C=IN"], studentScores: [80, 85] ```