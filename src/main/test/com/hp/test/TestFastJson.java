package com.hp.test;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class TestFastJson {

	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("a", "sadfadf");
		map.put("aa", "sadfadf");
		System.out.println(createJsonString(map));
	}

	public static String createJsonString(Object value) {
		String alibabaJson = JSON.toJSONString(value);
		return alibabaJson;
	}
}
