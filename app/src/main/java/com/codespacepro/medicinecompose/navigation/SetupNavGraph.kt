package com.codespacepro.medicinecompose.navigation

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.codespacepro.medicinecompose.screen.DetailScreen
import com.codespacepro.medicinecompose.screen.HomeScreen
import com.codespacepro.medicinecompose.screen.LoginScreen
import com.codespacepro.medicinecompose.screen.RegisterScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(Screen.Home.route,
            arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                }

            )
        ) {
            val name = it.arguments?.getString("name")
            Log.d("HomeScreen", "Name: $name")
            HomeScreen(navController, name)
        }
        composable(Screen.Login.route) {
            LoginScreen(navController)
        }
        composable(Screen.Register.route) {
            RegisterScreen(navController)
        }
        composable(route = Screen.Detail.route, arguments = listOf(
            navArgument("name") {
                type = NavType.StringType
            },
            navArgument("dose") {
                type = NavType.StringType
            },
            navArgument("symptoms") {
                type = NavType.StringType
            },
            navArgument("manufacturer") {
                type = NavType.StringType
            },
            navArgument("usage") {
                type = NavType.StringType
            },
            navArgument("warnings") {
                type = NavType.StringType
            }
        )) {
            val name = it.arguments?.getString("name")
            val dose = it.arguments?.getString("dose")
            val symptoms = it.arguments?.getString("symptoms")
            val manufacturer = it.arguments?.getString("manufacturer")
            val usage = it.arguments?.getString("usage")
            val warnings = it.arguments?.getString("warnings")
            Log.d(
                "DetailScreen",
                "SetupNavGraph: $name $dose $symptoms $manufacturer $usage $warnings"
            )
            DetailScreen(navController, name, dose, symptoms, manufacturer, usage, warnings)
        }

    }
}