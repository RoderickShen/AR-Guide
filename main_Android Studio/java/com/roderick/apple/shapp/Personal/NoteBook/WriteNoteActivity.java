package com.roderick.apple.shapp.Personal.NoteBook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.roderick.apple.shapp.Personal.Login.Login_Message;
import com.roderick.apple.shapp.R;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by dell on 2018/4/16.
 */

public class WriteNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.write_note_layout);
        ImageView writeNoteback=(ImageView) findViewById( R.id.writeNoteBack);
        writeNoteback.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );

//        EditText noteEditText=(EditText) findViewById(R.id.noteEditText);

        TextView saveNote=(TextView)findViewById( R.id.saveNote );
        saveNote.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText noteEditText=(EditText) findViewById(R.id.noteEditText);
                final String note=noteEditText.getText().toString();
                String url = "http://www.sskstudio.cn:8000/myApp/InPutNote/";
                StringRequest request = new StringRequest( Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String result) {
                        Toast.makeText( WriteNoteActivity.this,"上传成功！",Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText( WriteNoteActivity.this,"上传失败！",Toast.LENGTH_SHORT).show();
                    }
                } ) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {//在这里封装了需要发送的参数；
                        HashMap<String, String> map = new HashMap<>();
                        map.put("name", Login_Message.address);//以键值对的形式存放；
                        map.put("content", note);
                        return map;
                    }
                };
                Volley.newRequestQueue(getApplicationContext()).add(request);//加入请求队列；
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        finish();
                    }
                };
                Timer timer = new Timer();
                timer.schedule(task, 1000);//1秒后执行TimeTask的run方法
            }
        } );
    }
}