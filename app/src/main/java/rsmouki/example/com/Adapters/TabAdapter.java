package rsmouki.example.com.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import rsmouki.example.com.Fragments.SettingsActivity;
import rsmouki.example.com.Fragments.TrendingReposActivity;

public class TabAdapter extends FragmentPagerAdapter {

    int numoftabs;

    public TabAdapter(FragmentManager fm, int mnumoftabs) {
        super(fm);
        this.numoftabs = mnumoftabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                TrendingReposActivity trendingReposActivity = new TrendingReposActivity();
                return trendingReposActivity;

            case 1:
                SettingsActivity settingsActivity = new SettingsActivity();
                return settingsActivity;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numoftabs;
    }
}
