def call(){

pipeline {
    agent any
    parameters { choice(name: 'compilador', choices:['gradle','maven'], description:'compilador de construcion para aplicacion')}
    stages{
        stage('pipeline'){
            steps{
                script{
                    echo 'herramienta seleccionada: ' + params.compilador
                    def pipe = load "${params.compilador}.groovy"
                    pipe.call()
                }
            }
        }
    }

}

return this;