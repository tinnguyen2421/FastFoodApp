package com.example.appfood_by_tinnguyen2421.Customerr.CustomerAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfood_by_tinnguyen2421.Customerr.CustomerModel.CustomerFinalOrders;
import com.example.appfood_by_tinnguyen2421.R;

import java.util.List;
//May not be copied in any form
//Copyright belongs to Nguyen TrongTin. contact: email:tinnguyen2421@gmail.com
public class CustomerTrackAdapter extends RecyclerView.Adapter<CustomerTrackAdapter.ViewHolder> {

    private Context context;
    private List<CustomerFinalOrders> customerFinalOrderslist;

    public CustomerTrackAdapter(Context context, List<CustomerFinalOrders> customerFinalOrderslist) {
        this.customerFinalOrderslist = customerFinalOrderslist;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.customer_trackorder, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final CustomerFinalOrders customerFinalOrders = customerFinalOrderslist.get(position);
        holder.Dishname.setText(customerFinalOrders.getDishName());
        holder.Quantity.setText(customerFinalOrders.getDishQuantity() + "× ");
        holder.Totalprice.setText( customerFinalOrders.getTotalPrice()+"đ");

    }

    @Override
    public int getItemCount() {
        return customerFinalOrderslist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Dishname, Quantity, Totalprice;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Dishname = itemView.findViewById(R.id.dishnm);
            Quantity = itemView.findViewById(R.id.dishqty);
            Totalprice = itemView.findViewById(R.id.totRS);
        }
    }
}
