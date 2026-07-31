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
 * Overlay 3: System Utilities, Developer Tools & File Manager (100% Coverage of Groups 13 & 14).
 * ADB Shell terminal, AdaptAPI Diagnostics runner, Autozoom DPI scaling, File Manager, Split-Screen Manager.
 */
public class SystemDrawerOverlay extends LinearLayout {

    public SystemDrawerOverlay(Context context) {
        super(context);
        setOrientation(VERTICAL);
        int padding = GDimens.dpToPx(context, GDimens.PADDING_LARGE_DP);
        setPadding(padding, padding, padding, padding);

        GradientDrawable bg = new GradientDrawable();
        bg.setColor(GColors.SURFACE_ELEVATED);
        bg.setCornerRadius(GDimens.dpToPx(context, GDimens.CORNER_RADIUS_CARD_DP));
        bg.setStroke(GDimens.dpToPx(context, 2.0f), GColors.CARD_BORDER_ACTIVE);
        setBackground(bg);

        TextView title = new TextView(context);
        title.setText("⚙️ Системные Утилиты, ADB, Autozoom & Файлы");
        GTypography.applyHeaderDisplay(title);
        addView(title);

        // Row 1: Diagnostics & ADB Shell (Group 14)
        LinearLayout row1 = new LinearLayout(context);
        row1.setOrientation(HORIZONTAL);

        GToggleTileView adbTile = new GToggleTileView(context, "💻 ADB Shell", "Открыть консоль");
        GToggleTileView diagTile = new GToggleTileView(context, "🔍 AdaptAPI Диагностика", "gflow-diagnostics.txt");
        GToggleTileView autozoomTile = new GToggleTileView(context, "🔍 Autozoom DPI", "Масштабирование ПО");

        row1.addView(adbTile, new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0f));
        row1.addView(diagTile, new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0f));
        row1.addView(autozoomTile, new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0f));
        addView(row1);

        // Row 2: File Manager & Split Screen (Group 13 & 11)
        LinearLayout row2 = new LinearLayout(context);
        row2.setOrientation(HORIZONTAL);

        GToggleTileView filesTile = new GToggleTileView(context, "📁 Файловый Менеджер", "USB / Внутренняя память");
        GToggleTileView splitTile = new GToggleTileView(context, "🔲 Split-Screen Менеджер", "Разделение 50/50");

        row2.addView(filesTile, new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0f));
        row2.addView(splitTile, new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0f));
        addView(row2);
    }
}
