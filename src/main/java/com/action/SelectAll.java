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

@WebServlet("/SelectAll")
public class SelectAll extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

		WinterDao winterdao = new WinterDao();
		ResultSet rset;
		ArrayList<Winter> objData = new ArrayList<Winter>();
		try {

			rset = winterdao.selecttData();
			PrintWriter out = res.getWriter();
			while (rset.next()) {

				Winter winter = new Winter();
				winter.setSl_no(rset.getInt("sl_no"));
				Buisness obj = new Buisness(rset.getString("business_code"), rset.getString("business_name"));
				Customer objCustomer = new Customer(rset.getInt("cust_number"), rset.getString("name_customer"));

				winter.setObjBuisness(obj);
				winter.setObjCustomer(objCustomer);
				winter.setDoc_id(rset.getDouble("doc_id"));
				winter.setPosting_date(rset.getString("posting_date"));
				winter.setDocument_create_date(rset.getString("document_create_date"));
				winter.setDocument_create_date1(rset.getString("document_create_date1"));
				winter.setDue_in_date(rset.getString("due_in_date"));
				winter.setInvoice_currency(rset.getString("invoice_currency"));
				winter.setBuisness_year(rset.getInt("buisness_year"));
				winter.setDocument_type(rset.getString("document_type"));
				winter.setPosting_id(rset.getInt("posting_id"));
				winter.setClear_date(rset.getString("clear_date"));
				winter.setArea_business(rset.getString("area_business"));
				winter.setTotal_open_amount(rset.getInt("total_open_amount"));
				winter.setBaseline_create_date(rset.getString("baseline_create_date"));
				winter.setCust_payment_terms(rset.getString("cust_payment_terms"));
				winter.setInvoice_id(rset.getDouble("invoice_id"));
				winter.setIsOpen(rset.getInt("isOpen"));
				winter.setAging_bucket(rset.getString("aging_bucket"));
				winter.setIs_deleted(rset.getInt("is_deleted"));

				// Gson gson = new Gson();
				objData.add(winter);
			}

			Gson gson = new Gson();
			String jsonData = gson.toJson(objData);
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
