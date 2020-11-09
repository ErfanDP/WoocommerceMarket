package org.maktab.woocommercemarket.data.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.maktab.woocommercemarket.data.model.ListsType;
import org.maktab.woocommercemarket.data.model.Product;
import org.maktab.woocommercemarket.data.remote.NetworkParams;
import org.maktab.woocommercemarket.data.remote.RetrofitInstance;
import org.maktab.woocommercemarket.data.remote.WooServiceRetrofit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class WooRepository {

    private static WooRepository mRepository;
    private WooServiceRetrofit mWooService;
    private MutableLiveData<List<Product>> mListNewest = new MutableLiveData<>();
    private MutableLiveData<List<Product>> mListTopSales = new MutableLiveData<>();
    private MutableLiveData<List<Product>> mListMostPoints = new MutableLiveData<>();


    public WooRepository() {
        Retrofit retrofit = RetrofitInstance.getInstance();
        mWooService = retrofit.create(WooServiceRetrofit.class);
        mListNewest.setValue(new ArrayList<>());
        mListTopSales.setValue(new ArrayList<>());
        mListMostPoints.setValue(new ArrayList<>());
    }

    public static WooRepository getInstance() {
        if(mRepository == null){
            mRepository = new WooRepository();
        }
        return mRepository;
    }



    public LiveData<List<Product>> getLiveDataList(ListsType listsType) {
        switch (listsType) {
            case MOST_POINTS:
                return mListMostPoints;
            case TOP_SALE:
                return mListTopSales;
            case NEWEST:
                return mListNewest;
        }
        return null;
    }



    public void fetchItems(){

        Call<List<Product>> call = mWooService.listItems(NetworkParams.getNewestOptions());
        Log.d("Tag","response started");
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                Log.d("Tag","success");
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("Tag","fail");

            }
        });

    }


    public void setLiveDataValue(List<Product> products, ListsType listsType) {
        switch (listsType) {
            case MOST_POINTS:
                mListMostPoints.setValue(products);
            case TOP_SALE:
                mListTopSales.setValue(products);
            case NEWEST:
                mListNewest.setValue(products);
        }
    }
}
