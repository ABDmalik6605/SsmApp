package com.micromerger.ssms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import com.micromerger.ssms.R;

/* loaded from: classes2.dex */
public final class FragBackupnresetBinding implements ViewBinding {
    public final ImageView attendanceArrow;
    public final ImageView attendanceIcon;
    public final CardView cardBackup;
    public final CardView cardReset;
    public final CardView cardRestore;
    public final CardView cardSync;
    public final ImageView censusArrow;
    public final ImageView censusIcon;
    public final ImageView enrollmentArrow;
    public final ImageView enrollmentIcon;
    public final ImageView illegalOccupationArrow;
    public final ImageView illegalOccupationIcon;
    private final LinearLayout rootView;

    private FragBackupnresetBinding(LinearLayout rootView, ImageView attendanceArrow, ImageView attendanceIcon, CardView cardBackup, CardView cardReset, CardView cardRestore, CardView cardSync, ImageView censusArrow, ImageView censusIcon, ImageView enrollmentArrow, ImageView enrollmentIcon, ImageView illegalOccupationArrow, ImageView illegalOccupationIcon) {
        this.rootView = rootView;
        this.attendanceArrow = attendanceArrow;
        this.attendanceIcon = attendanceIcon;
        this.cardBackup = cardBackup;
        this.cardReset = cardReset;
        this.cardRestore = cardRestore;
        this.cardSync = cardSync;
        this.censusArrow = censusArrow;
        this.censusIcon = censusIcon;
        this.enrollmentArrow = enrollmentArrow;
        this.enrollmentIcon = enrollmentIcon;
        this.illegalOccupationArrow = illegalOccupationArrow;
        this.illegalOccupationIcon = illegalOccupationIcon;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragBackupnresetBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragBackupnresetBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.frag_backupnreset, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragBackupnresetBinding bind(View rootView) {
        int i = R.id.attendanceArrow;
        ImageView imageView = (ImageView) rootView.findViewById(R.id.attendanceArrow);
        if (imageView != null) {
            i = R.id.attendanceIcon;
            ImageView imageView2 = (ImageView) rootView.findViewById(R.id.attendanceIcon);
            if (imageView2 != null) {
                i = R.id.card_backup;
                CardView cardView = (CardView) rootView.findViewById(R.id.card_backup);
                if (cardView != null) {
                    i = R.id.card_reset;
                    CardView cardView2 = (CardView) rootView.findViewById(R.id.card_reset);
                    if (cardView2 != null) {
                        i = R.id.card_restore;
                        CardView cardView3 = (CardView) rootView.findViewById(R.id.card_restore);
                        if (cardView3 != null) {
                            i = R.id.card_sync;
                            CardView cardView4 = (CardView) rootView.findViewById(R.id.card_sync);
                            if (cardView4 != null) {
                                i = R.id.censusArrow;
                                ImageView imageView3 = (ImageView) rootView.findViewById(R.id.censusArrow);
                                if (imageView3 != null) {
                                    i = R.id.censusIcon;
                                    ImageView imageView4 = (ImageView) rootView.findViewById(R.id.censusIcon);
                                    if (imageView4 != null) {
                                        i = R.id.enrollmentArrow;
                                        ImageView imageView5 = (ImageView) rootView.findViewById(R.id.enrollmentArrow);
                                        if (imageView5 != null) {
                                            i = R.id.enrollmentIcon;
                                            ImageView imageView6 = (ImageView) rootView.findViewById(R.id.enrollmentIcon);
                                            if (imageView6 != null) {
                                                i = R.id.illegalOccupationArrow;
                                                ImageView imageView7 = (ImageView) rootView.findViewById(R.id.illegalOccupationArrow);
                                                if (imageView7 != null) {
                                                    i = R.id.illegalOccupationIcon;
                                                    ImageView imageView8 = (ImageView) rootView.findViewById(R.id.illegalOccupationIcon);
                                                    if (imageView8 != null) {
                                                        return new FragBackupnresetBinding((LinearLayout) rootView, imageView, imageView2, cardView, cardView2, cardView3, cardView4, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
