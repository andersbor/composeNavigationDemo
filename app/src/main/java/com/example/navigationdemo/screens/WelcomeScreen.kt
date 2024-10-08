package com.example.navigationdemo.screens

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
fun Welcome(username: String?, navigateToProfile: () -> Unit, navigateBack: () -> Unit) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Welcome $username", style = MaterialTheme.typography.headlineLarge)

            Spacer(modifier = Modifier.size(30.dp))

            Button(onClick = { navigateToProfile() }) {
                Text("Set up your profile")
            }

            Spacer(modifier = Modifier.size(30.dp))

            Button(onClick = { navigateBack() }) {
                Text("Go back")
            }
        }
    }
}