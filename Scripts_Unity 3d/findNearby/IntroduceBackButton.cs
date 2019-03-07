using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class IntroduceBackButton : MonoBehaviour {

    public GameObject IntroduceBackground;

	public void IntroduceBackButtonClicked()
    {
        IntroduceBackground.SetActive(false);
    }
}
