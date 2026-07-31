package com.prodject.gflow.ui.overlays;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prodject.gflow.ui.theme.GColors;
import com.prodject.gflow.ui.theme.GDimens;
import com.prodject.gflow.ui.theme.GTypography;
import com.prodject.gflow.ui.views.GToggleTileView;

/**
 * Overlay 1: Automation Engine & Smart Presets (100% Coverage of Group 8).
 * Scenarios v2: Winter, Summer, Welcome, Parking Guard, Rain Scenario, Low-Speed Camera automation.
 */
public class AutomationOverlayView extends LinearLayout {

    public AutomationOverlayView(Context context) {
        super(context);
        setOrientation(VERTICAL);
        int padding = GDimens.dpToPx(context, GDimens.PADDING_LARGE_DP);
        setPadding(padding, padding, padding, padding);

        GradientDrawable bg = new GradientDrawable();
        bg.setColor(GColors.SURFACE_ELEVATED);
        bg.setCornerRadius(GDimens.dpToPx(context, GDimens.CORNER_RADIUS_CARD_DP));
        bg.setStroke(GDimens.dpToPx(context, 2.0f), GColors.ACCENT_PURPLE);
        setBackground(bg);

        TextView title = new TextView(context);
        title.setText("⚡ Движок Автоматизации & Смарт-Сценарии (v2)");
        GTypography.applyHeaderDisplay(title);
        addView(title);

        LinearLayout row1 = new LinearLayout(context);
        row1.setOrientation(HORIZONTAL);

        GToggleTileView winter = new GToggleTileView(context, "❄️ Зимний Сценарий", "Авто-подогревы");
        GToggleTileView summer = new GToggleTileView(context, "☀️ Летний Сценарий", "Макс. охлаждение");
        GToggleTileView welcome = new GToggleTileView(context, "👋 Welcome / Leave", "Приветственный свет");

        row1.addView(winter, new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0f));
        row1.addView(summer, new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0f));
        row1.addView(welcome, new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0f));
        addView(row1);

        LinearLayout row2 = new LinearLayout(context);
        row2.setOrientation(HORIZONTAL);

        GToggleTileView parkGuard = new GToggleTileView(context, "🛡️ Parking Guard", "Охрана на стоянке");
        GToggleTileView rain = new GToggleTileView(context, "🌧️ Сценарий Дождь", "Закрыть люк/окна");
        GToggleTileView lowSpeedCam = new GToggleTileView(context, "📷 Low-Speed Cam", "360° при <15 км/ч");

        row2.addView(parkGuard, new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0f));
        row2.addView(rain, new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0f));
        row2.addView(lowSpeedCam, new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0f));
        addView(row2);
    }
}
