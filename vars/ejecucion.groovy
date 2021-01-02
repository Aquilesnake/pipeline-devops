def call(){

    pipeline {
        agent any
        parameters { choice(name: 'stage', choices:['gradle','maven'], description:'compilador de construcion para aplicacion')
                  //   choice(name: 'sub_stage', choices:['build','build,test y run','fullbuild'], description:'Construccion por stages')}
     string(name:'Stage',defaultValue:'',description:'''Selección de stage.Opciones para Gradle: Build; Sonar; Run; Test; Nexus; gitCreateRelease.
Opciones para Maven: Compile; Unit; Jar; Sonar; Sonar; Test; gitCreateRelease''')}

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
                                    }else(params.compilador == 'gradle' && params.sub_stage == 'fullbuild'){
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