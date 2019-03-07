using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SpeakAnimatorControl : MonoBehaviour {
	public Animator anim;
	public GameObject Sounds;

	void Start () {
		this.anim.SetBool ("Speak",false);
	}

	void Update () {
		if (Sounds.GetComponent<AudioSource> ().isPlaying) {
			this.anim.SetBool ("Speak", true);
		} else {
			this.anim.SetBool ("Speak", false);
		}
	}
}
