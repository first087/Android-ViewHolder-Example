package com.artitk.android_viewholder_example.data;

public class LoremItem {

    private String loremText;
    private Boolean loremCheck;

    public LoremItem() {
        loremText = "";
        loremCheck = false;
    }

    public String getLoremText() {
        return loremText;
    }

    public void setLoremText(String loremText) {
        this.loremText = loremText;
    }

    public Boolean getLoremCheck() {
        return loremCheck;
    }

    public void setLoremCheck(Boolean loremCheck) {
        this.loremCheck = loremCheck;
    }
}
