package com.pl.lbapps.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.pl.lbapps.R;

import java.util.List;

/**
 * Created by Administrator on 2017/4/5.
 */

public class ImageBannerFrameLayout extends FrameLayout implements  ImageBannerViewGroup.ImageBarnnerViewGroupLisnner,ImageBannerViewGroup.ImageBannerLister{
    private ImageBannerViewGroup imageBannerViewGroup;
    private  LinearLayout linearLayout;
    private FramLayoutlisenner lisenner;

    public FramLayoutlisenner getLisenner() {
        return lisenner;
    }

    public void setListener(FramLayoutlisenner lisenner) {
        this.lisenner = lisenner;
    }

    public ImageBannerFrameLayout(Context context) {
        super(context);
        initImageBannerViewGroup();
        initDotLinearlayout();
    }

    public ImageBannerFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initImageBannerViewGroup();
        initDotLinearlayout();
    }

    public ImageBannerFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initImageBannerViewGroup();
        initDotLinearlayout();
    }

    public void addBitmaps(List<Bitmap> list){
        for (int i =0;i < list.size();i++){
            Bitmap bitmap = list.get(i);
            addBitmapToImageBannerViewGroup(bitmap);
            addDotLinearlayout();
        }
    }
    private void addDotLinearlayout(){
        ImageView iv =new ImageView(getContext());
        LinearLayout.LayoutParams lp =new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(5,5,5,5);
        iv.setLayoutParams(lp);
        iv.setImageResource(R.drawable.dot_normal);
        linearLayout.addView(iv);
    }
    private void addBitmapToImageBannerViewGroup( Bitmap bitmap){
        ImageView iv =new ImageView(getContext());
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        iv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        iv.setImageBitmap(bitmap);
        imageBannerViewGroup.addView(iv);
    }

    /**
     * 初始化我们自定义的图片轮播的轮播核心类
     */
    private void initImageBannerViewGroup(){
        imageBannerViewGroup = new ImageBannerViewGroup(getContext());
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        imageBannerViewGroup.setLayoutParams(lp);
        imageBannerViewGroup.setBarnnerViewGroupLisnner(this);//这里就是讲Listnner 传递FramLayout
        imageBannerViewGroup.setLister(this);
        addView(imageBannerViewGroup);
    }

    /**
     * 我们需要初始化底部圆点布局
     */
    private void initDotLinearlayout(){
        linearLayout =new LinearLayout(getContext());
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, 40);
        linearLayout.setLayoutParams(lp);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setBackgroundColor(Color.parseColor("#F7F7F7"));
        addView(linearLayout);
        FrameLayout.LayoutParams layoutParams = (LayoutParams) linearLayout.getLayoutParams();
        layoutParams.gravity = Gravity.BOTTOM;
        linearLayout.setLayoutParams(layoutParams);

        //知识点：在3.0以后 我们使用的是 setAlpha(),在3.0之前也是setAlpha()，但调用不同
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            linearLayout.setAlpha(0.5f);
        }else {
            linearLayout.getBackground().setAlpha(100);
        }
    }

    @Override
    public void selectImage(int index) {
        int count = linearLayout.getChildCount();
        for (int i = 0; i< count; i++){
            ImageView iv = (ImageView) linearLayout.getChildAt(i);
            if(i == index){
                iv.setImageResource(R.drawable.dot_select);
            }else{
                iv.setImageResource(R.drawable.dot_normal);
            }
        }
    }

    @Override
    public void clickImageIndex(int pos) {
        lisenner.clickImageIndex(pos);
    }
    public interface  FramLayoutlisenner{
        void clickImageIndex(int pos);
    }
}
