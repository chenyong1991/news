package com.example.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = (RecyclerView) findViewById(R.id.recyclerview);

        List<String> datas = new ArrayList<>();

        for (int i =0 ; i < 100; i++){


            datas.add("小羊羊" + i + "号");

        }

       // rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,true));
        rv.setLayoutManager(new GridLayoutManager(this,3,LinearLayoutManager.VERTICAL,false));

        RecyclerviewAdapter adapter = new RecyclerviewAdapter(this,datas);

        rv.setAdapter(adapter);


    }
}
