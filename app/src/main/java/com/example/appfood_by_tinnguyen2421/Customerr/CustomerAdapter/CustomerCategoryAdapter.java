package com.example.appfood_by_tinnguyen2421.Customerr.CustomerAdapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appfood_by_tinnguyen2421.Categories;
import com.example.appfood_by_tinnguyen2421.Customerr.CustomerActivity.CustomerCategoriesAfterChoose;
import com.example.appfood_by_tinnguyen2421.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

//May not be copied in any form
//Copyright belongs to Nguyen TrongTin. contact: email:tinnguyen2421@gmail.com
public class CustomerCategoryAdapter extends RecyclerView.Adapter<CustomerCategoryAdapter.ViewHolder> {

    private List<Categories> categoryList;
    private Context mcontext;


    public CustomerCategoryAdapter(List<Categories> categoryList, Context context) {
        this.categoryList = categoryList;
         this.mcontext = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.loai_monan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        final Categories categories = categoryList.get(position);
        Glide.with(mcontext).load(categories.getImage()).into(holder.imageView);
        categories.getMatheloai();
        holder.title.setText(categories.getTentheloai());

            Picasso.get()
                    .load(categories.getImage())
                    .into(holder.imageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            holder.progressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError(Exception e) {
                            Log.d("Error : ", e.getMessage());
                        }
                    });
            holder.card_view4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(mcontext, CustomerCategoriesAfterChoose.class);
                    intent.putExtra("matl",categories.getMatheloai());
                    mcontext.startActivity(intent);
                }
            });



    }

    @Override
    public int getItemCount() {
        return categoryList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;
        ProgressBar progressBar;
        CardView cardView,cardView1,card_view4;
        LinearLayout line1;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.category_image);
            title = itemView.findViewById(R.id.category_title);
            progressBar = itemView.findViewById(R.id.progressbar1);
            cardView = itemView.findViewById(R.id.card_view);
            cardView1 = itemView.findViewById(R.id.card_view1);
            card_view4 = itemView.findViewById(R.id.card_view4);
            line1 = itemView.findViewById(R.id.line1);

        }
    }
}
