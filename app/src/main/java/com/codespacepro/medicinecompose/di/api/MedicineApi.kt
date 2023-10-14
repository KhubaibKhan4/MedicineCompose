package com.codespacepro.medicinecompose.di.api

import com.codespacepro.medicinecompose.model.Medicines
import retrofit2.Response
import retrofit2.http.GET

interface MedicineApi {

    @GET("/v3/51099d29-1b25-4fe3-8b56-a3f9a5747546")
    suspend fun getMedicines(): Response<Medicines>

}