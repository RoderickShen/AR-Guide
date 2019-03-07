using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SimpleActionForGuide : MonoBehaviour {
	int i=0;
	// Use this for initialization
	void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {
		//让物体上下飘动
		if (i < 871) {
			this.transform.Translate (Vector3.up*Time.deltaTime*0.06f);
			i+=30;
		}else {
			if (i == 871) {
				i = 1;
			}
			this.transform.Translate (Vector3.down*Time.deltaTime*0.06f);
			i-=1;
		}

		//滑动屏幕转动物体
		if (Input.touchCount == 1) {
			if (Input.touches [0].phase == TouchPhase.Moved) {//手指在屏幕上移动
				float s01 = Input.GetAxis ("Mouse X");
				this.transform.Rotate (0, -5 * s01, 0);
			}
		}
	}
}
