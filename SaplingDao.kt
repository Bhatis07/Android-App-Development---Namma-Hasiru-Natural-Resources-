package com.internshipproject

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SaplingDao {
    @Query("SELECT * FROM saplings")
    fun getAllSaplings(): LiveData<List<Sapling>>

    @Insert
    suspend fun insert(sapling: Sapling)

    @Update
    suspend fun update(sapling: Sapling)

    @Delete
    suspend fun delete(sapling: Sapling)
}
