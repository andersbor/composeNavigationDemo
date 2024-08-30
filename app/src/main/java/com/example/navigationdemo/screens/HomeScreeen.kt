package com.example.navigationdemo.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.navigationdemo.NavRoutes

@Composable
fun Home(navController: NavHostController) {
    var userName by rememberSaveable { mutableStateOf("") }
    val onUserNameChange = { text: String -> userName = text }
    var errorMessage by remember { mutableStateOf("") }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomTextField(
                title = "Enter your name",
                textState = userName,
                onTextChange = onUserNameChange
            )

            Text(
                text = if (errorMessage != "") errorMessage else "",
                style = TextStyle(color = MaterialTheme.colorScheme.error, fontSize = 20.sp)
            )

            Spacer(modifier = Modifier.size(30.dp))

            Button(onClick = {
                userName = userName.trim()
                if (userName.isEmpty()) {
                    errorMessage = "Please enter your name"
                    return@Button
                } else {
                    errorMessage = ""
                }
                navController.navigate(NavRoutes.Welcome.route + "/$userName")
            }) {
                Text("Register")
            }
        }
    }
}


@Composable
// error is field is empty
fun CustomTextField(
    title: String,
    textState: String,
    onTextChange: (String) -> Unit
) {
    OutlinedTextField(
        value = textState,
        onValueChange = { onTextChange(it) },
        singleLine = true,
        label = { Text(text = title) },
        modifier = Modifier.padding(10.dp),
        textStyle = TextStyle(fontWeight = FontWeight.Bold, fontSize = 30.sp),
        isError = textState.isEmpty()
        //supportingText = "supporting text"
    )
}

@Preview
@Composable
fun CustomTextFieldPreview() {
    var name by remember { mutableStateOf("") }
    CustomTextField(title = "Enter your name", textState = name,
        onTextChange = { name = it })
}