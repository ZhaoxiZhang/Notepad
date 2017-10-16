package com.zyzhang.notepad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class NoteContentActivity extends AppCompatActivity {

    private Toolbar contentToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_content);
        contentToolbar = (Toolbar)findViewById(R.id.contentToolbar);
        setSupportActionBar(contentToolbar);
    }
}
