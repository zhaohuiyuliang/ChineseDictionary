package com.zi.dian.ui;

import android.view.View;
import android.widget.TextView;

import com.zi.dian.unitl.PreferenceUtil;

import java.util.List;

import dian.zi.com.zidian.R;

/**
 * Created by wangliang on 6/24/16.
 */
public class FragmentSettings extends FragmentBase implements View.OnClickListener {
    private TextView tv_baidu_hanyu;
    private TextView tv_xinhua_cidian;

    @Override
    public int getResLayout() {
        return R.layout.fragment_settings;
    }

    @Override
    protected void initView() {
        tv_baidu_hanyu = (TextView) view.findViewById(R.id.tv_baidu_hanyu);
        tv_xinhua_cidian = (TextView) view.findViewById(R.id.tv_xinhua_cidian);
        tv_baidu_hanyu.setOnClickListener(this);
        tv_xinhua_cidian.setOnClickListener(this);
        loadData();
    }

    private void loadData() {
        String pronunciation = PreferenceUtil.getValue("pronunciation", "百度汉语", getActivity());
        if ("百度汉语".compareTo(pronunciation) == 0) {
            tv_baidu_hanyu.setTextColor(getResources().getColor(R.color.colorRed));
            tv_xinhua_cidian.setTextColor(getResources().getColor(R.color.colorBlack));
        } else if ("新华词典".compareTo(pronunciation) == 0) {
            tv_baidu_hanyu.setTextColor(getResources().getColor(R.color.colorBlack));

            tv_xinhua_cidian.setTextColor(getResources().getColor(R.color.colorRed));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_baidu_hanyu:
                PreferenceUtil.setKey("pronunciation", "百度汉语", getActivity());
                break;
            case R.id.tv_xinhua_cidian:
                PreferenceUtil.setKey("pronunciation", "新华词典", getActivity());
                break;
            default:
                break;
        }
        loadData();

    }

    @Override
    public void refreshView(List<?> str) {

    }
}
