package com.example.inmobiliaria_2025;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
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

        loginViewModel = new ViewModelProvider(this).get(LoginActivityViewModel.class);

        loginViewModel.getMensaje().observe(this, mensaje -> {
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        });

        // Hardcodear email y clave
        String email = "luisprofessor@gmail.com";
        String clave = "DEEKQW";

        // Mostrar en los EditText (opcional)
        etMail.setText(email);
        etClave.setText(clave);

        // Llamar al login automáticamente
        loginViewModel.login(email, clave, this);

        // Mantener el botón por si quieren probar manualmente también
        btIngresar.setOnClickListener(v -> {
            String inputEmail = etMail.getText().toString().trim();
            String inputClave = etClave.getText().toString().trim();

            if(inputEmail.isEmpty() || inputClave.isEmpty()){
                Toast.makeText(this, "Completá email y contraseña", Toast.LENGTH_SHORT).show();
                return;
            }

            loginViewModel.login(inputEmail, inputClave, this);
        });
    }
}
