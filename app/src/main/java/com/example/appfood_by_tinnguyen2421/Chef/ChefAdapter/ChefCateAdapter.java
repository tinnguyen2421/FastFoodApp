package com.example.appfood_by_tinnguyen2421.Chef.ChefAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfood_by_tinnguyen2421.Categories;
import com.example.appfood_by_tinnguyen2421.Chef.ChefActivity.ChefCategoryAfterChoose;
import com.example.appfood_by_tinnguyen2421.R;


import java.util.List;
//May not be copied in any form
//Copyright belongs to Nguyen TrongTin. contact: email:tinnguyen2421@gmail.com
public class ChefCateAdapter extends RecyclerView.Adapter<ChefCateAdapter.ViewHolder> {

    private Context mcont;
    private List<Categories> categoriesList;

    public ChefCateAdapter(Context context, List<Categories> categoriesList) {
        this.categoriesList = categoriesList;
        this.mcont = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcont).inflate(R.layout.chef_cate_update_delete, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Categories categories = categoriesList.get(position);
        holder.dishcate.setText(categories.getTentheloai());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mcont, ChefCategoryAfterChoose.class);
                intent.putExtra("matl",categories.getMatheloai());
                mcont.startActivity(intent);
            }
        });
    }




    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView dishcate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dishcate = itemView.findViewById(R.id.dish_cate);

        }
    }
}