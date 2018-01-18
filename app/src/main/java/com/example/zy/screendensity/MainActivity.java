package com.example.zy.screendensity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "zhang";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getScreenDensity_ByWindowManager();

        final Button button = findViewById(R.id.btn);

        ViewTreeObserver vto2 = button.getViewTreeObserver();
        vto2.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                button.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                Log.d(TAG,"width = "+button.getWidth()+" height = "+button.getHeight());
            }
        });
    }

    //获得手机的宽度和高度像素单位为px
// 通过WindowManager获取
    public void getScreenDensity_ByWindowManager(){
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();//屏幕分辨率容器
        getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        int width = mDisplayMetrics.widthPixels;
        int height = mDisplayMetrics.heightPixels;
        float density = mDisplayMetrics.density;
        int densityDpi = mDisplayMetrics.densityDpi;
        Log.d(TAG,"Screen Ratio: ["+width+"x"+height+"],density="+density+",densityDpi="+densityDpi);
        Log.d(TAG,"Screen mDisplayMetrics: "+mDisplayMetrics);
    }
}
