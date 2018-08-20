package com.example.maqian.navtabbartest;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.graphics.Color;
import android.location.LocationManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import devlight.io.library.ntb.NavigationTabBar;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private DrawerLayout layout;
    LocalActivityManager manager;
    private static ViewPager viewPager;
    private static MyNavgationBar navigationTabBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = new LocalActivityManager(this, true);
        manager.dispatchCreate( savedInstanceState );
        manager.dispatchResume();
        initUI();
        layout = findViewById(R.id.drawer_layout);
        button = findViewById(R.id.openBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.openDrawer(Gravity.RIGHT);
                initUI();

            }
        });
    }

    private void initUI() {

        List<View> lists = new ArrayList<>();
        Intent intent = new Intent(this, Pager1Activity.class);
        lists.add(getView("A", intent));
        lists.add(getView("B", intent));
        Intent intent2 = new Intent(this, InnerActivity.class);
        lists.add(getView("C", intent2));
        viewPager = (ViewPager) findViewById(R.id.vp_horizontal_ntb);
        viewPager.setAdapter(new MyPagerAdapter(lists));
        viewPager.setCurrentItem(0);
//        viewPager.setAdapter(new PagerAdapter() {
//            @Override
//            public int getCount() {
//                return 5;
//            }
//
//            @Override
//            public boolean isViewFromObject(final View view, final Object object) {
//                return view.equals(object);
//            }
//
//            @Override
//            public void destroyItem(final View container, final int position, final Object object) {
//                ((ViewPager) container).removeView((View) object);
//            }
//
//            @Override
//            public Object instantiateItem(final ViewGroup container, final int position) {
//                final View view = LayoutInflater.from(
//                        getBaseContext()).inflate(R.layout.item_vp, null, false);
//
//                final TextView txtPage = (TextView) view.findViewById(R.id.txt_vp_item_page);
//                txtPage.setText(String.format("Page #%d", position));
//
//                container.addView(view);
//                return view;
//            }
//        });

        final String[] colors = getResources().getStringArray(R.array.default_preview);

        navigationTabBar = (MyNavgationBar) findViewById(R.id.ntb_horizontal);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_launcher_background),
                        Color.parseColor(colors[0]))
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_launcher_background))
                        .title("Heart")
                        .badgeTitle("NTB")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_launcher_background),
                        Color.parseColor(colors[1]))
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_launcher_background))
                        .title("Cup")
                        .badgeTitle("with")
                        .build()
        );
//        models.add(
//                new NavigationTabBar.Model.Builder(
//                        getResources().getDrawable(R.drawable.ic_launcher_background),
//                        Color.parseColor(colors[2]))
//                        .selectedIcon(getResources().getDrawable(R.drawable.ic_launcher_background))
//                        .title("Diploma")
//                        .badgeTitle("state")
//                        .build()
//        );
//        models.add(
//                new NavigationTabBar.Model.Builder(
//                        getResources().getDrawable(R.drawable.ic_launcher_background),
//                        Color.parseColor(colors[3]))
////                        .selectedIcon(getResources().getDrawable(R.drawable.ic_eighth))
//                        .title("Flag")
//                        .badgeTitle("icon")
//                        .build()
//        );
//        models.add(
//                new NavigationTabBar.Model.Builder(
//                        getResources().getDrawable(R.drawable.ic_launcher_background),
//                        Color.parseColor(colors[4]))
//                        .selectedIcon(getResources().getDrawable(R.drawable.ic_launcher_background))
//                        .title("Medal")
//                        .badgeTitle("777")
//                        .build()
//        );

        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(viewPager, 0);
        navigationTabBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                navigationTabBar.getModels().get(position).hideBadge();
            }

            @Override
            public void onPageScrollStateChanged(final int state) {

            }
        });

        navigationTabBar.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < navigationTabBar.getModels().size(); i++) {
                    final NavigationTabBar.Model model = navigationTabBar.getModels().get(i);
                    navigationTabBar.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            model.showBadge();
                        }
                    }, i * 100);
                }
            }
        }, 500);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        if (viewPager != null) {
//            switch (viewPager.getCurrentItem()) {
//                case 0:
//                    Activity innerActivity = manager.getActivity("C");
//                    //((InnerActivity)innerActivity).invi
//                    break;
//                default:
//                    break;
//
//            }
//        }
    }

    private View getView(String pageId, Intent intent) {
        return manager.startActivity(pageId, intent).getDecorView();
    }

    public static void putActivityInPagerView(int id) {
        navigationTabBar.setViewPager(viewPager, id);
    }

}
