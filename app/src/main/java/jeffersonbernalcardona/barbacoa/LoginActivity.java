package jeffersonbernalcardona.barbacoa;

import android.content.Intent;
import android.content.SharedPreferences;
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
                    finish();
                    startActivity(intentMain);
                    editor.putInt("entro",1);
                    editor.putString("Usuario",rUser);
                    editor.putString("Contraseña",rContrasena);
                    editor.putString("Correo",rCorreo);
                    editor.commit();
                    Log.d("entro",String.valueOf(prefs.getInt("entro",-1)));
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
            //Toast.makeText(this,"User: "+rUser+"Contraseña: "+rContrasena,Toast.LENGTH_SHORT);

        }
    }
    private void Initialize(){
        prefs = getPreferences(MODE_PRIVATE);
        editor = prefs.edit();
        if(prefs.getInt("entro",-1)==1){
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

}
