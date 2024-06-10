package com.tihonyakovlev.rehabilitationapp.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tihonyakovlev.rehabilitationapp.data.entities.Section
import com.tihonyakovlev.rehabilitationapp.presentation.viewmodels.DiseaseViewModel
import com.tihonyakovlev.rehabilitationapp.presentation.viewmodels.SectionViewModel
import com.tihonyakovlev.rehabilitationapp.ui.theme.RehabilitationAppTheme
import org.koin.androidx.compose.getViewModel


@Composable
fun MainScreen(navController: NavController) {
    val viewModel: SectionViewModel = getViewModel()
    val sections by viewModel.sections.collectAsState()

    RehabilitationAppTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Пособие по реабилитации", color = MaterialTheme.colors.onPrimary) },
                    actions = {
                        IconButton(onClick = { navController.navigate("settings") }) {
                            Icon(Icons.Default.Settings, contentDescription = "Настройки")
                        }
                    },
                    backgroundColor = MaterialTheme.colors.primary
                )
            },
            content = {
                LazyColumn {
                    items(sections) { section ->
                        SectionItem(section, onClick = {
                            navController.navigate("section/${section.id}")
                        })
                    }
                }
            }
        )
    }
}

@Composable
fun SectionItem(section: Section, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Text(
            section.name,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.onSurface
        )
    }
}