package org.maktab.woocommercemarket.data.remote;

import org.maktab.woocommercemarket.data.model.Product;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface WooServiceRetrofit {
    @GET("products")
    Call<List<Product>> listItems(@QueryMap Map<String, String> options);

    @GET("products/{id}")
    Call<Product> getItem(@Path("id")int id, @QueryMap Map<String, String> options);
}
