using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using System.Collections;

public class SoundRecognition : MonoBehaviour
{
	//public InputField put;//替换成音频
	public AudioClip ShuDianWav;
	public AudioClip JiaoYuanWav;
	public AudioClip MeiYuanWav;
	public AudioClip JiDianWav;
	public AudioClip JianGongWav;
	public AudioClip XiaoShiBoWuGuanWav;
	public AudioClip ShengHuanWav;
	public AudioClip HuaCaiWav;
	public AudioClip BuQingWav;
	public AudioClip NoRecognitionWav;
	public AudioClip Start;
	public GameObject Sounds;

	private string showResult = "";

	public void startSoundRecognition()
	{ 
		AndroidJavaClass jc = new AndroidJavaClass ("com.unity3d.player.UnityPlayer");
		AndroidJavaObject jo = jc.GetStatic<AndroidJavaObject> ("currentActivity"); 
		jo.Call ("StartActivity1");  
		Sounds.GetComponent<AudioSource> ().Stop ();
		Sounds.GetComponent<AudioSource> ().PlayOneShot (Start);
		//string recognize
		Invoke("RecognizeString", 6);

	}

	public void Roderick (string recognizerResult)//android接收对应方法
	{  
		showResult= recognizerResult;  
		//put.text = recognizerResult;
	}

	public void RecognizeString()
	{
		if (showResult.Contains ("数理与电子信息工程学院") || showResult.Contains ("数电")) {
			Sounds.GetComponent<AudioSource> ().Stop ();
			Sounds.GetComponent<AudioSource> ().PlayOneShot (ShuDianWav);
		} else if (showResult.Contains ("教师教育学院") || showResult.Contains ("教院")) {
			Sounds.GetComponent<AudioSource> ().Stop ();
			Sounds.GetComponent<AudioSource> ().PlayOneShot (JiaoYuanWav);
		} else if (showResult.Contains ("美术与设计学院") || showResult.Contains ("美院")) {
			Sounds.GetComponent<AudioSource> ().Stop ();
			Sounds.GetComponent<AudioSource> ().PlayOneShot (MeiYuanWav);
		} else if (showResult.Contains ("机电工程学院") || showResult.Contains ("机电")) {
			Sounds.GetComponent<AudioSource> ().Stop ();
			Sounds.GetComponent<AudioSource> ().PlayOneShot (JiDianWav);
		} else if (showResult.Contains ("建筑工程学院") || showResult.Contains ("建工")) {
			Sounds.GetComponent<AudioSource> ().Stop ();
			Sounds.GetComponent<AudioSource> ().PlayOneShot (JianGongWav);
		} else if (showResult.Contains ("校史博物馆") || showResult.Contains ("校史")) {
			Sounds.GetComponent<AudioSource> ().Stop ();
			Sounds.GetComponent<AudioSource> ().PlayOneShot (XiaoShiBoWuGuanWav);
		} else if (showResult.Contains ("生命与环境科学学院") || showResult.Contains ("生物") || showResult.Contains ("生环")) {
			Sounds.GetComponent<AudioSource> ().Stop ();
			Sounds.GetComponent<AudioSource> ().PlayOneShot (ShengHuanWav);
		} else if (showResult.Contains ("化学与材料工程学院") || showResult.Contains ("化材") || showResult.Contains ("化学")) {
			Sounds.GetComponent<AudioSource> ().Stop ();
			Sounds.GetComponent<AudioSource> ().PlayOneShot (HuaCaiWav);
		} else if (showResult.Contains ("步青学区") || showResult.Contains ("学区")) {
			Sounds.GetComponent<AudioSource> ().Stop ();
			Sounds.GetComponent<AudioSource> ().PlayOneShot (BuQingWav);
		} else {
			Sounds.GetComponent<AudioSource> ().Stop ();
			Sounds.GetComponent<AudioSource> ().PlayOneShot (NoRecognitionWav);
		}
	}


}  