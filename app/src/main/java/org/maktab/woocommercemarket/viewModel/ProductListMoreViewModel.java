package org.maktab.woocommercemarket.viewModel;

import androidx.lifecycle.MutableLiveData;

import org.maktab.woocommercemarket.data.model.ListsType;
import org.maktab.woocommercemarket.data.model.Product;
import org.maktab.woocommercemarket.data.repository.WooRepository;

import java.util.ArrayList;
import java.util.List;

public class ProductListMoreViewModel extends ProductListViewModel {

    private int mNumberOfPage = 1;
    private final MutableLiveData<List<Product>> mProductLiveData = new MutableLiveData<>();
    private final WooRepository mRepository;
    private final int INSERT_PER_PAGE = 10;



    public ProductListMoreViewModel() {
        mProductLiveData.setValue(new ArrayList<>());
        mRepository = WooRepository.getInstance();
    }

    public void fetchItems(ListsType listsType) {
        switch (listsType){
            case NEWEST:
                mRepository.fetchNewestItems(mProductLiveData,mNumberOfPage);
                break;
            case TOP_SALE:
                mRepository.fetchTopSales(mProductLiveData,mNumberOfPage);
                break;
            case MOST_POINTS:
                mRepository.fetchMostPointItems(mProductLiveData,mNumberOfPage);
        }
    }

    public void nextPage(ListsType listsType){
        mNumberOfPage++;
        fetchItems(listsType);
    }

    public MutableLiveData<List<Product>> getProductLiveData() {
        return mProductLiveData;
    }

    @Override
    public List<Product> getListProducts(ListsType listsType) {
        return mProductLiveData.getValue();
    }

    @Override
    public Product getProductByPosition(int position, ListsType listsType) {
        return mProductLiveData.getValue().get(position);
    }

    public int getFirstNewItemPosition() {
        if(mProductLiveData.getValue().size() == 0){
            return 0;
        }else
            return mProductLiveData.getValue().size()-INSERT_PER_PAGE;
    }

}