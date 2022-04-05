package com.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.database.WinterDao;

@WebServlet("/DeleteData")
public class DeleteData extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String str = req.getParameter("slno");

		Matcher matcher = Pattern.compile("\\d+").matcher(str);
		ArrayList<Integer> list = new ArrayList<Integer>();
		while (matcher.find()) {
			list.add(Integer.parseInt(matcher.group()));
		}

		System.out.println(list);
		WinterDao winterDao = new WinterDao();

		try {
			winterDao.deleteDatabase(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
