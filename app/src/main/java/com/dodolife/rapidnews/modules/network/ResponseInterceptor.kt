package com.dodolife.rapidnews.modules.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import okio.IOException
import retrofit2.Retrofit
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.security.cert.CertificateException

object ResponseInterceptor : Interceptor {

    var retrofit: Retrofit? = null

    var getToken: String? = null

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val headers = request.headers.values("@")
        if (headers.contains("Auth")) {
            val builder = request.newBuilder()
            getToken?.let { builder.addHeader("Authorization", it) }
            request = builder.removeHeader("@").build()
        }
        val response = try {
            chain.proceed(request)
        } catch (error: Exception) {
            val message = when (error) {
                is UnknownHostException -> "Tidak dapat terhubung dengan server"
                is SocketTimeoutException -> "Koneksi timeout"
                is CertificateException -> "Membutuhkan sambungan koneksi terpercaya"
                else -> "Something went wrong"
            }
            throw IOException(message)
        }
        throwIfError(response)
        return response
    }

    private fun throwIfError(response: Response) {
        if (response.code in 200..299) {
            return
        }
        val errorBody = getError<Error>(response.body)
        if(errorBody != null) {
            val message = errorBody.message
            val exception = java.io.IOException(message)
            Log.e("SintagApp", "error response: ", exception)
            throw exception
        }
    }

    private inline fun <reified T> getError(body: ResponseBody?): T? = body?.let {
        retrofit?.responseBodyConverter<T>(T::class.java, T::class.java.annotations)
            ?.convert(it)
    }

}