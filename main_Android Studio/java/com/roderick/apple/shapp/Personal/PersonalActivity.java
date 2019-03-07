package com.roderick.apple.shapp.Personal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.allen.library.SuperTextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.StringSignature;
import com.roderick.apple.shapp.Personal.Login.Login_Message;
import com.roderick.apple.shapp.Personal.Login.Login_interface;
import com.roderick.apple.shapp.Personal.Login.Server_item_link;
import com.roderick.apple.shapp.Personal.NoteBook.NoteBookActivity;
import com.roderick.apple.shapp.R;

public class PersonalActivity extends AppCompatActivity {
    private static int LOGIN_REQUEST_CODE= 1;
    private static int LOGOUT_REQUSET_CODE= 2;

    public SuperTextView login_button;
    private TextView btn_about;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_personal);


        login_button=findViewById(R.id.login);
        btn_about=findViewById(R.id.about);

        btn_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PersonalActivity.this, Server_item_link.class));
            }
        });

        if(Login_Message.state==0){
            login_button.setLeftBottomString("注册/登录");
            login_button.setLeftIcon(R.drawable.head);
            login_button.setEnabled(true);
            login_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(com.roderick.apple.shapp.Personal.PersonalActivity.this,Login_interface.class);
                    startActivity(new Intent(PersonalActivity.this,Login_interface.class));
                }
            });
        }
        else if(Login_Message.state==1){
            login_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(com.roderick.apple.shapp.Personal.PersonalActivity.this,SetUp.class);
                    startActivity(intent);
                }
            });
            login_button.setLeftTopString(Login_Message.address);
            login_button.setLeftBottomString("修改个人信息>");
            String updateTime = String.valueOf(System.currentTimeMillis()); // 在需要重新获取更新的图片时调用
            Glide.with(this)
                    .load(Login_Message.headprotrait_url)
                    .fitCenter().signature(new StringSignature(updateTime))
                    .into(login_button.getLeftIconIV());

        }

        TextView toTravel=(TextView)findViewById( R.id.toTravel );
        toTravel.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent( com.roderick.apple.shapp.Personal.PersonalActivity.this, NoteBookActivity.class );
                startActivity( intent );
            }
        } );
    }



    @Override
    protected void onResume() {
        super.onResume();
        if(Login_Message.state==0){
            login_button.setLeftBottomString("注册/登录");
            login_button.setLeftIcon(R.drawable.head);
            login_button.setLeftTopString("");
            login_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(com.roderick.apple.shapp.Personal.PersonalActivity.this,Login_interface.class);
                    startActivity(intent);
                }
            });
        }
        else if(Login_Message.state==1){
            login_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(com.roderick.apple.shapp.Personal.PersonalActivity.this,SetUp.class);
                    startActivity(intent);
                }
            });
            login_button.setLeftBottomString(Login_Message.address);
            login_button.setLeftTopString(Login_Message.nickname);
            String updateTime = String.valueOf(System.currentTimeMillis());
            Glide.with(this)
                    .load(Login_Message.headprotrait_url)
                    .fitCenter().signature(new StringSignature(updateTime))
                    .into(login_button.getLeftIconIV());
        }
    }
}
