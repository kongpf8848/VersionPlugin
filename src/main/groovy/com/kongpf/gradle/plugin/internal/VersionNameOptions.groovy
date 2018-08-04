package com.kongpf.gradle.plugin.internal

import org.gradle.api.GradleScriptException

class VersionNameOptions {

    int mVersionMajor = 0
    int mVersionMinor = -1
    int mVersionPatch = -1
    int mVersionBuild = -1

    void versionMajor(int major) {
        this.mVersionMajor = major
    }

    void versionMinor(int minor) {
        this.mVersionMinor = minor
    }

    void versionPatch(int patch) {
        this.mVersionPatch = patch
    }

    void versionBuild(int build) {
        this.mVersionBuild = build
    }


    def getVersionName() {
        if (this.mVersionMajor < 0) {
            throw new GradleScriptException("nameOptions.versionMajor could not be less than 0", new Throwable());
        }
        if (this.mVersionMinor < -1) {
            throw new GradleScriptException("nameOptions.versionMinor could not be less than -1", new Throwable());
        }
        if (this.mVersionPatch < -1) {
            throw new GradleScriptException("nameOptions.versionPatch could not be less than -1", new Throwable());
        }
        if (this.mVersionBuild < -1) {
            throw new GradleScriptException("nameOptions.versionBuild could not be less than -1", new Throwable());
        }

        String build = this.mVersionBuild != -1 ? "." + this.mVersionBuild : ""
        String patch = this.mVersionPatch != -1 ? "." + mVersionPatch :  this.mVersionBuild != -1 ? ".0" : ""
        String minor = this.mVersionMinor != -1 ? "." + this.mVersionMinor : ".0"
        String major = String.valueOf(this.mVersionMajor)

        return major + minor + patch + build
    }
}
