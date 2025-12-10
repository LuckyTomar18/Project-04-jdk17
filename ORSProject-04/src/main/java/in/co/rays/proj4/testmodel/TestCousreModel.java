package in.co.rays.proj4.testmodel;

import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.CourseModel;

public class TestCousreModel {

	public static void main(String[] args) throws DatabaseException {

		//testAdd();
		// testUpdate();
		//testDelete();
		testSearch();
	
	}

	public static void testAdd() {

		CourseModel model = new CourseModel();
		CourseBean bean = new CourseBean();

		bean.setId(1);
		bean.setName("BBA");
		bean.setDuration("3Yaers");
		bean.setDescription("Regularity");

		try {
			model.add(bean);
		} catch (DatabaseException | ApplicationException | DuplicateRecordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void testUpdate() {

		CourseModel model = new CourseModel();
		try {
			CourseBean bean = model.findByPk(1l);
			bean.setName("BBA");

			model.update(bean);
		} catch (DuplicateRecordException | ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public static void testSearch() {

		CourseModel model = new CourseModel();
		CourseBean bean = new CourseBean();
	
		try {
			List list = model.search(bean, 0, 0);
			Iterator<CourseBean> it = list.iterator();
			while (it.hasNext()) {
				bean = it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getName());
				System.out.println(bean.getDuration());
				System.out.println(bean.getDescription());
			}
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
