package com.zi.dian.dao.impl;

/**
 * Created by wangliang on 6/14/16.
 */
public class DBVersionController {

    static final int DB_VERSION = 13;

    static final String DATABASE_NAME = "ZiDian.db";

    //版本一的表结构
    static final String SQL_CREATE_BS_TABLE = "create table if not exists bs_table (radical text, stroke text, linkUrl text, primary key (radical, stroke));";


    static final String SQL_CREATE_BS_ZI_TABLE = "create table if not exists bs_zi_table (radical text, zi text, spelling text, stroke int, linkUrl text, primary key (zi, spelling));";

    static final String SQL_CREATE_BS_ZI_Paraphrase_TABLE = "create table if not exists bs_zi_paraphrase_table (radical text, zi text, spelling text, stroke int, linkUrl text, baseParaphrase text, detailedParaphrase text, primary key (zi, spelling));";

    static final String SQL_CREATE_ZI_PY_READ = "create table if not exists py_read_table (py text, tone text,primary key (py, tone));";
    //搜查过的汉字
    static final String SQL_CREATE_TABLE_HISTROY_ZI = "create table if not exists table_histroy_zi (zi text primary key);";
    //收藏的汉字表

    static final String SQL_CREATE_TABLE_COLLENT_ZI = "create table if not exists table_collect_zi (zi text primary key);";

    static final String SQL_CREATE_TABLE_LETTER_SPELLING = "create table if not exists table_letter_spelling (letter text , spelling text, linkUrl text, primary key(letter, spelling));";

    static final String SQL_CREATE_TABLE_SPELLING_ZI = "create table if not exists table_spelling_zi (spelling text, zi text, stroke short,  primary key(spelling, zi));";
    //版本二的表结构
    //版本三的表结构

}
