package com.prodject.gflow.ui.views;

import android.content.Context;
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
 * Automotive Interactive Toggle Tile for car features (Doors, Lights, ADAS, Modes).
 */
public class GToggleTileView extends LinearLayout {

    private final ImageView iconView;
    private final TextView titleView;
    private final TextView statusView;
    private final GradientDrawable backgroundDrawable = new GradientDrawable();

    private boolean isChecked = false;
    private OnToggleListener listener;

    public interface OnToggleListener {
        void onToggleChanged(boolean isChecked);
    }

    public GToggleTileView(Context context, String title, String initialStatus) {
        super(context);
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        
        int padding = GDimens.dpToPx(context, GDimens.PADDING_MEDIUM_DP);
        setPadding(padding, padding, padding, padding);

        backgroundDrawable.setCornerRadius(GDimens.dpToPx(context, GDimens.CORNER_RADIUS_BUTTON_DP));
        backgroundDrawable.setColor(GColors.CARD_BACKGROUND);
        backgroundDrawable.setStroke(GDimens.dpToPx(context, 1.5f), GColors.CARD_BORDER);
        setBackground(backgroundDrawable);

        // Icon Container
        iconView = new ImageView(context);
        int iconSize = GDimens.dpToPx(context, 32);
        LayoutParams iconParams = new LayoutParams(iconSize, iconSize);
        iconParams.marginEnd = GDimens.dpToPx(context, GDimens.PADDING_MEDIUM_DP);
        iconView.setLayoutParams(iconParams);
        iconView.setColorFilter(GColors.ICON_INACTIVE);

        // Text Layout (Title & Status)
        LinearLayout textLayout = new LinearLayout(context);
        textLayout.setOrientation(VERTICAL);
        textLayout.setLayoutParams(new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0f));

        titleView = new TextView(context);
        titleView.setText(title);
        GTypography.applyButtonLabel(titleView);

        statusView = new TextView(context);
        statusView.setText(initialStatus);
        GTypography.applyCaption(statusView);

        textLayout.addView(titleView);
        textLayout.addView(statusView);

        addView(iconView);
        addView(textLayout);

        setOnClickListener(v -> {
            performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
            setChecked(!isChecked);
            if (listener != null) {
                listener.onToggleChanged(isChecked);
            }
        });
    }

    public void setChecked(boolean checked) {
        this.isChecked = checked;
        if (checked) {
            backgroundDrawable.setColor(GColors.CARD_BACKGROUND_ACTIVE);
            backgroundDrawable.setStroke(GDimens.dpToPx(getContext(), 2.0f), GColors.ACCENT_BLUE);
            iconView.setColorFilter(GColors.ACCENT_BLUE);
            titleView.setTextColor(GColors.TEXT_PRIMARY);
            statusView.setTextColor(GColors.ACCENT_BLUE);
            statusView.setText("ВКЛ");
        } else {
            backgroundDrawable.setColor(GColors.CARD_BACKGROUND);
            backgroundDrawable.setStroke(GDimens.dpToPx(getContext(), 1.5f), GColors.CARD_BORDER);
            iconView.setColorFilter(GColors.ICON_INACTIVE);
            titleView.setTextColor(GColors.TEXT_PRIMARY);
            statusView.setTextColor(GColors.TEXT_MUTED);
            statusView.setText("ВЫКЛ");
        }
        invalidate();
    }

    public void setStatusText(String text) {
        statusView.setText(text);
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setOnToggleListener(OnToggleListener listener) {
        this.listener = listener;
    }

    public ImageView getIconView() {
        return iconView;
    }
}
