package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.details.Buisness;
import com.details.Customer;
import com.details.Winter;

public class WinterDao {

	protected String url = "jdbc:mysql://localhost:3306/grey_goose";
	protected String jdbcdriver = "com.mysql.cj.jdbc.Driver";
	protected String username = "prac";
	protected String psw = "prac";

	protected static final String SELECT_ALL_NULL_DATA = "SELECT w.sl_no , b.business_code,b.business_name,c.cust_number,c.name_customer,w.clear_date,w.buisness_year,w.doc_id,w.posting_date,w.document_create_date "
			+ ", w.document_create_date1,w.due_in_date,w.invoice_currency,w.total_open_amount,w.baseline_create_date,w.document_type,w.posting_id,w.area_business,"
			+ "w.cust_payment_terms,w.invoice_id,w.isOpen,w.aging_bucket,w.is_deleted" + " from "
			+ "grey_goose.winter_internship w , grey_goose.business b , grey_goose.customer c "
			+ " where b.business_code = w.business_code and c.cust_number = w.cust_number and w.clear_date = '00-00-0000' ans w.is_deleted = 0 ORDER BY sl_no ";

	protected static final String SELECT_ALL_CUSTOMER = "SELECT w.sl_no , b.business_code,b.business_name,c.cust_number,c.name_customer,w.clear_date,w.buisness_year,w.doc_id,w.posting_date,w.document_create_date "
			+ ", w.document_create_date1,w.due_in_date,w.invoice_currency,w.document_type,w.posting_id,w.area_business,w.total_open_amount,w.baseline_create_date,"
			+ "w.cust_payment_terms,w.invoice_id,w.isOpen,w.aging_bucket,w.is_deleted" + " from "
			+ " grey_goose.winter_internship w ,grey_goose.business b ,grey_goose.customer c "
			+ " where b.business_code = w.business_code and c.cust_number = w.cust_number and w.is_deleted = 0 ORDER BY sl_no ";

	protected static final String SELECT_CUSTOMER_ID = "SELECT w.sl_no , b.business_code,b.business_name,c.cust_number,c.name_customer,w.clear_date,w.buisness_year,w.doc_id,w.posting_date,w.document_create_date "
			+ ", w.document_create_date1,w.due_in_date,w.invoice_currency,w.document_type,w.posting_id,w.area_business,w.total_open_amount,w.baseline_create_date,"
			+ "w.cust_payment_terms,w.invoice_id,w.isOpen,w.aging_bucket,w.is_deleted" + " from "
			+ " grey_goose.winter_internship w , grey_goose.business b , grey_goose.customer c "
			+ " where b.business_code = w.business_code and c.cust_number = w.cust_number and w.cust_number = ? and w.is_deleted = 0 ORDER BY sl_no ";

	protected static final String SELECT_CUSTOMER_ADV = "SELECT w.sl_no , b.business_code,b.business_name,c.cust_number,c.name_customer,w.clear_date,w.buisness_year,w.doc_id,w.posting_date,w.document_create_date "
			+ ", w.document_create_date1,w.due_in_date,w.invoice_currency,w.document_type,w.posting_id,w.area_business,w.total_open_amount,w.baseline_create_date,"
			+ "w.cust_payment_terms,w.invoice_id,w.isOpen,w.aging_bucket,w.is_deleted" + " from "
			+ " grey_goose.winter_internship w , grey_goose.business b , grey_goose.customer c "
			+ " where (b.business_code = w.business_code) and ( w.cust_number = c.cust_number) and ( w.doc_id LIKE ? ) and (w.buisness_year LIKE ? )"
			+ " and ( w.cust_number LIKE ?  ) and ( w.invoice_id LIKE ? ) and w.is_deleted = 0 ORDER BY sl_no ";

	protected static final String INSERT_DATA = " INSERT INTO `grey_goose`.`winter_internship`"
			+ "(sl_no,business_code,cust_number,clear_date,buisness_year,doc_id,posting_date,document_create_date ,"
			+ "due_in_date,invoice_currency,document_type,posting_id,total_open_amount,baseline_create_date,cust_payment_terms,invoice_id,is_deleted)"
			+ "VALUES( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?  , ? , ? , ? , ?,? )";

	protected static final String DELETE_DATA = " DELETE FROM grey_goose.winter_internship WHERE sl_no = ? ";

	protected static final String UPDATE_CURRENCY = " UPDATE grey_goose.winter_internship SET invoice_currency = ? WHERE sl_no = ? and is_deleted = 0";

	protected static final String UPDATE_CUSTOMER_TERMS = " UPDATE grey_goose.winter_internship SET cust_payment_terms = ? WHERE sl_no = ? and is_deleted = 0";

	protected static final String UPDATE_DELETE = "UPDATE grey_goose.winter_internship SET is_deleted = 1 WHERE sl_no = ?";

	protected static final String GET_MAX_SL_NO = "SELECT max(sl_no) FROM grey_goose.winter_internship";

	protected static final String ANALYTICS = "SELECT count(*) , sum(w.total_open_amount) , w.business_code from grey_goose.winter_internship w , "
			+ " grey_goose.business b WHERE (( w.clear_date LIKE ? ) OR (w.clear_date LIKE ? ) ) "
			+ " AND ((w.due_in_date LIKE ? ) OR (w.due_in_date LIKE ? ))  AND ((w.baseline_create_date LIKE ? ) or "
			+ " (w.baseline_create_date LIKE ? ))  AND (w.invoice_currency LIKE ? ) AND (w.business_code = b.business_code) GROUP BY business_code ORDER BY sl_no ";

	protected static final String ANALYTICS_PIE = "SELECT count(*)  , w.invoice_currency from grey_goose.winter_internship w , "
			+ " grey_goose.business b WHERE (( w.clear_date LIKE ? ) OR (w.clear_date LIKE ? ) ) "
			+ " AND ((w.due_in_date LIKE ? ) OR (w.due_in_date LIKE ? ))  AND ((w.baseline_create_date LIKE ? ) or "
			+ " (w.baseline_create_date LIKE ? ))  AND (w.invoice_currency LIKE ? ) AND (w.business_code = b.business_code) GROUP BY w.invoice_currency ORDER BY sl_no ";

	// ---->>>> Function to get jdbc connection
	protected Connection getConnection() {
		Connection connection = null;
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, psw);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return connection;
	}

	public ResultSet selecttData() throws SQLException {

		Connection connection = getConnection();
		PreparedStatement statement = connection.prepareStatement(SELECT_ALL_CUSTOMER);
		ResultSet rSet = statement.executeQuery();

		return rSet;
	}

	public ResultSet selectCustNum(Customer obj) throws SQLException {

		Connection connection = getConnection();
		PreparedStatement statement = connection.prepareStatement(SELECT_CUSTOMER_ID);
		statement.setDouble(1, obj.getCust_number());
		ResultSet rSet = statement.executeQuery();

		return rSet;
	}

	public ResultSet selectAdv(Winter winter) throws SQLException {
		/// -???
		StringBuffer cust = new StringBuffer("");
		StringBuffer docId = new StringBuffer("");
		StringBuffer invoiceID = new StringBuffer("");
		StringBuffer year = new StringBuffer("");
		if (winter.getObjCustomer().getCust_number() != 0) {
			cust.append(String.format("%.0f", winter.getObjCustomer().getCust_number()));
		}
		if (winter.getDoc_id() != 0) {
			docId.append(String.format("%.0f", winter.getDoc_id()));
		}
		if (winter.getInvoice_id() != 0) {
			invoiceID.append(String.format("%.0f", winter.getInvoice_id()));
		}
		if (winter.getBuisness_year() != 0) {
			year.append(winter.getBuisness_year());
		}

		String num = new String(cust);
		String doc = new String(docId);
		String invoice = new String(invoiceID);
		String byear = new String(year);
		System.out.println(winter.getObjCustomer().getCust_number());
		System.out.println("num :" + "'%" + num + "%'");
		System.out.println("doc : " + "'%" + doc + "%'");
		System.out.println("invoice : " + "'%" + invoice + "%'");
		System.out.println("byear : " + "'%" + byear + "%'");
		Connection connection = getConnection();
		PreparedStatement statement = connection.prepareStatement(SELECT_CUSTOMER_ADV);
		statement.setString(1, "%" + doc + "%");
		statement.setString(2, "%" + byear + "%");
		statement.setString(3, "%" + num + "%");
		statement.setString(4, "%" + invoice + "%");

		ResultSet rSet = statement.executeQuery();

		return rSet;
	}

	public void addDatabase(Winter winter, Buisness buisness, Customer customer) throws SQLException {

		Connection connection = getConnection();

		winter.setObjBuisness(buisness);
		winter.setObjCustomer(customer);

		Statement statement = connection.createStatement();
		ResultSet rset = statement.executeQuery(GET_MAX_SL_NO);
		rset.next();
		winter.setSl_no(rset.getInt("max(sl_no)") + 1);
		/*
		 * (sl_no,business_code,cust_number,clear_date,buisness_year,doc_id,posting_date
		 * ,document_create_date , document_create_date1,"
		 * "due_in_date,invoice_currency,document_type,posting_id,area_business,total_open_amount,baseline_create_date,cust_payment_terms,invoice_id,isOpen,"
		 * + "aging_bucket,is_deleted`)"
		 */

		PreparedStatement statement3 = connection.prepareStatement(INSERT_DATA);
		// --> now we have to assign values in query statement
		statement3.setInt(1, winter.getSl_no());
		statement3.setString(2, buisness.getBusiness_code());
		statement3.setDouble(3, customer.getCust_number());
		statement3.setString(4, winter.getClear_date());
		statement3.setInt(5, winter.getBuisness_year());
		statement3.setDouble(6, winter.getDoc_id());
		statement3.setString(7, winter.getPosting_date());
		statement3.setString(8, winter.getDocument_create_date());
		statement3.setString(9, winter.getDue_in_date());
		statement3.setString(10, winter.getInvoice_currency());
		statement3.setString(11, winter.getDocument_type());
		statement3.setInt(12, winter.getPosting_id());
		statement3.setDouble(13, winter.getTotal_open_amount());
		statement3.setString(14, winter.getBaseline_create_date());
		statement3.setString(15, winter.getCust_payment_terms());
		statement3.setDouble(16, winter.getInvoice_id());
		statement3.setInt(17, 0);

		// ---->>>execution of query
		statement3.executeUpdate();

		System.out.println(" data added ");

	}

	public void deleteDatabase(ArrayList<Integer> list) throws SQLException {

		Connection connection = getConnection();

		for (int i = 0; i < list.size(); i++) {
			PreparedStatement statement = connection.prepareStatement(UPDATE_DELETE);
			statement.setInt(1, list.get(i));

			statement.executeUpdate();
			statement.close();

		}
		System.out.println(" data deleted");

	}

	public void updateCurrency(int slno, String currency) {

		Connection connection = getConnection();
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(UPDATE_CURRENCY);
			statement.setString(1, currency);
			statement.setInt(2, slno);
			statement.executeUpdate();
			System.out.println("updated Currency");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateCustomersTerm(int slno, String terms) {

		Connection connection = getConnection();
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(UPDATE_CUSTOMER_TERMS);
			statement.setString(1, terms);
			statement.setInt(2, slno);
			statement.executeUpdate();
			System.out.println(" updated terms");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ResultSet analyticsView(Winter objfirst, Winter objSecond, String cur) {
		ResultSet rSet = null;
		Connection connection = getConnection();

		StringBuffer due1 = new StringBuffer("");
		StringBuffer due2 = new StringBuffer("");
		StringBuffer clear1 = new StringBuffer("");
		StringBuffer clear2 = new StringBuffer("");
		StringBuffer base1 = new StringBuffer("");
		StringBuffer base2 = new StringBuffer("");

		if (objfirst.getDue_in_date().length() < 15)
			due1.append(objfirst.getDue_in_date());

		if (objSecond.getDue_in_date().length() < 15)
			due2.append(objSecond.getDue_in_date());

		if (objfirst.getClear_date().length() < 15)
			clear1.append(objSecond.getClear_date());

		if (objSecond.getClear_date().length() < 15)
			clear2.append(objSecond.getClear_date());

		if (objfirst.getBaseline_create_date().length() < 15)
			base1.append(objfirst.getBaseline_create_date());

		if (objSecond.getBaseline_create_date().length() < 15)
			base2.append(objSecond.getBaseline_create_date());

		String dueS1 = new String(due1);
		String dueS2 = new String(due2);
		String clearS1 = new String(clear1);
		String clearS2 = new String(clear2);
		String baseS1 = new String(base1);
		String baseS2 = new String(base2);
		System.out.println("due1:" + dueS1 + "  due2:" + dueS2 + " clear1:" + clearS1 + " clear2:" + clearS2 + " base1:"
				+ baseS1 + " base2:" + baseS2 + " in:" + cur);

		try {
			PreparedStatement statement = connection.prepareStatement(ANALYTICS);
			statement.setString(1, "%" + clearS1 + "%");
			statement.setString(2, "%" + clearS2 + "%");
			statement.setString(3, "%" + dueS1 + "%");
			statement.setString(4, "%" + dueS2 + "%");
			statement.setString(5, "%" + baseS1 + "%");
			statement.setString(6, "%" + baseS2 + "%");
			statement.setString(7, "%" + cur + "%");

			rSet = statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rSet;
	}

	public ResultSet analyticsViewPie(Winter objfirst, Winter objSecond, String cur) {
		ResultSet rSet = null;
		Connection connection = getConnection();

		StringBuffer due1 = new StringBuffer("");
		StringBuffer due2 = new StringBuffer("");
		StringBuffer clear1 = new StringBuffer("");
		StringBuffer clear2 = new StringBuffer("");
		StringBuffer base1 = new StringBuffer("");
		StringBuffer base2 = new StringBuffer("");

		if (objfirst.getDue_in_date().length() < 15)
			due1.append(objfirst.getDue_in_date());

		if (objSecond.getDue_in_date().length() < 15)
			due2.append(objSecond.getDue_in_date());

		if (objfirst.getClear_date().length() < 15)
			clear1.append(objSecond.getClear_date());

		if (objSecond.getClear_date().length() < 15)
			clear2.append(objSecond.getClear_date());

		if (objfirst.getBaseline_create_date().length() < 15)
			base1.append(objfirst.getBaseline_create_date());

		if (objSecond.getBaseline_create_date().length() < 15)
			base2.append(objSecond.getBaseline_create_date());

		String dueS1 = new String(due1);
		String dueS2 = new String(due2);
		String clearS1 = new String(clear1);
		String clearS2 = new String(clear2);
		String baseS1 = new String(base1);
		String baseS2 = new String(base2);
		System.out.println("due1:" + dueS1 + "  due2:" + dueS2 + " clear1:" + clearS1 + " clear2:" + clearS2 + " base1:"
				+ baseS1 + " base2:" + baseS2 + " in:" + cur);

		try {
			PreparedStatement statement = connection.prepareStatement(ANALYTICS_PIE);
			statement.setString(1, "%" + clearS1 + "%");
			statement.setString(2, "%" + clearS2 + "%");
			statement.setString(3, "%" + dueS1 + "%");
			statement.setString(4, "%" + dueS2 + "%");
			statement.setString(5, "%" + baseS1 + "%");
			statement.setString(6, "%" + baseS2 + "%");
			statement.setString(7, "%" + cur + "%");

			rSet = statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rSet;
	}

}

/*
 * 
 * *?
 * 
 * 
 * class ABC implement interFace{
 * 
 * overide all methodds
 * 
 * }
 * 
 * 
 */
