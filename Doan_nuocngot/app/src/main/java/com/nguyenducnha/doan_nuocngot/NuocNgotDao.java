package com.nguyenducnha.doan_nuocngot;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NuocNgotDao {

    @Query("select * from nuocngot")
    LiveData<List<NuocNgot>> getAll();

    @Insert
    void insert(NuocNgot nn);

    @Update
    void update(NuocNgot nn);

    @Delete
    void delete(NuocNgot nn);

}
