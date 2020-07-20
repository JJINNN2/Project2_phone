package com.example.phonephoto.phone;

import android.telephony.PhoneNumberUtils;

import androidx.annotation.NonNull;

class PhoneItem {
    private String Name;
    private String PhoneNumber;
    //private boolean isSelected;

    public PhoneItem(String _Name, String _PhoneNumber)
    {
        this.Name = _Name;
        this.PhoneNumber = _PhoneNumber.replaceAll("-", "");
        //this.isSelected = isSelected;
    }

    public String getName() {
        return Name;
    }

    public String getNumber() {
        return PhoneNumberUtils.formatNumber(PhoneNumber);
    }

    @NonNull
    @Override
    public String toString() {
        super.toString();
        return "name: " + Name + " phone: " + PhoneNumber;
    }

    //public boolean isSelected() { return isSelected; }

//    public void setSelected(boolean isSelected) {
//        this.isSelected = isSelected;
//    }

    //    public String getId() { return id;}
}