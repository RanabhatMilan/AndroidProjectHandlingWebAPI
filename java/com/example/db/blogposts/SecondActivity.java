package com.example.db.blogposts;


import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SecondActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    RecyclerView recyclerView;
    LinearLayoutManager manager;
    PostAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        recyclerView = findViewById(R.id.postList);
        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        navigationView = findViewById(R.id.navigation_menu);

        setUpToolbar();
        getData();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                    switch (menuItem.getItemId()){
                        case R.id.nav_home:
                            Toast.makeText(SecondActivity.this,"Clicked Home", Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.nav_android:
                            Toast.makeText(SecondActivity.this,"Clicked Android",Toast.LENGTH_SHORT).show();
                            break;

                    }
                    return false;
                }
            });



    }
    private void setUpToolbar(){
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    private void getData(){

        Call<BlogPostList> postList = BloggerAPI.getService().getPostList();
        postList.enqueue(new Callback<BlogPostList>() {
            @Override
            public void onResponse(Call<BlogPostList> call, Response<BlogPostList> response) {
                BlogPostList list = response.body();
                recyclerView.setAdapter(new PostAdapter(SecondActivity.this,list.getItems()));
                Toast.makeText(SecondActivity.this,"Success",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<BlogPostList> call, Throwable t) {
                Toast.makeText(SecondActivity.this, "Error Occured", Toast.LENGTH_SHORT).show();
            }
        });
    }
}


