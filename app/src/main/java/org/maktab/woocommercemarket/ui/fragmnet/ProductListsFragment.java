package org.maktab.woocommercemarket.ui.fragmnet;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import org.maktab.woocommercemarket.R;
import org.maktab.woocommercemarket.adapters.HomeListAdapter;
import org.maktab.woocommercemarket.data.model.ListsType;
import org.maktab.woocommercemarket.data.model.Product;
import org.maktab.woocommercemarket.databinding.FragmentProductListsBinding;
import org.maktab.woocommercemarket.viewModel.ProductListHomeViewModel;

public class ProductListsFragment extends Fragment {

    private ProductListHomeViewModel mViewModel;
    private FragmentProductListsBinding mBinding;
    private FragmentCallBacks mFragmentCallBacks;

    public static ProductListsFragment newInstance() {
        Bundle args = new Bundle();
        ProductListsFragment fragment = new ProductListsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ProductListHomeViewModel.class);
        mViewModel.fetchItems();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mFragmentCallBacks = (FragmentCallBacks) context;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater
                ,R.layout.fragment_product_lists
                ,container
                ,false);

        registerObservers();
        initRecyclerViews();
        return mBinding.getRoot();
    }



    private void registerObservers() {
        Log.d("Tag","observers registered");
        mViewModel.getLiveDataProductLists(ListsType.NEWEST).observe(getViewLifecycleOwner()
                , products -> {
                    Log.d("Tag","observer newest on fragment");
                    mBinding.homeTopNewest.setAdapter(getListAdapter(ListsType.NEWEST));
                });
        mViewModel.getLiveDataProductLists(ListsType.MOST_POINTS).observe(getViewLifecycleOwner()
                , products -> {
                    Log.d("Tag","observer MostPoints on fragment");
                    mBinding.homeMostPoints.setAdapter(getListAdapter(ListsType.MOST_POINTS));
                });
        mViewModel.getLiveDataProductLists(ListsType.TOP_SALE).observe(getViewLifecycleOwner()
                , products -> {
                    Log.d("Tag","observer TopSale on fragment");
                    mBinding.homeTopSales.setAdapter(getListAdapter(ListsType.TOP_SALE));
                });

    }

    public HomeListAdapter getListAdapter(ListsType listsType) {
        HomeListAdapter.AdapterCallBacks adapterCallBacks =
                product -> mFragmentCallBacks.onHolderClick(product);
        switch (listsType) {
            case NEWEST:
                return new HomeListAdapter(adapterCallBacks,mViewModel, ListsType.NEWEST,getContext());
            case TOP_SALE:
                return new HomeListAdapter(adapterCallBacks,mViewModel, ListsType.TOP_SALE,getContext());
            case MOST_POINTS:
                return new HomeListAdapter(adapterCallBacks,mViewModel, ListsType.MOST_POINTS,getContext());
        }
        return null;
    }

    private void initRecyclerViews() {
        Log.d("Tag","recyclerview Init");

        mBinding.homeTopNewest
                .setLayoutManager(new LinearLayoutManager(getContext()
                        ,LinearLayoutManager.HORIZONTAL
                        ,false));
        mBinding.homeMostPoints
                .setLayoutManager(new LinearLayoutManager(getContext()
                        ,LinearLayoutManager.HORIZONTAL
                        ,false));
        mBinding.homeTopSales
                .setLayoutManager(new LinearLayoutManager(getContext()
                        ,LinearLayoutManager.HORIZONTAL
                        ,false));


    }

    public interface FragmentCallBacks{
        void onHolderClick(Product product);
    }
}