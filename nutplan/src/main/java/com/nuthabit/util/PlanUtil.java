package com.nuthabit.util;

import java.util.*;

import com.nuthabit.dao.MyplanHistory;

public class PlanUtil {
	public static long isDo(Collection coll, String distDate) {
		Iterator it = coll.iterator();
		while (it.hasNext()) {
			MyplanHistory h = (MyplanHistory) it.next();
			if (h.getPlanDate().endsWith(distDate)) {
				return h.getStatus();
			}
		}
		return 2;
	}

}
