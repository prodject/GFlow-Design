package com.prodject.gflow.ui.views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prodject.gflow.ui.theme.GColors;
import com.prodject.gflow.ui.theme.GDimens;
import com.prodject.gflow.ui.theme.GTypography;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Top Status Bar (Zone A - 64dp).
 * Contains Clock, Driver Profile Indicator, Vosk Offline Voice Status, Weather Info, and Quick Settings Trigger.
 */
public class GTopStatusBarView extends LinearLayout {

    private final TextView timeTextView;
    private final TextView profileTextView;
    private final TextView weatherTextView;
    private final ImageView voiceIconView;
    private final ImageView settingsIconView;

    private OnTopBarActionListener actionListener;

    public interface OnTopBarActionListener {
        void onProfileClick();
        void onVoiceClick();
        void onSettingsClick();
    }

    public GTopStatusBarView(Context context) {
        super(context);
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);

        int paddingH = GDimens.dpToPx(context, GDimens.PADDING_LARGE_DP);
        setPadding(paddingH, 0, paddingH, 0);

        GradientDrawable bg = new GradientDrawable();
        bg.setColor(GColors.TOP_BAR_BG);
        bg.setStroke(GDimens.dpToPx(context, 1.0f), GColors.CARD_BORDER);
        setBackground(bg);

        // 1. LEFT: Clock & Profile Selector
        LinearLayout leftSection = new LinearLayout(context);
        leftSection.setOrientation(HORIZONTAL);
        leftSection.setGravity(Gravity.CENTER_VERTICAL);
        leftSection.setLayoutParams(new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0f));

        timeTextView = new TextView(context);
        updateClock();
        GTypography.applyHeaderTitle(timeTextView);

        profileTextView = new TextView(context);
        profileTextView.setText(" 👤 Водитель 1");
        GTypography.applySubTitle(profileTextView);
        profileTextView.setOnClickListener(v -> {
            if (actionListener != null) actionListener.onProfileClick();
        });

        leftSection.addView(timeTextView);
        leftSection.addView(profileTextView);

        // 2. CENTER: Voice Indicator & Weather
        LinearLayout centerSection = new LinearLayout(context);
        centerSection.setOrientation(HORIZONTAL);
        centerSection.setGravity(Gravity.CENTER);
        centerSection.setLayoutParams(new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0f));

        voiceIconView = new ImageView(context);
        voiceIconView.setColorFilter(GColors.ACCENT_GREEN);
        int iconSize = GDimens.dpToPx(context, 28);
        LayoutParams voiceParams = new LayoutParams(iconSize, iconSize);
        voiceParams.marginEnd = GDimens.dpToPx(context, GDimens.PADDING_SMALL_DP);
        voiceIconView.setLayoutParams(voiceParams);
        voiceIconView.setOnClickListener(v -> {
            if (actionListener != null) actionListener.onVoiceClick();
        });

        weatherTextView = new TextView(context);
        weatherTextView.setText("☀️ +22°C Monji");
        GTypography.applySubTitle(weatherTextView);

        centerSection.addView(voiceIconView);
        centerSection.addView(weatherTextView);

        // 3. RIGHT: Quick System Settings Trigger
        LinearLayout rightSection = new LinearLayout(context);
        rightSection.setOrientation(HORIZONTAL);
        rightSection.setGravity(Gravity.END | Gravity.CENTER_VERTICAL);
        rightSection.setLayoutParams(new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0f));

        settingsIconView = new ImageView(context);
        settingsIconView.setColorFilter(GColors.ICON_INACTIVE);
        LayoutParams settingsParams = new LayoutParams(iconSize, iconSize);
        settingsIconView.setLayoutParams(settingsParams);
        settingsIconView.setOnClickListener(v -> {
            if (actionListener != null) actionListener.onSettingsClick();
        });

        rightSection.addView(settingsIconView);

        addView(leftSection);
        addView(centerSection);
        addView(rightSection);
    }

    public void updateClock() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
        timeTextView.setText(sdf.format(new Date()));
    }

    public void setActionListener(OnTopBarActionListener actionListener) {
        this.actionListener = actionListener;
    }
}
