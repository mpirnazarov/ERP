package com.mkyong.web.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Created by Muslimbek on 12/21/2016.
 */
public class Json {
    public static void main(String[] args){
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        JSONObject jsonObj= new JSONObject();
        jsonObj.put("color", "CrunchifyColor1");
        jsonObj.put("value", "#111");
        jsonArray.add(jsonObj);
        jsonArray.add(jsonObj);
        jsonArray.add(jsonObj);
        jsonObject.put("begin", 1234);
        jsonObject.put("value", jsonArray);
        System.out.println(jsonObject);
    }
}
