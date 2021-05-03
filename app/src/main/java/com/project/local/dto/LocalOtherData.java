package com.project.local.dto;

import com.project.R;

import java.io.Serializable;

public class LocalOtherData  implements Serializable {

    private String[] otherName={
            "베이컨말이", "콘치즈", "건빵튀김", "호떡","치즈라면","소떡소떡"
    };

    private String[] otherRate={
            "4.4", "3.2", "4.4", "4.1","5.0","3.7"
    };

    private Integer[] otherImg={
            R.drawable.other01, R.drawable.other02,
            R.drawable.other03, R.drawable.other04,
            R.drawable.other05, R.drawable.other06
    };

    public LocalOtherData(String[] otherName, String[] otherRate, Integer[] otherImg) {
        this.otherName = otherName;
        this.otherRate = otherRate;
        this.otherImg = otherImg;
    }

    public String[] getOtherName() {
        return otherName;
    }

    public void setOtherName(String[] otherName) {
        this.otherName = otherName;
    }

    public String[] getOtherRate() {
        return otherRate;
    }

    public void setOtherRate(String[] otherRate) {
        this.otherRate = otherRate;
    }

    public Integer[] getOtherImg() {
        return otherImg;
    }

    public void setOtherImg(Integer[] otherImg) {
        this.otherImg = otherImg;
    }
}
