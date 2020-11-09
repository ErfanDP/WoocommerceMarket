package org.maktab.woocommercemarket.viewModel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.maktab.woocommercemarket.adapters.HomeListAdapter;
import org.maktab.woocommercemarket.data.model.ListsType;
import org.maktab.woocommercemarket.data.model.Product;
import org.maktab.woocommercemarket.data.repository.WooRepository;

import java.util.List;

public class ProductListHomeViewModel extends ViewModel {


    private WooRepository mRepository;
    private LiveData<List<Product>> mLiveDataProductNewest;
    private LiveData<List<Product>> mLiveDataProductTopSale;
    private LiveData<List<Product>> mLiveDataProductMostPoints;

    public ProductListHomeViewModel() {
        mRepository = WooRepository.getInstance();
        mLiveDataProductNewest = mRepository.getLiveDataList(ListsType.NEWEST);
        mLiveDataProductMostPoints = mRepository.getLiveDataList(ListsType.MOST_POINTS);
        mLiveDataProductTopSale = mRepository.getLiveDataList(ListsType.TOP_SALE);
    }




    public List<Product> getListProducts(ListsType listsType) {
        switch (listsType) {
            case NEWEST:
                return mLiveDataProductNewest.getValue();
            case TOP_SALE:
                return mLiveDataProductTopSale.getValue();
            case MOST_POINTS:
                return mLiveDataProductMostPoints.getValue();
        }
        return null;
    }


    public LiveData<List<Product>> getLiveDataProductLists(ListsType listsType) {
        switch (listsType) {
            case NEWEST:
                return mLiveDataProductNewest;
            case TOP_SALE:
                return mLiveDataProductTopSale;
            case MOST_POINTS:
                return mLiveDataProductMostPoints;
        }
        return null;
    }

    public Product getProductByPosition(int position,ListsType listsType){
        switch (listsType) {
            case NEWEST:
                return mLiveDataProductNewest.getValue().get(position);
            case TOP_SALE:
                return mLiveDataProductTopSale.getValue().get(position);
            case MOST_POINTS:
                return mLiveDataProductMostPoints.getValue().get(position);
        }
        return null;

    }


    public void setLiveDataValue(List<Product> products, ListsType newest) {
        mRepository.setLiveDataValue(products,newest);
    }


    public void fetchNewestItems(){
        mRepository.fetchNewestItems();
    }
}