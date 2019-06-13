// Jenkinsfile

pipeline {
        agent none
        stages {
            stage('Build') {
                agent {
                    docker {
                        image 'maven:3.6.1-jdk-11-slim'
                        args '-v /root/.m2:/root/.m2 -v jenkins_data:/var/jenkins_home'
                    }
                }
                steps {
                    sh 'mvn package'
                    sh 'ls'
                }
            }
            stage('Deploy') {
                agent {
                    docker {
                        image 'jenkinsci/blueocean'
                        args '-v /root/.m2:/root/.m2 -v jenkins_data:/var/jenkins_home -v /var/run/docker.sock:/var/run/docker.sock --entrypoint=""  -d --rm'
                    }
                }
                steps {
                    sh './jenkins/deploy.sh'
                    }
                }
            }
}
