package com.github.kongpf8848.plugin

import com.github.kongpf8848.plugin.FileOutputOptions
import org.gradle.api.Project

import javax.inject.Inject

class AdvancedBuildVersionExtension {

    final Project project;
    FileOutputOptions outputOptions

    @Inject
    AdvancedBuildVersionExtension(Project project) {
        this.project = project
        outputOptions = new FileOutputOptions()
    }


    void outputOptions(Closure c) {
        project.configure(outputOptions, c)
    }


}
