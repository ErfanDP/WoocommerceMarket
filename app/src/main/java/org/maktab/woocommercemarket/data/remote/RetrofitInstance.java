package org.maktab.woocommercemarket.data.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    public static Retrofit getInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NetworkParams.BASE_PATH)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}
