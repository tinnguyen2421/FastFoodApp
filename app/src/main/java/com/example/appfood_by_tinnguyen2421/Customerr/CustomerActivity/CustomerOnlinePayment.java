package com.example.appfood_by_tinnguyen2421.Customerr.CustomerActivity;
//May not be copied in any form
//Copyright belongs to Nguyen TrongTin. contact: email:tinnguyen2421@gmail.com
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appfood_by_tinnguyen2421.R;
import com.google.android.material.textfield.TextInputLayout;

public class CustomerOnlinePayment extends AppCompatActivity {

    TextInputLayout cardname, cardnumber, expirydate, cvv;
    Button Addcard;
    String name, number, date, CVV;
    String RandomUID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_online_payment);

        Addcard = (Button) findViewById(R.id.addcard);
        cardname = (TextInputLayout) findViewById(R.id.nameoncard);
        cardnumber = (TextInputLayout) findViewById(R.id.cardnumber);
        expirydate = (TextInputLayout) findViewById(R.id.expirydate);
        cvv = (TextInputLayout) findViewById(R.id.CVV);
        RandomUID = getIntent().getStringExtra("randomUID");

        Addcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = cardname.getEditText().getText().toString().trim();
                number = cardnumber.getEditText().getText().toString().trim();
                date = expirydate.getEditText().getText().toString().trim();
                CVV = cvv.getEditText().getText().toString().trim();
                if (valid()) {
                    Intent intent = new Intent(CustomerOnlinePayment.this, CustomerPaymentOTP.class);
                    intent.putExtra("RandomUID",RandomUID);
                    startActivity(intent);
                }

            }
        });
    }

    private boolean valid() {


        cardname.setErrorEnabled(false);
        cardname.setError("");
        cardnumber.setErrorEnabled(false);
        cardnumber.setError("");
        expirydate.setErrorEnabled(false);
        expirydate.setError("");
        cvv.setErrorEnabled(false);
        cvv.setError("");


        boolean isValidname = false, isValidlnumber = false, isValidexpiry = false, isValidcvv = false, isvalid = false;
        if (TextUtils.isEmpty(name)) {
            cardname.setErrorEnabled(true);
            cardname.setError("Tên thẻ tín dụng không được để trống");
        } else {
            if(name.length()>20){
                cardname.setErrorEnabled(true);
                cardname.setError("Tên thẻ không vượt quá 20 kí tự");
            } else if (name.length()<6) {
                cardname.setErrorEnabled(true);
                cardname.setError("Tên thẻ không được ít hơn 6 kí tự");
            } else {
                isValidname = true;
            }
        }
        if (TextUtils.isEmpty(number)) {
            cardnumber.setErrorEnabled(true);
            cardnumber.setError("Số thẻ không được để trống");
        } else {
            if (number.length() < 16||number.length()>19) {
                cardnumber.setErrorEnabled(true);
                cardnumber.setError("Mã số thẻ không không tồn tại");
            } else {
                isValidlnumber = true;
            }
        }
        if (TextUtils.isEmpty(date)) {
            expirydate.setErrorEnabled(true);
            expirydate.setError("Ngày hết hạn không được để trống");
        } else {
            isValidexpiry = true;

        }
        if (TextUtils.isEmpty(CVV)) {
            cvv.setErrorEnabled(true);
            cvv.setError("CVV không được để trống");
        } else {
            if (CVV.length() < 3 ||CVV.length()>3) {
                cvv.setErrorEnabled(true);
                cvv.setError("Số CVV không tồn tại");
            } else {
                isValidcvv = true;
            }
        }
        isvalid = (isValidname && isValidlnumber && isValidexpiry && isValidcvv) ? true : false;
        return isvalid;

    }
}
