package com.codespacepro.medicinecompose.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home/{name}") {
        fun sendData(name: String): String {
            return "home/$name"
        }
    }

    object Login : Screen("login")
    object Register : Screen("register")
    object Detail : Screen("detail/{name}/{dose}/{symptoms}/{manufacturer}/{usage}/{warnings}") {
        fun passData(
            name: String,
            dose: String,
            symptoms: String,
            manufacturer: String,
            usage: String,
            warnings: String
        ): String {
            return "detail/$name/$dose/$symptoms/$manufacturer/$usage/$warnings"
        }
    }
}
