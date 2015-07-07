package com.artitk.android_viewholder_example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.artitk.android_viewholder_example.data.LoremItem;
import com.artitk.android_viewholder_example.data.SimulateItems;

import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        initInstances();
    }

    private void initInstances() {
        RecyclerView recycler_view = (RecyclerView) findViewById(R.id.recycler_view);

        ArrayList<LoremItem> items = SimulateItems.getSimulateItems(this);

        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        recycler_view.setAdapter(new RecyclerViewAdapter(items));
    }

    private class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private ArrayList<LoremItem> arrayItem;

        public RecyclerViewAdapter(ArrayList<LoremItem> arrayItem) {
            this.arrayItem = arrayItem;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            // TODO: Create ViewHolder
            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
            // TODO: Bind ViewHolder
        }

        @Override
        public int getItemCount() {
            return arrayItem.size();
        }

        private class ViewHolder extends RecyclerView.ViewHolder {
            public ViewHolder(View convertView) {
                super(convertView);

                // TODO: Hold view
            }
        }
    }
}
