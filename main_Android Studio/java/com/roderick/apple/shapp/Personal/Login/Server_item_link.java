package com.roderick.apple.shapp.Personal.Login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.roderick.apple.shapp.R;

/**
 * Created by cqc on 2018/4/1.
 */

public class Server_item_link extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.server_item_link);

            Button back1=(Button)findViewById(R.id.back1);
            back1.setOnClickListener(new MyListener());
        }
    class MyListener implements View.OnClickListener{
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.back1:
                    Server_item_link.this.finish();
                    break;
                default:break;
            }

        }
    }
}
