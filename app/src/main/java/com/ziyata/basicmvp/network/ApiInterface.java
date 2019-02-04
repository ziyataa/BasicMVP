package com.ziyata.basicmvp.network;

import com.ziyata.basicmvp.model.LoginBody;
import com.ziyata.basicmvp.model.LoginResponse;
import com.ziyata.basicmvp.model.SingelUserResponse;
import com.ziyata.basicmvp.model.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    // Membuat endpoint login
    @POST("api/login")
    Call<LoginResponse> postLogin(@Body LoginBody loginBody);

    // Membuat endpoint get data user
    @GET("api/users")
    Call<UserResponse> getDataUser(
            @Query("per_page") int perPage);

    // Membuat endpoint untuk get data singel user
    @GET("api/users/{id}")
    Call<SingelUserResponse> getDataSingleUser(@Path("id") int id);

    
}
