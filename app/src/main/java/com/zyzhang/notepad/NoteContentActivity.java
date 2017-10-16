package com.zyzhang.notepad;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NoteContentActivity extends AppCompatActivity {

    private Toolbar mContentToolbar;
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_content);
        mContentToolbar = (Toolbar)findViewById(R.id.contentToolbar);
        mEditText = (EditText)findViewById(R.id.edit_note_conten);
        setSupportActionBar(mContentToolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                insertData();
                finish();
                break;
            default:
        }
        return true;
    }

    private void insertData(){
        NoteBean note = new NoteBean();
        note.setContent(mEditText.getText().toString());
        note.setModifyTime(new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date(System.currentTimeMillis())));
        note.save();
    }
}
