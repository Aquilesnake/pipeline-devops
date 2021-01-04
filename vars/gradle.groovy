/*
forma de invocacion de metodo call; 

def ejecucion = load 'script.groovy'
ejecucion.call()

*/

def call (String etapa){
        stage('build'){
            bat './gradlew clean build'
    }
    stage('sonar') {
         def scannerHome = tool 'sonar';
             withSonarQubeEnv('sonar') {
                bat "${scannerHome}\\bin\\sonar-scanner -Dsonar.projectKey=ejemplo-gradle -Dsonar.java.binaries=build"
             }
    }
        stage('run') {
            bat 'start gradlew bootRun &'
            sleep 20
        }
            stage('test') {
            bat 'curl -X GET http://localhost:8082/rest/mscovid/test?msg=testing'
            }
                stage('nexus') {
                nexusPublisher nexusInstanceId: 'nexus', nexusRepositoryId: 'test-nexus', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: 'jar', filePath: 'build\\libs\\DevOpsUsach2020-0.0.1.jar']], mavenCoordinate: [artifactId: 'DevOpsUsach2020', groupId: 'com.devopsusach2020', packaging: 'jar', version: '0.0.1']]]
                }
}
return this;