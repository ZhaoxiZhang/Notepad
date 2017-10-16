package com.zyzhang.notepad;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ScrollView;

import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NoteContentActivity extends AppCompatActivity {

    private Toolbar mContentToolbar;
    private EditText mEditText;
    private Intent mIntent;
    private ScrollView mScrollView;
    private NoteBean mNoteBean;
    private int mNoteId;
    private static final String TAG = "NoteContentActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_content);

        mContentToolbar = (Toolbar)findViewById(R.id.contentToolbar);
        mEditText = (EditText)findViewById(R.id.edit_note_conten);
        mIntent = this.getIntent();

        setSupportActionBar(mContentToolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if ((mNoteBean = (NoteBean) mIntent.getSerializableExtra("note")) != null){
            mNoteId = mNoteBean.getId();
            mEditText.setText(mNoteBean.getContent());
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

        List<NoteBean>notes = DataSupport.where("id = ?",String.valueOf(mNoteId)).find(NoteBean.class);
        for (NoteBean x:notes){
            Log.d(TAG, "zxzhang" + String.valueOf(mNoteId));
        }
        if (notes.isEmpty()){
            NoteBean note = new NoteBean();
            note.setContent(mEditText.getText().toString());
            note.setModifyTime(new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date(System.currentTimeMillis())));
            note.save();
        }else{
            NoteBean note = new NoteBean();
            note.setContent(mEditText.getText().toString());
            note.setModifyTime(mNoteBean.getModifyTime());
            Log.d(TAG, "zxzhang id " + String.valueOf(mNoteId));
            Log.d(TAG, mNoteBean.getContent());
            note.updateAll("id = ?",String.valueOf(mNoteId));
        }

    }
}
