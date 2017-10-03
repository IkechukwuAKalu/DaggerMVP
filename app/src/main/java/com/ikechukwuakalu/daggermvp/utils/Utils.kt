package com.ikechukwuakalu.daggermvp.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val TAG = "AppClass"

fun info(msg: String){
    Log.i(TAG, msg)
}

fun error(msg: String) {
    Log.e(TAG, msg)
}

fun warn(msg: String) {
    Log.w(TAG, msg)
}

fun makeToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun <T> createService(serviceClass: Class<T>) : T{
    val service = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.github.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    return service.create(serviceClass)
}