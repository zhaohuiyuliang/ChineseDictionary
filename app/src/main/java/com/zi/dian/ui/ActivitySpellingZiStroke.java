package com.zi.dian.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.zi.dian.ControlApplication;
import com.zi.dian.adapter.AdapterSpellingZi;
import com.zi.dian.adapter.AdapterSpellingZiAndStroke;
import com.zi.dian.dao.TableSpellingZi;
import com.zi.dian.dao.model.ChineseCharacterParaphrase;
import com.zi.dian.dao.model.SpellingZi;

import java.util.List;
import java.util.Map;

import dian.zi.com.zidian.R;

/**
 * Created by wangliang on 6/28/16.
 */
public class ActivitySpellingZiStroke extends ActivityBase implements AdapterSpellingZi.IModelSpellingZi {
    private ListView list_view_spelling_zi_stroke;
    private AdapterSpellingZiAndStroke adapterSpellingZiStroke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spelling_zi_stroke);
        initView();
        loadData();
    }

    protected void initView() {
        super.initView();
        list_view_spelling_zi_stroke = (ListView) findViewById(R.id.list_view_spelling_zi_stroke);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected void loadData() {
        Intent intent = getIntent();
        String spelling = intent.getStringExtra("spelling");
        setTitleContent("拼音 " + spelling + " 的汉字");
        TableSpellingZi taskSpellingZi = getCtrApplication().getDaoManager().getTableSpellingZi();
        Map<Integer, List<SpellingZi>> listMap = taskSpellingZi.queryMapBySpelling(spelling);
        adapterSpellingZiStroke = new AdapterSpellingZiAndStroke(listMap, this, this);
        list_view_spelling_zi_stroke.setAdapter(adapterSpellingZiStroke);
    }

    @Override
    public void setOnclickListener(SpellingZi spellingZi) {
        Intent intent = new Intent(this, ActivityChineseCharacterDetail.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ChineseCharacterParaphrase hanZiParaphrase = ControlApplication.getApplication().getDaoManager().getTableZiParaphrase().queryDataByZi(spellingZi.zi);
        intent.putExtra("ChineseCharacterParaphrase", hanZiParaphrase);
        getApplication().startActivity(intent);
    }
}
