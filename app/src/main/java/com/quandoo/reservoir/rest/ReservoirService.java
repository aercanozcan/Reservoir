package com.quandoo.reservoir.rest;

import com.quandoo.reservoir.model.Customer;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by ercanozcan on 03/09/16.
 */
public class ReservoirService {
    public static final String END_POINT = "https://s3-eu-west-1.amazonaws.com/quandoo-assessment/";

    private static ReservoirService instance;

    private RestaurantService restaurantService;

    public ReservoirService() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(END_POINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        restaurantService = retrofit.create(RestaurantService.class);
    }

    public static ReservoirService getInstance() {
        if (instance == null) {
            instance = new ReservoirService();
        }
        return instance;
    }

    public RestaurantService getRestaurantService() {
        return restaurantService;
    }

    public interface RestaurantService {
        @GET("customer-list.json")
        Call<List<Customer>> customers();

        @GET("table-map.json")
        Call<List<Boolean>> tables();
    }

}
