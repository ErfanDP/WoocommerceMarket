package org.maktab.woocommercemarket.data.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;
import org.maktab.woocommercemarket.data.model.ListsType;
import org.maktab.woocommercemarket.data.model.Product;
import org.maktab.woocommercemarket.data.remote.NetworkParams;
import org.maktab.woocommercemarket.data.remote.retrofit.RetrofitInstance;
import org.maktab.woocommercemarket.data.remote.retrofit.WooServiceRetrofit;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class WooRepository {

    private static WooRepository mRepository;
    private final WooServiceRetrofit mWooService;
    private final MutableLiveData<List<Product>> mListNewest = new MutableLiveData<>();
    private final MutableLiveData<List<Product>> mListTopSales = new MutableLiveData<>();
    private final MutableLiveData<List<Product>> mListMostPoints = new MutableLiveData<>();
    private boolean mItemFetched =false;


    public WooRepository() {
        Retrofit retrofit = RetrofitInstance.getInstance();
        mWooService = retrofit.create(WooServiceRetrofit.class);
        Log.d("Tag","set initaill live data value in Repository constructor");
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


    public void fetchHomeItems(){
        if(!mItemFetched) {
            fetchNewestItems();
            fetchMostPointItems();
            fetchTopSales();
            mItemFetched = true;
        }
    }


    public void fetchMostPointItems(){
        Call<List<Product>> call = mWooService.listItems(NetworkParams.getMostPointsOptions());
        startRespondEnqueue(mListMostPoints,call);
    }

    public void fetchMostPointItems(MutableLiveData<List<Product>> liveData,int page){
        Call<List<Product>> call = mWooService.listItems(NetworkParams.getMostPointsOptions(page));
        startRespondEnqueue(liveData, call);
    }

    public void fetchTopSales(){
        Call<List<Product>> call = mWooService.listItems(NetworkParams.getPopularOptions());
        startRespondEnqueue(mListTopSales,call);
    }
    public void fetchTopSales(MutableLiveData<List<Product>> liveData,int page){
        Call<List<Product>> call = mWooService.listItems(NetworkParams.getPopularOptions(page));
        startRespondEnqueue(liveData, call);
    }

    public void fetchNewestItems(){
        Call<List<Product>> call = mWooService.listItems(NetworkParams.getNewestOptions());
        startRespondEnqueue(mListNewest,call);
    }
    public void fetchNewestItems(MutableLiveData<List<Product>> liveData,int page){
        Call<List<Product>> call = mWooService.listItems(NetworkParams.getNewestOptions(page));
        startRespondEnqueue(liveData, call);
    }

    private void startRespondEnqueue(MutableLiveData<List<Product>> liveData, Call<List<Product>> call) {
        Log.d("Tag","response started");
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(@NotNull Call<List<Product>> call, @NotNull Response<List<Product>> response) {
                if(response.isSuccessful()) {
                    if(Objects.requireNonNull(response.body()).size() > 0) {
                        Log.d("Tag", "success set value " + response.body().get(0).toString());
                        List<Product> list = liveData.getValue();
                        Objects.requireNonNull(list).addAll(response.body());
                        liveData.setValue(list);
                    }
                }
            }
            @Override
            public void onFailure(@NotNull Call<List<Product>> call, @NotNull Throwable t) {
                Log.d("Tag","fail");
            }
        });
    }

//    public LiveData<Product> getItem(int id){
//        Call<Product> call = mWooService.getItem(id,NetworkParams.BASE_OPTIONS);
//        MutableLiveData<Product> productMutableLiveData = new MutableLiveData<>();
//        productMutableLiveData.setValue(Product.getLoadingInstance());
//        Log.d("Tag","response get item started");
//        call.enqueue(new Callback<Product>() {
//            @Override
//            public void onResponse(@NotNull Call<Product> call, @NotNull Response<Product> response) {
//                productMutableLiveData.setValue(response.body());
//                Log.d("Tag","response get item successful");
//            }
//
//            @Override
//            public void onFailure(@NotNull Call<Product> call, @NotNull Throwable t) {
//                Log.d("Tag","response get item failed");
//            }
//        });
//        return null;
//    }



}
