package dev.matyaqubov.googletranslateclone.data.remote

import dev.matyaqubov.googletranslateclone.model.LanguageDetection
import dev.matyaqubov.googletranslateclone.model.TranslationResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

@JvmSuppressWildcards
interface ApiService {

    @FormUrlEncoded
    @POST("v2/detect")
    fun detectLanguage(@Field("q") q: String): Call<LanguageDetection>

    @FormUrlEncoded
    @POST("v2/")
    suspend fun translateToEng(
        @Field("q") q: String,
        @Field("source") source: String,
        @Field("target") target: String
    ): TranslationResponse
}