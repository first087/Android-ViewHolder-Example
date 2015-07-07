package com.artitk.android_viewholder_example;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
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

import com.artitk.android_viewholder_example.data.LoremItem;
import com.artitk.android_viewholder_example.data.SimulateItems;

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

        public ListViewAdapter(ArrayList<LoremItem> arrayItem) {
            this.arrayItem = arrayItem;
        }

        @Override
        public int getCount() {
            return arrayItem.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;

            if (convertView == null) {
                convertView = LayoutInflater.from(ListViewActivity.this).inflate(R.layout.view_item, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            initInstances(position, viewHolder);
            initData(position, viewHolder);

            return convertView;
        }

        private void initInstances(final int position, final ViewHolder viewHolder) {
            viewHolder.item_text.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    (new AlertDialog.Builder(ListViewActivity.this))
                            .setTitle(R.string.choose_background_color)
                            .setItems(SimulateItems.getSimulateColorNames(ListViewActivity.this),
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            arrayItem.get(position).setColorIndex(which);
                                            viewHolder.item_text.setBackgroundColor(
                                                    SimulateItems.getSimulateColor(ListViewActivity.this, which)
                                            );
                                        }
                            })
                            .show();

                    return true;
                }
            });

            viewHolder.item_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    arrayItem.get(position).setLoremCheck(isChecked);
                }
            });
        }

        private void initData(int position, ViewHolder viewHolder) {
            viewHolder.item_text.setText(arrayItem.get(position).getLoremText());
            viewHolder.item_check.setChecked(arrayItem.get(position).getLoremCheck());

            viewHolder.item_text.setBackgroundColor(
                    SimulateItems.getSimulateColor(ListViewActivity.this, arrayItem.get(position).getColorIndex())
            );
        }

        private class ViewHolder {
            public TextView item_text;
            public CheckBox item_check;

            public ViewHolder(View convertView) {
                item_text  = (TextView) convertView.findViewById(R.id.item_text);
                item_check = (CheckBox) convertView.findViewById(R.id.item_check);
            }
        }
    }
}
