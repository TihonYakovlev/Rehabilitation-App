package com.tihonyakovlev.rehabilitationapp.domain.repository

import com.tihonyakovlev.rehabilitationapp.data.entities.Disease
import com.tihonyakovlev.rehabilitationapp.data.entities.Section
import kotlinx.coroutines.flow.Flow

interface DiseaseRepository {
    fun getAllSections(): Flow<List<Section>>
    fun getDiseasesBySection(sectionId: Int): Flow<List<Disease>>
    fun getDiseaseById(diseaseId: Int): Flow<Disease?>
    fun getAllDiseases(): Flow<List<Disease>>
    suspend fun insertAllSections(sections: List<Section>)
    suspend fun insertAllDiseases(diseases: List<Disease>)
}
