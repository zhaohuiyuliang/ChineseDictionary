package dian.zi.com.zidian;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Toast;

import com.zi.dian.adapter.AdapterViewPagerHome;
import com.zi.dian.custom.view.ViewPagerHome;
import com.zi.dian.ui.fragment.FragmentBase;
import com.zi.dian.ui.fragment.FragmentCollection;
import com.zi.dian.ui.fragment.FragmentHistoryBrowse;
import com.zi.dian.ui.fragment.FragmentLocateByRadical;
import com.zi.dian.ui.fragment.FragmentLocateBySpelling;
import com.zi.dian.ui.fragment.FragmentLocateByStroke;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangliang on 6/12/16.
 */
public class FragmentActivityHome extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {
    private ViewPagerHome view_pager_home;

    private AdapterViewPagerHome adapterViewPagerHome;

    private FragmentLocateByRadical fragmentLookByRadical;
    private FragmentLocateBySpelling fragmentLookBySpelling;
    private FragmentLocateByStroke fragmentLookByStroke;
    private FragmentHistoryBrowse mFragmentHistoryBrowse;
    private FragmentCollection fragmentCollection;
    private long mExitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer_layout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        int taskId = getTaskId();
//        Toast.makeText(this, "FragmentActivityHome taskID is " + taskId, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @TargetApi(23)
    private void initView() {
        view_pager_home = (ViewPagerHome) findViewById(R.id.view_pager_home);
        fragmentLookByRadical = new FragmentLocateByRadical();
        fragmentLookBySpelling = new FragmentLocateBySpelling();
        fragmentLookByStroke = new FragmentLocateByStroke();
        mFragmentHistoryBrowse = new FragmentHistoryBrowse();
        fragmentCollection = new FragmentCollection();
        List<FragmentBase> fragmentBaseList = new ArrayList<>();
        fragmentBaseList.add(fragmentLookByRadical);
        fragmentBaseList.add(fragmentLookByStroke);
        fragmentBaseList.add(fragmentLookBySpelling);
        fragmentBaseList.add(mFragmentHistoryBrowse);
        fragmentBaseList.add(fragmentCollection);
        adapterViewPagerHome = new AdapterViewPagerHome(getSupportFragmentManager(), fragmentBaseList);
        view_pager_home.setAdapter(adapterViewPagerHome);

        view_pager_home.setPageSelected(new ViewPagerHome.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
                if (position == 0) {
                    toolbar.setTitle("部首查字");
                } else if (position == 1) {
                    toolbar.setTitle("笔画查字");
                } else if (position == 2) {
                    toolbar.setTitle("拼音查字");
                } else if (position == 3) {
                    toolbar.setTitle("浏览历史");
                } else if (position == 4) {
                    toolbar.setTitle("收藏");
                }
            }

        });
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_radical) {
            view_pager_home.setCurrentItem(0, false);
        } else if (id == R.id.nav_stroke) {
            view_pager_home.setCurrentItem(1, false);
        } else if (id == R.id.nav_spelling) {
            view_pager_home.setCurrentItem(2, false);
        } else if (id == R.id.nav_history) {
            view_pager_home.setCurrentItem(3, false);
        } else if (id == R.id.nav_collection) {
            view_pager_home.setCurrentItem(4, false);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_BACK) {
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

