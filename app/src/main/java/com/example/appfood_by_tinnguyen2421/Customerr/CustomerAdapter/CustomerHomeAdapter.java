package com.example.appfood_by_tinnguyen2421.Customerr.CustomerAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.appfood_by_tinnguyen2421.Chef.ChefModel.UpdateDishModel;
import com.example.appfood_by_tinnguyen2421.Customerr.CustomerActivity.OrderDish;
import com.example.appfood_by_tinnguyen2421.R;


import java.text.DecimalFormat;
import java.util.List;

//May not be copied in any form
//Copyright belongs to Nguyen TrongTin. contact: email:tinnguyen2421@gmail.com
public class CustomerHomeAdapter extends RecyclerView.Adapter<CustomerHomeAdapter.ViewHolder> {


    private Context mcontext;
    private List<UpdateDishModel>updateDishModellist;


    public CustomerHomeAdapter(Context context, List<UpdateDishModel>updateDishModellist)
    {
        this.updateDishModellist=updateDishModellist;
        this.mcontext=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mcontext).inflate(R.layout.monan,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        final UpdateDishModel updateDishModel=updateDishModellist.get(position);
        Glide.with(mcontext).load(updateDishModel.getImageURL()).into(holder.imageView);
        holder.Dishname.setText(updateDishModel.getDishes());
        updateDishModel.getRandomUID();
        updateDishModel.getChefId();
        double price=Double.parseDouble(updateDishModel.getPrice());
        DecimalFormat decimalFormat=new DecimalFormat("#,###,###,###");
        String FormatPrice=decimalFormat.format(price);
        holder.price.setText("Giá:" +FormatPrice+"đ");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(mcontext, OrderDish.class);
                intent.putExtra("FoodMenu"  ,updateDishModel.getRandomUID());
                intent.putExtra("ChefId",updateDishModel.getChefId());
                intent.putExtra("CateID",updateDishModel.getCateID());
                intent.putExtra("TenMon",updateDishModel.getDishes());
                mcontext.startActivity(intent);
            }
        });
        holder.imageViewShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Intent intent=new Intent(Intent.ACTION_SEND);
             intent.setType("text/plain");
             String shareBody=updateDishModel.getDishes();
             String shareSub=updateDishModel.getDescription();
                String shareSub1=updateDishModel.getImageURL();
                String combinedText=shareSub+"\n"+shareSub1;
             intent.putExtra(Intent.EXTRA_SUBJECT,shareBody);
             intent.putExtra(Intent.EXTRA_TEXT,combinedText);

             mcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return updateDishModellist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView,imageViewShare;
        TextView Dishname,price;
        ElegantNumberButton additem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
 imageViewShare=itemView.findViewById(R.id.share_btn);
            imageView=itemView.findViewById(R.id.menu_image);
            Dishname=itemView.findViewById(R.id.dishname);
            price=itemView.findViewById(R.id.dishprice);
            additem=itemView.findViewById(R.id.number_btn);


        }
    }
}
