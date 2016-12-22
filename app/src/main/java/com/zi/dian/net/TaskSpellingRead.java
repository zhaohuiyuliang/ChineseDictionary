package com.zi.dian.net;

import android.util.Log;

import com.zi.dian.dao.TablePyRead;
import com.zi.dian.dao.model.HanZi;
import com.zi.dian.dao.model.PyRead;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by wangliang on 6/20/16.
 */
public class TaskSpellingRead extends LoadData {
    String TAG = "TaskSpellingRead";

    public TaskSpellingRead(String linkUrl) {
        url = linkUrl;
    }

    @Override
    public void run() {
        List<PyRead> pyReadList = parserPyRead();
        TablePyRead tablePyRead = getAppliction().getDaoManager().getTablePyRead();
        for (PyRead pyRead : pyReadList) {
            if (!tablePyRead.isData(pyRead.py)) {
                Log.d(TAG, pyRead.py);
                tablePyRead.insertData(pyRead);
            }
        }

    }

    @Override
    public void completeLoad(String str) {

    }


    private List<PyRead> parserPyRead() {
        List<PyRead> hanZiList = new ArrayList<>();
        try {
            Parser htmlParser = new Parser(url);
            htmlParser.setEncoding("GBK");
            // 获取指定的 li 节点，即 <li> 标签
            NodeList divOfTab2 = htmlParser.extractAllNodesThatMatch(
                    new AndFilter(new TagNameFilter("li"), new HasAttributeFilter("id", "py")));
            if (divOfTab2 != null && divOfTab2.size() > 0) {
                // 获取指定  标签
                Node liNode = divOfTab2.elementAt(0);
                NodeList ddChildNodeList = liNode.getChildren();
                PyRead pyRead = null;
                for (int i = 1; i < ddChildNodeList.size() - 1; ++i) {
                    //读音
                    Node aNode = ddChildNodeList.elementAt(i);
                    if (i % 2 != 0) {
                        pyRead = new PyRead();
                        String spelling = aNode.getText();
                        pyRead.py = spelling;
                    } else {
                        String content = aNode.getChildren().elementAt(0).getText();
                        pyRead.tone = content.substring(7, content.length() - 2);
                        hanZiList.add(pyRead);
                    }
                }
            }
        } catch (ParserException e) {
            e.printStackTrace();
        }

        return hanZiList;
    }

}
