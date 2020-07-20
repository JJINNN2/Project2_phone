package com.example.phonephoto.phone;

import com.google.gson.annotations.SerializedName;

// 연락처 이름, 번호 받을 데이터
public class PhoneDownloadResponse {

    @SerializedName("name")
    private String name;

    @SerializedName("number")
    private String number;

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

}
