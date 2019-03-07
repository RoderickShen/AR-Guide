package com.roderick.apple.shapp.More;

import android.app.ActivityGroup;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.roderick.apple.shapp.Community.CommunityActivity;
import com.roderick.apple.shapp.Main.MainActivity;
import com.roderick.apple.shapp.Personal.PersonalActivity;
import com.roderick.apple.shapp.R;

/**
 * Created by apple on 18/2/23.
 */

public class TabGroupActivity extends ActivityGroup implements View.OnClickListener{
    private static final String TAG="TabGroupActicity";
    private Bundle mBundle=new Bundle();
    private LinearLayout ll_container,ll_first,ll_second,ll_third;

    private TextView text_main;
    private TextView text_community;
    private TextView text_personal;

    private ImageView img_main;
    private ImageView img_community;
    private ImageView img_personal;

    long exitTime=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        initView();
        ll_first.setOnClickListener(this);
        ll_second.setOnClickListener(this);
        ll_third.setOnClickListener(this);
        mBundle.putString("tag",TAG);
        changeContainerView(ll_first);
    }

    private void initView()
    {
        ll_container=(LinearLayout)findViewById(R.id.ll_container);
        ll_first=(LinearLayout)findViewById(R.id.ll_first);
        ll_second=(LinearLayout)findViewById(R.id.ll_second);
        ll_third=(LinearLayout)findViewById(R.id.ll_third);
        text_main=(TextView)findViewById(R.id.text_main);
        text_community=(TextView)findViewById(R.id.text_community);
        text_personal=(TextView)findViewById(R.id.text_personal);
        img_main=findViewById(R.id.img_main);
        img_community=findViewById(R.id.img_community);
        img_personal=findViewById(R.id.img_personal);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.ll_first||view.getId()==R.id.ll_second||view.getId()==R.id.ll_third){
            changeContainerView(view);
        }
    }
    private void changeContainerView(View view) {
        resetView();
        view.setSelected(true);
        int lightBlue=Color.argb(255,0,191,255);
        if(view==ll_first){
            toActivity("first",MainActivity.class);
            text_main.setTextColor(lightBlue);
            img_main.setImageResource(R.drawable.home_press);
        }else if(view==ll_second){
            toActivity("second",CommunityActivity.class);
            text_community.setTextColor(lightBlue);
            img_community.setImageResource(R.drawable.community_press);
        }else if(view==ll_third){
            toActivity("third",PersonalActivity.class);
            text_personal.setTextColor(lightBlue);
            img_personal.setImageResource(R.drawable.mine_press);
        }
    }

    private void toActivity(String label, Class<?>cls) {
        Intent intent=new Intent(this,cls).putExtras(mBundle);
        ll_container.removeAllViews();
        View view=getLocalActivityManager().startActivity(label,intent).getDecorView();
        view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        ll_container.addView(view);
    }

    private void resetView()
    {
        ll_first.setSelected(false);
        ll_second.setSelected(false);
        ll_third.setSelected(false);
        text_main.setTextColor(Color.BLACK);
        text_community.setTextColor(Color.BLACK);
        text_personal.setTextColor(Color.BLACK);
        img_main.setImageResource(R.drawable.home);
        img_community.setImageResource(R.drawable.community);
        img_personal.setImageResource(R.drawable.mine);
    }
    /*
    重写返回键：按两次退出
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN)
        {

            if((System.currentTimeMillis()-exitTime) > 2000)  //System.currentTimeMillis()无论何时调用，肯定大于2000
            {
                Toast.makeText(getApplicationContext(), "再按一次退出程序",Toast.LENGTH_SHORT).show();
                 exitTime = System.currentTimeMillis();
            }
            else
            {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
