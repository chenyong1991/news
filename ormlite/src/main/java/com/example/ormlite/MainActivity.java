package com.example.ormlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {

            OrmliteOpenHelper instance = OrmliteOpenHelper.getInstance(this);

            Dao<User, Long> dao = OrmliteOpenHelper.getDao(this);

            dao.create(new User("小洋洋",25));

            List<User> users = dao.queryForAll();

            Toast.makeText(this, users.get(0).toString(), Toast.LENGTH_SHORT).show();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
