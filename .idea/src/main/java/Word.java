import com.baidu.aip.ocr.AipOcr;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by 蔡琦 on 2017-9-6.
 * 文字识别
 */
public class Word {
    //设置APPID/AK/SK
    public static final String APP_ID = "10100681";//你的 App ID
    public static final String API_KEY = "pLDvpiugYzrBGDhWUSQNrRGe";//你的 Api Key
    public static final String SECRET_KEY = "WPATfhhmTWmXUDm63RhaUiYPaVegNy6R";//你的Secret Key

    public static void main(String[] args) {
        // 初始化一个OcrClient
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 调用身份证识别接口
        String idFilePath = "F:\\娱乐\\20170906173124.png";
        JSONObject idcardRes = client.idcard(idFilePath, true,new HashMap<String, String>());
        System.out.println("调用身份证识别接口" + idcardRes.toString(2));

        // 调用银行卡识别接口
        String bankFilePath = "F:\\娱乐\\银行卡.png";
        JSONObject bankRes = client.bankcard(bankFilePath);
        System.out.println("调用银行卡识别接口:" + bankRes.toString(2));

        // 调用通用识别接口
        JSONObject genRes = client.basicGeneral(idFilePath, new HashMap<String, String>());
        System.out.println("调用通用识别接口:" + genRes.toString(2));


        // 调用通用识别（含位置信息）接口
        JSONObject genRes2 = client.general(idFilePath, new HashMap<String, String>());
        System.out.println("调用通用识别（含位置信息）接口:" + genRes2.toString(2));

        // 调用通用识别接口(生僻字)暂不支持使用
        JSONObject genRes3 = client.enhancedGeneral(idFilePath,new HashMap<String, String>());
        System.out.println("调用通用识别含生僻字接口:" + genRes3.toString(2));

        // 调用通用识别接口(高精度)
        JSONObject genRes4 = client.basicAccurateGeneral(idFilePath,new HashMap<String, String>());
        System.out.println("调用通用识别高精度版接口:" + genRes4.toString(2));

        // 调用通用识别接口(高精度含位置)
        JSONObject genRes5 = client.accurateGeneral(idFilePath,new HashMap<String, String>());
        System.out.println("调用通用识别高精度含位置版接口:" + genRes5.toString(2));

        // 行驶证识别接口
        String vehicleFilePath = "F:\\娱乐\\行驶证.png";
        JSONObject genRes6 = client.vehicleLicense(vehicleFilePath,new HashMap<String, String>());
        System.out.println("调用行驶证识别接口:" + genRes6.toString(2));

        // 驾驶证识别接口
        String drivingFilePath = "F:\\娱乐\\驾驶证.png";
        JSONObject genRes7 = client.drivingLicense(drivingFilePath,new HashMap<String, String>());
        System.out.println("调用驾驶证识别接口:" + genRes7.toString(2));
    }
}
