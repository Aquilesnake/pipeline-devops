//import ejecucion.groovy.*

def call(){

    pipeline {
        agent any
        parameters { choice(name: 'compilador', choices:['gradle','maven'], description:'compilador de construcion para aplicacion')
                     choice(name: 'stage', choices:['build','build;test;run','fullbuild'], description:'Construccion por stages')}

        stages{
            stage('pipeline'){
                steps{
                    script{
                        echo 'herramienta seleccionada: ' + params.compilador
                        stage.call()
                        /*"${params.compilador}".call()*/
                        /*def pipe = load "${params.compilador}.groovy"
                        pipe.call()*/
                   /*     if(params.compilador == 'gradle' && params.stage == 'build'){
                            gradle.call('build & test')
                            }else if(params.compilador == 'gradle' && params.stage == 'build,test y run'){
                                gradle.call('build & test')
                                gradle.call('run')
                                    }else(params.compilador == 'gradle' && params.stage == 'fullbuild'){
                                        gradle.call()
                                    }
                       if(params.compilador == 'maven' && params.stage == 'build'){
                            gradle.call('build & test')
                            }else if(params.compilador == 'maven' && params.stage == 'build,test y run'){
                                gradle.call('build & test')
                                gradle.call('run')
                                    }else(params.compilador == 'maven' && params.stage == 'fullbuild'){
                                        gradle.call()
                                    }     

                        }  */
                    }
                }
            }
        }
    }
} 
return this;