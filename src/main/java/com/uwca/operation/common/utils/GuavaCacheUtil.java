package com.uwca.operation.common.utils;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class GuavaCacheUtil {

	static LoadingCache<String, String> cache = CacheBuilder.newBuilder()
			.expireAfterWrite(30 * 60 * 1000, TimeUnit.SECONDS)
			.initialCapacity(10).build(new CacheLoader<String, String>() {
				public String load(String key) throws Exception {
					return "";
				}
			});;

	public static void put(String key, String value) {
		cache.put(key, value);
	}

	public static String get(String key) {
		String code = "";
		try {
			code = cache.get(key);
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return code;
	}
}
