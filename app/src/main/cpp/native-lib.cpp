//
// Created by mekso on 20.02.2019.
//
#include <jni.h>
#include <string>
#include "SimpleSum.h"

using namespace std;

extern "C"
JNIEXPORT jstring JNICALL
/*function name : Java_+_{package name}_+{class name}_+{function name}  */
Java_com_mek_haberlerkotlin_home_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */)
        {
    string hello = "Hello World from C++";
    return env->NewStringUTF(hello.c_str());
}
extern "C"
JNIEXPORT jint JNICALL
Java_com_mek_haberlerkotlin_home_MainActivity_simpleSum(JNIEnv *env, jobject instance, jint num1, jint num2) {

    auto *simpleSum = new SimpleSum(num1, num2);
    int result = simpleSum->sum();

    return result;
}




