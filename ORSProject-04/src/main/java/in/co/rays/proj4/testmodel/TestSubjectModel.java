package in.co.rays.proj4.testmodel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.SubjectBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.SubjectModel;

public class TestSubjectModel {

	public static void main(String[] args) {
		// testAdd();
		//testUpdate();
		testDelete();
		//testSearch();
	}

	public static void testAdd() {

		SubjectModel model = new SubjectModel();
		SubjectBean bean = new SubjectBean();

		bean.setId(5);
		bean.setName("python");
		bean.setCourseId(1);

		try {
			model.add(bean);
		} catch (ApplicationException | DuplicateRecordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void testUpdate() {

		SubjectModel model = new SubjectModel();
		try {
			SubjectBean bean = model.findByPk(1l);
			bean.setCourseId(2);

			model.update(bean);
		} catch (ApplicationException | DuplicateRecordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void testDelete() {

		SubjectModel model = new SubjectModel();
		try {
			SubjectBean bean = model.findByPk(2l);

			model.delete(bean);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void testSearch() {
		
		SubjectModel model = new SubjectModel();
		SubjectBean bean = null;
		List list = new ArrayList();
		
		try {
			list = model.search(bean, 0, 0);
			Iterator<SubjectBean> it = list.iterator();
			while(it.hasNext()) {
				bean = it.next();
				System.out.println(bean.getCourseId());
				System.out.println(bean.getCourseName());
				System.out.println(bean.getName());
				
			}
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
