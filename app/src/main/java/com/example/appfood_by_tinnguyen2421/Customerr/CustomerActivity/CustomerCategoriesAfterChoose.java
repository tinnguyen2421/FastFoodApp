package com.example.appfood_by_tinnguyen2421.Customerr.CustomerActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.appfood_by_tinnguyen2421.Chef.ChefModel.UpdateDishModel;
import com.example.appfood_by_tinnguyen2421.Customerr.CustomerAdapter.CustomerHomeAdapter;
import com.example.appfood_by_tinnguyen2421.Customerr.CustomerModel.Customer;
import com.example.appfood_by_tinnguyen2421.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

//May not be copied in any form
//Copyright belongs to Nguyen TrongTin. contact: email:tinnguyen2421@gmail.com
public class CustomerCategoriesAfterChoose extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
RecyclerView rcvHome;
    SwipeRefreshLayout swipeRefreshLayout;
    DatabaseReference dataaa, databaseReference;
    private CustomerHomeAdapter adapter;
    private List<UpdateDishModel> updateDishModelList;


    String State, City, Sub, Matl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_after_choose);
        rcvHome=findViewById(R.id.rcvfood);
        rcvHome.setHasFixedSize(true);
        updateDishModelList = new ArrayList<>();
        Intent getdata = getIntent();
        Matl = getdata.getStringExtra("matl");
        GridLayoutManager gridLayoutManager = new GridLayoutManager(CustomerCategoriesAfterChoose.this,2);
        rcvHome.setLayoutManager(gridLayoutManager);
        swipeRefreshLayout = findViewById(R.id.swipelayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimaryDark, R.color.green);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                dataaa = FirebaseDatabase.getInstance().getReference("Customer").child(userid);
                dataaa.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Customer cust = dataSnapshot.getValue(Customer.class);
                        State = cust.getState();
                        City = cust.getCity();
                        Sub = cust.getSuburban();

                        customermenu();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
    private void customermenu() {

        swipeRefreshLayout.setRefreshing(true);
        databaseReference = FirebaseDatabase.getInstance().getReference("FoodSupplyDetails").child(State).child(City).child(Sub);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                updateDishModelList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        UpdateDishModel updateDishModel = snapshot1.getValue(UpdateDishModel.class);
                        if(Matl.equals(updateDishModel.getCateID())) {
                            updateDishModelList.add(updateDishModel);
                        }
                    }
                }
                adapter = new CustomerHomeAdapter(CustomerCategoriesAfterChoose.this, updateDishModelList);
                rcvHome.setAdapter(adapter);
                swipeRefreshLayout.setRefreshing(false);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }


    @Override
    public void onRefresh() {
        customermenu();

    }
}