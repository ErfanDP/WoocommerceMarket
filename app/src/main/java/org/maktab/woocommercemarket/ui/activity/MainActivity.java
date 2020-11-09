package org.maktab.woocommercemarket.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.maktab.woocommercemarket.R;
import org.maktab.woocommercemarket.ui.fragmnet.CategoriesFragment;
import org.maktab.woocommercemarket.ui.fragmnet.ProductListsFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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



    }
    public void openFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.nav_container_fragment,fragment)
                .commit();
    }

}