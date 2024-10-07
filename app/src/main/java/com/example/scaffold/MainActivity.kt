package com.example.scaffold

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.scaffold.ui.theme.ScaffoldTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScaffoldTheme {
                ScaffoldApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "Home"
    ) {
        composable(route = "Home") {
            MainScreen(navController)
        }
        composable(route = "Info") {
            InfoScreen(navController)
        }
        composable(route = "Settings") {
            SettingsScreen(navController)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(title: String, navController: NavController) {
    var expanded by remember { mutableStateOf(false) }

    CenterAlignedTopAppBar(
        title = { Text(text = title) },
        actions = {
            IconButton(onClick = { expanded = !expanded }) {
                Icon(Icons.Default.MoreVert, contentDescription = "More Options")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Info") },
                    onClick = {
                        expanded = false
                        navController.navigate("Info")
                    }
                )
                DropdownMenuItem(
                    text = { Text("Settings") },
                    onClick = {
                        expanded = false
                        navController.navigate("Settings")
                    }
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenTopBar(title: String, navController: NavController) {
    CenterAlignedTopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
        }
    )
}

@Composable
fun MainScreen(navController: NavController) {
    Scaffold(
        topBar = { MainTopBar(title = "My App", navController = navController) },
        content = { paddingValues ->
            Text(
                text = "Content for Home screen",
                modifier = Modifier.padding(paddingValues).padding(16.dp)
            )
        }
    )
}

@Composable
fun InfoScreen(navController: NavController) {
    Scaffold(
        topBar = { ScreenTopBar(title = "Info", navController = navController) },
        content = { paddingValues ->
            Text(
                text = "Content for Info screen",
                modifier = Modifier.padding(paddingValues).padding(16.dp)
            )
        }
    )
}

@Composable
fun SettingsScreen(navController: NavController) {
    Scaffold(
        topBar = { ScreenTopBar(title = "Settings", navController = navController) },
        content = { paddingValues ->
            Text(
                text = "Content for Settings screen",
                modifier = Modifier.padding(paddingValues).padding(16.dp)
            )
        }
    )
}


@Preview(showBackground = true)
@Composable
fun ScaffoldPreview() {
    ScaffoldTheme {
        ScaffoldApp()
    }
}