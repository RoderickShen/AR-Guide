package com.roderick.apple.shapp.Personal.NoteBook;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.roderick.apple.shapp.R;

import java.util.List;

/**
 * Created by dell on 2018/4/18.
 */

public class NoteAdapter extends BaseAdapter {
    private Activity context;
    private List<NoteModel> noteModelList;
    public NoteAdapter(Activity context,List<NoteModel> noteModelList){
        this.context=context;
        this.noteModelList=noteModelList;
    }

    @Override
    public int getCount() {
        return noteModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return noteModelList.get( position );
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate( R.layout.note_item,null);
        }
        TextView noteContent=(TextView) convertView.findViewById(R.id.noteContent);
        NoteModel noteModel=noteModelList.get( position );
        noteContent.setText(noteModel.getContent());
        return convertView;
    }
}
