package com.example.inmobiliaria_2025;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

public class LoginActivity extends AppCompatActivity {

    private EditText etMail, etClave;
    private Button btIngresar;
    private LoginActivityViewModel mv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etMail = findViewById(R.id.etMail);
        etClave = findViewById(R.id.etClave);
        btIngresar = findViewById(R.id.btIngresar);

        mv = new ViewModelProvider(this).get(LoginActivityViewModel.class);

        mv.getMensaje().observe(this, mensaje -> {
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        });

        // Hardcodear email y clave
        String email = "luisprofessor@gmail.com";
        String clave = "DEEKQW";

        // Mostrar en los EditText (opcional)
        etMail.setText(email);
        etClave.setText(clave);

        // Llamar al login automáticamente
        mv.login(email, clave, this);

        // Mantener el botón por si quieren probar manualmente también
        btIngresar.setOnClickListener(v -> {
            String inputEmail = etMail.getText().toString().trim();
            String inputClave = etClave.getText().toString().trim();

            if(inputEmail.isEmpty() || inputClave.isEmpty()){
                Toast.makeText(this, "Completá email y contraseña", Toast.LENGTH_SHORT).show();
                return;
            }

            mv.login(inputEmail, inputClave, this);
        });
        // Solicitar permiso en tiempo de ejecución
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 101);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        mv.iniciarDeteccionDeSacudida(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        // Detiene la detección para no consumir batería cuando no está visible
        mv.detenerDeteccionDeSacudida();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
