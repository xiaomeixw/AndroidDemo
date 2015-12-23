package sabria.demo.zoomtextview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.TextView;

/**
 * Created by xiongwei,An Android project Engineer.
 * Date:2015-12-23  10:15
 * Base on Meilimei.com (PHP Service)
 * Describe:
 * Version:1.0
 * Open source
 */
public class ZoomTextView extends TextView {

    private static final String TAG = "ZoomTextView";

    float defaultSize;
    ScaleGestureDetector mScaleDetector;
    //限制最大只能三倍
    private float zoomLimit = 3.0f;
    float mScaleFactor=1.f;


    public ZoomTextView(Context context) {
        super(context);
        initialize();
    }

    public ZoomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public ZoomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }




    private void initialize() {

        defaultSize = getTextSize();
        //1.初始化ScaleGestureDetector
        mScaleDetector = new ScaleGestureDetector(getContext(), new MyScaleListener());


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /*@Override
    public boolean onTouchEvent(@NonNull MotionEvent ev) {
        super.onTouchEvent(ev);
        mScaleDetector.onTouchEvent(ev);
        return true;
    }*/

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //2.将onTouchEvent事件转交ScaleGestureDetector处理
        return mScaleDetector.onTouchEvent(event);
    }

    //1.并指定Listener
    private class MyScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {

        /**
         * onScaleBegin 一定要返回true才会进入onScale()这个函数
         * SimpleOnScaleGestureListener 实现就是return true.
         * @param detector
         * @return
         */
        @Override
        public boolean onScale(ScaleGestureDetector detector) {

            Log.e(TAG, String.valueOf(detector.getScaleFactor()));

            //detector.getScaleFactor()：返回从前一个伸缩事件至当前伸缩事件的伸缩比率
            mScaleFactor = mScaleFactor * detector.getScaleFactor();

            //mScaleFactor的值在1.0和3.0之间
            mScaleFactor=Math.max(1.0f, Math.min(mScaleFactor, zoomLimit));


            setTextSize(TypedValue.COMPLEX_UNIT_PX, defaultSize*mScaleFactor);

            //Log.e(TAG, String.valueOf(mScaleFactor));


            return true;
        }
    }
}
