package com.mobile.sunrin.hischool;

import android.content.Context;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.mobile.sunrin.hischool.ConnectListener.GetMembersGPSListener;
import com.mobile.sunrin.hischool.ConnectListener.JoinListener;
import com.mobile.sunrin.hischool.ConnectListener.LoginListener;
import com.mobile.sunrin.hischool.ConnectListener.PublicKeyListener;
import com.mobile.sunrin.hischool.ConnectListener.SchoolMealListener;
import com.mobile.sunrin.hischool.ConnectListener.SignUpListener;
import com.mobile.sunrin.hischool.ConnectListener.UpdateXYListener;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

/**
 * Created by parkjaemin on 2015. 5. 30..
 */
public class ConnectManager {
    private final String SchoolMealURL = "http://chmod777.kr/7_bob.json";
    private final String JoinURL = "http://chmod777.kr:5000/account/join";
    private final String LoginURL = "http://chmod777.kr:5000/signin/";
    private final String SignURL = "http://chmod777.kr:5000/signup/";
    private final String UpdateXYURL = "http://chmod777.kr:5000/api/account/updatexy";
    private final String GroupMemGPSURL = "http://http://chmod777.kr/hischool/test.json/";
    private final String uploadHomeworkURL = "http://chmod777.kr:5000/api/notice/";

    private static ConnectManager mInstance = null;

    private ConnectManager() {
    }

    public static ConnectManager getInstance() {
        if (mInstance == null) {
            mInstance = new ConnectManager();
        }
        return mInstance;
    }



/*
   public JsonObjectRequest signUp(String id, String pwd, String name, int age, String addr,  Context context) {
        JsonObjectRequest req = null;
        req = new JsonObjectRequest(
                Request.Method.GET,
                SIGN_URL
                ,
                null,
                new JoinListener( context),
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error!", error.toString());
                    }
                }
        );
        return req;
    }
*/

    public StringRequest uploadHomework(Context context,
                                        final String content)
    {
        StringRequest req = null;
        req = new StringRequest(
                Request.Method.POST,
                uploadHomeworkURL,
                new LoginListener(context),
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Log.d("uploadHomeWORK", error.toString());
                    }
                }
        ){
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("content", content);
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = super.getHeaders();

                if (headers == null
                        || headers.equals(Collections.emptyMap())) {
                    headers = new HashMap<String, String>();
                }

                MyApp.get().addSessionCookie(headers);
                Log.d("Upload - Header", headers.toString());

                return headers;
            }
        };
        return req;
    }

    public StringRequest signUp(Context context,
                                final String dialog,
                                final String pw,
                                final String name,
                                final int account,
                                final String student,
                                final String gcm)
    {
        StringRequest req = null;
        req = new StringRequest(
                Request.Method.POST,
                SignURL,
                new SignUpListener(context),
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Log.d("SignUP", error.toString());
                    }
                }
        ){
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("phone", dialog);
                params.put("name", name);
                params.put("password", pw);
                params.put("ban", String.valueOf(account/100%100));
                params.put("grade", String.valueOf(account/10000));
                params.put("num", String.valueOf(account%100));
                params.put("student",student);
                params.put("gcm_id", gcm);
                return params;
            }
        };
        return req;
    }

    public StringRequest login(final Context context, final String phone, final String pwd)
    {
        StringRequest req = null;
        req = new StringRequest(
                Request.Method.POST,
                LoginURL,
                new LoginListener(context),
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Log.d("LOGIN", error.toString());
                        Toast.makeText(context, "로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("phone", phone);
                params.put("password", pwd);
                return params;
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                // since we don't know which of the two underlying network vehicles
                // will Volley use, we have to handle and store session cookies manually
                MyApp.get().checkSessionCookie(response.headers);

                return super.parseNetworkResponse(response);
            }

            /* (non-Javadoc)
             * @see com.android.volley.Request#getHeaders()
             */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = super.getHeaders();

                if (headers == null
                        || headers.equals(Collections.emptyMap())) {
                    headers = new HashMap<String, String>();
                }

                MyApp.get().addSessionCookie(headers);

                Log.d("Login - Header", headers.toString());

                return headers;
            }
        };
        return req;
    }

    public StringRequest updateXY(Context context, final double lat, final double lon)
    {
        HashMap map = PublicKeyListener.getInstance(context).getHashMap();
        StringRequest req = null;
        req = new StringRequest(
                Request.Method.PUT,
                UpdateXYURL,
                new UpdateXYListener(context),
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Log.d("LOGIN", error.toString());
                    }
                }
        ){
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("x", String.valueOf(lat));
                params.put("y", String.valueOf(lon));
                return params;
            }
        };
        return req;
    }

    public JsonObjectRequest getRSAPublicKey(Context context)
    {
        JsonObjectRequest req = null;
        req = new JsonObjectRequest(
                Request.Method.GET,
                LoginURL,
                null,
                PublicKeyListener.getInstance(context),
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Log.d("getRSA!", error.toString());
                    }
                }

        );
        return req;
    }

    public JsonObjectRequest getMembersGPS(Context context, int groupCode)
    {
        JsonObjectRequest req = null;
        req = new JsonObjectRequest(
                Request.Method.GET,
                GroupMemGPSURL,
                null,
                GetMembersGPSListener.getInstance(context),
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Log.e("getMembersGPS ERror!", error.toString());
                    }
                }

        );
        return req;
    }

    public JsonObjectRequest getSchoolMeal(Context context)
    {
        JsonObjectRequest req = null;
        req = new JsonObjectRequest(
                Request.Method.GET,
                SchoolMealURL,
                null,
                SchoolMealListener.getInstance(context),
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Log.d("SchoolMealError!", error.toString());
                    }
                }
        );
        return req;
    }

    public StringRequest JoinService(Context context, final String email, final String pwd, final String name, final String gcm) {
        StringRequest req = null;
        req = new StringRequest(Request.Method.POST,
                JoinURL,
                new JoinListener(context),
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("JoinError!", error.toString());
                    }
                }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("pw", pwd);
                params.put("name", name);
                params.put("gcm", gcm);
                return params;
            }
        };
        return req;
    }

    public String myEncode(String id) {
        try {
            return URLEncoder.encode(id, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getMD5(String target)
    {
        String MD5 = "";
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(target.getBytes());
            byte byteData[] = md.digest();
            StringBuffer sb = new StringBuffer();
            for(int i = 0 ; i < byteData.length ; i++){
                sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
            }
            MD5 = sb.toString();

        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
            MD5 = null;
        }
        return MD5;
    }

}
