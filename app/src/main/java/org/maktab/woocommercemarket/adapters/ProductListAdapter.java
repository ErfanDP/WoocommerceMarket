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
import org.maktab.woocommercemarket.data.model.Product;
import org.maktab.woocommercemarket.databinding.ListProductItemBinding;
import org.maktab.woocommercemarket.viewModel.ProductListViewModel;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductHolder> {

    public static final int LIMITED_NUMBER_ITEMS = 8;
    private final ProductListViewModel mViewModel;
    private final ListsType mListsType;
    private final Context mContext;
    private final AdapterCallBacks mAdapterCallBacks;
    private final boolean mLimitedNumber;

    public ProductListAdapter(AdapterCallBacks adapterCallBacks
            , ProductListViewModel viewModel
            , ListsType listsType
            , Context context
            ,boolean limitedNumber) {
        mAdapterCallBacks = adapterCallBacks;
        mViewModel = viewModel;
        mListsType = listsType;
        mContext = context;
        mLimitedNumber = limitedNumber;
    }

    @Override
    public int getItemCount() {
        if(mLimitedNumber){
            return LIMITED_NUMBER_ITEMS;
        }
        return mViewModel.getListProducts(mListsType).size();
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListProductItemBinding listProductItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(mContext),
                R.layout.list_product_item,
                parent,
                false);
        return new ProductHolder(listProductItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        holder.bindPhoto(position);
    }

    class ProductHolder extends RecyclerView.ViewHolder {

        private final ListProductItemBinding mProductItemBinding;

        public ProductHolder(ListProductItemBinding listHomeProductsBinding) {
            super(listHomeProductsBinding.getRoot());
            mProductItemBinding = listHomeProductsBinding;
            mProductItemBinding.setViewModel(mViewModel);
            mProductItemBinding.setListType(mListsType);

        }

        public void bindPhoto(int position) {
            mProductItemBinding.setPosition(position);
            mProductItemBinding.getRoot().setOnClickListener(v ->
                    mAdapterCallBacks.onHolderClick(mViewModel.getProductByPosition(position,mListsType)));

            try {
                Glide
                        .with(mContext)
                        .load(mViewModel.getProductByPosition(position,mListsType).getImages().get(0).getSrc())
                        .centerCrop()
                        .placeholder(R.drawable.ic_product_place_holder)
                        .into(mProductItemBinding.productImage);
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }
    }

    public interface AdapterCallBacks{
        void onHolderClick(Product product);
    }
}
