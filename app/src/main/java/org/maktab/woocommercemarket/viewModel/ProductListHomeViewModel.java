package org.maktab.woocommercemarket.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import org.maktab.woocommercemarket.data.model.ListsType;
import org.maktab.woocommercemarket.data.model.Product;
import org.maktab.woocommercemarket.data.repository.WooRepository;

import java.util.List;

public class ProductListHomeViewModel extends ProductListViewModel {

    private final WooRepository mRepository;
    private final LiveData<List<Product>> mLiveDataProductNewest;
    private final LiveData<List<Product>> mLiveDataProductTopSale;
    private final LiveData<List<Product>> mLiveDataProductMostPoints;

    public ProductListHomeViewModel() {
        mRepository = WooRepository.getInstance();
        mLiveDataProductNewest = mRepository.getLiveDataList(ListsType.NEWEST);
        mLiveDataProductMostPoints = mRepository.getLiveDataList(ListsType.MOST_POINTS);
        mLiveDataProductTopSale = mRepository.getLiveDataList(ListsType.TOP_SALE);
    }

    @Override
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
    @Override
    public Product getProductByPosition(int position,ListsType listsType){
        try {
            switch (listsType) {
                case NEWEST:
                    return mLiveDataProductNewest.getValue().get(position);
                case TOP_SALE:
                    return mLiveDataProductTopSale.getValue().get(position);
                case MOST_POINTS:
                    return mLiveDataProductMostPoints.getValue().get(position);
            }
        } catch (IndexOutOfBoundsException e) {
            return Product.getLoadingInstance();
        }
        return null;
    }



    public void fetchItems(){
        Log.d("Tag","fetch Items on viewmodel");
        mRepository.fetchHomeItems();
    }


}