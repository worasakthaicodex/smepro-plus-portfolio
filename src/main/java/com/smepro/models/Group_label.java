package com.smepro.models;

import java.util.List;

public class Group_label {

		private int id;
		private String group_label_name;
		private 	List<eformfield> eformfields;
		
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getGroup_label_name() {
			return group_label_name;
		}
		public void setGroup_label_name(String group_label_name) {
			this.group_label_name = group_label_name;
		}
		public List<eformfield> getEformfields() {
			return eformfields;
		}
		public void setEformfields(List<eformfield> eformfields) {
			this.eformfields = eformfields;
		}
		
		
}
