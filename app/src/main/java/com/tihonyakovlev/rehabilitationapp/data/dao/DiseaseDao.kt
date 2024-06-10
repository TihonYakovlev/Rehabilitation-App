package com.tihonyakovlev.rehabilitationapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tihonyakovlev.rehabilitationapp.data.entities.Disease
import kotlinx.coroutines.flow.Flow

@Dao
interface DiseaseDao {
    @Query("SELECT * FROM diseases")
    fun getAllDiseases(): Flow<List<Disease>>

    @Query("SELECT * FROM diseases WHERE sectionId = :sectionId")
    fun getDiseasesBySection(sectionId: Int): Flow<List<Disease>>

    @Query("SELECT * FROM diseases WHERE id = :diseaseId")
    fun getDiseaseById(diseaseId: Int): Flow<Disease?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(diseases: List<Disease>)
}
