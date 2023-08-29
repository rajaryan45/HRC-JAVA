package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.database.WinterDao;
import com.details.AnalyticsData;
import com.details.Winter;
import com.google.gson.Gson;

@WebServlet("/AnalyticsPie")
public class AnalyticsPie extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Winter objFirst = new Winter();
		Winter objSecond = new Winter();

		objFirst.setClear_date(req.getParameter("clear_date1"));
		objSecond.setClear_date(req.getParameter("clear_date2"));

		objFirst.setDue_in_date(req.getParameter("due_in_date1"));
		objSecond.setDue_in_date(req.getParameter("due_in_date2"));

		objFirst.setBaseline_create_date(req.getParameter("baseline_create_date1"));
		objSecond.setBaseline_create_date(req.getParameter("baseline_create_date2"));

		String cur = new String(req.getParameter("invoice_currency"));

		WinterDao winterDao = new WinterDao();
		ResultSet rset;
		PrintWriter out = res.getWriter();
		ArrayList<AnalyticsData> objWinters = new ArrayList<>();
		try {
			rset = winterDao.analyticsViewPie(objFirst, objSecond, cur);

			while (rset.next()) {
				AnalyticsData obj = new AnalyticsData();
				obj.setCurrencyCount(rset.getInt(1));
				obj.setCurrency(rset.getString(2));
				objWinters.add(obj);
			}
			Gson gson = new Gson();
			out.println(gson.toJson(objWinters));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
