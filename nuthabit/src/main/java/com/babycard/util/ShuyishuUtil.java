package com.babycard.util;

import java.util.*;

public class ShuyishuUtil {

	private static final int maxIndx = 300;

	private Collection coordinateCOll = new ArrayList();
	private int innerIndex = 0;

	public Collection getCoordinate(int nums, double maxLeft, double maxTop, double range) {

		if (++innerIndex > maxIndx) {
			System.out.println("需要检查程序，已经超过" + maxIndx);
			return coordinateCOll;
		}

		if (coordinateCOll.size() >= nums) {
			//System.out.println("用了多少次：" + innerIndex);
			return coordinateCOll;
		}

		double left = Math.random() * maxLeft;
		double top = Math.random() * maxTop;
		
		if(left<20 && top<20){
			return getCoordinate(nums, maxLeft, maxTop, range);
		}

		Iterator it = coordinateCOll.iterator();
		while (it.hasNext()) {
			double[] temp = (double[]) it.next();
			double tempLeft = temp[0];
			double tempTop = temp[1];
			if (left < tempLeft && left + range > tempLeft) {
				return getCoordinate(nums, maxLeft, maxTop, range);
			}

			if (left > tempLeft && left - range < tempLeft) {
				return getCoordinate(nums, maxLeft, maxTop, range);
			}

			if (top < tempTop && top + range > tempTop) {
				return getCoordinate(nums, maxLeft, maxTop, range);
			}

			if (top > tempTop && top - range < tempTop) {
				return getCoordinate(nums, maxLeft, maxTop, range);
			}

		}
		coordinateCOll.add(new double[] { left, top });

		return getCoordinate(nums, maxLeft, maxTop, range);
	}

	public ShuyishuUtil() {

	}

	public static void main(String arg[]) {
//		for (int i = 0; i < 1000; i++)
//			new ShuyishuUtil().getCoordinate(8, 80, 70, 5).size();
		Iterator it = new ShuyishuUtil().getCoordinate(8, 80, 70, 5).iterator();
		while(it.hasNext()){
			double[] temp = (double[]) it.next();
			System.out.println(temp[0]+" : "+temp[1]);
		}
	}

}
