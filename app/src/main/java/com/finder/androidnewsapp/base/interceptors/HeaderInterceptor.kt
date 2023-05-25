package com.finder.androidnewsapp.base.interceptors

import com.finder.androidnewsapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
        builder.addHeader("X-Api-Key", BuildConfig.API_KEY)

        return chain.proceed(builder.build())
    }
}