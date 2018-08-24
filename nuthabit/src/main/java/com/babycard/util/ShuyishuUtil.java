package com.babycard.util;

import java.util.*;

import com.babycard.dao.*;

public class ShuyishuUtil {

	private static final int maxIndx = 1000;

	private Collection coordinateCOll = new ArrayList();
	private int innerIndex = 0;

	public Collection getCoordinate(int nums, double maxLeft, double maxTop, double range) {

		if (++innerIndex > maxIndx) {
			System.out.println("需要检查程序，已经超过" + maxIndx);
			return coordinateCOll;
		}

		if (coordinateCOll.size() >= nums) {
			// System.out.println("用了多少次：" + innerIndex);
			return coordinateCOll;
		}

		double left = Math.random() * maxLeft;
		double top = Math.random() * maxTop;

		if (left < 20 && top < 20) {
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

	private int picIndex = 0;

	public Collection getCoordinate(Collection picColl, double maxLeft, double maxTop, double range) {
		long nums = picColl.size();

		if (++innerIndex > maxIndx) {
			System.out.println("需要检查程序，已经超过" + maxIndx);
			return coordinateCOll;
		}

		if (coordinateCOll.size() >= nums) {
			// System.out.println("用了多少次：" + innerIndex);
			return coordinateCOll;
		}

		double left = Math.random() * maxLeft;
		double top = Math.random() * maxTop;

		if (left < 20 && top < 20) {
			return getCoordinate(picColl, maxLeft, maxTop, range);
		}

		Iterator it = coordinateCOll.iterator();
		while (it.hasNext()) {
			double[] temp = (double[]) it.next();
			double tempLeft = temp[0];
			double tempTop = temp[1];
			if (left < tempLeft && left + range > tempLeft) {
				return getCoordinate(picColl, maxLeft, maxTop, range);
			}

			if (left > tempLeft && left - range < tempLeft) {
				return getCoordinate(picColl, maxLeft, maxTop, range);
			}

			if (top < tempTop && top + range > tempTop) {
				return getCoordinate(picColl, maxLeft, maxTop, range);
			}

			if (top > tempTop && top - range < tempTop) {
				return getCoordinate(picColl, maxLeft, maxTop, range);
			}

		}

		CardPic cp = (CardPic) picColl.toArray()[picIndex];
		cp.setTopP((long) top);
		cp.setLeftP((long) left);
		cp.setWidthP(18);
		new CardPicDAO().updatePicPosition(cp.getPicId(), (long) left, (long) top, 18,-2,0);
		coordinateCOll.add(new double[] { left, top });
		picIndex++;

		return getCoordinate(picColl, maxLeft, maxTop, range);
	}

	public ShuyishuUtil() {

	}

	public static void main(String arg[]) {
		new ShuyishuUtil().getCoordinate(new CardPicDAO().getCardPicByCardId(2264), 70, 70, 10);
	}

}
