package com.example.appfood_by_tinnguyen2421.DeliveryPerson.DeliveryAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfood_by_tinnguyen2421.DeliveryPerson.DeliveryActivity.DeliveryShipOrderView;
import com.example.appfood_by_tinnguyen2421.DeliveryPerson.DeliveryActivity.Delivery_ShippingOrder;
import com.example.appfood_by_tinnguyen2421.DeliveryPerson.DeliveryModel.DeliveryShipFinalOrders1;
import com.example.appfood_by_tinnguyen2421.R;
import com.example.appfood_by_tinnguyen2421.SendNotification.APIService;
import com.example.appfood_by_tinnguyen2421.SendNotification.Client;
import com.example.appfood_by_tinnguyen2421.SendNotification.Data;
import com.example.appfood_by_tinnguyen2421.SendNotification.MyResponse;
import com.example.appfood_by_tinnguyen2421.SendNotification.NotificationSender;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
//May not be copied in any form
//Copyright belongs to Nguyen TrongTin. contact: email:tinnguyen2421@gmail.com
public class DeliveryShipOrderFragmentAdapter extends RecyclerView.Adapter<DeliveryShipOrderFragmentAdapter.ViewHolder> {

    private Context context;
    private List<DeliveryShipFinalOrders1> deliveryShipFinalOrders1list;
    private APIService apiService;


    public DeliveryShipOrderFragmentAdapter(Context context, List<DeliveryShipFinalOrders1> deliveryShipFinalOrders1list) {
        this.deliveryShipFinalOrders1list = deliveryShipFinalOrders1list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.delivery_shiporders, parent, false);
        apiService = Client.getClient("https://fcm.googleapis.com/").create(APIService.class);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final DeliveryShipFinalOrders1 deliveryShipFinalOrders1 = deliveryShipFinalOrders1list.get(position);
        holder.Stt.setText("Đơn hàng số:"+position+1);
        holder.Address.setText("Địa chỉ:"+deliveryShipFinalOrders1.getAddress());
        holder.grandtotalprice.setText("Tổng đơn hàng:" + deliveryShipFinalOrders1.getGrandTotalPrice()+"đ");
        holder.mobilenumber.setText("Số điện thoại:" + deliveryShipFinalOrders1.getMobileNumber());
        final String random = deliveryShipFinalOrders1.getRandomUID();
        final String userid = deliveryShipFinalOrders1.getUserId();
        holder.Vieworder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DeliveryShipOrderView.class);
                intent.putExtra("RandomUID", random);
                context.startActivity(intent);
            }
        });

        holder.ShipOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase.getInstance().getReference("CustomerFinalOrders").child(userid).child(random).child("OtherInformation").child("Status").setValue("Đơn hàng của bạn đang trên đường giao đến...").addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        FirebaseDatabase.getInstance().getReference().child("Tokens").child(userid).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                String usertoken = dataSnapshot.getValue(String.class);
                                sendNotifications(usertoken, "Xác nhận đơn hàng", "Đơn hàng của bạn đã được Người giao hàng nhận, Anh ấy đang trên đường đến", "DeliverOrder");
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                }).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Intent intent = new Intent(context, Delivery_ShippingOrder.class);
                        intent.putExtra("RandomUID",random);
                        context.startActivity(intent);
                    }
                });

            }
        });

    }

    private void sendNotifications(String usertoken, String title, String message, String order) {

        Data data = new Data(title, message, order);
        NotificationSender sender = new NotificationSender(data, usertoken);
        apiService.sendNotification(sender).enqueue(new Callback<MyResponse>() {
            @Override
            public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                if (response.code() == 200) {
                    if (response.body().success != 1) {
                        Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<MyResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return deliveryShipFinalOrders1list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView Address, grandtotalprice, mobilenumber,Stt;
        Button Vieworder, ShipOrder;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Stt = itemView.findViewById(R.id.STT111);
            Address = itemView.findViewById(R.id.ad2);
            mobilenumber = itemView.findViewById(R.id.MB2);
            grandtotalprice = itemView.findViewById(R.id.TP2);
            Vieworder = itemView.findViewById(R.id.view2);
            ShipOrder = itemView.findViewById(R.id.ship2);
        }
    }
}
