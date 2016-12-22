package com.zi.dian.ui;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.zi.dian.ControlApplication;
import com.zi.dian.adapter.AdapterContent;
import com.zi.dian.dao.TableHistroyZi;
import com.zi.dian.dao.model.HanZiParaphrase;
import com.zi.dian.dao.model.HistroyZi;

import java.util.List;

import dian.zi.com.zidian.R;

/**
 * Created by wangliang on 6/24/16.
 */
public class FragmentHistroyFind extends FragmentBase implements AdapterContent.IModelContent {
    private GridView grid_view_histroy;
    private AdapterContent adapterContent;
    private TextView tv_clear_histroy;

    @Override
    public View loadViewLayout(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.fragment_histroy_find, viewGroup, false);
        initView();
        getApplication().setFragmentBase(this);
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        initLoadData();
    }

    private void initView() {
        grid_view_histroy = (GridView) view.findViewById(R.id.grid_view_histroy);
        tv_clear_histroy = (TextView) view.findViewById(R.id.tv_clear_histroy);
        tv_clear_histroy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TableHistroyZi tableUseZi = getApplication().getDaoManager().getTableHistroyZi();
                tableUseZi.clearTable();
                initLoadData();
            }
        });
    }

    private void initLoadData() {
        TableHistroyZi tableUseZi = getApplication().getDaoManager().getTableHistroyZi();
        List<HistroyZi> useZiList = tableUseZi.queryData();
        adapterContent = new AdapterContent(getActivity(), useZiList, this);
        grid_view_histroy.setAdapter(adapterContent);
    }

    @Override
    public void setOnclickListener(String zi) {
        Intent intent = new Intent(getActivity(), ActivityHanZiDetail.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        HanZiParaphrase hanZiParaphrase = ControlApplication.getApplication().getDaoManager().getTableZiParaphrase().queryDataByZi(zi);
        intent.putExtra("HanZiParaphrase", hanZiParaphrase);
        getApplication().startActivity(intent);
    }

    @Override
    public void refreshView(List<?> str) {

    }
}
