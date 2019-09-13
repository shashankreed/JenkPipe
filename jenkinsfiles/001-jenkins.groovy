pipeline {
  agent any
    tools { 
        jdk 'System'
	go 'Main_go'
    }
stages {
stage('build1') {
	steps{
    checkout([$class: 'GitSCM', userRemoteConfigs: [[credentialsId: 'smganesha', url: 'https://bitbucket.org/tekion/tekionbuild.git']], branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'CleanBeforeCheckout'], [$class: 'RelativeTargetDirectory', relativeTargetDir: 'tekionbuild']], submoduleCfg: []])

}
}
stage('build2') {
    steps {
     ///  git url:"https://bitbucket.org/tekion/tfees.git", credentialsId: 'smganesha', branch: 'b7U'
checkout([$class: 'GitSCM', userRemoteConfigs: [[credentialsId: 'smganesha', url: 'https://bitbucket.org/tekion/tappointment.git']], branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'CleanBeforeCheckout'], [$class: 'RelativeTargetDirectory', relativeTargetDir: 'tappointment']], submoduleCfg: []])
    withEnv(['VAR1=VALUE ONE',
	     'AWS_CLUSTER_NAME=ecs-devalpha-cluster',
	     'SERVICE_NAME=tfees_ecsdev',
	     'REGION_NAME=us-west-1',
	     'BRANCH_NAME=release-1.9'
            ]) {
        sh '''
#!/bin/bash
cp tekionbuild/scripts/tekion-common-build.sh tappointment
cp tekionbuild/scripts/nestedDependency.sh tappointment
cd tappointment
#bash -ex nestedDependency.sh
echo "Test23"
#cd vendor/bitbucket.org/tekion
#rm -rf tappointment
#rm -rf tbaas
#git clone -b release-1.8.1 https://bitbucket.org/tekion/tbaas.git
#rm -rf tscrittypes
#git clone -b doubleOptIn https://bitbucket.org/tekion/tscrittypes.git
#cd ../../..

#bash -ex tekion-common-build.sh
	'''
    }
}
}
}
}

