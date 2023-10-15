package com.example.recyclerviewenhanced.data.sources.remote

import okhttp3.Interceptor
import okhttp3.Response

class ServiceInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url.newBuilder()
            // .addQueryParameter("device_id", App.deviceId!!)
            // .addQueryParameter("device_platform", "Android")
            .addQueryParameter("api_key", "8979fea4ecc57850778fa624133234d8")
            .build()
        val request = chain.request().newBuilder()
            // .addHeader("Authorization", "Bearer token")     necesario para llamadas que pasen token
            .url(url)
            .build()
        return chain.proceed(request)
    }
}
