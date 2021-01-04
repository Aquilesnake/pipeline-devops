def call(){
   parameters { choice(name: 'compilador', choices:['gradle','maven'], description:'compilador de construcion para aplicacion')
            //   choice(name: 'sub_stage', choices:['build','build,test y run','fullbuild'], description:'Construccion por stages')}
            string(name:'etapa',defaultValue:'',description:'Seleccion de stage.Opciones para Gradle: Build; Sonar; Run; Test; Nexus; gitCreateRelease. Opciones para Maven: Compile; Unit; Jar; Sonar; Sonar; Test; gitCreateRelease')}
  pipeline {
        agent any
        stages{
            stage('pipeline'){
                steps{
                    script{
                        echo 'herramienta seleccionada: ' + params.compilador
                        /*"${params.compilador}".call()*/
                        /*def pipe = load "${params.compilador}.groovy"
                        pipe.call()*/
                        if(params.compilador == 'gradle' && params.etapa == 'build'){
                            gradle.call('gradle','build & test')
                            }else if(params.compilador == 'gradle' && params.etapa == 'build,test y run'){
                                gradle.call('build & test')
                                gradle.call('run')
                                    }else(params.compilador == 'gradle' && params.etapa == 'fullbuild'){
                                        gradle.call('')
                                    }     
                        if(params.compilador == 'maven' && params.etapa == 'build'){
                            gradle.call('build & test')
                            }else if(params.compilador == 'maven' && params.etapa == 'build,test y run'){
                                gradle.call('build & test')
                                gradle.call('run')
                                    }else(params.compilador == 'maven' && params.etapa == 'fullbuild'){
                                        gradle.call('')
                                    }     

                        } 
                   }
                }
            }
        }
    }
return this;