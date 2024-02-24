package com.example.appfood_by_tinnguyen2421.Customerr.CustomerFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appfood_by_tinnguyen2421.Customerr.CustomerActivity.PayableOrders;
import com.example.appfood_by_tinnguyen2421.Customerr.CustomerActivity.PendingOrders;
import com.example.appfood_by_tinnguyen2421.R;
//May not be copied in any form
//Copyright belongs to Nguyen TrongTin. contact: email:tinnguyen2421@gmail.com
public class CustomerOrderFragment extends Fragment {

    TextView Pendingorder, Payableorder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Đơn hàng của bạn ");
        View v = inflater.inflate(R.layout.fragment_customerorder, null);

        Pendingorder = (TextView) v.findViewById(R.id.pendingorder);
        Payableorder = (TextView) v.findViewById(R.id.payableorder);

        Pendingorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), PendingOrders.class);
                startActivity(intent);
            }
        });


        Payableorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), PayableOrders.class);
                startActivity(i);
            }
        });
        return v;
    }
}