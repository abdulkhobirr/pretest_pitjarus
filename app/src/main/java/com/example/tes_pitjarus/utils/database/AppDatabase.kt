package com.example.tes_pitjarus.utils.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tes_pitjarus.data.stores.local.dao.StoresDao
import com.example.tes_pitjarus.data.stores.local.entity.Stores

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = [Stores::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun storesDao(): StoresDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(
            context: Context
        ): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "tes_app_db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
