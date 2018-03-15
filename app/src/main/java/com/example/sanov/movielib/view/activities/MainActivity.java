package com.example.sanov.movielib.view.activities;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.sanov.movielib.R;
import com.example.sanov.movielib.view.fragments.NowPlayingFragment;
import com.example.sanov.movielib.view.fragments.UpcomingFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViewNowPlaying();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_now_playing:
                                setViewNowPlaying();
                                break;
                            case R.id.action_upcoming:
                                setViewUpcoming();
                                break;
                            case R.id.action_search:
                                setViewSearch();
                                break;

                        }
                        return true;
                    }
                });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_lang){
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void setViewSearch() {
        Intent intent = new Intent(MainActivity.this, MovieSearchActivity.class);
        startActivity(intent);
    }

    private void setViewUpcoming() {
        setTitle(getResources().getString(R.string.upcoming));
        UpcomingFragment upcomingFragment = new UpcomingFragment();
        android.support.v4.app.FragmentTransaction mFragmentTransactionUp = getSupportFragmentManager().beginTransaction();
        mFragmentTransactionUp.replace(R.id.frame_container, upcomingFragment, UpcomingFragment.class.getSimpleName());
        mFragmentTransactionUp.commit();
    }

    private void setViewNowPlaying() {
        setTitle(getResources().getString(R.string.now_playing));
        NowPlayingFragment nowPlayingFragment = new NowPlayingFragment();
        android.support.v4.app.FragmentTransaction mFragmentTransactionNp = getSupportFragmentManager().beginTransaction();
        mFragmentTransactionNp.replace(R.id.frame_container, nowPlayingFragment, NowPlayingFragment.class.getSimpleName());
        mFragmentTransactionNp.commit();
    }
}
