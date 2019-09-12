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
    def someGroovyVar = 'Hello world'
    withEnv(['VAR1=VALUE ONE',
             "VAR2=${someGroovyVar}"
	     'AWS_CLUSTER_NAME=ecs-devalpha-cluster',
	     'SERVICE_NAME=tfees_ecsdev',
	     'REGION_NAME=us-west-1',
	     'BRANCH_NAME=release-1.9'
            ]) {
        def result = sh(script: 'echo $VAR1; echo $AWS_CLUSTER_NAME; echo $VAR2', returnStdout: true)
        echo result
    }
}
  }
}
