package com.details;

public class Winter {
	// --->>> instances
	private int sl_no;

	private Buisness objBuisness;
	private Customer objCustomer;

	private double doc_id;
	private String invoice_currency;
	private String document_type;
	private int posting_id;
	private String area_business;
	private double total_open_amount;
	private String cust_payment_terms;
	private double invoice_id;
	private int isOpen;
	private String aging_bucket;
	private int is_deleted;

	private String clear_date;
	private int buisness_year;
	private String posting_date;
	private String document_create_date;
	private String document_create_date1;
	private String due_in_date;
	private String baseline_create_date;

	// --->>> methods
	public int getSl_no() {
		return sl_no;
	}

	public void setSl_no(int sl_no) {
		this.sl_no = sl_no;
	}

	public Buisness getObjBuisness() {
		return objBuisness;
	}

	public void setObjBuisness(Buisness objBuisness) {
		this.objBuisness = objBuisness;
	}

	public Customer getObjCustomer() {
		return objCustomer;
	}

	public void setObjCustomer(Customer objCustomer) {
		this.objCustomer = objCustomer;
	}

	public double getDoc_id() {
		return doc_id;
	}

	public void setDoc_id(double doc_id) {
		this.doc_id = doc_id;
	}

	public String getInvoice_currency() {
		return invoice_currency;
	}

	public void setInvoice_currency(String invoice_currency) {
		this.invoice_currency = invoice_currency;
	}

	public String getDocument_type() {
		return document_type;
	}

	public void setDocument_type(String document_type) {
		this.document_type = document_type;
	}

	public int getPosting_id() {
		return posting_id;
	}

	public void setPosting_id(int posting_id) {
		this.posting_id = posting_id;
	}

	public String getArea_business() {
		return area_business;
	}

	public void setArea_business(String area_business) {
		this.area_business = area_business;
	}

	public double getTotal_open_amount() {
		return total_open_amount;
	}

	public void setTotal_open_amount(double total_open_amount) {
		this.total_open_amount = total_open_amount;
	}

	public String getCust_payment_terms() {
		return cust_payment_terms;
	}

	public void setCust_payment_terms(String cust_payment_terms) {
		this.cust_payment_terms = cust_payment_terms;
	}

	public double getInvoice_id() {
		return invoice_id;
	}

	public void setInvoice_id(double invoice_id) {
		this.invoice_id = invoice_id;
	}

	public int getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(int isOpen) {
		this.isOpen = isOpen;
	}

	public String getAging_bucket() {
		return aging_bucket;
	}

	public void setAging_bucket(String aging_bucket) {
		this.aging_bucket = aging_bucket;
	}

	public int getIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(int is_deleted) {
		this.is_deleted = is_deleted;
	}

	public String getClear_date() {
		return clear_date;
	}

	public void setClear_date(String clear_date) {
		this.clear_date = clear_date;
	}

	public int getBuisness_year() {
		return buisness_year;
	}

	public void setBuisness_year(int buisness_year) {
		this.buisness_year = buisness_year;
	}

	public String getPosting_date() {
		return posting_date;
	}

	public void setPosting_date(String posting_date) {
		this.posting_date = posting_date;
	}

	public String getDocument_create_date() {
		return document_create_date;
	}

	public void setDocument_create_date(String document_create_date) {
		this.document_create_date = document_create_date;
	}

	public String getDocument_create_date1() {
		return document_create_date1;
	}

	public void setDocument_create_date1(String document_create_date1) {
		this.document_create_date1 = document_create_date1;
	}

	public String getDue_in_date() {
		return due_in_date;
	}

	public void setDue_in_date(String due_in_date) {
		this.due_in_date = due_in_date;
	}

	public String getBaseline_create_date() {
		return baseline_create_date;
	}

	public void setBaseline_create_date(String baseline_create_date) {
		this.baseline_create_date = baseline_create_date;
	}

	public Winter(int sl_no, Buisness objBuisness, Customer objCustomer, int doc_id, String invoice_currency,
			String document_type, int posting_id, String area_business, double total_open_amount,
			String cust_payment_terms, int invoice_id, int isOpen, String aging_bucket, int is_deleted,
			String clear_date, int buisness_year, String posting_date, String document_create_date,
			String document_create_date1, String due_in_date, String baseline_create_date) {
		super();
		this.sl_no = sl_no;
		this.objBuisness = objBuisness;
		this.objCustomer = objCustomer;
		this.doc_id = doc_id;
		this.invoice_currency = invoice_currency;
		this.document_type = document_type;
		this.posting_id = posting_id;
		this.area_business = area_business;
		this.total_open_amount = total_open_amount;
		this.cust_payment_terms = cust_payment_terms;
		this.invoice_id = invoice_id;
		this.isOpen = isOpen;
		this.aging_bucket = aging_bucket;
		this.is_deleted = is_deleted;
		this.clear_date = clear_date;
		this.buisness_year = buisness_year;
		this.posting_date = posting_date;
		this.document_create_date = document_create_date;
		this.document_create_date1 = document_create_date1;
		this.due_in_date = due_in_date;
		this.baseline_create_date = baseline_create_date;
	}

	public Winter() {
		// TODO Auto-generated constructor stub
	}

}