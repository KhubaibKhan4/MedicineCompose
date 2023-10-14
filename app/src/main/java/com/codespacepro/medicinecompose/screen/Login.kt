package com.codespacepro.medicinecompose.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.codespacepro.medicinecompose.R
import com.codespacepro.medicinecompose.navigation.Screen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    var isPasswordValid by remember { mutableStateOf(true) }
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Add your logo or other UI elements here
            Spacer(modifier = Modifier.height(100.dp))

            Text(
                text = "Login",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                ),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                        Icon(
                            painter = painterResource(id = if (passwordVisibility) R.drawable.visibility else R.drawable.visibility_off),
                            contentDescription = ""
                        )
                    }
                },
                isError = !isPasswordValid,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            if (!isPasswordValid) {
                Text(
                    text = "Password should be at least 8 characters long",
                    color = Color.Red,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            Text(
                text = "Forgot Password?",
                color = Color.Blue,
                modifier = Modifier
                    .padding(top = 4.dp, bottom = 8.dp)
                    .align(Alignment.End)
            )

            Button(
                onClick = {
                    if (validatePassword(password) and password.isNotBlank() and username.isNotBlank()) {
                        navController.navigate(Screen.Home.sendData(username))
                    } else if (password.isBlank()) {
                        Toast.makeText(context, "Password is Blank...", Toast.LENGTH_SHORT)
                            .show()
                    } else if (!validatePassword(password)) {
                        Toast.makeText(context, "Password is not Valid...", Toast.LENGTH_SHORT)
                            .show()
                    }else if (username.isBlank()) {
                        Toast.makeText(context, "Username is Blank...", Toast.LENGTH_SHORT)
                            .show()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Login")
            }

            Text(
                text = "Create New Account",
                color = Color.Black,
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 8.dp)
                    .align(Alignment.CenterHorizontally)
                    .clickable {
                            navController.navigate(Screen.Register.route)
                    }
            )

        }
    }
}

fun validatePassword(password: String): Boolean {
    return password.length >= 8
}
