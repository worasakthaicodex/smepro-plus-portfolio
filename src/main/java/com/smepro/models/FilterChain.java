package com.smepro.models;

import java.util.List;

public class FilterChain {
	
	private int id;
	private int id_refer;
	private int tables;
	private String type; // index,doc
	private String query_text; // index,doc
	private String readableText; // index,doc
	private String groups; // index,doc
	private Object datalist; // index,doc
	private Object headlist; // index,doc
	private Object datalist2; // index,doc
	private int status; // index,doc
	private String json_out_put;
	List<FilterDisplayItem> filterDisplayItem;
	private Object saved_columns; // 🌟 เพิ่มตัวแปรนี้เข้าไป
	// 🌟 2. เพิ่มกลุ่มตัวแปร Join เข้าไปใหม่!
    private String join_type;
    private String join_master_key;
    private String join_master_table;
    private String join_child_key;
    private String join_child_table;
    private String display_columns;
    private Object custom_labels;
    // ==========================================
    // 🌟 3. อย่าลืม Generate Getter / Setter ให้พวกมันด้วยนะครับ
    // ==========================================
    
    
    
    
    public String getJoin_type() { return join_type; }
    public Object getCustom_labels() {
		return custom_labels;
	}
	public void setCustom_labels(Object custom_labels) {
		this.custom_labels = custom_labels;
	}
	public void setJoin_type(String join_type) { this.join_type = join_type; }

    public String getJoin_master_key() { return join_master_key; }
    public void setJoin_master_key(String join_master_key) { this.join_master_key = join_master_key; }

    public String getJoin_master_table() { return join_master_table; }
    public void setJoin_master_table(String join_master_table) { this.join_master_table = join_master_table; }

    public String getJoin_child_key() { return join_child_key; }
    public void setJoin_child_key(String join_child_key) { this.join_child_key = join_child_key; }

    public String getJoin_child_table() { return join_child_table; }
    public void setJoin_child_table(String join_child_table) { this.join_child_table = join_child_table; }

    public String getDisplay_columns() { return display_columns; }
    public void setDisplay_columns(String display_columns) { this.display_columns = display_columns; }
	// อย่าลืม Generate Getter / Setter ให้มันด้วยนะครับ
	public Object getSaved_columns() {
	    return saved_columns;
	}
	public void setSaved_columns(Object saved_columns) {
	    this.saved_columns = saved_columns;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_refer() {
		return id_refer;
	}
	public void setId_refer(int id_refer) {
		this.id_refer = id_refer;
	}
	public int getTables() {
		return tables;
	}
	public void setTables(int tables) {
		this.tables = tables;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getQuery_text() {
		return query_text;
	}
	public void setQuery_text(String query_text) {
		this.query_text = query_text;
	}
	public String getGroups() {
		return groups;
	}
	public void setGroups(String groups) {
		this.groups = groups;
	}
	public Object getDatalist() {
		return datalist;
	}
	public void setDatalist(Object datalist) {
		this.datalist = datalist;
	}
	public Object getHeadlist() {
		return headlist;
	}
	public void setHeadlist(Object headlist) {
		this.headlist = headlist;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getReadableText() {
		return readableText;
	}
	public void setReadableText(String readableText) {
		this.readableText = readableText;
	}
	public List<FilterDisplayItem> getFilterDisplayItem() {
		return filterDisplayItem;
	}
	public void setFilterDisplayItem(List<FilterDisplayItem> filterDisplayItem) {
		this.filterDisplayItem = filterDisplayItem;
	}
	public Object getDatalist2() {
		return datalist2;
	}
	public void setDatalist2(Object datalist2) {
		this.datalist2 = datalist2;
	}
	public String getJson_out_put() {
		return json_out_put;
	}
	public void setJson_out_put(String json_out_put) {
		this.json_out_put = json_out_put;
	}
	
	
	
	
}
