package com.zi.dian.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.zi.dian.ControlApplication;
import com.zi.dian.adapter.AdapterContent;
import com.zi.dian.dao.TableCollectZi;
import com.zi.dian.dao.model.ChineseCharacterParaphrase;
import com.zi.dian.dao.model.CollectZi;
import com.zi.dian.ui.ActivityChineseCharacterDetail;

import java.util.List;

import dian.zi.com.zidian.R;

/**
 * Created by wangliang on 6/24/16.
 */
public class FragmentCollection extends FragmentBase implements AdapterContent.IModelContent {
    private GridView grid_view_collect;
    private AdapterContent adapterContent;
    private TextView tv_clear_collect;


    @Override
    public void onResume() {
        super.onResume();
        initLoadData();
    }

    @Override
    public int getResLayout() {
        return R.layout.fragment_collection;
    }

    @Override
    protected void initView() {
        grid_view_collect = (GridView) view.findViewById(R.id.grid_view_collection);

        tv_clear_collect = (TextView) view.findViewById(R.id.tv_clear_collect);
        tv_clear_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TableCollectZi tableUseZi = getApplication().getDaoManager().getTableCollectZi();
                tableUseZi.clearTable();
                initLoadData();
            }
        });
    }

    private void initLoadData() {
        TableCollectZi tableUseZi = getApplication().getDaoManager().getTableCollectZi();
        List<CollectZi> useZiList = tableUseZi.queryData();
        adapterContent = new AdapterContent(getActivity(), useZiList, this);
        grid_view_collect.setAdapter(adapterContent);
    }

    @Override
    public void setOnclickListener(String zi) {
        Intent intent = new Intent(getActivity(), ActivityChineseCharacterDetail.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ChineseCharacterParaphrase hanZiParaphrase = ControlApplication.getApplication().getDaoManager().getTableZiParaphrase().queryDataByZi(zi);
        intent.putExtra("ChineseCharacterParaphrase", hanZiParaphrase);
        getApplication().startActivity(intent);
    }

    @Override
    public void refreshView(List<?> str) {

    }
}
