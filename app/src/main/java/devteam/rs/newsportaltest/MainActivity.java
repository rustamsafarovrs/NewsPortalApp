package devteam.rs.newsportaltest;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

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

    RecyclerView mRecyclerView;
    NewsList mNewsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar != null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        initNavigationDrawer(toolbar);

        mNewsList = new NewsList();

        mRecyclerView = findViewById(R.id.main_activity_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Call<NewsList> call = ((App) getApplication()).getApi().getNewsList();

        call.enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(Call<NewsList> call, Response<NewsList> response) {

                mRecyclerView.setAdapter(new Adapter(MainActivity.this, response.body().getNewsList()));
                Log.i("NewsPortal", "onResponse");

            }

            @Override
            public void onFailure(Call<NewsList> call, Throwable t) {
                Toast.makeText(MainActivity.this, "An error occurred during networking", Toast.LENGTH_LONG)
                        .show();
                Log.i("NewsPortal", "onFailure", t);
            }
        });

    }

    private void initNavigationDrawer(Toolbar toolbar) {

        IProfile profile = new ProfileDrawerItem()
                .withName(getString(R.string.nav_item_header))
                .withEmail(getString(R.string.nav_item_mail))
                .withIcon(getResources().getDrawable(R.drawable.ic_launcher_background));

        AccountHeader accountHeader = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.background_image)
                .addProfiles(profile)
                .build();

        Drawer drawerBuilder = new DrawerBuilder()
                .withActivity(this)
                .withActionBarDrawerToggle(true)
                .withAccountHeader(accountHeader)
                .withToolbar(toolbar)
                .withDisplayBelowStatusBar(true)
                .withActionBarDrawerToggleAnimated(true)
                .addDrawerItems(
                        getIDrawerItems()
                )
                .build();

    }

    private IDrawerItem[] getIDrawerItems() {
        return new IDrawerItem[]{new PrimaryDrawerItem()
                .withName(getString(R.string.nav_item_1))
                .withIcon(R.drawable.ic_nav_home)
                .withIdentifier(1),

                new DividerDrawerItem(),

                new PrimaryDrawerItem()
                        .withName(getString(R.string.nav_item_2))
                        .withIcon(R.drawable.ic_nav_item_2)
                        .withIdentifier(2),

                new PrimaryDrawerItem()
                        .withName(getString(R.string.nav_item_3))
                        .withIcon(R.drawable.ic_nav_item_3)
                        .withIdentifier(3),

                new PrimaryDrawerItem()
                        .withName(getString(R.string.nav_item_4))
                        .withIcon(R.drawable.ic_nav_item_4)
                        .withIdentifier(4),

                new PrimaryDrawerItem()
                        .withName(getString(R.string.nav_item_5))
                        .withIcon(R.drawable.ic_nav_item_5)
                        .withIdentifier(5),

                new PrimaryDrawerItem()
                        .withName(getString(R.string.nav_item_6))
                        .withIcon(R.drawable.ic_nav_item_6)
                        .withIdentifier(6),

                new PrimaryDrawerItem()
                        .withName(getString(R.string.nav_item_7))
                        .withIcon(R.drawable.ic_nav_item_7)
                        .withIdentifier(7),

                new PrimaryDrawerItem()
                        .withName(getString(R.string.nav_item_8))
                        .withIcon(R.drawable.ic_nav_item_8)
                        .withIdentifier(8),

                new PrimaryDrawerItem()
                        .withName(getString(R.string.nav_item_9))
                        .withIcon(R.drawable.ic_nav_item_9)
                        .withIdentifier(9),

                new PrimaryDrawerItem()
                        .withName(getString(R.string.nav_item_10))
                        .withIcon(R.drawable.ic_nav_item_10)
                        .withIdentifier(10)};
    }
}
