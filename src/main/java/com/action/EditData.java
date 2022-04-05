package com.action;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.database.WinterDao;

@WebServlet("/EditData")
public class EditData extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

		WinterDao winterDao = new WinterDao();
		int slno = Integer.parseInt(req.getParameter("slno"));
		String currency = req.getParameter("currency");
		String terms = req.getParameter("terms");
		if (currency != null) {
			winterDao.updateCurrency(slno, currency);
			System.out.println("not null c");
		} else {
			System.out.println("null c");
		}

		if (terms != null) {
			winterDao.updateCustomersTerm(slno, terms);
			System.out.println(" not null t");
		} else {
			System.out.println("null t");
		}

	}

}
