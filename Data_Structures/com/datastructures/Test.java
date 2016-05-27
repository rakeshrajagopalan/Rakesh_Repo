package com.datastructures;

import java.util.HashMap;
import java.util.Map;

public class Test {
	public static void main(String[] args) {
		Map<String, String> h = new HashMap<>();
		System.out.println(h.put("A", "Apple"));
		System.out.println(h.put("A", "Boy"));
		System.out.println(h.get("A"));
		System.out.println(h.size());
	}
}
