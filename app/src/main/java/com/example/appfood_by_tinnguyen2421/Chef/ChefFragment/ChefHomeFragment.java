package com.example.appfood_by_tinnguyen2421.Chef.ChefFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.appfood_by_tinnguyen2421.Categories;
import com.example.appfood_by_tinnguyen2421.Chef.ChefActivity.Chef_PostCate;
import com.example.appfood_by_tinnguyen2421.Chef.ChefAdapter.ChefCateAdapter;
import com.example.appfood_by_tinnguyen2421.Chef.ChefModel.Chef;
import com.example.appfood_by_tinnguyen2421.Chef.ChefModel.UpdateDishModel;
import com.example.appfood_by_tinnguyen2421.MainMenu;
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
public class ChefHomeFragment extends Fragment {



    RecyclerView RecyclerDish,RecyclerCate;
    private List<UpdateDishModel> updateDishModelList;
private List<Categories> categoriesList;

    private ChefCateAdapter adapterCate;
    DatabaseReference dataaa;
    FloatingActionButton add;
    private String State, City, Sub;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chef_home, null);
        getActivity().setTitle("Giờ ăn tới rồi");

        setHasOptionsMenu(true);
        RecyclerCate=v.findViewById(R.id.Recycle_cate);
        //RecyclerDish = v.findViewById(R.id.Recycle_menu);

        //RecyclerDish.setHasFixedSize(true);
        //RecyclerDish.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerCate.setLayoutManager(new LinearLayoutManager(getContext()));
        updateDishModelList = new ArrayList<>();
        categoriesList=new ArrayList<>();
        add=v.findViewById(R.id.themMoi);

        String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        dataaa = FirebaseDatabase.getInstance().getReference("Chef").child(userid);
        dataaa.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Chef chefc = dataSnapshot.getValue(Chef.class);
                if(chefc !=null)
                    State = chefc.getState();
                    City = chefc.getCity();
                    Sub = chefc.getSuburban();
                    add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(getContext(), Chef_PostCate.class));
                      }
                    });
                    chefCate();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return v;
    }

    private void chefCate() {

        String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Categories").child(State).child(City).child(Sub).child(userid);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                categoriesList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Categories categories = snapshot.getValue(Categories.class);
                    categoriesList.add(categories);

                }
                adapterCate = new ChefCateAdapter(getContext(), categoriesList);
                RecyclerCate.setAdapter(adapterCate);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }




    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.logout, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int idd = item.getItemId();
        if (idd == R.id.LogOut) {
            Logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void Logout() {

        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getActivity(), MainMenu.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
        startActivity(intent);

    }



}
