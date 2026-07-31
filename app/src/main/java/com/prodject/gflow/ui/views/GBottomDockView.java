package com.prodject.gflow.ui.views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.HapticFeedbackConstants;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prodject.gflow.ui.theme.GColors;
import com.prodject.gflow.ui.theme.GDimens;
import com.prodject.gflow.ui.theme.GTypography;

/**
 * Zone C: Persistent Bottom Control Dock (Tesla Style, 96dp height).
 * Placed on top of / integrated with OneOS DockBar.
 * Provides blind 1-touch actions for:
 * Driver Temp, Driver Seat Heater, Front Defrost, Central GFlowD Home button, Rear Defrost, Pass Seat Heater, Pass Temp.
 */
public class GBottomDockView extends LinearLayout {

    private static final class TextHolder {
        private TextView view;
    }

    private float driverTemp = 21.5f;
    private float passTemp = 22.0f;
    private int driverSeatWarmLevel = 0; // 0, 1, 2, 3
    private int passSeatWarmLevel = 0;

    private final TextView driverTempText;
    private final TextView passTempText;
    private final TextView driverSeatText;
    private final TextView passSeatText;
    private final GradientDrawable frontDefrostBg = new GradientDrawable();
    private final GradientDrawable rearDefrostBg = new GradientDrawable();

    private boolean isFrontDefrostActive = false;
    private boolean isRearDefrostActive = false;

    private OnDockActionListener listener;

    public interface OnDockActionListener {
        void onDriverTempChange(float temp);
        void onPassTempChange(float temp);
        void onDriverSeatToggle(int level);
        void onPassSeatToggle(int level);
        void onFrontDefrostToggle(boolean active);
        void onRearDefrostToggle(boolean active);
        void onHomeClick();
        void onHomeLongClick();
    }

    public GBottomDockView(Context context) {
        super(context);
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);

        int paddingH = GDimens.dpToPx(context, GDimens.PADDING_MEDIUM_DP);
        setPadding(paddingH, 0, paddingH, 0);

        GradientDrawable bg = new GradientDrawable();
        bg.setColor(GColors.BOTTOM_DOCK_BG);
        bg.setStroke(GDimens.dpToPx(context, 1.5f), GColors.CARD_BORDER);
        setBackground(bg);

        int buttonSize = GDimens.dpToPx(context, GDimens.TOUCH_TARGET_MIN_DP);

        // 1. DRIVER TEMP REGULATION (- / Temp / +)
        LinearLayout driverTempLayout = createTempWidget(context, true);

        // 2. DRIVER SEAT HEATING / VENTILATION TOGGLE
        TextHolder driverSeatHolder = new TextHolder();
        driverSeatText = createDockButton(context, "🔥 0", v -> {
            driverSeatWarmLevel = (driverSeatWarmLevel + 1) % 4;
            driverSeatHolder.view.setText("🔥 " + driverSeatWarmLevel);
            driverSeatHolder.view.setTextColor(driverSeatWarmLevel > 0 ? GColors.ACCENT_ORANGE : GColors.TEXT_SECONDARY);
            if (listener != null) listener.onDriverSeatToggle(driverSeatWarmLevel);
        });
        driverSeatHolder.view = driverSeatText;

        // 3. FRONT DEFROST QUICK TOGGLE
        TextHolder frontDefrostHolder = new TextHolder();
        TextView frontDefrostBtn = createDockButton(context, "🌬️ ЛОБ", v -> {
            isFrontDefrostActive = !isFrontDefrostActive;
            frontDefrostHolder.view.setTextColor(isFrontDefrostActive ? GColors.ACCENT_RED : GColors.TEXT_SECONDARY);
            if (listener != null) listener.onFrontDefrostToggle(isFrontDefrostActive);
        });
        frontDefrostHolder.view = frontDefrostBtn;

        // 4. CENTRAL HOME BUTTON (GFlowD Center)
        LinearLayout homeBtn = createCentralHomeButton(context);

        // 5. REAR DEFROST & MIRRORS
        TextHolder rearDefrostHolder = new TextHolder();
        TextView rearDefrostBtn = createDockButton(context, "♨️ З А Д", v -> {
            isRearDefrostActive = !isRearDefrostActive;
            rearDefrostHolder.view.setTextColor(isRearDefrostActive ? GColors.ACCENT_ORANGE : GColors.TEXT_SECONDARY);
            if (listener != null) listener.onRearDefrostToggle(isRearDefrostActive);
        });
        rearDefrostHolder.view = rearDefrostBtn;

        // 6. PASSENGER SEAT HEATING TOGGLE
        TextHolder passSeatHolder = new TextHolder();
        passSeatText = createDockButton(context, "🔥 0", v -> {
            passSeatWarmLevel = (passSeatWarmLevel + 1) % 4;
            passSeatHolder.view.setText("🔥 " + passSeatWarmLevel);
            passSeatHolder.view.setTextColor(passSeatWarmLevel > 0 ? GColors.ACCENT_ORANGE : GColors.TEXT_SECONDARY);
            if (listener != null) listener.onPassSeatToggle(passSeatWarmLevel);
        });
        passSeatHolder.view = passSeatText;

        // 7. PASSENGER TEMP REGULATION (- / Temp / +)
        LinearLayout passTempLayout = createTempWidget(context, false);

        // Assemble Dock Layout
        driverTempText = driverTempLayout.findViewById(101);
        passTempText = passTempLayout.findViewById(102);

        addView(driverTempLayout);
        addView(driverSeatText);
        addView(frontDefrostBtn);
        addView(homeBtn);
        addView(rearDefrostBtn);
        addView(passSeatText);
        addView(passTempLayout);
    }

    private LinearLayout createTempWidget(Context context, boolean isDriver) {
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(HORIZONTAL);
        layout.setGravity(Gravity.CENTER_VERTICAL);
        layout.setLayoutParams(new LayoutParams(0, LayoutParams.MATCH_PARENT, 1.2f));

        TextView minus = new TextView(context);
        minus.setText(" - ");
        GTypography.applyHeaderTitle(minus);
        minus.setPadding(16, 16, 16, 16);
        minus.setOnClickListener(v -> {
            performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
            if (isDriver) {
                driverTemp = Math.max(16.0f, driverTemp - 0.5f);
                driverTempText.setText(String.format(java.util.Locale.US, "%.1f°", driverTemp));
                if (listener != null) listener.onDriverTempChange(driverTemp);
            } else {
                passTemp = Math.max(16.0f, passTemp - 0.5f);
                passTempText.setText(String.format(java.util.Locale.US, "%.1f°", passTemp));
                if (listener != null) listener.onPassTempChange(passTemp);
            }
        });

        TextView val = new TextView(context);
        val.setId(isDriver ? 101 : 102);
        val.setText(isDriver ? "21.5°" : "22.0°");
        GTypography.applyButtonLabel(val);

        TextView plus = new TextView(context);
        plus.setText(" + ");
        GTypography.applyHeaderTitle(plus);
        plus.setPadding(16, 16, 16, 16);
        plus.setOnClickListener(v -> {
            performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
            if (isDriver) {
                driverTemp = Math.min(30.0f, driverTemp + 0.5f);
                driverTempText.setText(String.format(java.util.Locale.US, "%.1f°", driverTemp));
                if (listener != null) listener.onDriverTempChange(driverTemp);
            } else {
                passTemp = Math.min(30.0f, passTemp + 0.5f);
                passTempText.setText(String.format(java.util.Locale.US, "%.1f°", passTemp));
                if (listener != null) listener.onPassTempChange(passTemp);
            }
        });

        layout.addView(minus);
        layout.addView(val);
        layout.addView(plus);
        return layout;
    }

    private TextView createDockButton(Context context, String label, OnClickListener clickListener) {
        TextView btn = new TextView(context);
        btn.setText(label);
        GTypography.applyButtonLabel(btn);
        btn.setGravity(Gravity.CENTER);

        int size = GDimens.dpToPx(context, GDimens.TOUCH_TARGET_MIN_DP);
        LayoutParams params = new LayoutParams(size, size);
        params.setMargins(8, 0, 8, 0);
        btn.setLayoutParams(params);

        GradientDrawable bg = new GradientDrawable();
        bg.setColor(GColors.DOCK_BUTTON_BG);
        bg.setCornerRadius(GDimens.dpToPx(context, GDimens.CORNER_RADIUS_BUTTON_DP));
        btn.setBackground(bg);

        btn.setOnClickListener(v -> {
            performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
            clickListener.onClick(v);
        });
        return btn;
    }

    private LinearLayout createCentralHomeButton(Context context) {
        LinearLayout btn = new LinearLayout(context);
        btn.setOrientation(VERTICAL);
        btn.setGravity(Gravity.CENTER);

        int size = GDimens.dpToPx(context, GDimens.TOUCH_TARGET_LARGE_DP);
        LayoutParams params = new LayoutParams(size, size);
        params.setMargins(16, 0, 16, 0);
        btn.setLayoutParams(params);

        GradientDrawable bg = new GradientDrawable();
        bg.setColor(GColors.ACCENT_BLUE);
        bg.setCornerRadius(GDimens.dpToPx(context, GDimens.CORNER_RADIUS_PILL_DP));
        btn.setBackground(bg);

        TextView label = new TextView(context);
        label.setText("GFlowD");
        GTypography.applyButtonLabel(label);
        label.setTextColor(Color.WHITE);

        btn.addView(label);

        btn.setOnClickListener(v -> {
            performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
            if (listener != null) listener.onHomeClick();
        });

        btn.setOnLongClickListener(v -> {
            performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
            if (listener != null) listener.onHomeLongClick();
            return true;
        });

        return btn;
    }

    public void setDockActionListener(OnDockActionListener listener) {
        this.listener = listener;
    }
}
