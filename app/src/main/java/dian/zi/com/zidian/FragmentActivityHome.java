package dian.zi.com.zidian;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.widget.ListView;
import android.widget.Toast;

import com.zi.dian.adapter.AdapterHome;
import com.zi.dian.adapter.AdapterViewPagerHome;
import com.zi.dian.custom.view.ViewPagerHome;
import com.zi.dian.ui.FragmentBase;
import com.zi.dian.ui.FragmentCollection;
import com.zi.dian.ui.FragmentHistroyFind;
import com.zi.dian.ui.FragmentLocateByRadical;
import com.zi.dian.ui.FragmentLocateBySpelling;
import com.zi.dian.ui.FragmentLocateByStroke;
import com.zi.dian.ui.FragmentSettings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wangliang on 6/12/16.
 */
public class FragmentActivityHome extends FragmentActivity implements AdapterHome.IModelHome {
//    private String[] iconName = {"部首", "笔画", "拼音", "历史", "收藏", "设置"};
    private String[] iconName = {"部首", "笔画", "拼音", "历史", "收藏"};
    private ListView lview;
    private List data_list;
    private AdapterHome adapterHome;

    private ViewPagerHome view_pager_home;

    private AdapterViewPagerHome adapterViewPagerHome;

    private FragmentLocateByRadical fragmentLookByRadical;
    private FragmentLocateBySpelling fragmentLookBySpelling;
    private FragmentLocateByStroke fragmentLookByStroke;
    private FragmentHistroyFind fragmentHistroyFind;
    private FragmentCollection fragmentCollection;
//    private FragmentSettings fragmentSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        initData();
    }

    private void initView() {
        lview = (ListView) findViewById(R.id.lview_home);
        view_pager_home = (ViewPagerHome) findViewById(R.id.view_pager_home);
        view_pager_home.setScrollble(false);
    }

    private void initData() {
        fragmentLookByRadical = new FragmentLocateByRadical();
        fragmentLookByStroke = new FragmentLocateByStroke();
        fragmentLookBySpelling = new FragmentLocateBySpelling();
        fragmentHistroyFind = new FragmentHistroyFind();
        fragmentCollection = new FragmentCollection();
//        fragmentSettings = new FragmentSettings();
        List<FragmentBase> fragmentBaseList = new ArrayList<>();
        fragmentBaseList.add(fragmentLookByRadical);
        fragmentBaseList.add(fragmentLookByStroke);
        fragmentBaseList.add(fragmentLookBySpelling);
        fragmentBaseList.add(fragmentHistroyFind);
        fragmentBaseList.add(fragmentCollection);
//        fragmentBaseList.add(fragmentSettings);
        adapterViewPagerHome = new AdapterViewPagerHome(getSupportFragmentManager(), fragmentBaseList);
        view_pager_home.setAdapter(adapterViewPagerHome);

        data_list = Arrays.asList(iconName);
        adapterHome = new AdapterHome(this, this, data_list);
        lview.setAdapter(adapterHome);
    }

    @Override
    public void setOnclickListener(int position) {
        switch (position) {
            case 0: {
                adapterHome.setPosition(0);
                view_pager_home.setCurrentItem(0, false);
            }

            break;
            case 1: {
                adapterHome.setPosition(1);
                view_pager_home.setCurrentItem(1, false);

            }
            break;
            case 2: {
                adapterHome.setPosition(2);
                view_pager_home.setCurrentItem(2, false);

            }
            break;
            case 3: {
                adapterHome.setPosition(3);
                view_pager_home.setCurrentItem(3, false);

            }
            break;
            case 4: {
                adapterHome.setPosition(4);
                view_pager_home.setCurrentItem(4, false);

            }
            break;
            case 5: {
                adapterHome.setPosition(5);
                view_pager_home.setCurrentItem(5, false);

            }
            break;
            default:
                break;

        }
    }

    private long mExitTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

