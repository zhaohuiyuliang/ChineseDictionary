package com.zi.dian.ui;

import android.content.Intent;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.zi.dian.ControlApplication;
import com.zi.dian.adapter.AdapterContent;
import com.zi.dian.dao.TableHistoryZi;
import com.zi.dian.dao.model.ChineseCharacterParaphrase;
import com.zi.dian.dao.model.HistoryZi;

import java.util.List;

import dian.zi.com.zidian.R;

/**
 * Created by wangliang on 6/24/16.
 */
public class FragmentHistoryBrowse extends FragmentBase implements AdapterContent.IModelContent {
    private GridView grid_view_history;
    private AdapterContent adapterContent;
    private TextView tv_clear_history;


    @Override
    public int getResLayout() {
        return R.layout.fragment_histroy_find;
    }


    @Override
    public void onResume() {
        super.onResume();
        initLoadData();
    }

    @Override
    protected void initView() {
        grid_view_history = (GridView) view.findViewById(R.id.grid_view_history);
        tv_clear_history = (TextView) view.findViewById(R.id.tv_clear_history);
        tv_clear_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TableHistoryZi tableUseZi = getApplication().getDaoManager().getTableHistroyZi();
                tableUseZi.clearTable();
                initLoadData();
            }
        });
    }

    private void initLoadData() {
        TableHistoryZi tableUseZi = getApplication().getDaoManager().getTableHistroyZi();
        List<HistoryZi> useZiList = tableUseZi.queryData();
        adapterContent = new AdapterContent(getActivity(), useZiList, this);
        grid_view_history.setAdapter(adapterContent);
    }

    @Override
    public void setOnclickListener(String zi) {
        Intent intent = new Intent(getActivity(), ActivityHanZiDetail.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ChineseCharacterParaphrase hanZiParaphrase = ControlApplication.getApplication().getDaoManager().getTableZiParaphrase().queryDataByZi(zi);
        intent.putExtra("ChineseCharacterParaphrase", hanZiParaphrase);
        getApplication().startActivity(intent);
    }

    @Override
    public void refreshView(List<?> str) {

    }
}
