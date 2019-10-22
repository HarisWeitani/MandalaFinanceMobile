package com.mf.id.solidapp.Util;

import java.util.List;

public class ResponseModel {
    private String status;
    private String message;
    private List<ResponseDataModel> datas;

    public ResponseModel(String status, String message, List<ResponseDataModel> datas) {
        this.status = status;
        this.message = message;
        this.datas = datas;
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

    public List<ResponseDataModel> getDatas() {
        return datas;
    }

    public void setDatas(List<ResponseDataModel> datas) {
        this.datas = datas;
    }
}
