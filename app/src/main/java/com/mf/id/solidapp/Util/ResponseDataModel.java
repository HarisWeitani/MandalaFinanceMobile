package com.mf.id.solidapp.Util;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "ResponseDatas")
public class ResponseDataModel {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "calculationId")
    private String calculationId;
    @ColumnInfo(name = "vehicleTypeName")
    private String vehicleTypeName;
    @ColumnInfo(name = "vehicleAge")
    private String vehicleAge;
    @ColumnInfo(name = "interestId")
    private String interestId;
    @ColumnInfo(name = "isDeleted")
    private String isDeleted;
    @ColumnInfo(name = "createdDate")
    private String createdDate;
    @ColumnInfo(name = "updatedDate")
    private String updatedDate;
    @ColumnInfo(name = "createdBy")
    private String createdBy;
    @ColumnInfo(name = "updatedBy")
    private String updatedBy;
    @ColumnInfo(name = "dtInterestId")
    private String dtInterestId;
    @ColumnInfo(name = "dtInterestName")
    private String dtInterestName;
    @ColumnInfo(name = "dtInterestValue")
    private String dtInterestValue;

    public ResponseDataModel(@NonNull String calculationId, String vehicleTypeName, String vehicleAge, String interestId, String isDeleted, String createdDate, String updatedDate, String createdBy, String updatedBy, String dtInterestId, String dtInterestName, String dtInterestValue) {
        this.calculationId = calculationId;
        this.vehicleTypeName = vehicleTypeName;
        this.vehicleAge = vehicleAge;
        this.interestId = interestId;
        this.isDeleted = isDeleted;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.dtInterestId = dtInterestId;
        this.dtInterestName = dtInterestName;
        this.dtInterestValue = dtInterestValue;
    }

    @Ignore
    public ResponseDataModel(){}

    @NonNull
    public String getCalculationId() {
        return calculationId;
    }

    public void setCalculationId(@NonNull String calculationId) {
        this.calculationId = calculationId;
    }

    public String getVehicleTypeName() {
        return vehicleTypeName;
    }

    public void setVehicleTypeName(String vehicleTypeName) {
        this.vehicleTypeName = vehicleTypeName;
    }

    public String getVehicleAge() {
        return vehicleAge;
    }

    public void setVehicleAge(String vehicleAge) {
        this.vehicleAge = vehicleAge;
    }

    public String getInterestId() {
        return interestId;
    }

    public void setInterestId(String interestId) {
        this.interestId = interestId;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getDtInterestId() {
        return dtInterestId;
    }

    public void setDtInterestId(String dtInterestId) {
        this.dtInterestId = dtInterestId;
    }

    public String getDtInterestName() {
        return dtInterestName;
    }

    public void setDtInterestName(String dtInterestName) {
        this.dtInterestName = dtInterestName;
    }

    public String getDtInterestValue() {
        return dtInterestValue;
    }

    public void setDtInterestValue(String dtInterestValue) {
        this.dtInterestValue = dtInterestValue;
    }
}
