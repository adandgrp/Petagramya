package com.example.rsc.myapplication.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.rsc.myapplication.pojo.Mascota;
import com.example.rsc.myapplication.fragments.MascotaRVFragment;
import com.example.rsc.myapplication.adapters.PageAdapter;
import com.example.rsc.myapplication.fragments.PerfilMascotaFragment;
import com.example.rsc.myapplication.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        if (validaCuenta()){
            setUpViewPager();
        }

        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Proximamente fotos", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    private boolean validaCuenta() {
        SharedPreferences miCuentaPref = getSharedPreferences("MiCuenta", Context.MODE_PRIVATE);
        String cuentaUsuario = miCuentaPref.getString("cuenta",null);
        if(cuentaUsuario == null){
            Log.v("Cuenta","null");
            Intent intent = new Intent(MainActivity.this,ConfigCuentaActivity.class);
            startActivity(intent);
            Toast.makeText(MainActivity.this, "Primero es necesario guardar tu cuenta", Toast.LENGTH_LONG).show();
            finish();
            return false;
        }else{
            Log.v("Cuenta",cuentaUsuario);
            return true;
        }


    }

    private ArrayList<Fragment> agregarFragments(){
        ArrayList<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(new MascotaRVFragment());
        fragments.add(new PerfilMascotaFragment());
        return fragments;
    }

    private void setUpViewPager() {
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText(getResources().getString(R.string.mascotas));
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_mascotas);
        tabLayout.getTabAt(1).setText(getResources().getString(R.string.mimascotatitulo));
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_mimascota);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.mcontacto) {
            Intent intent = new Intent(MainActivity.this,ContactoActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.macercade) {
            Intent intent = new Intent(MainActivity.this,AcercaDeActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.favoritos) {
            Intent intent = new Intent(MainActivity.this,FavoritosActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.mconfigcuenta){
            Intent intent = new Intent(MainActivity.this,ConfigCuentaActivity.class);
            startActivity(intent);
            finish();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
