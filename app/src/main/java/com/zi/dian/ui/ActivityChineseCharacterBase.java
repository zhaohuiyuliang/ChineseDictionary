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
import com.zi.dian.dao.model.ChineseCharacter;
import com.zi.dian.dao.model.ChineseCharacterParaphrase;

import dian.zi.com.zidian.R;

import static com.zi.dian.adapter.AdapterHanZi.IModelHanZi;

/**
 * Created by wangliang on 6/22/16.
 */
public abstract class ActivityChineseCharacterBase extends ActivityBase implements IModelHanZi, View.OnClickListener {
    protected GridView grid_view_zi;
    protected ListView list_view_chinese_character_and_stroke;
    protected AdapterHanZi adapterHanZi;
    protected AdapterHanZiAndStroke adapterHanZiAndStroke;
    protected ImageView img_phone_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinese_charater);
        initView();
        initLoadData();
    }

    protected void initView() {
        super.initView();
        grid_view_zi = (GridView) findViewById(R.id.grid_view_chinese_character);
        list_view_chinese_character_and_stroke = (ListView) findViewById(R.id.list_view_chinese_character_and_stroke);
        img_phone_search = (ImageView) findViewById(R.id.img_phone_search);
        img_phone_search.setOnClickListener(this);
    }

    protected abstract void initLoadData();


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_phone_search: {
                break;
            }
            default: {
                break;
            }
        }
    }

    @Override
    public void setOnclickListener(ChineseCharacter chineseCharacter) {
        Intent intent = new Intent(this, ActivityChineseCharacterDetail.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ChineseCharacterParaphrase hanZiParaphrase = ControlApplication.getApplication().getDaoManager().getTableZiParaphrase().queryDataByZi(chineseCharacter.zi);
        intent.putExtra("ChineseCharacterParaphrase", hanZiParaphrase);
        getApplication().startActivity(intent);

    }
}
