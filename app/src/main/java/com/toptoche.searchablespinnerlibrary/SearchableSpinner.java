package com.toptoche.searchablespinnerlibrary;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import com.toptoche.searchablespinnerlibrary.SearchableListDialog;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class SearchableSpinner extends Spinner implements View.OnTouchListener, SearchableListDialog.SearchableItem {
    public static final int NO_ITEM_SELECTED = -1;
    private ArrayAdapter _arrayAdapter;
    private Context _context;
    private boolean _isDirty;
    private boolean _isFromInit;
    private List _items;
    private SearchableListDialog _searchableListDialog;
    private String _strHintText;

    public SearchableSpinner(Context context) {
        super(context);
        this._context = context;
        init();
    }

    public SearchableSpinner(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this._context = context;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SearchableSpinner);
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i);
            if (index == R.styleable.SearchableSpinner_hintText) {
                this._strHintText = typedArrayObtainStyledAttributes.getString(index);
            }
        }
        typedArrayObtainStyledAttributes.recycle();
        init();
    }

    public SearchableSpinner(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this._context = context;
        init();
    }

    private void init() {
        ArrayList arrayList = new ArrayList();
        this._items = arrayList;
        SearchableListDialog searchableListDialogNewInstance = SearchableListDialog.newInstance(arrayList);
        this._searchableListDialog = searchableListDialogNewInstance;
        searchableListDialogNewInstance.setOnSearchableItemClickListener(this);
        setOnTouchListener(this);
        this._arrayAdapter = (ArrayAdapter) getAdapter();
        if (TextUtils.isEmpty(this._strHintText)) {
            return;
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(this._context, android.R.layout.simple_list_item_1, new String[]{this._strHintText});
        this._isFromInit = true;
        setAdapter((SpinnerAdapter) arrayAdapter);
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (!this._searchableListDialog.isAdded() && motionEvent.getAction() == 1 && this._arrayAdapter != null) {
            this._items.clear();
            for (int i = 0; i < this._arrayAdapter.getCount(); i++) {
                this._items.add(this._arrayAdapter.getItem(i));
            }
            this._searchableListDialog.show(scanForActivity(this._context).getFragmentManager(), "TAG");
        }
        return true;
    }

    @Override // android.widget.AdapterView
    public void setAdapter(SpinnerAdapter spinnerAdapter) {
        if (!this._isFromInit) {
            this._arrayAdapter = (ArrayAdapter) spinnerAdapter;
            if (!TextUtils.isEmpty(this._strHintText) && !this._isDirty) {
                super.setAdapter((SpinnerAdapter) new ArrayAdapter(this._context, android.R.layout.simple_list_item_1, new String[]{this._strHintText}));
                return;
            } else {
                super.setAdapter(spinnerAdapter);
                return;
            }
        }
        this._isFromInit = false;
        super.setAdapter(spinnerAdapter);
    }

    @Override // com.toptoche.searchablespinnerlibrary.SearchableListDialog.SearchableItem
    public void onSearchableItemClicked(Object obj, int i) {
        setSelection(this._items.indexOf(obj));
        if (this._isDirty) {
            return;
        }
        this._isDirty = true;
        setAdapter((SpinnerAdapter) this._arrayAdapter);
        setSelection(this._items.indexOf(obj));
    }

    public void setTitle(String str) {
        this._searchableListDialog.setTitle(str);
    }

    public void setPositiveButton(String str) {
        this._searchableListDialog.setPositiveButton(str);
    }

    public void setPositiveButton(String str, DialogInterface.OnClickListener onClickListener) {
        this._searchableListDialog.setPositiveButton(str, onClickListener);
    }

    public void setOnSearchTextChangedListener(SearchableListDialog.OnSearchTextChanged onSearchTextChanged) {
        this._searchableListDialog.setOnSearchTextChangedListener(onSearchTextChanged);
    }

    private Activity scanForActivity(Context context) {
        if (context == null) {
            return null;
        }
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            return scanForActivity(((ContextWrapper) context).getBaseContext());
        }
        return null;
    }

    @Override // android.widget.AdapterView
    public int getSelectedItemPosition() {
        if (TextUtils.isEmpty(this._strHintText) || this._isDirty) {
            return super.getSelectedItemPosition();
        }
        return -1;
    }

    @Override // android.widget.AdapterView
    public Object getSelectedItem() {
        if (TextUtils.isEmpty(this._strHintText) || this._isDirty) {
            return super.getSelectedItem();
        }
        return null;
    }
}