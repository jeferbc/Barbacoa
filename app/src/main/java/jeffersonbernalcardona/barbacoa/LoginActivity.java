package jeffersonbernalcardona.barbacoa;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    Button bRegistro,bEntrar;
    EditText eContrasena,eUsuario;
    private boolean Close;
    private  String rUser,rContrasena=null,lUser,lContrasena=null,rCorreo;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Initialize();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.Registrar:
                    Intent intent=new Intent(this,RegisterActivity.class);
                    startActivityForResult(intent,1234);//LLama actividad y espera resultados
                    //startActivity(intent);
                break;
            case R.id.Entrar:
                GetInfo();
                if(match()){
                    Intent intentMain=new Intent(this,MainActivity.class);
                    intentMain.putExtra("Usuario",rUser);
                    intentMain.putExtra("Contrasena",rContrasena);
                    intentMain.putExtra("Correo",rCorreo);
                    setDefaults("entro",1,this);
                    setDefaults("Usuario",rUser,this);
                    setDefaults("Contraseña",rContrasena,this);
                    setDefaults("Correo",rCorreo,this);
                    finish();
                    startActivity(intentMain);
                }else {
                    Toast.makeText(this, "Usuario o contraseña no es correcta", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    /**
     * Dispatch incoming result to the correct fragment.
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) { //Recibe los resultados
        if(requestCode==1234 && resultCode==RESULT_OK){
            rUser=data.getExtras().getString("Usuario");
            rContrasena=data.getExtras().getString("Contrasena");
            rCorreo=data.getExtras().getString(("Correo"));
            Toast.makeText(this,"User: "+rUser+"Contraseña: "+rCorreo,Toast.LENGTH_SHORT).show();

        }
    }
    private void Initialize(){
        try{
            Bundle extras = getIntent().getExtras();           //obtiene el intent que lo llevo alli, y obtiene los extras
            Close = extras.getBoolean("Close");
            setDefaults("entro",0,this);
            rUser = getDefaultsString("Usuario",this);
            rContrasena = getDefaultsString("Contraseña" , this);
            rCorreo = getDefaultsString("Correo" , this);
        }catch (Exception e){

        }
        if(getDefaults("entro",this)==1){
            finish();
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
        }
        bRegistro=(Button)findViewById(R.id.Registrar);
        bEntrar=(Button)findViewById(R.id.Entrar);
        eContrasena=(EditText)findViewById(R.id.Contraseña);
        eUsuario=(EditText)findViewById(R.id.Usuario);
        bRegistro.setOnClickListener(this);             //Ejecuta listener en esta actividad
        bEntrar.setOnClickListener(this);

    }
    public boolean match(){
        return lUser.equals(rUser) && lContrasena.equals(rContrasena);

    }
    public void GetInfo(){

            lUser=eUsuario.getText().toString();
            lContrasena=eContrasena.getText().toString();

    }
    public static void setDefaults(String key, String value, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.commit();
    }
    public static void setDefaults(String key, int value, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key, value);
        editor.commit();
    }
    public static int getDefaults(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getInt(key,-1);
    }
    public static String getDefaultsString(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key,"");
    }

}
