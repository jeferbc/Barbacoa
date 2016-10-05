package jeffersonbernalcardona.barbacoa;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    String lUser,lCorreo;
    TextView pUser,pCorreo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Initialize();
        SetText();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.Inicio:
                Intent intent=new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.mMenu:
                Intent intentMenu=new Intent(this,MenuActivity.class);
                startActivity(intentMenu);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void Initialize(){
        lUser=getDefaults("Usuario",this);
        lCorreo=getDefaults("Correo",this);
        pCorreo=(TextView)findViewById(R.id.pCorreo);
        pUser=(TextView)findViewById(R.id.pUser);
    }
    public void SetText(){
        pUser.setText("Usuario: "+lUser);
        pCorreo.setText("Correo: "+lCorreo);
    }
    public static String getDefaults(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }
}
