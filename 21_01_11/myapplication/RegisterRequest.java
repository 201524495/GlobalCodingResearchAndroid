package com.example.myapplication;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    //서버 url 설정 (php 파일 연동)
    final static private String URL = "http://annjs0308.dothome.co.kr/Register2.php";
    private Map<String, String> map;

    public RegisterRequest(String userID, String userPassword, String userName, String userMail, Response.Listener<String>listener) {
    super(Method.POST, URL, listener, null);

    map = new HashMap<>();
    map.put("userID", userID);
    map.put("userPassword", userPassword);
    map.put("userName", userName);
    map.put("userMail", userMail);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
//다음으로 RegisterRequest 클래스는  URL에 POST방식으로
// 파라미터들을 전송하는 역할을 수행합니다.
// 여기서는 회원가입정보를 PHP 서버에 보내서 데이터베이스에 저장시키게 합니다.