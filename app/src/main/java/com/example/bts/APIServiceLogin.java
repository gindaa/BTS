package com.example.bts;

import com.example.bts.model.Login;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIServiceLogin {

    @POST("/login")
    Call<LoginResponse> login (@Body Login login);
}
