package com.roderick.apple.shapp.Community.Discuss;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.roderick.apple.shapp.Personal.Login.Login_Message;
import com.roderick.apple.shapp.Personal.NoteBook.WriteNoteActivity;
import com.roderick.apple.shapp.R;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class WriteMessageActivity extends AppCompatActivity {
    RequestQueue queue=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_message);
        ImageView writeMessageback=(ImageView) findViewById( R.id.writeMessageBack);
        queue=Volley.newRequestQueue(this);
        writeMessageback.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );

//        EditText noteEditText=(EditText) findViewById(R.id.noteEditText);

        TextView saveMessage=(TextView)findViewById( R.id.saveMessage );
        saveMessage.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText messageEditText=(EditText) findViewById(R.id.MessageEditText);
                final String message=messageEditText.getText().toString();
                String url = "http://www.sskstudio.cn:8000/myApp/InPutMessage/";//需修改！
                StringRequest request = new StringRequest( Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String result) {
                        Toast.makeText( WriteMessageActivity.this,"发表成功！",Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText( WriteMessageActivity.this,"请先登录！！",Toast.LENGTH_SHORT).show();
                    }
                } ) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {//在这里封装了需要发送的参数；
                        HashMap<String, String> map = new HashMap<>();
                        map.put("user", Login_Message.address);//以键值对的形式存放；
                        map.put("content",message);
                        map.put("nickname",Login_Message.nickname);
                        map.put("headprotrait_url",Login_Message.headprotrait_url);
                        return map;
                    }
                };
                queue.add(request);//加入请求队列；
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
