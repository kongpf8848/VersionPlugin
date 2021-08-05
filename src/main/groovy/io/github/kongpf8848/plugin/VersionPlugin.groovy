package io.github.kongpf8848.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class VersionPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        def androidGradlePlugin = getAndroidPluginVersion(project)
        if (androidGradlePlugin == null) {
            throw new IllegalStateException("The Android Gradle plugin not found")
        }
        def advancedVersioning = project.extensions.create("advancedVersioning", AdvancedBuildVersionExtension, project)
        project.configurations {
            project.android.applicationVariants.all {
                if (advancedVersioning.outputOptions.renameOutput) {
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