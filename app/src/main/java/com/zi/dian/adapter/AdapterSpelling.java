package com.zi.dian.adapter;

import android.content.Context;
import android.view.View;

import com.zi.dian.dao.model.LetterSpelling;

import java.util.List;

import dian.zi.com.zidian.R;

/**
 * Created by wangliang on 6/28/16.
 */
public class AdapterSpelling extends BaseCAdapter<LetterSpelling> {
    private IModelSpelling modelSpelling;
    private List<LetterSpelling> mLetterSpelling;

    public AdapterSpelling(List<LetterSpelling> letterSpellingList, Context context, IModelSpelling modelSpelling) {
        super(context, letterSpellingList);
        this.modelSpelling = modelSpelling;
    }

    public void setData(List<LetterSpelling> letterSpellingList) {
        this.mLetterSpelling = letterSpellingList;
        notifyDataSetChanged();
    }

    @Override
    protected int getReLayout() {
        return R.layout.item_spelling;
    }

    @Override
    protected void setData(final LetterSpelling localT, HoldView holdView, int position) {
        holdView.setText(R.id.tv_spelling, localT.spelling);
        holdView.setOnClickListener(R.id.tv_spelling, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modelSpelling.setOnclickListener(localT);
            }
        });

    }

    public interface IModelSpelling {
        void setOnclickListener(LetterSpelling letterSpelling);
    }

}
