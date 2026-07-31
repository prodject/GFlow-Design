package com.prodject.gflow;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.prodject.gflow.ui.hubs.ClimateHubView;
import com.prodject.gflow.ui.hubs.HomeHubView;
import com.prodject.gflow.ui.hubs.SafetyHubView;
import com.prodject.gflow.ui.hubs.VehicleHubView;
import com.prodject.gflow.ui.overlays.AutomationOverlayView;
import com.prodject.gflow.ui.overlays.SystemDrawerOverlay;
import com.prodject.gflow.ui.overlays.VoiceOverlayView;
import com.prodject.gflow.ui.theme.GColors;
import com.prodject.gflow.ui.theme.GDimens;
import com.prodject.gflow.ui.views.GBottomDockView;
import com.prodject.gflow.ui.views.GTopStatusBarView;

/**
 * GFlowD Main Host Activity for 13.2" Vertical Android Automotive Head Unit.
 * Consolidates all 26 screens into a 3-Zone Tesla-Style Ergonomic Interface.
 */
public class GFlowMainActivity extends AppCompatActivity {

    private GTopStatusBarView topStatusBar;
    private GBottomDockView bottomDockView;
    private FrameLayout workspaceContainer;

    // Core 4 Hub Views
    private HomeHubView homeHubView;
    private VehicleHubView vehicleHubView;
    private ClimateHubView climateHubView;
    private SafetyHubView safetyHubView;

    // Overlay Views
    private VoiceOverlayView voiceOverlayView;
    private SystemDrawerOverlay systemDrawerOverlay;
    private AutomationOverlayView automationOverlayView;

    private int activeHubIndex = 0; // 0: Home, 1: Vehicle, 2: Climate, 3: Safety

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Root vertical layout for 13.2" Screen
        LinearLayout rootLayout = new LinearLayout(this);
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        rootLayout.setBackgroundColor(GColors.SURFACE_DARK);

        // 1. ZONE A: Top Status Bar (64dp)
        topStatusBar = new GTopStatusBarView(this);
        LinearLayout.LayoutParams topParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, GDimens.dpToPx(this, GDimens.TOP_BAR_HEIGHT_DP));
        rootLayout.addView(topStatusBar, topParams);

        // 2. ZONE B: Main Dynamic Workspace Container
        workspaceContainer = new FrameLayout(this);
        LinearLayout.LayoutParams workspaceParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 1.0f);
        rootLayout.addView(workspaceContainer, workspaceParams);

        // Initialize Hubs
        homeHubView = new HomeHubView(this);
        vehicleHubView = new VehicleHubView(this);
        climateHubView = new ClimateHubView(this);
        safetyHubView = new SafetyHubView(this);

        workspaceContainer.addView(homeHubView);
        workspaceContainer.addView(vehicleHubView);
        workspaceContainer.addView(climateHubView);
        workspaceContainer.addView(safetyHubView);

        // Initialize Overlays
        voiceOverlayView = new VoiceOverlayView(this);
        systemDrawerOverlay = new SystemDrawerOverlay(this);
        automationOverlayView = new AutomationOverlayView(this);

        voiceOverlayView.setVisibility(View.GONE);
        systemDrawerOverlay.setVisibility(View.GONE);
        automationOverlayView.setVisibility(View.GONE);

        workspaceContainer.addView(voiceOverlayView);
        workspaceContainer.addView(systemDrawerOverlay);
        workspaceContainer.addView(automationOverlayView);

        // 3. ZONE C: Persistent Bottom Dock (96dp) - Integrated on top of OneOS Dock
        bottomDockView = new GBottomDockView(this);
        LinearLayout.LayoutParams bottomParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, GDimens.dpToPx(this, GDimens.BOTTOM_DOCK_HEIGHT_DP));
        rootLayout.addView(bottomDockView, bottomParams);

        setContentView(rootLayout);

        // Setup Listeners & Navigation Routing
        setupActionListeners();
        switchHub(0); // Start on Home Hub
    }

    private void switchHub(int hubIndex) {
        this.activeHubIndex = hubIndex;
        homeHubView.setVisibility(hubIndex == 0 ? View.VISIBLE : View.GONE);
        vehicleHubView.setVisibility(hubIndex == 1 ? View.VISIBLE : View.GONE);
        climateHubView.setVisibility(hubIndex == 2 ? View.VISIBLE : View.GONE);
        safetyHubView.setVisibility(hubIndex == 3 ? View.VISIBLE : View.GONE);
    }

    private void setupActionListeners() {
        topStatusBar.setActionListener(new GTopStatusBarView.OnTopBarActionListener() {
            @Override
            public void onProfileClick() {
                Toast.makeText(GFlowMainActivity.this, "Смена Профиля Водителя", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVoiceClick() {
                toggleOverlay(voiceOverlayView);
            }

            @Override
            public void onSettingsClick() {
                toggleOverlay(systemDrawerOverlay);
            }
        });

        bottomDockView.setDockActionListener(new GBottomDockView.OnDockActionListener() {
            @Override
            public void onDriverTempChange(float temp) {
                Toast.makeText(GFlowMainActivity.this, "Климат Водитель: " + temp + "°C", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPassTempChange(float temp) {
                Toast.makeText(GFlowMainActivity.this, "Климат Пассажир: " + temp + "°C", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDriverSeatToggle(int level) {
                Toast.makeText(GFlowMainActivity.this, "Подогрев сиденья водителя: " + level, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPassSeatToggle(int level) {
                Toast.makeText(GFlowMainActivity.this, "Подогрев сиденья пассажира: " + level, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFrontDefrostToggle(boolean active) {
                Toast.makeText(GFlowMainActivity.this, "Макс. обдув лобового: " + (active ? "ВКЛ" : "ВЫКЛ"), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRearDefrostToggle(boolean active) {
                Toast.makeText(GFlowMainActivity.this, "Обогрев заднего стекла: " + (active ? "ВКЛ" : "ВЫКЛ"), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onHomeClick() {
                // Cycle through 4 Hubs or Return to Home
                int nextHub = (activeHubIndex + 1) % 4;
                switchHub(nextHub);
            }

            @Override
            public void onHomeLongClick() {
                toggleOverlay(automationOverlayView);
            }
        });
    }

    private void toggleOverlay(View overlayView) {
        boolean isVisible = overlayView.getVisibility() == View.VISIBLE;
        voiceOverlayView.setVisibility(View.GONE);
        systemDrawerOverlay.setVisibility(View.GONE);
        automationOverlayView.setVisibility(View.GONE);

        if (!isVisible) {
            overlayView.setVisibility(View.VISIBLE);
        }
    }
}
