pipeline {
  agent any
  stages {

    stage('Stage 1') {
      steps {
        script {
          echo 'Stage 1'
        }
      }
    }

    stage('Stage 2') {
      steps {
        script {
	sh '''
         pwd
	ls 
	'''
        }
      }
    }
stage('build') {
    steps {
    withEnv(['VAR1=VALUE ONE',
	     'AWS_CLUSTER_NAME=ecs-devalpha-cluster',
	     'SERVICE_NAME=tfees_ecsdev',
	     'REGION_NAME=us-west-1',
	     'BRANCH_NAME=release-1.9'
            ]) {
        sh '''
	echo $VAR1
        echo $AWS_CLUSTER_NAME
	'''
    }
}
}
  }
    configure { project ->
    project / publishers << 'jenkins.plugins.slack.SlackNotifier' {
      baseUrl("https://whatever.slack.com/services/hooks/jenkins-ci/")
      room("#room")
      notifyAborted(true)
      notifyEveryFailure(true)
      notifyNotBuilt(true)
      notifySuccess(true)
      notifyUnstable(true)
      notifyRegression(true)
      notifyBackToNormal(true)
      startNotification(true)
    }
  }
}
