//stage.groovy

import ejecucion.groovy.*

def call(String stage.params, String sub_stage.params){

    println 'Ejecución de Pipeline'

    pipeline{
        agent any
        stages{
            stage('Pipeline'){
                steps{
                    script{
                        try {

                            def funciones   = new Funciones()

                            stage('Inicio'){
                                println 'Inicio'
                                println 'String 1: ' + param1
                                println 'String 2: ' + param2

                                gradle.call()
                            }

                            stage('Union'){
                                println 'Union de 2 Strings: ' + funciones.unirDosStrings(param1, param2)          
                            }

                            stage('MostrarNombre'){
                                println 'Nombre obtenido desde Json: ' + funciones.mostrarNombre()
                            }

                        } catch(Exception e) {
                            error ('Ha ocurrido el siguiente error: ' + e)
                        }
                    }
                }
            }
        }
    }
}

return this;


   /* pipeline{
        agent any
                stages{
            stage('pipeline'){
                steps{
                    script{
                        echo 'herramienta seleccionada: ' + params.stage
                        /*"${params.compilador}".call()*/
                        /*def pipe = load "${params.compilador}.groovy"
                        pipe.call()*/
                      /*  if(params.stage == 'gradle' && params.sub_stage == 'build'){
                            gradle.call('build & test')
                            }else if(params.stage == 'gradle' && params.sub_stage == 'build,test y run'){
                                gradle.call('build & test')
                                gradle.call('run')
                                    }else(params.stage == 'gradle' && params.sub_stage == 'fullbuild'){
                                        gradle.call('')
                                    }               
                        if(params.stage == 'maven' && params.sub_stage == 'build'){
                            gradle.call('build & test')
                            }else if(params.stage == 'maven' && params.sub_stage == 'build,test y run'){
                                gradle.call('build & test')
                                gradle.call('run')
                                    }else(params.stage == 'maven' && params.sub_stage == 'fullbuild'){
                                        gradle.call('')
                                    }     

                        }  
                }
            }
        }
    }

return this;*/