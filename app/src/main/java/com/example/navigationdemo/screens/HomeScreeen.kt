package com.example.navigationdemo.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.navigationdemo.NavRoutes

@OptIn(ExperimentalMaterial3Api::class) // TopAppBar
@Composable
fun HomeScreen(navController: NavHostController) {
    var userName by rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Registration") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
            )
        },
    ) { innerPadding ->
        Register(
            userName, { userName = it },
            navigateToNext = { navController.navigate(NavRoutes.Welcome.route + "/$userName") },
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun Register(
    username: String,
    onUserNameChange: (String) -> Unit,
    navigateToNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp).fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(value = username, onValueChange = onUserNameChange,
            label = { Text("Enter your name") },
            isError = username.trim().isEmpty(),
            supportingText = { if (username.isEmpty()) Text(text = "Please enter your name") }
        )

        Spacer(modifier = Modifier.size(24.dp))

        Button(
            onClick = {
                onUserNameChange(username.trim())
                navigateToNext()
            },
            enabled = username.trim().isNotEmpty()
        ) {
            Text("Register")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterPreview() {
    val username by remember { mutableStateOf("John Doe") }
    Register(username = username, onUserNameChange = {}, navigateToNext = {})
}