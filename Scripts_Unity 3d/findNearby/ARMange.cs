using UnityEngine;
using System.Collections;
using System.Collections.Generic;
using UnityEngine.UI;
using System;

public class ARMange : MonoBehaviour
{

    public List<PlaceInfo> places = new List<PlaceInfo>();
    public GameObject PlaceGather;
    public GameObject perfab;
    public GameObject positionText;
    public GameObject distanceText;
    private const double EARTH_RADIUS = 6378137;  //地球半径

    private void Awake()
    {
        places = GetComponent<PlaceGather>().places;
    }

    public void ShowPlaces(double nowLatitude, double nowLongitude)
    {
        ClearPlace();

        for (int i = 0; i < places.Count; i++)
        {
            GameObject newPlace = Instantiate<GameObject>(perfab);
            newPlace.transform.parent = this.transform;
            newPlace.name = places[i].Name;

            double posZ = places[i].Latitude - nowLatitude;
            double posX = places[i].Longitude - nowLongitude;
            double distance = getDistance(nowLatitude, nowLongitude, places[i].Latitude, places[i].Longitude);
            float z = 0;
            float x = 0;

            if (posZ > 0)
            {
                z = 1000f;
            }
            else
            {
                z = -1000f;
            }

            if (posX > 0)
            {
                x = 1000f;
            }
            else
            {
                x = -1000f;
            }
			z = (float)(posZ * 500000)+z;
			x = (float)(posX * 500000)+x;
            newPlace.transform.localScale = new Vector3(4f, 4f, 4f);
            newPlace.transform.localPosition = new Vector3(x, i * 300 - 1400, z);//设置Marker
            newPlace.transform.LookAt(this.transform);
            positionText.GetComponent<Text>().text = places[i].Name;

            String distanceTextString = "Distance:  " + ((int)distance).ToString() + "m";
            distanceText.gameObject.GetComponentInChildren<Text>().text = distanceTextString;
        }
    }

    private void ClearPlace()
    {
        GameObject[] oldPlaces = GameObject.FindGameObjectsWithTag("Place");
        for (int i = 0; i < oldPlaces.Length; i++)
        {
            Destroy(oldPlaces[i].gameObject);
        }
    }

    /// <summary>
    /// 计算两点位置的距离，返回两点的距离，单位 米
    /// </summary>
    /// <param name="lat1">第一点纬度</param>
    /// <param name="lng1">第一点经度</param>
    /// <param name="lat2">第二点纬度</param>
    /// <param name="lng2">第二点经度</param>
    /// <returns></returns>
    public static double getDistance(double lat1, double lng1, double lat2, double lng2)
    {
        double radLat1 = Rad(lat1);
        double radLng1 = Rad(lng1);
        double radLat2 = Rad(lat2);
        double radLng2 = Rad(lng2);
        double a = radLat1 - radLat2;
        double b = radLng1 - radLng2;
        double result = 2 * Math.Asin(Math.Sqrt(Math.Pow(Math.Sin(a / 2), 2) + Math.Cos(radLat1) * Math.Cos(radLat2) * Math.Pow(Math.Sin(b / 2), 2))) * EARTH_RADIUS;
        return result;
    }

    /// <summary>
    /// 经纬度转化成弧度
    /// </summary>
    /// <param name="d"></param>
    /// <returns></returns>
    private static double Rad(double d)
    {
        return (double)d * Math.PI / 180d;
    }

}
