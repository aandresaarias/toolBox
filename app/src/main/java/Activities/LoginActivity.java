package Activities;

import android.content.Intent;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.toolbox.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity
{

    Button loginBoton, loginRegistro;
    EditText etNombreLogin, etPassLogin;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBoton = (Button) findViewById(R.id.loginBoton);
        loginRegistro = (Button) findViewById(R.id.loginRegistro);
        etNombreLogin = (EditText) findViewById(R.id.etNombreLogin);
        etPassLogin = (EditText) findViewById(R.id.etPassLogin);
        auth = FirebaseAuth.getInstance();

        loginBoton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String usuario = etNombreLogin.getText().toString();
                String contrasena = etPassLogin.getText().toString();

                if(TextUtils.isEmpty(usuario))
                {
                    Toast.makeText(LoginActivity.this, "Favor ingresar Email",Toast.LENGTH_SHORT).show();
                }

                if(TextUtils.isEmpty(contrasena))
                {
                    Toast.makeText(LoginActivity.this, "Contraseña no debe estar en blanco",Toast.LENGTH_SHORT).show();
                }


                auth.signInWithEmailAndPassword(usuario,contrasena).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {

                        if(!task.isSuccessful())
                        {
                            Toast.makeText(LoginActivity.this, "Correo o Contraseña incorrectaaaaa", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Intent intent = new Intent(LoginActivity.this , MainActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    }
                });
            }
        });

        /*loginRegistro.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(LoginActivity.this, RegistrarActivity.class);
                startActivity(intent);
            }
        });*/
    }
}