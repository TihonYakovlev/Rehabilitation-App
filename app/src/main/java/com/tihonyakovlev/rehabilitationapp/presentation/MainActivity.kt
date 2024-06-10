package com.tihonyakovlev.rehabilitationapp.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tihonyakovlev.rehabilitationapp.presentation.screens.DiseaseScreen
import com.tihonyakovlev.rehabilitationapp.presentation.screens.MainScreen
import com.tihonyakovlev.rehabilitationapp.presentation.screens.SectionScreen
import com.tihonyakovlev.rehabilitationapp.presentation.screens.SettingsScreen
import com.tihonyakovlev.rehabilitationapp.presentation.viewmodels.SettingsViewModel
import com.tihonyakovlev.rehabilitationapp.ui.theme.RehabilitationAppTheme
import org.koin.androidx.compose.getViewModel

var darkMode by mutableStateOf(false)
var fontSize by mutableStateOf(12f)


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RehabilitationAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    RehabilitationApp()
                }
            }
        }
    }
}

@Composable
fun RehabilitationApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "main") {
        composable("main") { MainScreen(navController) }
        composable("section/{sectionId}") { backStackEntry ->
            val sectionId = backStackEntry.arguments?.getString("sectionId")?.toInt()
            if (sectionId != null) {
                SectionScreen(sectionId, navController)
            }
        }
        composable("disease/{diseaseId}") { backStackEntry ->
            val diseaseId = backStackEntry.arguments?.getString("diseaseId")?.toInt()
            Log.d("NavHost", "Navigating to disease screen with ID: $diseaseId")
            if (diseaseId != null) {
                DiseaseScreen(diseaseId)
            }
        }
        composable("settings") { SettingsScreen(navController) }
    }
}