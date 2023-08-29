package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.database.WinterDao;
import com.details.Buisness;
import com.details.Customer;
import com.details.Winter;
import com.google.gson.Gson;

@WebServlet("/SearchById")
public class SearchById extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

		double id = Double.parseDouble(req.getParameter("customer_number"));

		Winter winter = new Winter();
		Customer obj = new Customer(id);
		winter.setObjCustomer(obj);

		WinterDao winterDao = new WinterDao();

		ResultSet rset;
		ArrayList<Winter> objWinters = new ArrayList<>();
		try {
			rset = winterDao.selectCustNum(obj);
			PrintWriter out = res.getWriter();

			while (rset.next()) {

				Winter winterobj = new Winter();
				winterobj.setSl_no(rset.getInt("sl_no"));
				Buisness objBuisness = new Buisness(rset.getString(2), rset.getString(3));
				Customer objCustomer = new Customer(rset.getInt(4), rset.getString(5));

				winterobj.setObjBuisness(objBuisness);
				winterobj.setObjCustomer(objCustomer);
				winterobj.setDoc_id(rset.getDouble("doc_id"));
				winterobj.setBuisness_year(rset.getInt("buisness_year"));
				winterobj.setPosting_date(rset.getString("posting_date"));
				winterobj.setDocument_create_date(rset.getString("document_create_date"));
				winterobj.setDocument_create_date1(rset.getString("document_create_date1"));
				winterobj.setDue_in_date(rset.getString("due_in_date"));
				winterobj.setInvoice_currency(rset.getString("invoice_currency"));
				winterobj.setDocument_type(rset.getString("document_type"));
				winterobj.setPosting_id(rset.getInt("posting_id"));

				// winter.setClear_date(Date.valueOf(rset.getString("clear_date")));
				winterobj.setClear_date(rset.getString("clear_date"));
				winterobj.setArea_business(rset.getString("area_business"));
				winterobj.setTotal_open_amount(rset.getDouble("total_open_amount"));
				winterobj.setBaseline_create_date(rset.getString("baseline_create_date"));
				winterobj.setCust_payment_terms(rset.getString("cust_payment_terms"));
				winterobj.setInvoice_id(rset.getDouble("invoice_id"));
				winterobj.setIsOpen(rset.getInt("isOpen"));
				winterobj.setAging_bucket(rset.getString("aging_bucket"));
				winterobj.setIs_deleted(rset.getInt("is_deleted"));

				objWinters.add(winterobj);

			}

			Gson gson = new Gson();
			String jsonData = gson.toJson(objWinters);
			PrintWriter printWriter = res.getWriter();
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			printWriter.write(jsonData);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
