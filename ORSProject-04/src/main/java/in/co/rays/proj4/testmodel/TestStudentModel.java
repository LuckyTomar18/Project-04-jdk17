package in.co.rays.proj4.testmodel;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import in.co.rays.proj4.bean.StudentBean;
import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.RoleModel;
import in.co.rays.proj4.model.StudentModel;
import in.co.rays.proj4.model.UserModel;

public class TestStudentModel {

	public static void main(String[] args) throws ParseException, ApplicationException {

		//testAdd();
		testUpdate();
	}

	public static void testAdd() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		StudentBean bean = new StudentBean();
		StudentModel model = new StudentModel();

		bean.setFirstName("Ankit");
		bean.setLastName("Rawat");
		bean.setDob(sdf.parse("2005-01-18"));
		bean.setGender("male");
		bean.setMobileNo("7697687749");
		bean.setEmail("ar@gmail.com");
		bean.setCollegeId(2);

		try {
			long pk = model.add(bean);
			model.findByPk(pk);
		} catch (ApplicationException | DuplicateRecordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void testUpdate() throws ApplicationException {

		StudentModel model = new StudentModel();
		StudentBean bean = new StudentBean();
		
		bean = model.findByPk(1L);
		
		bean.setCollegeId(1);
		bean.setEmail("Ankit@gmail.com");

		try {
			model.update(bean);
		} catch (ApplicationException | DuplicateRecordException e) {

			e.printStackTrace();
		}
	}
	
	public static void testDelete() {
		StudentModel model = new StudentModel();
		
		try {
			StudentBean bean = model.findByPk(1l);
			model.delete(bean);
		} catch (ApplicationException e) {
			
			e.printStackTrace();
		}
		
		
	}
}
