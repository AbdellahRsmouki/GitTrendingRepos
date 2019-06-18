package rsmouki.example.com.githubrepos;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import rsmouki.example.com.Adapters.TabAdapter;

public class HomePageActivity extends AppCompatActivity {

    private static final String TAG = "HomePageActivty";
    
    ViewPager viewPager;
    TabLayout tabLayout;
    TabAdapter adapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        setupTablayout();


    }
    private void setupTablayout() {
        tabLayout = findViewById(R.id.tablyout);
        viewPager = findViewById(R.id.viewpager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        tabLayout.addTab(tabLayout.newTab().setText("TRENDING"));
        tabLayout.addTab(tabLayout.newTab().setText("SETTINGS"));

        adapter = new TabAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setCurrentItem(1);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }
        });
    }
}
