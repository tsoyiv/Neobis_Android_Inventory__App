package com.example.shop_app.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ShoeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addShoe(shoe: Shoe)

    @Query("SELECT * FROM shoe_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Shoe>>

}