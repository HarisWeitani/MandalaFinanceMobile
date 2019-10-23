package com.mf.id.solidapp.Util.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.mf.id.solidapp.Util.ResponseDataModel;

import java.util.List;

@Dao
public interface DataDao {
    @Query("SELECT * FROM ResponseDatas")
    List<ResponseDataModel> getAllData();

    @Query("SELECT DISTINCT vehicleTypeName FROM ResponseDatas")
    List<String> getJenis();

    @Query("SELECT DISTINCT vehicleAge FROM ResponseDatas")
    List<String> getUsia();

    @Query("SELECT * FROM ResponseDatas WHERE vehicleAge = :age AND vehicleTypeName =:type")
    List<ResponseDataModel> getFilteredData(String age, String type);

    @Query("DELETE FROM ResponseDatas")
    void deleteAllData();

    @Insert
    void insertData(ResponseDataModel ... datas);

    @Delete
    void deleteData(ResponseDataModel data);
}
