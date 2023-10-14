package com.codespacepro.medicinecompose.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.codespacepro.medicinecompose.R
import com.codespacepro.medicinecompose.model.Medicine
import com.codespacepro.medicinecompose.navigation.Screen

@Composable
fun MedicinesList(medicines: List<Medicine>, navController: NavHostController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        items(medicines) { medicine ->
            MedicinesItem(medicine = medicine, navController)
        }
    }
}

@Composable
fun MedicinesItem(medicine: Medicine, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate(
                    Screen.Detail.passData(
                        medicine.name,
                        medicine.dose,
                        medicine.symptoms.toString(),
                        medicine.additional_information.manufacturer,
                        medicine.additional_information.usage,
                        medicine.additional_information.warnings
                    )
                )
            }
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            // You can replace the icon with the appropriate one from your resources
            Icon(
                painter = painterResource(id = R.drawable.local_hospita),
                contentDescription = null,
                modifier = Modifier.size(48.dp)
            )


            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = "Medicine Name: ${medicine.name}", fontWeight = FontWeight.Bold)
                Text(text = "Dose: ${medicine.dose}")
                Text(text = "Manufacturer: ${medicine.additional_information.manufacturer}")
            }
            //Spacer(modifier = Modifier.width(16.dp))
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
            )
        }

    }
}