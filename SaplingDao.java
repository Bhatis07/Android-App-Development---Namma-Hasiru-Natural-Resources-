package com.internshipproject;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SaplingDao {
    @Query("SELECT * FROM saplings")
    LiveData<List<Sapling>> getAllSaplings();

    @Insert
    void insert(Sapling sapling);

    @Update
    void update(Sapling sapling);

    @Delete
    void delete(Sapling sapling);
}
