package com.example.appfood_by_tinnguyen2421.Chef.ChefAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfood_by_tinnguyen2421.Chef.ChefModel.ChefPendingOrders;
import com.example.appfood_by_tinnguyen2421.R;


import java.text.DecimalFormat;
import java.util.List;
//May not be copied in any form
//Copyright belongs to Nguyen TrongTin. contact: email:tinnguyen2421@gmail.com
public class ChefOrderDishesAdapter extends RecyclerView.Adapter<ChefOrderDishesAdapter.ViewHolder> {


    private Context mcontext;
    private List<ChefPendingOrders> chefPendingOrderslist;

    public ChefOrderDishesAdapter(Context context, List<ChefPendingOrders> chefPendingOrderslist) {
        this.chefPendingOrderslist = chefPendingOrderslist;
        this.mcontext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.chef_order_dishes, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final ChefPendingOrders chefPendingOrders = chefPendingOrderslist.get(position);
        holder.dishname.setText(position+1+"."+chefPendingOrders.getDishName());
        //holder.price.setText("Giá: " + chefPendingOrders.getPrice()+"đ");
        if (chefPendingOrders != null && chefPendingOrders.getPrice() != null) {
            String priceString = chefPendingOrders.getPrice();

            // Loại bỏ dấu phẩy và khoảng trắng từ chuỗi
            String priceWithoutComma = priceString.replace(",", "").trim();

            try {
                // Chuyển đổi chuỗi thành số và định dạng lại
                double parsedNumber = Double.parseDouble(priceWithoutComma);

                // Sử dụng parsedNumber trong giao diện người dùng với định dạng số
                DecimalFormat decimalFormat = new DecimalFormat("#,###,###,###");
                String formattedPrice = decimalFormat.format(parsedNumber);

                holder.price.setText("Giá: " + formattedPrice + "đ");
            } catch (NumberFormatException e) {
                // Xử lý trường hợp không thể chuyển đổi thành số
                e.printStackTrace();
            }
        }
        holder.quantity.setText("× " + chefPendingOrders.getDishQuantity());
        //holder.totalprice.setText("Tổng tiền: " + chefPendingOrders.getTotalPrice()+"đ");
        if (chefPendingOrders != null && chefPendingOrders.getTotalPrice() != null) {
            String priceString = chefPendingOrders.getTotalPrice();

            // Loại bỏ dấu phẩy và khoảng trắng từ chuỗi
            String priceWithoutComma = priceString.replace(",", "").trim();

            try {
                // Chuyển đổi chuỗi thành số và định dạng lại
                double parsedNumber = Double.parseDouble(priceWithoutComma);

                // Sử dụng parsedNumber trong giao diện người dùng với định dạng số
                DecimalFormat decimalFormat = new DecimalFormat("#,###,###,###");
                String formattedPrice = decimalFormat.format(parsedNumber);

                holder.totalprice.setText("Tổng tiền : " + formattedPrice + "đ");
            } catch (NumberFormatException e) {
                // Xử lý trường hợp không thể chuyển đổi thành số
                e.printStackTrace();
            }
        }

    }

    @Override
    public int getItemCount() {
        return chefPendingOrderslist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView dishname, price, totalprice, quantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            dishname = itemView.findViewById(R.id.DN);
            price = itemView.findViewById(R.id.PR);
            totalprice = itemView.findViewById(R.id.TR);
            quantity = itemView.findViewById(R.id.QY);
        }
    }
}
