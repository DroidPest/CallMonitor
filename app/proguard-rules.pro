# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-keep class dagger.hilt.** { *; }
-keep class javax.inject.** { *; }
-keep class com.google.inject.** { *; }
-keepclasseswithmembers class * {
    @dagger.hilt.* <methods>;
}

-keep interface android.support.** { *; }
-keep class android.support.** { *; }
-keep class androidx.annotation.** { *; }

-dontwarn kotlinx.serialization.**
-dontwarn java.beans.**

# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature

# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { <fields>; }

# Prevent R8 from leaving Data object members always null
-keepclassmembers,allowobfuscation class * {
  @com.google.gson.annotations.SerializedName <fields>;
}

# Retain generic signatures of TypeToken and its subclasses with R8 version 3.0 and higher.
-keep,allowobfuscation,allowshrinking class com.google.gson.reflect.TypeToken
-keep,allowobfuscation,allowshrinking class * extends com.google.gson.reflect.TypeToken

-dontwarn com.google.**
-dontwarn android.media.**
-keep class android.media.** { *; }
-keep class com.google.** { *; }
-keep interface com.google.** { *; }

-keepattributes Exceptions, InnerClasses, Signature, Deprecated, SourceFile, LineNumberTable, *Annotation*, EnclosingMethod

-keep class com.google.android.gms.ads.identifier.** { *; }

# Gson
-keep class * extends com.google.gson.TypeAdapter
-dontwarn sun.misc.**
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.examples.android.model.** { *; }
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

# OkHttp3
-dontwarn okhttp3.**
-dontwarn org.codehaus.mojo.animal_sniffer.*
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase

# Okio
-dontwarn okio.**

# Logback and slf4j-api
-dontwarn ch.qos.logback.core.net.*
-dontwarn org.slf4j.**
-keep class ch.qos.** { *; }
-keep class org.slf4j.** { *; }

# Keep annotations to retain runtime functionality
-keepattributes *Annotation*

# If you face issues with inner classes, add:
-keepattributes InnerClasses

# Avoid issues with lambda functions if you're using them with TheoPlayer
-keepnames class kotlin.Metadata { *; }
-keepclassmembers class **$WhenMappings {
    <fields>;
}

# For native Google IMA integration
-keep class com.google.obf.** { *; }
-keep interface com.google.obf.** { *; }
-keep class com.google.ads.interactivemedia.** { *; }
-keep interface com.google.ads.interactivemedia.** { *; }

# And in case of using a CastOptionsProvider and/or custom MediaRouteActionProvider for chromecast, you have to keep those classes too.
# eg:
-keep class com.yourcomp.yourchromecastpackage.** {*;}
-dontwarn com.yourcomp.yourchromecastpackage.**
