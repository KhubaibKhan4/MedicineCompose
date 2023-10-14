package com.codespacepro.medicinecompose.screen

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.NavHostController
import com.codespacepro.medicinecompose.component.MedicinesList
import com.codespacepro.medicinecompose.model.Medicines
import com.codespacepro.medicinecompose.repository.Repository
import com.codespacepro.medicinecompose.ui.theme.MedicineComposeTheme
import com.codespacepro.medicinecompose.viewmodel.MainViewModel
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(navController: NavHostController, name: String?) {
    val currentTime = LocalTime.now()
    val greeting = when (currentTime.hour) {
        in 0..11 -> "Good Morning"
        in 12..15 -> "Good Afternoon"
        else -> "Good Evening"
    }
    val repository = Repository()
    val mainViewModel = MainViewModel(repository)
    val owner: LifecycleOwner = LocalLifecycleOwner.current
    val context: Context = LocalContext.current
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    var data by remember {
        mutableStateOf<Medicines?>(null)
    }
    var isLoading by remember {
        mutableStateOf(true)
    }
    try {
        mainViewModel.getMedicines()
        mainViewModel.myResponse.observe(owner, Observer { response ->
            if (response.isSuccessful) {
                data = response.body()
                Log.d(
                    "" +
                            "", "${response.body()}"
                )
                isLoading = false
            } else {
                isLoading = false
                Log.d("HomeScreen", "${response.code()}")
                Log.d("HomeScreen", "HomeScreen: ${response.code()}")
            }
        })

    } catch (e: Exception) {
        isLoading = false
        Log.d("Exception", "HomeScreen: ${e.printStackTrace()}")
    }
    MedicineComposeTheme {
        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = "$greeting, $name!",
                            )
                        },
                        colors = TopAppBarDefaults.smallTopAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            scrolledContainerColor = Color.DarkGray, titleContentColor = Color.White
                        ),
                        scrollBehavior = scrollBehavior,
                        windowInsets = TopAppBarDefaults.windowInsets
                    )
                },
                content = {
                    Box(
                        modifier = Modifier
                            .padding(top = it.calculateTopPadding())
                            .fillMaxSize()

                    ) {
                        data?.medicines?.let { data ->
                            MedicinesList(medicines = data, navController)
                        }
                    }
                }
            )

        }
    }

}