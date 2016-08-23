package com.example.rsc.myapplication.restApi.adapter;

import com.example.rsc.myapplication.restApi.ConstantesRestApi;
import com.example.rsc.myapplication.restApi.EndpointsApi;
import com.example.rsc.myapplication.restApi.deserializador.MascotaDeserialRecentMedia;
import com.example.rsc.myapplication.restApi.deserializador.MascotaDeserialUsersFollows;
import com.example.rsc.myapplication.restApi.model.MascotaResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RestApiAdapter {

    public EndpointsApi conexionRestApi(Gson gson){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(EndpointsApi.class);
    }

    public Gson gsonDeserialRecentMedia(){

        GsonBuilder gsonBuilder = new GsonBuilder()
                .registerTypeAdapter(MascotaResponse.class, new MascotaDeserialRecentMedia());

        return gsonBuilder.create();

    }

    public Gson gsonDeserialUsersFollows(){
        GsonBuilder gsonBuilder = new GsonBuilder()
                .registerTypeAdapter(MascotaResponse.class,new MascotaDeserialUsersFollows());

        return gsonBuilder.create();
    }

}
