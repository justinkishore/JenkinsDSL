freeStyleJob('DSL JOB end-end') {
    scm {
        github('justinkishore/NugetDemo', 'master')
    }
    triggers {
        githubPush()
    }
    steps {
        msBuild {
            msBuildInstallation('MSBuild-default')
            buildFile('NugetDemo/NugetDemo.sln')
            passBuildVariables()
            continueOnBuildFailure()
            unstableIfWarnings()
        }
msTestBuilder {
msTestName('MSTest')
testFiles('NugetDemo/TestNugetDemo/bin/Debug/TestNugetDemo.dll')
resultFile('NugetTest')
cmdLineArgs('')
categories('')
continueOnFail(false)
}
    }
    publishers {
       buildTrigger {
         childProjects('DSL JOB Chef Trigger')
         threshold('SUCCESS')
       }
         }

  wrappers
  {
  artifactoryGenericConfigurator {
details {
artifactoryName('jfrogrepo')
artifactoryUrl('https://kishore.jfrog.io/kishore')
deployReleaseRepository {
keyFromText('')
keyFromSelect('ext-release-local')
dynamicMode(false)
}
deploySnapshotRepository {
keyFromText('')
keyFromSelect('')
dynamicMode(false)
}
resolveReleaseRepository {
keyFromText('')
keyFromSelect('')
dynamicMode(false)
}
resolveSnapshotRepository {
keyFromText('')
keyFromSelect('')
dynamicMode(false)
}
userPluginKey('')
userPluginParams('')
}
resolverDetails {
artifactoryName('jfrogrepo')
artifactoryUrl('https://kishore.jfrog.io/kishore')

deployReleaseRepository {
keyFromText('')
keyFromSelect('')
dynamicMode(false)
}
deploySnapshotRepository {
keyFromText('')
keyFromSelect('')
dynamicMode(false)
}
resolveReleaseRepository {
keyFromText('')
keyFromSelect('')
dynamicMode(false)
}
resolveSnapshotRepository {
keyFromText('')
keyFromSelect('')
dynamicMode(false)
}
userPluginKey('')
userPluginParams('')

}
deployerCredentialsConfig {
username('')
password('')
credentialsId('')
overridingCredentials(false)
}
resolverCredentialsConfig {
username('')
password('')
credentialsId('')
overridingCredentials(false)
}
deployPattern('NugetDemo/MSI/bin/debug/*.*=>NugetDemo')
resolvePattern('')
matrixParams('')
deployBuildInfo(true)
includeEnvVars(false)
envVarsPatterns {
includePatterns('')
excludePatterns('*password*,*secret*,*key*')
}
discardOldBuilds(false)
discardBuildArtifacts(true)
multiConfProject(false)
artifactoryCombinationFilter('')
}
  }


    }

freeStyleJob('DSL JOB Chef Trigger') {
    scm {

    }
    triggers {
        githubPush()
    }
    steps {
      batchFile('knife ssl fetch')
        batchFile('knife winrm \'40.83.18.123\' \'chef-client -c c:/chef/client.rb\' -m -x kishorewin -P \'Kishorewin@87\'')
    }
}
