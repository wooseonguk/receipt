package com.project.local.dto;

import com.project.R;

import java.io.Serializable;

public class LocalSubData implements Serializable {

    private String[] subName={
            "제육볶음", "버섯볶음", "감자볶음", "연근조림","소세지야채볶음","무채"
    };

    private String[] subRate={
            "3.5", "4.0", "5.0", "3.7","4.3","4.7"
    };

    private Integer[] subImg={
            R.drawable.sub01, R.drawable.sub02,
            R.drawable.sub03, R.drawable.sub04,
            R.drawable.sub05, R.drawable.sub06
    };

    public LocalSubData(String[] subName, String[] subRate, Integer[] subImg) {
        this.subName = subName;
        this.subRate = subRate;
        this.subImg = subImg;
    }

    public String[] getSubName() {
        return subName;
    }

    public void setSubName(String[] subName) {
        this.subName = subName;
    }

    public String[] getSubRate() {
        return subRate;
    }

    public void setSubRate(String[] subRate) {
        this.subRate = subRate;
    }

    public Integer[] getSubImg() {
        return subImg;
    }

    public void setSubImg(Integer[] subImg) {
        this.subImg = subImg;
    }
}
