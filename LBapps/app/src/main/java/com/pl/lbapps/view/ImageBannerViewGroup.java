package com.pl.lbapps.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

import java.util.Timer;
import java.util.TimerTask;

/**
 * C该类实现突破轮播的核心类
 */

public class ImageBannerViewGroup extends ViewGroup{
    private int children;
    private int childwidth;
    private int childheight;
    private int x ;//此时的x的值，代表的是第一次按下的位置的坐标、每一次移动过程中 移动之前的位置横坐标
    private int index  = 0 ;//每张图片的索引

    //实现图片的点击事件获取
    //采用一个单击变量开关判断，在用户离开屏幕的一瞬间去判断变量开关用户的操作是点击还是移动
    private boolean isClick;//判断是否是点击事件

    private ImageBannerLister lister;
    public ImageBannerLister getLister() {
        return lister;
    }

    public void setLister(ImageBannerLister lister) {
        this.lister = lister;
    }

    public interface ImageBannerLister{
        void clickImageIndex(int pos);//pos代表的是我们当前图片的索引
    }
    private Scroller scroller;//Scrollerd对象轮播
    //自动轮播，采用timer,TimerTask,Handler 三者想结合的方式实现自动轮播
    //我们会抽两个方法来控制，是否自动轮播，我们称之为starAuto()，stopAuto();
    //定义一个boolean变量是否开启自动轮播

    private ImageBarnnerViewGroupLisnner barnnerViewGroupLisnner;

    public ImageBarnnerViewGroupLisnner getBarnnerViewGroupLisnner() {
        return barnnerViewGroupLisnner;
    }

    public void setBarnnerViewGroupLisnner(ImageBarnnerViewGroupLisnner barnnerViewGroupLisnner) {
        this.barnnerViewGroupLisnner = barnnerViewGroupLisnner;
    }

    private boolean isAuto = true;
    private Timer timer =new Timer();
    private TimerTask task ;
    private Handler autoHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0://此时我们需要图骗的自动轮播
                    if(++index >= children){//说明我们此时如果最后一张图片的话，我们将会从第一张图片开始重新滑动
                        index = 0;
                    }
                    scrollTo(childwidth * index,0);
                    barnnerViewGroupLisnner.selectImage(index);
                    break;
            }
        }
    };
    private void starAuto(){
            isAuto = true;
    }
    private void stopAuto(){
        isAuto = false;
    }
    public ImageBannerViewGroup(Context context) {
        super(context);
        initObj();
    }

    public ImageBannerViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        initObj();
    }
    private void initObj(){
        scroller =new Scroller(getContext());
        task =new TimerTask(){
            @Override
            public void run() {
                if(isAuto){//判断开启轮播图
                    autoHandler.sendEmptyMessage(0);
                }
            }
        };
        timer.schedule(task,100,3000);
    }

    /**
     * 轮播图底部远点的功能
     * 1、我们需要自定义一个继承自Fragmentlayout的布局的特性(在同位置不同的view最终显示的最后一个View)
     * 就可以实现底部圆点的布局。
     * 2、我们需要独步原点的素材，就是底部的素材，我们可以利用Drawable的功能，区实现一个原点图片的展示
     *3、我们就需要继承FragmentLayout 来自定义一个类，在该类的实现过程中，我们去加载我们刚才自定义的ImageBannerViewGroup
     * 的核心类和我们圆点布局Linux
     */
    @Override
    public void computeScroll() {
        super.computeScroll();
        if(scroller.computeScrollOffset()){
            scrollTo(scroller.getCurrX(),0);
            invalidate();
        }
    }

    public ImageBannerViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 我们在自定义方法viewgroup中我们必须实现的方法有： 测量-》布局-》绘制
     * 那么对于来说就是： onMeasure()
     * 我们对于绘制来说，因为我们是自定义的ViewGroup容器，容器的绘制我们只需要掉头系统自带的绘制，即可，
     * 也就是说，对于ViewGroup的绘制过程我们不需要再重写该方法，调用系统自带的即可
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        /**
         * 我们应该知道容器的所有子视图
         * 我们要知道viewGroup的宽度和高度，那我么我们要去测量自视图的宽度和高度的和
         */
        children  =getChildCount();//子视图个数
        if(0 == children){
            setMeasuredDimension(0,0);
        }else {
            //测量自视图的宽度和高度
            measureChildren(widthMeasureSpec,heightMeasureSpec);
            //此时我们以第一个自视图为基准
            View view = getChildAt(0);//因为第一个自视图是一定存在的
            childwidth = view.getMeasuredWidth();
            childheight = view.getMeasuredHeight();
            int width = view.getMeasuredWidth() * children;//宽度是所有自视图的总和
            setMeasuredDimension(width,childheight);
        }
    }
    /**
     * 事件的传递过程中的调用方法：我们需要调用容器的拦截方法 onInterceptTouchEvent
     * 针对该方法我们可以理解为，如果说该方法的返回值为true的时候，那么我们自定义的ViewGroup容器就会处理
     * 此次拦截事件，如果说 返回值为false 的时候，那么我们自定义的viewGroup容器将不会接收此次事件的处理过程，将会继续向下传递改事件
     * 针对我们自定义的ViewGroup 我们当然希望我们的viewGroup 容器处理接收事件我们的返回值就是true
     * 如果返回true的话，真正处理该事件的方法是onTouch方法
     */
    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN://表示用户 按下的一瞬间
                //禁用自动轮播
                stopAuto();
                //判断图片试试否滑动完成
                if(!scroller.isFinished()){
                    scroller.abortAnimation();
                }
                isClick = true;
                x = (int) ev.getX();
                break;
            case MotionEvent.ACTION_MOVE://表示用户 按下之后再屏幕上移动的过程
                int moveX = (int) ev.getX();
                int distance  = moveX - x ;
                scrollBy(-distance,0);
                x = moveX;
                isClick = false;
                break;
            case MotionEvent.ACTION_UP://表示用户抬起一瞬间
                int scrollX =getScrollX();
                index = (scrollX + childwidth / 2) / childwidth;
                    if (index < 0){
                        index = 0;
                    }else if (index > children - 1){
                        index = children - 1;
                    }
                if (isClick){//代表点击事件
                    lister.clickImageIndex(index);
                }else {
                    int dx = index * childwidth - scrollX;
                    scroller.startScroll(scrollX, 0, dx, 0);
                    postInvalidate();
                    barnnerViewGroupLisnner.selectImage(index);
                }
                starAuto();
               // scrollTo(index * childwidth,0);
                break;
            default:
                break;
        }
        return true;//返回true的目的告诉我们改Viewgroup容器的夫View 我们已经处理好了该事件
    }

    /**
     *
     * 继承viewgroup必须实现布局onlayout方法
     * @param b 代表是当ViewGroup发生改变的时候为true，没有发生改变为FALSE
     *
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if(changed){
            int leftMargin = 0;
            for (int i = 0; i < children; i++){
                View view = getChildAt(i);
                view.layout(leftMargin, 0,leftMargin + childwidth,childheight);
                leftMargin += childwidth;
            }
        }
    }
    public interface ImageBarnnerViewGroupLisnner{
        void selectImage(int index);
    }
}
