package com.pl.lbapps;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.pl.lbapps.view.CustomTitleBar;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationBar bottom_bar;
    private FragmentOne fragment1;
    private FragmentTwo fragment2;
    private FragmentThree fragment3;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();//隐藏系统自带标题栏
        initView();//定义BottomNavigationBar的导航栏
        initEvent();//定义切换事件
        setDefaultFragment();//设置默认显示的Fragment
        //设置标题栏
        setTitleBar();

    }

    //设置标题栏
    private void setTitleBar() {
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.ct);
        customTitleBar.setTitle_text("流帮");
        customTitleBar.setOnTitleClickListener(new CustomTitleBar.TitleOnClickListener() {
            @Override
            public void onLeftClick() {
                Toast.makeText(MainActivity.this, "点击了左边的返回按钮", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightClick() {
                Toast.makeText(MainActivity.this, "点击了右边的保存按钮", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent();
                intent.setClass(MainActivity.this,Login.class);
                //Intent intent=new Intent(IntentTest.this,MyActivity.class);
                MainActivity.this.startActivity(intent);            }
        });
    }

    //设置显示默认的Fragment
    private void setDefaultFragment() {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        fragment1 = new FragmentOne();
        transaction.replace(R.id.main_frame_layout,fragment1);
        transaction.commit();
    }
    //设置Fragment的切换
    private void initEvent() {
        bottom_bar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                FragmentManager fm = getFragmentManager();
                // 开启Fragment事务
                FragmentTransaction transaction = fm.beginTransaction();
                switch (position) {
                    case 0:
                        fragment1= new FragmentOne();
                        //txt_main.setText(getString(R.string.home));
                        if (fragment1.isAdded()) {
                            transaction.show(fragment1);
                        }else {
                            transaction.add(R.id.main_frame_layout, fragment1);
                        }
                        transaction.commit();

                        System.out.println("1");
                        break;
                    case 1:
                        fragment2= new FragmentTwo();
                        if (fragment2 != null) {
                            transaction.replace(R.id.main_frame_layout,fragment2);
                            transaction.commit();
                        }
                        System.out.println("2");
                        break;
                    case 2:
                        fragment3 = new FragmentThree();
                        if (fragment3 != null) {
                            transaction.replace(R.id.main_frame_layout,fragment3);
                            transaction.commit();
                        }
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }
    //定义BottomNavigationBar的导航栏
    private void initView() {
        bottom_bar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottom_bar.addItem(new BottomNavigationItem(R.drawable.ic_location_on_white_24dp, getString(R.string.home)))
                .addItem(new BottomNavigationItem(R.drawable.ic_book_white_24dp, getString(R.string.order)))
                .addItem(new BottomNavigationItem(R.drawable.ic_music_note_white_24dp, getString(R.string.mine)))
                .initialise();
        bottom_bar.setMode(BottomNavigationBar.MODE_FIXED);
        // bottom_bar.setBackgroundStyle(bottom_bar.BACKGROUND_STYLE_RIPPLE);
        bottom_bar.setBarBackgroundColor("#9ACD32");
        bottom_bar.setBackgroundColor(bottom_bar.BACKGROUND_STYLE_STATIC);
    }
}
