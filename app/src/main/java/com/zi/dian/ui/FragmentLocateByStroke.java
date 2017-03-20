package com.zi.dian.ui;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.zi.dian.adapter.AdapterStrokeOfHanZi;
import com.zi.dian.dao.TableZi;

import java.util.List;

import dian.zi.com.zidian.R;

/**
 * 按笔画查找汉字
 * Created by wangliang on 6/13/16.
 */
public class FragmentLocateByStroke extends FragmentBase implements AdapterStrokeOfHanZi.IModelHanZiStroke {
    private GridView grid_view_stroke;
    private AdapterStrokeOfHanZi adapterHanZiStroke;

    @Override
    public View loadViewLayout(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.fragment_look_zi_by_stroke, viewGroup, false);
        initView();
        getApplication().setFragmentBase(this);
        initLoadData();
        return view;
    }

    private void initView() {
        grid_view_stroke = (GridView) view.findViewById(R.id.grid_view_stroke);
    }

    private void initLoadData() {
        TableZi tableZi = getApplication().getDaoManager().getTableZi();
        List<Integer> integerList = tableZi.queryAllStroke();
        adapterHanZiStroke = new AdapterStrokeOfHanZi(getActivity(), this, integerList);
        grid_view_stroke.setAdapter(adapterHanZiStroke);
    }

    @Override
    public void refreshView(List str) {
        Message msg = new Message();
        msg.obj = str;
        handler.sendMessage(msg);

    }

    @Override
    public void setOnClickListener(int stroke) {
        Intent intent = new Intent(getActivity(), ActivityChineseCharacter.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("stroke", stroke);
        getApplication().startActivity(intent);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
        }
    };

}
