package com.zi.dian.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.zi.dian.ControlApplication;
import com.zi.dian.adapter.AdapterViewPagerHome;
import com.zi.dian.custom.view.ViewPagerHome;
import com.zi.dian.dao.TableCollectZi;
import com.zi.dian.dao.TableHistoryZi;
import com.zi.dian.dao.TablePyRead;
import com.zi.dian.dao.model.ChineseCharacterParaphrase;
import com.zi.dian.dao.model.CollectZi;
import com.zi.dian.dao.model.HistoryZi;
import com.zi.dian.dao.model.PyRead;
import com.zi.dian.net.TaskErrorCorrection;
import com.zi.dian.ui.fragment.FragmentBase;
import com.zi.dian.ui.fragment.FragmentChineseCharacterExplain;
import com.zi.dian.unitl.SundUnitl;

import java.util.ArrayList;
import java.util.List;

import dian.zi.com.zidian.R;

import static dian.zi.com.zidian.R.id.tv_paraphrase;

/**
 * Created by wangliang on 6/17/16.
 */
public class ActivityChineseCharacterDetail extends AppCompatActivity implements View.OnClickListener {
    private ChineseCharacterParaphrase hanZi;
    private ViewPagerHome mViewPagerHome;
    private TextView tv_chinese_character;
    private TextView tv_spelling_;
    private TextView tv_spelling_2;
    private TextView tv_base;
    private TextView tv_detail;
    private TextView tv_dh;
    private TablePyRead tablePyRead;
    private TextView tv_collect;
    private FragmentChineseCharacterExplain mExplain;
    private FragmentChineseCharacterExplain mExplain2;
    private AdapterViewPagerHome adapterViewPagerHome;
    private ControlApplication mApplication = ControlApplication.getApplication();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinese_character_detial);
        tablePyRead = ControlApplication.getApplication().getDaoManager().getTablePyRead();
        hanZi = (ChineseCharacterParaphrase) getIntent().getSerializableExtra("ChineseCharacterParaphrase");
        initView();
        loadData();
    }

    protected void initView() {
        tv_collect = (TextView) findViewById(R.id.tv_collect);
        mViewPagerHome = (ViewPagerHome) findViewById(R.id.view_pager_explain);
        tv_collect.setVisibility(View.VISIBLE);
        tv_chinese_character = (TextView) findViewById(R.id.tv_chinese_character);
        tv_spelling_ = (TextView) findViewById(R.id.tv_spelling_);
        tv_spelling_2 = (TextView) findViewById(R.id.tv_spelling_2);
        tv_base = (TextView) findViewById(R.id.tv_base);
        tv_detail = (TextView) findViewById(R.id.tv_detail);
        tv_dh = (TextView) findViewById(R.id.tv_error_correction);

        tv_base.setOnClickListener(this);
        tv_detail.setOnClickListener(this);
        tv_dh.setOnClickListener(this);
        tv_spelling_2.setOnClickListener(this);
        tv_spelling_.setOnClickListener(this);
        tv_collect.setOnClickListener(this);
        tv_base.setTextColor(getResources().getColor(R.color.colorRed));
        mExplain = new FragmentChineseCharacterExplain();
        mExplain2 = new FragmentChineseCharacterExplain();
        Bundle bundle = new Bundle();
        bundle.putString("content", hanZi.baseParaphrase);
        mExplain.setArguments(bundle);
        Bundle bundle2 = new Bundle();
        bundle2.putString("content", hanZi.detailedParaphrase);
        mExplain2.setArguments(bundle2);

        List<FragmentBase> fragmentBaseList = new ArrayList<>();
        fragmentBaseList.add(mExplain);
        fragmentBaseList.add(mExplain2);
        adapterViewPagerHome = new AdapterViewPagerHome(getSupportFragmentManager(), fragmentBaseList);
        mViewPagerHome.setAdapter(adapterViewPagerHome);
        mViewPagerHome.setPageSelected(new ViewPagerHome.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0: {
                        tv_base.setTextColor(getResources().getColor(R.color.colorRed));
                        tv_detail.setTextColor(getResources().getColor(R.color.colorBlack));
                        tv_dh.setTextColor(getResources().getColor(R.color.colorBlack));
                        break;
                    }
                    case 1: {
                        tv_base.setTextColor(getResources().getColor(R.color.colorBlack));
                        tv_detail.setTextColor(getResources().getColor(R.color.colorRed));
                        tv_dh.setTextColor(getResources().getColor(R.color.colorBlack));
                        break;
                    }
                }

            }
        });

    }

    private void loadData() {
        tv_chinese_character.setText(hanZi.zi);
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
        TableCollectZi tableUseZi = mApplication.getDaoManager().getTableCollectZi();
        TableHistoryZi tableHistoryZi = mApplication.getDaoManager().getTableHistroyZi();
        HistoryZi historyZi = new HistoryZi();
        historyZi.zi = hanZi.zi;
        tableHistoryZi.insertData(historyZi);

        boolean useZi = tableUseZi.isData(hanZi.zi);
        if (useZi) {
            tv_collect.setText("取消收藏");
        } else {
            tv_collect.setText("收藏");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_spelling_2: {
                String spelling_2 = tv_spelling_2.getText().toString();
                PyRead pyRead = tablePyRead.queryDataByZi(spelling_2);
                SundUnitl.startPlayVideo(pyRead.tone);
                break;
            }
            case R.id.tv_spelling_: {
                String spelling_ = tv_spelling_.getText().toString();
                PyRead pyRead = tablePyRead.queryDataByZi(spelling_);
                SundUnitl.startPlayVideo(pyRead.tone);
                break;
            }
            case R.id.tv_chinese_character: {
                break;
            }

            case R.id.tv_base: {

                tv_base.setTextColor(getResources().getColor(R.color.colorRed));
                tv_detail.setTextColor(getResources().getColor(R.color.colorBlack));
                tv_dh.setTextColor(getResources().getColor(R.color.colorBlack));
                mViewPagerHome.setCurrentItem(0);
            }
            break;
            case R.id.tv_detail: {

                tv_base.setTextColor(getResources().getColor(R.color.colorBlack));
                tv_detail.setTextColor(getResources().getColor(R.color.colorRed));
                tv_dh.setTextColor(getResources().getColor(R.color.colorBlack));
                mViewPagerHome.setCurrentItem(1);
                break;
            }
            case R.id.tv_error_correction: {//汉字纠错
                tv_base.setTextColor(getResources().getColor(R.color.colorBlack));
                tv_detail.setTextColor(getResources().getColor(R.color.colorBlack));
                tv_dh.setTextColor(getResources().getColor(R.color.colorRed));
                break;
            }
            case tv_paraphrase: {
                new Thread(new TaskErrorCorrection(hanZi)).start();
                break;
            }
            case R.id.tv_collect: {
                TableCollectZi tableUseZi = mApplication.getDaoManager().getTableCollectZi();
                if (((TextView) v).getText().toString().compareTo("取消收藏") == 0) {
                    tv_collect.setText("收藏");
                    tableUseZi.deleteDataByZi(hanZi.zi);
                } else if (((TextView) v).getText().toString().compareTo("收藏") == 0) {
                    tv_collect.setText("取消收藏");
                    CollectZi collectZi = new CollectZi();
                    collectZi.zi = hanZi.zi;
                    tableUseZi.insertData(collectZi);
                }

                break;
            }
            default: {
                break;
            }
        }

    }
}
