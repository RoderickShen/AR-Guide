using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class FindNearbyButtonControl : MonoBehaviour {

    public GameObject introduceBackground;
    public GameObject sceneNameText;
    public GameObject IntroduceText;
    public List<PlaceInfo> places = new List<PlaceInfo>();

    public void GoIntroduce(string name)
    {
        introduceBackground.SetActive(true);
        sceneNameText.GetComponent<Text>().text = name;
        places = GetComponent<PlaceGather>().places;
        for (int i = 0; i < places.Count; i++)
        {
            if (name.Equals(places[i].Name))
            {
                IntroduceText.GetComponent<Text>().text = places[i].IntroduceString;
                break;
            }
        }
    }
}
