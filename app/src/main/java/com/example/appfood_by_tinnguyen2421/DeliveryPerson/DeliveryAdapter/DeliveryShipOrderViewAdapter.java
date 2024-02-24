package com.example.appfood_by_tinnguyen2421.DeliveryPerson.DeliveryAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.appfood_by_tinnguyen2421.DeliveryPerson.DeliveryModel.DeliveryShipFinalOrders;
import com.example.appfood_by_tinnguyen2421.R;

import java.util.List;
//May not be copied in any form
//Copyright belongs to Nguyen TrongTin. contact: email:tinnguyen2421@gmail.com
public class DeliveryShipOrderViewAdapter extends RecyclerView.Adapter<DeliveryShipOrderViewAdapter.ViewHolder> {


    private Context mcontext;
    private List<DeliveryShipFinalOrders> deliveryShipFinalOrderslist;

    public DeliveryShipOrderViewAdapter(Context context, List<DeliveryShipFinalOrders> deliveryShipFinalOrderslist) {
        this.deliveryShipFinalOrderslist = deliveryShipFinalOrderslist;
        this.mcontext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.shiporderview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final DeliveryShipFinalOrders deliveryShipFinalOrders = deliveryShipFinalOrderslist.get(position);
        holder.dishname.setText(position+1+"."+deliveryShipFinalOrders.getDishName());
        holder.price.setText("Giá:" + deliveryShipFinalOrders.getDishPrice()+"đ");
        holder.quantity.setText("× " + deliveryShipFinalOrders.getDishQuantity());
        holder.totalprice.setText("Tổng tiền:" + deliveryShipFinalOrders.getTotalPrice()+"đ");
    }

    @Override
    public int getItemCount() {
        return deliveryShipFinalOrderslist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView dishname, price, totalprice, quantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dishname = itemView.findViewById(R.id.dish2);
            price = itemView.findViewById(R.id.Price2);
            totalprice = itemView.findViewById(R.id.Total2);
            quantity = itemView.findViewById(R.id.Qty2);
        }
    }
}
