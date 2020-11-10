package org.maktab.woocommercemarket.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.maktab.woocommercemarket.R;
import org.maktab.woocommercemarket.data.model.ListsType;
import org.maktab.woocommercemarket.databinding.ListHomeProductBinding;
import org.maktab.woocommercemarket.viewModel.ProductListHomeViewModel;

public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.ProductHolder> {
    private ProductListHomeViewModel mViewModel;
    private ListsType mListsType;
    private Context mContext;

    public HomeListAdapter(ProductListHomeViewModel viewModel, ListsType listsType, Context context) {
        mViewModel = viewModel;
        mListsType = listsType;
        mContext = context;
    }

    @Override
    public int getItemCount() {
        return mViewModel.getListProducts(mListsType).size();
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListHomeProductBinding listHomeProductsBinding = DataBindingUtil.inflate(
                LayoutInflater.from(mContext),
                R.layout.list_home_product,
                parent,
                false);
        return new ProductHolder(listHomeProductsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        holder.bindPhoto(position);
    }


    class ProductHolder extends RecyclerView.ViewHolder {

        private ListHomeProductBinding mListHomeProductBinding;


        public ProductHolder(ListHomeProductBinding listHomeProductsBinding) {
            super(listHomeProductsBinding.getRoot());
            mListHomeProductBinding = listHomeProductsBinding;
            mListHomeProductBinding.setViewModel(mViewModel);
            mListHomeProductBinding.setListType(mListsType);
        }

        public void bindPhoto(int position) {
            mListHomeProductBinding.setPosition(position);
            Glide
                    .with(mContext)
                    .load(mViewModel.getProductByPosition(position,mListsType).getImages().get(0).getSrc())
                    .centerCrop()
                    .placeholder(R.drawable.ic_product_place_holder)
                    .into(mListHomeProductBinding.productImage);
        }
    }
}
