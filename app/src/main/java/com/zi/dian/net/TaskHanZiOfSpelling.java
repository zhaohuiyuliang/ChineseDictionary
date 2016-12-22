package com.zi.dian.net;

import com.zi.dian.dao.TableSpellingZi;
import com.zi.dian.dao.model.LetterSpelling;
import com.zi.dian.dao.model.SpellingZi;

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
 * Created by wangliang on 6/27/16.
 */
public class TaskHanZiOfSpelling extends LoadData {
    private String url;
    private LetterSpelling letterSpelling;

    public TaskHanZiOfSpelling(LetterSpelling letterSpelling) {
        this.url = letterSpelling.link;
        this.letterSpelling = letterSpelling;
    }

    @Override
    public void run() {
        List<SpellingZi> spellingZiList = parserSpellingZi();
        TableSpellingZi tableSpellingZi = getAppliction().getDaoManager().getTableSpellingZi();
        tableSpellingZi.batchInsertData(spellingZiList);
    }

    @Override
    public void completeLoad(String str) {

    }

    private List<SpellingZi> parserSpellingZi() {
        List<SpellingZi> hanZiList = new ArrayList<>();
        try {
            Parser htmlParser = new Parser(url);
            htmlParser.setEncoding("GBK");
            //获取指定的 dd 节点，即 <dd> 标签
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

                        String title = ((LinkTag) aNode).getAttribute("title");
                        String[] titles = title.split("&#10");

                        SpellingZi hanZi = new SpellingZi();
                        hanZi.zi = ((LinkTag) aNode).getLinkText();
                        hanZi.stroke = Integer.valueOf(titles[2].split("：")[1]);
                        hanZi.spelling = letterSpelling.spelling;
                        hanZiList.add(hanZi);
                    }
                }
            }
        } catch (ParserException e) {
            e.printStackTrace();
        }

        return hanZiList;
    }


}
