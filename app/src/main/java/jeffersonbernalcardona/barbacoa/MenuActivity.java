package jeffersonbernalcardona.barbacoa;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


/**
 * Created by Aldebarantech on 19/09/2016.
 */
public class MenuActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private String lUser,lCorreo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Bundle extras = getIntent().getExtras();           //obtiene el intent que lo llevo alli, y obtiene los extras
        lUser = extras.getString("Usuario");
        lCorreo = extras.getString("Correo");
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(adapter);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar.TabListener tabListener= new ActionBar.TabListener()
        {

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
        ActionBar.Tab tab = actionBar.newTab().setText("Hamburguesas").setTabListener(tabListener);
        actionBar.addTab (tab);
        tab = actionBar.newTab().setText("Others").setTabListener(tabListener);
        actionBar.addTab (tab);
        tab = actionBar.newTab().setText("Cervezas").setTabListener(tabListener);
        actionBar.addTab (tab);

        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                getSupportActionBar().setSelectedNavigationItem(position);

            }
        });


    }
    public class PagerAdapter extends FragmentPagerAdapter {
        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0: return new HamburguerFragment();
                case 1: return new OthersFragment();
                case 2: return new CervezasFragment();
                default: return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menumenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.mMiperfil:
                Intent intent=new Intent(this,ProfileActivity.class);
                intent.putExtra("Usuario",lUser);
                intent.putExtra("Correo",lCorreo);
                startActivity(intent);
                break;
            case R.id.Inicio:
                Intent intentMenu=new Intent(this,MainActivity.class);
                intentMenu.putExtra("Usuario",lUser);
                intentMenu.putExtra("Correo",lCorreo);
                startActivity(intentMenu);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
