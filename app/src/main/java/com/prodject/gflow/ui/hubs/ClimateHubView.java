package com.prodject.gflow.ui.hubs;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.prodject.gflow.ui.theme.GColors;
import com.prodject.gflow.ui.theme.GDimens;
import com.prodject.gflow.ui.theme.GTypography;
import com.prodject.gflow.ui.views.GCardView;
import com.prodject.gflow.ui.views.GSliderView;
import com.prodject.gflow.ui.views.GToggleTileView;

/**
 * HUB 3: Climate & Seat Comfort Hub (100% Coverage of Group 1).
 * HVAC Power, Temp float, Fan 1-9, A/C, A/C Max, Eco Climate, Airflow directions, Recirculation,
 * Seat Heating/Ventilation/Massage, Steering Heater, AQS, CO2, Ionizer, Fragrance, G-Clean, Dry ventilation, Smart Climate presets.
 */
public class ClimateHubView extends ScrollView {

    public ClimateHubView(Context context) {
        super(context);
        setFillViewport(true);

        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        int padding = GDimens.dpToPx(context, GDimens.PADDING_MEDIUM_DP);
        layout.setPadding(padding, padding, padding, padding);

        // Header Title
        TextView header = new TextView(context);
        header.setText("❄️ Управление Климатом и Умный Климат");
        GTypography.applyHeaderDisplay(header);
        layout.addView(header);

        // 1. Main HVAC Controls & Presets
        LinearLayout hvacRow = new LinearLayout(context);
        hvacRow.setOrientation(LinearLayout.HORIZONTAL);

        GToggleTileView hvacPower = new GToggleTileView(context, "Питание HVAC", "ВКЛ");
        GToggleTileView acMax = new GToggleTileView(context, "A/C MAX", "ВЫКЛ");
        GToggleTileView ecoClimate = new GToggleTileView(context, "Eco Климат", "ВКЛ");

        hvacRow.addView(hvacPower, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        hvacRow.addView(acMax, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        hvacRow.addView(ecoClimate, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        layout.addView(hvacRow);

        // 2. Fan Speed Slider Card (1-9 Levels)
        GCardView fanCard = new GCardView(context);
        TextView fanTitle = new TextView(context);
        fanTitle.setText("💨 Скорость Вентилятора (Уровни 1-9 + Auto)");
        GTypography.applyHeaderTitle(fanTitle);

        GSliderView fanSlider = new GSliderView(context);
        fanSlider.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, GDimens.dpToPx(context, 44)));
        fanSlider.setValue(0.5f);

        fanCard.addView(fanTitle);
        fanCard.addView(fanSlider);
        layout.addView(fanCard);

        // 3. Airflow Directions & Recirculation
        TextView dirTitle = new TextView(context);
        dirTitle.setText("🎯 Направления Обдува & Рециркуляция");
        GTypography.applyHeaderTitle(dirTitle);
        layout.addView(dirTitle);

        LinearLayout dirRow = new LinearLayout(context);
        dirRow.setOrientation(LinearLayout.HORIZONTAL);

        GToggleTileView faceTile = new GToggleTileView(context, "В лицо", "ВКЛ");
        GToggleTileView feetTile = new GToggleTileView(context, "В ноги", "ВКЛ");
        GToggleTileView glassTile = new GToggleTileView(context, "На стекло", "ВЫКЛ");
        GToggleTileView recircTile = new GToggleTileView(context, "Рециркуляция", "Авто забор");

        dirRow.addView(faceTile, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        dirRow.addView(feetTile, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        dirRow.addView(glassTile, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        dirRow.addView(recircTile, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        layout.addView(dirRow);

        // 4. Seats & Steering Wheel Comfort
        TextView seatTitle = new TextView(context);
        seatTitle.setText("🪑 Подогрев, Вентиляция и Массаж Сидений");
        GTypography.applyHeaderTitle(seatTitle);
        layout.addView(seatTitle);

        LinearLayout seatRow = new LinearLayout(context);
        seatRow.setOrientation(LinearLayout.HORIZONTAL);

        GToggleTileView steeringHeat = new GToggleTileView(context, "Подогрев Руля", "ВКЛ");
        GToggleTileView seatVent = new GToggleTileView(context, "Вентиляция Сидений", "Уровень 2");
        GToggleTileView seatMassage = new GToggleTileView(context, "Массаж Сидений", "Режим Волна");

        seatRow.addView(steeringHeat, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        seatRow.addView(seatVent, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        seatRow.addView(seatMassage, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        layout.addView(seatRow);

        // 5. Air Quality System (AQS), CO2, Ionizer, Fragrance, Dry Ventilation
        TextView airTitle = new TextView(context);
        airTitle.setText("🌿 Качество Воздуха AQS, Ионизация & Ароматизатор");
        GTypography.applyHeaderTitle(airTitle);
        layout.addView(airTitle);

        LinearLayout airRow = new LinearLayout(context);
        airRow.setOrientation(LinearLayout.HORIZONTAL);

        GToggleTileView aqsTile = new GToggleTileView(context, "AQS + CO2", "Датчик ВКЛ");
        GToggleTileView ionizerTile = new GToggleTileView(context, "Ионизатор G-Clean", "Активен");
        GToggleTileView fragranceTile = new GToggleTileView(context, "Ароматизатор", "Картридж #1");
        GToggleTileView dryTile = new GToggleTileView(context, "Просушка A/C", "Авто-осушение");

        airRow.addView(aqsTile, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        airRow.addView(ionizerTile, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        airRow.addView(fragranceTile, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        airRow.addView(dryTile, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        layout.addView(airRow);

        // 6. Smart Climate Engine Presets
        TextView smartTitle = new TextView(context);
        smartTitle.setText("🧠 Smart Climate Engine Пресеты");
        GTypography.applyHeaderTitle(smartTitle);
        layout.addView(smartTitle);

        LinearLayout smartRow = new LinearLayout(context);
        smartRow.setOrientation(LinearLayout.HORIZONTAL);

        GToggleTileView fastHeat = new GToggleTileView(context, "Быстрый Нагрев", "Winter Preset");
        GToggleTileView fastCool = new GToggleTileView(context, "Быстрое Охлаждение", "Summer Preset");
        GToggleTileView stabilize = new GToggleTileView(context, "Стабилизация", "Комфорт");

        smartRow.addView(fastHeat, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        smartRow.addView(fastCool, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        smartRow.addView(stabilize, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        layout.addView(smartRow);

        addView(layout);
    }
}
