# VersionPlugin

## 使用

* 在项目根目录的```build.gradle```中添加以下代码：
```gradle
 buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath 'io.github.kongpf8848:version-plugin:1.0.1'
    }
}
```

* 在```app```模块的```build.gradle```中添加以下代码：
```gradle
apply plugin: 'version'

advancedVersioning{
    outputOptions{
        renameOutput true       
        nameFormat 'xxx_${buildType}_${versionName}_${versionCode}'
    }
}
```

## outputOptions说明
| 属性  | 说明 |
| :------| :------ | 
| renameOutput |是否重命名生成文件，默认为false|
| nameFormat | 重命名文件格式，可以使用插件内置属性字符串模板|

## 插件内置属性
| 属性  | 说明 |
| :------| :------ | 
| appName | app名称，如sky|
| flavorName | 渠道名称，此值可能为空，如定义productFlavors则不为空，如xiaomi,huawei,360... |
| buildType | 版本类型，如debug,release...|
| versionName | 版本名称，如1.2.3|
| versionCode | 版本号，如88 |

如我们想生成的文件名格式为zab_release_2.4.5_67.apk，其中zab为文件名前缀，release为编译类型，2.4.5为版本名称，67为版本号，则我们可以这样定义：

```gradle
advancedVersioning{
    outputOptions{
        renameOutput true       
        nameFormat 'zab_${buildType}_${versionName}_${versionCode}'
    }
}
```

