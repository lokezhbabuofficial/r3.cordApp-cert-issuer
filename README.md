# Certificate Issuer

**_This CorDapp allows a node (examinar) to issue a course certificate to another node (student)_**

## Deploying and Testing

### Required Prerequisites

- Corda CLI
- Cordapp Builder
- Node CLI
- Docker

### Deployment via Corda CLI

1. Navigate to the app directory
2. Build the app with `gradlew build`
3. Build the Cordapp with the cordapp-builder CLI util `cordapp-builder create --cpk ./contracts/build/libs/corda5-r3.cordApp-cert-issuer-demo-contracts-1.0-SNAPSHOT-cordapp.cpk --cpk ./workflows/build/libs/corda5-r3.cordApp-cert-issuer-demo-workflows-1.0-SNAPSHOT-cordapp.cpk -o certification-issue.cpb`
4. Configure the network with `corda-cli network config docker-compose certification-issue`
5. Build the network deployment dockerfile using corda-cli `corda-cli network deploy -n certification-issue -f certification-issue.yaml > docker-compose.yaml`
6. Deploy the network using docker-compose `docker-compose -f docker-compose.yaml up -d`
7. When deployed check the status with corda-cli `corda-cli network status -n certification-issue` note the mapped web ports for Http RPC
8. Install the application on the network using corda-cli `corda-cli package install -n certification-issue certification-issue.cpb`

### Testing VIA Swagger

- Using the port noted from the network status visit `https://localhost:<port>/api/v1/swagger`
- Login using the button on the top right usernames and passwords are as follows:

  | Nodes    | Username  | Password |
  | -------- | --------- | -------- |
  | Examinar | examinaar | password |
  | Peter    | peteer    | password |
  | Lokesh   | lokezh    | password |

## Case 1:

**_Trying to achieve a basic example_**

- Student => Asks for Course Certification by passing the score
- Creates a new output `CourseCertificateState` using the `CourseCertificateContract.Command.Issue`
- Both Signs the transaction
- `CourseCertificateState` issued to Student

### To issue a certificate start the `CourseCertificationFlow` from `Peter` or `Lokesh` nodes via the Start Flow api by passing something similar

```json
{
	"rpcStartFlowRequest": {
		"clientId": "<client-id>",
		"flowName": "com.tutorial.CourseCertificationFlowInitiator",
		"parameters": {
			"parametersInJson": "{\"score\": \"85\", \"examinarName\": \"O=CordaExaminar, L=Tamilnadu, C=IN \"}"
		}
	}
}
```

### To Query the vault

```json
{
	"filterRequest": {
		"contractStateClassNames": ["com.tutorial.states.CourseCertificationState"],
		"txIds": [
			"<txIds>"
		]
	},
	"context": {
		"awaitForResultTimeout": "PT1M",
		"currentPosition": 0,
		"maxCount": 10
	}
}
```

## Case 2: TBD

**_Trying to achieve multiple outputs for single transactions_**

- CordaExamiar => Selects a `List` for Students (Parties) and Provides `List` for scores
- Creates a new `List` of output `CourseCertificateState` using the `CourseCertificateContract.Command.BulkIssue`
- Initiates flow for all the output state (students) by including their `getOwningKey()`
- All the parties signs the transactions
- `CourseCertificateState` issued to each of the Students


### To issue a certificate start the `CourseCertificationFlow` from `Peter` or `Lokesh` nodes via the Start Flow api by passing something similar

```json
{
	"rpcStartFlowRequest": {
		"clientId": "<client-id>",
		"flowName": "com.tutorial.BulkStudentIssueCourseCertificationFlowInitator",
		"parameters": {
			"parametersInJson": "[{\"participant\":\"O=Peter, L=New York, C=US\",\"score\":85},{\"participant\":\"O=Lokesh, L=Delhi, C=IN\",\"score\":95}]"
		}
	}
}
```

### To Query the vault !!--- Make sure you use the txId for identifing the appropiate state ---!!

```json
{
	"filterRequest": {
		"contractStateClassNames": ["com.tutorial.states.CourseCertificationState"],
		"txIds": [
			"<txIds>"
		]
	},
	"context": {
		"awaitForResultTimeout": "PT1M",
		"currentPosition": 0,
		"maxCount": 10
	}
}
```

## Web UI

TBD
