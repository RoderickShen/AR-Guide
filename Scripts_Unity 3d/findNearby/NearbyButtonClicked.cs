using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class NearbyButtonClicked : MonoBehaviour {


    public GameObject getNameText;
    public GameObject findNearbyButtonControl;

    private void Start()
    {
        findNearbyButtonControl = GameObject.Find("findNearbyButtonControl");
    }
    public void NearbyClicked()
    {
        string name = getNameText.GetComponent<Text>().text;

        findNearbyButtonControl.GetComponent<FindNearbyButtonControl>().GoIntroduce(name);
        
    }
}
