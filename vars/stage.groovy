//stage.groovy
def call(java.lang.String uno, org.jenkinsci.plugins.workflow.cps.CpsClosure2 dos){
    println 'asdasdasd aEjecución de Pipeline'

            stage('cualquiewa'){
            
                        try {
                        if(compilador == 'gradle' && sub_stage == 'build'){
                         //   gradle.call('build & test')
                            }else if(compilador == 'gradle' && sub_stage == 'build;test;run'){
                          //      gradle.call('build & test')
                         //       gradle.call('run')
                                    }else(compilador == 'gradle' && sub_stage == 'fullbuild'){
                          //              gradle.call()
                                    }
                        if(compilador == 'maven' && sub_stage == 'build'){
                           // gradle.call('build & test')
                            }else if(compilador == 'maven' && sub_stage == 'build,test;run'){
                           //     gradle.call('build & test')
                           //     gradle.call('run')
                                    }else(compilador == 'maven' && sub_stage == 'fullbuild'){
                            //            gradle.call()
                                    }
                            } catch(Exception e) {
                                error ('Ha ocurrido el siguiente error: ' + e)
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