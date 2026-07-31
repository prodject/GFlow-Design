package com.prodject.gflow.ui.hubs;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.prodject.gflow.ui.theme.GColors;
import com.prodject.gflow.ui.theme.GDimens;
import com.prodject.gflow.ui.theme.GTypography;
import com.prodject.gflow.ui.views.GCardView;
import com.prodject.gflow.ui.views.GCityrayCarView;
import com.prodject.gflow.ui.views.GToggleTileView;

/**
 * HUB 1: Home & Launcher Workspace.
 * Top 50%: Interactive Geely Cityray Canvas.
 * Bottom 50%: Modular Quick Cards Grid (Climate Presets, Media, ADAS, Car Scenes).
 */
public class HomeHubView extends ScrollView {

    private final GCityrayCarView cityrayCarView;

    public HomeHubView(Context context) {
        super(context);
        setFillViewport(true);

        LinearLayout mainLayout = new LinearLayout(context);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        int padding = GDimens.dpToPx(context, GDimens.PADDING_MEDIUM_DP);
        mainLayout.setPadding(padding, padding, padding, padding);

        // 1. TOP HALF: Geely Cityray Visualizer Canvas (Height: 380dp)
        cityrayCarView = new GCityrayCarView(context);
        LinearLayout.LayoutParams carParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, GDimens.dpToPx(context, 380));
        carParams.bottomMargin = padding;
        cityrayCarView.setLayoutParams(carParams);

        mainLayout.addView(cityrayCarView);

        // 2. BOTTOM HALF: Modular Cards Grid (Tesla Card Style)
        LinearLayout cardsGrid = new LinearLayout(context);
        cardsGrid.setOrientation(LinearLayout.VERTICAL);

        // Row 1: Climate Presets & Media Card
        LinearLayout row1 = new LinearLayout(context);
        row1.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams rowParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        rowParams.bottomMargin = padding;

        GCardView climateCard = createClimateCard(context);
        GCardView mediaCard = createMediaCard(context);

        row1.addView(climateCard);
        row1.addView(mediaCard);

        // Row 2: Car Scenes & ADAS Status
        LinearLayout row2 = new LinearLayout(context);
        row2.setOrientation(LinearLayout.HORIZONTAL);

        GCardView scenesCard = createScenesCard(context);
        GCardView adasCard = createAdasCard(context);

        row2.addView(scenesCard);
        row2.addView(adasCard);

        cardsGrid.addView(row1, rowParams);
        cardsGrid.addView(row2, rowParams);

        mainLayout.addView(cardsGrid);
        addView(mainLayout);
    }

    private GCardView createClimateCard(Context context) {
        GCardView card = new GCardView(context);
        card.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));

        TextView title = new TextView(context);
        title.setText("🌡️ Смарт-Климат");
        GTypography.applyHeaderTitle(title);

        TextView sub = new TextView(context);
        sub.setText("Режим: Комфорт (Auto)");
        GTypography.applySubTitle(sub);

        card.addView(title);
        card.addView(sub);
        return card;
    }

    private GCardView createMediaCard(Context context) {
        GCardView card = new GCardView(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
        params.marginStart = GDimens.dpToPx(context, GDimens.PADDING_MEDIUM_DP);
        card.setLayoutParams(params);

        TextView title = new TextView(context);
        title.setText("🎵 Мультимедиа");
        GTypography.applyHeaderTitle(title);

        TextView sub = new TextView(context);
        sub.setText("Bluetooth / Radio Monji");
        GTypography.applySubTitle(sub);

        card.addView(title);
        card.addView(sub);
        return card;
    }

    private GCardView createScenesCard(Context context) {
        GCardView card = new GCardView(context);
        card.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));

        TextView title = new TextView(context);
        title.setText("🚘 Сцены Авто");
        GTypography.applyHeaderTitle(title);

        TextView sub = new TextView(context);
        sub.setText("Быстрые режимы (Wash, Camp, Pet)");
        GTypography.applySubTitle(sub);

        card.addView(title);
        card.addView(sub);
        return card;
    }

    private GCardView createAdasCard(Context context) {
        GCardView card = new GCardView(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
        params.setMarginStart(GDimens.dpToPx(context, GDimens.PADDING_MEDIUM_DP));
        card.setLayoutParams(params);

        TextView title = new TextView(context);
        title.setText("🛡️ Безопасность ADAS");
        GTypography.applyHeaderTitle(title);

        TextView sub = new TextView(context);
        sub.setText("AEB, LKA, BSD Активны");
        GTypography.applySubTitle(sub);

        card.addView(title);
        card.addView(sub);
        return card;
    }

    public GCityrayCarView getCityrayCarView() {
        return cityrayCarView;
    }
}
