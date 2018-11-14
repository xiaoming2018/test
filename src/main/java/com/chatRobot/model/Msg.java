package com.chatRobot.model;


import java.util.HashMap;
import java.util.Map;

public class Msg {
    /*
     状态码
     成功： 100
     失败： 200
     */
    private int code;
    // 错误信息
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }

    //用户返回给浏览器的信息
    private Map<String, Object> extend = new HashMap<String,Object>();

    public static Msg success(){
        Msg result = new Msg();
        result.setCode(100);
        result.setMsg("成功！");
        return result;
    }

    public static Msg fail(){
        Msg result = new Msg();
        result.setCode(200);
        result.setMsg("失败！");
        return result;
    }

    public Msg add(String key, Object value){
        this.getExtend().put(key,value);
        return this;
    }
}
