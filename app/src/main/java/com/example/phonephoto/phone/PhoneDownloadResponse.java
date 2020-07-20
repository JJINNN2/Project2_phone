package com.example.phonephoto.phone;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

// 연락처 이름, 번호 받을 데이터
public class PhoneDownloadResponse {

    @SerializedName("name")
    ArrayList<String> name;

    @SerializedName("number")
    ArrayList<String> number;

    public ArrayList<String> getName() {
        return name;
    }

    public ArrayList<String> getNumber() {
        return number;
    }

}
