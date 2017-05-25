package com.pl.lbapps;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.pl.lbapps.view.ImageBannerFrameLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * ViewPager实现的轮播图广告自定义视图，如京东首页的广告轮播图效果；
 * 既支持自动轮播页面也支持手势滑动切换页面

 *
 */
public class FragmentOne extends Fragment implements ImageBannerFrameLayout.FramLayoutlisenner{
    private String TAG="fragment1";
    private int[]ids={R.drawable.mimg1,R.drawable.eson2,R.drawable.eson3,R.drawable.eson4};
    private ImageBannerFrameLayout mGroup;
    private static final String[] strs = new String[] {
        "2017年10月1日-10:20", "2017年10月1日-10:50", "2017年10月1日-10:60", "2017年10月1日-12:10", "2017年10月1日-13:20"
    };//定义一个String数组用来显示ListView的内容
    private ListView lv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public void clickImageIndex(int pos){
        Toast.makeText(getActivity(),"pos="+pos,Toast.LENGTH_SHORT).show();
    }
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        ListView lv = (ListView) view.findViewById(R.id.lv);
                mGroup = (ImageBannerFrameLayout) view.findViewById(R.id.image_group);
        /*为ListView设置Adapter来绑定数据*/
        lv.setAdapter(new ArrayAdapter<String>(getActivity(),
                R.layout.my_listitem,R.id.ItemTitle,strs));

        mImageBanner();
        return view;
    }
    private void mImageBanner(){
        mGroup.setListener(this);
        List<Bitmap> list=new ArrayList<>();
        for (int i=0; i< ids.length; i++){
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),ids[i]);
            list.add(bitmap);
        }
        mGroup.addBitmaps(list);
    }
}
