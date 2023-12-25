# approval-tests

This projects exists to demonstrate the approval tests library in a Spring boot Java project.

Uses Java 21.

## Approvals tests? What? Huh? Who?

I came accross approvals tests on an episode of the [software engineering daily podcast](https://www.se-radio.net/2023/12/se-radio-595-llewelyn-falco-on-approval-testing/) which is the best place to start with this if you have time.

The basic approach is explained [at the approval tests website](https://approvaltests.com/) and Java specific steps [on github](https://github.com/approvals/ApprovalTests.java).

## How to use this Repo:

- run `mvn clean install` and check the output of the jacoco report
- check out the subpackages of dev/atrobertsmith/approvaltests and you will see the test classes all use the org.approvaltests.Approvals static method to make assertions.
- you will also see txt files containing the expected (approved) outputs

Note that there are unit tests (in the domain test package) and integration tests (in the rest package) showing that this can be used for both. There is also one selenium test which also integrations Approvals as the assertion library