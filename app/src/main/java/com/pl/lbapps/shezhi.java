package com.pl.lbapps;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.pl.lbapps.view.CustomTitleBar;

public class shezhi extends AppCompatActivity {
    private Button mystar,mymesage,exit;
    private  SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();//隐藏系统自带标题栏
        setContentView(R.layout.shezhi);
        sp = this.getSharedPreferences("userInfo", Context.MODE_WORLD_READABLE);
        setTitleBar();
        mystar = (Button) findViewById(R.id.button55);
        mymesage = (Button) findViewById(R.id.button11);
        mystar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = sp.getString("USER_NAME", "");
                String pass = sp.getString("PASSWORD", "");
                SharedPreferences.Editor editor = sp.edit();
                editor.remove("USER_NAME");
                editor.remove("PASSWORD");
                editor.commit();
                Toast.makeText(shezhi.this,name+"/n"+pass,Toast.LENGTH_LONG).show();
            }
        });
        mymesage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(shezhi.this,mymessage.class);
                //Intent intent=new Intent(IntentTest.this,MyActivity.class);
                shezhi.this.startActivity(intent);
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    private void setTitleBar() {
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.ct);
        customTitleBar.setTitle_text("设置");
        customTitleBar.setLeft_button_imageId(R.drawable.back);
        customTitleBar.setOnTitleClickListener(new CustomTitleBar.TitleOnClickListener() {
            @Override
            public void onLeftClick() {
                // Toast.makeText(MainActivity.this, "点击了左边的返回按钮", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent();
                intent.setClass(shezhi.this,mine.class);
                //Intent intent=new Intent(IntentTest.this,MyActivity.class);
                shezhi.this.startActivity(intent);
            }

            @Override
            public void onRightClick() {
                // Toast.makeText(MainActivity.this, "点击了右边的保存按钮", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent();
                intent.setClass(shezhi.this,CallPhone.class);
                //Intent intent=new Intent(IntentTest.this,MyActivity.class);
                shezhi.this.startActivity(intent);
            }
        });
    }
}
