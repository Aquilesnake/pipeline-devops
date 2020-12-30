def call(){

    pipeline {
        agent any
        parameters { choice(name: 'compilador', choices:['gradle','maven'], description:'compilador de construcion para aplicacion')}
        string {name: 'stage', defaultValue:'', description ''}

        stages{
            stage('pipeline'){
                steps{
                    script{
                        echo 'herramienta seleccionada: ' + params.compilador
                        /*"${params.compilador}".call()*/
                        /*def pipe = load "${params.compilador}.groovy"
                        pipe.call()*/
                        if(params.compilador == 'gradle'){
                            gradle.call()
                        }else {
                            maven.call()

                        }
                    }
                }
            }
        }

    }
}
return this;