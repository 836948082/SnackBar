package com.runtai.snackbar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;

import com.androidadvance.topsnackbar.TSnackbar;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    CoordinatorLayout container;
    Snackbar snackbar;
    private String msg;
    View view;
    AppCompatButton acbtn1, acbtn2, acbtn3, acbtn4;
    private TSnackbar topbar;
    private long exitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container = (CoordinatorLayout) findViewById(R.id.container);
        view = findViewById(android.R.id.content);
        acbtn1 = (AppCompatButton) findViewById(R.id.btn1);
        acbtn2 = (AppCompatButton) findViewById(R.id.btn2);
        acbtn3 = (AppCompatButton) findViewById(R.id.btn3);
        acbtn4 = (AppCompatButton) findViewById(R.id.btn4);
        acbtn1.setOnClickListener(this);
        acbtn2.setOnClickListener(this);
        acbtn3.setOnClickListener(this);
        acbtn4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                msg = "底部不带按钮";
                snackbar();
                break;
            case R.id.btn2:
                msg = "底部带按钮";
                snackbarc();
                break;
            case R.id.btn3:
                msg = "顶部不带按钮";
                topsnackbar();
                break;
            case R.id.btn4:
                msg = "顶部带按钮";
                topsnackbarc();
                break;
        }
    }

    //SnackBar通过改变下面的颜色来改变背景颜色
    //setActionTextColor来改变按钮颜色
    private void topsnackbar() {
        topbar = TSnackbar.make(view, msg, TSnackbar.LENGTH_SHORT);
        View snackbarView = topbar.getView();
        snackbarView.setBackgroundColor(Color.parseColor("#FF4111"));
        topbar.show();
    }

    private void snackbar() {
        snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    private void topsnackbarc() {
        topbar = TSnackbar.make(view, msg, TSnackbar.LENGTH_SHORT);
        topbar.setAction("确定", new OnClickListener() {
            @Override
            public void onClick(View p1) {
                snackbar.dismiss();
            }
        });
        View snackbarView = topbar.getView();
        snackbarView.setBackgroundColor(Color.parseColor("#FF4111"));
        topbar.setActionTextColor(Color.parseColor("#FFFFFF"));
        topbar.show();
    }

    private void snackbarc() {
        snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_SHORT);
        snackbar.setAction("确定", new OnClickListener() {
            @Override
            public void onClick(View p1) {
                snackbar.dismiss();
            }
        });
        snackbar.show();
    }

    //再按一次退出
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 1500) {
                msg = "再按一次退出！";
                topsnackbar();
                exitTime = System.currentTimeMillis();
            } else {
                // System.exit(0);
                // android.os.Process.killProcess(android.os.Process.myPid());
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
