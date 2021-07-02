package com.example.zoobox.service;

import com.example.zoobox.Entity.KHospital;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    private String key="b874b9e4eef54a1c9f894bae06a983da";


    public String saveInfo() throws Exception {
        String url="https://data.gm.go.kr/openapi/Animalhosptl&key="+key+"&Type=json&pIndex=1&pSize=100";

        HashMap<String,Object> resultMap=getDataFromJson(url,"UTF-8","get","");
        System.out.println("#Result: "+resultMap);
        JSONObject jsonObj=new JSONObject();
        jsonObj.put("result",resultMap);
        return jsonObj.toString();

    }
    public HashMap<String,Object> getDataFromJson(String url,String encoding,String type,String jsonStr) throws Exception {
        boolean isPost=false;
        if("post".equals(type)){
            isPost=true;
        }else{
            url="".equals(jsonStr)?url:url+"?request="+jsonStr;

        }
            return getStringFromURL(url,encoding,isPost,jsonStr,"application/json");
        }
        public HashMap<String,Object> getStringFromURL(String url,String encoding,boolean isPost, String parameter, String contentType)throws Exception{
        URL ApiUrl=new URL(url);
        HttpURLConnection con=null;
        BufferedReader br=null;
            BufferedWriter bw=null;

            HashMap<String,Object> resultMap=new HashMap<String,Object>();
            try{
                con=(HttpURLConnection) ApiUrl.openConnection();
                con.setConnectTimeout(5000);
                con.setReadTimeout(5000);
                con.setDoOutput(true);

                if(isPost){
                    con.setRequestMethod("POST");
                    con.setRequestProperty("Content-Type",contentType);
                    con.setRequestProperty("Accept","*/*");
                }else{
                    con.setRequestMethod("GET");
                }
                con.connect();
                if(isPost){
                    bw=new BufferedWriter(new OutputStreamWriter(con.getOutputStream(),"UTF-8"));
                    bw.write(parameter);
                    bw.flush();
                    bw=null;
                }
                br=new BufferedReader(new InputStreamReader(con.getInputStream(),encoding));
                String line=null;
                StringBuffer result=new StringBuffer();
                while((line=br.readLine()) != null) result.append(line);
                ObjectMapper mapper=new ObjectMapper();
                resultMap=mapper.readValue(result.toString(),HashMap.class);

            }catch(Exception e){
                e.printStackTrace();
                throw new Exception(url +"interface failed"+e.toString());
            }
            finally {
                if(con != null) con.disconnect();
                if(br != null) br.close();
                if(bw !=null) bw.close();
            }
            return resultMap;

        }
}




