pipeline {
  agent any
stages {
stage('build1') {
	steps{
	git url: "https://bitbucket.org/tekion/tekionbuild.git", credentialsId: 'smganesha', branch: 'master'
}
}
stage('build2') {
    steps {
      git url:"https://bitbucket.org/tekion/tfees.git", credentialsId: 'smganesha', branch: 'b7U'
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
