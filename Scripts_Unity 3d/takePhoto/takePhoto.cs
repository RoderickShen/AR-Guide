using System.Collections;
using System.Collections.Generic;
using System.IO;
using UnityEngine;
using UnityEngine.UI;

public class takePhoto : MonoBehaviour {

    public GameObject photoImage;

    public void CaptureCamera(Camera camera, Rect rect)
    {
        System.DateTime now = System.DateTime.Now;
        string times = now.ToString();
        times = times.Trim();
        times = times.Replace("/", "-");
        string filename = "Screenshot" + times + ".png";
        //判断是否为Android平台  
        if (Application.platform == RuntimePlatform.Android)
        {

            // 创建一个RenderTexture对象  
            RenderTexture rt = new RenderTexture((int)rect.width, (int)rect.height, 0);
            // 临时设置相关相机的targetTexture为rt, 并手动渲染相关相机  
            camera.targetTexture = rt;
            camera.Render();
            // 激活这个rt, 并从中中读取像素。  
            RenderTexture.active = rt;
            Texture2D texture = new Texture2D(Screen.width, Screen.height, TextureFormat.RGB24, false);
            texture.ReadPixels(new Rect(0, 0, Screen.width, Screen.height), 0, 0);
            texture.Apply();
            camera.targetTexture = null;  //ps: camera2.targetTexture = null;  
            RenderTexture.active = null; // JC: added to avoid errors  
            GameObject.Destroy(rt);
            //转为字节数组  
            byte[] bytes = texture.EncodeToPNG();
            string destination = "/sdcard/DCIM/ARphoto";
            //判断目录是否存在，不存在则会创建目录  
            if (!Directory.Exists(destination))
            {
                Directory.CreateDirectory(destination);
            }
            string Path_save = destination + "/" + filename;
            //存图片  
            System.IO.File.WriteAllBytes(Path_save, bytes);
            photoImage.SetActive(true);
            photoImage.GetComponent<RawImage>().texture = texture;
            Invoke("photoImageDisappear", 1);
        }
    }

    private void photoImageDisappear()
    {
        photoImage.SetActive(false);
    }
}
