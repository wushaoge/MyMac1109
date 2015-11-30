package com.wsgmac1105.demo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Scroller;

import com.wsgmac1105.demo.R;
import com.wsgmac1105.demo.util.Tools;

import butterknife.Bind;

/**
 * Created by wushaoge on 15/11/27.
 */
public class DragLoginView2 extends LinearLayout {

    private final String TAG = "DragLoginView2";


    private ViewDragHelper mViewDragHelper;

    private boolean isOpen = true;

    private View myView;
    private LinearLayout slidingLayer1;
    private ImageView ivClose;
    private RelativeLayout rlHead;
    private Button btnWeibo;
    private Button btnQq;


    private Context mContext;

    private int screenHeight = 0; //屏幕高度
    private int mHeight = 0; //总高度
    private int headHeight = 0; //登陆那个头部高度
    private int shengyuHeight = 0; //剩余高度

    private Scroller mScroller;

    DefaultDragHelper defaultDragHelper;

    public DragLoginView2(Context context) {
        super(context);
        Log.e(TAG, "1");
        initView(context,null,0);
    }

    public DragLoginView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.e(TAG, "2");
        initView(context,attrs,0);
    }

    public DragLoginView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.e(TAG, "3");
        initView(context,attrs,defStyleAttr);
    }


    private void initView(Context mContext,AttributeSet attrs, int defStyle) {
        this.mContext = mContext;

        mScroller = new Scroller(mContext);

        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.DragLoginView2, defStyle, 0);

        isOpen = a.getBoolean(R.styleable.DragLoginView2_isOpen,true);

        defaultDragHelper = new DefaultDragHelper();
        mViewDragHelper = ViewDragHelper.create(this,1.0f,defaultDragHelper);
    }


    public void open(){
        defaultDragHelper.openLogin();
    }

    public void close(){
        defaultDragHelper.closeLogin();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        Log.e(TAG, "onFinishInflate");

        myView = LayoutInflater.from(mContext).inflate(R.layout.view_drag_helper_layout, this,false);
        this.addView(myView);

        slidingLayer1 =  (LinearLayout)myView.findViewById(R.id.slidingLayer1);
        ivClose = (ImageView)myView.findViewById(R.id.iv_close);
        rlHead = (RelativeLayout)myView.findViewById(R.id.rl_head);
        btnWeibo = (Button)myView.findViewById(R.id.btn_weibo);
        btnQq = (Button)myView.findViewById(R.id.btn_qq);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //计算出所有的childView的宽和高  //计算出所有的childView的宽和高
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        screenHeight = Tools.getWindowHeigh(mContext);
//        mHeight = MeasureSpec.getSize(heightMeasureSpec); //这个布局的高度
        mHeight = myView.getMeasuredHeight();
        headHeight = rlHead.getMeasuredHeight();
        shengyuHeight = mHeight - headHeight;

        Log.e(TAG,"屏幕高度"+screenHeight);
        Log.e(TAG,"高度1---"+mHeight);
        Log.e(TAG, "高度2---" + slidingLayer1.getMeasuredHeight());
        Log.e(TAG, "高度3---" + myView.getMeasuredHeight());
        Log.e(TAG, "登陆头部的高度---" + rlHead.getMeasuredHeight());
        Log.e(TAG, "剩余高度---" + shengyuHeight);
        Log.e(TAG,isOpen+"");

        if(isOpen){
            //open();
            myView.scrollTo(0,0);
        }else{
            //close();
            myView.scrollTo(0,-shengyuHeight);
        }

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }


    @Override
    public void computeScroll() {
        if(mViewDragHelper.continueSettling(true)){
            postInvalidate();
        }
    }



    private class DefaultDragHelper extends ViewDragHelper.Callback{

        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return true;
        }

        //手指释放的时候回调
        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            //这边其实只需要一个判断就可以了  因为我们只计算最后点的位置
            Log.e(TAG,"距离高度"+myView.getTop());
            Log.e(TAG, "剩余高度" + shengyuHeight / 2.0f);
            if(yvel < 0){ //上拉  这个yvel其实是手指滑动的惯性高度  使劲往上滑松手就是负值  如果慢慢滑动松手  yve一只是0
                if(myView.getTop() < shengyuHeight/2.0f){
                        openLogin();
                }else{
                        closeLogin();
                }
            }else{ //下拉
                if(myView.getTop() < shengyuHeight/2.0f){
                    openLogin();
                }else{
                    closeLogin();
                }
            }
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
            //Log.e(TAG,"拖动过程中top的值"+top);
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            Log.e(TAG, "拖动的高度" + top);
            if(top<0){  //小于0说明向上拖动
                return getPaddingTop();
            }
            return top;
        }

        @Override
        public void onViewDragStateChanged(int state) {
            super.onViewDragStateChanged(state);
        }


        @Override
        public void onEdgeDragStarted(int edgeFlags, int pointerId) {
            super.onEdgeDragStarted(edgeFlags, pointerId);
        }



        //开启动画并打开
        private void openLogin(){
            Log.e(TAG,"打开");
            mViewDragHelper.smoothSlideViewTo(myView, 0, 0);
            isOpen = true;
            invalidate();
        }


        //关闭
        private void closeLogin(){
            Log.e(TAG,"关闭"+shengyuHeight);
            mViewDragHelper.smoothSlideViewTo(myView, 0, shengyuHeight);
            isOpen = false;
            invalidate();
        }


    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        Log.d(TAG, "onInterceptTouchEvent");
        final int action = MotionEventCompat.getActionMasked(event);
        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
            mViewDragHelper.cancel();
            return false;
        }
        boolean flag=mViewDragHelper.shouldInterceptTouchEvent(event);
        Log.d(TAG,"onInterceptTouchEvent:"+flag);
        return flag;

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent");
        mViewDragHelper.processTouchEvent(event);
        return true;
    }



}
