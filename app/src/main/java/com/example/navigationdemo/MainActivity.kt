package com.example.navigationdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigationdemo.screens.HomeScreen
import com.example.navigationdemo.screens.ProfileScreen
import com.example.navigationdemo.screens.WelcomeScreen
import com.example.navigationdemo.ui.theme.NavigationDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationDemoTheme {
                MainScreen()
            }
        }
    }
}

// library dependencies
// at the bottom of the dependencies in the build.gradle.kts file (app)
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoutes.Home.route
    ) {
        composable(NavRoutes.Home.route) {
            // TODO don't pass navController to Home
            HomeScreen(navController = navController)
        }
        composable(NavRoutes.Welcome.route + "/{userName}")
        { backstackEntry ->
            val username = backstackEntry.arguments?.getString("userName")
            val usernameNotNull = username ?: "Unknown"
            WelcomeScreen(
                username = usernameNotNull,
                // navController should not be passed down to the composable
                onNavigateToProfile = { navController.navigate(NavRoutes.Profile.route) },
                onNavigateBack = { navController.popBackStack() })
        }
        composable(
            NavRoutes.Profile.route
            // animations https://developer.android.com/develop/ui/compose/animation/composables-modifiers#animatedcontent
        ) {
            ProfileScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NavigationDemoTheme {
        MainScreen()
    }
}