package com.project.local.dto;

import com.project.R;

import java.io.Serializable;

public class LocalMainData implements Serializable {

    private String[] mainName={
            "간장 계란밥", "야끼 오니기리", "비빔밥", "누룽지","김밥","볶음밥"
    };

    private String[] mainRate={
            "4.2", "4.0", "3.2", "3.7","4.1","3.2"
    };

    private Integer[] mainImg={
            R.drawable.rice01, R.drawable.rice02,
            R.drawable.rice03, R.drawable.rice04,
            R.drawable.rice05, R.drawable.rice06
    };

    public LocalMainData() {
    }

    public LocalMainData(String[] mainName, String[] mainRate, Integer[] mainImg) {
        this.mainName = mainName;
        this.mainRate = mainRate;
        this.mainImg = mainImg;
    }

    public String[] getMainName() {
        return mainName;
    }

    public void setMainName(String[] mainName) {
        this.mainName = mainName;
    }

    public String[] getMainRate() {
        return mainRate;
    }

    public void setMainRate(String[] mainRate) {
        this.mainRate = mainRate;
    }

    public Integer[] getMainImg() {
        return mainImg;
    }

    public void setMainImg(Integer[] mainImg) {
        this.mainImg = mainImg;
    }
}
