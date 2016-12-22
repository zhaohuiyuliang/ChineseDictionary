package com.zi.dian.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zi.dian.ControlApplication;
import com.zi.dian.dao.TableCollectZi;
import com.zi.dian.dao.TablePyRead;
import com.zi.dian.dao.TableHistroyZi;
import com.zi.dian.dao.model.CollectZi;
import com.zi.dian.dao.model.HanZiParaphrase;
import com.zi.dian.dao.model.PyRead;
import com.zi.dian.dao.model.HistroyZi;
import com.zi.dian.net.TaskErrorCorrection;
import com.zi.dian.unitl.SundUnitl;

import dian.zi.com.zidian.R;

/**
 * Created by wangliang on 6/17/16.
 */
public class ActivityHanZiDetail extends ActivityBase implements View.OnClickListener {
    private HanZiParaphrase hanZi;
    private TextView tv_hanzi_;
    private TextView tv_spelling_;
    private TextView tv_spelling_2;
    private TextView tv_base;
    private TextView tv_detail;
    private TextView tv_dh;
    private TextView tv_paraphrase;
    private TablePyRead tablePyRead;
    private TextView tv_collect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hanzi_detial);
        tablePyRead = ControlApplication.getApplication().getDaoManager().getTablePyRead();
        hanZi = (HanZiParaphrase) getIntent().getSerializableExtra("HanZiParaphrase");
        initView();
        loadData();
    }

    protected void initView() {
        super.initView();
        tv_collect = (TextView) findViewById(R.id.tv_collect);
        tv_collect.setVisibility(View.VISIBLE);
        tv_hanzi_ = (TextView) findViewById(R.id.tv_hanzi_);
        tv_spelling_ = (TextView) findViewById(R.id.tv_spelling_);
        tv_spelling_2 = (TextView) findViewById(R.id.tv_spelling_2);
        tv_base = (TextView) findViewById(R.id.tv_base);
        tv_detail = (TextView) findViewById(R.id.tv_detail);
        tv_dh = (TextView) findViewById(R.id.tv_error_correction);
        tv_paraphrase = (TextView) findViewById(R.id.tv_paraphrase);
        tv_base.setOnClickListener(this);
        tv_detail.setOnClickListener(this);
        tv_dh.setOnClickListener(this);
        tv_spelling_2.setOnClickListener(this);
        tv_spelling_.setOnClickListener(this);
        tv_collect.setOnClickListener(this);
        tv_base.setTextColor(getResources().getColor(R.color.colorRed));

    }

    private void loadData() {
        setTitleContent(hanZi.zi + "字详情");
        tv_hanzi_.setText(hanZi.zi);
        String spelling = hanZi.spelling.substring(1, hanZi.spelling.length() - 1);
        String[] spellings = spelling.split(",");
        for (int i = 0; i < spellings.length; i++) {
            if (i == 0) {
                tv_spelling_.setText(spellings[i]);
            } else if (i == 1) {
                tv_spelling_2.setText(spellings[i].replace(" ", ""));
                tv_spelling_2.setVisibility(View.VISIBLE);
            }
        }
        tv_paraphrase.setText(hanZi.baseParaphrase);
        TableCollectZi tableUseZi = getCtrApplication().getDaoManager().getTableCollectZi();
        TableHistroyZi tableHistroyZi = getCtrApplication().getDaoManager().getTableHistroyZi();
        HistroyZi histroyZi = new HistroyZi();
        histroyZi.zi = hanZi.zi;
        tableHistroyZi.insertData(histroyZi);

        boolean useZi = tableUseZi.isData(hanZi.zi);
        if (useZi) {
            tv_collect.setText("取消收藏");
            tv_collect.setTextColor(getResources().getColor(R.color.colorRed));
        } else {
            tv_collect.setText("收藏");
            tv_collect.setTextColor(getResources().getColor(R.color.colorBlack));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_spelling_2: {
                String spelling_2 = tv_spelling_2.getText().toString();
                PyRead pyRead = tablePyRead.queryDataByZi(spelling_2);
                SundUnitl.startPlayVideo(pyRead.tone);
            }
            break;
            case R.id.tv_spelling_: {
                String spelling_ = tv_spelling_.getText().toString();
                PyRead pyRead = tablePyRead.queryDataByZi(spelling_);
                SundUnitl.startPlayVideo(pyRead.tone);
            }
            break;
            case R.id.tv_hanzi_:
                break;

            case R.id.tv_base:
                tv_paraphrase.setText(hanZi.baseParaphrase);
                tv_paraphrase.setOnClickListener(null);

                tv_base.setTextColor(getResources().getColor(R.color.colorRed));
                tv_detail.setTextColor(getResources().getColor(R.color.colorBlack));
                tv_dh.setTextColor(getResources().getColor(R.color.colorBlack));
                break;
            case R.id.tv_detail:
                tv_paraphrase.setText(hanZi.detailedParaphrase);
                tv_paraphrase.setOnClickListener(null);

                tv_base.setTextColor(getResources().getColor(R.color.colorBlack));
                tv_detail.setTextColor(getResources().getColor(R.color.colorRed));
                tv_dh.setTextColor(getResources().getColor(R.color.colorBlack));
                break;
            case R.id.tv_error_correction://汉字纠错
                tv_paraphrase.setText("点击纠错");
                tv_paraphrase.setOnClickListener(this);
                tv_base.setTextColor(getResources().getColor(R.color.colorBlack));
                tv_detail.setTextColor(getResources().getColor(R.color.colorBlack));
                tv_dh.setTextColor(getResources().getColor(R.color.colorRed));
                break;
            case R.id.tv_paraphrase: {
                new Thread(new TaskErrorCorrection(hanZi)).start();
            }
            break;
            case R.id.tv_collect: {
                TableCollectZi tableUseZi = getCtrApplication().getDaoManager().getTableCollectZi();
                if (((TextView) v).getText().toString().compareTo("取消收藏") == 0) {
                    tv_collect.setText("收藏");
                    tableUseZi.deleteDataByZi(hanZi.zi);
                    tv_collect.setTextColor(getResources().getColor(R.color.colorBlack));
                } else if (((TextView) v).getText().toString().compareTo("收藏") == 0) {
                    tv_collect.setTextColor(getResources().getColor(R.color.colorRed));
                    tv_collect.setText("取消收藏");
                    CollectZi collectZi = new CollectZi();
                    collectZi.zi = hanZi.zi;
                    tableUseZi.insertData(collectZi);
                }
            }

            break;
            default:
                break;
        }

    }
}
