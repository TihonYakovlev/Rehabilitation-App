package com.tihonyakovlev.rehabilitationapp.presentation.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tihonyakovlev.rehabilitationapp.presentation.fontSize
import com.tihonyakovlev.rehabilitationapp.presentation.viewmodels.SettingsViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun SettingsScreen(navController: NavController) {
    val appViewModel: SettingsViewModel = getViewModel()
    val selectedTheme by appViewModel.selectedTheme.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Настройки", fontSize = fontSize.sp) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Назад")
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text("Выбор темы", fontSize = fontSize.sp)
                Spacer(modifier = Modifier.height(8.dp))
                ThemeSelector(selectedTheme) { newTheme ->
                    appViewModel.setTheme(newTheme)
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text("Размер шрифта", fontSize = fontSize.sp)
                Spacer(modifier = Modifier.height(8.dp))
                FontSizeSelector(fontSize)
            }
        }
    )
}

@Composable
fun FontSizeSelector(currentFontSize: Float) {
    val appViewModel: SettingsViewModel = getViewModel()
    val fontSizes = listOf(12f, 14f, 16f, 18f, 20f, 22f, 24f)
    Column {
        fontSizes.forEach { size ->
            Button(
                onClick = { appViewModel.setFontSize(size) },
                modifier = Modifier.padding(4.dp)
            ) {
                Text(text = "$size sp")
            }
        }
        Text("Текущий размер: ${currentFontSize.sp}", modifier = Modifier.padding(top = 8.dp))
    }
}




@Composable
fun ThemeSelector(selectedTheme: String, onThemeSelected: (String) -> Unit) {
    val themes = listOf("Light", "Dark")
    Column {
        themes.forEach { theme ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .clickable { onThemeSelected(theme) },
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = theme == selectedTheme,
                    onClick = { onThemeSelected(theme) }
                )
                Text(theme, modifier = Modifier.padding(start = 8.dp))
            }
        }
    }
}


