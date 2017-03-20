package com.zi.dian.ui;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.zi.dian.ControlApplication;
import com.zi.dian.adapter.AdapterContent;
import com.zi.dian.dao.TableCollectZi;
import com.zi.dian.dao.model.ChineseCharacterParaphrase;
import com.zi.dian.dao.model.CollectZi;

import java.util.List;

import dian.zi.com.zidian.R;

/**
 * Created by wangliang on 6/24/16.
 */
public class FragmentCollection extends FragmentBase implements AdapterContent.IModelContent{
    private GridView grid_view_collect;
    private AdapterContent adapterContent;
    private TextView tv_clear_collect;


    @Override
    public void onResume() {
        super.onResume();
        initLoadData();
    }

    @Override
    public View loadViewLayout(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.fragment_collection, viewGroup, false);
        initView();
        getApplication().setFragmentBase(this);
        return view;
    }

    private void initView() {
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
        adapterContent = new AdapterContent(getActivity(), useZiList,this);
        grid_view_collect.setAdapter(adapterContent);
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
