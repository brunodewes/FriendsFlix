package com.friendsflix.network

import okhttp3.Interceptor
import okhttp3.Response

const val API_KEY = "15b87c756b37c553c83f88e5d3892c94"

class ApiKeyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val httpUrl = chain.request().url
        val newHttpUrl = httpUrl.newBuilder().addQueryParameter(API_KEY_QUERY, API_KEY).build()
        request.url(newHttpUrl)
        return chain.proceed(request.build())
    }

    companion object {
        const val API_KEY_QUERY = "api_key"
    }
}