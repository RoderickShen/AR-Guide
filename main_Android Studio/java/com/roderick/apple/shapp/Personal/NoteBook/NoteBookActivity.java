package com.roderick.apple.shapp.Personal.NoteBook;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.roderick.apple.shapp.Personal.Login.Login_Message;
import com.roderick.apple.shapp.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NoteBookActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout noteBookRefresh;
    private TextView addNote;
    private ListView noteList;
    private NoteAdapter adapter;
    private List<NoteModel> noteModelList;
    public final String GET_NOTE_URL = "http://www.sskstudio.cn:8000/myApp/ShowNote/";
    public String NoteJSONArray="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_note_book );
        addNote=(TextView) findViewById(R.id.addNote);
        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(NoteBookActivity.this,WriteNoteActivity.class);
                startActivity(intent);
            }
        });


        ImageView noteBookBack=(ImageView) findViewById( R.id.noteBookBack);
        noteBookBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );

        /*
        先获取用户名 没有登录则关闭并吐司“请先登录”
         */

        noteModelList = new ArrayList<NoteModel>();
        noteList =(ListView) findViewById( R.id.noteBookList );
        adapter = new NoteAdapter( this, noteModelList );
        noteList.setAdapter( adapter );
        noteList.setOnItemClickListener(this );
        StringRequest request = new StringRequest( Request.Method.POST, GET_NOTE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String result) {
                NoteJSONArray = result;
                ShowNoteList( NoteJSONArray );
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText( NoteBookActivity.this,"请先登录！！",Toast.LENGTH_SHORT ).show();
            }
        } ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {//在这里封装了需要发送的参数；
                HashMap<String, String> map = new HashMap<>();
                map.put("name", Login_Message.address);//以键值对的形式存放；
                return map;
            }
        };
        Volley.newRequestQueue(getApplicationContext()).add(request);//加入请求队列；

        noteBookRefresh=(SwipeRefreshLayout)findViewById( R.id.noteBook_refresh );
        noteBookRefresh.setOnRefreshListener( this );
        //noteBookRefresh.setColorSchemeResources(  );
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        NoteModel tourGroupModel=noteModelList.get(position);
        Intent intent=new Intent(NoteBookActivity.this,ShowNoteActivity.class);
        intent.putExtra("content",tourGroupModel.getContent());
        startActivity(intent);
    }

    public void ShowNoteList(String NoteJSONArray){
        noteModelList.clear();
        int jsonArrayLenth=0,i=0;
        try {
            JSONArray jsonArray=new JSONArray(NoteJSONArray);
            jsonArrayLenth=jsonArray.length();
            for(;i<jsonArrayLenth;i++){
                JSONObject object=jsonArray.getJSONObject(i);
                String content=object.getString("content");
                noteModelList.add(new NoteModel(content));
            }
            adapter.notifyDataSetChanged();//通知适配器更新
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed( mRefresh,2000 );
    }
    private Handler mHandler=new Handler(  );
    private Runnable mRefresh=new Runnable() {
        @Override
        public void run() {
            StringRequest request = new StringRequest( Request.Method.POST, GET_NOTE_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String result) {
                    NoteJSONArray = result;
                    ShowNoteList( NoteJSONArray );
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText( NoteBookActivity.this,"加载错误！",Toast.LENGTH_LONG ).show();
                }
            } ) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {//在这里封装了需要发送的参数；
                    HashMap<String, String> map = new HashMap<>();
                    map.put("name", Login_Message.address);//以键值对的形式存放；
                    return map;
                }
            };
            Volley.newRequestQueue(getApplicationContext()).add(request);//加入请求队列；
            noteBookRefresh.setRefreshing( false );
        }
    };
}
