package com.roderick.apple.shapp.Main;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Region;
import android.os.SystemClock;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.roderick.apple.shapp.Personal.Login.Login_Message;
import com.roderick.apple.shapp.R;
import com.ssk.unityARGuide.Speech;
import com.ssk.unityARGuide.UnityPlayerActivity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private ListView listItem_main;
    private ScrollView scrollView;
    private TextView WenZhouUniversity;


    private android.support.v7.widget.SearchView searchView;
    //private ListView listView_scenery;
    //private final String[] mStrings={"温州大学","江心屿","温州小学"};
    //private ArrayAdapter sceneryAdapt;
    //private String search_text;     //适配器中点击返回的text
    //private EditText search_textView;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        //region 加载登录状态
        String content="";
        FileInputStream fis;
        try{
            fis=openFileInput("data.txt");
            byte[] buffer=new byte[fis.available()];
            fis.read(buffer);
            content=new String(buffer);
            String[] userInfo=content.split("##");
            Login_Message.address=userInfo[0];
            Login_Message.state=Integer.parseInt(userInfo[1]);
            if(Login_Message.state==1){
                Login_Message.nickname=userInfo[2];
                Login_Message.sex=userInfo[3];
                Login_Message.headprotrait_url=userInfo[4];
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //endregion
        //sceneryAdapt=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mStrings);
        //listView_scenery.setAdapter(sceneryAdapt);
        //listView_scenery.setOnItemClickListener(this);
    }

    private void initView() {
        scrollView=(ScrollView)findViewById(R.id.scrollView);
        OnClickTextViewListener onClickTextViewListener=new OnClickTextViewListener();
        WenZhouUniversity=(TextView)findViewById(R.id.photo_WenZhouUniversity);
        WenZhouUniversity.setOnClickListener(onClickTextViewListener);
    }

    /*
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        search_text=listView_scenery.getItemAtPosition(i)+"";   //点击listview获得的文字
        //if(searchView==null)
            //return;
Toast.makeText(this,search_text, Toast.LENGTH_SHORT);
        int id = searchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        search_textView = (EditText)searchView.findViewById(id);
        if(search_textView==null)
            Toast.makeText(this,"111",Toast.LENGTH_SHORT);
        //search_textView.setText("1111");
    }*/

    public class OnClickTextViewListener implements View.OnClickListener
    {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.photo_WenZhouUniversity:
                    showExitDialog("确认进入温州大学AR导游");
                    break;

            }
        }
    }

    // 带“是”和“否”的提示框
    private void showExitDialog(String msg){
        new AlertDialog.Builder(this)
                .setTitle("进入AR导游")
                .setMessage(msg)
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent=new Intent(MainActivity.this, Speech.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }

    //region 2次后退退出程序
    private long clickTime=0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (SystemClock.uptimeMillis() - clickTime <= 1500) {
                //如果两次的时间差＜1s，就不执行操作
            } else {
                //当前系统时间的毫秒值
                clickTime = SystemClock.uptimeMillis();
                Toast.makeText(MainActivity.this, "再次点击退出", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
    //endregion


}
