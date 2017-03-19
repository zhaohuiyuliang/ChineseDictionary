package com.zi.dian.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import com.zi.dian.ControlApplication;
import com.zi.dian.adapter.AdapterHanZi;
import com.zi.dian.adapter.AdapterHanZiAndStroke;
import com.zi.dian.dao.model.HanZi;
import com.zi.dian.dao.model.HanZiParaphrase;
import com.zi.dian.dao.model.Radicals;

import java.util.List;
import java.util.Map;

import dian.zi.com.zidian.R;

import static com.zi.dian.adapter.AdapterHanZi.IModelHanZi;

/**
 * Created by wangliang on 6/22/16.
 */
public class ActivityHanZi extends ActivityBase implements IModelHanZi, View.OnClickListener {
    private GridView grid_view_zi;
    private ListView list_view_hanzi_and_stroke;
    private AdapterHanZi adapterHanZi;
    private AdapterHanZiAndStroke adapterHanZiAndStroke;
    private ImageView img_phone_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hanzi);
        initView();
        initLoadData();
    }

    protected void initView() {
        super.initView();
//        findViewById(R.id.img_phone_search).setVisibility(View.VISIBLE);
        grid_view_zi = (GridView) findViewById(R.id.grid_view_zi);
        list_view_hanzi_and_stroke = (ListView) findViewById(R.id.list_view_hanzi_and_stroke);
        img_phone_search = (ImageView) findViewById(R.id.img_phone_search);
        img_phone_search.setOnClickListener(this);
    }

    private void initLoadData() {
        Intent intent = getIntent();
        int stroke = intent.getIntExtra("stroke", -1);
        if (stroke != -1) {
            List<HanZi> hanZiList = getCtrApplication().getDaoManager().getTableZi().queryDataByStrokeAndRadicals("", stroke);
            setTitleContent("笔画数 " + stroke + " 的字");

            grid_view_zi.setVisibility(View.VISIBLE);
            list_view_hanzi_and_stroke.setVisibility(View.GONE);
            adapterHanZi = new AdapterHanZi(this, this, null);
            grid_view_zi.setAdapter(adapterHanZi);
            adapterHanZi.setData(hanZiList);
        } else {
            Radicals radicals = (Radicals) intent.getSerializableExtra("radicals");
            setTitleContent("部首为 " + radicals.radical + " 的字");
            List<HanZi> hanZiList = getCtrApplication().getDaoManager().getTableZi().queryDataByStrokeAndRadicals(radicals.radical, -1);
            if (hanZiList != null && hanZiList.size() > 36) {
                grid_view_zi.setVisibility(View.GONE);
                list_view_hanzi_and_stroke.setVisibility(View.VISIBLE);

                Map<Integer, List<HanZi>> listMap = getCtrApplication().getDaoManager().getTableZi().queryAllData(radicals.radical);
                adapterHanZiAndStroke = new AdapterHanZiAndStroke(this, listMap);
                list_view_hanzi_and_stroke.setAdapter(adapterHanZiAndStroke);
//            CommonUntil.setListViewHeightBasedOnChildren(list_view_hanzi_and_stroke);
//            adapterHanZi = new AdapterHanZi(this, this, null);
//            grid_view_zi.setAdapter(adapterHanZi);
//            adapterHanZi.setData(hanZiList);

            } else {
                grid_view_zi.setVisibility(View.VISIBLE);
                list_view_hanzi_and_stroke.setVisibility(View.GONE);
                adapterHanZi = new AdapterHanZi(this, this, null);
                grid_view_zi.setAdapter(adapterHanZi);
                adapterHanZi.setData(hanZiList);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_phone_search:
                break;
            default:
                break;
        }
    }

    @Override
    public void setOnclickListener(HanZi hanZi) {
        Intent intent = new Intent(this, ActivityHanZiDetail.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        HanZiParaphrase hanZiParaphrase = ControlApplication.getApplication().getDaoManager().getTableZiParaphrase().queryDataByZi(hanZi.zi);
        intent.putExtra("HanZiParaphrase", hanZiParaphrase);
        getApplication().startActivity(intent);

    }
}
