package com.pl.lbapps;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private int[]imgId={R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4};
    private int[]ids={R.drawable.mimg1,R.drawable.mimg2,R.drawable.mimg3,R.drawable.mimg4};
    private ImageBannerFrameLayout mGroup;
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
        mGroup = (ImageBannerFrameLayout) view.findViewById(R.id.image_group);
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
