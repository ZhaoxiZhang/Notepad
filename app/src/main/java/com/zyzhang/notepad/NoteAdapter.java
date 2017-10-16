package com.zyzhang.notepad;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder>{

    private List<NoteBean>mNotes;
    private Context mContext;

    static class NoteViewHolder extends RecyclerView.ViewHolder{
        View noteView;
        TextView contentText;
        TextView timeText;
        public NoteViewHolder(View view){
            super(view);
            noteView = view;
            contentText = (TextView)view.findViewById(R.id.note_content);
            timeText = (TextView)view.findViewById(R.id.note_time);
        }
    }



    public NoteAdapter(Context context,List<NoteBean>notes){
        mContext = context;
        mNotes = notes;
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item,parent,false);
        final NoteViewHolder holder = new NoteViewHolder(view);

        holder.noteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                NoteBean note = mNotes.get(position);
                Toast.makeText(v.getContext(),note.getContent(),Toast.LENGTH_SHORT).show();

                String content = note.getContent();
                int id = note.getId();
                NoteBean newNote = new NoteBean(id,content);
                Intent intent = new Intent(mContext,NoteContentActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("note",newNote);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });


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
