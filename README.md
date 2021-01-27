# Logcat

[![Download](https://api.bintray.com/packages/lt-123/maven/logcat/images/download.svg)](https://bintray.com/lt-123/maven/logcat/)

一个 Java 平台的简易的 log 工具。 可用于 Java、 kotlin、 Android。共三个模块， logcat 是基础包， logcat4kt, 是kotlin 的扩展， logcat4Android 内置了 AndroidLogcatHandler（使用安卓内置的Log工具输出）。

## 集成

### 添加仓库

```groovy
maven { url "https://dl.bintray.com/lt-123/maven" }
```

### 添加依赖
  
### Java

```groovy
compile 'xyz.liut.logcat:logcat:版本号'
```

### kotlin

```groovy
compile 'xyz.liut.logcat:logcat:版本号'
compile 'xyz.liut.logcat:logcat4kt:版本号'
```

### android

```java
implementation 'xyz.liut.logcat:logcat:版本号'
implementation 'xyz.liut.logcat:logcat4kt:版本号'
implementation 'xyz.liut.logcat:logcat4android:版本号'
```

## 使用

### 初始化-以kotlin为例，Java也一样

```kotlin
L
    .getDefault()
    // 等级
    .setLevel(LogLevel.VERBOSE)
    //输出到控制台
    .addHandler(AndroidLogcatHandler("LOGCAT_" + Process.myPid() + "_"))
    // 标准输出 System.out
    .addHandler(StdHandler(false, true, false))
    // 输出到文件
    .addHandler(
        RollbackFileHandler(
            getExternalFilesDir("logcat").toString(),
            1,
            "yyyy-MM-dd-HH-mm-ss'.log'"
        )
    )
    // 自定义输出，到屏幕
    .addHandler { _, tag, msg ->
        runOnUiThread {
            with(tv_txt) {
                append("\n")
                append(tag)
                append(": ")
                append(msg)
            }
        }
    }
```

### 输出日志-Java

```java
L.v("verbose");
L.v("tag", "verbose");
L.d("debug");
L.d("tag", "debug");
L.i("info");
L.i("tag", "info");
L.w("warn");
L.w("tag", "warn");
L.w("tag", "warn", new Throwable("warn test"));
L.w("warn", new Throwable("warn test"));
L.e("error");
L.e("tag", "error");
L.e("tag", "error", new Throwable("error test"));
L.e("warn", new Throwable("error test"));
L.wtf("wtf");
L.wtf("tag", "wtf");
L.wtf("tag", "wtf", new Throwable("wtf test"));
L.wtf("wtf", new Throwable("wtf test"));
L.wtf(new Throwable("wtf test"));
```

### 输出日志-kotlin

```kotlin
logVerbose("verbose")
logDebug("debug")
logInfo("info")
logWarn("warn")
logError("error")
logWtf("what the fuck")

logVerbose("verbose", tag = "tag")
logDebug("debug", tag = "tag")
logInfo("info", tag = "tag")
logWarn("warn", tag = "tag")
logError("error", tag = "tag")
logWtf("what the fuck", tag = "tag")
```
