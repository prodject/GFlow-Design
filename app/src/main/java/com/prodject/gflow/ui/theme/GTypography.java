package com.prodject.gflow.ui.theme;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

/**
 * High-legibility Automotive Typography for fast reading while driving.
 */
public class GTypography {
    
    public static void applyHeaderDisplay(TextView textView) {
        textView.setTextSize(32);
        textView.setTypeface(Typeface.create("sans-serif-medium", Typeface.BOLD));
        textView.setTextColor(GColors.TEXT_PRIMARY);
    }

    public static void applyHeaderTitle(TextView textView) {
        textView.setTextSize(22);
        textView.setTypeface(Typeface.create("sans-serif-medium", Typeface.BOLD));
        textView.setTextColor(GColors.TEXT_PRIMARY);
    }

    public static void applySubTitle(TextView textView) {
        textView.setTextSize(16);
        textView.setTypeface(Typeface.create("sans-serif", Typeface.NORMAL));
        textView.setTextColor(GColors.TEXT_SECONDARY);
    }

    public static void applyButtonLabel(TextView textView) {
        textView.setTextSize(16);
        textView.setTypeface(Typeface.create("sans-serif-medium", Typeface.BOLD));
        textView.setTextColor(GColors.TEXT_PRIMARY);
    }

    public static void applyValueDisplay(TextView textView) {
        textView.setTextSize(44);
        textView.setTypeface(Typeface.create("sans-serif-medium", Typeface.BOLD));
        textView.setTextColor(GColors.TEXT_PRIMARY);
    }

    public static void applyCaption(TextView textView) {
        textView.setTextSize(13);
        textView.setTypeface(Typeface.create("sans-serif", Typeface.NORMAL));
        textView.setTextColor(GColors.TEXT_MUTED);
    }
}
