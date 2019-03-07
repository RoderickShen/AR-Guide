using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class IntroduceStart : MonoBehaviour {
	public GameObject PlaneFinder;
	public GameObject GroudPlaneStage;
	public GameObject GameObjectSelf;
	// Use this for initialization
	void Start () {
		//Invoke ("SetStartActive",2f);
	}
	public void SetStartActive(){
		PlaneFinder.SetActive(false);
		GroudPlaneStage.SetActive(false);
		Destroy (GameObjectSelf);
	}
	// Update is called once per frame
	void Update () {
		
	}
}
