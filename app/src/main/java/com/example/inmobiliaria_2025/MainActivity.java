package com.example.inmobiliaria_2025;

import android.os.Bundle;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.navigation.NavigationView;
import androidx.appcompat.widget.Toolbar;

import com.example.inmobiliaria_2025.ui.inicio.InicioFragment;
import com.example.inmobiliaria_2025.ui.perfil.PerfilFragment;
import com.example.inmobiliaria_2025.ui.inmueble.InmueblesFragment;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_inicio) {
                loadFragment(new InicioFragment(), "Inicio");
            } else if (id == R.id.nav_perfil) {
                loadFragment(new PerfilFragment(), "Perfil");
            } else if (id == R.id.nav_inmuebles) {
                loadFragment(new InmueblesFragment(), "Inmuebles");
            }

            drawerLayout.closeDrawers();
            return true;
        });

        // Cargar fragment inicial
        if (savedInstanceState == null) {
            loadFragment(new InicioFragment(), "Inicio");
            navigationView.setCheckedItem(R.id.nav_inicio);
        }
    }

    private void loadFragment(androidx.fragment.app.Fragment fragment, String title) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment);
        ft.commit();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }
}
