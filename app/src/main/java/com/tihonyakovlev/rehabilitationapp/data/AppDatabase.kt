package com.tihonyakovlev.rehabilitationapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.tihonyakovlev.rehabilitationapp.data.dao.DiseaseDao
import com.tihonyakovlev.rehabilitationapp.data.dao.SectionDao
import com.tihonyakovlev.rehabilitationapp.data.entities.Disease
import com.tihonyakovlev.rehabilitationapp.data.entities.Section
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Section::class, Disease::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun sectionDao(): SectionDao
    abstract fun diseaseDao(): DiseaseDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "rehabilitation-db"
                )
                    .addCallback(DatabaseCallback)
                    .fallbackToDestructiveMigration() // Позволяет уничтожить старую базу данных
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private val DatabaseCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    CoroutineScope(Dispatchers.IO).launch {
                        populateDatabase(database.sectionDao(), database.diseaseDao())
                    }
                }
            }
        }

        suspend fun populateDatabase(sectionDao: SectionDao, diseaseDao: DiseaseDao) {
            sectionDao.insertAll(InitialData.sections)
            diseaseDao.insertAll(InitialData.diseases)
        }
    }
}
