def call(){

    /* groovylint-disable-next-line NglParseError */
    pipeline {
        agent any
        parameters { choice(name: 'stage', choices:['gradle','maven'], description:'compilador de construcion')}
        /* groovylint-disable-next-line LineLength */
        string {name: 'sub_stage', defaultValue:'', description:'Selección de stage')}
        /* groovylint-disable-next-line LineLength */
        /*parameters { choice(name: 'sub_stage', choices:['build','build,test y run','fullbuild'], description:'Construccion por stages')}*/

        stages{
            stage('pipeline'){
                steps{
                    script{
                        echo 'herramienta seleccionada: ' + params.stage
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