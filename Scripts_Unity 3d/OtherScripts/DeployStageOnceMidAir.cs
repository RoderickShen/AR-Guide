using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using System;
using Vuforia;

public class DeployStageOnceMidAir : MonoBehaviour {

	public GameObject AnchorStage;
	private PositionalDeviceTracker _deviceTracker;
	private GameObject _previousAnchor;

	public void Start ()
	{
		if (AnchorStage == null)
		{
			Debug.Log("AnchorStage must be specified");
			return;
		}

		AnchorStage.SetActive(false);
	}

	public void Awake()
	{
		VuforiaARController.Instance.RegisterVuforiaStartedCallback(OnVuforiaStarted);
	}

	public void OnDestroy()
	{
		VuforiaARController.Instance.UnregisterVuforiaStartedCallback(OnVuforiaStarted);
	}

	private void OnVuforiaStarted()
	{
		_deviceTracker = TrackerManager.Instance.GetTracker<PositionalDeviceTracker>();
	}

	public void PlaceObjectInMidAir(Transform midAirTransform)
	{
		if (midAirTransform == null || AnchorStage == null)
		{
			Debug.LogWarning("Hit test is invalid or AnchorStage not set");
			return;
		}

		var anchor = _deviceTracker.CreateMidAirAnchor(Guid.NewGuid().ToString(),midAirTransform);

		if (anchor != null)
		{
			AnchorStage.transform.parent = anchor.transform;
			AnchorStage.transform.localPosition = Vector3.zero;
			AnchorStage.transform.localRotation = Quaternion.identity;
			AnchorStage.SetActive(true);
		}

		if (_previousAnchor != null)
		{
			Destroy(_previousAnchor);
		}

		_previousAnchor = anchor;
	}

	/*
	public void HandleAutomaticHitTest(HitTestResult result)
	{
		AutomaticHitTestFrameCount = Time.frameCount;

		if (!m_PlacementToggle.interactable)
		{
			// Runs only once after first successful Automatic hit test
			m_PlacementToggle.interactable = true;
			m_GroundToggle.interactable = true;
			// Make the PlacementToggle active
			m_PlacementToggle.isOn = true;
		}

		if (planeMode == PlaneMode.PLACEMENT && !m_PlacementAugmentation.activeInHierarchy)
		{
			SetSurfaceIndicatorVisible(false);
			m_PlacementPreview.SetActive(true);
			m_PlacementPreview.PositionAt(result.Position);
			RotateTowardCamera(m_PlacementPreview);
		}
		else
		{
			m_PlacementPreview.SetActive(false);
		}
	}


	public void PlaceObjectInMidAir(Transform midAirTransform)
	{
		Debug.Log("PlaceObjectInMidAir() called.");

		if (planeMode != PlaneMode.MIDAIR)
		{
			Debug.Log("Invalid Ground Plane Mode:" + planeMode);
			return;
		}

		if (m_PositionalDeviceTracker != null && m_PositionalDeviceTracker.IsActive)
		{
			DestroyAnchors();

			m_MidAirAnchor = m_PositionalDeviceTracker.CreateMidAirAnchor("MyMidAirAnchor_" + (++m_AnchorCounter), midAirTransform);
			m_MidAirAnchor.name = "MidAirAnchor";

			if (!m_MidAirAugmentation.activeInHierarchy)
			{
				Debug.Log("Setting Mid-Air Augmentation to Active");
				// On initial run, unhide the augmentation
				m_MidAirAugmentation.SetActive(true);
			}

			Debug.Log("Positioning Mid-Air Augmentation at: " + midAirTransform.position.ToString());
			// parent the augmentation to the anchor
			m_MidAirAugmentation.transform.SetParent(m_MidAirAnchor.transform);
			m_MidAirAugmentation.transform.localPosition = Vector3.zero;
			RotateTowardCamera(m_MidAirAugmentation);
		}
	}*/
}
	