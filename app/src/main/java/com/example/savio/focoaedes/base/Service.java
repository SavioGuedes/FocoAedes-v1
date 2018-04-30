package com.example.savio.focoaedes.base;

import com.example.savio.focoaedes.model.Ocorrencia;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Service {

    public static final String base_url = "http://104.236.7.68:8080/";

    Gson gson = new GsonBuilder().setLenient().create();

    //busca a url do arquivo JSON e faz a conversão
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Service.base_url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    //@GET("followers")
    //Call<Ocorrencia> listaCatalogos1(); //JSON OBJECT

    @GET("ocorrencias")
    Call<List<Ocorrencia>> getOcorrencias(); //capturar todas as ocorrencias

    @GET("ocorrencias/{id}")
    Call<Ocorrencia> showOcorrencias(@Path("id") String itemId); //Detalhes da ocorrencia

    @POST("ocorrencias")
    Call<Ocorrencia> setOcorrencias(@Body Ocorrencia ocorrencias); //postar uma ocorrencia

    @DELETE("ocorrencias/{id}")
    Call<Void> deleteOcorrencias(@Path("id") String itemId); //deletar uma ocorrencia

}
