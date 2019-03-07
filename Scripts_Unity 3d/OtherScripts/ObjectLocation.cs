using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class ObjectLocation : MonoBehaviour {
	public GameObject LocationObj;
	private float time = 10;
	//public string gps_info = "";  
	//public Text text;
	float x=0.0f;
	float z=0.0f;
	float latitude;
	float longitude;
	void Start () {  
		StartCoroutine(StartGPS());
	}  
		
	void Update () {
		time += Time.deltaTime;
		if (time > 10)
		{
			StartCoroutine(StartGPS());
			time = 0;
			//this.gps_info = "N:" + Input.location.lastData.latitude + " E:"+Input.location.lastData.longitude; 
			latitude = (float)Input.location.lastData.latitude;
			longitude = (float)Input.location.lastData.longitude;
			//text.text ="N:" + Input.location.lastData.latitude + " E:"+Input.location.lastData.longitude;
			x = (float)(System.Convert.ToDouble(-(latitude-27.921984) *9053.3297797));
			z = (float)(System.Convert.ToDouble((longitude - 120.689801) * 6669.8727984));
			LocationObj.transform.localPosition = new Vector3 (x,0.5f,z);
			//text.text = text.text +"@"+ x +":::"+ z;
		}

	}
		
	IEnumerator StartGPS () {  
		// Input.location 用于访问设备的位置属性（手持设备）, 静态的LocationService位置  
		// LocationService.isEnabledByUser 用户设置里的定位服务是否启用  
		if (!Input.location.isEnabledByUser) {  
			//this.gps_info = "isEnabledByUser value is:"+Input.location.isEnabledByUser.ToString()+" Please turn on the GPS";   
			//return false;  
		}  

		// LocationService.Start() 启动位置服务的更新,最后一个位置坐标会被使用  
		Input.location.Start(10.0f, 10.0f);
		//this.gps_info = "N:" + Input.location.lastData.latitude + " E:" + Input.location.lastData.longitude;
		int maxWait = 20;  
		while (Input.location.status == LocationServiceStatus.Initializing && maxWait > 0) {
			// 暂停协同程序的执行(1秒)  
			yield return new WaitForSeconds(1);  
			maxWait--;  
		}  

		if (maxWait < 1) {  
			//this.gps_info = "Init GPS service time out";  
			//return false;  
		}  

		if (Input.location.status == LocationServiceStatus.Failed) {  
			//this.gps_info = "Unable to determine device location";  
			//return false;  
		}   
		else {  
			//this.gps_info = "N:" + Input.location.lastData.latitude + " E:"+Input.location.lastData.longitude;
			//this.gps_info = this.gps_info + " Time:" + Input.location.lastData.timestamp;
			yield return new WaitForSeconds(1);  
		}  
	}
}
