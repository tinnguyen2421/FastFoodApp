package com.example.appfood_by_tinnguyen2421.DeliveryPerson.DeliveryAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.appfood_by_tinnguyen2421.DeliveryPerson.DeliveryModel.DeliveryShipOrders;
import com.example.appfood_by_tinnguyen2421.R;

import java.util.List;
//May not be copied in any form
//Copyright belongs to Nguyen TrongTin. contact: email:tinnguyen2421@gmail.com
public class DeliveryPendingOrderViewAdapter extends RecyclerView.Adapter<DeliveryPendingOrderViewAdapter.ViewHolder> {


    private Context mcontext;
    private List<DeliveryShipOrders> deliveryShipOrderslist;

    public DeliveryPendingOrderViewAdapter(Context context, List<DeliveryShipOrders> deliveryShipOrderslist) {
        this.deliveryShipOrderslist = deliveryShipOrderslist;
        this.mcontext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.delivery_pendingorder, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final DeliveryShipOrders deliveryShipOrders = deliveryShipOrderslist.get(position);
        holder.dishname.setText(position+1+"."+deliveryShipOrders.getDishName());
        holder.price.setText("Giá:" + deliveryShipOrders.getDishPrice()+"đ");
        holder.quantity.setText("× " + deliveryShipOrders.getDishQuantity());
        holder.totalprice.setText("Tổng tiền:" + deliveryShipOrders.getTotalPrice()+"đ");
    }

    @Override
    public int getItemCount() {
        return deliveryShipOrderslist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView dishname, price, totalprice, quantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dishname = itemView.findViewById(R.id.Dish1);
            price = itemView.findViewById(R.id.Price1);
            totalprice = itemView.findViewById(R.id.Total1);
            quantity = itemView.findViewById(R.id.Quantity1);
        }
    }
}
