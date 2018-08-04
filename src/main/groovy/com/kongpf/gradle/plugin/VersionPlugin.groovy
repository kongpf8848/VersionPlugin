package com.kongpf.gradle.plugin

import com.kongpf.gradle.plugin.internal.AdvancedBuildVersionExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class VersionPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {

        def androidGradlePlugin = getAndroidPluginVersion(project)
        if (androidGradlePlugin == null) {
            throw new IllegalStateException("The Android Gradle plugin not found")
        } else if (!checkAndroidVersion(androidGradlePlugin.version)) {
            throw new IllegalStateException("The Android Gradle plugin ${androidGradlePlugin.version} is not supported.")
        }

        println(androidGradlePlugin.name+"---version:"+androidGradlePlugin.version)

        def advancedVersioning = project.extensions.create("advancedVersioning", AdvancedBuildVersionExtension, project)


        project.afterEvaluate {
            if (advancedVersioning.outputOptions.renameOutput) {
                project.android.applicationVariants.all {
                    advancedVersioning.outputOptions.generateOutputName(project, it)
                }

            }

        }

    }

    private static final String[] SUPPORTED_ANDROID_VERSIONS = ['0.14.', '1.', '2.', '3.']

    def static boolean checkAndroidVersion(String version) {
        for (String supportedVersion : SUPPORTED_ANDROID_VERSIONS) {
            if (version.startsWith(supportedVersion)) {
                return true
            }
        }

        return false
    }

    def static getAndroidPluginVersion(Project project) {
        def projectGradle = findClassPathDependencyVersion(project, 'com.android.tools.build', 'gradle')
        if (projectGradle == null) {
            projectGradle = findClassPathDependencyVersion(project.getRootProject(), 'com.android.tools.build', 'gradle')
        }
        return projectGradle
    }

    def static findClassPathDependencyVersion(Project project, String group, String artifactId) {
        return project.buildscript.configurations.classpath.dependencies.find {
            it.group != null && it.group.equals(group) && it.name != null && it.name.equals(artifactId);
        }
    }
}