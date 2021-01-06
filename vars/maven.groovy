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
