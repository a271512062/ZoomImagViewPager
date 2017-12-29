package com.lihaibin.zoomimageviewpagerlibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList imgs = new ArrayList<>();
        imgs.add(R.mipmap.ic_launcher);
        imgs.add(R.mipmap.ic_launcher);
        imgs.add(R.mipmap.ic_launcher);
        BigPicActivity.start(this,imgs,3);
    }
}
