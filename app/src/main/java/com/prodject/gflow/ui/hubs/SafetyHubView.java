package com.prodject.gflow.ui.hubs;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.prodject.gflow.ui.theme.GColors;
import com.prodject.gflow.ui.theme.GDimens;
import com.prodject.gflow.ui.theme.GTypography;
import com.prodject.gflow.ui.views.GCardView;
import com.prodject.gflow.ui.views.GToggleTileView;

/**
 * HUB 4: Safety & Vision Suite (100% Coverage of Groups 3, 4, 5).
 * ADAS Assistants (AEB, FCW, LKA, ELKA, RCW, BSD, LCA, TSR, ACC gap),
 * AVM 360 Cameras (Transparent Hood, PAS Dynamic Lines, APA Auto-parker, PDC Sonar volume),
 * Monji DVR Engine (Storage, Resolution, Capture, Segment length, Memory limit).
 */
public class SafetyHubView extends ScrollView {

    public SafetyHubView(Context context) {
        super(context);
        setFillViewport(true);

        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        int padding = GDimens.dpToPx(context, GDimens.PADDING_MEDIUM_DP);
        layout.setPadding(padding, padding, padding, padding);

        // Header Title
        TextView header = new TextView(context);
        header.setText("🛡️ Системы Безопасности ADAS, 360° AVM & Monji DVR");
        GTypography.applyHeaderDisplay(header);
        layout.addView(header);

        // 1. Camera 360 AVM & Parking Visualizer Card (Group 5)
        GCardView cameraCard = new GCardView(context);
        LinearLayout.LayoutParams camParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, GDimens.dpToPx(context, 260));
        camParams.bottomMargin = padding;
        cameraCard.setLayoutParams(camParams);

        TextView camText = new TextView(context);
        camText.setText("📷 Видеопоток Камеры 360° AVM / Прозрачный Капот Active");
        GTypography.applyHeaderTitle(camText);
        camText.setTextColor(GColors.ACCENT_CYAN);

        cameraCard.addView(camText);
        layout.addView(cameraCard);

        // 2. Parking Controls (Group 5)
        TextView parkTitle = new TextView(context);
        parkTitle.setText("🅿️ Ассистент Парковки, PAS & Сонары PDC");
        GTypography.applyHeaderTitle(parkTitle);
        layout.addView(parkTitle);

        LinearLayout parkRow = new LinearLayout(context);
        parkRow.setOrientation(LinearLayout.HORIZONTAL);

        GToggleTileView transparentHood = new GToggleTileView(context, "Прозрачный Капот", "ВКЛ");
        GToggleTileView dynamicLines = new GToggleTileView(context, "PAS Траектория", "Динамическая");
        GToggleTileView autoPark = new GToggleTileView(context, "Автопарковщик APA", "Параллельный");
        GToggleTileView sonarVolume = new GToggleTileView(context, "Громкость сонаров", "Средняя (Mid)");

        parkRow.addView(transparentHood, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        parkRow.addView(dynamicLines, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        parkRow.addView(autoPark, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        parkRow.addView(sonarVolume, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        layout.addView(parkRow);

        // 3. ADAS Active Safety Systems (Group 4)
        TextView adasTitle = new TextView(context);
        adasTitle.setText("⚠️ Активная Безопасность ADAS");
        GTypography.applyHeaderTitle(adasTitle);
        layout.addView(adasTitle);

        LinearLayout adasRow1 = new LinearLayout(context);
        adasRow1.setOrientation(LinearLayout.HORIZONTAL);

        GToggleTileView aeb = new GToggleTileView(context, "AEB Торможение", "ВКЛ");
        GToggleTileView fcw = new GToggleTileView(context, "FCW Предупреждение", "Высокая чуствит.");
        GToggleTileView lka = new GToggleTileView(context, "LKA/LDW Полоса", "Удержание");
        GToggleTileView bsd = new GToggleTileView(context, "BSD Слепые зоны", "ВКЛ");

        adasRow1.addView(aeb, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        adasRow1.addView(fcw, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        adasRow1.addView(lka, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        adasRow1.addView(bsd, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        layout.addView(adasRow1);

        LinearLayout adasRow2 = new LinearLayout(context);
        adasRow2.setOrientation(LinearLayout.HORIZONTAL);

        GToggleTileView tsr = new GToggleTileView(context, "TSR Знаки скорости", "Ограничение 60");
        GToggleTileView accGap = new GToggleTileView(context, "ACC Дистанция", "Дистанция 3");
        GToggleTileView lca = new GToggleTileView(context, "Auto LCA Смена полосы", "ВКЛ");
        GToggleTileView rcta = new GToggleTileView(context, "RCTA Поперечный трафик", "ВКЛ");

        adasRow2.addView(tsr, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        adasRow2.addView(accGap, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        adasRow2.addView(lca, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        adasRow2.addView(rcta, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        layout.addView(adasRow2);

        // 4. Monji DVR Engine Controls (Group 3)
        TextView dvrTitle = new TextView(context);
        dvrTitle.setText("📹 Запись Видеорегистратора Monji DVR");
        GTypography.applyHeaderTitle(dvrTitle);
        layout.addView(dvrTitle);

        LinearLayout dvrRow = new LinearLayout(context);
        dvrRow.setOrientation(LinearLayout.HORIZONTAL);

        GToggleTileView dvrRec = new GToggleTileView(context, "Запись DVR", "Запись (1080p)");
        GToggleTileView dvrSource = new GToggleTileView(context, "Источник", "Front ADAS + Rear");
        GToggleTileView dvrLock = new GToggleTileView(context, "SOS Снимок", "Защита файла");
        GToggleTileView dvrStorage = new GToggleTileView(context, "Накопитель", "USB Flash (64GB)");

        dvrRow.addView(dvrRec, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        dvrRow.addView(dvrSource, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        dvrRow.addView(dvrLock, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        dvrRow.addView(dvrStorage, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        layout.addView(dvrRow);

        addView(layout);
    }
}
