package com.pl.lbapps;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.pl.lbapps.view.CustomTitleBar;

public class Login extends AppCompatActivity {
    private Button login;
    private EditText EditText1,EditText2;
    private String TAG ="Login";
    private SharedPreferences sp;
    private CheckBox rem_pw,auto_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();//隐藏系统自带标题栏
        setContentView(R.layout.activity_login);
        setTitleBar();
        login = (Button) findViewById(R.id.login);
        //获得组件实例
        sp = this.getSharedPreferences("userInfo", Context.MODE_WORLD_READABLE);
        EditText1= (EditText) findViewById(R.id.editText1);
        EditText2= (EditText) findViewById(R.id.editText2);
        rem_pw = (CheckBox) findViewById(R.id.rem_pw);
        auto_login = (CheckBox) findViewById(R.id.auto_login);

        //判断记住密码多选框的状态
        if(sp.getBoolean("ISCHECK", false))
        {
            //设置默认是记录密码状态
            rem_pw.setChecked(true);
            EditText1.setText(sp.getString("USER_NAME", ""));
            EditText2.setText(sp.getString("PASSWORD", ""));
            //判断自动登陆多选框状态
            if(sp.getBoolean("AUTO_ISCHECK", false))
            {
                //设置默认是自动登录状态
                auto_login.setChecked(true);
                //跳转界面
                Intent intent = new Intent(Login.this,MainActivity.class);
                Login.this.startActivity(intent);
                Toast.makeText(Login.this,"登录成功!欢迎"+EditText1.getText().toString(),Toast.LENGTH_LONG).show();
            }
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = EditText1.getText().toString();
                String password = EditText2.getText().toString();
                Log.d(TAG,user+": "+password );
                if (user.equals("admin") && password.equals("123")){
                    //记住用户名、密码、
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("USER_NAME", user);
                    editor.putString("PASSWORD",password);
                    editor.commit();
                    Toast.makeText(Login.this,"登录成功!欢迎"+user,Toast.LENGTH_LONG).show();
                    Intent intent =new Intent();
                    intent.setClass(Login.this,MainActivity.class);
                    Login.this.startActivity(intent);
                    SharedPreferences userSettings= getSharedPreferences("setting", 0);
                    Log.d(TAG, userSettings.getString("USER_NAME","PASSWORD")+"..............................................");
                }
                else{
                    Toast.makeText(Login.this,"用户名或者密码错误！",Toast.LENGTH_LONG).show();
                }
            }
        });
        //监听记住密码多选框按钮事件
        rem_pw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if (rem_pw.isChecked()) {

                    System.out.println("记住密码已选中");
                    sp.edit().putBoolean("ISCHECK", true).commit();

                }else {

                    System.out.println("记住密码没有选中");
                    sp.edit().putBoolean("ISCHECK", false).commit();

                }

            }
        });

        //监听自动登录多选框事件
        auto_login.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if (auto_login.isChecked()) {
                    System.out.println("自动登录已选中");
                    sp.edit().putBoolean("AUTO_ISCHECK", true).commit();

                } else {
                    System.out.println("自动登录没有选中");
                    sp.edit().putBoolean("AUTO_ISCHECK", false).commit();
                }
            }
        });
    }
    private void setTitleBar() {
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.ct);
        customTitleBar .setLeft_button_imageId(0);
        customTitleBar.setLeft(0);
        customTitleBar.setTitle_text("登录");
        customTitleBar.setOnTitleClickListener(new CustomTitleBar.TitleOnClickListener() {
            @Override
            public void onLeftClick() {
                // Toast.makeText(MainActivity.this, "点击了左边的返回按钮", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent();
                intent.setClass(Login.this,Login.class);
                //Intent intent=new Intent(IntentTest.this,MyActivity.class);
                Login.this.startActivity(intent);
            }

            @Override
            public void onRightClick() {
                // Toast.makeText(MainActivity.this, "点击了右边的保存按钮", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent();
                intent.setClass(Login.this,CallPhone.class);
                //Intent intent=new Intent(IntentTest.this,MyActivity.class);
                Login.this.startActivity(intent);
            }
        });
    }
}
