package com.codespacepro.medicinecompose.di.network

import com.codespacepro.medicinecompose.di.api.MedicineApi
import com.codespacepro.medicinecompose.model.Medicines
import com.codespacepro.medicinecompose.utils.Constant.Companion.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {

    private val client = OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)
        .build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: MedicineApi by lazy {
        retrofit.create(MedicineApi::class.java)
    }


}