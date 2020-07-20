package com.example.phonephoto.phone;

import com.google.gson.annotations.SerializedName;

// 요청 시 보낼 데이터
public class PhoneUploadData {
    @SerializedName("phoneName")
    String phoneName;

    @SerializedName("phoneNumber")
    String phoneNumber;

    public PhoneUploadData(String phoneName, String phoneNumber) {
        this.phoneName = phoneName;
        this.phoneNumber = phoneNumber;
    }
}
