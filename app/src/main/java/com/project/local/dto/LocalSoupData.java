package com.project.local.dto;

import com.project.R;

import java.io.Serializable;

public class LocalSoupData implements Serializable {

    private String[] SoupName={
            "부대찌개", "김치찌개", "콩나물국", "된장찌개","육개장","미역국"
    };

    private String[] SoupRate={
            "3.4", "4.9", "3.2", "4.1","4.5","4.2"
    };

    private Integer[] SoupImg={
            R.drawable.soup01, R.drawable.soup02,
            R.drawable.soup03, R.drawable.soup04,
            R.drawable.soup05, R.drawable.soup06
    };

    public LocalSoupData(String[] soupName, String[] soupRate, Integer[] soupImg) {
        SoupName = soupName;
        SoupRate = soupRate;
        SoupImg = soupImg;
    }

    public String[] getSoupName() {
        return SoupName;
    }

    public void setSoupName(String[] soupName) {
        SoupName = soupName;
    }

    public String[] getSoupRate() {
        return SoupRate;
    }

    public void setSoupRate(String[] soupRate) {
        SoupRate = soupRate;
    }

    public Integer[] getSoupImg() {
        return SoupImg;
    }

    public void setSoupImg(Integer[] soupImg) {
        SoupImg = soupImg;
    }
}
