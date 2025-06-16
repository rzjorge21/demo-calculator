pipeline {
    agent any

    tools {
        maven 'Maven3'
        jdk 'jdk-17'
    }

    environment {
        SONAR_HOST_URL = 'http://localhost:9000'
        ARTIFACTORY_URL = 'http://localhost:8081/artifactory'
        ARTIFACTORY_REPO = 'monolito'
        ARTIFACTORY_CREDS = 'ARTIFACTORY_CREDENTIALS'
    }

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        // stage('Testing') {
        //     steps {
        //         sh 'mvn test'
        //     }
        //     post {
        //         always {
        //             junit 'target/surefire-reports/*.xml'
        //             jacoco execPattern: 'target/jacoco.exec'
        //         }
        //     }
        // }

        // stage('SonarQube Analysis') {
        //     steps {
        //       withCredentials([string(credentialsId: 'SONAR_TOKEN', variable: 'SONAR_TOKEN')]) {
        //           withSonarQubeEnv('SonarQube') {
        //               sh 'mvn sonar:sonar -Dsonar.projectKey=monolito -Dsonar.host.url=$SONAR_HOST_URL -Dsonar.login=$SONAR_TOKEN'
        //           }
        //       }
        //     }
        // }

        // stage('Quality Gate') {
        //     steps {
        //         timeout(time: 1, unit: 'MINUTES') {
        //             waitForQualityGate abortPipeline: true
        //         }
        //     }
        // }
        
        // stage('Publish to Artifactory') {
        //     steps {
        //         script {
        //             def pom = readMavenPom file: 'pom.xml'
        //             def artifactPath = "${pom.groupId.replace('.', '/')}/${pom.artifactId}/${pom.version}"
        //             def artifactName = "${pom.artifactId}-${pom.version}.jar"
                    
        //             def server = Artifactory.server 'artifactory'
                    
        //             def uploadSpec = """{
        //                 "files": [
        //                     {
        //                         "pattern": "target/${artifactName}",
        //                         "target": "${ARTIFACTORY_REPO}/${artifactPath}/",
        //                         "props": "build.number=${env.BUILD_NUMBER};build.name=${env.JOB_NAME}"
        //                     }
        //                 ]
        //             }"""
                    
        //             def buildInfo = server.upload spec: uploadSpec
        //             server.publishBuildInfo buildInfo
                    
        //             echo "Artifact ${artifactName} published to Artifactory at ${ARTIFACTORY_URL}/${ARTIFACTORY_REPO}/${artifactPath}"
        //         }
        //     }
        // }
    }
}
