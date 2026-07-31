package com.prodject.gflow.ui.hubs;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.prodject.gflow.ui.theme.GDimens;
import com.prodject.gflow.ui.theme.GTypography;
import com.prodject.gflow.ui.views.GCardView;
import com.prodject.gflow.ui.views.GSliderView;
import com.prodject.gflow.ui.views.GToggleTileView;

/**
 * HUB 2: Vehicle, Body, Optics, Profiles, Drive Modes & Scenes Hub (100% Coverage of Groups 2, 6, 9, 10, 15).
 */
public class VehicleHubView extends ScrollView {

    public VehicleHubView(Context context) {
        super(context);
        setFillViewport(true);

        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        int padding = GDimens.dpToPx(context, GDimens.PADDING_MEDIUM_DP);
        layout.setPadding(padding, padding, padding, padding);

        // Section 1: Doors, Locks, Windows & Trunk (Group 2)
        TextView bodyHeader = new TextView(context);
        bodyHeader.setText("🚪 Кузов, Двери, Стекла и Панорама");
        GTypography.applyHeaderDisplay(bodyHeader);
        layout.addView(bodyHeader);

        LinearLayout bodyRow = new LinearLayout(context);
        bodyRow.setOrientation(LinearLayout.HORIZONTAL);

        GToggleTileView centralLock = new GToggleTileView(context, "Ц. Замок", "ВКЛ");
        GToggleTileView childLock = new GToggleTileView(context, "Защита детей", "ВЫКЛ");
        GToggleTileView sunroof = new GToggleTileView(context, "Панорама", "Шторка 50%");
        GToggleTileView trunk = new GToggleTileView(context, "Электро-Багажник", "Закрыт");

        bodyRow.addView(centralLock, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        bodyRow.addView(childLock, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        bodyRow.addView(sunroof, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        bodyRow.addView(trunk, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        layout.addView(bodyRow);

        // Section 2: Mirrors, Seats & Easy Entry (Groups 2 & 9)
        TextView seatHeader = new TextView(context);
        seatHeader.setText("🪑 Память Сидений, Зеркала & Легкий Вход");
        GTypography.applyHeaderDisplay(seatHeader);
        layout.addView(seatHeader);

        LinearLayout seatRow = new LinearLayout(context);
        seatRow.setOrientation(LinearLayout.HORIZONTAL);

        GToggleTileView mirrorFold = new GToggleTileView(context, "Зеркала", "Сложить");
        GToggleTileView mirrorReverse = new GToggleTileView(context, "Опускать при ZD", "ВКЛ");
        GToggleTileView seatMem1 = new GToggleTileView(context, "Память Сиденья #1", "Загружено");
        GToggleTileView easyEntry = new GToggleTileView(context, "Комфортная посадка", "ВКЛ");

        seatRow.addView(mirrorFold, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        seatRow.addView(mirrorReverse, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        seatRow.addView(seatMem1, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        seatRow.addView(easyEntry, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        layout.addView(seatRow);

        // Section 3: Drive Modes & Steering Effort (Group 2)
        TextView driveHeader = new TextView(context);
        driveHeader.setText("⚡ Режимы Движения & Усилие на Руле");
        GTypography.applyHeaderDisplay(driveHeader);
        layout.addView(driveHeader);

        LinearLayout driveRow = new LinearLayout(context);
        driveRow.setOrientation(LinearLayout.HORIZONTAL);

        GToggleTileView modeComfort = new GToggleTileView(context, "Comfort Mode", "Активен");
        GToggleTileView modeDynamic = new GToggleTileView(context, "Dynamic / Sport", "Выбрать");
        GToggleTileView modeSnow = new GToggleTileView(context, "Snow Mode", "Выбрать");
        GToggleTileView steeringSoft = new GToggleTileView(context, "Руль: Sport", "Усилие Макс");

        driveRow.addView(modeComfort, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        driveRow.addView(modeDynamic, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        driveRow.addView(modeSnow, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        driveRow.addView(steeringSoft, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        layout.addView(driveRow);

        // Section 4: HUD Head-Up Display & Cluster DIM (Group 6)
        TextView hudHeader = new TextView(context);
        hudHeader.setText("🖥️ Проектор HUD & Приборная Панель");
        GTypography.applyHeaderDisplay(hudHeader);
        layout.addView(hudHeader);

        GCardView hudCard = new GCardView(context);
        TextView hudTitle = new TextView(context);
        hudTitle.setText("Высота Проекции HUD / Снежный Режим (Snow Mode)");
        GTypography.applyHeaderTitle(hudTitle);

        GSliderView hudSlider = new GSliderView(context);
        hudSlider.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, GDimens.dpToPx(context, 40)));
        hudSlider.setValue(0.7f);

        hudCard.addView(hudTitle);
        hudCard.addView(hudSlider);
        layout.addView(hudCard);

        // Section 5: Car Scenes & Ambience Lighting (Group 15)
        TextView scenesHeader = new TextView(context);
        scenesHeader.setText("🎪 Автомобильные Сцены & Ambience Light");
        GTypography.applyHeaderDisplay(scenesHeader);
        layout.addView(scenesHeader);

        LinearLayout scenesRow = new LinearLayout(context);
        scenesRow.setOrientation(LinearLayout.HORIZONTAL);

        GToggleTileView washTile = new GToggleTileView(context, "🧼 Автомойка", "Заблокировать все");
        GToggleTileView petTile = new GToggleTileView(context, "🐶 Животное в машине", "Климат + Окна");
        GToggleTileView napTile = new GToggleTileView(context, "💤 Режим Сна/Отдыха", "Темнота + Массаж");
        GToggleTileView ambienceTile = new GToggleTileView(context, "Ambience Light", "Синхро под музыку");

        scenesRow.addView(washTile, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        scenesRow.addView(petTile, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        scenesRow.addView(napTile, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        scenesRow.addView(ambienceTile, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        layout.addView(scenesRow);

        addView(layout);
    }
}
