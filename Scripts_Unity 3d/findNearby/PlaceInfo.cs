
public class PlaceInfo
{

    public string Name;
    /// <summary>
    /// 纬度
    /// </summary>
    public double Latitude;
    /// <summary>
    /// 经度
    /// </summary>
    public double Longitude;

    public double Distance;

    public string IntroduceString;

    public PlaceInfo(string Name,double Latitude,double Longitude,string IntroduceString)
    {
        this.Name = Name;
        this.Latitude = Latitude;
        this.Longitude = Longitude;
        this.IntroduceString = IntroduceString;
    }
}
