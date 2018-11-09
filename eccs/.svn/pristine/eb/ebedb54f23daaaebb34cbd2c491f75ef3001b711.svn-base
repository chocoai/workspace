package com.smart.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * collection 工具类
 * 
 * @author 充满智慧的威哥
 */
public class CollectionUtil {

	/**
	 * 集合为空
	 * 
	 * @param array
	 * @return
	 */
	public static boolean empty(Object array[]) {
		if (array == null || array.length == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean empty(Collection<?> array) {
		if (array == null || array.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean empty(Map<?, ?> map) {
		if (map == null || map.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 集合不为空
	 * 
	 * @param array
	 * @return
	 */
	public static boolean notEmpty(Object array[]) {
		return !empty(array);
	}

	public static boolean notEmpty(Collection<?> connection) {
		return !empty(connection);
	}

	public static boolean notEmpty(Map<?, ?> map) {
		return !empty(map);
	}

	/**
	 * 反转集合
	 * 
	 * @param array
	 */
	public static void reverse(List<?> list) {
		Collections.reverse(list);
	}

	public static void reverse(Object array[]) {
		if (array == null) {
			return;
		}
		int i = 0;
		for (int j = array.length - 1; j > i; i++) {
			Object tmp = array[j];
			array[j] = array[i];
			array[i] = tmp;
			j--;
		}
	}

	/**
	 * 集合排序
	 * 
	 * @param <T>
	 * @param list
	 *            排序集合
	 * @param sortField
	 *            排序字段名
	 */
	public static <T> List<T> sort(List<T> list, String sortField) {
		for (T t : list) {
			ReflectionUtil.getFieldValue(t, sortField);
		}
		return null;
	}

	public static <T> T[] sort(T array[], String sortField) {

		return null;
	}

	public static Object[] add(Object array1[], Object array2[]) {
		if (array1 == null) {
			return array2 == null ? null : (Object[]) array2.clone();
		}
		if (array2 == null) {
			return array1 == null ? null : (Object[]) array1.clone();
		} else {
			Object joinedArray[] = (Object[]) Array.newInstance(
					((Object) (array1)).getClass().getComponentType(),
					array1.length + array2.length);
			System.arraycopy(((Object) (array1)), 0, ((Object) (joinedArray)),
					0, array1.length);
			System.arraycopy(((Object) (array2)), 0, ((Object) (joinedArray)),
					array1.length, array2.length);
			return joinedArray;
		}
	}

	/**
	 * 拆分集合
	 * 
	 * @param array
	 * @param start
	 * @param end
	 * @return
	 */
	public static Object[] subarray(Object array[], int start, int end) {
		if (array == null) {
			return null;
		}
		if (start < 0) {
			start = 0;
		}
		if (end > array.length) {
			end = array.length;
		}
		int newSize = end - start;
		Class<?> type = ((Object) (array)).getClass().getComponentType();
		if (newSize <= 0) {
			return (Object[]) Array.newInstance(type, 0);
		} else {
			Object subarray[] = (Object[]) Array.newInstance(type, newSize);
			System.arraycopy(((Object) (array)), start, ((Object) (subarray)),
					0, newSize);
			return subarray;
		}
	}

	public static List<?> subarray(List<?> list, int start, int end) {
		if (list == null) {
			return null;
		}
		if (start < 0) {
			start = 0;
		}
		if (end > list.size()) {
			end = list.size();
		}
		return list.subList(start, end);
	}

}
