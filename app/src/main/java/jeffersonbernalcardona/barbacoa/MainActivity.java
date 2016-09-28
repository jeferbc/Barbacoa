package jeffersonbernalcardona.barbacoa;


import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String lUser,lCorreo;
    private String[] opciones = new String[]{
            "Mi Perfil",
            "Menu"
    };
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getstring();
        initialize();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.contenedorPrincipal);
        mDrawerList = (ListView) findViewById(R.id.MenuIzq);
        mDrawerList.setAdapter(new ArrayAdapter<String>(getSupportActionBar().getThemedContext(),android.R.layout.simple_list_item_1, opciones));
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent,intentMenu;
                Log.d ("entro","si");
                    switch (i){
                        case(0):
                            Toast.makeText(MainActivity.this,"presiono"+i,Toast.LENGTH_SHORT).show();
                            intent=new Intent(getApplicationContext(),ProfileActivity.class);
                            intent.putExtra("Usuario",lUser);
                            intent.putExtra("Correo",lCorreo);
                            startActivity(intent);
                            finish();
                            break;
                        case(1):
                            Toast.makeText(MainActivity.this,"presiono"+i,Toast.LENGTH_SHORT).show();
                            intentMenu=new Intent(getApplicationContext(),MenuActivity.class);
                            intentMenu.putExtra("Usuario",lUser);
                            intentMenu.putExtra("Correo",lCorreo);
                            startActivity(intentMenu);
                            finish();
                            break;
                    }
                mDrawerList.setItemChecked(i,true);
                mDrawerLayout.closeDrawer(mDrawerList);
            }
        });

        drawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.Abierto,R.string.Cerrado);
        mDrawerLayout.setDrawerListener(drawerToggle);

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(Gravity.START);
                return true;


        }
        return super.onOptionsItemSelected(item);
    }

    public void getstring (){
        Bundle extras = getIntent().getExtras();           //obtiene el intent que lo llevo alli, y obtiene los extras
        lUser = extras.getString("Usuario");
        lCorreo = extras.getString("Correo");
    }

    public void initialize(){
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


    }




}

