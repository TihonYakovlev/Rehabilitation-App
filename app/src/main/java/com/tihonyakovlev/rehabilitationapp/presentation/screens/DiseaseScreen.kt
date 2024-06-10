package com.tihonyakovlev.rehabilitationapp.presentation.screens

import android.graphics.BitmapFactory
import android.media.Image
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tihonyakovlev.rehabilitationapp.data.Content
import com.tihonyakovlev.rehabilitationapp.presentation.viewmodels.DiseaseViewModel
import com.tihonyakovlev.rehabilitationapp.ui.theme.RehabilitationAppTheme
import org.koin.androidx.compose.getViewModel


@Composable
fun DiseaseScreen(diseaseId: Int) {
    Log.d("Disease Screen", "disease id on it screen: $diseaseId")

    val viewModel: DiseaseViewModel = getViewModel()
    val disease by viewModel.disease.collectAsState()
    val descriptionContent by viewModel.descriptionContent.collectAsState()
    val exercisesContent by viewModel.exercisesContent.collectAsState()

    LaunchedEffect(diseaseId) {
        viewModel.loadDiseaseById(diseaseId)
    }
    LaunchedEffect(disease) {
        disease?.let {
            Log.d("Disease Screen", "Loading content for disease: ${it.name}")
            viewModel.loadDiseaseContent(it.descriptionFile, it.exercisesFile)
        }
    }

    RehabilitationAppTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        descriptionContent?.let {
                            val name = it.title
                            Text(name)
                        }
                    },
                    backgroundColor = MaterialTheme.colors.primary
                )
            },
            content = {
                if (descriptionContent == null && exercisesContent == null) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = MaterialTheme.colors.primary)
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        descriptionContent?.let {
                            items(it.content.size) { index ->
                                val item = it.content[index]
                                when (item.type) {
                                    "text" -> Text(
                                        text = item.value,
                                        style = MaterialTheme.typography.body1,
                                        modifier = Modifier.padding(vertical = 8.dp)
                                    )
                                    "image" -> ImageFromAssets(assetName = item.value)
                                }
                            }
                        }
                        item {
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                        exercisesContent?.let {
                            items(it.content.size) { index ->
                                val item = it.content[index]
                                when (item.type) {
                                    "text" -> Text(
                                        text = item.value,
                                        style = MaterialTheme.typography.body1,
                                        modifier = Modifier.padding(vertical = 8.dp)
                                    )
                                    "image" -> ImageFromAssets(assetName = item.value)
                                }
                            }
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun ImageFromAssets(assetName: String) {
    val context = LocalContext.current
    val assetManager = context.assets
    val bitmap = remember(assetName) {
        val inputStream = assetManager.open(assetName)
        BitmapFactory.decodeStream(inputStream)
    }
    Image(
        bitmap = bitmap.asImageBitmap(),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    )
}