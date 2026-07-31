package com.prodject.gflow.ui.theme;

import android.content.Context;
import android.util.TypedValue;

/**
 * Automotive Dimension Tokens (Ergonomic Touch Targets for 13.2" Screen)
 */
public class GDimens {
    // Touch & Hit Targets (Driving Safety)
    public static final int TOUCH_TARGET_MIN_DP = 64;   // Minimum size for drive ergonomics
    public static final int TOUCH_TARGET_LARGE_DP = 80;  // Large HVAC / Defrost buttons
    
    // Layout Heights & Spacing
    public static final int TOP_BAR_HEIGHT_DP = 64;
    public static final int BOTTOM_DOCK_HEIGHT_DP = 96;
    public static final int PADDING_SMALL_DP = 8;
    public static final int PADDING_MEDIUM_DP = 16;
    public static final int PADDING_LARGE_DP = 24;
    
    // Corner Radii
    public static final int CORNER_RADIUS_CARD_DP = 24;
    public static final int CORNER_RADIUS_BUTTON_DP = 16;
    public static final int CORNER_RADIUS_PILL_DP = 999;
    
    // Helper Conversion Utilities
    public static int dpToPx(Context context, float dp) {
        return Math.round(TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics()));
    }

    public static int spToPx(Context context, float sp) {
        return Math.round(TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics()));
    }
}
