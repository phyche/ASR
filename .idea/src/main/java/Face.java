import com.baidu.aip.face.AipFace;
import com.baidu.aip.speech.AipSpeech;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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

        // 人脸检测
        //faceRecognize(client);

        //人脸识别
        identifyUser(client);

        //人脸注册
        //facesetAddUser(client);

        //人脸认证
        verifyUser(client);

        //人脸更新
        //facesetUpdateUser(client);

        //用户信息查询
        getUser(client);

    }

    public static void faceRecognize(AipFace client) {
        // 自定义参数定义
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("max_face_num", "1");
        options.put("face_fields", "expression");

        // 参数为本地图片路径
        String imagePath = "F:\\a.jpg";
        JSONObject response = client.detect(imagePath, options);
        System.out.println("参数为本地图片路径:" + response.toString());

        // 参数为本地图片文件二进制数组
        /*byte[] file = readImageFile(imagePath);
        JSONObject response = client.detect(file, options);
        System.out.println("参数为本地图片文件二进制数组:" + response.toString());*/

        // 参数为本地图片路径
        String imagePath1 = "F:\\a.jpg";
        String imagePath2 = "F:\\娱乐\\20170906173124.png";
        ArrayList<String> pathArray = new ArrayList<String>();
        pathArray.add(imagePath1);
        pathArray.add(imagePath2);
        response = client.match(pathArray, new HashMap<String, String>());
        System.out.println("参数为本地图片路径:" + response.toString());

    }

    public static void identifyUser(AipFace client) {
        String path = "F:\\娱乐\\驾驶证.png";
        HashMap<String, Object> options = new HashMap<String, Object>(1);
        options.put("user_top_num", 1);
        JSONObject res = client.identifyUser(Arrays.asList("group1", "group2"), path, options);
        System.out.println("人脸识别" + res.toString(2));
    }

    //返回{"log_id": 2191026935090713}
    public static void facesetAddUser(AipFace client) {
        // 参数为本地图片路径
        String path = "F:\\娱乐\\20170906173124.png";
        HashMap<String, String> options = new HashMap<String, String>();
        JSONObject res = client.addUser("uid1", "test_user_info", Arrays.asList("group1", "group2"), path, options);
        System.out.println("人脸注册" + res.toString(2));
    }

    /**
     * {
            "result": [
            45.72191619873,
            45.72191619873,
            45.72191619873,
            45.72191619873
            ],
            "log_id": 2224991011090714,
            "result_num": 4
        }
    */
    public static void verifyUser(AipFace client) {
        String path = "F:\\娱乐\\20170906173124.png";
        HashMap<String, Object> options = new HashMap<String, Object>(1);
        options.put("top_num", 5);
        JSONObject res = client.verifyUser("uid1", Arrays.asList("group1", "group2"), path, options);
        System.out.println("人脸验证" + res.toString(2));
    }

    public static void facesetUpdateUser(AipFace client) {
        // 参数为本地图片路径
        String path = "F:\\娱乐\\驾驶证.png";
        HashMap<String, String> options = new HashMap<String, String>();
        JSONObject res = client.updateUser("uid1", "user_info_memo", "group1", path, options);
        System.out.println("人脸更新" + res.toString(2));
    }

    public static void getUser(AipFace client) {
        // 查询一个用户在所有组内的信息
        JSONObject res = client.getUser("uid1");
        System.out.println("获取所有组内人脸信息" + res.toString(2));

        // 查询一个用户在指定组内的信息
        JSONObject res2 = client.getUser("uid1", Arrays.asList("group1"));
        System.out.println("获取指定组人脸信息" + res2.toString(2));
    }
}
