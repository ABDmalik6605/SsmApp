package com.micromerger.ssms.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public class Settings extends Fragment {
    Button btn_change_pw;
    View mView;
    TextView tv_app_version;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(R.layout.frag_changepass, (ViewGroup) null);
        this.mView = viewInflate;
        TextView textView = (TextView) viewInflate.findViewById(R.id.tv_app_ver);
        this.tv_app_version = textView;
        textView.setText("App version : SSMS (2.91)");
        Button button = (Button) this.mView.findViewById(R.id.btn_change_pw);
        this.btn_change_pw = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.micromerger.ssms.settings.Settings.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FragmentTransaction fragmentTransactionBeginTransaction = Settings.this.getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransactionBeginTransaction.replace(R.id.container, new ChangePassword());
                fragmentTransactionBeginTransaction.addToBackStack(null);
                fragmentTransactionBeginTransaction.commit();
            }
        });
        return this.mView;
    }
}
