package com.example.inmobiliaria_2025;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private BottomNavigationView bottomNavigationView;
    private NavController navController;
    private AppBarConfiguration appBarConfiguration;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // DrawerLayout y NavigationView
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        // BottomNavigationView
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // NavController
        NavHostFragment navHostFragment = (NavHostFragment)
                getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_content_main);
        navController = navHostFragment.getNavController();

        // AppBarConfiguration
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_inicio,
                R.id.nav_perfil,
                R.id.nav_inmuebles,
                R.id.nav_contratos,
                R.id.nav_logout // "Salir"
        )
                .setOpenableLayout(drawerLayout)
                .build();

        // Toolbar con NavController
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        // BottomNavigationView con NavController
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        // NavigationView (Drawer) con NavController y cierre automático
        navigationView.setNavigationItemSelectedListener(item -> {
            boolean handled = NavigationUI.onNavDestinationSelected(item, navController);
            if (handled) {
                drawerLayout.closeDrawers(); // cierra el drawer
            }
            return handled;
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }
}
