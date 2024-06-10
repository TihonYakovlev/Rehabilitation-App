package com.tihonyakovlev.rehabilitationapp.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tihonyakovlev.rehabilitationapp.data.entities.Section
import com.tihonyakovlev.rehabilitationapp.domain.repository.DiseaseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SectionViewModel(private val repository: DiseaseRepository) : ViewModel(){
private val _sections = MutableStateFlow<List<Section>>(emptyList())
val sections: StateFlow<List<Section>> get() = _sections
    init {
        viewModelScope.launch {
            repository.getAllSections().collect {
                _sections.value = it
                Log.d("DiseaseViewModel", "Sections loaded: $it")
            }
        }
    }
}