package com.mf.id.solidapp.Util.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.mf.id.solidapp.Util.ResponseDataModel;

import java.util.Iterator;
import java.util.List;

@Dao
public interface DataDao {
    @Query("SELECT * FROM ResponseDatas")
    List<ResponseDataModel> getAllData();

    @Query("SELECT DISTINCT vehicle_type_name FROM ResponseDatas")
    List<String> getJenis();

    @Query("SELECT DISTINCT vehicle_age FROM ResponseDatas")
    List<String> getUsia();

    @Query("SELECT * FROM ResponseDatas WHERE vehicle_age LIKE :age AND vehicle_type_name LIKE :type")
    List<ResponseDataModel> getFilteredData(String age, String type);

    @Query("DELETE FROM ResponseDatas")
    void deleteAllData();

    @Insert()
    void insertData(ResponseDataModel... data);

    @Delete
    void deleteData(ResponseDataModel data);
}
