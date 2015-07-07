package com.artitk.android_viewholder_example.data;

import android.content.Context;

import com.artitk.android_viewholder_example.R;

import java.util.ArrayList;

public class SimulateItems {

    public static ArrayList<LoremItem> getSimulateItems(Context context) {
        int[] loremResId = {
                R.string.lorem_long_1, R.string.lorem_long_2, R.string.lorem_long_3,
                R.string.lorem_long_4, R.string.lorem_long_5
        };

        ArrayList<LoremItem> arrayList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            LoremItem loremItem = new LoremItem();
            loremItem.setLoremText(context.getString(loremResId[i]));
            arrayList.add(loremItem);
        }

        return arrayList;
    }
}
