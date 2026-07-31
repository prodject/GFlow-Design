package com.prodject.gflow.ui.views;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.widget.LinearLayout;

import com.prodject.gflow.ui.theme.GColors;
import com.prodject.gflow.ui.theme.GDimens;

/**
 * Modern Tesla-style Glassmorphic Card Container built programmatically.
 */
public class GCardView extends LinearLayout {

    private final GradientDrawable backgroundDrawable = new GradientDrawable();
    private boolean isInteractive = true;
    private boolean isActive = false;

    public GCardView(Context context) {
        super(context);
        init();
    }

    private void init() {
        setOrientation(VERTICAL);
        int padding = GDimens.dpToPx(getContext(), GDimens.PADDING_MEDIUM_DP);
        setPadding(padding, padding, padding, padding);

        backgroundDrawable.setCornerRadius(GDimens.dpToPx(getContext(), GDimens.CORNER_RADIUS_CARD_DP));
        backgroundDrawable.setColor(GColors.CARD_BACKGROUND);
        backgroundDrawable.setStroke(GDimens.dpToPx(getContext(), 1.5f), GColors.CARD_BORDER);

        setBackground(backgroundDrawable);
    }

    public void setActive(boolean active) {
        this.isActive = active;
        if (active) {
            backgroundDrawable.setColor(GColors.CARD_BACKGROUND_ACTIVE);
            backgroundDrawable.setStroke(GDimens.dpToPx(getContext(), 2.0f), GColors.CARD_BORDER_ACTIVE);
        } else {
            backgroundDrawable.setColor(GColors.CARD_BACKGROUND);
            backgroundDrawable.setStroke(GDimens.dpToPx(getContext(), 1.5f), GColors.CARD_BORDER);
        }
        invalidate();
    }

    public boolean isActive() {
        return isActive;
    }
}
