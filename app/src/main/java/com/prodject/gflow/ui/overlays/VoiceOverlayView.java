package com.prodject.gflow.ui.overlays;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prodject.gflow.ui.theme.GColors;
import com.prodject.gflow.ui.theme.GDimens;
import com.prodject.gflow.ui.theme.GTypography;

/**
 * Overlay 2: Vosk Offline Voice Assistant Listener.
 * Appears when triggered from steering wheel button or top status bar icon.
 */
public class VoiceOverlayView extends LinearLayout {

    private final TextView commandText;

    public VoiceOverlayView(Context context) {
        super(context);
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);
        int padding = GDimens.dpToPx(context, GDimens.PADDING_LARGE_DP);
        setPadding(padding, padding, padding, padding);

        GradientDrawable bg = new GradientDrawable();
        bg.setColor(Color.parseColor("#0F172A"));
        bg.setCornerRadius(GDimens.dpToPx(context, GDimens.CORNER_RADIUS_CARD_DP));
        bg.setStroke(GDimens.dpToPx(context, 2.0f), GColors.ACCENT_GREEN);
        setBackground(bg);

        TextView title = new TextView(context);
        title.setText("🎙️ Слушаю команды Vosk...");
        GTypography.applyHeaderTitle(title);
        title.setTextColor(GColors.ACCENT_GREEN);

        commandText = new TextView(context);
        commandText.setText("«Включи подогрев руля» / «Открыть окна»");
        GTypography.applySubTitle(commandText);

        addView(title);
        addView(commandText);
    }

    public void setRecognizedText(String text) {
        commandText.setText("«" + text + "»");
    }
}
