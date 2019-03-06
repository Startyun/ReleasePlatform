package android.com.releaseplatform.OkHttpUtil;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpUtil {
    public static void sendOkHttpRequest(String address,okhttp3.Callback callback) {
        OkHttpClient client =new OkHttpClient();
        Request request =new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }


    public static void sendOkHttpPost(String address,String data,okhttp3.Callback callback) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),data);
        OkHttpClient client =new OkHttpClient();
        Request request =new Request.Builder().post(requestBody).url(address).build();
        client.newCall(request).enqueue(callback);
    }
}
