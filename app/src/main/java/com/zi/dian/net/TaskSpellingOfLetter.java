package com.zi.dian.net;

import android.util.Log;

import com.zi.dian.dao.TableLetter;
import com.zi.dian.dao.model.LetterSpelling;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import java.util.ArrayList;
import java.util.List;

/**
 * 解析"在线新华字典"->"按拼音查字"的html内容
 * Created by wangliang on 6/20/16.
 */
public class TaskSpellingOfLetter extends LoadData {
    String TAG = "TaskSpellingOfLetter";

    public TaskSpellingOfLetter() {
        url = "http://zd.diyifanwen.com/zidian/py/";
    }

    @Override
    public void run() {
        List<LetterSpelling> spellingList = parserLetterSpelling();
        TableLetter tablePyRead = getAppliction().getDaoManager().getTableTableLetter();
        tablePyRead.batchInsertData(spellingList);
        for (LetterSpelling spelling : spellingList) {
            new TaskHanZiOfSpelling(spelling).run();
        }
    }

    @Override
    public void completeLoad(String str) {

    }


    private List<LetterSpelling> parserLetterSpelling() {
        List<LetterSpelling> spellingList = new ArrayList<>();
        try {
            Parser htmlParser = new Parser(url);
            htmlParser.setEncoding("GBK");
            // 获取指定的 dd 节点，即 <dd> 标签
            NodeList divOfTab2 = htmlParser.extractAllNodesThatMatch(
                    new AndFilter(new TagNameFilter[]{(new TagNameFilter("dd"))}));
            if (divOfTab2 != null && divOfTab2.size() > 0) {
                for (int i = 0; i < divOfTab2.size(); ++i) {
                    // 获取指定 dd 标签
                    Node ddNode = divOfTab2.elementAt(i);
                    NodeList ddChildNodeList = ddNode.getChildren();

                    if (ddChildNodeList != null) {
                        //获取指定 dd 标签的子节点中的 <a> 节点
                        Node aNode = ddChildNodeList.elementAt(0);
                        // 在 <a> 节点的子节点中获取 Link 节点
                        LetterSpelling spelling = new LetterSpelling();
                        spelling.spelling = ((LinkTag) aNode).getLinkText();
                        spelling.link = ((LinkTag) aNode).getLink();
                        spelling.letter = spelling.spelling.substring(0, 1);
                        spellingList.add(spelling);
                        Log.d(TAG, spelling.letter + " " + spelling.spelling + " " + spelling.link);
                    }
                }
            }
        } catch (ParserException e) {
            e.printStackTrace();
        }
        return spellingList;
    }

}
