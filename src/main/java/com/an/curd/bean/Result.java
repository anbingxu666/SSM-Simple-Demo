package com.an.curd.bean;

import java.util.HashMap;
import java.util.Map;

public class Result {
    private Integer code;
    private String msg;
    private Map<String,Object> map = new HashMap<>();
    public Result add(String key,Object value){
        this.map.put(key,value);
        return this;
    }
    public static Result success(){

        Result result = new Result(100,"成功");

        return result;
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }
}
