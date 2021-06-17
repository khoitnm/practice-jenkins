# practice-jenkins

# Variables available for shell scripts
http://localhost:8080/env-vars.html/

Check out a branch based on tag:
https://stackoverflow.com/questions/54953220/jenkinsfile-checkout-git-tag

Fetch tag name in Jenkins file (without this, the `script: "git tag --points-at=HEAD"` won't work):
- https://stackoverflow.com/questions/48292080/jenkins-multibranch-reference-git-repos-tag-from-pipeline-file-jenkinsfile
- Detail configuration of ref to tag: https://docs.mohami.io/webhook-to-jenkins-for-bitbucket/Triggering-Jenkins-Based-on-New-Tags.1787824873.html

Auto trigger build when tag a branch:
https://stackoverflow.com/questions/50778526/trigger-a-jenkins-pipeline-by-tagging-an-existing-commit