package org.maktab.woocommercemarket.ui.fragmnet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import org.maktab.woocommercemarket.R;
import org.maktab.woocommercemarket.adapters.SliderAdapter;
import org.maktab.woocommercemarket.data.model.Product;
import org.maktab.woocommercemarket.databinding.FragmentProductInfoBinding;
import org.maktab.woocommercemarket.utils.ChartPreferences;
import org.maktab.woocommercemarket.viewModel.ProductInfoViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductInfoFragment extends Fragment {

    private static final String ARG_PRODUCT = "product_arg";

    private FragmentProductInfoBinding mBinding;
    private ProductInfoViewModel mViewModel;


    public static ProductInfoFragment newInstance(Product product) {
        ProductInfoFragment fragment = new ProductInfoFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PRODUCT, product);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ProductInfoViewModel.class);
        if (getArguments() != null) {
            Product product = (Product) getArguments().getSerializable(ARG_PRODUCT);
            mViewModel.setProduct(product);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_info
                , container, false);
        initViews();
        initObservers();
        return mBinding.getRoot();
    }

    private void initObservers() {
        mViewModel.getIsAddedToChart().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                changeAddToChartVisibility(aBoolean);
            }
        });
    }

    private void changeAddToChartVisibility(Boolean aBoolean) {
        if (aBoolean)
            mBinding.buttonAddToChart.setVisibility(View.GONE);
        else
            mBinding.buttonAddToChart.setVisibility(View.VISIBLE);
    }

    private void initViews() {
        mBinding.setViewModel(mViewModel);
        changeAddToChartVisibility(ChartPreferences
                .isInChart(this.getContext(),String.valueOf(mViewModel.getProduct().getId())));
        mBinding.imageSlider.setSliderAdapter(new SliderAdapter(getContext(), mViewModel.getProduct().getImages()));
    }


}