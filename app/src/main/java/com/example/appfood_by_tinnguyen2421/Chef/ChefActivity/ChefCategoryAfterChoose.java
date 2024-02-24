package com.example.appfood_by_tinnguyen2421.Chef.ChefActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.example.appfood_by_tinnguyen2421.Chef.ChefAdapter.ChefhomeAdapter;
import com.example.appfood_by_tinnguyen2421.Chef.ChefModel.Chef;
import com.example.appfood_by_tinnguyen2421.Chef.ChefModel.UpdateDishModel;
import com.example.appfood_by_tinnguyen2421.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
public class ChefCategoryAfterChoose extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    RecyclerView rcvHome;
    List<UpdateDishModel> updateDishModelList;
    ChefhomeAdapter adapter;
    DatabaseReference dataaa, databaseReference;
    String Matl;
    SwipeRefreshLayout swipeRefreshLayout;
    String State,City,Sub;
    FloatingActionButton addDishes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_category_after_choose);
        rcvHome = findViewById(R.id.rcvHomee);
        updateDishModelList = new ArrayList<>();
        rcvHome.setHasFixedSize(true);
        Intent getdata = getIntent();
        Matl = getdata.getStringExtra("matl");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ChefCategoryAfterChoose.this);
        rcvHome.setLayoutManager(linearLayoutManager);
        swipeRefreshLayout = findViewById(R.id.swipelayout);
        swipeRefreshLayout = findViewById(R.id.swipelayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimaryDark, R.color.green);
        addDishes=findViewById(R.id.themMoi);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                dataaa = FirebaseDatabase.getInstance().getReference("Chef").child(userid);
                dataaa.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Chef chef = dataSnapshot.getValue(Chef.class);
                        State = chef.getState();
                        City = chef.getCity();
                        Sub = chef.getSuburban();
                        addDishes.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                startActivity(new Intent(ChefCategoryAfterChoose.this, Chef_PostDish.class));
                            }
                        });
                        Chefmenu();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
    private void Chefmenu() {

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
                adapter = new ChefhomeAdapter(ChefCategoryAfterChoose.this, updateDishModelList);
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

    }
}