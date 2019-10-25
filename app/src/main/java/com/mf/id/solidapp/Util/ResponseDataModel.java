package com.mf.id.solidapp.Util;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "ResponseDatas")
public class ResponseDataModel {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "calculation_id")
    private String calculation_id;
    @ColumnInfo(name = "vehicle_type_name")
    private String vehicle_type_name;
    @ColumnInfo(name = "vehicle_age")
    private String vehicle_age;
    @ColumnInfo(name = "interest_id")
    private String interest_id;
    @ColumnInfo(name = "is_deleted")
    private String is_deleted;
    @ColumnInfo(name = "created_date")
    private String created_date;
    @ColumnInfo(name = "updated_date")
    private String updated_date;
    @ColumnInfo(name = "created_by")
    private String created_by;
    @ColumnInfo(name = "updated_by")
    private String updated_by;
    @ColumnInfo(name = "dt_interest_id")
    private String dt_interest_id;
    @ColumnInfo(name = "dt_interest_name")
    private String dt_interest_name;
    @ColumnInfo(name = "dt_interest_value")
    private String dt_interest_value;

    public ResponseDataModel(
            @NonNull String calculation_id,
            String vehicle_type_name,
            String vehicle_age,
            String interest_id,
            String is_deleted,
            String created_date,
            String updated_date,
            String created_by,
            String updated_by,
            String dt_interest_id,
            String dt_interest_name,
            String dt_interest_value) {
        this.calculation_id = calculation_id;
        this.vehicle_type_name = vehicle_type_name;
        this.vehicle_age = vehicle_age;
        this.interest_id = interest_id;
        this.is_deleted = is_deleted;
        this.created_date = created_date;
        this.updated_date = updated_date;
        this.created_by = created_by;
        this.updated_by = updated_by;
        this.dt_interest_id = dt_interest_id;
        this.dt_interest_name = dt_interest_name;
        this.dt_interest_value = dt_interest_value;
    }

    @Ignore
    public ResponseDataModel(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCalculation_id() {
        return calculation_id;
    }

    public void setCalculation_id(String calculation_id) {
        this.calculation_id = calculation_id;
    }

    public String getVehicle_type_name() {
        return vehicle_type_name;
    }

    public void setVehicle_type_name(String vehicle_type_name) {
        this.vehicle_type_name = vehicle_type_name;
    }

    public String getVehicle_age() {
        return vehicle_age;
    }

    public void setVehicle_age(String vehicle_age) {
        this.vehicle_age = vehicle_age;
    }

    public String getInterest_id() {
        return interest_id;
    }

    public void setInterest_id(String interest_id) {
        this.interest_id = interest_id;
    }

    public String getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(String is_deleted) {
        this.is_deleted = is_deleted;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(String updated_date) {
        this.updated_date = updated_date;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }

    public String getDt_interest_id() {
        return dt_interest_id;
    }

    public void setDt_interest_id(String dt_interest_id) {
        this.dt_interest_id = dt_interest_id;
    }

    public String getDt_interest_name() {
        return dt_interest_name;
    }

    public void setDt_interest_name(String dt_interest_name) {
        this.dt_interest_name = dt_interest_name;
    }

    public String getDt_interest_value() {
        return dt_interest_value;
    }

    public void setDt_interest_value(String dt_interest_value) {
        this.dt_interest_value = dt_interest_value;
    }
}
