# ============================================================
# Release 混淆规则（模板默认未开启 minify，规则先备好）
# 说明：开启 isMinifyEnabled = true 后，以下规则生效
# ============================================================

# 保留 kotlinx.serialization 生成的序列化器，避免反射解析失败
-keepattributes *Annotation*, InnerClasses
-dontnote kotlinx.serialization.**
-keepclassmembers class **$$serializer {
    *** descriptor;
}
-keepclasseswithmembers class * {
    @kotlinx.serialization.Serializable <fields>;
}

# 保留 Retrofit 接口方法上的注解与泛型签名
-keepattributes Signature, Exceptions
-keep,allowobfuscation,allowshrinking interface retrofit2.Call
-keep,allowobfuscation,allowshrinking class retrofit2.Response
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation

# 保留 OkHttp / Retrofit 平台调用相关类（官方推荐规则）
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn retrofit2.**
