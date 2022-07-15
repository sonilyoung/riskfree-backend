package egovframework.com.global.util;

import org.apache.commons.collections.map.ListOrderedMap;

/**
 * 카멜표기법으로 DB컬럼을 매핑해주는 클래스
 * Map을 대체하기 위해 작성됨
 *
 */
public class OfficeMap extends ListOrderedMap {
	
	@Override
	public Object put(Object key, Object value) {
		return super.put(convertCase((String) key), value);
	}
	
	public static String convertCase(String underScore) {
		if (underScore.indexOf('_') < 0 && Character.isLowerCase(underScore.charAt(0))) {
			return underScore;
		}
		
		StringBuilder result = new StringBuilder();
		boolean nextUpper = false;
		int len = underScore.length();

		for (int i = 0; i < len; i++) {
			char currentChar = underScore.charAt(i);
			if (currentChar == '_') {
				nextUpper = true;
			} else {
				if (nextUpper) {
					result.append(Character.toUpperCase(currentChar));
					nextUpper = false;
				} else {
					result.append(Character.toLowerCase(currentChar));
				}
			}
		}
		
		return result.toString();
	}
}
