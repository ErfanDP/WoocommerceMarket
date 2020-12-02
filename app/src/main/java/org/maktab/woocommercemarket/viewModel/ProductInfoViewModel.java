package org.maktab.woocommercemarket.viewModel;

import android.app.Application;
import android.os.Build;
import android.text.Html;
import android.text.Spanned;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import org.maktab.woocommercemarket.data.model.Product;
import org.maktab.woocommercemarket.utils.ChartPreferences;

public class ProductInfoViewModel extends AndroidViewModel {

    private Product mProduct;
    private MutableLiveData<Boolean> mIsAddedToChart = new MutableLiveData<>(false);

    public ProductInfoViewModel(@NonNull Application application) {
        super(application);
    }

    public void setProduct(Product product) {
        mProduct = product;
        if(ChartPreferences.isInChart(getApplication(),String.valueOf(mProduct.getId()))){
            mIsAddedToChart.setValue(true);
        }
    }

    public MutableLiveData<Boolean> getIsAddedToChart() {
        return mIsAddedToChart;
    }

    public Product getProduct() {
        return mProduct;
    }

    public Spanned getDescription(){
        String string = mProduct.getDescription();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(string, Html.FROM_HTML_MODE_COMPACT);
        } else {
            return Html.fromHtml(string);
        }
    }

    public void addToChart(){
        ChartPreferences.addToChart(getApplication(), String.valueOf(mProduct.getId()));
        mIsAddedToChart.setValue(true);
    }


}
