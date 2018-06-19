package com.example.gibson.carlife.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.gibson.carlife.Adapters.HistoryAdapter;
import com.example.gibson.carlife.Model.DataManagement;
import com.example.gibson.carlife.R;

public class HistoryActivity extends AppCompatActivity {
    public ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        listView =findViewById(R.id.historyeListview);
        HistoryAdapter adapter = new HistoryAdapter(this, DataManagement.getHistories());
        listView.setAdapter(adapter);
    }
}
