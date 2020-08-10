package com.lyw.lib_customview.recyclerview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.ref.WeakReference;

public class AutoScrollReccycleView extends RecyclerView {
    private static final long TIME_AUTO_POLL = 16;
    private static final long TIME_AUTO_POLL_1 = 3000;
    AutoPollTask2 autoPollTask;
    private boolean running; //标示是否正在自动轮询
    private boolean canRun;//标示是否可以自动轮询,可在不需要的是否置false
    private int index = 1;
    private int mTouchSlop = 0;

    public AutoScrollReccycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        autoPollTask = new AutoPollTask2(this);
    }

    static class AutoPollTask implements Runnable {
        private final WeakReference<AutoScrollReccycleView> mReference;

        //使用弱引用持有外部类引用->防止内存泄漏
        public AutoPollTask(AutoScrollReccycleView reference) {
            this.mReference = new WeakReference<AutoScrollReccycleView>(reference);
        }

        @Override
        public void run() {
            AutoScrollReccycleView recyclerView = mReference.get();
            if (recyclerView != null && recyclerView.running && recyclerView.canRun) {
                int height = recyclerView.getChildAt(0).getHeight();
//                recyclerView.scrollBy(0, height*2);
                recyclerView.scrollBy(2, 2);
//                recyclerView.smoothScrollToPosition(++recyclerView.index);
                recyclerView.postDelayed(recyclerView.autoPollTask, recyclerView.TIME_AUTO_POLL);
            }
        }
    }

    static class AutoPollTask2 implements Runnable {
        private final WeakReference<AutoScrollReccycleView> mReference;

        //使用弱引用持有外部类引用->防止内存泄漏
        public AutoPollTask2(AutoScrollReccycleView reference) {
            this.mReference = new WeakReference<AutoScrollReccycleView>(reference);
        }

        @Override
        public void run() {
            AutoScrollReccycleView recyclerView = mReference.get();
            if (recyclerView != null && recyclerView.running && recyclerView.canRun) {
//                recyclerView.scrollBy(0, height*2);
                recyclerView.smoothScrollToPosition(++recyclerView.index);
                recyclerView.postDelayed(recyclerView.autoPollTask, recyclerView.TIME_AUTO_POLL_1);
            }
        }
    }

    //开启:如果正在运行,先停止->再开启
    public void start() {
        if (running)
            stop();
        canRun = true;
        running = true;
        postDelayed(autoPollTask, TIME_AUTO_POLL_1);
    }

    public void stop() {
        running = false;
        removeCallbacks(autoPollTask);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent e) {
//        switch (e.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                if (running)
//                    stop();
//                break;
//            case MotionEvent.ACTION_UP:
//            case MotionEvent.ACTION_CANCEL:
//            case MotionEvent.ACTION_OUTSIDE:
//                if (canRun)
//                    start();
//                break;
//        }
//        return super.onTouchEvent(e);
//    }

    //取消RecyclerView的惯性，使每次手动只能滑一个


    int lastY = 0;
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                lastY = (int) ev.getRawY();
                if (running)
                    stop();
                break;
            case MotionEvent.ACTION_UP:

            case MotionEvent.ACTION_CANCEL:

            case MotionEvent.ACTION_OUTSIDE:
                int nowY = (int) ev.getRawY();
                if (nowY - lastY > mTouchSlop) {//向下滑动
                    smoothScrollToPosition(index == 0 ? 0 : --index);
                    if (canRun)
                        start();
                    return true;
                } else if (lastY - nowY > mTouchSlop) {//向上滑动
                    smoothScrollToPosition(++index);
                    if (canRun)
                        start();
                    return true;
                }else {
                    if (canRun)
                        start();
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
