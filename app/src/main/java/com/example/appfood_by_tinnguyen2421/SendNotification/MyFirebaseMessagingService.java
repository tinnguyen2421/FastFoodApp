package com.example.appfood_by_tinnguyen2421.SendNotification;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
//May not be copied in any form
//Copyright belongs to Nguyen TrongTin. contact: email:tinnguyen2421@gmail.com
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    String title, message, typepage;

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        title = remoteMessage.getData().get("Title");
        message = remoteMessage.getData().get("Message");
        typepage = remoteMessage.getData().get("Typepage");

        ShowNotification.ShowNotif(getApplicationContext(),title,message,typepage);
    }
}
