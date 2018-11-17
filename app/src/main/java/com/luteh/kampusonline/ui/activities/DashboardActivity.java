package com.luteh.kampusonline.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.luteh.kampusonline.common.AppConstant;
import com.luteh.kampusonline.common.util.HeaderViewHolder;
import com.luteh.kampusonline.model.User;
import com.luteh.kampusonline.presenter.dashboardactivity.DashboardActivityPresenterImp;
import com.luteh.kampusonline.presenter.dashboardactivity.IDashboardActivityPresenter;
import com.luteh.kampusonline.ui.fragments.DashboardFragment;
import com.luteh.kampusonline.ui.fragments.HasilStudiFragment;
import com.luteh.kampusonline.R;
import com.luteh.kampusonline.common.base.BaseActivity;
import com.luteh.kampusonline.view.IDashboardActivityView;
import com.squareup.picasso.Picasso;

import butterknife.BindView;

public class DashboardActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, IDashboardActivityView {

    private IDashboardActivityPresenter iDashboardActivityPresenter;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAuth = FirebaseAuth.getInstance();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        // Create and set Android Fragment as default.
        Fragment dashboardFragment = new DashboardFragment();
        setDefaultFragment(dashboardFragment);
    }

    @Override
    protected void onInit() {
        super.onInit();
        iDashboardActivityPresenter = new DashboardActivityPresenterImp(this, this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        iDashboardActivityPresenter.getUserInfo(bundle.getString(AppConstant.KEY_UID));
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
            finishWithFade();
            startActivityWithFade(LoginActivity.class);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navDashboard:
                replaceFragment(new DashboardFragment(), R.string.title_dashboard_fragment);
                break;
            case R.id.navKeuangan:
                break;
            case R.id.navHasilStudi:
                replaceFragment(new HasilStudiFragment(), R.string.title_hasil_studi_fragment);
                break;
            case R.id.navJadwal:
                break;
            case R.id.navRencanaStudi:
                break;
            case R.id.navLogout:
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onRetrieveUserInfoSuccess(User user) {
        View headerLayout = navigationView.getHeaderView(0);
        HeaderViewHolder headerViewHolder = new HeaderViewHolder(headerLayout);

        if (user.getUri() != null) {
            Picasso.get()
                    .load(user.getUri())
                    .into(headerViewHolder.imgProfile);
        }

        if (user.getName() != null) {
            headerViewHolder.tvProfileName.setText(user.getName());
            headerViewHolder.tvProfileNpm.setText(user.getNpm());
        }
    }
}
