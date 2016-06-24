package graduation.hnust.simplebook.common;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.common.collect.Maps;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import graduation.hnust.simplebook.util.ToastUtil;
import graduation.hnust.simplebook.web.api.MessageApi;
import graduation.hnust.simplebook.web.base.HttpUrl;
import graduation.hnust.simplebook.web.base.RequestMethod;

/**
 * 发送短信服务, 用于注册.
 *
 * @Author : panxin
 * @Date : 9:00 PM 3/27/16
 * @Email : panxin109@gmail.com
 */
public final class SmsService {

    private String mobile;
    private String code;

    private static final String SEND_SMS = "send_sms";
    private static final String VERIFY_SMS = "verify";

    /**
     * 发送短信
     *
     * @param context 上下文
     * @param mobile 手机号
     * @return 发送结果
     */
    public Boolean sendSms(Context context, String mobile) {
        if (!NetworkHelper.isNetworkAvailable(context)) {
            return Boolean.FALSE;
        }
        this.mobile = mobile;
        String result = null;
        try {
            result = new HttpRequest().execute(HttpUrl.DOMAIN+ MessageApi.SEND_SMS, SEND_SMS).get();
            if (result == null) {
                return Boolean.FALSE;
            }
        }catch (Exception e) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * 校验短信验证码
     *
     * @param context xx
     * @param mobile 手机号
     * @param verifyCode 短信验证码
     * @return 校验结果
     */
    public Boolean verifySmsCode(Context context, String mobile, String verifyCode) {
        this.mobile = mobile;
        this.code = verifyCode;
        if (!NetworkHelper.isNetworkAvailable(context)) {
            return Boolean.FALSE;
        }
        this.mobile = mobile;
        String result = null;
        try {
            result = new HttpRequest().execute(HttpUrl.DOMAIN+ MessageApi.VERIFY_SMS_CODE, VERIFY_SMS).get();
            if (result == null || result.equals("false")) {
                return Boolean.FALSE;
            }
        }catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * 发送HTTP请求
     */
    private class HttpRequest extends AsyncTask<String, Void, String> {

        private String action;

        /**
         * URL 作为参数, 获取并处理网页返回的数据. 执行完毕后, 返回一个结果字符串.
         * @param urls 请求url
         * @return 结果
         */
        @Override
        protected String doInBackground(String... urls) {
            try {
                this.action = urls[1];
                return doRequest(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        /**
         * 接收结果字符串并处理
         *
         * @param result 请求结果字符串
         */
        @Override
        protected void onPostExecute(String result) {
            Log.i("simplebook", " -- -- >" + result);
        }

        /**
         * 发送请求
         *
         * @param requestUrl 请求URL
         * @throws IOException
         */
        private String doRequest(String requestUrl) throws IOException {
            try{
                if (action.equals(SEND_SMS)) {
                    return doSendSms(requestUrl);
                }else {
                    return doVerifySmsCode(requestUrl);
                }
            }catch (Exception e) {
                Log.i("sms3", e.toString());
                return null;
            }
        }

    }

    /**
     * 校验短信验证码
     *
     * @param requestUrl 请求地址
     * @return 校验结果
     * @throws Exception
     */
    private String doVerifySmsCode(String requestUrl) throws Exception{
        // 创建连接
        URL url = new URL(requestUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod(RequestMethod.POST);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setUseCaches(false);

        Map<String, Object> context = Maps.newHashMap();
        context.put("mobile", mobile);
        context.put("smsCode", code);

        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        writer.write(HttpHelper.getPostDataString(context));

        writer.flush();
        writer.close();
        os.close();

        // 响应处理
        int response = conn.getResponseCode();
        String result = null;
        try {
            if (response == HttpURLConnection.HTTP_OK) {
                InputStream in = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                result = reader.readLine();
            }
            Log.i("sms4", result);
            return result;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 发送短信
     *
     * @param requestUrl 请求url
     * @return 发送结果
     * @throws Exception
     */
    private String doSendSms(String requestUrl) throws Exception{
        // 创建连接
        URL url = new URL(requestUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod(RequestMethod.POST);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setUseCaches(false);

        Map<String, Object> context = Maps.newHashMap();
        context.put("mobile", mobile);

        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        writer.write(HttpHelper.getPostDataString(context));

        writer.flush();
        writer.close();
        os.close();

        // 响应处理
        int response = conn.getResponseCode();
        String result = null;
        try {
            if (response == HttpURLConnection.HTTP_OK) {
                InputStream in = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                result = reader.readLine();
            }
            return result;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
