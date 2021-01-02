def call(){

    pipeline {
        agent any
        parameters { choice(name: 'stage', choices:['gradle','maven'], description:'compilador de construcion para aplicacion')}
        parameters { choice(name: 'sub_stage', choices:['build','build,test y run','fullbuild'], description:'Construccion por stages')}

        stages{
            stage('pipeline'){
                steps{
                    script{
                        echo 'herramienta seleccionada: ' + params.compilador
                        /*"${params.compilador}".call()*/
                        /*def pipe = load "${params.compilador}.groovy"
                        pipe.call()*/
                        if(params.stage == 'gradle' && params.sub_stage == 'build'){
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
}
return this;