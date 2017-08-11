package com.example.penny.accessibilityyysp.service;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import com.example.penny.accessibilityyysp.app.ELMCate;
import com.example.penny.accessibilityyysp.model.ELMModel;
import com.example.penny.accessibilityyysp.util.FileUtil;
import com.example.penny.accessibilityyysp.util.PackageConts;

import java.util.List;

/**
 * Created on 2017/8/5 0005.
 * by penny
 */

public class SimulateClickService extends AccessibilityService {
    public static final String TAG = "SimulateClickService";
    public static boolean isELM = false;
    private ELMCate mELM;
    private boolean completed = false;

    public static boolean isELM() {
        return isELM;
    }

    public static void setELM(boolean pELM) {
        isELM = pELM;
    }

    @Override
    protected void onServiceConnected() {
        Log.d(TAG, "onServiceConnected   start");
        AccessibilityServiceInfo accessibilityServiceInfo = new AccessibilityServiceInfo();
        accessibilityServiceInfo.eventTypes = AccessibilityEvent.TYPES_ALL_MASK;
        accessibilityServiceInfo.feedbackType = AccessibilityServiceInfo.FEEDBACK_SPOKEN;
        accessibilityServiceInfo.notificationTimeout = 1000;
        setServiceInfo(accessibilityServiceInfo);

    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent pAccessibilityEvent) {
        int eventType = pAccessibilityEvent.getEventType();
        String eventText = "";
        switch (eventType) {
            case AccessibilityEvent.TYPE_VIEW_CLICKED:
                eventText = "TYPE_VIEW_CLICKED";
                break;
            case AccessibilityEvent.TYPE_VIEW_FOCUSED:
                eventText = "TYPE_VIEW_FOCUSED";
                break;
            case AccessibilityEvent.TYPE_VIEW_LONG_CLICKED:
                eventText = "TYPE_VIEW_LONG_CLICKED";
                break;
            case AccessibilityEvent.TYPE_VIEW_SELECTED:
                eventText = "TYPE_VIEW_SELECTED";
                break;
            case AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED:
                eventText = "TYPE_VIEW_TEXT_CHANGED";
                break;
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
                eventText = "TYPE_WINDOW_STATE_CHANGED";
                break;
            case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:
                eventText = "TYPE_NOTIFICATION_STATE_CHANGED";
                break;
            case AccessibilityEvent.TYPE_TOUCH_EXPLORATION_GESTURE_END:
                eventText = "TYPE_TOUCH_EXPLORATION_GESTURE_END";
                break;
            case AccessibilityEvent.TYPE_ANNOUNCEMENT:
                eventText = "TYPE_ANNOUNCEMENT";
                break;
            case AccessibilityEvent.TYPE_TOUCH_EXPLORATION_GESTURE_START:
                eventText = "TYPE_TOUCH_EXPLORATION_GESTURE_START";
                break;
            case AccessibilityEvent.TYPE_VIEW_HOVER_ENTER:
                eventText = "TYPE_VIEW_HOVER_ENTER";
                break;
            case AccessibilityEvent.TYPE_VIEW_HOVER_EXIT:
                eventText = "TYPE_VIEW_HOVER_EXIT";
                break;
            case AccessibilityEvent.TYPE_VIEW_SCROLLED:
                eventText = "TYPE_VIEW_SCROLLED";
                break;
            case AccessibilityEvent.TYPE_VIEW_TEXT_SELECTION_CHANGED:
                eventText = "TYPE_VIEW_TEXT_SELECTION_CHANGED";
                break;
            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED:
                eventText = "TYPE_WINDOW_CONTENT_CHANGED";
                CharSequence lPackageName = pAccessibilityEvent.getPackageName();
                if (lPackageName.equals(PackageConts.ELM)) {
                    if (!isELM) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                sleep();
                                findAddress();

                            }
                        }).start();

                    }

                }
                break;
        }
        eventText = eventText + ":" + eventType;
        Log.i(TAG, eventText);
    }

    private void findAddress() {
        AccessibilityNodeInfo lInWindow = getRootInWindow();
        if (lInWindow != null) {
            List<AccessibilityNodeInfo> lViewById = findViewById(lInWindow, "me.ele:id/ajm");
            for (AccessibilityNodeInfo o :
                    lViewById) {
                performClick(o);
                setELM(true);
                sleep();
                chooseAddress();
            }
        } else {
            Log.d(TAG, "未找到======================");
            findAddress();
        }
//        if (lNodeInfo != null) {
//            performClick(lNodeInfo);
//            setELM(true);
//            sleep();
//            chooseAddress();
//        }
    }

    private void chooseAddress() {
        AccessibilityNodeInfo lInWindow = getRootInWindow();
        if (lInWindow == null) return;
        List<AccessibilityNodeInfo> lNodeInfos = findViewById(lInWindow, "me.ele:id/xk");
        for (AccessibilityNodeInfo i :
                lNodeInfos) {
            List<AccessibilityNodeInfo> lViewById = findViewById(i, "me.ele:id/nh");
            for (AccessibilityNodeInfo j :
                    lViewById) {
                Log.d(TAG, "地址:" + j.getText());
                if (j.getText().toString().contains("武昌区大东门")) {
                    performClick(i);
                    sleep();
                    findCate();
                }
            }
        }
    }

    private void findList() {
        Log.d(TAG, "===============findList");
        AccessibilityNodeInfo lNodeInfo = getRootInWindow();
        if (lNodeInfo == null) return;
        Log.d(TAG, "child" + lNodeInfo.getChildCount() + "-- className:" + lNodeInfo.getClassName());
        List<AccessibilityNodeInfo> list = findViewById(lNodeInfo, "me.ele:id/ej");
//        Log.d(TAG, "list" + lNodeInfo.getChildCount());
        for (AccessibilityNodeInfo l : list) {
            List<AccessibilityNodeInfo> itemInfo = findViewById(lNodeInfo, "me.ele:id/a77");
            Log.d(TAG, "遍历：" + itemInfo.size());
            for (AccessibilityNodeInfo i : itemInfo) {
                Log.d(TAG, "===============:");
                i.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                sleep();
                findAvatar();
                if (completed) {
                    back();
                    Log.d(TAG, "===================写文件成功");
//                    sleep();
//                    back();
//                    back();
//
                } else {
                    Log.d(TAG, "===================写文件失败");
                    findAvatar();
                }

            }
            if (itemInfo.size() > 0) {
                sleep();
                Log.d(TAG, "开始滚动=====================");
                scroll(l);
                Log.d(TAG, "开始递归=====================");
                findList();
            }
        }
    }

    private void findAvatar() {
        AccessibilityNodeInfo lNodeInfo = getRootInActiveWindow();
        if (lNodeInfo != null) {
            if (lNodeInfo.getChildCount() != 0) {
                List<AccessibilityNodeInfo> list = lNodeInfo.findAccessibilityNodeInfosByViewId("me.ele:id/ahn");
                for (AccessibilityNodeInfo l2 : list) {
                    if (l2.isClickable()) {
                        performClick(l2);
                        getDetails();
                    } else {
                        performClick(l2);
                        getDetails();
                    }
                }
            }
        } else {
            findAvatar();
        }

    }

    private void getDetails() {
        sleep();
        final ELMModel lModel = new ELMModel();
        AccessibilityNodeInfo lNodeInfo = getRootInActiveWindow();
        if (lNodeInfo != null) {
            List<AccessibilityNodeInfo> list = lNodeInfo.findAccessibilityNodeInfosByViewId("me.ele:id/a_9");
            for (AccessibilityNodeInfo i : list) {
                String storeName = i.getText().toString();
                Log.d(TAG, "=================" + storeName);
                lModel.setStoreName(storeName);
            }
            List<AccessibilityNodeInfo> list2 = lNodeInfo.findAccessibilityNodeInfosByViewId("me.ele:id/nz");
            for (AccessibilityNodeInfo i : list) {
                scroll(i);
            }
            List<AccessibilityNodeInfo> shopInfo = findViewById(lNodeInfo, "me.ele:id/anj");
            List<AccessibilityNodeInfo> brand = findViewById(lNodeInfo, "me.ele:id/agy");
            List<AccessibilityNodeInfo> phone = findViewById(lNodeInfo, "me.ele:id/anm");
            List<AccessibilityNodeInfo> address = findViewById(lNodeInfo, "me.ele:id/ajm");
            String lInfo = serchText(shopInfo);
            String lBrand = serchText(brand);
            String lPhone = serchText(phone);
            String lAddress = serchText(address);

            lModel.setStoreInfomation(lInfo);
            lModel.setPhone(lPhone);
            lModel.setAddress(lAddress);
            lModel.setBrand(lBrand);
            completed = FileUtil.writeFile(lModel.toString());
            back();
        } else {
            getDetails();
        }

    }

    private String serchText(List<AccessibilityNodeInfo> pNodeInfos) {
        if (pNodeInfos == null) return null;
        String text = null;
        for (AccessibilityNodeInfo i : pNodeInfos) {
            text = i.getText().toString();
        }
        return text;
    }

    public AccessibilityNodeInfo getRootInWindow() {
        AccessibilityNodeInfo lNodeInfo = getRootInActiveWindow();
        if (lNodeInfo == null) return null;
        if (lNodeInfo.getChildCount() != 0) {
            return lNodeInfo;
        } else {
            return null;
        }
    }

    public List<AccessibilityNodeInfo> findViewById(AccessibilityNodeInfo pNodeInfo, String pId) {
        List<AccessibilityNodeInfo> lNodeInfosByViewId = pNodeInfo.findAccessibilityNodeInfosByViewId(pId);
        return lNodeInfosByViewId;
    }

    public List<AccessibilityNodeInfo> findRootViewById(String pId) {
        AccessibilityNodeInfo lRootInWindow = getRootInWindow();
        if (lRootInWindow == null) return null;
        List<AccessibilityNodeInfo> lNodeInfosByViewId = lRootInWindow.findAccessibilityNodeInfosByViewId(pId);
        return lNodeInfosByViewId;
    }

    public AccessibilityNodeInfo findViewByText(AccessibilityNodeInfo pNodeInfo, String text) {
        AccessibilityNodeInfo nodeInfo = null;
        List<AccessibilityNodeInfo> lNodeInfosByViewId = pNodeInfo.findAccessibilityNodeInfosByText(text);
        for (AccessibilityNodeInfo info : lNodeInfosByViewId) {
            if (info.getText().toString().equals(text)) {
                nodeInfo = info;
            } else {
                nodeInfo = info;
            }
        }
        return nodeInfo;
    }

    private void scroll(AccessibilityNodeInfo i) {
        if (i.isScrollable()) {
            i.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
        } else {
            if (i.getParent().isScrollable()) {
                i.getParent().performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
            } else {
                scroll(i.getParent().getParent());
            }
        }
        sleep();
    }


    private void performClick(AccessibilityNodeInfo l2) {
        if (l2.isClickable()) {
            l2.performAction(AccessibilityNodeInfo.ACTION_CLICK);
        } else {
            if (l2.getParent().isClickable()) {
                l2.getParent().performAction(AccessibilityNodeInfo.ACTION_CLICK);
            } else {
                performClick(l2.getParent());
            }
        }
    }

    private void findHot() {
        sleep();
        AccessibilityNodeInfo lRootInWindow = getRootInWindow();
        if (lRootInWindow == null) return;
        AccessibilityNodeInfo lNodeInfo = findViewByText(lRootInWindow, "销量最高");
        if (lNodeInfo == null) return;
        lNodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
        sleep();
        findList();
//        for (AccessibilityNodeInfo info : lText) {
//            Log.d(TAG, info.getText().toString());
//            if (info.getText().toString().equals("销量最高")) {
//                info.performAction(AccessibilityNodeInfo.ACTION_CLICK);
//                sleep();
//                findList(false);
//            }
//        }
    }

    private void findSynthesize() {
        sleep();
        AccessibilityNodeInfo lRootInWindow = getRootInWindow();
        if (lRootInWindow == null) return;
        AccessibilityNodeInfo lText = findViewByText(lRootInWindow, "综合排序");
        if (lText == null) return;
        performClick(lText);
//        sleep();
        findHot();
//        for (AccessibilityNodeInfo info : lText) {
//            Log.d(TAG, info.getText().toString());
//            if (info.getText().toString().equals("综合排序")) {
//                info.getParent().performAction(AccessibilityNodeInfo.ACTION_CLICK);
//                sleep();
//            }
//        }
    }

    public void back() {
        performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK);
        sleep();
    }

    private void findCate() {
        AccessibilityNodeInfo lRootInWindow = getRootInWindow();
        if (lRootInWindow == null) return;
//        AccessibilityNodeInfo lText = findViewByText(lRootInWindow, "美食");
//        if (lText == null ) return;
        List<AccessibilityNodeInfo> lViewById = findViewById(lRootInWindow, "me.ele:id/akh");
        if (lViewById != null && lViewById.size() != 0) {
            AccessibilityNodeInfo lNodeInfo = lViewById.get(0);
            performClick(lNodeInfo);
            findSynthesize();
        } else {
            findCate();
        }
//        for (AccessibilityNodeInfo info : lText) {
//            Log.d(TAG, info.getText().toString());
//            if (info.getText().toString().equals("美食")) {
//                info.getParent().performAction(AccessibilityNodeInfo.ACTION_CLICK);
//            }
//        }
    }

    @Override
    public void onInterrupt() {

    }

    public void sleep() {
        try {
            Thread.sleep(2500);
        } catch (InterruptedException pE) {
            pE.printStackTrace();
        }
    }
}
