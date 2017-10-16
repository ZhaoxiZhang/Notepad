package com.zyzhang.notepad;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder>{

    private List<NoteBean>mNotes;

    static class NoteViewHolder extends RecyclerView.ViewHolder{
        TextView contentText;
        TextView timeText;
        public NoteViewHolder(View view){
            super(view);
            contentText = (TextView)view.findViewById(R.id.note_content);
            timeText = (TextView)view.findViewById(R.id.note_time);
        }
    }

    public NoteAdapter(List<NoteBean>notes){
        mNotes = notes;
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item,parent,false);
        NoteViewHolder holder = new NoteViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        NoteBean note = mNotes.get(position);
        holder.contentText.setText(note.getContent());
        holder.timeText.setText(note.getModifyTime());
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

}
