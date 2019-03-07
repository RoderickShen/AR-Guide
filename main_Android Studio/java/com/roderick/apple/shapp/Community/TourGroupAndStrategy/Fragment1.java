package com.roderick.apple.shapp.Community.TourGroupAndStrategy;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.roderick.apple.shapp.More.WebViewActivity;
import com.roderick.apple.shapp.Personal.NoteBook.NoteBookActivity;
import com.roderick.apple.shapp.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by apple on 18/2/24.
 */

public class Fragment1 extends Fragment implements AdapterView.OnItemClickListener,SwipeRefreshLayout.OnRefreshListener{

    private SwipeRefreshLayout tourGroupRefresh;
    private ListView tourGroupList;
    private TourGroupAdapter adapter;
    private List<TourGroupModel> tourGroupModelList;
    public final String GET_TOURGROUP_URL = "http://www.sskstudio.cn:8000/myApp/getTourGroupData/";
    public String TourGroupJSONArray="";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        View rootView = inflater.inflate( R.layout.community_grouptour, container, false );
        tourGroupModelList = new ArrayList<TourGroupModel>();
        tourGroupList = rootView.findViewById( R.id.tourGroupList );
        adapter = new TourGroupAdapter( this, tourGroupModelList );
        tourGroupList.setAdapter( adapter );
        tourGroupList.setOnItemClickListener( this );

        //Volley获取JSON字符串
        RequestQueue mQueue = Volley.newRequestQueue( getContext() );
        StringRequest stringRequest = new StringRequest( GET_TOURGROUP_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                TourGroupJSONArray = s;
                ShowTouGroupList( TourGroupJSONArray );
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

        tourGroupRefresh=rootView.findViewById( R.id.grouptour_refresh);
        tourGroupRefresh.setOnRefreshListener( this );

        return rootView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TourGroupModel tourGroupModel=tourGroupModelList.get(position);
        Intent intent=new Intent(getActivity(),WebViewActivity.class);
        intent.putExtra("content_url",tourGroupModel.getContent_url());
        startActivity(intent);
    }

    /***
     * 把JSON字符串转化成相应显示的团游ListItem
     * @param TourGroupJSONArray
     */
    public void ShowTouGroupList(String TourGroupJSONArray){
        tourGroupModelList.clear();
        int jsonArrayLenth=0,i=0;
        try {
            JSONArray jsonArray=new JSONArray(TourGroupJSONArray);
            jsonArrayLenth=jsonArray.length();
            for(;i<jsonArrayLenth;i++){
                JSONObject object=jsonArray.getJSONObject(i);
                String title=object.getString("title");
                String desc=object.getString("desc");
                String picture=object.getString("picture");
                String content_url=object.getString("content_url");
                tourGroupModelList.add(new TourGroupModel(title,desc,picture,content_url));
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
            //Volley获取JSON字符串
            RequestQueue mQueue = Volley.newRequestQueue( getContext() );
            StringRequest stringRequest = new StringRequest( GET_TOURGROUP_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String s) {
                    TourGroupJSONArray = s;
                    ShowTouGroupList( TourGroupJSONArray );
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
            tourGroupRefresh.setRefreshing( false );
        }
    };
}
