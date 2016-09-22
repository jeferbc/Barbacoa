package jeffersonbernalcardona.barbacoa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {
    private static Button bCancelar,bEntrar;
    private static EditText eUsuario,eContrasena,eCorreo, eContrasena1;
    private static String Usuario,Contrasena,Correo,Contrasena1;
    private static boolean Share=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Initialize();


        bEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetInfo();
                if(!Validation()){
                    Share=true;                                 //Bandera encargada del SetResult
                    ShareInfo(Share);
                };



            }
        });
        bCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Share=false;
                ShareInfo(Share);

            }
        });
    }

    public boolean Validation(){
        if(TextUtils.isEmpty(Usuario)){
            eUsuario.setError("Digite Usuario");
            return true;
        }else if(TextUtils.isEmpty(Contrasena)){
            eContrasena.setError("Digite Contraseña");
            return true;
        }else if(TextUtils.isEmpty(Contrasena1)){
            eContrasena1.setError("Repita Contraseña");
            return true;
        }else if(!Contrasena.equals(Contrasena1)) {
            eContrasena1.setError("Contraseñas Diferentes");
            return true;
        }else if(TextUtils.isEmpty(Correo)){
            eCorreo.setError("Digite Correo");
            return true;
        } else{
            return false;

        }


    }

    public void GetInfo(){
        Usuario=eUsuario.getText().toString();
        Contrasena=eContrasena.getText().toString();
        Contrasena1=eContrasena1.getText().toString();
        Correo=eCorreo.getText().toString();
    }
    public void Initialize(){
        bCancelar=(Button)findViewById(R.id.Cancelar);
        bEntrar=(Button)findViewById(R.id.Registro);
        eUsuario=(EditText)findViewById(R.id.Usuario);
        eContrasena=(EditText)findViewById(R.id.Contraseña);
        eContrasena1=(EditText)findViewById(R.id.Contraseña1);
        eCorreo=(EditText)findViewById(R.id.Correo);
    }
    public void ShareInfo(boolean share){
        //Intent intent=new Intent(getApplicationContext(),LoginActivity.class);  //se usa getapp para que el programa detecte donde esta el boton
        Intent intent=new Intent();
        if(share){

            intent.putExtra("Usuario",eUsuario.getText().toString());
            intent.putExtra("Contrasena",eContrasena.getText().toString());
            intent.putExtra("Correo",eCorreo.getText().toString());
            //startActivity(intent);
            setResult(RESULT_OK,intent);
            finish();
        }else {
            setResult(RESULT_CANCELED, intent);
            finish();
        }


    }
}
