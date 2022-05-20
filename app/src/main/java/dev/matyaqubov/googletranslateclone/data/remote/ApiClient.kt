package dev.matyaqubov.googletranslateclone.data.remote

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    var BASE_URl = "https://google-translate1.p.rapidapi.com/language/translate/"

    private val client = getClient()
    private val retrofit = getRetrofit(client)


    private fun getRetrofit(client: OkHttpClient): Retrofit {

        val okHttpClient = OkHttpClient
            .Builder()
            .build()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(BASE_URl)
            .client(client)
            .build()
    }

    private fun getClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .addInterceptor(Interceptor { chain ->
            val builder = chain.request().newBuilder()
            chain.proceed(builder.build())
        })
        .build()

    private fun <T> createServiceWithAuth(service: Class<T>?): T {
        val newClient =
            client.newBuilder().addInterceptor(Interceptor { chain ->
                var request = chain.request()
                val builder = request.newBuilder()
                builder.addHeader("Content-Type", "application/x-www-form-urlencoded")
                builder.addHeader("Accept-Encoding", "application/gzip")
                builder.addHeader("X-RapidApi-Host", "google-translate1.p.rapidapi.com")
                builder.addHeader(
                    "X-RapidAPI-Key",
                    "8a1e73773cmsh36547dcac413d7bp11d777jsn5348682a7c37"
                )
                request = builder.build()
                chain.proceed(request)
            }).build()
        val newRetrofit = retrofit.newBuilder().client(newClient).build()
        return newRetrofit.create(service!!)
    }

    val translateService = createServiceWithAuth(ApiService::class.java)
}