package io.github.kongpf8848.plugin

import groovy.text.SimpleTemplateEngine
import org.gradle.api.Project

class FileOutputOptions {

    String nameFormat

    boolean renameOutput = false

    private def templateEngine = new SimpleTemplateEngine()

    void renameOutput(boolean b) {
        renameOutput = b
    }

    void nameFormat(String format) {
        nameFormat = format
    }

    def generateOutputName(Project project,variant) {
        def map = [
                'appName'    : project.name,
                'projectName': project.rootProject.name,
                'flavorName' : variant.flavorName,
                'buildType'  : variant.buildType.name,
                'versionName': variant.versionName,
                'versionCode': variant.versionCode
        ]

        //println("++++++++appName:${project.name}")
        //println("++++++++variant:${variant}")
        //println("++++++++project name:${project.rootProject.name}")
        //println("++++++++flavorName:${variant.flavorName}")
        //println("++++++++buildType:${variant.buildType.name}")
        //println("++++++++versionName:${variant.versionName}")
        //println("++++++++versionCode:${variant.versionCode}")
        def defaultTemplate =(!isEmpty(variant.flavorName)) ? '$appName-$flavorName-$buildType-$versionName-$versionCode' : '$appName-$buildType-$versionName-$versionCode'

        def template = nameFormat == null ? defaultTemplate : nameFormat
        def fileName = templateEngine.createTemplate(template).make(map).toString()
        if(!fileName.endsWith(".apk")){
            fileName+=".apk"
        }
        def androidGradlePlugin = VersionPlugin.getAndroidPluginVersion(project)
        if (androidGradlePlugin != null) {
            variant.outputs.all { output ->
                output.outputFileName="${fileName}"
            }
        }
    }

    static def isEmpty(String str){
        return str==null || str.length()==0
    }

}

