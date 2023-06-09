package com.example.shop_app.data

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shop_app.model.Shoe

@Database(entities = [Shoe::class], version = 1, exportSchema = false)
abstract class ShoeDatabase : RoomDatabase() {

    abstract fun shoeDao(): ShoeDao

    companion object {
        @Volatile
        private var INSTANCE: ShoeDatabase? = null

        fun getDatabase(context: Context): ShoeDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ShoeDatabase::class.java,
                    "shoe_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}