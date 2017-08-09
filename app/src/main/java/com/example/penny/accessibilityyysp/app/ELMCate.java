package com.example.penny.accessibilityyysp.app;

import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;

import com.example.penny.accessibilityyysp.operate.Strategy;
import com.example.penny.accessibilityyysp.service.SimulateClickService;

import java.util.List;

/**
 * Created on 2017/8/7 0007.
 * by penny
 */

public class ELMCate implements Strategy {
    public static final String TAG = "ELMCate";

    private final SimulateClickService mSimulateClickService;
    private boolean isELM;

    public ELMCate(SimulateClickService pSimulateClickService) {
        mSimulateClickService = pSimulateClickService;
    }

    @Override
    public void enterDifferentPage() {
        if (isELM()) {
            Log.d(TAG, "enterDifferentPage");
            AccessibilityNodeInfo lWindow = mSimulateClickService.getRootInActiveWindow();
            List<AccessibilityNodeInfo> lText = lWindow.findAccessibilityNodeInfosByText("美食");
            for (AccessibilityNodeInfo i : lText) {
                Log.d(TAG,"enterDifferentPage:"+i.getText());
            }
            enterDetailPage();
        }
    }

    @Override
    public void enterDetailPage() {
        if (isELM()) {

        }
    }

    @Override
    public void collectionData() {
        if (isELM()) {

        }
    }

    @Override
    public void requestUploadData() {
        if (isELM()) {

        }
    }

    public boolean isELM() {
        return isELM;
    }

    public void setELM(boolean pELM) {
        isELM = pELM;
    }
}
