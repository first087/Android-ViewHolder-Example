package com.artitk.android_viewholder_example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        initInstances();
    }

    private void initInstances() {
        ListView list_view = (ListView) findViewById(R.id.list_view);

        ArrayList<LoremItem> items = SimulateItems.getSimulateItems(this);

        list_view.setAdapter(new ListViewAdapter(items));
    }

    private class ListViewAdapter extends BaseAdapter {
        private ArrayList<LoremItem> arrayItem;

        private TextView item_text;
        private CheckBox item_check;

        public ListViewAdapter(ArrayList<LoremItem> arrayItem) {
            this.arrayItem = arrayItem;
        }

        @Override
        public int getCount() {
            return arrayItem.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = LayoutInflater.from(ListViewActivity.this).inflate(R.layout.view_item, null);

            initInstances(i, view);
            initData(i);

            return view;
        }

        private void initInstances(final int i, View view) {
            item_text  = (TextView) view.findViewById(R.id.item_text);
            item_check = (CheckBox) view.findViewById(R.id.item_check);

            item_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    arrayItem.get(i).setLoremCheck(b);
                }
            });
        }

        private void initData(int i) {
            item_text.setText(arrayItem.get(i).getLoremText());
            item_check.setChecked(arrayItem.get(i).getLoremCheck());
        }
    }
}
