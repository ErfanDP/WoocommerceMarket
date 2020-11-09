package org.maktab.woocommercemarket.ui.fragmnet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import org.maktab.woocommercemarket.R;
import org.maktab.woocommercemarket.adapters.HomeListAdapter;
import org.maktab.woocommercemarket.data.model.ListsType;
import org.maktab.woocommercemarket.databinding.FragmentProductListsBinding;
import org.maktab.woocommercemarket.viewModel.ProductListHomeViewModel;

public class ProductListsFragment extends Fragment {

    private ProductListHomeViewModel mViewModel;
    private FragmentProductListsBinding mBinding;


    public static ProductListsFragment newInstance() {
        Bundle args = new Bundle();
        ProductListsFragment fragment = new ProductListsFragment();
        fragment.setArguments(args);
        return fragment;
    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater
                ,R.layout.fragment_product_lists
                ,container
                ,false);


        mViewModel = new ViewModelProvider(this).get(ProductListHomeViewModel.class);
        mViewModel.fetchNewestItems();
        registerObservers();
        return mBinding.getRoot();
    }



    private void registerObservers() {
        mViewModel.getLiveDataProductLists(ListsType.NEWEST).observe(getViewLifecycleOwner(), products -> {
            mViewModel.setLiveDataValue(products,ListsType.NEWEST);
            mBinding.homeTopNewest.setAdapter(getListAdapter(ListsType.NEWEST));
            initRecyclerViews();
        });

    }


    public HomeListAdapter getListAdapter(ListsType listsType) {
        switch (listsType) {
            case NEWEST:
                return new HomeListAdapter(mViewModel, ListsType.NEWEST,getContext());
            case TOP_SALE:
                return new HomeListAdapter(mViewModel, ListsType.TOP_SALE,getContext());
            case MOST_POINTS:
                return new HomeListAdapter(mViewModel, ListsType.MOST_POINTS,getContext());
        }
        return null;
    }

    private void initRecyclerViews() {
        mBinding.homeTopNewest
                .setLayoutManager(new LinearLayoutManager(getContext()
                        ,LinearLayoutManager.HORIZONTAL
                        ,false));

    }
}