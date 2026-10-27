package com.smepro.models;

public class dataset_data {

	
   private  int dataset_data_id;
   private  int dataset_data_id_refer;
   private  String dataset_data_title;
   private  String dataset_data_subtitle1;
   private  String dataset_data_subtitle2;
   private  String dataset_data_subtitle3;
   private  String dataset_data_subtitle4;
   private  int main;
   
   
   
		public int getDataset_data_id() {
			return dataset_data_id;
		}
		public void setDataset_data_id(int dataset_data_id) {
			this.dataset_data_id = dataset_data_id;
		}
		public int getDataset_data_id_refer() {
			return dataset_data_id_refer;
		}
		public void setDataset_data_id_refer(int dataset_data_id_refer) {
			this.dataset_data_id_refer = dataset_data_id_refer;
		}
		public String getDataset_data_title() {
			return dataset_data_title;
		}
		public void setDataset_data_title(String dataset_data_title) {
			this.dataset_data_title = dataset_data_title;
		}
		public String getDataset_data_subtitle1() {
			return dataset_data_subtitle1;
		}
		public void setDataset_data_subtitle1(String dataset_data_subtitle1) {
			this.dataset_data_subtitle1 = dataset_data_subtitle1;
		}
		public String getDataset_data_subtitle2() {
			return dataset_data_subtitle2;
		}
		public void setDataset_data_subtitle2(String dataset_data_subtitle2) {
			this.dataset_data_subtitle2 = dataset_data_subtitle2;
		}
		public String getDataset_data_subtitle3() {
			return dataset_data_subtitle3;
		}
		public void setDataset_data_subtitle3(String dataset_data_subtitle3) {
			this.dataset_data_subtitle3 = dataset_data_subtitle3;
		}
		public String getDataset_data_subtitle4() {
			return dataset_data_subtitle4;
		}
		public void setDataset_data_subtitle4(String dataset_data_subtitle4) {
			this.dataset_data_subtitle4 = dataset_data_subtitle4;
		}
	
		
		public int getMain() {
			return main;
		}
		public void setMain(int main) {
			this.main = main;
		}
		@Override
		public String toString() {
			return String.format(
					"dataset_data [dataset_data_id=%s, dataset_data_id_refer=%s, dataset_data_title=%s, dataset_data_subtitle1=%s, dataset_data_subtitle2=%s, dataset_data_subtitle3=%s, dataset_data_subtitle4=%s]",
					dataset_data_id, dataset_data_id_refer, dataset_data_title, dataset_data_subtitle1, dataset_data_subtitle2,
					dataset_data_subtitle3, dataset_data_subtitle4);
		}
				
   
   
	
}
