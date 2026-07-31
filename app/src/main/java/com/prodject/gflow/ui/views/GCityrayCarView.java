package com.prodject.gflow.ui.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.HapticFeedbackConstants;
import android.view.MotionEvent;
import android.view.View;

import com.prodject.gflow.ui.theme.GColors;
import com.prodject.gflow.ui.theme.GDimens;

/**
 * 2D Interactive Visualizer of Geely Cityray (Monji) with interactive touch hotspots.
 * Handles door open/close, trunk, hood, sunroof, lights, and TPMS status display.
 */
public class GCityrayCarView extends View {

    private final Paint bodyPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint strokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint glassPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint activeZonePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    // Car Interactive States
    private boolean isDoorFrontLeftOpen = false;
    private boolean isDoorFrontRightOpen = false;
    private boolean isDoorRearLeftOpen = false;
    private boolean isDoorRearRightOpen = false;
    private boolean isTrunkOpen = false;
    private boolean isHoodOpen = false;
    private boolean isSunroofOpen = false;
    private boolean isHeadlightsOn = false;

    // Hotspot bounding boxes
    private final RectF carBounds = new RectF();
    private final RectF flDoorRect = new RectF();
    private final RectF frDoorRect = new RectF();
    private final RectF rlDoorRect = new RectF();
    private final RectF rrDoorRect = new RectF();
    private final RectF trunkRect = new RectF();
    private final RectF hoodRect = new RectF();
    private final RectF sunroofRect = new RectF();

    private OnCarPartClickListener listener;

    public interface OnCarPartClickListener {
        void onDoorClick(String doorName, boolean isOpen);
        void onTrunkClick(boolean isOpen);
        void onHoodClick(boolean isOpen);
        void onSunroofClick(boolean isOpen);
    }

    public GCityrayCarView(Context context) {
        super(context);
        init();
    }

    private void init() {
        bodyPaint.setColor(Color.parseColor("#1E2633"));
        bodyPaint.setStyle(Paint.Style.FILL);

        strokePaint.setColor(GColors.ACCENT_BLUE);
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setStrokeWidth(GDimens.dpToPx(getContext(), 2.5f));

        glassPaint.setColor(Color.parseColor("#38BDF8"));
        glassPaint.setAlpha(80);
        glassPaint.setStyle(Paint.Style.FILL);

        activeZonePaint.setColor(GColors.ACCENT_RED);
        activeZonePaint.setStyle(Paint.Style.FILL);
        activeZonePaint.setAlpha(120);

        textPaint.setColor(GColors.TEXT_PRIMARY);
        textPaint.setTextSize(GDimens.spToPx(getContext(), 14));
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setFakeBoldText(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float w = getWidth();
        float h = getHeight();
        if (w == 0 || h == 0) return;

        // Centered Geely Cityray Top-Down Silhouette Box
        float carWidth = w * 0.45f;
        float carHeight = h * 0.75f;
        float left = (w - carWidth) / 2.0f;
        float top = (h - carHeight) / 2.0f;

        carBounds.set(left, top, left + carWidth, top + carHeight);

        // Draw main body shape
        canvas.drawRoundRect(carBounds, GDimens.dpToPx(getContext(), 32), GDimens.dpToPx(getContext(), 32), bodyPaint);
        canvas.drawRoundRect(carBounds, GDimens.dpToPx(getContext(), 32), GDimens.dpToPx(getContext(), 32), strokePaint);

        // Glass & Roof Zone
        float glassMarginX = carWidth * 0.15f;
        float glassMarginY = carHeight * 0.22f;
        sunroofRect.set(left + glassMarginX, top + glassMarginY, left + carWidth - glassMarginX, top + carHeight - glassMarginY);
        canvas.drawRoundRect(sunroofRect, 16, 16, isSunroofOpen ? activeZonePaint : glassPaint);
        canvas.drawText("Geely Cityray", sunroofRect.centerX(), sunroofRect.centerY() + 5, textPaint);

        // Front Hood Hotspot
        hoodRect.set(left, top, left + carWidth, top + glassMarginY);
        if (isHoodOpen) {
            canvas.drawRoundRect(hoodRect, 20, 20, activeZonePaint);
        }

        // Trunk Hotspot
        trunkRect.set(left, top + carHeight - glassMarginY, left + carWidth, top + carHeight);
        if (isTrunkOpen) {
            canvas.drawRoundRect(trunkRect, 20, 20, activeZonePaint);
        }

        // Doors Hotspots
        float midY = top + carHeight * 0.5f;
        flDoorRect.set(left - 20, top + glassMarginY, left + 20, midY);
        frDoorRect.set(left + carWidth - 20, top + glassMarginY, left + carWidth + 20, midY);
        rlDoorRect.set(left - 20, midY, left + 20, top + carHeight - glassMarginY);
        rrDoorRect.set(left + carWidth - 20, midY, left + carWidth + 20, top + carHeight - glassMarginY);

        // Draw door open indicators
        if (isDoorFrontLeftOpen) canvas.drawRect(flDoorRect, activeZonePaint);
        if (isDoorFrontRightOpen) canvas.drawRect(frDoorRect, activeZonePaint);
        if (isDoorRearLeftOpen) canvas.drawRect(rlDoorRect, activeZonePaint);
        if (isDoorRearRightOpen) canvas.drawRect(rrDoorRect, activeZonePaint);

        // TPMS Tire Pressure Labels
        textPaint.setTextSize(GDimens.spToPx(getContext(), 12));
        textPaint.setColor(GColors.ACCENT_GREEN);
        canvas.drawText("2.4 bar", left - 40, top + 40, textPaint);
        canvas.drawText("2.4 bar", left + carWidth + 40, top + 40, textPaint);
        canvas.drawText("2.3 bar", left - 40, top + carHeight - 40, textPaint);
        canvas.drawText("2.3 bar", left + carWidth + 40, top + carHeight - 40, textPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float x = event.getX();
            float y = event.getY();

            performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

            if (sunroofRect.contains(x, y)) {
                isSunroofOpen = !isSunroofOpen;
                if (listener != null) listener.onSunroofClick(isSunroofOpen);
            } else if (hoodRect.contains(x, y)) {
                isHoodOpen = !isHoodOpen;
                if (listener != null) listener.onHoodClick(isHoodOpen);
            } else if (trunkRect.contains(x, y)) {
                isTrunkOpen = !isTrunkOpen;
                if (listener != null) listener.onTrunkClick(isTrunkOpen);
            } else if (flDoorRect.contains(x, y)) {
                isDoorFrontLeftOpen = !isDoorFrontLeftOpen;
                if (listener != null) listener.onDoorClick("FL", isDoorFrontLeftOpen);
            } else if (frDoorRect.contains(x, y)) {
                isDoorFrontRightOpen = !isDoorFrontRightOpen;
                if (listener != null) listener.onDoorClick("FR", isDoorFrontRightOpen);
            }

            invalidate();
            return true;
        }
        return super.onTouchEvent(event);
    }

    public void setOnCarPartClickListener(OnCarPartClickListener listener) {
        this.listener = listener;
    }
}
