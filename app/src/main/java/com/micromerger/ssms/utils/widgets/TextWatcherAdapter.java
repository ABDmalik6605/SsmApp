package com.micromerger.ssms.utils.widgets;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/* loaded from: classes2.dex */
public class TextWatcherAdapter implements TextWatcher {
    private final TextWatcherListener listener;
    private final EditText view;

    public interface TextWatcherListener {
        void onTextChanged(EditText view, String text);
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable s) {
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    public TextWatcherAdapter(EditText editText, TextWatcherListener listener) {
        this.view = editText;
        this.listener = listener;
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        this.listener.onTextChanged(this.view, s.toString());
    }
}
