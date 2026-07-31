package com.prodject.gflow.ui.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

import com.prodject.gflow.ui.theme.GColors;
import com.prodject.gflow.ui.theme.GDimens;

/**
 * High-Ergonomic Touch Slider built for driving without looking (Thick Bar with Touch Feedback).
 */
public class GSliderView extends View {

    private final Paint bgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint fillPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final RectF rect = new RectF();

    private float value = 0.5f; // 0.0 to 1.0
    private OnSliderChangeListener listener;

    public interface OnSliderChangeListener {
        void onValueChanged(float value);
    }

    public GSliderView(Context context) {
        super(context);
        init();
    }

    private void init() {
        bgPaint.setColor(GColors.CARD_BACKGROUND);
        bgPaint.setStyle(Paint.Style.FILL);

        fillPaint.setColor(GColors.ACCENT_BLUE);
        fillPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float width = getWidth();
        float height = getHeight();
        float radius = height / 2.0f;

        rect.set(0, 0, width, height);
        canvas.drawRoundRect(rect, radius, radius, bgPaint);

        float fillWidth = width * value;
        if (fillWidth > 0) {
            rect.set(0, 0, fillWidth, height);
            canvas.drawRoundRect(rect, radius, radius, fillPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                float x = event.getX();
                value = Math.max(0.0f, Math.min(1.0f, x / getWidth()));
                invalidate();
                if (listener != null) {
                    listener.onValueChanged(value);
                }
                return true;
        }
        return super.onTouchEvent(event);
    }

    public void setValue(float value) {
        this.value = Math.max(0.0f, Math.min(1.0f, value));
        invalidate();
    }

    public float getValue() {
        return value;
    }

    public void setOnSliderChangeListener(OnSliderChangeListener listener) {
        this.listener = listener;
    }

    public void setAccentColor(int color) {
        fillPaint.setColor(color);
        invalidate();
    }
}
