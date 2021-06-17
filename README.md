# practice-jenkins

# Variables available for shell scripts
http://localhost:8080/env-vars.html/

Check out a branch based on tag:
https://stackoverflow.com/questions/54953220/jenkinsfile-checkout-git-tag

Fetch tag name in Jenkins file (without this, the `script: "git tag --points-at=HEAD"` won't work):
- https://stackoverflow.com/questions/48292080/jenkins-multibranch-reference-git-repos-tag-from-pipeline-file-jenkinsfile


Auto trigger build when tag a branch by using Ref Spec (looks like doesn't work):
- https://stackoverflow.com/questions/50778526/trigger-a-jenkins-pipeline-by-tagging-an-existing-commit
- Detail configuration of ref to tag: https://docs.mohami.io/webhook-to-jenkins-for-bitbucket/Triggering-Jenkins-Based-on-New-Tags.1787824873.html
    - Config "Specify ref specs": `+refs/tags/*:refs/remotes/@{remote}/tags/*`

Auto trigger build when tag a branch by using "Discover Tag":
Note: Even when using Discover Tag option, it just creates a pipeline for the tag, but not start any build for that pipeline:
- Config Discover Tag: https://www.jenkins.io/blog/2018/05/16/pipelines-with-git-tags/
- The problem and the solution: https://issues.jenkins.io/browse/JENKINS-47496
    - Solution by the plugin "Basic Branch Build Strategies": https://issues.jenkins.io/browse/JENKINS-47496?focusedCommentId=346194&page=com.atlassian.jira.plugin.system.issuetabpanels:comment-tabpanel#comment-346194