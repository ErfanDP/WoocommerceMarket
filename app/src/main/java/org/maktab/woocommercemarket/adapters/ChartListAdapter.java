package org.maktab.woocommercemarket.adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ChartListAdapter extends RecyclerView.Adapter<ChartListAdapter.CategoryHolder> {
    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class CategoryHolder extends RecyclerView.ViewHolder{

        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
