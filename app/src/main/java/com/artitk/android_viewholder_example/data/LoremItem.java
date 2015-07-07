package com.artitk.android_viewholder_example.data;

public class LoremItem {

    private String loremText;
    private Boolean loremCheck;
    private int colorIndex;

    public LoremItem() {
        loremText = "";
        loremCheck = false;
        colorIndex = -1;
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

    public int getColorIndex() {
        return colorIndex;
    }

    public void setColorIndex(int colorIndex) {
        this.colorIndex = colorIndex;
    }
}
