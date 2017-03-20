package com.zi.dian.ui;

import android.content.Intent;
import android.view.View;

import com.zi.dian.adapter.AdapterHanZi;
import com.zi.dian.adapter.AdapterHanZiAndStroke;
import com.zi.dian.dao.model.ChineseCharacter;
import com.zi.dian.dao.model.Radicals;

import java.util.List;
import java.util.Map;

/**
 * Created by wangliang on 2017/3/20.
 */

public class ActivityChineseCharacterByRadical extends ActivityChineseCharacterBase {
    private static final short CHINESE_CHARACTER_NUM = 36;

    @Override
    protected void initLoadData() {
        Intent intent = getIntent();
        Radicals radicals = (Radicals) intent.getSerializableExtra("radicals");
        setTitleContent("部首为 " + radicals.radical + " 的字");
        List<ChineseCharacter> chineseCharacterList = getCtrApplication().getDaoManager().getTableZi().queryDataByStrokeAndRadicals(radicals.radical, -1);
        if (chineseCharacterList.size() > CHINESE_CHARACTER_NUM) {
            grid_view_zi.setVisibility(View.GONE);
            list_view_chinese_character_and_stroke.setVisibility(View.VISIBLE);

            Map<Integer, List<ChineseCharacter>> listMap = getCtrApplication().getDaoManager().getTableZi().queryAllData(radicals.radical);
            adapterHanZiAndStroke = new AdapterHanZiAndStroke(this, listMap);
            list_view_chinese_character_and_stroke.setAdapter(adapterHanZiAndStroke);
        } else {
            grid_view_zi.setVisibility(View.VISIBLE);
            list_view_chinese_character_and_stroke.setVisibility(View.GONE);
            adapterHanZi = new AdapterHanZi(this, this, null);
            grid_view_zi.setAdapter(adapterHanZi);
            adapterHanZi.setData(chineseCharacterList);
        }

    }
}