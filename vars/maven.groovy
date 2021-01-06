import pipeline.*

def call()
{
    figlet 'Maven'

    def arr_stages_CI = ['buildAndTest','sonar','runJar','rest','nexusCI']
    def arr_stages_CD = ['downloadNexus','runDownloadedJar','rest','nexusCD']

    String rama = sh(returnStdout: true, script: 'git rev-parse --abbrev-ref HEAD').trim();
    Boolean var_expresion = ((rama.indexOf('feature')>0) || (rama.indexOf('develop')>0));

    if (var_expresion == true)
    {
        figlet 'Continuous Integration'
        buildAndTest()
        sonar()
        runJar()
        rest()
        nexusCI()
    }
    else
    {
        figlet 'Continuous Deploy'
        downloadNexus()
        runDownloadedJar()
        rest()
        nexusCD()
    }
}

def buildAndTest()
{
    sh 'mvn clean compile -e'
    sh 'mvn clean test -e'
}

def runJar()
{
    sh 'mvn clean package -e'
}

def rest()
{
    sleep 5
    sh "curl -X GET http://localhost:8081/rest/mscovid/test?msg=testing"
}

def downloadNexus()
{
    sh 'curl -X GET -u admin:criveros http://localhost:8082/repository/test-nexus/com/devopsusach2020/DevOpsUsach2020/0.0.1/DevOpsUsach2020-0.0.1.jar -O'
}

def runDownloadedJar()
{
    sh "java -jar DevOpsUsach2020-0.0.1.jar &"
    sh "sleep 5"
}

def nexusCI()
{
    nexusPublisher nexusInstanceId: 'nexus', nexusRepositoryId: 'test-nexus', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: 'jar', filePath: "DevOpsUsach2020-0.0.1.jar"]], mavenCoordinate: [artifactId: 'DevOpsUsach2020', groupId: 'com.devopsusach2020', packaging: 'jar', version: '3.0.1']]] 
}

def nexusCD()
{
    nexusPublisher nexusInstanceId: 'nexus', nexusRepositoryId: 'test-nexus', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: 'jar', filePath: "DevOpsUsach2020-0.0.1.jar"]], mavenCoordinate: [artifactId: 'DevOpsUsach2020', groupId: 'com.devopsusach2020', packaging: 'jar', version: '3.1.1']]] 
}

return this;


/*
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