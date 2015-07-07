package com.artitk.android_viewholder_example;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.artitk.android_viewholder_example.data.LoremItem;
import com.artitk.android_viewholder_example.data.SimulateItems;

import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        context = this;

        initInstances();
    }

    private void initInstances() {
        RecyclerView recycler_view = (RecyclerView) findViewById(R.id.recycler_view);

        ArrayList<LoremItem> items = SimulateItems.getSimulateItems(this);

        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        recycler_view.setAdapter(new RecyclerViewAdapter(items));
    }

    private class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
        private ArrayList<LoremItem> arrayItem;
        private ArrayList<Integer> arrayInitPosition;

        public RecyclerViewAdapter(ArrayList<LoremItem> arrayItem) {
            this.arrayItem = arrayItem;
            arrayInitPosition = new ArrayList<>();
        }

        @Override
        public int getItemCount() {
            return arrayItem.size();
        }

        @Override
        public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View convertView = LayoutInflater.from(context).inflate(R.layout.view_item, null);
            return new ViewHolder(convertView);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            initInstances(viewHolder, position);
            initData(viewHolder, position);
        }

        private void initInstances(final ViewHolder viewHolder, final int position) {
            if (arrayInitPosition.contains(position)) return;

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

            arrayInitPosition.add(position);
        }

        private void initData(ViewHolder viewHolder, int position) {
            viewHolder.item_text.setText(arrayItem.get(position).getLoremText());
            viewHolder.item_check.setChecked(arrayItem.get(position).getLoremCheck());

            viewHolder.item_text.setBackgroundColor(
                    SimulateItems.getSimulateColor(context, arrayItem.get(position).getColorIndex())
            );
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView item_text;
            public CheckBox item_check;

            public ViewHolder(View convertView) {
                super(convertView);

                item_text  = (TextView) convertView.findViewById(R.id.item_text);
                item_check = (CheckBox) convertView.findViewById(R.id.item_check);
            }
        }
    }
}
