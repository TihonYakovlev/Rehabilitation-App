package com.tihonyakovlev.rehabilitationapp.presentation.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tihonyakovlev.rehabilitationapp.data.entities.Disease
import com.tihonyakovlev.rehabilitationapp.presentation.viewmodels.DiseaseViewModel
import com.tihonyakovlev.rehabilitationapp.presentation.viewmodels.SectionViewModel
import com.tihonyakovlev.rehabilitationapp.ui.theme.RehabilitationAppTheme
import org.koin.androidx.compose.getViewModel

@Composable
fun SectionScreen(sectionId: Int, navController: NavController) {
    val viewModel: DiseaseViewModel = getViewModel()

    LaunchedEffect(sectionId) {
        viewModel.loadDiseasesBySection(sectionId)
    }
    val sectionDiseases by viewModel.sectionDiseases.collectAsState()
    val section = getSectionById(sectionId)

    RehabilitationAppTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(section, color = MaterialTheme.colors.onPrimary) },
                    backgroundColor = MaterialTheme.colors.primary
                )
            },
            content = {
                LazyColumn {
                    items(sectionDiseases) { disease ->
                        DiseaseItem(disease, onClick = {
                            navController.navigate("disease/${disease.id}")
                            Log.d("Disease", "Navigating to disease with id ${disease.id}")
                        })
                    }
                }
            }
        )
    }
}

@Composable
fun DiseaseItem(disease: Disease, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(disease.name, style = MaterialTheme.typography.h6, color = MaterialTheme.colors.onSurface)
        }
    }
}

fun getSectionById(sectionId: Int): String {
    return when (sectionId) {
        1 -> "Нервная система"
        2 -> "Опорно-двигательный аппарат"
        3 -> "Желудочно-кишечный тракт (ЖКТ)"
        4 -> "Кардиология"
        else -> "Заболевание"
    }
}
