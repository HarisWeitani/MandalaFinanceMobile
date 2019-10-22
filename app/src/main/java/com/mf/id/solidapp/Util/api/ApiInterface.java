package com.mf.id.solidapp.Util.api;

import com.mf.id.solidapp.Util.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("")
    Call<ResponseModel> getData();

}
