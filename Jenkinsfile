pipeline {
    agent any

    stages {
        stage ('npm build') {
            steps {
                dir("obosa_front"){
                    script {
                      FAILED_STAGE=env.STAGE_NAME
                    }
                    sh 'npm install'
                    sh 'npm run build'
                }
            }
        }
        stage ('gradle build') {
            steps {
                dir("obosa_server"){
                    script {
                      FAILED_STAGE=env.STAGE_NAME
                    }
                    sh 'mkdir -p src/main/resources'
                    sh 'cp /home/ubuntu/application/* src/main/resources/'
                    sh 'chmod +x gradlew'
                    sh './gradlew build -x test'
                }
            }
        }
        stage ('deploy front server') {
            when{
                expression {
                    return gitlabTargetBranch == 'master';
                 }
            }
            steps {
                script {
                    FAILED_STAGE=env.STAGE_NAME
                }
                sh 'chmod +x ${WORKSPACE}/deployFront.sh'
                sh "JENKINS_NODE_COOKIE=dontKillMe ${WORKSPACE}/deployFront.sh 8111 obosa-0.0.1-SNAPSHOT"
            }
        }
        
        stage ('deploy spring server') {
            when{
                expression {
                    return gitlabTargetBranch == 'master';
                 }
            }
            steps {
                script {
                    FAILED_STAGE=env.STAGE_NAME
                }
                sh 'cp obosa_server/src/main/resources/* obosa_server/build/libs'
                sh 'chmod +x ${WORKSPACE}/deploySpring.sh'
                sh 'cp deploySpring.sh obosa_server/build/libs'
                sshPublisher(publishers: [sshPublisherDesc(configName: 'ObosaAPIServer1', transfers: [sshTransfer(cleanRemote: false, excludes: '', execCommand: '''chmod +x /home/ubuntu/ObosaSpring/deploySpring.sh 
/home/ubuntu/ObosaSpring/deploySpring.sh 8080 obosa-0.0.1-SNAPSHOT application.yml &''', execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: '', remoteDirectorySDF: false, removePrefix: 'obosa_server/build/libs', sourceFiles: 'obosa_server/build/libs/*')], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: false), sshPublisherDesc(configName: 'ObosaAPIServer2', transfers: [sshTransfer(cleanRemote: false, excludes: '', execCommand: '''chmod +x /home/ubuntu/ObosaSpring/deploySpring.sh 
/home/ubuntu/ObosaSpring/deploySpring.sh 8080 obosa-0.0.1-SNAPSHOT application.yml &''', execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: '', remoteDirectorySDF: false, removePrefix: 'obosa_server/build/libs', sourceFiles: 'obosa_server/build/libs/*')], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: false)])
            }
        }

    }
    post {
        always {
            script{
                if ( currentBuild.currentResult == "SUCCESS" ) {
                    slackSend color: "good", message: "[ user : ${gitlabUserName} ] , [ Job: ${env.JOB_NAME} ] with buildnumber ${env.BUILD_NUMBER} was successful"
                }
                else if( currentBuild.currentResult == "FAILURE" ) { 
                    slackSend color: "danger", message: "[ user : ${gitlabUserName} ] , [ Job: ${env.JOB_NAME} ] , [ Stage: ${FAILED_STAGE} ] with buildnumber ${env.BUILD_NUMBER} was failed"
                }
                else if( currentBuild.currentResult == "UNSTABLE" ) { 
                    slackSend color: "warning", message: "[ user : ${gitlabUserName} ] , [ Job: ${env.JOB_NAME} ] with buildnumber ${env.BUILD_NUMBER} was unstable"
                }
                else {
                    slackSend color: "danger", message: "[ user : ${gitlabUserName} ] , [ Job: ${env.JOB_NAME} ] with buildnumber ${env.BUILD_NUMBER} its resulat was unclear"	
                }
            }
        }
    }
}

