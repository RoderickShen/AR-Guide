using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class FollowARCamera : MonoBehaviour {
	public GameObject ARCamera;
	// Use this for initialization
	void Start () {
		this.transform.position = ARCamera.transform.position;
	}
	
	// Update is called once per frame
	void Update () {
		this.transform.position = ARCamera.transform.position;
	}
}
