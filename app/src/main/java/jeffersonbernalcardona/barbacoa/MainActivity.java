package jeffersonbernalcardona.barbacoa;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String lUser,lCorreo;
    private String[] opciones = new String[]{
            "Mi Perfil",
            "Menu"
    };
    private Promocion[] datos = new Promocion[]{
            new Promocion(R.drawable.burguer,20000,"Hamburguesa","Con Papas Y Gaseosa"),
            new Promocion(R.drawable.cerveza,5000,"Cerveza","Corona, Pilsen, Aguila"),
            new Promocion(R.drawable.costilla,20000,"Costilla","Con Papas Y Gaseosa"),
            new Promocion(R.drawable.spaghetti,15000,"Spaguetti","Con Papas Y Gaseosa"),
            new Promocion(R.drawable.costilla,20000,"Costilla","Con Papas Y Gaseosa"),
            new Promocion(R.drawable.spaghetti,15000,"Spaguetti","Con Papas Y Gaseosa")
    };
    private DrawerLayout mDrawerLayout;
    ListView mDrawerList;
    ListView pListView;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        getstring();
        pListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),"presiono "+i,Toast.LENGTH_SHORT).show();
                Log.d("Producto Presionado ",((Promocion)adapterView.getItemAtPosition(i)).getNombre());

                switch (i){
                    case(0):break;
                    case(1):break;
                    case(2):break;
                    case(3):break;
                    case(4):break;
                    case(5):break;
                }
            }
        });
        mDrawerList.setAdapter(new ArrayAdapter<String>(getSupportActionBar().getThemedContext(),android.R.layout.simple_list_item_1, opciones));
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Intent intent,intentMenu;
                Log.d ("entro","si");
                Fragment fragment=null;
                    switch (i){
                        case(0):
                            /*
                            Toast.makeText(MainActivity.this,"presiono"+i,Toast.LENGTH_SHORT).show();
                            intent=new Intent(getApplicationContext(),ProfileActivity.class);
                            intent.putExtra("Usuario",lUser);
                            intent.putExtra("Correo",lCorreo);
                            startActivity(intent);
                            finish();*/
                            fragment = new HamburguerFragment();break;
                        case(1):
                            /*Toast.makeText(MainActivity.this,"presiono"+i,Toast.LENGTH_SHORT).show();
                            intentMenu=new Intent(getApplicationContext(),MenuActivity.class);
                            intentMenu.putExtra("Usuario",lUser);
                            intentMenu.putExtra("Correo",lCorreo);
                            startActivity(intentMenu);
                            finish();*/
                            fragment = new OthersFragment();break;
                    }
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.contenedorFrame, fragment).commit();
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
                mDrawerLayout.openDrawer(Gravity.LEFT);
                return true;


        }
        return super.onOptionsItemSelected(item);
    }

    public void getstring (){
        try{
            Bundle extras = getIntent().getExtras();           //obtiene el intent que lo llevo alli, y obtiene los extras
            lUser = extras.getString("Usuario");
            lCorreo = extras.getString("Correo");
        }catch (Exception e){
            SharedPreferences prefs = getPreferences(MODE_PRIVATE);
            lUser = prefs.getString("Usuario","");
            lCorreo = prefs.getString("Correo" , "");
        }
    }

    public void initialize(){
        Adapter adaptador = new Adapter(this,datos);
        pListView = (ListView) findViewById(R.id.List);
        pListView.setAdapter(adaptador);
        final ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        mDrawerLayout = (DrawerLayout) findViewById(R.id.contenedorPrincipal);
        mDrawerList = (ListView) findViewById(R.id.MenuIzq);


    }
    class Adapter extends ArrayAdapter<Promocion>{

        public Adapter(Context context, Promocion[] datos) {
            super(context, R.layout.promocion_item,datos);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View item = inflater.inflate(R.layout.promocion_item,null);

            ImageView imagen = (ImageView) item.findViewById(R.id.imagenhamburguesa);
            imagen.setImageResource(datos[position].getIdImagen());

            TextView nombre = (TextView) item.findViewById(R.id.tNombre);
            nombre.setText(datos[position].getNombre());

            TextView descripcion = (TextView) item.findViewById(R.id.tDescription);
            descripcion.setText(datos[position].getDescripcion());

            TextView precio = (TextView) item.findViewById(R.id.tPrecio);
            precio.setText(String.valueOf(datos[position].getPrecio()));

            return (item);
        }
    }
}






