package com.codespacepro.medicinecompose.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DataUsage
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.MedicalInformation
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material.icons.filled.PrecisionManufacturing
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.codespacepro.medicinecompose.component.DetailRow
import com.codespacepro.medicinecompose.component.removeSquareBrackets

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navController: NavHostController,
    name: String?,
    dose: String?,
    symptoms: String?,
    manufacturer: String?,
    usage: String?,
    warnings: String?
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "$name") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = "Back",
                            modifier = Modifier.clickable {
                                navController.popBackStack()
                            }
                        )
                    }
                }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = it.calculateTopPadding(),
                        bottom = it.calculateBottomPadding(),
                        start = 16.dp,
                        end = 16.dp
                    )
            ) {
                Card(
                    shape = MaterialTheme.shapes.medium,
                    elevation = CardDefaults.cardElevation(6.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        DetailRow("Name", name, Icons.Default.Medication)
                        DetailRow("Dose", dose, Icons.Default.MedicalInformation)
                        DetailRow("Symptoms",
                            symptoms?.let { it1 -> removeSquareBrackets(it1) }, Icons.Default.Psychology)
                        DetailRow(
                            "Manufacturer",
                            manufacturer,
                            Icons.Default.PrecisionManufacturing
                        )
                        DetailRow("Usage", usage, Icons.Default.DataUsage)
                        DetailRow("Warnings", warnings, Icons.Default.Warning)
                    }
                }
            }
        }
    )
}