pipeline {
    agent any

    tools {
        maven 'Maven3'
        jdk 'jdk-17'
    }

    environment {
        SONAR_HOST_URL = 'http://localhost:9000'
        ARTIFACTORY_URL = 'http://localhost:8081/artifactory'
        ARTIFACTORY_REPO = 'mitocode'
        ARTIFACTORY_CREDS = 'ARTIFACTORY_CREDENTIALS'
    }

    stages {
        // stage('Build') {
        //     steps {
        //         sh 'mvn clean compile -B -ntp'
        //     }
        // }

        // stage('Testing') {
        //     steps {
        //         sh 'mvn test -B -ntp'
        //     }
        //     post {
        //         success {
        //             junit 'target/surefire-reports/*.xml'
        //         }
        //         failure { 
        //             echo 'Tests failed!'
        //         }
        //     }
        // }
        // stage('Coverage') {
        //     steps {
        //         sh 'mvn jacoco:report -B -ntp'
        //     }
        //     post { 
        //         success { 
        //             recordCoverage(
        //                 tools: [[parser: 'JACOCO']],
        //                 sourceCodeRetention: 'EVERY_BUILD',
        //                 qualityGates: [
        //                     [threshold: 60.0, metric: 'LINE', criticality: 'FAILURE'],
        //                 ]
        //             )
        //         }
        //     }  
        // }

        // stage('SonarQube Analysis') {
        //     steps {
        //         withCredentials([string(credentialsId: 'SONAR_TOKEN', variable: 'SONAR_TOKEN')]) {
        //             withSonarQubeEnv('SonarQube') {
        //                 // Envía los datos a SonarQube incluyendo el umbral de cobertura
        //                 sh """
        //                 mvn sonar:sonar -B -ntp \
        //                   -Dsonar.projectKey=demo-calculator \
        //                   -Dsonar.host.url=$SONAR_HOST_URL \
        //                   -Dsonar.login=$SONAR_TOKEN \
        //                   -Dsonar.java.coveragePlugin=jacoco \
        //                   -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml \
        //                   -Dsonar.coverage.exclusions=**/test/** \
        //                   -Dsonar.test.exclusions=**/test/** \
        //                   -Dsonar.java.test.exclusions=**/test/**
        //                 """
        //             }
        //         }
        //     }
        // }
        
        // stage('Quality Gate') {
        //     steps {
        //         timeout(time: 1, unit: 'MINUTES') {
        //             waitForQualityGate abortPipeline: true
        //         }
        //     }
        // }

        stage('Package') {
            steps {
                sh 'env | sort'
                sh 'mvn clean package -DskipTests -B -ntp'
            }
        }
        
        stage('Publish to Artifactory') {
            steps {
                script {
                    def jarFile = findFiles(glob: 'target/*.jar')[0]?.path
                    if (!jarFile) {
                        error "No se encontró el archivo JAR en target/"
                    }
                    
                    def server = Artifactory.server 'artifactory'

                    

                    def pom = readMavenPom file: 'pom.xml'
                    def artifactPath = "${pom.groupId.replace('.', '/')}/${pom.artifactId}/${pom.version}"
                    def artifactName = "${pom.artifactId}-${pom.version}.jar"
                    
                    def uploadSpec = """{
                        "files": [
                            {
                                "pattern": "target/${artifactName}",
                                "target": "${ARTIFACTORY_REPO}/${artifactPath}/",
                                "props": "build.number=${env.BUILD_NUMBER};build.name=${env.JOB_NAME}"
                            }
                        ]
                    }"""
                    
                    def buildInfo = server.upload spec: uploadSpec
                    server.publishBuildInfo buildInfo
                    
                    echo "Artifact ${artifactName} published to Artifactory at ${ARTIFACTORY_URL}/${ARTIFACTORY_REPO}/${artifactPath}"
                    echo "${ARTIFACTORY_URL}/${ARTIFACTORY_REPO}/${artifactPath}/${artifactName}"
                }
            }
        }
    }
    post { 
        success { 
            cleanWs()
        }
    } 
}
