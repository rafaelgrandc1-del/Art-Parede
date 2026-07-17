package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.LibraryBooks
import androidx.compose.material.icons.filled.BusinessCenter
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import androidx.room.Room
import com.example.data.AppDatabase
import com.example.data.ArtParedeRepository
import com.example.ui.*
import com.example.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {

    // Initialize Room Database and Repository lazily
    private val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "art_parede_database"
        )
        .fallbackToDestructiveMigration(dropAllTables = true)
        .build()
    }
    private val repository by lazy { ArtParedeRepository(database.artParedeDao()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                // Instantiating our ViewModel
                val factory = ArtParedeViewModelFactory(repository)
                val viewModel: ArtParedeViewModel = viewModel(factory = factory)

                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentDestination = navBackStackEntry?.destination

                        NavigationBar(
                            modifier = Modifier
                                .windowInsetsPadding(WindowInsets.navigationBars)
                                .testTag("bottom_nav_bar"),
                            containerColor = MaterialTheme.colorScheme.background,
                            tonalElevation = 8.dp
                        ) {
                            // Tab 1: Catalog
                            NavigationBarItem(
                                icon = { Icon(Icons.Default.GridView, contentDescription = "Catálogo") },
                                label = { Text("Catálogo") },
                                selected = currentDestination?.hierarchy?.any { it.route == "catalog_home" || it.route?.startsWith("effect_detail") == true || it.route?.startsWith("simulator") == true } == true,
                                onClick = {
                                    navController.navigate("catalog_home") {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                },
                                modifier = Modifier.testTag("nav_catalog")
                            )

                            // Tab 2: Projects
                            NavigationBarItem(
                                icon = { Icon(Icons.AutoMirrored.Filled.LibraryBooks, contentDescription = "Moodboards") },
                                label = { Text("Moodboards") },
                                selected = currentDestination?.hierarchy?.any { it.route == "projects" } == true,
                                onClick = {
                                    navController.navigate("projects") {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                },
                                modifier = Modifier.testTag("nav_projects")
                            )

                            // Tab 3: Atendimento
                            NavigationBarItem(
                                icon = { Icon(Icons.Default.BusinessCenter, contentDescription = "Atendimento") },
                                label = { Text("Atendimento") },
                                selected = currentDestination?.hierarchy?.any { it.route == "contact" } == true,
                                onClick = {
                                    navController.navigate("contact") {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                },
                                modifier = Modifier.testTag("nav_contact")
                            )
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "catalog_home",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("catalog_home") {
                            CatalogHomeScreen(viewModel = viewModel, navController = navController)
                        }
                        composable(
                            route = "effect_detail/{key}",
                            arguments = listOf(navArgument("key") { type = NavType.StringType })
                        ) { backStackEntry ->
                            val key = backStackEntry.arguments?.getString("key") ?: ""
                            EffectDetailScreen(effectKey = key, viewModel = viewModel, navController = navController)
                        }
                        composable(
                            route = "simulator/{key}",
                            arguments = listOf(navArgument("key") { type = NavType.StringType })
                        ) { backStackEntry ->
                            val key = backStackEntry.arguments?.getString("key") ?: ""
                            SimulatorScreen(effectKey = key, viewModel = viewModel, navController = navController)
                        }
                        composable("projects") {
                            ProjectsScreen(viewModel = viewModel, navController = navController)
                        }
                        composable("contact") {
                            ContactScreen()
                        }
                    }
                }
            }
        }
    }
}
