package com.example.appfood_by_tinnguyen2421.Chef.ChefModel;
//May not be copied in any form
//Copyright belongs to Nguyen TrongTin. contact: email:tinnguyen2421@gmail.com
public class FoodSupplyDetails {

    public String CateID,Dishes,Quantity,Price,Description,ImageURL,RandomUID,ChefId;

    public FoodSupplyDetails(String cateID,String dishes, String quantity, String price, String description, String imageURL, String randomUID, String chefId) {
        CateID=cateID;
        Dishes = dishes;
        Quantity = quantity;
        Price = price;
        Description = description;
        ImageURL = imageURL;
        RandomUID=randomUID;
        ChefId=chefId;
    }

}
