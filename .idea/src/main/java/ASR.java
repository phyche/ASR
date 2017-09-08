import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import com.baidu.aip.util.Util;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by 蔡琦 on 2017-9-6.
 * 语音识别
 *
 * 请求说明
 *
 * 原始语音的录音格式目前只支持评测 8k/16k 采样率 16bit 位深的单声道语音
 * 压缩格式支持：pcm（不压缩）、wav、opus、speex、amr
 * 系统支持语言种类：中文（zh）、粤语（ct）、英文（en）。
 *
 */
public class ASR {
    //设置APPID/AK/SK
    public static final String APP_ID = "10100681";//你的 App ID
    public static final String API_KEY = "pLDvpiugYzrBGDhWUSQNrRGe";//你的 Api Key
    public static final String SECRET_KEY = "WPATfhhmTWmXUDm63RhaUiYPaVegNy6R";//你的Secret Key

    public static void main(String[] args) throws IOException {
        //初始化一个FaceClient
        AipSpeech client = new AipSpeech(APP_ID,API_KEY,SECRET_KEY);

        //可选，设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        /*JSONObject res = client.asr("test.pcm","pcm",16000,null);
        System.out.println(res.toString(2));*/

        //语音识别
        systhesis(client);

        //语音合成
        synthesis(client);
    }

    public static void systhesis(AipSpeech client) throws IOException {

        //对本地语音文件进行识别
        String path = "F:\\娱乐\\新录音.pcm";
        JSONObject asrRes = client.asr(path,"pcm",16000,null);
        System.out.println("对本地语音文件进行识别:" + asrRes);

        // 对语音二进制数据进行识别
        byte[] data = Util.readFileByBytes(path);     //readFileByBytes仅为获取二进制数据示例
        JSONObject asrRes2 = client.asr(data, "pcm", 16000, null);
        System.out.println("对语音二进制数据进行识别:" + asrRes2);

        // 对网络上音频进行识别
        String url = "http://somehost/res/16k_test.pcm";
        String callback = "http://callbackhost/aip/dump";
        JSONObject res = client.asr(url, callback, "pcm", 16000, null);
        System.out.println("对网络上音频进行识别:" + res);

    }

    //语音合成
    public static void synthesis(AipSpeech client)
    {
        TtsResponse res = client.synthesis("你好百度", "zh", 1, null);
        System.out.println("语音合成" + res.getErrorCode());

        // 设置可选参数
        HashMap<String, Object> options = new HashMap<String, Object>();
        options.put("spd", "5");
        options.put("pit", "5");
        options.put("per", "4");
        TtsResponse res1 = client.synthesis("你好百度", "zh", 1, options);
        System.out.println("语音合成" + res1.getErrorCode());
        byte[] data = res.getData();
    }
}
