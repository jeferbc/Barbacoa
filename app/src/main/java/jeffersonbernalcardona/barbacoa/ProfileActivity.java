package jeffersonbernalcardona.barbacoa;

import android.content.Intent;
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
                intent.putExtra("Usuario",lUser);
                intent.putExtra("Correo",lCorreo);
                startActivity(intent);
                break;
            case R.id.mMenu:
                Intent intentMenu=new Intent(this,MenuActivity.class);
                intentMenu.putExtra("Usuario",lUser);
                intentMenu.putExtra("Correo",lCorreo);
                startActivity(intentMenu);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void Initialize(){
        Bundle extras=getIntent().getExtras();           //obtiene el intent que lo llevo alli, y obtiene los extras
        lUser=extras.getString("Usuario");
        lCorreo=extras.getString("Correo");
        pCorreo=(TextView)findViewById(R.id.pCorreo);
        pUser=(TextView)findViewById(R.id.pUser);
    }
    public void SetText(){
        pUser.setText("Usuario: "+lUser);
        pCorreo.setText("Correo: "+lCorreo);
    }
}
