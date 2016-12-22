package com.zi.dian;

import android.app.Application;

import com.zi.dian.dao.impl.DaoManager;
import com.zi.dian.net.Controller;
import com.zi.dian.ui.FragmentBase;

/**
 * Created by wangliang on 6/13/16.
 */
public class ControlApplication extends Application {
    private static ControlApplication application;
    private Controller controller;
    private FragmentBase fragmentBase;
    private DaoManager daoManager;
    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public static ControlApplication getApplication() {
        return application;
    }

    public Controller getController() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }

    public DaoManager getDaoManager() {
        if (daoManager == null) {
            daoManager = new DaoManager(this);
        }
        return daoManager;
    }

    public FragmentBase getFragmentBase() {
        return fragmentBase;
    }

    public void setFragmentBase(FragmentBase fragmentBase) {
        this.fragmentBase = fragmentBase;
    }
}
