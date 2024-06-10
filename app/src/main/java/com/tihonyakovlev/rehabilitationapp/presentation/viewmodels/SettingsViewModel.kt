package com.tihonyakovlev.rehabilitationapp.presentation.viewmodels


import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.tihonyakovlev.rehabilitationapp.presentation.darkMode
import com.tihonyakovlev.rehabilitationapp.presentation.fontSize
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SettingsViewModel : ViewModel() {
    private val _selectedTheme = MutableStateFlow("Light")
    val selectedTheme: StateFlow<String> = _selectedTheme

    private val _fontSize = MutableStateFlow(16f)

    fun setTheme(theme: String) {
        _selectedTheme.value = theme
        darkMode = !darkMode
    }



    fun setFontSize(newSize: Float) {
        Log.d("SettingsViewModel", "Setting new font size: $newSize")
        _fontSize.value = newSize
        fontSize = newSize
    }

}