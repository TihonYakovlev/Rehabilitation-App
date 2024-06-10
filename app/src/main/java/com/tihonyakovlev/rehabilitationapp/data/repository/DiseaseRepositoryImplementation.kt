package com.tihonyakovlev.rehabilitationapp.data.repository

import com.tihonyakovlev.rehabilitationapp.data.dao.DiseaseDao
import com.tihonyakovlev.rehabilitationapp.data.dao.SectionDao
import com.tihonyakovlev.rehabilitationapp.data.entities.Disease
import com.tihonyakovlev.rehabilitationapp.data.entities.Section
import com.tihonyakovlev.rehabilitationapp.domain.repository.DiseaseRepository
import kotlinx.coroutines.flow.Flow

class DiseaseRepositoryImplementation(
    private val diseaseDao: DiseaseDao,
    private val sectionDao: SectionDao
) : DiseaseRepository {
    override fun getAllSections(): Flow<List<Section>> {
        return sectionDao.getAllSections()
    }

    override fun getDiseasesBySection(sectionId: Int): Flow<List<Disease>> {
        return diseaseDao.getDiseasesBySection(sectionId)
    }

    override fun getDiseaseById(diseaseId: Int): Flow<Disease?> {
        return diseaseDao.getDiseaseById(diseaseId)
    }

    override fun getAllDiseases(): Flow<List<Disease>> {
        return diseaseDao.getAllDiseases()
    }

    override suspend fun insertAllSections(sections: List<Section>) {
        return sectionDao.insertAll(sections)
    }

    override suspend fun insertAllDiseases(diseases: List<Disease>) {
        return diseaseDao.insertAll(diseases)
    }
}
