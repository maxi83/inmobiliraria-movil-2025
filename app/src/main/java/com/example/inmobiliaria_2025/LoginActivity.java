package com.example.inmobiliaria_2025;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class LoginActivity extends AppCompatActivity {

    private EditText etMail, etClave;
    private Button btIngresar;
    private LoginActivityViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etMail = findViewById(R.id.etMail);
        etClave = findViewById(R.id.etClave);
        btIngresar = findViewById(R.id.btIngresar);

        // Inicializar ViewModel
        loginViewModel = new ViewModelProvider(this).get(LoginActivityViewModel.class);

        // Observar mensajes del login
        loginViewModel.getMensaje().observe(this, mensaje -> {
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        });

        btIngresar.setOnClickListener(v -> {
            String email = etMail.getText().toString().trim();
            String clave = etClave.getText().toString().trim();

            if (email.isEmpty() || clave.isEmpty()) {
                Toast.makeText(this, "Completá email y contraseña", Toast.LENGTH_SHORT).show();
                return;
            }

            // Llamar al ViewModel para hacer login con Retrofit
            loginViewModel.login(email, clave);
        });
    }
}
