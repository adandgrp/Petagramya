package com.example.rsc.myapplication.restApi;

import com.example.rsc.myapplication.restApi.model.MascotaResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface EndpointsApi {

    @GET(ConstantesRestApi.URL_GET_FOLLOWS)
    Call<MascotaResponse> getUserFollows();

    @GET(ConstantesRestApi.KEY_GET_RECENT_MEDIA_USERS + "{user}" + ConstantesRestApi.URL_RECENT_MEDIA)
    Call<MascotaResponse> getUserRecentMedia(@Path("user") String user, @Query("count") int count);

    @GET(ConstantesRestApi.KEY_GET_SEARCH +  ConstantesRestApi.URL_SEARCH)
    Call<MascotaResponse> getUserSearch(@Query("q") String q);

    @GET(ConstantesRestApi.KEY_GET_RECENT_MEDIA_USERS + "{user}" + ConstantesRestApi.URL_RECENT_MEDIA_ALL)
    Call<MascotaResponse> getUserRecentMediaAll(@Path("user") String user);


}
