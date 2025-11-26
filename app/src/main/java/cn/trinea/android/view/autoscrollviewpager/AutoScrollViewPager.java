package cn.trinea.android.view.autoscrollviewpager;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.Interpolator;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import java.lang.reflect.Field;

/* loaded from: classes.dex */
public class AutoScrollViewPager extends ViewPager {
    public static final int DEFAULT_INTERVAL = 1500;
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int SCROLL_WHAT = 0;
    public static final int SLIDE_BORDER_MODE_CYCLE = 1;
    public static final int SLIDE_BORDER_MODE_NONE = 0;
    public static final int SLIDE_BORDER_MODE_TO_PARENT = 2;
    private int direction;
    private float downX;
    private Handler handler;
    private long interval;
    private boolean isAutoScroll;
    private boolean isBorderAnimation;
    private boolean isCycle;
    private boolean isStopByTouch;
    private CustomDurationScroller scroller;
    private int slideBorderMode;
    private boolean stopScrollWhenTouch;
    private float touchX;

    public AutoScrollViewPager(Context context) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        super(context);
        this.interval = 1500L;
        this.direction = 1;
        this.isCycle = true;
        this.stopScrollWhenTouch = true;
        this.slideBorderMode = 0;
        this.isBorderAnimation = true;
        this.isAutoScroll = false;
        this.isStopByTouch = false;
        this.touchX = 0.0f;
        this.downX = 0.0f;
        this.scroller = null;
        init();
    }

    public AutoScrollViewPager(Context context, AttributeSet attributeSet) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        super(context, attributeSet);
        this.interval = 1500L;
        this.direction = 1;
        this.isCycle = true;
        this.stopScrollWhenTouch = true;
        this.slideBorderMode = 0;
        this.isBorderAnimation = true;
        this.isAutoScroll = false;
        this.isStopByTouch = false;
        this.touchX = 0.0f;
        this.downX = 0.0f;
        this.scroller = null;
        init();
    }

    private void init() throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        this.handler = new MyHandler();
        setViewPagerScroller();
    }

    public void startAutoScroll() {
        this.isAutoScroll = true;
        sendScrollMessage(this.interval);
    }

    public void startAutoScroll(int i) {
        this.isAutoScroll = true;
        sendScrollMessage(i);
    }

    public void stopAutoScroll() {
        this.isAutoScroll = false;
        this.handler.removeMessages(0);
    }

    public void setScrollDurationFactor(double d) {
        this.scroller.setScrollDurationFactor(d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendScrollMessage(long j) {
        this.handler.removeMessages(0);
        this.handler.sendEmptyMessageDelayed(0, j);
    }

    private void setViewPagerScroller() throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        try {
            Field declaredField = ViewPager.class.getDeclaredField("mScroller");
            declaredField.setAccessible(true);
            Field declaredField2 = ViewPager.class.getDeclaredField("sInterpolator");
            declaredField2.setAccessible(true);
            CustomDurationScroller customDurationScroller = new CustomDurationScroller(getContext(), (Interpolator) declaredField2.get(null));
            this.scroller = customDurationScroller;
            declaredField.set(this, customDurationScroller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void scrollOnce() {
        int count;
        PagerAdapter adapter = getAdapter();
        int currentItem = getCurrentItem();
        if (adapter == null || (count = adapter.getCount()) <= 1) {
            return;
        }
        int i = this.direction == 0 ? currentItem - 1 : currentItem + 1;
        if (i < 0) {
            if (this.isCycle) {
                setCurrentItem(count - 1, this.isBorderAnimation);
            }
        } else {
            if (i == count) {
                if (this.isCycle) {
                    setCurrentItem(0, this.isBorderAnimation);
                    return;
                }
                return;
            }
            setCurrentItem(i, true);
        }
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.stopScrollWhenTouch) {
            if (motionEvent.getAction() == 0 && this.isAutoScroll) {
                this.isStopByTouch = true;
                stopAutoScroll();
            } else if (motionEvent.getAction() == 1 && this.isStopByTouch) {
                startAutoScroll();
            }
        }
        int i = this.slideBorderMode;
        if (i == 2 || i == 1) {
            this.touchX = motionEvent.getX();
            if (motionEvent.getAction() == 0) {
                this.downX = this.touchX;
            }
            int currentItem = getCurrentItem();
            PagerAdapter adapter = getAdapter();
            int count = adapter == null ? 0 : adapter.getCount();
            if ((currentItem == 0 && this.downX <= this.touchX) || (currentItem == count - 1 && this.downX >= this.touchX)) {
                if (this.slideBorderMode == 2) {
                    getParent().requestDisallowInterceptTouchEvent(false);
                } else {
                    if (count > 1) {
                        setCurrentItem((count - currentItem) - 1, this.isBorderAnimation);
                    }
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                return super.onTouchEvent(motionEvent);
            }
        }
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.onTouchEvent(motionEvent);
    }

    private class MyHandler extends Handler {
        private MyHandler() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what != 0) {
                return;
            }
            AutoScrollViewPager.this.scrollOnce();
            AutoScrollViewPager autoScrollViewPager = AutoScrollViewPager.this;
            autoScrollViewPager.sendScrollMessage(autoScrollViewPager.interval);
        }
    }

    public long getInterval() {
        return this.interval;
    }

    public void setInterval(long j) {
        this.interval = j;
    }

    public int getDirection() {
        return this.direction == 0 ? 0 : 1;
    }

    public void setDirection(int i) {
        this.direction = i;
    }

    public boolean isCycle() {
        return this.isCycle;
    }

    public void setCycle(boolean z) {
        this.isCycle = z;
    }

    public boolean isStopScrollWhenTouch() {
        return this.stopScrollWhenTouch;
    }

    public void setStopScrollWhenTouch(boolean z) {
        this.stopScrollWhenTouch = z;
    }

    public int getSlideBorderMode() {
        return this.slideBorderMode;
    }

    public void setSlideBorderMode(int i) {
        this.slideBorderMode = i;
    }

    public boolean isBorderAnimation() {
        return this.isBorderAnimation;
    }

    public void setBorderAnimation(boolean z) {
        this.isBorderAnimation = z;
    }
}
