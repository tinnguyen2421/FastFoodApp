package com.example.appfood_by_tinnguyen2421.CustomerAccount;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appfood_by_tinnguyen2421.R;
import com.example.appfood_by_tinnguyen2421.ReusableCodeForAll;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hbb20.CountryCodePicker;

import java.util.ArrayList;
import java.util.HashMap;

//May not be copied in any form
//Copyright belongs to Nguyen TrongTin. contact: email:tinnguyen2421@gmail.com
public class CustomerRegisteration extends AppCompatActivity {

    String[] TP_HCM = {"Q1", "Q2", "Q3","Q4","Q5","Q6","Q7","Q8","Q9","Q10","Q11","Q12","Quận Tân Bình","Quận Bình Tân","Quận Phú Nhuận","Quận Bình Thạnh"};
    String[] TP_HàNội = {"Quận Hoàn Kiếm", "Quận Ba Đình", "Quận Đống Đa"};
    String[] TiềnGiang = {"Huyện Chợ Gạo", "Huyện Tân Phú Đông", "Huyện Gò Công"};


    String[] Q1 = {"Phường Bến Nghé", "Phường Bến Thành", "Phường Cô Giang", "Phường Cầu Kho", "Phường Cầu Ông Lãnh", "Phường Nguyễn Cư Trinh", "Phường Nguyễn Thái Bình", "Phường Phạm Ngũ Lão",
            "Phường Tân Định", "Phường ĐaKao"};


    String[] Q2 = {"Phường An Khánh", "Phường An Lợi Đông", " Phường An Phú", "PBình An", "Phường Bình Khánh","Phường Bình Trưng Đông","Phường Bình Trưng Tây","Phường Cát Lái","Phường Thạnh Mỹ Lợi","Phường Thảo Điền","Phường Thủ Thiêm"};
    String[] Q3 = {"P1", "P2", "P3", "P4", "P5", "P9", "P10", "P11", "P12", "P13", "P14"};

    TextInputLayout fname, lname, localadd, emaill, pass, cmpass, Mobileno;
    Spinner CitySpin, Distric, Ward;
    Button Signin, Email, Phone;
    FirebaseAuth FAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    String statee;
    String cityy;
    String suburban;
    String email;
    String password;
    String firstname;
    String lastname;
    String Localaddress;
    String confirmpass;
    String mobileno;
    String role = "Customer";
    CountryCodePicker Cpp;
    ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_registeration);
        try {
            mDialog = new ProgressDialog(CustomerRegisteration.this);
            mDialog.setMessage("Đang đăng kí, vui lòng đợi...");
            mDialog.setCancelable(false);
            mDialog.setCanceledOnTouchOutside(false);
            fname = (TextInputLayout) findViewById(R.id.Fname);
            lname = (TextInputLayout) findViewById(R.id.Lname);
            localadd = (TextInputLayout) findViewById(R.id.Localaddress);
            emaill = (TextInputLayout) findViewById(R.id.Emailid);
            pass = (TextInputLayout) findViewById(R.id.Password);
            cmpass = (TextInputLayout) findViewById(R.id.confirmpass);
            Signin = (Button) findViewById(R.id.button);
            CitySpin = (Spinner) findViewById(R.id.Statee);
            Distric = (Spinner) findViewById(R.id.Citys);
            Ward = (Spinner) findViewById(R.id.Suburban);
            Mobileno = (TextInputLayout) findViewById(R.id.Mobilenumber);
            Cpp = (CountryCodePicker) findViewById(R.id.CountryCode);
            Cpp.setDefaultCountryUsingNameCode("VN");
            Cpp.resetToDefaultCountry();
            Email = (Button) findViewById(R.id.emaill);
            Phone = (Button) findViewById(R.id.phone);

            CitySpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Object value = parent.getItemAtPosition(position);
                    statee = value.toString().trim();
                    if (statee.equals("Thành Phố Hồ Chí Minh")) {
                        ArrayList<String> list = new ArrayList<>();
                        for (String text : TP_HCM) {
                            list.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(CustomerRegisteration.this, android.R.layout.simple_spinner_item, list);

                        Distric.setAdapter(arrayAdapter);
                    }
                    if (statee.equals("Thành Phố Hà Nội")) {
                        ArrayList<String> list = new ArrayList<>();
                        for (String text : TP_HàNội) {
                            list.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(CustomerRegisteration.this, android.R.layout.simple_spinner_item, list);

                        Distric.setAdapter(arrayAdapter);
                    }
                    if (statee.equals("Tỉnh Tiền Giang")) {
                        ArrayList<String> list = new ArrayList<>();
                        for (String text : TiềnGiang) {
                            list.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(CustomerRegisteration.this, android.R.layout.simple_spinner_item, list);

                        Distric.setAdapter(arrayAdapter);
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            Distric.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Object value = parent.getItemAtPosition(position);
                    cityy = value.toString().trim();
                    if (cityy.equals("Q1")) {
                        ArrayList<String> listt = new ArrayList<>();
                        for (String text : Q1) {
                            listt.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(CustomerRegisteration.this, android.R.layout.simple_spinner_item, listt);
                        Ward.setAdapter(arrayAdapter);
                    }

                    if (cityy.equals("Q2")) {
                        ArrayList<String> listt = new ArrayList<>();
                        for (String text : Q2) {
                            listt.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(CustomerRegisteration.this, android.R.layout.simple_spinner_item, listt);
                        Ward.setAdapter(arrayAdapter);
                    }

                    if (cityy.equals("Q3")) {
                        ArrayList<String> listt = new ArrayList<>();
                        for (String text : Q3) {
                            listt.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(CustomerRegisteration.this, android.R.layout.simple_spinner_item, listt);
                        Ward.setAdapter(arrayAdapter);
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            Ward.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Object value = parent.getItemAtPosition(position);
                    suburban = value.toString().trim();
                    if (suburban.equals("Phường Bến Nghé")) {
                        ArrayList<String> listt = new ArrayList<>();
                        for (String text : Q1) {
                            listt.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(CustomerRegisteration.this, android.R.layout.simple_spinner_item, listt);
                        Ward.setAdapter(arrayAdapter);
                    }

                    if (Ward.equals("Phường An Khánh")) {
                        ArrayList<String> listt = new ArrayList<>();
                        for (String text : Q2) {
                            listt.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(CustomerRegisteration.this, android.R.layout.simple_spinner_item, listt);
                        Ward.setAdapter(arrayAdapter);
                    }

                    if (Ward.equals("P1")) {
                        ArrayList<String> listt = new ArrayList<>();
                        for (String text : Q3) {
                            listt.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(CustomerRegisteration.this, android.R.layout.simple_spinner_item, listt);
                        Ward.setAdapter(arrayAdapter);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


            databaseReference = firebaseDatabase.getInstance().getReference("Customer");
            FAuth = FirebaseAuth.getInstance();

            Signin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    email = emaill.getEditText().getText().toString().trim();
                    password = pass.getEditText().getText().toString().trim();
                    firstname = fname.getEditText().getText().toString().trim();
                    lastname = lname.getEditText().getText().toString().trim();
                    Localaddress = localadd.getEditText().getText().toString().trim();
                    confirmpass = cmpass.getEditText().getText().toString().trim();
                    mobileno = Mobileno.getEditText().getText().toString().trim();

                    if (isValid()) {


                        mDialog.show();

                        FAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    String useridd = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                    databaseReference = FirebaseDatabase.getInstance().getReference("User").child(useridd);
                                    final HashMap<String, String> hashMap = new HashMap<>();
                                    hashMap.put("Role", role);
                                    databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            HashMap<String, String> hashMappp = new HashMap<>();
                                            hashMappp.put("City", cityy);
                                            hashMappp.put("ConfirmPassword", confirmpass);
                                            hashMappp.put("EmailID", email);
                                            hashMappp.put("FirstName", firstname);
                                            hashMappp.put("LastName", lastname);
                                            hashMappp.put("Mobileno", mobileno);
                                            hashMappp.put("Password", password);
                                            hashMappp.put("LocalAddress", Localaddress);
                                            hashMappp.put("State", statee);
                                            hashMappp.put("Suburban", suburban);
                                            firebaseDatabase.getInstance().getReference("Customer")
                                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                    .setValue(hashMappp).addOnCompleteListener(new OnCompleteListener<Void>() {

                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            FAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {

                                                                @Override
                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                    if (task.isSuccessful()) {
                                                                        mDialog.dismiss();
                                                                        AlertDialog.Builder builder = new AlertDialog.Builder(CustomerRegisteration.this);
                                                                        builder.setMessage("Đăng kí thành công !, vui lòng xác nhận email");
                                                                        builder.setCancelable(false);
                                                                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                            @Override
                                                                            public void onClick(DialogInterface dialog, int which) {

                                                                                dialog.dismiss();
                                                                                String phonenumber = Cpp.getSelectedCountryCodeWithPlus() + mobileno;
                                                                                Intent b = new Intent(CustomerRegisteration.this, CustomerRegisteration.class);
                                                                                b.putExtra("phonenumber", phonenumber);
                                                                                startActivity(b);

                                                                            }
                                                                        });
                                                                        AlertDialog alert = builder.create();
                                                                        alert.show();

                                                                    } else {
                                                                        mDialog.dismiss();
                                                                        ReusableCodeForAll.ShowAlert(CustomerRegisteration.this,"Error",task.getException().getMessage());

                                                                    }
                                                                }
                                                            });
                                                        }
                                                    });
                                        }
                                    });

                                } else {
                                    mDialog.dismiss();
                                    ReusableCodeForAll.ShowAlert(CustomerRegisteration.this,"Lỗi","Email này đã được đăng kí sử dụng");
                                }
                            }
                        });
                    }


                }
            });
        } catch (Exception e) {
            mDialog.dismiss();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


        Email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(CustomerRegisteration.this, CustomerLoginEmail.class);
                startActivity(i);
                finish();
            }
        });

        Phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent e = new Intent(CustomerRegisteration.this, CustomerLoginPhone.class);
                startActivity(e);
                finish();
            }
        });


    }

    String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String namepattern="[^a-zA-Z0-9 ]";
    String numberPattern = "\\d+";
//else if (firstname.matches(".*" + namepattern + ".*")) {
  //      fname.setErrorEnabled(true);
    //    fname.setError("Họ và tên lót không được chứa kí tự đặc biệt");
    //}
    //else if (firstname.matches(numberPattern)) {
      //  fname.setErrorEnabled(true);
        //fname.setError("Họ và tên lót không được là số");
    //}

    public boolean isValid() {
        emaill.setErrorEnabled(false);
        emaill.setError("");
        fname.setErrorEnabled(false);
        fname.setError("");
        lname.setErrorEnabled(false);
        lname.setError("");
        pass.setErrorEnabled(false);
        pass.setError("");
        cmpass.setErrorEnabled(false);
        cmpass.setError("");
        Mobileno.setErrorEnabled(false);
        Mobileno.setError("");
        //Set các thông báo khi người dùng nhập Fname lỗi
        boolean isValidfirstname = false, isValidlastname = false, isValidaddress = false, isValidemail = false, isvalidpassword = false, isvalidconfirmpassword = false, isvalid = false, isvalidmobileno = false;
        if (TextUtils.isEmpty(firstname)) {
            fname.setErrorEnabled(true);
            fname.setError("Họ và tên lót không được để trống");
        } else {
            if (firstname.length() > 30) {
                fname.setErrorEnabled(true);
                fname.setError("Họ và tên lót không vượt quá 30 ký tự");
            } else if (firstname.length() < 3){
                fname.setErrorEnabled(true);
                fname.setError("Họ và tên lót phải lớn hơn 3 ký tự");
            }  else {
                isValidfirstname = true;
            }
        }

        //Set các thông báo khi người dùng nhập Lname lỗi
        if (TextUtils.isEmpty(lastname)) {
            lname.setErrorEnabled(true);
            lname.setError("Tên không được để trống");
        } else {
            if (lastname.length() > 30) {
                lname.setErrorEnabled(true);
                lname.setError("Tên không vượt quá 30 ký tự");
            }
            else {
                isValidlastname = true;
            }
        }
        //Set các thông báo khi người dùng nhập email lỗi
        if (TextUtils.isEmpty(email)) {
            emaill.setErrorEnabled(true);
            emaill.setError("Email không được để trống");
        } else {
            if (email.matches(emailpattern)) {
                isValidemail = true;
            } else {
                emaill.setErrorEnabled(true);
                emaill.setError("Hãy nhập một địa chỉ email chính xác");
            }

        }
        //Set các thông báo khi người dùng nhập sđt lỗi
        if (TextUtils.isEmpty(mobileno)) {
            Mobileno.setErrorEnabled(true);
            Mobileno.setError("Số điện thoại không được để trống");
        } else {
            if (mobileno.length() < 10) {
                Mobileno.setErrorEnabled(true);
                Mobileno.setError("Số điện thoại không tồn tại");
            } else if (mobileno.length() > 11) {
                Mobileno.setErrorEnabled(true);
                Mobileno.setError("Số điện thoại không tồn tại");
            }
            else {isvalidmobileno = true;}
        }
        //Set các thông báo khi người dùng nhập PW lỗi
        if (TextUtils.isEmpty(password)) {
            pass.setErrorEnabled(true);
            pass.setError("Mật khẩu không được để trống");
        } else {
            if (password.length() < 6) {
                pass.setErrorEnabled(true);
                pass.setError("Mật khẩu quá yếu");
                cmpass.setError("Mật khẩu quá yếu");
            } else if (password.length() > 14) {
                pass.setErrorEnabled(true);
                pass.setError("Mật khẩu không được dài hơn 14 ký tự");
                cmpass.setError("Mật khẩu không được dài hơn 14 ký tự");
            } else {
                isvalidpassword = true;
            }
        }
        if (TextUtils.isEmpty(confirmpass)) {
            cmpass.setErrorEnabled(true);
            cmpass.setError("Xác nhận mật khẩu không được để trống");
        } else {
            if (!password.equals(confirmpass)) {
                pass.setErrorEnabled(true);
                pass.setError("Mật khẩu không khớp");
                cmpass.setError("Mật khẩu không khớp");
            } else {
                isvalidconfirmpassword = true;
            }
        }
        if (TextUtils.isEmpty(Localaddress)) {
            localadd.setErrorEnabled(true);
            localadd.setError("Địa chỉ không được để trống");
        } else if (Localaddress.length()>30) {
            localadd.setErrorEnabled(true);
            localadd.setError("Địa chỉ không được quá 30 kí tự");
        } else if (Localaddress.length()<6) {
            localadd.setErrorEnabled(true);
            localadd.setError("Địa chỉ không được ít hơn 6 kí tự");
        } else {
            isValidaddress = true;
        }
        isvalid = (isValidfirstname && isValidlastname && isValidemail && isvalidconfirmpassword && isvalidpassword && isvalidmobileno && isValidaddress) ? true : false;
        return isvalid;
    }
}



