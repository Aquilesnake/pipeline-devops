def call()
{
    pipeline
    {
        agent any
        parameters
        {
            choice(name: 'par_buildtool', choices: ['gradle','maven'], description: 'COVID-19 : Opciones disponibles para construcción de la aplicación')
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
