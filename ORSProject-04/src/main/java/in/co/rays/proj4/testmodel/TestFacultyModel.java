package in.co.rays.proj4.testmodel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.FacultyBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.FacultyModel;

public class TestFacultyModel {

	public static void main(String[] args) throws ParseException {

		 testAdd();
		// testUpdate();
		// testDelete();
		//testSearch();
	}

	public static void testAdd() throws ParseException {

		FacultyModel model = new FacultyModel();
		FacultyBean bean = new FacultyBean();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		bean.setId(1);
		bean.setFirstName("Sky");
		bean.setLastName("yadav");
		bean.setDob(sdf.parse("2002-06-16"));
		bean.setGender("male");
		bean.setMobileNo("1234567890");
		bean.setEmail("sk@gmail.com");
		bean.setCollegeId(1);
		bean.setSubjectId(1);
		bean.setCourseId(1);
		try {
			model.add(bean);
		} catch (ApplicationException | DuplicateRecordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void testUpdate() {

		FacultyModel model = new FacultyModel();
		try {
			FacultyBean bean = model.findByPk(2l);
			bean.setFirstName("Surya");

			model.update(bean);
		} catch (DuplicateRecordException | ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void testDelete() {

		FacultyModel model = new FacultyModel();
		FacultyBean bean = null;
		try {
			bean = model.findByPk(2l);
			model.delete(bean);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void testSearch() {

		FacultyModel model = new FacultyModel();
		FacultyBean bean = new FacultyBean();

		try {
			List list = model.search(bean, 0, 0);
			Iterator<FacultyBean> it = list.iterator();
			while (it.hasNext()) {
				bean = it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getCollegeName());
				System.out.println(bean.getCourseName());
				System.out.println(bean.getSubjectName());
			}
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
