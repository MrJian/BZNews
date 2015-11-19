package com.bz.activity;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Toast;

import com.bz.event.ExitEvent;
import com.bz.fragment.BaseFragment;
import com.bz.fragment.WelcomeFragment;

import java.util.LinkedList;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

public class MainActivity extends AppCompatActivity {

    //Fragment 标记
    private LinkedList<String> fragmentTags = new LinkedList<>();
    private FragmentManager fm;

    public static final int LAST_CLICK_GAP = 600;

    public long lastClickTime = 0;

    private long mExitTime = 0;

    public static final int EXIT_TIME_GAP = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        fm = getSupportFragmentManager();

        WelcomeFragment fragment = WelcomeFragment.getInstance();
        String tag = fragment.getmTag();
        fragmentTags.add(tag);
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.main_contaner, fragment, tag);
        ft.addToBackStack(tag);
        ft.commit();

    }

    /**
     * 获取当前的Fragment
     */
    private BaseFragment getCurrentFragment() {

        if (fragmentTags == null) return null;
        return (BaseFragment) fm.findFragmentByTag(fragmentTags.peekLast());
    }

    /**
     * 启动Fragment
     */
    private void startFragment(BaseFragment fragment) {

        if (fragment == null) {
            throw new IllegalArgumentException("fragment is null");
        }
        //如果两次点击间隔时间少于600毫秒，则不算
        if (lastClickTime + LAST_CLICK_GAP < System.currentTimeMillis()) {
            String tag = fragment.getmTag();
            FragmentTransaction ft = fm.beginTransaction();
            //设置动画
//            ft.setCustomAnimations();
            ft.add(R.id.main_contaner, fragment, tag);
            BaseFragment currentFragment = getCurrentFragment();
            if (currentFragment != null) {
                // 5 隐藏当前或者finish的Fragment
                if (currentFragment.finish()) {
                    fragmentTags.pollLast();
                    fm.popBackStack();
                } else {
                    ft.hide(currentFragment);
                }
            }
            //把tag 添加到 fragmentTags
            fragmentTags.add(tag);
            //添加到返回栈
            ft.addToBackStack(tag);
            ft.commit();
            lastClickTime = System.currentTimeMillis();
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (KeyEvent.KEYCODE_BACK == keyCode) {

            if (!backCurrentFragment()) {
                goBack();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public boolean backCurrentFragment() {
        BaseFragment currFragment = getCurrentFragment();
        if (currFragment != null) {
            //TODO 如果为true 永远退不出， 有这样的需求？
            return currFragment.onBack();
        }
        return false;
    }

    // 处理默认返回的
    private void goBack() {

        int count = fm.getBackStackEntryCount();
        if (count == 1) {
            // 只存在一个Fragment时候，进行二次提醒
            if (SystemClock.uptimeMillis() - mExitTime > EXIT_TIME_GAP) {
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                mExitTime = SystemClock.uptimeMillis();
            } else {
                // 假如多个Activity，清除多个Activity
                MainActivity.this.finish();
            }
        } else {
            fm.popBackStack();// 把Fragment移除出去
            if (fragmentTags.size() > 0) {
                fragmentTags.pollLast();
            }
        }

    }

    /**
     * 处理Fragment间的跳转
     */
    @Subscribe
    public void onEventMainThread(BaseFragment fragment) {
        startFragment(fragment);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEventExit(ExitEvent event) {

        fragmentTags.pollLast();
        fm.popBackStack();
    }
}
