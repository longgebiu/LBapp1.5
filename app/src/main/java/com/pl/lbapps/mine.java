package com.pl.lbapps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.pl.lbapps.view.CustomTitleBar;

public class mine extends AppCompatActivity {
    private Button btn3,btn2, btn1;
    private String TAG ="mine";
    private TextView textView;
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();//隐藏系统自带标题栏
        setContentView(R.layout.activity_mine);
        setTitleBar();
        btn3 = (Button) findViewById(R.id.btn3);
        img = (ImageView) findViewById(R.id.imgUser);
        textView = (TextView) findViewById(R.id.username);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(mine.this,shezhi.class);
                //Intent intent=new Intent(IntentTest.this,MyActivity.class);
                mine.this.startActivity(intent);
            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(mine.this,mymessage.class);
                //Intent intent=new Intent(IntentTest.this,MyActivity.class);
                mine.this.startActivity(intent);
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(mine.this,mymessage.class);
                //Intent intent=new Intent(IntentTest.this,MyActivity.class);
                mine.this.startActivity(intent);
            }
        });
    }
    private void setTitleBar() {
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.ct);
        customTitleBar.setTitle_text("个人设置");
        customTitleBar.setLeft_button_imageId(R.drawable.back);
        customTitleBar.setOnTitleClickListener(new CustomTitleBar.TitleOnClickListener() {
            @Override
            public void onLeftClick() {
                // Toast.makeText(MainActivity.this, "点击了左边的返回按钮", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent();
                intent.setClass(mine.this,MainActivity.class);
                //Intent intent=new Intent(IntentTest.this,MyActivity.class);
                mine.this.startActivity(intent);
            }

            @Override
            public void onRightClick() {
                // Toast.makeText(MainActivity.this, "点击了右边的保存按钮", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent();
                intent.setClass(mine.this,CallPhone.class);
                //Intent intent=new Intent(IntentTest.this,MyActivity.class);
                mine.this.startActivity(intent);
            }
        });
    }
}
