package com.kongpf.gradle.plugin.internal

import org.gradle.api.Project

import javax.inject.Inject

class AdvancedBuildVersionExtension {

    final Project project;
    FileOutputOptions outputOptions
    VersionNameOptions nameOptions;

    @Inject
    AdvancedBuildVersionExtension(Project project) {
        this.project = project
        nameOptions=new VersionNameOptions();
        outputOptions = new FileOutputOptions()

    }

    void nameOptions(Closure c){
        project.configure(nameOptions,c)
    }

    void outputOptions(Closure c) {
        project.configure(outputOptions, c)
    }

    String getVersionName()
    {
        return nameOptions.getVersionName()
    }

}
