package com.example.penny.accessibilityyysp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.penny.accessibilityyysp.service.SimulateClickService;
import com.example.penny.accessibilityyysp.util.PackageConts;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mGet;
    private Button mJump_elm;
    private boolean accessibilityManagerEnabled;
    public static final String TAG = "MainActivity";
    private AccessibilityManager mAccessibilityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_main);
        findView();
    }

    private void findView() {
        mAccessibilityManager = (AccessibilityManager) getSystemService(ACCESSIBILITY_SERVICE);
        mGet = (Button) findViewById(R.id.get);
        mJump_elm = (Button) findViewById(R.id.elm);
        mGet.setOnClickListener(this);
        mJump_elm.setOnClickListener(this);
//        checkStealFeature1();
    }


    @Override
    public void onClick(View pView) {
        switch (pView.getId()) {
            case R.id.get:
                openPermission();
                break;
            case R.id.elm:
                openApp();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void openApp() {
        Log.d(TAG, "accessibilityManagerEnabled:" + accessibilityManagerEnabled);
        if (mAccessibilityManager.isEnabled()) {
            PackageManager packageManager = this.getPackageManager();
            Intent it = packageManager.getLaunchIntentForPackage(PackageConts.ELM);
            startActivity(it);
        } else {
            Toast.makeText(this, "辅助权限未打开", Toast.LENGTH_LONG).show();
        }
    }

    private void openPermission() {
        accessibilityManagerEnabled = mAccessibilityManager.isEnabled();
        Log.d(TAG, "Enabled:" + accessibilityManagerEnabled);
        if (!accessibilityManagerEnabled) {
            Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
            startActivity(intent);
        }
    }

    public void permission(View view) {
        if (ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this,"成功",Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE}, 0);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this,"成功",Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this,"失败",Toast.LENGTH_LONG).show();
            }
        }

    }
}
