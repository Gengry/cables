package com.gry.cable.common;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class StructUtil {

	public static Map<Object,Map<String,Object>> listToMap(List<Map<String,Object>> list,String colname){
		Map<Object,Map<String,Object>> maps = new LinkedHashMap<Object, Map<String,Object>>();
		for (Map<String, Object> map : list) {
			maps.put(map.get(colname),map);
		}
		return maps;
	}
	
	public static boolean isDouble(String str) {    
	    Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");    
	    return pattern.matcher(str).matches();    
	  } 
	
}
