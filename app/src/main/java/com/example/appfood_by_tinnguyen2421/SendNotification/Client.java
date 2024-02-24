package com.example.appfood_by_tinnguyen2421.SendNotification;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
//May not be copied in any form
//Copyright belongs to Nguyen TrongTin. contact: email:tinnguyen2421@gmail.com
public class Client {
    private static Retrofit retrofit=null;

    public static Retrofit getClient(String url)
    {
        if (retrofit==null)
        {
            retrofit=new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        }

        return retrofit;
    }
}
