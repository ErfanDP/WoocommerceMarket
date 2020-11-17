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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;
import org.maktab.woocommercemarket.R;
import org.maktab.woocommercemarket.adapters.ProductListAdapter;
import org.maktab.woocommercemarket.data.model.ListsType;
import org.maktab.woocommercemarket.data.model.Product;
import org.maktab.woocommercemarket.databinding.FragmentProductListMoreBinding;
import org.maktab.woocommercemarket.viewModel.ProductListMoreViewModel;

import java.util.List;

public class ProductListMoreFragment extends Fragment {

    public static final String ARG_LIST_TYPE = "arg_list_type";
    private static final int NUMBER_OF_ROWS = 2;
    private FragmentProductListMoreBinding mBinding;
    private ProductListMoreViewModel mViewModel;
    private ListsType mListsType;
    private FragmentMoreCallBacks mFragmentMoreCallBacks;
    private ProductListAdapter mAdapter;

    public static ProductListMoreFragment newInstance(ListsType listsType) {
        ProductListMoreFragment fragment = new ProductListMoreFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_LIST_TYPE, listsType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mFragmentMoreCallBacks = (FragmentMoreCallBacks) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            mListsType = (ListsType) getArguments().getSerializable(ARG_LIST_TYPE);
        }
        mViewModel = new ViewModelProvider(this).get(ProductListMoreViewModel.class);
        mViewModel.fetchItems(mListsType);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater
                ,R.layout.fragment_product_list_more
                ,container
                ,false);
        recyclerViewInit();
        registerObservers();
        mBinding.listMore.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NotNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1)) {
                    mViewModel.nextPage(mListsType);
                }
            }
        });
        return mBinding.getRoot();
    }

    private void registerObservers() {
        Log.d("Tag","register observer List more");
        mViewModel.getProductLiveData().observe(getViewLifecycleOwner()
                , products -> mAdapter
                        .notifyItemRangeInserted(mViewModel.getFirstNewItemPosition()
                                ,products.size()-1));
    }

    private void recyclerViewInit() {
        mBinding.listMore.setLayoutManager(new GridLayoutManager(getContext(),NUMBER_OF_ROWS));
        ProductListAdapter.AdapterCallBacks adapterCallBacks =
                product -> mFragmentMoreCallBacks.onHolderMoreClick(product);
        mAdapter = new ProductListAdapter(adapterCallBacks
                ,mViewModel
                , ListsType.TOP_SALE
                ,getContext()
                ,false);
        mBinding.listMore.setAdapter(mAdapter);
    }


    public interface FragmentMoreCallBacks{
        void onHolderMoreClick(Product product);
    }

}