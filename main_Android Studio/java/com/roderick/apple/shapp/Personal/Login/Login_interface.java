package com.roderick.apple.shapp.Personal.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.roderick.apple.shapp.Personal.PersonalActivity;
import com.roderick.apple.shapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class Login_interface extends AppCompatActivity {

    private EditText editText_loginAddress;
    private EditText editText_loginPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_interface);
        Button login_confirm=(Button)findViewById(R.id.login_button);
        Button forgetpassword=(Button)findViewById(R.id.forgetpassword);
        Button new_user=(Button)findViewById(R.id.new_user);        //新用户注册
        Button login_back=(Button)findViewById(R.id.login_back);
        Button server_item_link=(Button)findViewById(R.id.server_item_link);


        login_confirm.setOnClickListener(new MyListener());
        forgetpassword.setOnClickListener(new MyListener());
        new_user.setOnClickListener(new MyListener());
        server_item_link.setOnClickListener(new MyListener());
        login_back.setOnClickListener(new MyListener());

        editText_loginAddress=findViewById(R.id.login_address);
        editText_loginPassword=findViewById(R.id.login_password);
        editText_loginAddress.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
    }

    class MyListener implements View.OnClickListener{
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.login_button:
                    String url = "http://www.sskstudio.cn:8000/myApp/Login/";//这里和GET方式不同的是去掉了“？”后面的参数；
                    /**
                     * 第一个参数指定了请求方式，第二个参数指定了url，第三个参数指定了正确访问的返回结果，第四个参数是访问失败后的业务逻辑；
                     */
                    StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String result) { //0：邮箱对密码错 1：邮箱不存在 2：登录成功 -1：登录失败

                            JSONArray jsonArray= null;
                            try {
                                /*
                                登录获取用户信息
                                 */
                                jsonArray = new JSONArray(result);
                                JSONObject object=jsonArray.getJSONObject(0);
                                String temp=object.getString("temp");
                                if(Integer.parseInt(temp)==1)
                                    Toast.makeText(Login_interface.this,"邮箱不存在",Toast.LENGTH_SHORT).show();
                                else if(Integer.parseInt(temp)==0)
                                    Toast.makeText(Login_interface.this,"密码错误",Toast.LENGTH_SHORT).show();
                                else if(Integer.parseInt(temp)==2)
                                {
                                    //获取用户信息
                                    String nickname=object.getString("nickname");
                                    String sex=object.getString("sex");
                                    String headprotrait_url=object.getString("headprotrait_url");
                                    String state=object.getString("state");

                                    Toast.makeText(Login_interface.this,"登录成功",Toast.LENGTH_SHORT).show();
                                    //region保存登录状态到本地及手机
                                    Login_Message.address=editText_loginAddress.getText().toString();
                                    Login_Message.state=1;
                                    Login_Message.nickname=nickname;
                                    Login_Message.sex=sex;
                                    Login_Message.headprotrait_url=headprotrait_url;

                                    FileOutputStream fos;
                                    String content=Login_Message.address+"##"+Login_Message.state+"##"+Login_Message.nickname+"##"+Login_Message.sex+"##"+Login_Message.headprotrait_url;
                                    try{
                                        fos=openFileOutput("data.txt",MODE_PRIVATE);
                                        fos.write(content.getBytes());
                                        fos.close();
                                        //Toast.makeText(Login_interface.this,"保存用户登录信息成功",Toast.LENGTH_SHORT).show();
                                    } catch (FileNotFoundException e) {
                                        e.printStackTrace();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    //endregion
                                    finish();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }



                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            Toast.makeText(Login_interface.this,"登录失败", Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {//在这里封装了需要发送的参数；
                            HashMap<String, String> map = new HashMap<>();
                            map.put("name", editText_loginAddress.getText().toString());//以键值对的形式存放；
                            map.put("password",stringToMD5(editText_loginPassword.getText().toString()));    //将密码转为md5码再传入后台
                            return map;
                        }
                    };
                    Volley.newRequestQueue(getApplicationContext()).add(request);//加入请求队列
                    break;
                case R.id.forgetpassword:
                    Intent intent3=new Intent(Login_interface.this,ForgetPassword.class);
                    startActivity(intent3);
                    break;
                case R.id.new_user:
                    Intent intent1=new Intent(Login_interface.this,Register.class);
                    startActivity(intent1);
                    break;
                case R.id.server_item_link:
                    Intent intent2=new Intent(Login_interface.this,Server_item_link.class);
                    startActivity(intent2);
                    break;
                case R.id.login_back:
                    Login_interface.this.finish();
                    break;
                default:break;
            }

        }
    }

    //转MD5码
    public String stringToMD5(String string) {
        byte[] hash;

        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10)
                hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }

        return hex.toString();
    }
}
