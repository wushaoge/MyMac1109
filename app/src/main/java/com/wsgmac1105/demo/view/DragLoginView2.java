package com.wsgmac1105.demo.view;

import android.content.Context;
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

import com.wsgmac1105.demo.R;
import com.wsgmac1105.demo.util.Tools;

import butterknife.Bind;

/**
 * Created by wushaoge on 15/11/27.
 */
public class DragLoginView2 extends LinearLayout {

    private final String TAG = "DragLoginView2";


    private ViewDragHelper mViewDragHelper;


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

    DefaultDragHelper defaultDragHelper;

    public DragLoginView2(Context context) {
        super(context);
        initView(context);
    }

    public DragLoginView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public DragLoginView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }


    private void initView(Context mContext) {
        this.mContext = mContext;
        defaultDragHelper = new DefaultDragHelper();
        mViewDragHelper = ViewDragHelper.create(this,1.0f,defaultDragHelper);
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
        mHeight = MeasureSpec.getSize(heightMeasureSpec); //这个布局的高度
        headHeight = rlHead.getMeasuredHeight();
        shengyuHeight = mHeight - headHeight;

        Log.e(TAG,"高度"+mHeight);
        Log.e(TAG, "高度" + slidingLayer1.getMeasuredHeight());
        Log.e(TAG, "登陆头部的高度" + rlHead.getMeasuredHeight());

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

            invalidate();
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
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

        private void openLogin(){



        }


        private void closeLogin(){



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
