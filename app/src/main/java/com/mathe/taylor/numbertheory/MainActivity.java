package com.mathe.taylor.numbertheory;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

import static java.lang.Math.ceil;
import static java.lang.Math.sqrt;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, GoldBachCon.OnFragmentInteractionListener,
        PrimeCheck.OnFragmentInteractionListener, LucasLehmer.OnFragmentInteractionListener,EuclidAlgor.OnFragmentInteractionListener {
    NavigationView navigationView = null;
    Toolbar toolbar = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Set the fragment initially

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick (View view){

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
            }

            );



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        if (savedInstanceState == null) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, new PrimeCheck(),"fragment_main")
                    .commit();

        }


        }


        @Override
        public String primecheck(long num) {

            double rn;
            String result = "";
        rn = ceil(sqrt(num));

        if (num % 2 != 0 && num != 1 && num != 2 && num != 3) {
            for (int i = 3; i <= rn; i += 2) {

                if (num % i == 0) {
                    result = "Composite!";
                    break;
                } else {
                    result = "Prime!";
                }
            }
        } else if (num % 2 == 0 && num != 2) {
            result = "Composite!";
        } else if (num == 1 || num == 2 || num == 3) {
            result = "Prime!";
        }
            return result;
        }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        android.support.v4.app.FragmentTransaction transact = getSupportFragmentManager().beginTransaction();


        int id = item.getItemId();

        if (id == R.id.nav_primechk) {

            transact.replace(R.id.fragment_container, new PrimeCheck(), "fragment_prime_check");
            transact.commit();

        } else if (id == R.id.nav_golbcon) {

            transact.replace(R.id.fragment_container, new GoldBachCon(), "fragment_gold_bach_con");
            transact.commit();

        } else if (id == R.id.nav_luclehm) {

            transact.replace(R.id.fragment_container, new LucasLehmer(), "fragment_lucas_lehmer");
            transact.commit();


        } else if (id == R.id.nav_euclidalgor) {

            transact.replace(R.id.fragment_container, new EuclidAlgor(), "fragment_euclid_algor");
            transact.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    public void onCheckButtonClick(View view) {
        Toast.makeText(this, "You entered!" , Toast.LENGTH_SHORT).show();
    }
}
