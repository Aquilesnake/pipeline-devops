/*
	forma de invocación de método call:
	def ejecucion = load 'script.groovy'
	ejecucion.call()
*/

def call (String etapa = ''){

    switch(etapa) {
    case 'build':
    stage('build'){
        bat 'mvnw.cmd clean compile -e'
    }    
    break
    case 'test':
    stage('test'){
        bat 'mvnw.cmd clean test -e'
    }
    break
    case 'jar':
    stage('jar') {
        bat 'mvnw.cmd clean package -e'
    }
    break
 
     case 'sonar':
     stage('sonar') {
         def scannerHome = tool 'sonar';
            withSonarQubeEnv('sonar') {
            bat "${scannerHome}\\bin\\sonar-scanner -Dsonar.projectKey=ejemplo-gradle -Dsonar.java.binaries=build"
            }
    }
    break
        case 'run':
    stage('run'){
        bat 'start mvnw.cmd spring-boot:run'
    }
    break
    case 'testing':
    stage('testing'){
        sleep 10
        bat 'curl http://localhost:8082/rest/mscovid/test?msg=testing'
    }
    break
    case 'nexus':
    stage('nexus'){
    nexusPublisher nexusInstanceId: 'nexus', nexusRepositoryId: 'test-nexus', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: 'jar', filePath: 'build\\DevOpsUsach2020-0.0.1.jar']], mavenCoordinate: [artifactId: 'DevOpsUsach2020', groupId: 'com.devopsusach2020', packaging: 'jar', version: '0.0.1']]]
    }
    break
    }
}
return this;