import com.baidu.aip.ocr.AipOcr;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Administrator on 2017-9-6.
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
        String bankFilePath = "F:\\娱乐\\20170906173124.png";
        JSONObject bankRes = client.bankcard(bankFilePath);
        System.out.println("调用银行卡识别接口:" + bankRes.toString(2));

        // 调用通用识别接口
        String genFilePath = "F:\\a.jpg";
        JSONObject genRes = client.basicGeneral(genFilePath, new HashMap<String, String>());
        System.out.println("调用通用识别接口:" + genRes.toString(2));

        // 调用通用识别（含位置信息）接口
        String genFilePath2 = "F:\\a.jpg";
        JSONObject genRes2 = client.general(genFilePath2, new HashMap<String, String>());
        System.out.println("调用通用识别（含位置信息）接口:" + genRes2.toString(2));
    }
}
