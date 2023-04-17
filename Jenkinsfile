pipeline {
    agent any
    stages{
        stage('Build Maven'){
            steps{
                checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/gem-bijaykumarray/imsSpringBootApp']])
                sh 'mvn clean install'
            }
        }
        stage('Build docker image'){
            steps{
                script{
                    sh 'docker build -t misApp:1.0 .'
                }
            }
        }
    }
}