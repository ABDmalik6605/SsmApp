package com.toptoche.searchablespinnerlibrary;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.SearchManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.Serializable;
import java.util.List;

/* loaded from: classes2.dex */
public class SearchableListDialog extends DialogFragment implements SearchView.OnQueryTextListener, SearchView.OnCloseListener {
    private static final String ITEMS = "items";
    private ListView _listViewItems;
    private DialogInterface.OnClickListener _onClickListener;
    private OnSearchTextChanged _onSearchTextChanged;
    private SearchView _searchView;
    private SearchableItem _searchableItem;
    private String _strPositiveButtonText;
    private String _strTitle;
    private ArrayAdapter listAdapter;

    public interface OnSearchTextChanged {
        void onSearchTextChanged(String str);
    }

    public interface SearchableItem<T> extends Serializable {
        void onSearchableItemClicked(T t, int i);
    }

    @Override // android.widget.SearchView.OnCloseListener
    public boolean onClose() {
        return false;
    }

    public static SearchableListDialog newInstance(List list) {
        SearchableListDialog searchableListDialog = new SearchableListDialog();
        Bundle bundle = new Bundle();
        bundle.putSerializable("items", (Serializable) list);
        searchableListDialog.setArguments(bundle);
        return searchableListDialog;
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        getDialog().getWindow().setSoftInputMode(2);
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    @Override // android.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(getActivity());
        if (bundle != null) {
            this._searchableItem = (SearchableItem) bundle.getSerializable("item");
        }
        View viewInflate = layoutInflaterFrom.inflate(R.layout.searchable_list_dialog, (ViewGroup) null);
        setData(viewInflate);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(viewInflate);
        String str = this._strPositiveButtonText;
        if (str == null) {
            str = "CLOSE";
        }
        builder.setPositiveButton(str, this._onClickListener);
        String str2 = this._strTitle;
        if (str2 == null) {
            str2 = "Select Item";
        }
        builder.setTitle(str2);
        AlertDialog alertDialogCreate = builder.create();
        alertDialogCreate.getWindow().setSoftInputMode(2);
        return alertDialogCreate;
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putSerializable("item", this._searchableItem);
        super.onSaveInstanceState(bundle);
    }

    public void setTitle(String str) {
        this._strTitle = str;
    }

    public void setPositiveButton(String str) {
        this._strPositiveButtonText = str;
    }

    public void setPositiveButton(String str, DialogInterface.OnClickListener onClickListener) {
        this._strPositiveButtonText = str;
        this._onClickListener = onClickListener;
    }

    public void setOnSearchableItemClickListener(SearchableItem searchableItem) {
        this._searchableItem = searchableItem;
    }

    public void setOnSearchTextChangedListener(OnSearchTextChanged onSearchTextChanged) {
        this._onSearchTextChanged = onSearchTextChanged;
    }

    private void setData(View view) {
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(FirebaseAnalytics.Event.SEARCH);
        SearchView searchView = (SearchView) view.findViewById(R.id.search);
        this._searchView = searchView;
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        this._searchView.setIconifiedByDefault(false);
        this._searchView.setOnQueryTextListener(this);
        this._searchView.setOnCloseListener(this);
        this._searchView.clearFocus();
        ((InputMethodManager) getActivity().getSystemService("input_method")).hideSoftInputFromWindow(this._searchView.getWindowToken(), 0);
        List list = (List) getArguments().getSerializable("items");
        this._listViewItems = (ListView) view.findViewById(R.id.listItems);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, list);
        this.listAdapter = arrayAdapter;
        this._listViewItems.setAdapter((ListAdapter) arrayAdapter);
        this._listViewItems.setTextFilterEnabled(true);
        this._listViewItems.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.toptoche.searchablespinnerlibrary.SearchableListDialog.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view2, int i, long j) {
                SearchableListDialog.this._searchableItem.onSearchableItemClicked(SearchableListDialog.this.listAdapter.getItem(i), i);
                SearchableListDialog.this.getDialog().dismiss();
            }
        });
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
        dismiss();
    }

    @Override // android.widget.SearchView.OnQueryTextListener
    public boolean onQueryTextSubmit(String str) {
        this._searchView.clearFocus();
        return true;
    }

    @Override // android.widget.SearchView.OnQueryTextListener
    public boolean onQueryTextChange(String str) {
        if (TextUtils.isEmpty(str)) {
            ((ArrayAdapter) this._listViewItems.getAdapter()).getFilter().filter(null);
        } else {
            ((ArrayAdapter) this._listViewItems.getAdapter()).getFilter().filter(str);
        }
        OnSearchTextChanged onSearchTextChanged = this._onSearchTextChanged;
        if (onSearchTextChanged == null) {
            return true;
        }
        onSearchTextChanged.onSearchTextChanged(str);
        return true;
    }
}