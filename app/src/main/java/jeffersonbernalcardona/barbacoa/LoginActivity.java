package jeffersonbernalcardona.barbacoa;

import android.content.Intent;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Initialize();


        /*Bundle extras=getIntent().getExtras();           //obtiene el intent que lo llevo alli, y obtiene los extras

        String user=extras.getString("Usuario");
        String pass=extras.getString("Contrasena");

        Toast.makeText(this, "User: "+user+ " Contraseña: "+pass,Toast.LENGTH_SHORT).show();*/


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
            //Toast.makeText(this,"User: "+rUser+"Contraseña: "+rContrasena,Toast.LENGTH_SHORT);

        }
    }
    private void Initialize(){
        bRegistro=(Button)findViewById(R.id.Registrar);
        bEntrar=(Button)findViewById(R.id.Entrar);
        eContrasena=(EditText)findViewById(R.id.Contraseña);
        eUsuario=(EditText)findViewById(R.id.Usuario);
        bRegistro.setOnClickListener(this);             //Ejecuta listener en esta actividad
        bEntrar.setOnClickListener(this);

    }
    public boolean match(){
        if(lUser.equals(rUser) && lContrasena.equals(rContrasena)){
            return true;
        }else{
            return false;
        }

    }
    public void GetInfo(){
        lUser=eUsuario.getText().toString();
        lContrasena=eContrasena.getText().toString();
    }

}
