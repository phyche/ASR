import com.baidu.aip.face.AipFace;
import com.baidu.aip.speech.AipSpeech;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 蔡琦 on 2017-9-6.
 * 人脸识别
 */
public class Face {

    //设置APPID/AK/SK
    public static final String APP_ID = "10100681";//你的 App ID
    public static final String API_KEY = "pLDvpiugYzrBGDhWUSQNrRGe";//你的 Api Key
    public static final String SECRET_KEY = "WPATfhhmTWmXUDm63RhaUiYPaVegNy6R";//你的Secret Key

    public static void main(String[] args) throws IOException {
        //初始化一个FaceClient
        // 初始化一个FaceClient
        AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 调用API
        faceRecognize(client);
    }

    public static void faceRecognize(AipFace client) {
        // 自定义参数定义
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("max_face_num", "1");
        options.put("face_fields", "expression");

        // 参数为本地图片路径
        String imagePath = "F:\\a.jpg";
        JSONObject response = client.detect(imagePath, options);
        System.out.println(response.toString());

        // 参数为本地图片文件二进制数组
        /*byte[] file = readImageFile(imagePath);
        JSONObject response = client.detect(file, options);
        System.out.println(response.toString());*/

        // 参数为本地图片路径
        String imagePath1 = "F:\\a.jpg";
        String imagePath2 = "F:\\test.jpg";
        ArrayList<String> pathArray = new ArrayList<String>();
        pathArray.add(imagePath1);
        pathArray.add(imagePath2);
        response = client.match(pathArray, new HashMap<String, String>());
        System.out.println(response.toString());

    }
}
