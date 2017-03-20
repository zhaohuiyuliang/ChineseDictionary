package com.zi.dian.ui;

import android.content.Intent;
import android.view.View;

import com.zi.dian.adapter.AdapterHanZi;
import com.zi.dian.dao.model.ChineseCharacter;

import java.util.List;

/**
 * Created by wangliang on 2017/3/20.
 */

public class ActivityChineseCharacterByStroke extends ActivityChineseCharacterBase {
    @Override
    protected void initLoadData() {
        Intent intent = getIntent();
        int stroke = intent.getIntExtra("stroke", -1);
        List<ChineseCharacter> chineseCharacterList = getCtrApplication().getDaoManager().getTableZi().queryDataByStrokeAndRadicals("", stroke);
        setTitleContent("笔画数 " + stroke + " 的字");

        grid_view_zi.setVisibility(View.VISIBLE);
        list_view_chinese_character_and_stroke.setVisibility(View.GONE);
        adapterHanZi = new AdapterHanZi(this, this, null);
        grid_view_zi.setAdapter(adapterHanZi);
        adapterHanZi.setData(chineseCharacterList);
    }
}
