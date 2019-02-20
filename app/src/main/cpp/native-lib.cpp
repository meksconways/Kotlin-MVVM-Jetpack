//
// Created by mekso on 20.02.2019.
//
#include <jni.h>
#include <string>


extern "C" JNIEXPORT jstring JNICALL
/*function name : Java_+_{package name}_+{class name}_+{function name}  */
Java_com_mek_haberlerkotlin_home_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */)
        {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}


