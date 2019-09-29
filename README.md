# Logcat

[ ![Download](https://api.bintray.com/packages/lt-123/maven/logcat/images/download.svg) ](https://bintray.com/lt-123/maven/logcat/)

一个 Java 平台的简易的 log 工具。 可用于 Java、 kotlin、 Android。共三个模块， logcat 是基础包， logcat4kt, 是kotlin 的扩展， logcat4Android 内置了 AndroidLogcatHandler（使用安卓内置的Log工具输出）。 

- 添加依赖
  
    - Java
        ``` groovy
        compile 'xyz.liut.logcat:logcat:xxx'
        ```
    - kotlin
        ``` groovy
        compile 'xyz.liut.logcat:logcat:xxx'
        compile 'xyz.liut.logcat:logcat4kt:xxx'
        ```
    - android 
        ```groovy
        implementation 'xyz.liut.logcat:logcat:xxx'
        implementation 'xyz.liut.logcat:logcat4kt:xxx'
        implementation 'xyz.liut.logcat:logcat4android:xxx'
        ```
- 使用 以 kotlin 为例
	``` kotlin
    // 添加 LogHandler， 必须， 内置 StdHandler， 可继承 LogHandler 实现自己的， 可添加多个。
    Logcat.getHandlers().add(StdHandler())
    // 设置 level （可选， 默认全开）
    Logcat.setLevel(LogLevel.WARN)

    // 调用
    println("----------------")
    L.v("verbose 1")
    L.d("debug 1")
    L.i("info 1")
    L.w("warn 1")
    L.e("warn 1")
    L.w("tag", "warn 1")
    L.e("tag", "eeeeeee 1")

    // 调用 kotlin
    println("----------------")
    "verbose".logVerbose("--tag--")
    "debug ".logDebug("--tag--")
    "info ".logInfo("--tag--")
    "info ".logInfo()
    "warn ".logWarn()
    "error ".logError()

    println("-------关闭 log---------")
    Logcat.setLevel(LogLevel.NO)
    "error ".logError()
	```

    
  