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

	protected String url = "jdbc:mysql://localhost:3306/hrc_database";
	protected String jdbcdriver = "com.mysql.cj.jdbc.Driver";
	protected String username = "root";
	protected String psw = "root";

	protected static final String SELECT_ALL_NULL_DATA = "SELECT w.sl_no , b.business_code,b.business_name,c.cust_number,c.name_customer,w.clear_date,w.buisness_year,w.doc_id,w.posting_date,w.document_create_date "
			+ ", w.document_create_date1,w.due_in_date,w.invoice_currency,w.total_open_amount,w.baseline_create_date,w.document_type,w.posting_id,w.area_business,"
			+ "w.cust_payment_terms,w.invoice_id,w.isOpen,w.aging_bucket,w.is_deleted" + " from "
			+ " hrc_database.winter_internship w , hrc_database.business b , hrc_database.customer c "
			+ " where b.business_code = w.business_code and c.cust_number = w.cust_number and w.clear_date = '00-00-0000' ans w.is_deleted = 0 ORDER BY sl_no ";

	protected static final String SELECT_ALL_CUSTOMER = "SELECT w.sl_no , b.business_code,b.business_name,c.cust_number,c.name_customer,w.clear_date,w.buisness_year,w.doc_id,w.posting_date,w.document_create_date "
			+ ", w.document_create_date1,w.due_in_date,w.invoice_currency,w.document_type,w.posting_id,w.area_business,w.total_open_amount,w.baseline_create_date,"
			+ "w.cust_payment_terms,w.invoice_id,w.isOpen,w.aging_bucket,w.is_deleted" + " from "
			+ " hrc_database.winter_internship w , hrc_database.business b , hrc_database.customer c "
			+ " where b.business_code = w.business_code and c.cust_number = w.cust_number and w.is_deleted = 0 ORDER BY sl_no ";

	protected static final String SELECT_CUSTOMER_ID = "SELECT w.sl_no , b.business_code,b.business_name,c.cust_number,c.name_customer,w.clear_date,w.buisness_year,w.doc_id,w.posting_date,w.document_create_date "
			+ ", w.document_create_date1,w.due_in_date,w.invoice_currency,w.document_type,w.posting_id,w.area_business,w.total_open_amount,w.baseline_create_date,"
			+ "w.cust_payment_terms,w.invoice_id,w.isOpen,w.aging_bucket,w.is_deleted" + " from "
			+ " hrc_database.winter_internship w , hrc_database.business b , hrc_database.customer c "
			+ " where b.business_code = w.business_code and c.cust_number = w.cust_number and w.cust_number = ? and w.is_deleted = 0 ORDER BY sl_no ";

	protected static final String SELECT_CUSTOMER_ADV = "SELECT w.sl_no , b.business_code,b.business_name,c.cust_number,c.name_customer,w.clear_date,w.buisness_year,w.doc_id,w.posting_date,w.document_create_date "
			+ ", w.document_create_date1,w.due_in_date,w.invoice_currency,w.document_type,w.posting_id,w.area_business,w.total_open_amount,w.baseline_create_date,"
			+ "w.cust_payment_terms,w.invoice_id,w.isOpen,w.aging_bucket,w.is_deleted" + " from "
			+ " hrc_database.winter_internship w , hrc_database.business b , hrc_database.customer c "
			+ " where b.business_code = w.business_code and c.cust_number = w.cust_number and w.cust_number = ? and w.doc_id = ? and w.invoice_id = ? and w.buisness_year = ? and w.is_deleted = 0 ORDER BY sl_no ";

	protected static final String INSERT_DATA = " INSERT INTO `hrc_database`.`winter_internship`"
			+ "(sl_no,business_code,cust_number,clear_date,buisness_year,doc_id,posting_date,document_create_date ,"
			+ "due_in_date,invoice_currency,document_type,posting_id,total_open_amount,baseline_create_date,cust_payment_terms,invoice_id,is_deleted)"
			+ "VALUES( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?  , ? , ? , ? , ?,? )";

	protected static final String DELETE_DATA = " DELETE FROM hrc_database.winter_internship WHERE sl_no = ? ";

	protected static final String UPDATE_CURRENCY = " UPDATE hrc_database.winter_internship SET invoice_currency = ? WHERE sl_no = ? and is_deleted = 0";

	protected static final String UPDATE_CUSTOMER_TERMS = " UPDATE hrc_database.winter_internship SET cust_payment_terms = ? WHERE sl_no = ? and = 0";

	protected static final String UPDATE_DELETE = "UPDATE hrc_database.winter_internship SET is_deleted = 1 WHERE sl_no = ?";

	protected static final String GET_MAX_SL_NO = "SELECT max(sl_no) FROM hrc_database.winter_internship";

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

		Connection connection = getConnection();
		PreparedStatement statement = connection.prepareStatement(SELECT_CUSTOMER_ADV);
		statement.setDouble(1, winter.getObjCustomer().getCust_number());
		statement.setDouble(2, winter.getDoc_id());
		statement.setDouble(3, winter.getInvoice_id());
		statement.setInt(4, winter.getBuisness_year());

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

}
