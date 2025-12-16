package in.co.rays.proj4.testmodel;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import in.co.rays.proj4.bean.StaffBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.StaffModel;

public class TestStaffModel {
	
	public static void testAdd() throws ParseException {
		
		StaffBean bean = new StaffBean();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		StaffModel model = new StaffModel();
		
		try {
		bean.setFullName("Ankit Rawat");
		bean.setJoiningDate(sdf.parse("2003-12-14"));
		bean.setDivision("Ground");
		bean.setPreviousEmployer("NCS");
		bean.setCreatedBy("Lucky");
		bean.setModifiedBy("Lucky");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		
		
			model.add(bean);
			System.out.println("Added succcesfully");
		} catch (ApplicationException | DuplicateRecordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
