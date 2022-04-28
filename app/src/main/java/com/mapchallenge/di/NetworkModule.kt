package com.mapchallenge.di

import android.annotation.SuppressLint
import com.mapchallenge.network.ServiceInterface
import com.mapchallenge.utils.BASE_URL
import com.mapchallenge.utils.CustomHttpLogger
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

class NetworkModule {

    fun service(): ServiceInterface {

        val httpClient = getHttpClient()

        val clt = httpClient.build()

        val retrofit = getRetrofitBuilder(clt).build()

        return retrofit.create(ServiceInterface::class.java)
    }
}

fun getUnsafeOkHttpClient(builder: OkHttpClient.Builder): OkHttpClient.Builder {
    try {
        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
            @SuppressLint("TrustAllX509TrustManager")
            @Throws(CertificateException::class)
            override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
            }

            @SuppressLint("TrustAllX509TrustManager")
            @Throws(CertificateException::class)
            override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
            }

            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }
        })

        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, java.security.SecureRandom())

        val sslSocketFactory = sslContext.socketFactory

        builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
        builder.hostnameVerifier { _, _ -> true }
        return builder
    } catch (e: Exception) {
        e.printStackTrace()
        throw RuntimeException(e)
    }

}

fun getHttpClient(): OkHttpClient.Builder {
    val logging = HttpLoggingInterceptor(CustomHttpLogger())
    logging.level = HttpLoggingInterceptor.Level.BODY


    val httpClient = getUnsafeOkHttpClient(OkHttpClient.Builder().apply {
        connectTimeout(1, TimeUnit.HOURS)
        writeTimeout(1, TimeUnit.HOURS)
        readTimeout(1, TimeUnit.HOURS)
        callTimeout(1, TimeUnit.HOURS)
    })
    httpClient.addInterceptor(logging)
    httpClient.addInterceptor { chain ->
        val original = chain.request()
        val request = original.newBuilder()
            .header("Content-Type", "application/json; charset=utf-8")


        chain.proceed(request.build())
    }
    return httpClient
}

fun getRetrofitBuilder(client: OkHttpClient): Retrofit.Builder {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(client)
}