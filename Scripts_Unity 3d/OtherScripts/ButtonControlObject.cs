using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ButtonControlObject : MonoBehaviour {
	public GameObject mic;
	public GameObject virtualGuideText;
	public GameObject building3dText;
	public GameObject PlaneFinder;
	public GameObject GroudPlaneStage;
	public GameObject MidAirStage;
	public GameObject MidAirPositioner;
    public GameObject findNearbyCanvas;
    public GameObject nowPositionText;
    public GameObject IntroduceBackground;

    public GameObject arCamera;
	public GameObject Sounds;//播报景点的喇叭

    private void Start()
    {
        
    }
    public void takePhotoButtonControl(){
        if (mic.activeSelf==true) {
			mic.SetActive(false);
		}
		if (virtualGuideText.activeSelf==true) {
			virtualGuideText.SetActive(false);
		}
		if (building3dText.activeSelf==true) {
			building3dText.SetActive(false);
		}
		if (PlaneFinder.activeSelf==true) {
			PlaneFinder.SetActive(false);
		}
		if (GroudPlaneStage.activeSelf==true) {
			GroudPlaneStage.SetActive(false);
		}
		if (MidAirPositioner.activeSelf==true) {
			MidAirPositioner.SetActive(false);
		} 
		if (MidAirStage.activeSelf==true) {
			MidAirStage.SetActive(false);
		}
        if (findNearbyCanvas.activeSelf == true){
            findNearbyCanvas.SetActive(false);
        }
        if (nowPositionText.activeSelf == true) {
            nowPositionText.SetActive(false);
        }
        if (IntroduceBackground.activeSelf == true)
        {
            IntroduceBackground.SetActive(false);
        }
        if (arCamera.GetComponent<MinimalSensorCamera>().enabled == true)
            arCamera.GetComponent<MinimalSensorCamera>().enabled = false;
        Sounds.GetComponent<AudioSource>().Stop();
	}

	public void findNearbyButtonControl(){
        if (mic.activeSelf==true) {
			mic.SetActive(false);
		}
		if (virtualGuideText.activeSelf==true) {
			virtualGuideText.SetActive(false);
		}
		if (building3dText.activeSelf==true) {
			building3dText.SetActive(false);
		}
		if (PlaneFinder.activeSelf==true) {
			PlaneFinder.SetActive(false);
		}
		if (GroudPlaneStage.activeSelf==true) {
			GroudPlaneStage.SetActive(false);
		}
		if (MidAirPositioner.activeSelf==true) {
			MidAirPositioner.SetActive(false);
		} 
		if (MidAirStage.activeSelf==true) {
			MidAirStage.SetActive(false);
		}
        if (findNearbyCanvas.activeSelf == true)
        {
            findNearbyCanvas.SetActive(false);
        }
        else
            findNearbyCanvas.SetActive(true);
        if (nowPositionText.activeSelf == true)
        {
            nowPositionText.SetActive(false);
        }
        else
            nowPositionText.SetActive(true);
        if (arCamera.GetComponent<MinimalSensorCamera>().enabled == false)
            arCamera.GetComponent<MinimalSensorCamera>().enabled = true;
        else
            arCamera.GetComponent<MinimalSensorCamera>().enabled = false;
        if (IntroduceBackground.activeSelf == true)
        {
            IntroduceBackground.SetActive(false);
        }
        Sounds.GetComponent<AudioSource>().Stop();
	}

	public void building3dButtonControl(){
        if (mic.activeSelf==true) {
			mic.SetActive(false);
		}
		if (virtualGuideText.activeSelf==true) {
			virtualGuideText.SetActive(false);
		}
		if (building3dText.activeSelf == true) {
			building3dText.SetActive (false);
		} else {
			building3dText.SetActive (true);
		}
		if (PlaneFinder.activeSelf==true) {
			PlaneFinder.SetActive(false);
		} else {
			PlaneFinder.SetActive(true);
		}
		if (GroudPlaneStage.activeSelf==true) {
			GroudPlaneStage.SetActive(false);
		} else {
			GroudPlaneStage.SetActive(true);
		}
		if (MidAirPositioner.activeSelf==true) {
			MidAirPositioner.SetActive(false);
		} 
		if (MidAirStage.activeSelf==true) {
			MidAirStage.SetActive(false);
		}
        if (findNearbyCanvas.activeSelf == true)
        {
            findNearbyCanvas.SetActive(false);
        }
        if (nowPositionText.activeSelf == true)
        {
            nowPositionText.SetActive(false);
        }
        if (IntroduceBackground.activeSelf == true)
        {
            IntroduceBackground.SetActive(false);
        }
        if (arCamera.GetComponent<MinimalSensorCamera>().enabled == true)
            arCamera.GetComponent<MinimalSensorCamera>().enabled = false;
        Sounds.GetComponent<AudioSource>().Stop();
	}

	public void virtualGuideButtonControl(){
		if (mic.activeSelf==true) {
			mic.SetActive(false);
		} else {
			mic.SetActive(true);
		}
		if (virtualGuideText.activeSelf == true) {
			virtualGuideText.SetActive (false);
		} else {
			virtualGuideText.SetActive (true);
		}
		if (building3dText.activeSelf==true) {
			building3dText.SetActive(false);
		}
		if (PlaneFinder.activeSelf==true) {
			PlaneFinder.SetActive(false);
		}
		if (GroudPlaneStage.activeSelf==true) {
			GroudPlaneStage.SetActive(false);
		}
		if (MidAirPositioner.activeSelf==true) {
			MidAirPositioner.SetActive(false);
		} else {
			MidAirPositioner.SetActive(true);
		}
		if (MidAirStage.activeSelf==true) {
			MidAirStage.SetActive(false);
		} else {
			MidAirStage.SetActive(true);
		}
        if (findNearbyCanvas.activeSelf == true)
        {
            findNearbyCanvas.SetActive(false);
        }
        if (nowPositionText.activeSelf == true)
        {
            nowPositionText.SetActive(false);
        }
        if (arCamera.GetComponent<MinimalSensorCamera>().enabled == true)
            arCamera.GetComponent<MinimalSensorCamera>().enabled = false;
        if (IntroduceBackground.activeSelf == true)
        {
            IntroduceBackground.SetActive(false);
        }
        Sounds.GetComponent<AudioSource>().Stop();
	}
	public void backButtonControl(){
		Debug.Log ("khkjhkj32222222222");
		AndroidJavaClass jc = new AndroidJavaClass ("com.unity3d.player.UnityPlayer");   
		AndroidJavaObject jo = jc.GetStatic<AndroidJavaObject> ("currentActivity");   
		jo.Call ("onBackPressed");  
	}
}
