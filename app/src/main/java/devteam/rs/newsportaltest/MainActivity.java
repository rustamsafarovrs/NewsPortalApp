package devteam.rs.newsportaltest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    RecyclerView mRecyclerView;
    NewsList mNewsList;
    Drawer drawerBuilder;
    private int mCategory = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        initNavigationDrawer(toolbar);

        mNewsList = new NewsList();

        mRecyclerView = findViewById(R.id.main_activity_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadData(mCategory);

    }

    private void loadData(int category) {
        Call<NewsList> call = ((App) getApplication()).getApi().getNewsList(category);

        call.enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(@NonNull Call<NewsList> call, @NonNull Response<NewsList> response) {

                mRecyclerView.setAdapter(new Adapter(MainActivity.this, response.body().getNewsList()));
                Log.i("NewsPortal", "onResponse");

            }

            @Override
            public void onFailure(Call<NewsList> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "An error occurred during networking", Toast.LENGTH_LONG)
//                        .show();

                Snackbar.make(getWindow().getDecorView().getRootView(),
                        "NO INTERNET CONNECTIONS", Snackbar.LENGTH_LONG)
                        .setAction("Retry", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                loadData(mCategory);

                            }
                        })
                        .show();


                Log.i("NewsPortal", "onFailure", t);
            }
        });
    }

    private void initNavigationDrawer(Toolbar toolbar) {

        IProfile profile = new ProfileDrawerItem()
                .withTextColor(getResources().getColor(android.R.color.white))
                .withName(getString(R.string.nav_item_header))
                .withEmail(getString(R.string.nav_item_mail))
                .withIcon(getResources().getDrawable(R.drawable.ic_launcher_background));

        AccountHeader accountHeader = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.background_image)
                .addProfiles(profile)
                .build();

        drawerBuilder = new DrawerBuilder()
                .withActivity(this)
                .withActionBarDrawerToggle(true)
                .withAccountHeader(accountHeader)
                .withToolbar(toolbar)
                .withDisplayBelowStatusBar(true)
                .withActionBarDrawerToggleAnimated(true)
                .addDrawerItems(
                        getIDrawerItems()
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        //for categories
                        if (position > 0 && position < 11) {
                            loadData(position);
                            mCategory = position;
                        }

                        Log.i(TAG, "Clicked in pos " + position);

                        return false;
                    }
                })
                .build();

    }

    private IDrawerItem[] getIDrawerItems() {

        return new IDrawerItem[]{
                new PrimaryDrawerItem()
                        .withName(getString(R.string.nav_item_1))
                        .withIcon(R.drawable.ic_nav_home)
                        .withSelectedTextColor(getResources().getColor(R.color.nav_item_selected))
                        .withIdentifier(1),

                new DividerDrawerItem(),

                new PrimaryDrawerItem()
                        .withName(getString(R.string.nav_item_2))
                        .withIcon(R.drawable.ic_nav_item_2)
                        .withSelectedTextColor(getResources().getColor(R.color.nav_item_selected))
                        .withIdentifier(2),

                new PrimaryDrawerItem()
                        .withName(getString(R.string.nav_item_3))
                        .withIcon(R.drawable.ic_nav_item_3)
                        .withSelectedTextColor(getResources().getColor(R.color.nav_item_selected))
                        .withIdentifier(3),

                new PrimaryDrawerItem()
                        .withName(getString(R.string.nav_item_4))
                        .withIcon(R.drawable.ic_nav_item_4)
                        .withSelectedTextColor(getResources().getColor(R.color.nav_item_selected))
                        .withIdentifier(4),

                new PrimaryDrawerItem()
                        .withName(getString(R.string.nav_item_5))
                        .withIcon(R.drawable.ic_nav_item_5)
                        .withSelectedTextColor(getResources().getColor(R.color.nav_item_selected))
                        .withIdentifier(5),

                new PrimaryDrawerItem()
                        .withName(getString(R.string.nav_item_6))
                        .withIcon(R.drawable.ic_nav_item_6)
                        .withSelectedTextColor(getResources().getColor(R.color.nav_item_selected))
                        .withIdentifier(6),

                new PrimaryDrawerItem()
                        .withName(getString(R.string.nav_item_7))
                        .withIcon(R.drawable.ic_nav_item_7)
                        .withSelectedTextColor(getResources().getColor(R.color.nav_item_selected))
                        .withIdentifier(7),

                new PrimaryDrawerItem()
                        .withName(getString(R.string.nav_item_8))
                        .withIcon(R.drawable.ic_nav_item_8)
                        .withSelectedTextColor(getResources().getColor(R.color.nav_item_selected))
                        .withIdentifier(8),

                new PrimaryDrawerItem()
                        .withName(getString(R.string.nav_item_9))
                        .withIcon(R.drawable.ic_nav_item_9)
                        .withSelectedTextColor(getResources().getColor(R.color.nav_item_selected))
                        .withIdentifier(9),

                new PrimaryDrawerItem()
                        .withName(getString(R.string.nav_item_10))
                        .withIcon(R.drawable.ic_nav_item_10)
                        .withSelectedTextColor(getResources().getColor(R.color.nav_item_selected))
                        .withIdentifier(10),

                new DividerDrawerItem(),

                new PrimaryDrawerItem()
                        .withName(getString(R.string.nav_item_settings))
                        .withIcon(R.drawable.ic_nav_item_settings)
                        .withSelectedTextColor(getResources().getColor(R.color.nav_item_selected))
                        .withIdentifier(11)
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings){

            return true;
        } else if(id == R.id.action_add_news){
            Intent intent = new Intent(MainActivity.this, AddNewsActivity.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.action_refresh){
            loadData(1);
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onBackPressed() {

        if (drawerBuilder.isDrawerOpen()) {
            drawerBuilder.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }
}
