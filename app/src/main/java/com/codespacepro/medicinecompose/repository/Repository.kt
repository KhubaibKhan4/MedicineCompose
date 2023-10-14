package com.codespacepro.medicinecompose.repository

import com.codespacepro.medicinecompose.di.network.RetrofitInstance
import com.codespacepro.medicinecompose.model.Medicines
import retrofit2.Response

class Repository {
    suspend fun getMedicines(): Response<Medicines> {
        return RetrofitInstance.api.getMedicines()
    }
}