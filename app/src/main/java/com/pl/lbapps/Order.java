package com.pl.lbapps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pl.lbapps.view.CustomTitleBar;

public class Order extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();//隐藏系统自带标题栏
        setContentView(R.layout.activity_order);
        setTitleBar();
    }
    private void setTitleBar() {
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.ct);
        customTitleBar.setTitle_text("流帮");
        customTitleBar.setLeft_button_imageId(R.drawable.back);
        customTitleBar.setOnTitleClickListener(new CustomTitleBar.TitleOnClickListener() {
            @Override
            public void onLeftClick() {
                // Toast.makeText(MainActivity.this, "点击了左边的返回按钮", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent();
                intent.setClass(Order.this,MainActivity.class);
                //Intent intent=new Intent(IntentTest.this,MyActivity.class);
                Order.this.startActivity(intent);
            }

            @Override
            public void onRightClick() {
                // Toast.makeText(MainActivity.this, "点击了右边的保存按钮", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent();
                intent.setClass(Order.this,CallPhone.class);
                //Intent intent=new Intent(IntentTest.this,MyActivity.class);
                Order.this.startActivity(intent);
            }
        });
    }
}
