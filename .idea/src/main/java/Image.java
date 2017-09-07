import com.baidu.aip.imagecensor.AipImageCensor;
import com.baidu.aip.imagecensor.EImgType;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by 蔡琦 on 2017-9-7.
 * 图像审核
 */
public class Image {

    //设置APPID/AK/SK
    public static final String APP_ID = "10100681";//你的 App ID
    public static final String API_KEY = "pLDvpiugYzrBGDhWUSQNrRGe";//你的 Api Key
    public static final String SECRET_KEY = "WPATfhhmTWmXUDm63RhaUiYPaVegNy6R";//你的Secret Key

    public static void main(String[] args) throws IOException {
        AipImageCensor client = new AipImageCensor(APP_ID,API_KEY,SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        //色情识别
        antiPorn(client);

        //GIF色情图像识别
        antiPornGif(client);

        //暴恐图像识别
        antiTerror(client);

        //头像审核
        faceAudit(client);

        //组合审核
        imageCensorComb(client);
    }

    public static void antiPorn(AipImageCensor client) {
        // 参数为本地图片路径
        String imagePath = "F:\\娱乐\\20170906173124.png";
        JSONObject response = client.antiPorn(imagePath);
        System.out.println("色情识别" + response.toString(2));
    }

    public static void antiPornGif(AipImageCensor client) {
        // 参数为本地图片路径
        String imagePath = "F:\\娱乐\\20170906173124.png";
        JSONObject response = client.antiPornGif(imagePath);
        System.out.println("GIF色情图像识别" + response.toString(2));
    }

    public static void antiTerror(AipImageCensor client) {
        // 参数为本地图片路径
        String imagePath = "F:\\娱乐\\20170906173124.png";
        JSONObject response = client.antiTerror(imagePath);
        System.out.println("暴恐图像识别" + response.toString(2));
    }

    public static void faceAudit(AipImageCensor client) {
        // 参数为本地图片路径
        String path1 = "F:\\娱乐\\20170906173124.png";
        String path2 = "F:\\娱乐\\习大大.png";
        JSONObject response = client.faceAudit(Arrays.asList(path1, path2), EImgType.FILE, new HashMap<String, String>());
        System.out.println("头像审核" + response.toString(2));
    }

    public static void imageCensorComb(AipImageCensor client) {
        // 参数为本地图片路径
        String path = "F:\\娱乐\\习大大.png";
        JSONObject response = client.imageCensorComb(path, EImgType.FILE, Arrays.asList("antiporn", "terror", "disgust"), null);
        System.out.println("本地图像组合审核" + response.toString(2));

        // 参数为url
        String url = "http://testurl";
        JSONObject response2 = client.imageCensorComb(url, EImgType.FILE, Arrays.asList("antiporn", "terror", "disgust"), null);
        System.out.println("网络图像组合审核" + response2.toString(2));
    }
}
