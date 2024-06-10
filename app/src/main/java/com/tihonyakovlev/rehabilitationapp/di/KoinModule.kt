package com.tihonyakovlev.rehabilitationapp.di

import androidx.room.Room
import com.tihonyakovlev.rehabilitationapp.data.AppDatabase
import com.tihonyakovlev.rehabilitationapp.data.repository.DiseaseRepositoryImplementation
import com.tihonyakovlev.rehabilitationapp.domain.repository.DiseaseRepository
import com.tihonyakovlev.rehabilitationapp.presentation.viewmodels.DiseaseViewModel
import com.tihonyakovlev.rehabilitationapp.presentation.viewmodels.SectionViewModel
import com.tihonyakovlev.rehabilitationapp.presentation.viewmodels.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { AppDatabase.getDatabase(get()) }
    single { get<AppDatabase>().sectionDao() }
    single { get<AppDatabase>().diseaseDao() }
    single<DiseaseRepository> { DiseaseRepositoryImplementation(get(), get()) }
    viewModel { DiseaseViewModel(get(), get()) }
    viewModel { SectionViewModel(get()) }
    viewModel { SettingsViewModel() }
}



