package org.usfirst.frc.team2609.utils;

import java.util.HashMap;
import java.util.Map;

public class MapOperations {
	public static <K,V> Map<V, K> invertMap(Map<K, V> toInvert) {
	    Map<V, K> result = new HashMap<V, K>();
	    for(K k: toInvert.keySet()){
	        result.put(toInvert.get(k), k);
	    }
	    return result;
	}
}
