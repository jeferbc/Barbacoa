package jeffersonbernalcardona.barbacoa;

import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class PromocionActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private int selection = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promocion);
        Initialize();
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.Pager);
        mViewPager.setAdapter(adapter);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar.TabListener tabListener = new ActionBar.TabListener(){

            /**
             * Called when a tab enters the selected state.
             *
             * @param tab The tab that was selected
             * @param ft  A {@link FragmentTransaction} for queuing fragment operations to execute
             *            during a tab switch. The previous tab's unselect and this tab's select will be
             *            executed in a single transaction. This FragmentTransaction does not support
             */
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                if (selection != 99) {
                    mViewPager.setCurrentItem(selection);
                    selection = 99;
                }else
                    mViewPager.setCurrentItem(tab.getPosition());

            }

            /**
             * Called when a tab exits the selected state.
             *
             * @param tab The tab that was unselected
             * @param ft  A {@link FragmentTransaction} for queuing fragment operations to execute
             *            during a tab switch. This tab's unselect and the newly selected tab's select
             *            will be executed in a single transaction. This FragmentTransaction does not
             */
            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }

            /**
             * Called when a tab that is already selected is chosen again by the user.
             * Some applications may use this action to return to the top level of a category.
             *
             * @param tab The tab that was reselected.
             * @param ft  A {@link FragmentTransaction} for queuing fragment operations to execute
             *            once this method returns. This FragmentTransaction does not support
             */
            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }
        };
        ActionBar.Tab tab = actionBar.newTab().setText("Promocion 1").setTabListener(tabListener);
        actionBar.addTab (tab);
        tab = actionBar.newTab().setText("Promocion 2").setTabListener(tabListener);
        actionBar.addTab (tab);
        tab = actionBar.newTab().setText("Promocion 3").setTabListener(tabListener);
        actionBar.addTab (tab);
        tab = actionBar.newTab().setText("Promocion 4").setTabListener(tabListener);
        actionBar.addTab (tab);
        tab = actionBar.newTab().setText("Promocion 5").setTabListener(tabListener);
        actionBar.addTab (tab);

        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                getSupportActionBar().setSelectedNavigationItem(position);


            }
        });
    }


    private void Initialize() {
        try{
            Bundle extras = getIntent().getExtras();           //obtiene el intent que lo llevo alli, y obtiene los extras
            selection = extras.getInt("Eleccion");

        }catch (Exception e){
        }
    }


    public class PagerAdapter extends FragmentPagerAdapter {
        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0: return new PromocionFragment1();
                case 1: return new Promocion2Fragment();
                case 2: return new Promocion3Fragment();
                case 3: return new Promocion4Fragment();
                case 4: return new Promocion5Fragment();
                default: return null;
            }
        }

        @Override
        public int getCount() {
            return 5;
        }
    }
}
