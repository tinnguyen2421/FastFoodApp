package com.example.appfood_by_tinnguyen2421.DeliveryAccount;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appfood_by_tinnguyen2421.R;
import com.example.appfood_by_tinnguyen2421.ReusableCodeForAll;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
//May not be copied in any form
//Copyright belongs to Nguyen TrongTin. contact: email:tinnguyen2421@gmail.com
public class DeliveryForgotPassword extends AppCompatActivity {

    TextInputLayout forgetpassword;
    Button Reset;
    FirebaseAuth FAuth;
    String pgp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_forgot_password);

        forgetpassword = (TextInputLayout) findViewById(R.id.forgotEmailid);
        Reset = (Button) findViewById(R.id.forgotreset);
        pgp = forgetpassword.getEditText().getText().toString().trim();
        FAuth = FirebaseAuth.getInstance();
        Reset.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                if (isvalid()) {
                    final ProgressDialog mDialog = new ProgressDialog(DeliveryForgotPassword.this);
                    mDialog.setCancelable(false);
                    mDialog.setCanceledOnTouchOutside(false);
                    mDialog.setMessage("Đang thực hiện...");
                    mDialog.show();


                    FAuth.sendPasswordResetEmail(forgetpassword.getEditText().getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                mDialog.dismiss();
                                ReusableCodeForAll.ShowAlert(DeliveryForgotPassword.this, "", "Mật khẩu đã được gửi đến email của bạn");
                            } else {
                                mDialog.dismiss();
                                ReusableCodeForAll.ShowAlert(DeliveryForgotPassword.this, "Lỗi", task.getException().getMessage());
                            }
                        }
                    });
                }
            }
        });

    }
    public boolean isvalid() {
        forgetpassword.setErrorEnabled(false);
        forgetpassword.setError("");
        boolean isValidEmail = false,isvalid = false;
        if (TextUtils.isEmpty(pgp)) {
            forgetpassword.setErrorEnabled(true);
            forgetpassword.setError("Email không được để trống");
        }
        else {
            if (pgp.matches(emailpattern))
            {
                isValidEmail=true;
            }
            else {
                forgetpassword.setErrorEnabled(true);
                forgetpassword.setError("Email không hợp lệ");
            }
        }
        isvalid = (isValidEmail) ? true : false;
        return isvalid;
    }
    String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
}
