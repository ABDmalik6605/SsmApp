package com.micromerger.ssms.utils.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Selection;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatEditText;
import com.micromerger.ssms.R;
import com.micromerger.ssms.utils.Constant;

/* loaded from: classes2.dex */
public class MaskedEditText extends AppCompatEditText {
    private static final char ALPHANUMERIC_MASK = '*';
    private static final char ALPHA_MASK = 'A';
    private static final char CHARACTER_MASK = '?';
    private static final char ESCAPE_CHAR = '\\';
    private static final char NUMBER_MASK = '9';
    private static final char PLUS_MASK = '+';
    private String mask;
    private String placeholder;

    private boolean isMaskChar(char mask) {
        return mask == '*' || mask == '+' || mask == '9' || mask == '?' || mask == 'A';
    }

    public MaskedEditText(Context context) {
        this(context, "");
    }

    public MaskedEditText(Context context, String mask) {
        this(context, mask, ' ');
    }

    public MaskedEditText(Context context, String mask, char placeholder) {
        this(context, null, mask, placeholder);
    }

    public MaskedEditText(Context context, AttributeSet attr) {
        this(context, attr, "");
    }

    public MaskedEditText(Context context, AttributeSet attr, String mask) {
        this(context, attr, "", ' ');
    }

    public MaskedEditText(Context context, AttributeSet attr, String mask, char placeholder) {
        super(context, attr);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attr, R.styleable.MaskedEditText);
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i);
            if (index == 4) {
                if (mask.length() <= 0) {
                    mask = typedArrayObtainStyledAttributes.getString(index);
                }
            } else if (index == 5 && typedArrayObtainStyledAttributes.getString(index).length() > 0 && placeholder == ' ') {
                placeholder = typedArrayObtainStyledAttributes.getString(index).charAt(0);
            }
        }
        typedArrayObtainStyledAttributes.recycle();
        this.mask = mask;
        this.placeholder = String.valueOf(placeholder);
        addTextChangedListener(new MaskTextWatcher());
        if (mask.length() > 0) {
            setText(getText());
        }
    }

    public String getMask() {
        return this.mask;
    }

    public void setMask(String mask, int type) {
        if (type == 1) {
            mask = Constant.MOBILENUMBERMASK;
        } else if (type == 2) {
            mask = Constant.LANDLINENUMBERMASK;
        } else if (type == 3) {
            mask = Constant.CNICMASK;
        }
        this.mask = mask;
        setText(getText());
    }

    public char getPlaceholder() {
        return this.placeholder.charAt(0);
    }

    public void setPlaceholder(char placeholder) {
        this.placeholder = String.valueOf(placeholder);
        setText(getText());
    }

    public Editable getText(boolean removeMask) {
        if (!removeMask) {
            return getText();
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(getText());
        stripMaskChars(spannableStringBuilder);
        return spannableStringBuilder;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void formatMask(Editable value) {
        InputFilter[] filters = value.getFilters();
        value.setFilters(new InputFilter[0]);
        Object obj = new Object();
        value.setSpan(obj, Selection.getSelectionStart(value), Selection.getSelectionEnd(value), 17);
        int i = 0;
        boolean z = false;
        int i2 = 0;
        int i3 = 0;
        while (i < this.mask.length()) {
            if (!z && isMaskChar(this.mask.charAt(i))) {
                if (i3 >= value.length()) {
                    value.insert(i3, this.placeholder);
                    PlaceholderSpan placeholderSpan = new PlaceholderSpan();
                    int i4 = i3 + 1;
                    value.setSpan(placeholderSpan, i3, i4, 33);
                    i3 = i4;
                } else if (matchMask(this.mask.charAt(i), value.charAt(i3))) {
                    i3++;
                } else if (value.charAt(i3) == '*') {
                    i2++;
                } else {
                    value.delete(i3, i3 + 1);
                    i--;
                    i2--;
                }
                i2++;
            } else if (z || this.mask.charAt(i) != '\\') {
                value.insert(i3, String.valueOf(this.mask.charAt(i)));
                LiteralSpan literalSpan = new LiteralSpan();
                int i5 = i3 + 1;
                value.setSpan(literalSpan, i3, i5, 33);
                i2++;
                i3 = i5;
                z = false;
            } else {
                z = true;
            }
            i++;
        }
        while (value.length() > i2) {
            int length = value.length() - 1;
            value.delete(length, length + 1);
        }
        Selection.setSelection(value, value.getSpanStart(obj), value.getSpanEnd(obj));
        value.removeSpan(obj);
        value.setFilters(filters);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stripMaskChars(Editable value) {
        PlaceholderSpan[] placeholderSpanArr = (PlaceholderSpan[]) value.getSpans(0, value.length(), PlaceholderSpan.class);
        LiteralSpan[] literalSpanArr = (LiteralSpan[]) value.getSpans(0, value.length(), LiteralSpan.class);
        for (int i = 0; i < placeholderSpanArr.length; i++) {
            value.delete(value.getSpanStart(placeholderSpanArr[i]), value.getSpanEnd(placeholderSpanArr[i]));
        }
        for (int i2 = 0; i2 < literalSpanArr.length; i2++) {
            value.delete(value.getSpanStart(literalSpanArr[i2]), value.getSpanEnd(literalSpanArr[i2]));
        }
    }

    private boolean matchMask(char mask, char value) {
        return ((((mask == '9' && Character.isDigit(value)) || (mask == 'A' && Character.isLetter(value))) || (mask == '*' && (Character.isDigit(value) || Character.isLetter(value)))) || mask == '?') || mask == '+';
    }

    private class MaskTextWatcher implements TextWatcher {
        private boolean updating;

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        private MaskTextWatcher() {
            this.updating = false;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable s) {
            if (this.updating || MaskedEditText.this.mask.length() == 0 || s.toString().trim().isEmpty() || this.updating) {
                return;
            }
            this.updating = true;
            MaskedEditText.this.stripMaskChars(s);
            MaskedEditText.this.formatMask(s);
            this.updating = false;
        }
    }

    private class PlaceholderSpan {
        private PlaceholderSpan() {
        }
    }

    private class LiteralSpan {
        private LiteralSpan() {
        }
    }
}
