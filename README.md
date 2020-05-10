# VersionPlugin

## 使用方法

+ 在项目的根目录build.gralde脚本中添加以下代码：
```gradle
    dependencies {
        classpath 'com.github.kongpf8848.plugin:version:1.0.0'
    }
```

* 在app模块的build.gradle中添加以下代码：
```gradle
apply plugin: 'version'

advancedVersioning{
    outputOptions{
        renameOutput true       
        nameFormat 'xxx_${buildType}_${versionName}_${versionCode}'
    }
}
```
