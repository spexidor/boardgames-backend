// Jenkinsfile

pipeline {
        agent none
        stages {
            stage('Build') {
                agent {
                    docker {
                        image 'maven:3-alpine'
                        args '-v /root/.m2:/root/.m2 -v jenkins_data:/var/jenkins_home'
                    }
                }
                steps {
                    sh 'pwd'
                    sh 'ls'

                    echo $JAVA_HOME
                    echo $PATH
                    which java
                    java -version
                    mvn --version

                    echo "${JENKINS_HOME}"
                    sh 'mvn -DskipTests package'
                    sh 'ls'
                }
            }
            stage('Deploy') {
                agent {
                    docker {
                        image 'jenkinsci/blueocean'
                        args '-v /root/.m2:/root/.m2 -v jenkins_data:/var/jenkins_home -v /var/run/docker.sock:/var/run/docker.sock --entrypoint=""'
                    }
                }
                steps {
                    sh 'pwd'
                    sh 'ls'
                    echo "${JENKINS_HOME}"
                    sh './jenkins/deploy.sh'
                    }
                }
            }
}
