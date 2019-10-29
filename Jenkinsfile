pipeline {
    agent any
    stages {
        stage ('npm build') {
            steps {
                dir("obosa_front"){
                    sh 'npm install'
                    sh 'npm run build'
                }
            }
        }
        stage ('gradle build') {
            steps {
                dir("obosa_server"){
                    sh 'mkdir src/main/resources'
                    sh 'cp /home/ubuntu/application/* src/main/resources/'
                    sh 'chmod +x gradlew'
                    sh './gradlew build -x test'
                }
            }
        }
        stage ('run front/backend server') {
            steps {
                sh 'chmod +x ${WORKSPACE}/deploy.sh'
                sh 'JENKINS_NODE_COOKIE=dontKillMe ${WORKSPACE}/deploy.sh ${env.BRANCH_NAME} 8080 8111 obosa-0.0.1-SNAPSHOT application.yml'
            }
        }
    }
}
