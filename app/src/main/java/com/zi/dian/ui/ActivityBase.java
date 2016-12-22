package com.zi.dian.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zi.dian.ControlApplication;

import org.htmlparser.Text;

import dian.zi.com.zidian.R;

/**
 * Created by wangliang on 6/22/16.
 */
public class ActivityBase extends Activity {

    private RelativeLayout title_back_r;
    private TextView tv_top_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void initView() {
        title_back_r = (RelativeLayout) findViewById(R.id.title_back_r);
        tv_top_bar = (TextView) findViewById(R.id.tv_top_bar);
        title_back_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public ControlApplication getCtrApplication() {
        return ControlApplication.getApplication();
    }

    public void setTitleContent(String param) {
        tv_top_bar.setText(param);
    }

}
