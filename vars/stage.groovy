def call(String compilador,String sub_stage){
    echo 'complilador'+compilador
        stages{
            stage('pipeline'){
                steps{
                    script{
                       // def pipe = load "${params.compilador}.groovy"
                       // pipe.call()

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