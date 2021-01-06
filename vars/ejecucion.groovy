import ejecucion.groovy.*

def call()
{
    pipeline
    {
        agent any
        parameters
        {
            choice(name: 'par_buildtool', choices: ['gradle','maven'], description: 'Opciones disponibles para construcción de la aplicación')
        }
        stages
        {
            stage('Pipeline')
            {
                steps
                {
                    script
                    {
                        if (params.par_buildtool == 'gradle')
                        {
                            gradle.call()
                        }
                        else
                        {
                            maven.call()
                        }
                    }
                }
            }
        }
    }
}

return this;
/*
def call(){
    
    parameters{ choice(name: 'compilador', choices:['gradle','maven'], description:'compilador de construcion para aplicacion')
            //   choice(name: 'sub_stage', choices:['build','build,test y run','fullbuild'], description:'Construccion por stages')}
            string(name:'etapa',defaultValue:'',description:'Seleccion de stage.Opciones para Gradle: Build; Sonar; Run; Test; Nexus; gitCreateRelease. Opciones para Maven: Compile; Unit; Jar; Sonar; Sonar; Test; gitCreateRelease')}
 
        stages{
            stage{
                script{
                    println 'herramienta de ejecucion gradle' + params.compilador

                    if(params.compilador == 'gradle'){
                        gradle "${params.etapa}"
                    } else {
                        maven "${params.etapa}"
                    }
            }
        }
 
 /* pipeline {
        agent any
        stages{
            stage('pipeline'){
                steps{
                    script{
                        if (env.BRANCH_NAME)
                                    'buildAndTest','sonar','runJar','rest','nexusCI'
                        else if('downloadNexus','runDownloadedJar','rest','nexusCD')
                        if(feature or develop ==)Ejecutar(pipeline CI)
                        release == Ejecutar(pipeline CD)

                        echo 'herramienta seleccionada: ' + params.compilador
                        /*"${params.compilador}".call()*/
                        /*def pipe = load "${params.compilador}.groovy"
                        pipe.call()*/
             /*           if(params.compilador == 'gradle' && params.etapa == 'build'){
                             gradle "build"
                            
                            }else if(params.compilador == 'gradle' && params.etapa == 'build;sonar;run'){
                                gradle 'build';
                                gradle 'sonar';
                                gradle 'run';
                                
                                    }else if(params.compilador == 'gradle' && params.etapa =='build;sonar;run;test;nexus'){
                                        gradle 'build';
                                        gradle 'sonar';
                                        gradle 'run';
                                        gradle 'test';
                                        gradle 'nexus';
                                        
                                        }else if(params.compilador == 'gradle' && params.etapa == ''){
                                        gradle 'build';
                                        gradle 'sonar';
                                        gradle 'run';
                                        gradle 'test';
                                        gradle 'nexus';   
                                            }
                        else if(params.compilador == 'maven' && params.etapa == 'build'){
                                maven 'build';                         
                                }else if(params.compilador == 'maven' && params.etapa == 'build,test y run'){
                                    maven 'build';
                                    maven 'test';
                                    maven 'jar';
                                    maven 'run';
                                    maven 'testing';
                                    maven 'nexus';
                                    }else if(params.compilador == 'maven' && params.etapa == 'build,test y run'){
                                    maven 'build';
                                    maven 'test';
                                    maven 'jar';
                                    maven 'run';
                                    maven 'testing';
                                    maven 'nexus';
                                        }else{
                                        throw new Exception('Stages no ingresadas correctamente en maven o se cumplieron los anteriores');
                                        }    
                            }
                        } 
                   }
                }
            }
        }
    
return this;