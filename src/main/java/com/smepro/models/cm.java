package com.smepro.models;

public class cm  {
   private int  cm_id;
   private String  cm_title;
   private String  cm_date;
   private int  cm_idadd;
   private int  cm_idrefer;
   private String  cm_user_name;
   private String  type;
   
		public int getCm_id() {
			return cm_id;
		}
		public void setCm_id(int cm_id) {
			this.cm_id = cm_id;
		}
		public String getCm_title() {
			return cm_title;
		}
		public void setCm_title(String cm_title) {
			this.cm_title = cm_title;
		}
		public String getCm_date() {
			return cm_date;
		}
		public void setCm_date(String cm_date) {
			this.cm_date = cm_date;
		}
		public int getCm_idadd() {
			return cm_idadd;
		}
		public void setCm_idadd(int cm_idadd) {
			this.cm_idadd = cm_idadd;
		}
		public int getCm_idrefer() {
			return cm_idrefer;
		}
		public void setCm_idrefer(int cm_idrefer) {
			this.cm_idrefer = cm_idrefer;
		}
		public String getCm_user_name() {
			return cm_user_name;
		}
		public void setCm_user_name(String cm_user_name) {
			this.cm_user_name = cm_user_name;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		@Override
		public String toString() {
			return String.format("cm [cm_id=%s, cm_title=%s, cm_date=%s, cm_idadd=%s, cm_idrefer=%s, cm_user_name=%s]",
					cm_id, cm_title, cm_date, cm_idadd, cm_idrefer, cm_user_name);
		}

		
	
		
		
   
   
}





