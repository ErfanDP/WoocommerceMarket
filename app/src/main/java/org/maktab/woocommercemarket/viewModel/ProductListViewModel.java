package org.maktab.woocommercemarket.viewModel;

import androidx.lifecycle.ViewModel;

import org.maktab.woocommercemarket.data.model.ListsType;
import org.maktab.woocommercemarket.data.model.Product;

import java.util.List;

public abstract class ProductListViewModel extends ViewModel {

    public abstract List<Product> getListProducts(ListsType listsType);

    public abstract Product getProductByPosition(int position, ListsType listsType);


}
