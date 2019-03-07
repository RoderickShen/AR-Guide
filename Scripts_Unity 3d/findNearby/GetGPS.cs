using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
public class GetGPS : MonoBehaviour
{
    string GetGps = "";
    public Text ShowGPS;
    private float time = 0;

    public GameObject ARMangeGameObject;


    // 初始化一次位置  
    void Start()
    {
        StartCoroutine(StartGPS());
        ShowGPS.text = "Getting GPS,please wait...";
        //GetGps = "N:" + Input.location.lastData.latitude + " E:" + Input.location.lastData.longitude;
        //GetGps = GetGps + " Time:" + Input.location.lastData.timestamp;
        //ShowGPS.text = GetGps;
    }

    private void Update()
    {
        time += Time.deltaTime;
        if (time > 5)
        {
            StartCoroutine(StartGPS());
            time = 0;
        }
        
        //GetGps = "N:" + Input.location.lastData.latitude + " E:" + Input.location.lastData.longitude;
        //GetGps = GetGps + " Time:" + Input.location.lastData.timestamp;
        // GoARMange(Input.location.lastData.latitude, Input.location.lastData.longitude);
        //ShowGPS.text = GetGps;
    }

    // 停止刷新位置（节省手机电量）  
    void StopGPS()
    {
        Input.location.Stop();
    }

    IEnumerator StartGPS()
    {
        // Input.location 用于访问设备的位置属性（手持设备）, 静态的LocationService位置    
        // LocationService.isEnabledByUser 用户设置里的定位服务是否启用  
        if (!Input.location.isEnabledByUser)
        {
            GetGps = "isEnabledByUser value is:" + Input.location.isEnabledByUser.ToString() + " Please turn on the GPS";
            yield return false;
        }
        // LocationService.Start() 启动位置服务的更新,最后一个位置坐标会被使用    
        Input.location.Start(10f, 10f);
        int maxWait = 20;
        while (Input.location.status == LocationServiceStatus.Initializing && maxWait > 0)
        {
            // 暂停协同程序的执行(1秒)    
            yield return new WaitForSeconds(1);
            maxWait--;
        }
        if (maxWait < 1)
        {
            GetGps = "Init GPS service time out";
            yield return false;
        }
        if (Input.location.status == LocationServiceStatus.Failed)
        {
            GetGps = "Unable to determine device location";
            yield return false;
        }
        else
        {
            GetGps = "N:" + Input.location.lastData.latitude + " E:" + Input.location.lastData.longitude;
            ShowGPS.text = GetGps;
            GoARMange(Input.location.lastData.latitude, Input.location.lastData.longitude);
            yield return new WaitForSeconds(5);
        }
    }

    private void GoARMange(double nowLatitude, double nowLongitude)
    {
        ARMangeGameObject.GetComponent<ARMange>().ShowPlaces(nowLatitude, nowLongitude);
    }
}