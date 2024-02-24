package com.example.appfood_by_tinnguyen2421;
//May not be copied in any form
//Copyright belongs to Nguyen TrongTin. contact: email:tinnguyen2421@gmail.com
public class Categories {
    public String CateID;
    public String tentheloai;
    public String mota;
    public String image;

    public Categories() {
    }

    public Categories(String matheloai, String tentheloai, String mota, String image) {
        this.CateID = matheloai;
        this.tentheloai = tentheloai;
        this.mota = mota;
        this.image = image;
    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMatheloai() {
        return CateID;
    }

    public void setMatheloai(String matheloai) {
        this.CateID = matheloai;
    }

    public String getTentheloai() {
        return tentheloai;
    }

    public void setTentheloai(String tentheloai) {
        this.tentheloai = tentheloai;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
}
