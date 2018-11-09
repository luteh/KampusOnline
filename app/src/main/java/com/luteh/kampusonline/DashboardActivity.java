package com.luteh.kampusonline;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.luteh.kampusonline.common.base.BaseActivity;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnItemSelected;

public class DashboardActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindString(R.string.title_dashboard_fragment)
    String dashboardTitle;
    @BindString(R.string.title_hasil_studi_fragment)
    String hasilStudiTitle;

    private FirebaseAuth mAuth;
    private Fragment mCurrentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAuth = FirebaseAuth.getInstance();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Create and set Android Fragment as default.
        Fragment dashboardFragment = new DashboardFragment();
        this.setDefaultFragment(dashboardFragment);
    }

    // This method is used to set the default fragment that will be shown.
    private void setDefaultFragment(Fragment defaultFragment) {
        this.replaceFragment(defaultFragment, R.string.title_dashboard_fragment);
    }

    // Replace current Fragment with the destination Fragment.
    public void replaceFragment(Fragment destFragment, int titleResId) {

        if (mCurrentFragment != destFragment) {
            // First get FragmentManager object.
            FragmentManager fragmentManager = this.getSupportFragmentManager();

            // Begin Fragment transaction.
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            // Replace the layout holder with the required Fragment object.
            fragmentTransaction.replace(R.id.fragmentFrameLayout, destFragment);


            // Commit the Fragment replace action.
            fragmentTransaction.commit();
            setTitle(titleResId);
            mCurrentFragment = destFragment;
        }
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
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuLogout:
                mAuth.signOut();
                FirebaseUser user = mAuth.getCurrentUser();
                updateUI(user);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void updateUI(FirebaseUser currentUser) {
        if (currentUser == null) {
            startActivityFromRight(LoginActivity.class);
            finishToRight();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navDashboard:
                replaceFragment(new DashboardFragment(), R.string.title_dashboard_fragment);
                break;
            case R.id.navHasilStudi:
                replaceFragment(new HasilStudiFragment(), R.string.title_hasil_studi_fragment);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
