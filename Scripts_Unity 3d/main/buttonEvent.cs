using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using System.IO;
using UnityEngine.UI;
using System;

public class buttonEvent : MonoBehaviour
{

    public Camera ARcamera;
    //public Button takePhotoButton;      //拍照功能按钮
    //public Button findNearbyButton;     //发现附近景点按钮
    //public Button virtualGuideButton;   //虚拟导游按钮
    //public Button building3dButton;     //3d建筑按钮
    public GameObject takePhotoGameObject;//拍照功能代码
    public Button[] buttonGather = new Button[4];
    
	public AudioClip CameraWav;//拍照音效

    public Sprite[] image_unclicked = new Sprite[4];
    public Sprite[] image_clicked = new Sprite[4];

    void Start()
    {
        initButtonImg();
    }

    //初始化按钮的颜色
    private void initButtonImg()
    {
        for(int i=0;i<4;i++)
        {
            buttonGather[i].GetComponent<Image>().sprite = image_unclicked[i];
        }
    }

    //点击按钮改变颜色
	private void changeButtonImg(int changeNum)
	{
		for(int i=0;i<4;i++)
		{
			if(i!=changeNum)
				buttonGather[i].GetComponent<Image>().sprite = image_unclicked[i];
			else
			{
				if(buttonGather[i].GetComponent<Image>().sprite == image_clicked[i])
					buttonGather[i].GetComponent<Image>().sprite = image_unclicked[i];
				else
					buttonGather[i].GetComponent<Image>().sprite = image_clicked[i];
			}
		}
	}

    //点击拍照功能按钮事件
    public void takePhotoButtonClicked()
    {
		ARcamera.GetComponent<AudioSource>().Stop();
		ARcamera.GetComponent<AudioSource> ().PlayOneShot (CameraWav);
        changeButtonImg(0);
        takePhotoGameObject.GetComponent<takePhoto>().CaptureCamera(ARcamera, new Rect(0, 0, Screen.width, Screen.height));
        Invoke("initButtonImg",0.2f);
    }

    //点击发现附近景点按钮事件
    public void findNearbyButtonClicked()
    {
        changeButtonImg(1);
    }

    //点击虚拟导游按钮事件
    public void virtualGuideButtonClicked()
    {
        changeButtonImg(2);
    }

    //点击3d建筑按钮事件
    public void building3dButtonClicked()
    {
        changeButtonImg(3);
    }

}
