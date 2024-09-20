
node('linux')
{
  stage ('Poll') {
                // Poll for local changes
                checkout([
                        $class: 'GitSCM',
                        branches: [[name: '*/main']],
                        doGenerateSubmoduleConfigurations: false,
                        extensions: [],
                        userRemoteConfigs: [[url: "https://github.com/zopencommunity/groffport.git"]]])
  }

  stage('Build') {
    build job: 'Port-Pipeline', parameters: [
      string(name: 'PORT_GITHUB_REPO', value: 'https://github.com/zopencommunity/groffport.git'), 
      string(name: 'PORT_DESCRIPTION', value: 'Typesetting library'),
      string(name: 'BUILD_LINE', value: 'STABLE')
    ]
  }
}

