package com.example.appfood_by_tinnguyen2421.SendNotification;
//May not be copied in any form
//Copyright belongs to Nguyen TrongTin. contact: email:tinnguyen2421@gmail.com
public class Data {

    private String Title;
    private String Message;
    private String Typepage;

    public Data(String title, String message, String typepage) {
        Title = title;
        Message = message;
        Typepage = typepage;
    }
    public Data() {

    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getTypepage() {
        return Typepage;
    }

    public void setTypepage(String typepage) {
        Typepage = typepage;
    }
}
