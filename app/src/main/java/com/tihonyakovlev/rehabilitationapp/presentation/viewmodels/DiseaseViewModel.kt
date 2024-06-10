package com.tihonyakovlev.rehabilitationapp.presentation.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tihonyakovlev.rehabilitationapp.data.Content
import com.tihonyakovlev.rehabilitationapp.data.entities.Disease
import com.tihonyakovlev.rehabilitationapp.data.entities.Section
import com.tihonyakovlev.rehabilitationapp.domain.JsonUtils
import com.tihonyakovlev.rehabilitationapp.domain.repository.DiseaseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DiseaseViewModel(private val repository: DiseaseRepository, private val context: Context) : ViewModel() {
    private val _diseases = MutableStateFlow<List<Disease>>(emptyList())
    val diseases: StateFlow<List<Disease>> get() = _diseases

    private val _disease = MutableStateFlow<Disease?>(null)
    val disease: StateFlow<Disease?> get() = _disease

    private val _sectionDiseases = MutableStateFlow<List<Disease>>(emptyList())
    val sectionDiseases: StateFlow<List<Disease>> get() = _sectionDiseases

    private val _descriptionContent = MutableStateFlow<Content?>(null)
    val descriptionContent: StateFlow<Content?> get() = _descriptionContent

    private val _exercisesContent = MutableStateFlow<Content?>(null)
    val exercisesContent: StateFlow<Content?> get() = _exercisesContent

    init {
        viewModelScope.launch {
            loadAllDiseases()
        }
    }

    fun loadDiseaseById(diseaseId: Int) {
        viewModelScope.launch {
            repository.getDiseaseById(diseaseId).collect {
                _disease.value = it
                Log.d("DiseaseViewModel", "Disease loaded: $it")
            }
        }
    }
    private suspend fun loadAllDiseases() {
        repository.getAllDiseases().collect {
            _diseases.value = it
            Log.d("DiseaseViewModel", "All Diseases loaded: $it")
        }
    }

    fun loadDiseasesBySection(sectionId: Int) {
        viewModelScope.launch {
            repository.getDiseasesBySection(sectionId).collect {
                _sectionDiseases.value = it
                Log.d("DiseaseViewModel", "Diseases for section $sectionId: $it")
            }
        }
    }

    fun loadDiseaseContent(descriptionFile: String, exercisesFile: String) {
        viewModelScope.launch {
            try {
                Log.d("DiseaseViewModel", "Loading description from $descriptionFile")
                val descriptionJson = JsonUtils.loadJSONFromAsset(context, descriptionFile)
                _descriptionContent.value = JsonUtils.parseContent(descriptionJson)
                Log.d("DiseaseViewModel", "Description content loaded: ${_descriptionContent.value}")

                Log.d("DiseaseViewModel", "Loading exercises from $exercisesFile")
                val exercisesJson = JsonUtils.loadJSONFromAsset(context, exercisesFile)
                _exercisesContent.value = JsonUtils.parseContent(exercisesJson)
                Log.d("DiseaseViewModel", "Exercises content loaded: ${_exercisesContent.value}")
            } catch (e: Exception) {
                Log.e("DiseaseViewModel", "Error loading disease content", e)
            }
        }
    }

}




