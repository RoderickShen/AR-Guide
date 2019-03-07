package com.roderick.apple.shapp.Personal.Login;

import android.support.v7.app.AppCompatActivity;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.roderick.apple.shapp.R;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class ForgetPassword extends AppCompatActivity {

    private EditText editText_address_forgetPassword;
    private EditText editText_addressYanZheng_forgetPassword;
    private EditText editText_password_forgetPassword;
    private EditText editText_passwordAgain_forgetPassword;
    private Button clearButton_forgetPassword;
    private Button submitButton_forgetPassword;
    private Button passwordButton_forgetPassword;
    private Button passwordAgainButton_forgetPassword;

    private int randomNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgetpassword);
        initView();
    }

    public void initView() {
        Button back2=(Button)findViewById(R.id.back2);
        back2.setOnClickListener(new MyListener());
        //region 发送邮件
        Button send_address=findViewById(R.id.send_address_forgetPassword);
        clearButton_forgetPassword=findViewById(R.id.button_clear_forgetPassword);
        editText_addressYanZheng_forgetPassword=findViewById(R.id.input_identifyingCode_forgetPassword);
        editText_address_forgetPassword=findViewById(R.id.editText_address_forgetPassword);
        editText_address_forgetPassword.addTextChangedListener(addressTextWatcher);
        clearButton_forgetPassword.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                editText_address_forgetPassword.setText("");
            }
        });
        send_address.setOnClickListener(new MyListener());
        //endregion
        //region 密码隐藏/显示
        passwordButton_forgetPassword=findViewById(R.id.button_password_forgetPassword);
        editText_password_forgetPassword=findViewById(R.id.input_password_forgetPassword);
        editText_password_forgetPassword.addTextChangedListener(passWordTextWatcher);
        passwordButton_forgetPassword.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Drawable drawable1=getResources().getDrawable(R.drawable.appear_password);
                Drawable.ConstantState drawablenow=passwordButton_forgetPassword.getBackground().getConstantState();
                if(drawable1.getConstantState().equals(drawablenow))
                {
                    editText_password_forgetPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    passwordButton_forgetPassword.setBackgroundResource(R.drawable.disappear_password);
                }
                else
                {
                    editText_password_forgetPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    passwordButton_forgetPassword.setBackgroundResource(R.drawable.appear_password);
                }
            }
        });
        //endregion
        //region 再次输入密码隐藏/显示
        passwordAgainButton_forgetPassword=findViewById(R.id.button_passwordAgain_forgetPassword);
        editText_passwordAgain_forgetPassword=findViewById(R.id.input_passwordAgain_forgetPassword);
        editText_passwordAgain_forgetPassword.addTextChangedListener(passWordAgainTextWatcher);
        passwordAgainButton_forgetPassword.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Drawable drawable1=getResources().getDrawable(R.drawable.appear_password);
                Drawable.ConstantState drawablenow=passwordAgainButton_forgetPassword.getBackground().getConstantState();
                if(drawable1.getConstantState().equals(drawablenow))
                {
                    editText_passwordAgain_forgetPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    passwordAgainButton_forgetPassword.setBackgroundResource(R.drawable.disappear_password);
                }
                else
                {
                    editText_passwordAgain_forgetPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    passwordAgainButton_forgetPassword.setBackgroundResource(R.drawable.appear_password);
                }
            }
        });
        //endregion
        submitButton_forgetPassword=findViewById(R.id.button_submit_forgetPassword);
        submitButton_forgetPassword.setOnClickListener(new MyListener());
    }

    //开启一个线程模拟处理发邮件的操作
    private void initData() {
        //开启一个线程模拟处理耗时的操作
        new Thread(new Runnable() {
            @Override
            public void run() {
                SendEmail mail=new SendEmail();
                try {
                    mail.send();
                    //Toast.makeText(MainActivity.this, "邮件发送成功 ", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    //Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }).start();
    }

    //监听按钮事件
    class MyListener implements View.OnClickListener{
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.back2:
                    ForgetPassword.this.finish();
                    break;
                //region发送验证码按钮事件
                case R.id.send_address_forgetPassword:
                    if(judgeAddress(editText_address_forgetPassword.getText().toString())==false)
                    {
                        printError("这个邮箱不合理");
                        break;
                    }
                    initData();
                    break;
                //endregion
                //region提交按钮事件
                case R.id.button_submit_forgetPassword:
                    if(judgeAddress(editText_address_forgetPassword.getText().toString())==false)
                    {
                        printError("这个邮箱不合理");
                        break;
                    }
                    if(Integer.parseInt(editText_addressYanZheng_forgetPassword.getText().toString())!=randomNum)
                    {
                        printError("验证码错误");
                        break;
                    }
                    if(judgePassWord(editText_password_forgetPassword,editText_passwordAgain_forgetPassword)==0)
                        break;
                    duihuakuang();
                    break;
                //endregion
                default:break;
            }
        }
    }

    //判断电子邮箱是否符合邮箱要求
    public boolean judgeAddress(String email){
        if (null==email || "".equals(email)) return false;
        //Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配
        Pattern p =  Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//复杂匹配
        Matcher m = p.matcher(email);
        return m.matches();
    }

    //返回密码是否符合规范（1表示规范，0表示有错误）
    public int judgePassWord(EditText editText_password,EditText editText_passwordAgain){
        String passwordString=editText_password.getText().toString();
        String passwordStringAgain=editText_passwordAgain.getText().toString();
        if(passwordString==null||passwordString.length()<=0)
        {
            printError("密码不能为空");
            return 0;
        }
        else if(passwordString.length()>=16)
        {
            printError("密码过长");
            return 0;
        }
        else if(!passwordString.equals(passwordStringAgain))
        {
            printError("两次密码不一致");
            return 0;
        }
        return 1;
    }

    //点击确认以后的提交确认框（转后台在这里写）
    public void duihuakuang(){
        String correctString;
        AlertDialog.Builder correctKuang=new AlertDialog.Builder(this);
        //region 发送至后台
        correctKuang.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //点击确认后的事件
                String url = "http://www.sskstudio.cn:8000/myApp/ChangePassword/";//这里和GET方式不同的是去掉了“？”后面的参数；
                /**
                 * 第一个参数指定了请求方式，第二个参数指定了url，第三个参数指定了正确访问的返回结果，第四个参数是访问失败后的业务逻辑；
                 */
                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String result) { //不存在则返回1  操作成功返回0 没有操作为－1
                        if(Integer.parseInt(result)==1)
                            Toast.makeText(ForgetPassword.this,"邮箱不存在",Toast.LENGTH_SHORT).show();
                        else if(Integer.parseInt(result)==0)
                        {
                            Toast.makeText(ForgetPassword.this,"修改成功",Toast.LENGTH_SHORT).show();
                            finish();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(ForgetPassword.this,"注册失败", Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {//在这里封装了需要发送的参数；
                        HashMap<String, String> map = new HashMap<>();
                        map.put("name", editText_address_forgetPassword.getText().toString());//以键值对的形式存放；
                        map.put("password",stringToMD5(editText_password_forgetPassword.getText().toString()));    //将密码转为md5码再传入后台
                        return map;
                    }
                };
                Volley.newRequestQueue(getApplicationContext()).add(request);//加入请求队列；
            }//volleyPost();
        });
        correctKuang.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //点击取消后的事件
                dialogInterface.dismiss();
            }
        });
        //endregion
        correctString="邮箱  ："+editText_address_forgetPassword.getText().toString()+'\n'+"密码  ："+editText_password_forgetPassword.getText().toString()+'\n';
        correctKuang.setMessage(correctString);
        correctKuang.setTitle("请确认");
        correctKuang.show();
    }

    //closeButton_address的监听
    TextWatcher addressTextWatcher = new TextWatcher() {
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            // TODO Auto-generated method stub
        }

        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            // TODO Auto-generated method stub
        }

        public void afterTextChanged(Editable s) {
            if (editText_address_forgetPassword.getText().toString() != null
                    && !editText_address_forgetPassword.getText().toString().equals("")) {
                clearButton_forgetPassword.setVisibility(View.VISIBLE);
            } else {
                clearButton_forgetPassword.setVisibility(View.INVISIBLE);
            }
        }
    };

    //passWordButton的监听
    TextWatcher passWordTextWatcher = new TextWatcher() {
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            // TODO Auto-generated method stub
        }

        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            // TODO Auto-generated method stub
        }

        public void afterTextChanged(Editable s) {
            if (editText_password_forgetPassword.getText().toString() != null
                    && !editText_password_forgetPassword.getText().toString().equals("")) {
                passwordButton_forgetPassword.setVisibility(View.VISIBLE);
            } else {
                passwordButton_forgetPassword.setVisibility(View.INVISIBLE);
            }
        }
    };

    //passWordButtonAgain_address的监听
    TextWatcher passWordAgainTextWatcher = new TextWatcher() {
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            // TODO Auto-generated method stub
        }

        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            // TODO Auto-generated method stub
        }

        public void afterTextChanged(Editable s) {
            if (editText_passwordAgain_forgetPassword.getText().toString() != null
                    && !editText_passwordAgain_forgetPassword.getText().toString().equals("")) {
                passwordAgainButton_forgetPassword.setVisibility(View.VISIBLE);
            } else {
                passwordAgainButton_forgetPassword.setVisibility(View.INVISIBLE);
            }
        }
    };

    //输出错误
    public void printError(String word){
        Toast.makeText(ForgetPassword.this,word, Toast.LENGTH_LONG).show();
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

    class SendEmail {
        public  String myEmailAccount = "sskstudio@163.com";
        public  String myEmailPassword = "sskstudio520";
        public  String myEmailSMTPHost = "smtp.163.com";
        public  String receiveMailAccount = editText_address_forgetPassword.getText().toString();

        public  void send() throws Exception {
            Properties props = new Properties();                    // 参数配置
            props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
            props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
            props.setProperty("mail.smtp.auth", "true");            // 需要请求认证
            Session session = Session.getInstance(props);
            session.setDebug(true);                                 // 设置为debug模式, 可以查看详细的发送 log
            MimeMessage message = createMimeMessage(session, myEmailAccount, receiveMailAccount);
            Transport transport = session.getTransport();
            transport.connect(myEmailAccount, myEmailPassword);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }

        public  MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail) throws Exception {
            randomNum=0;
            for (int i = 0; i <= 100; i++)
            {
                int flag = new Random().nextInt(999999);
                if (flag < 100000)
                {
                    flag += 100000;
                }
                randomNum=flag;
            }
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sendMail, "发件人是我", "UTF-8"));
            message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "这里是测试一号", "UTF-8"));
            message.setSubject("这是来自视时空工作室的一封测试邮件", "UTF-8");
            message.setContent("您收到的验证码是"+randomNum+"", "text/html;charset=UTF-8");
            message.setSentDate(new Date());
            message.saveChanges();
            return message;
        }

    }
}
