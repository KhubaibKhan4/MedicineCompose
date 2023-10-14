package com.codespacepro.medicinecompose.model

data class Medicine(
    val additional_information: AdditionalInformation,
    val dose: String,
    val medicine_id: Int,
    val name: String,
    val symptoms: List<String>
)