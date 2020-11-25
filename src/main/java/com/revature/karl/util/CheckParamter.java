package com.revature.karl.util;

public class CheckParamter {
	public static int hasParameter(String idParam) {
		int id = 0;
		if (idParam != null) {
			String[] split = idParam.split("/");
			if(split.length > 0) {
				id = Integer.parseInt(idParam.split("/")[1]);
			}
		}
		return id;
	}
}
