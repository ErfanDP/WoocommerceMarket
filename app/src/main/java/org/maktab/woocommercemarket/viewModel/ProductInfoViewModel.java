package org.maktab.woocommercemarket.viewModel;

import android.os.Build;
import android.text.Html;
import android.text.Spanned;

import androidx.lifecycle.ViewModel;

public class ProductInfoViewModel extends ViewModel {

    public Spanned deserializeHtml(String string){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(string, Html.FROM_HTML_MODE_COMPACT);
        } else {
            return Html.fromHtml(string);
        }
    }

}
