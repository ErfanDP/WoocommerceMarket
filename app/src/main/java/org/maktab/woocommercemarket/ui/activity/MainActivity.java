package org.maktab.woocommercemarket.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.maktab.woocommercemarket.R;
import org.maktab.woocommercemarket.data.model.ListsType;
import org.maktab.woocommercemarket.data.model.Product;
import org.maktab.woocommercemarket.data.repository.WooRepository;
import org.maktab.woocommercemarket.ui.fragmnet.CategoriesFragment;
import org.maktab.woocommercemarket.ui.fragmnet.ProductInfoFragment;
import org.maktab.woocommercemarket.ui.fragmnet.ProductListMoreFragment;
import org.maktab.woocommercemarket.ui.fragmnet.ProductListsFragment;

public class MainActivity extends AppCompatActivity implements ProductListsFragment.FragmentCallBacks
, ProductListMoreFragment.FragmentMoreCallBacks {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openFragment(ProductListsFragment.newInstance());
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    openFragment(ProductListsFragment.newInstance());
                    return true;
                case R.id.navigation_categories:
                    openFragment(CategoriesFragment.newInstance());
                    return true;
            }
            return false;
        });


        navView.setOnNavigationItemReselectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                case R.id.navigation_categories:
            }
        });



    }
    public <T extends Fragment> void openFragment(T fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.nav_container_fragment,fragment)
                    .commit();
    }


    @Override
    public void onHolderClick(Product product) {
        openFragment(ProductInfoFragment.newInstance(product));
    }

    @Override
    public void onMoreClick(ListsType listsType) {
        openFragment(ProductListMoreFragment.newInstance(listsType));
    }

    @Override
    public void onHolderMoreClick(Product product) {
        openFragment(ProductInfoFragment.newInstance(product));
    }
}