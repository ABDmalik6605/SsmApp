package com.micromerger.ssms.utils.widgets;

import android.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import com.micromerger.ssms.utils.Strings;
import com.micromerger.ssms.utils.widgets.TextWatcherAdapter;

/* loaded from: classes2.dex */
public class ClearableEditText extends EditText implements View.OnTouchListener, View.OnFocusChangeListener, TextWatcherAdapter.TextWatcherListener {
    private View.OnFocusChangeListener f;
    private View.OnTouchListener l;
    private Listener listener;
    private Location loc;
    private Drawable xD;

    public interface Listener {
        void didClearText();
    }

    public enum Location {
        LEFT(0),
        RIGHT(2);

        final int idx;

        Location(int idx) {
            this.idx = idx;
        }
    }

    public ClearableEditText(Context context) {
        super(context);
        this.loc = Location.RIGHT;
        init();
    }

    public ClearableEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.loc = Location.RIGHT;
        init();
    }

    public ClearableEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.loc = Location.RIGHT;
        init();
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public void setIconLocation(Location loc) {
        this.loc = loc;
        initIcon();
    }

    @Override // android.view.View
    public void setOnTouchListener(View.OnTouchListener l) {
        this.l = l;
    }

    @Override // android.view.View
    public void setOnFocusChangeListener(View.OnFocusChangeListener f) {
        this.f = f;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View v, MotionEvent event) {
        if (getDisplayedDrawable() != null) {
            int x = (int) event.getX();
            int y = (int) event.getY();
            if (x >= (this.loc == Location.LEFT ? 0 : (getWidth() - getPaddingRight()) - this.xD.getIntrinsicWidth()) && x <= (this.loc == Location.LEFT ? getPaddingLeft() + this.xD.getIntrinsicWidth() : getWidth()) && y >= 0 && y <= getBottom() - getTop()) {
                if (event.getAction() == 1) {
                    setText("");
                    Listener listener = this.listener;
                    if (listener != null) {
                        listener.didClearText();
                    }
                }
                return true;
            }
        }
        View.OnTouchListener onTouchListener = this.l;
        if (onTouchListener != null) {
            return onTouchListener.onTouch(v, event);
        }
        return false;
    }

    @Override // android.view.View.OnFocusChangeListener
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            setClearIconVisible(Strings.isNotEmpty(getText()));
        } else {
            setClearIconVisible(false);
        }
        View.OnFocusChangeListener onFocusChangeListener = this.f;
        if (onFocusChangeListener != null) {
            onFocusChangeListener.onFocusChange(v, hasFocus);
        }
    }

    @Override // com.micromerger.ssms.utils.widgets.TextWatcherAdapter.TextWatcherListener
    public void onTextChanged(EditText view, String text) {
        if (isFocused()) {
            setClearIconVisible(Strings.isNotEmpty(text));
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawables(Drawable left, Drawable top, Drawable right, Drawable bottom) {
        super.setCompoundDrawables(left, top, right, bottom);
        initIcon();
    }

    private void init() {
        super.setOnTouchListener(this);
        super.setOnFocusChangeListener(this);
        addTextChangedListener(new TextWatcherAdapter(this, this));
        initIcon();
        setClearIconVisible(false);
    }

    private void initIcon() {
        this.xD = null;
        if (this.loc != null) {
            this.xD = getCompoundDrawables()[this.loc.idx];
        }
        if (this.xD == null) {
            this.xD = getResources().getDrawable(R.drawable.presence_offline);
        }
        Drawable drawable = this.xD;
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), this.xD.getIntrinsicHeight());
        int paddingTop = getPaddingTop() + this.xD.getIntrinsicHeight() + getPaddingBottom();
        if (getSuggestedMinimumHeight() < paddingTop) {
            setMinimumHeight(paddingTop);
        }
    }

    private Drawable getDisplayedDrawable() {
        if (this.loc != null) {
            return getCompoundDrawables()[this.loc.idx];
        }
        return null;
    }

    protected void setClearIconVisible(boolean visible) {
        Drawable[] compoundDrawables = getCompoundDrawables();
        if (visible != (getDisplayedDrawable() != null)) {
            Drawable drawable = visible ? this.xD : null;
            Drawable drawable2 = this.loc == Location.LEFT ? drawable : compoundDrawables[0];
            Drawable drawable3 = compoundDrawables[1];
            if (this.loc != Location.RIGHT) {
                drawable = compoundDrawables[2];
            }
            super.setCompoundDrawables(drawable2, drawable3, drawable, compoundDrawables[3]);
        }
    }
}
