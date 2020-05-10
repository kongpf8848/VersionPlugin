# VersionPlugin

## 使用方法

+ 在项目根目录的build.gralde脚本中添加以下代码：
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
        nameFormat 'xxx_${versionName}_${versionCode}'
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

