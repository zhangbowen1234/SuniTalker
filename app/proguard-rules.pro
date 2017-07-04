# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in F:\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# 暂时不能进行混淆，会出错

###-----------基本配置-不能被混淆的------------
-keep public class * extends android.app.Fragment 
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService
-keep class com.silver.clound.model.** { *;}
#support.v4/v7包不混淆
-keep class android.support.** { *; }
-keep class android.support.v4.** { *; }
-keep public class * extends android.support.v4.**
-keep interface android.support.v4.app.** { *; }
-keep class android.support.v7.** { *; }
-keep public class * extends android.support.v7.**
-keep interface android.support.v7.app.** { *; }
-dontwarn android.support.**    # 忽略警告
#保持注解继承类不混淆
-keep class * extends java.lang.annotation.Annotation {*;}
#保持Serializable实现类不被混淆
-keepnames class * implements java.io.Serializable
#保持Serializable不被混淆并且enum 类也不被混淆
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
#保持枚举enum类不被混淆
-keepclassmembers enum * {
  public static **[] values();
 public static ** valueOf(java.lang.String);
}
#自定义组件不被混淆
-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
}
#不混淆资源类
-keepclassmembers class **.R$* {
    public static <fields>;
}
###-----------第三方jar包library混淆配置------------
#ormlite混淆配置
#-libraryjars libs/ormlite-android-5.0.jar
#-libraryjars libs/ormlite-core-5.0.jar
-dontwarn com.j256.ormlite.**
-keep class com.j256.ormlite.** { *;}
-libraryjars libs/greendao-1.3.7.jar
-keep class de.greenrobot.dao.** {*;}
#保持greenDao的方法不被混淆
#用来保持生成的表名不被混淆    
-keepclassmembers class * extends de.greenrobot.dao.AbstractDao {

public static java.lang.String TABLENAME; }
-keep class **$Properties
#Gson相关的不混淆配置
-keepattributes Signature
-keepattributes *Annotation*
-keep class com.google.gson.** { *; }
-dontwarn com.google.gson.**
#prt-lib下拉刷新框架不混淆
-keep class in.srain.cube.views.ptr.** { *; }
-dontwarn in.srain.cube.views.ptr.**
#PullToRefreshLibrary下拉刷新框架不混淆
-keep class com.handmark.pulltorefresh.library.** { *; }
-dontwarn com.handmark.pulltorefresh.library.**
-optimizationpasses 5
# 指定代码的压缩级别  

-dontusemixedcaseclassnames 
# 是否使用大小写混合  

-dontskipnonpubliclibraryclasses
# 是否混淆第三方jar  

-dontpreverify
# 混淆时是否做预校验  

-verbose
# 混淆时是否记录日志  

-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
# 混淆时所采用的算法  
# 保持 native 方法不被混淆  
-keepclasseswithmembernames class * {
    native <methods>;
}
# 保持 Parcelable 不被混淆  
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
-keep class MyClass;
# 保持自己定义的类不被混淆  