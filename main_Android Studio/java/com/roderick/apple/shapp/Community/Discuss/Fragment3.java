package com.roderick.apple.shapp.Community.Discuss;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.roderick.apple.shapp.More.WebViewActivity;
import com.roderick.apple.shapp.Personal.Login.Login_Message;
import com.roderick.apple.shapp.Personal.Login.Login_interface;
import com.roderick.apple.shapp.Personal.NoteBook.NoteModel;
import com.roderick.apple.shapp.Personal.NoteBook.ShowNoteActivity;
import com.roderick.apple.shapp.Personal.PersonalActivity;
import com.roderick.apple.shapp.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by apple on 18/2/24.
 */

public class Fragment3 extends Fragment implements AdapterView.OnItemClickListener,SwipeRefreshLayout.OnRefreshListener{
    @Nullable

    private FloatingActionButton btn_message;
    private SwipeRefreshLayout messageRefresh;
    private ListView momentList;
    private MessageAdapter messageAdapter;
    private List<MessageModel> messageModelList;
    public final String GET_MESSAGE_URL ="http://www.sskstudio.cn:8000/myApp/ShowMessage/";
    public String MessageJSONArray="";
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        View rootView = inflater.inflate( R.layout.community_discuss, container, false );
        btn_message=(FloatingActionButton)rootView.findViewById(R.id.fab);
        messageModelList = new ArrayList<MessageModel>();
        momentList =(ListView)rootView.findViewById(R.id.MomentList);
        messageAdapter = new MessageAdapter(this,messageModelList );
        momentList.setAdapter( messageAdapter );
        momentList.setOnItemClickListener(this);



        btn_message.setOnClickListener(new View.OnClickListener() {//编写message
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),WriteMessageActivity.class));
            }
        });



        RequestQueue mQueue = Volley.newRequestQueue( getContext() );
        StringRequest request = new StringRequest(  GET_MESSAGE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String result) {
                MessageJSONArray = result;
                ShowMessageList( MessageJSONArray );
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               // Toast.makeText( getActivity(),"加载错误！", Toast.LENGTH_LONG ).show();
            }
        } );
        mQueue.add( request );//加入请求队列；

        messageRefresh=rootView.findViewById( R.id.grouptour_refresh);
        messageRefresh.setOnRefreshListener( this );
//

        return rootView;
    }
    public void ShowMessageList(String TourGroupJSONArray){
        messageModelList.clear();
        int jsonArrayLenth=0,i=0;
        try {
            JSONArray jsonArray=new JSONArray(TourGroupJSONArray);
            jsonArrayLenth=jsonArray.length();
            for(;i<jsonArrayLenth;i++){
                JSONObject object=jsonArray.getJSONObject(i);
                String user=object.getString("nickname");

                String content=object.getString("content");
                String headprotraitUrl=object.getString("headprotrait_url");
                messageModelList.add(new MessageModel(content,headprotraitUrl,user));
            }
            messageAdapter.notifyDataSetChanged();//通知适配器更新
        }catch (Exception e){
            e.printStackTrace();
        }
    }

   @Override
   public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

       ImageView love=(ImageView)view.findViewById(R.id.love);
       if(love.getDrawable().getCurrent().getConstantState().equals(getResources().getDrawable(R.drawable.love1).getConstantState()))
           love.setImageResource(R.drawable.love2);
       else
           love.setImageResource(R.drawable.love1);
   }

    @Override
    public void onRefresh() {
        mHandler.postDelayed( mRefresh,2000 );
    }
    private Handler mHandler=new Handler(  );
    private Runnable mRefresh=new Runnable() {
        @Override
        public void run() {
            //Volley获取JSON字符串
            RequestQueue mQueue = Volley.newRequestQueue( getContext() );
            StringRequest stringRequest = new StringRequest( GET_MESSAGE_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String s) {
                    MessageJSONArray = s;
                    ShowMessageList( MessageJSONArray );
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //Log.i("error>", error.toString());
                    Toast toast = Toast.makeText( getContext(), "网络加载错误", Toast.LENGTH_SHORT );
                    toast.setGravity( Gravity.CENTER | Gravity.CENTER, 0, 100 );
                    toast.show();
                }
            } );
            mQueue.add( stringRequest );
            messageRefresh.setRefreshing( false );
        }
    };
}
