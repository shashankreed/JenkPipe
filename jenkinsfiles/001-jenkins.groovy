pipeline {
  agent any
  stages {
	    properties {
      githubProjectUrl("https://bitbucket.org/tekion/tappointment/")
    }  
   jdk('System')
  
      logRotator {
        daysToKeep(2)
        numToKeep(2)
      }
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
}
