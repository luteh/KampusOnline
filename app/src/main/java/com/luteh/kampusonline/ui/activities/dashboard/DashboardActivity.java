package com.luteh.kampusonline.ui.activities.dashboard;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;

import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.luteh.kampusonline.common.AppConstant;
import com.luteh.kampusonline.common.Common;
import com.luteh.kampusonline.common.util.HeaderViewHolder;
import com.luteh.kampusonline.model.JatuhTempoDate;
import com.luteh.kampusonline.model.User;
import com.luteh.kampusonline.ui.activities.login.LoginActivity;
import com.luteh.kampusonline.ui.fragments.dashboard.DashboardFragment;
import com.luteh.kampusonline.ui.fragments.hasilstudi.HasilStudiFragment;
import com.luteh.kampusonline.R;
import com.luteh.kampusonline.common.base.BaseActivity;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import libs.mjn.prettydialog.PrettyDialog;
import libs.mjn.prettydialog.PrettyDialogCallback;

public class DashboardActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, IDashboardActivityView {

    private IDashboardActivityPresenter iDashboardActivityPresenter;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindString(R.string.label_jadwal_berwalian)
    String labelJadwalPerwalian;

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

        iDashboardActivityPresenter.getJatuhTempoDate();
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

                Common.isFrsDialogShowed = false;
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

    @Override
    public void showJatuhTempoDialog(List<JatuhTempoDate> jatuhTempoDateList) {
        long MAGIC = 86400000L;
        Date today = Calendar.getInstance().getTime();
        int currentDate = (int) (today.getTime() / MAGIC);
        int lastDate = (int) (jatuhTempoDateList.get(0).lastDate.getTime() / MAGIC);
        int differenceDate = lastDate - currentDate;

        if (!Common.isFrsDialogShowed &&
                currentDate < lastDate) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            final PrettyDialog prettyDialog = new PrettyDialog(this);
            prettyDialog.setTitle(getResources().getText(R.string.label_belum_isi_frs).toString())
                    .setTitleColor(R.color.pdlg_color_red)
                    .setMessage(
                            new StringBuilder()
                                    .append(labelJadwalPerwalian)
                                    .append("\n")
                                    .append(String.format("Semester %s", jatuhTempoDateList.get(0).semester))
                                    .append("\n")
                                    .append(simpleDateFormat.format(jatuhTempoDateList.get(0).startDate))
                                    .append(" s/d ")
                                    .append(simpleDateFormat.format(jatuhTempoDateList.get(0).lastDate))
                                    .append("\n")
                                    .append(String.format("Perwalian akan berakhir %d hari lagi !", differenceDate + 1))
                                    .toString())
                    .setMessageColor(R.color.pdlg_color_black)
                    .setIcon(R.drawable.pdlg_icon_info)
                    .setIconTint(R.color.pdlg_color_green)
                    .addButton(
                            "Isi FRS",
                            R.color.pdlg_color_white,
                            R.color.pdlg_color_green, new PrettyDialogCallback() {
                                @Override
                                public void onClick() {

                                }
                            })
                    .addButton(
                            "Cancel",
                            R.color.pdlg_color_white,
                            R.color.pdlg_color_red,
                            new PrettyDialogCallback() {
                                @Override
                                public void onClick() {
                                    // Dismiss
                                    prettyDialog.dismiss();
                                }
                            }
                    )
                    .setGravity(Gravity.CENTER)
                    .setAnimationEnabled(true)
                    .show();
            prettyDialog.setCancelable(false);

            Common.isFrsDialogShowed = true;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iDashboardActivityPresenter.onDestroy();
    }
}
