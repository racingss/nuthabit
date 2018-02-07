package com.nuthabit.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nuthabit.dao.Kehu;
import com.nuthabit.dao.MyplanDAO;
import com.nuthabit.util.DataFormatUtil;

@WebServlet("/myplan/dakahistory.html")
public class DakaHistoryServlet  extends HttpServlet{
	Log log=LogFactory.getLog(this.getClass().getName());
	private static final long serialVersionUID = 1L;

	public DakaHistoryServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Kehu k = null;
		if (request.getSession().getAttribute("kehu") != null) {
			k = (Kehu) request.getSession().getAttribute("kehu");
		} else {
			request.getSession().setAttribute("sessionURL", request.getRequestURL()+"?"+request.getQueryString());
			request.getRequestDispatcher("loginwx.jsp").forward(request, response);
			return;
		}
		
		MyplanDAO dao = new MyplanDAO();
		String planId = request.getParameter("planId");
		if (!NumberUtils.isDigits(planId)) {
			return;
		}
		List<String> dakaHistory = null;
		String from = request.getParameter("from");
		if (from == null) {
			dakaHistory = dao.getDakaHistory(Long.valueOf(planId), DataFormatUtil.getFirstDayOfLastMonth(), DataFormatUtil.getLastDayOfNextMonth() );
		} else {
			String[] yearMonth = from.split("[,]");
			if (yearMonth == null || yearMonth.length != 2)	return;
			String[] range = DataFormatUtil.getMonthDayRange(Integer.parseInt(yearMonth[0]) , Integer.parseInt(yearMonth[1]));
			dakaHistory = dao.getDakaHistory(Long.valueOf(planId), range[0], range[1]);
		}
		
		PrintWriter out=response.getWriter();
		try {
	        out.println(wrapperDakaHistoryJson(dakaHistory));
		}catch(Exception e) {
			out.println(StringUtils.EMPTY);
			log.error("DakaHistoryServlet error " + e.getMessage());
		} finally {
			out.close();
		}
	}
	
	private JSONArray wrapperDakaHistoryJson(List<String> dakaHistory) throws Exception {
		if (dakaHistory == null) return new JSONArray();
		
		JSONArray array = new JSONArray();
		for (String planDate : dakaHistory) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date planDateObj = sdf.parse(planDate);
			long timestamp = planDateObj.getTime();
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("stamp", timestamp);
			jsonObj.put("className", "able2");
			array.add(jsonObj);
		}
		return array;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
