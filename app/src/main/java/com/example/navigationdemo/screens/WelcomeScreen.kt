package com.example.navigationdemo.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WelcomeScreen(username: String, onNavigateToProfile: () -> Unit, onNavigateBack: () -> Unit) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Welcome $username", style = MaterialTheme.typography.headlineLarge)

            Spacer(modifier = Modifier.size(30.dp))

            Button(onClick = { onNavigateToProfile() }) {
                Text("Set up your profile")
            }

            Spacer(modifier = Modifier.size(30.dp))

            Button(onClick = { onNavigateBack() }) {
                Text("Go back")
            }
        }
    }
}