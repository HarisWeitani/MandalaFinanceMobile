package com.mf.id.solidapp.Util;

import java.util.List;

public class ResponseModel {
    private String status;
    private String message;
    private List<ResponseDataModel> data;

    public ResponseModel(String status, String message, List<ResponseDataModel> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ResponseDataModel> getData() {
        return data;
    }

    public void setData(List<ResponseDataModel> data) {
        this.data = data;
    }
}
