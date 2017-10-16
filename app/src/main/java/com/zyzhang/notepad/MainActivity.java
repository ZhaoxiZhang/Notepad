package com.zyzhang.notepad;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<NoteBean>notes;
    private Toolbar mToolbar;
    private FloatingActionButton mFloatingActionButton;
    private RecyclerView mrecyclerView;
    private DrawerLayout mDrawerLayout;
    private NoteAdapter noteAdapter;

    private static final String TAG = "MainActivity";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        initMenu();

        mFloatingActionButton = (FloatingActionButton)findViewById(R.id.add_note);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Hello",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,NoteContentActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        initRecyclerView();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
        }
        return true;
    }

    private void initToolbar(){
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        setTitle("Notepad");
    }

    private void initData(){
        NoteBean note = new NoteBean();
        note.setContent("Hello Wolrd!");
        note.setModifyTime(new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date(System.currentTimeMillis())));
        note.save();
    }

    private void initRecyclerView(){
        mrecyclerView = (RecyclerView)findViewById(R.id.recycle_notes_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mrecyclerView.setLayoutManager(linearLayoutManager);
        notes = DataSupport.findAll(NoteBean.class);
        noteAdapter = new NoteAdapter(getApplicationContext(),notes);
        mrecyclerView.setAdapter(noteAdapter);


    }

    private void initMenu(){
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
    }
}
