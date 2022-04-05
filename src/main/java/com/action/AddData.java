package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.database.WinterDao;
import com.details.Buisness;
import com.details.Customer;
import com.details.Winter;

@WebServlet("/AddData")
public class AddData extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

		Winter winter = new Winter();

		Buisness buisness = new Buisness(req.getParameter("buisness_code"));
		Customer customer = new Customer(Double.parseDouble(req.getParameter("Customer_number")));
		winter.setClear_date(req.getParameter("clear_date"));

		PrintWriter out = res.getWriter();

		// ******* ----->>> clear Date ask to moderator
		winter.setBuisness_year(Integer.parseInt(req.getParameter("buisness_year")));
		winter.setDoc_id(Double.parseDouble(req.getParameter("doc_id")));
		winter.setPosting_date(req.getParameter("posting_date"));
		winter.setDocument_create_date(req.getParameter("document_create_date"));
		winter.setDue_in_date(req.getParameter("due_in_date"));
		winter.setInvoice_currency(req.getParameter("invoice_currency"));
		winter.setDocument_type(req.getParameter("document_type"));
		winter.setPosting_id(Integer.parseInt(req.getParameter("posting_id")));
		winter.setTotal_open_amount(Double.parseDouble(req.getParameter("total_open_amount")));
		winter.setBaseline_create_date(req.getParameter("baseline_create_date"));
		winter.setCust_payment_terms(req.getParameter("customer_payment_terms"));
		winter.setInvoice_id(Double.parseDouble(req.getParameter("invoice_id")));

		WinterDao winterDao = new WinterDao();
		try {
			winterDao.addDatabase(winter, buisness, customer);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println(" Data added ");

	}

}
