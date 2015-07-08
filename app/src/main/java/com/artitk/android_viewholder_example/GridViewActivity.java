package com.artitk.android_viewholder_example;

import android.content.Context;
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
import android.widget.GridView;
import android.widget.TextView;

import com.artitk.android_viewholder_example.data.LoremItem;
import com.artitk.android_viewholder_example.data.SimulateItems;

import java.util.ArrayList;

public class GridViewActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        context = this;

        initInstances();
    }

    private void initInstances() {
        GridView grid_view = (GridView) findViewById(R.id.grid_view);

        ArrayList<LoremItem> items = SimulateItems.getSimulateItemsForGridView(this);

        grid_view.setAdapter(new GridViewAdapter(items));
    }

    private class GridViewAdapter extends BaseAdapter {
        private ArrayList<LoremItem> arrayItem;

        public GridViewAdapter(ArrayList<LoremItem> arrayItem) {
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
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;

            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.view_item, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            initInstances(viewHolder, position);
            initData(viewHolder, position);

            return convertView;
        }

        private void initInstances(final ViewHolder viewHolder, final int position) {
            viewHolder.item_text.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    (new AlertDialog.Builder(context))
                            .setTitle(R.string.choose_background_color)
                            .setItems(SimulateItems.getSimulateColorNames(context),
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            arrayItem.get(position).setColorIndex(which);
                                            viewHolder.item_text.setBackgroundColor(
                                                    SimulateItems.getSimulateColor(context, which)
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

        private void initData(ViewHolder viewHolder, int position) {
            viewHolder.item_text.setText(arrayItem.get(position).getLoremText());
            viewHolder.item_check.setChecked(arrayItem.get(position).getLoremCheck());

            viewHolder.item_text.setBackgroundColor(
                    SimulateItems.getSimulateColor(context, arrayItem.get(position).getColorIndex())
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
