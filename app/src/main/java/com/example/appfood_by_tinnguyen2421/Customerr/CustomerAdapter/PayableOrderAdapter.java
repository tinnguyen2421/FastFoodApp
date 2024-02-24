package com.example.appfood_by_tinnguyen2421.Customerr.CustomerAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfood_by_tinnguyen2421.Customerr.CustomerModel.CustomerPaymentOrders;
import com.example.appfood_by_tinnguyen2421.R;


import java.text.DecimalFormat;
import java.util.List;
//May not be copied in any form
//Copyright belongs to Nguyen TrongTin. contact: email:tinnguyen2421@gmail.com
public class PayableOrderAdapter extends RecyclerView.Adapter<PayableOrderAdapter.ViewHolder> {

    private Context context;
    private List<CustomerPaymentOrders> customerPaymentOrderslist;

    public PayableOrderAdapter(Context context, List<CustomerPaymentOrders> customerPendingOrderslist) {
        this.customerPaymentOrderslist = customerPendingOrderslist;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.customer_payableorder, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final CustomerPaymentOrders customerPaymentOrders = customerPaymentOrderslist.get(position);
        holder.Dishname.setText(position+1+"."+customerPaymentOrders.getDishName());
        //holder.Price.setText("Giá:" + customerPaymentOrders.getDishPrice()+"đ");
        if (customerPaymentOrders != null && customerPaymentOrders.getDishPrice() != null) {
            String priceString = customerPaymentOrders.getDishPrice();
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
        holder.Quantity.setText("× " + customerPaymentOrders.getDishQuantity());
        if (customerPaymentOrders != null && customerPaymentOrders.getTotalPrice() != null) {
            String totalPriceString = customerPaymentOrders.getTotalPrice();
            // Loại bỏ dấu phẩy và khoảng trắng từ chuỗi
            String totalPriceWithoutComma = totalPriceString.replace(",", "").trim();
            try {
                // Chuyển đổi chuỗi thành số và định dạng lại
                double parsedTotalPrice = Double.parseDouble(totalPriceWithoutComma);
                // Sử dụng parsedTotalPrice trong giao diện người dùng với định dạng số
                DecimalFormat decimalFormat = new DecimalFormat("#,###,###,###");
                String formattedTotalPrice = decimalFormat.format(parsedTotalPrice);
                holder.Totalprice.setText("Tổng tiền: " + formattedTotalPrice + "đ");
            } catch (NumberFormatException e) {
                // Xử lý trường hợp không thể chuyển đổi thành số
                e.printStackTrace();
            }
        }
        //holder.Totalprice.setText("Tổng tiền: " + customerPaymentOrders.getTotalPrice()+"đ");
    }

    @Override
    public int getItemCount() {
        return customerPaymentOrderslist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView Dishname, Price, Quantity, Totalprice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Dishname = itemView.findViewById(R.id.dish);
            Price = itemView.findViewById(R.id.pri);
            Quantity = itemView.findViewById(R.id.qt);
            Totalprice = itemView.findViewById(R.id.Tot);
        }
    }
}
