package org.maktab.woocommercemarket.ui.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.ActivityNavigator;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.maktab.woocommercemarket.R;
import org.maktab.woocommercemarket.data.model.ListsType;
import org.maktab.woocommercemarket.data.model.Product;
import org.maktab.woocommercemarket.ui.fragmnet.CategoriesFragment;
import org.maktab.woocommercemarket.ui.fragmnet.ProductInfoFragment;
import org.maktab.woocommercemarket.ui.fragmnet.ProductListMoreFragment;
import org.maktab.woocommercemarket.ui.fragmnet.ProductListMoreFragmentDirections;
import org.maktab.woocommercemarket.ui.fragmnet.ProductListsFragment;
import org.maktab.woocommercemarket.ui.fragmnet.ProductListsFragmentDirections;

public class MainActivity extends AppCompatActivity implements ProductListsFragment.FragmentCallBacks
        , ProductListMoreFragment.FragmentMoreCallBacks {
    private NavController mNavController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationInit();
    }

    private void navigationInit() {
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_container_fragment);
        mNavController =  navHostFragment.getNavController();
        BottomNavigationView bottomNavigationView =findViewById(R.id.bottom_nav_view);
        NavigationUI.setupWithNavController(bottomNavigationView,navHostFragment.getNavController());
        bottomNavigationView.setOnNavigationItemReselectedListener(item -> {});
    }


    @Override
    public void onHolderClick(Product product) {
        NavDirections action = ProductListsFragmentDirections
                        .actionProductListsFragmentToProductInfoFragment(product);
        mNavController.navigate(action);
    }

    @Override
    public void onMoreClick(ListsType listsType) {
        NavDirections action = ProductListsFragmentDirections.
                actionProductListsFragmentToProductListMoreFragment(listsType);
        mNavController.navigate(action);
    }

    @Override
    public void onHolderMoreClick(Product product) {
        NavDirections action = ProductListMoreFragmentDirections
                .actionProductListMoreFragmentToProductInfoFragment(product);
        mNavController.navigate(action);
    }
}