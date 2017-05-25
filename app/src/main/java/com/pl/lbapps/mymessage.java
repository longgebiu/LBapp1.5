package com.pl.lbapps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pl.lbapps.view.CustomTitleBar;

public class mymessage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();//隐藏系统自带标题栏
        setContentView(R.layout.mymessage);
        setTitleBar();
    }
    private void setTitleBar() {
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.ct);
        customTitleBar.setLeft_button_imageId(R.drawable.back);
        customTitleBar.setTitle_text("个人信息");
        customTitleBar.setLeft_button_imageId(R.drawable.back);
        customTitleBar.setOnTitleClickListener(new CustomTitleBar.TitleOnClickListener() {
            @Override
            public void onLeftClick() {
                // Toast.makeText(MainActivity.this, "点击了左边的返回按钮", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent();
                intent.setClass(mymessage.this,mine.class);
                //Intent intent=new Intent(IntentTest.this,MyActivity.class);
                mymessage.this.startActivity(intent);
            }

            @Override
            public void onRightClick() {
                // Toast.makeText(MainActivity.this, "点击了右边的保存按钮", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent();
                intent.setClass(mymessage.this,CallPhone.class);
                //Intent intent=new Intent(IntentTest.this,MyActivity.class);
                mymessage.this.startActivity(intent);
            }
        });
    }
}
