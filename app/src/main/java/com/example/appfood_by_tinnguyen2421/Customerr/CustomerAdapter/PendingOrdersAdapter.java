package com.example.appfood_by_tinnguyen2421.Customerr.CustomerAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfood_by_tinnguyen2421.Customerr.CustomerModel.CustomerPendingOrders;
import com.example.appfood_by_tinnguyen2421.R;

import java.text.DecimalFormat;
import java.util.List;
//May not be copied in any form
//Copyright belongs to Nguyen TrongTin. contact: email:tinnguyen2421@gmail.com
public class PendingOrdersAdapter extends RecyclerView.Adapter<PendingOrdersAdapter.ViewHolder> {

    private Context context;
    private List<CustomerPendingOrders> customerPendingOrderslist;

    public PendingOrdersAdapter(Context context, List<CustomerPendingOrders> customerPendingOrderslist) {
        this.customerPendingOrderslist = customerPendingOrderslist;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pending_order_dishes, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final CustomerPendingOrders customerPendingOrders = customerPendingOrderslist.get(position);
        holder.Dishname.setText(position+1+"."+customerPendingOrders.getDishName());
        //holder.Price.setText("Giá:  " + customerPendingOrders.getPrice()+"đ");
        if (customerPendingOrders != null && customerPendingOrders.getPrice() != null) {
            String priceString = customerPendingOrders.getPrice();
            // Loại bỏ dấu phẩy và khoảng trắng từ chuỗi
            String priceWithoutComma = priceString.replace(",", "").trim();
            try {
                // Chuyển đổi chuỗi thành số và định dạng lại
                double parsedNumber = Double.parseDouble(priceWithoutComma);
                // Sử dụng parsedNumber trong giao diện người dùng với định dạng số
                DecimalFormat decimalFormat = new DecimalFormat("#,###,###,###");
                String formattedPrice = decimalFormat.format(parsedNumber);
                holder.Price.setText("Giá: " + formattedPrice + "đ");
            } catch (NumberFormatException e) {
                // Xử lý trường hợp không thể chuyển đổi thành số
                e.printStackTrace();
            }
        }
        holder.Quantity.setText("× " + customerPendingOrders.getDishQuantity());
        if (customerPendingOrders != null && customerPendingOrders.getPrice() != null) {
            String priceString = customerPendingOrders.getTotalPrice();
            // Loại bỏ dấu phẩy và khoảng trắng từ chuỗi
            String priceWithoutComma = priceString.replace(",", "").trim();
            try {
                // Chuyển đổi chuỗi thành số và định dạng lại
                double parsedNumber = Double.parseDouble(priceWithoutComma);
                // Sử dụng parsedNumber trong giao diện người dùng với định dạng số
                DecimalFormat decimalFormat = new DecimalFormat("#,###,###,###");
                String formattedPrice = decimalFormat.format(parsedNumber);
                holder.Totalprice.setText("Tổng tiền: " + formattedPrice + "đ");
            } catch (NumberFormatException e) {
                // Xử lý trường hợp không thể chuyển đổi thành số
                e.printStackTrace();
            }
        }
        //holder.Totalprice.setText("tổng tiền: " + customerPendingOrders.getTotalPrice()+"đ");

    }

    @Override
    public int getItemCount() {
        return customerPendingOrderslist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView Dishname, Price, Quantity, Totalprice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Dishname = itemView.findViewById(R.id.Dishh);
            Price = itemView.findViewById(R.id.pricee);
            Quantity = itemView.findViewById(R.id.qtyy);
            Totalprice = itemView.findViewById(R.id.total);

        }
    }
}
