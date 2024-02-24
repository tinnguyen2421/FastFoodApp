package com.example.appfood_by_tinnguyen2421.Chef.ChefActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appfood_by_tinnguyen2421.Chef.ChefModel.Chef;
import com.example.appfood_by_tinnguyen2421.Chef.ChefModel.FoodSupplyDetails;
import com.example.appfood_by_tinnguyen2421.R;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.ArrayList;
import java.util.UUID;



public class Chef_PostDish extends AppCompatActivity {
//May not be copied in any form
//Copyright belongs to Nguyen TrongTin. contact: email:tinnguyen2421@gmail.com

    ImageButton imageButton;
    Button post_dish;
    EditText Dishes;
    TextInputLayout desc, qty, pri;
    String cateID,description, quantity, price, dishes;
    Uri imageuri;
    //ex
    private ArrayList<String> categoryList;

    Spinner spinner;
    //ex
    private Uri mCropimageuri;
    FirebaseStorage storage;
    StorageReference storageReference;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    DatabaseReference dataaa;
    FirebaseAuth FAuth;
    StorageReference ref;
    String ChefId;
    String RandomUId;
    String State, City, Sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef__post_dish);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        spinner=findViewById(R.id.spinnerID);
        Dishes = (EditText) findViewById(R.id.dishes_ID);
        desc = (TextInputLayout) findViewById(R.id.nameCate);
        qty = (TextInputLayout) findViewById(R.id.Mo_ta);
        pri = (TextInputLayout) findViewById(R.id.price);
        post_dish = (Button) findViewById(R.id.postCate);
        FAuth = FirebaseAuth.getInstance();
        categoryList=new ArrayList<String>();
        databaseReference = firebaseDatabase.getInstance().getReference("FoodSupplyDetails");
        try {
            String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            dataaa = firebaseDatabase.getInstance().getReference("Chef").child(userid);
            dataaa.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Chef chefc = dataSnapshot.getValue(Chef.class);

                    State = chefc.getState();
                    City = chefc.getCity();
                    Sub = chefc.getSuburban();
                    showdataspinner();
                    imageButton = (ImageButton) findViewById(R.id.imageupload);
                    imageButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onSelectImageClick(v);
                        }
                    });


                    post_dish.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            cateID=spinner.getSelectedItem().toString().trim();
                            dishes = Dishes.getText().toString().trim();
                            description = desc.getEditText().getText().toString().trim();
                            quantity = qty.getEditText().getText().toString().trim();
                            price = pri.getEditText().getText().toString().trim();

                            if (isValid()) {
                                uploadImage();
                            }
                        }
                    });

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        } catch (Exception e) {

            Log.e("Errrrrr: ", e.getMessage());
        }

    }
    // lấy dữ liệu từ RealtimeDb xuống để vào spinner
    public void showdataspinner()
    {
        RandomUId = getIntent().getStringExtra("RandomUID");
        String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        dataaa = firebaseDatabase.getInstance().getReference("Chef").child(userid);
        dataaa.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Chef chefc = dataSnapshot.getValue(Chef.class);
                State = chefc.getState();
                City = chefc.getCity();
                Sub = chefc.getSuburban();
                DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("Categories").child(State).child(City).child(Sub);
                databaseReference1.child(userid).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        categoryList.clear();
                        for (DataSnapshot item: snapshot.getChildren())
                        {
                            categoryList.add(item.child("CateID").getValue(String.class));
                        }
                        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(Chef_PostDish.this, com.hbb20.R.layout.support_simple_spinner_dropdown_item,categoryList);
                        spinner.setAdapter(arrayAdapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });





            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private boolean isValid() {
        
        desc.setError("");
        desc.setErrorEnabled(false);
        desc.setError("");
        qty.setErrorEnabled(false);
        qty.setError("");
        pri.setErrorEnabled(false);
        pri.setError("");

        boolean isValiDescription = false, isValidPrice = false, isvalidQuantity = false, isvalid = false;
        if (TextUtils.isEmpty(description)) {
            desc.setErrorEnabled(true);
            desc.setError("Chi tiết món ăn không được để trống");
        } else if (description.length()>20) {
            desc.setErrorEnabled(true);
            desc.setError("Chi tiết món ăn không được quá 20 kí tự");
        }
        else if (description.length()<3) {
            desc.setErrorEnabled(true);
            desc.setError("Chi tiết món ăn không được ít hơn 3 kí tự");
        }
            else {
            desc.setError(null);
            isValiDescription = true;
        }
        if (TextUtils.isEmpty(quantity)) {
            qty.setErrorEnabled(true);
            qty.setError("Số lượng không được để trống");
        } else {
            double qtyy = Double.parseDouble(quantity);
            if (qtyy > 1000) {
                qty.setErrorEnabled(true);
                qty.setError("Số lượng không vượt quá 1000 ");
            } else if (qtyy <1){
                qty.setErrorEnabled(true);
                qty.setError("Số lượng không được nhỏ hơn 1 ");
            }
            else
            {
                isvalidQuantity = true;
            }
        }

        if (TextUtils.isEmpty(price)) {
            pri.setErrorEnabled(true);
            pri.setError("Giá không được để trống");
        } else {
            double priceValue = Double.parseDouble(price);
            if (priceValue > 1000000) {
                pri.setErrorEnabled(true);
                pri.setError("Giá món ăn không được lớn hơn 1.000.000 vnđ");
            } else if (priceValue<1000) {
                pri.setErrorEnabled(true);
                pri.setError("Giá món ăn không được nhỏ hơn 1.000 vnđ");

            } else {
                isValidPrice = true;
            }
        }
        isvalid = (isValiDescription && isvalidQuantity && isValidPrice) ? true : false;
        return isvalid;
    }

    private void uploadImage() {

        if (imageuri != null) {
            final ProgressDialog progressDialog = new ProgressDialog(Chef_PostDish.this);
            progressDialog.setTitle("Đang tải ảnh...");
            progressDialog.show();
            RandomUId = UUID.randomUUID().toString();
            ref = storageReference.child(RandomUId);
            ChefId = FirebaseAuth.getInstance().getCurrentUser().getUid();

//tải hình ảnh lên Firebase Storage thông qua đối tượng ref
            ref.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {


                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            FoodSupplyDetails info = new FoodSupplyDetails(cateID,dishes, quantity, price, description, String.valueOf(uri), RandomUId, ChefId);
                            firebaseDatabase.getInstance().getReference("FoodSupplyDetails").child(State).child(City).child(Sub).child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(RandomUId)
                                    .setValue(info).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressDialog.dismiss();
                                    Toast.makeText(Chef_PostDish.this, "Đăng món thành công", Toast.LENGTH_SHORT).show();//Chuyển từ postdish sang home
                                }
                            });
                        }
                    });

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    progressDialog.dismiss();
                    Toast.makeText(Chef_PostDish.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                    double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                    progressDialog.setMessage("Tải lên " + (int) progress + "%");
                    progressDialog.setCanceledOnTouchOutside(false);
                }
            });
        }
        else
        {
            final ProgressDialog progressDialog = new ProgressDialog(Chef_PostDish.this);
            progressDialog.setTitle("Hình ảnh không được để trống...");
            progressDialog.show();
        }

    }


    private void onSelectImageClick(View v) {

        CropImage.startPickImageActivity(this);
    }

    @Override
    @SuppressLint("NewApi")
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            imageuri = CropImage.getPickImageResultUri(this, data);

            if (CropImage.isReadExternalStoragePermissionsRequired(this, imageuri)) {
                mCropimageuri = imageuri;
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);

            } else {

                startCropImageActivity(imageuri);
            }
        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                ((ImageButton) findViewById(R.id.imageupload)).setImageURI(result.getUri());
                Toast.makeText(this, "Cắt ảnh thành công", Toast.LENGTH_SHORT).show();
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toast.makeText(this, "Cắt ảnh thất bại" + result.getError(), Toast.LENGTH_SHORT).show();
            }
        }


        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (mCropimageuri != null && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startCropImageActivity(mCropimageuri);
        } else {
            Toast.makeText(this, "cancelling,required permission not granted", Toast.LENGTH_SHORT).show();
        }
    }

    private void startCropImageActivity(Uri imageuri) {

        CropImage.activity(imageuri)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setMultiTouchEnabled(true)
                .start(this);


    }
}


