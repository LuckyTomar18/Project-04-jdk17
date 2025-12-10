package in.co.rays.proj4.testmodel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.bean.FacultyBean;
import in.co.rays.proj4.bean.MarksheetBean;
import in.co.rays.proj4.bean.TimetableBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.CourseModel;
import in.co.rays.proj4.model.FacultyModel;
import in.co.rays.proj4.model.MarksheetModel;
import in.co.rays.proj4.model.TimetableModel;

public class TestTimeTable {

	public static void main(String[] args) throws ParseException {

		// testAdd();
		// testUpdate();
		// testDelete();
		testSearch();
	}

	public static void testAdd() throws ParseException {

		TimetableModel model = new TimetableModel();
		TimetableBean bean = new TimetableBean();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setId(1);
		bean.setSemester("second");
		bean.setDescription("Must");
		bean.setExamDate(sdf.parse("2025-11-20"));
		bean.setExamTime("9:00 to 12:00");
		bean.setCourseId(1);
		bean.setSubjectId(1);

		try {
			model.add(bean);
		} catch (ApplicationException | DuplicateRecordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void testUpdate() {

		TimetableModel model = new TimetableModel();
		try {
			TimetableBean bean = model.findByPk(1l);
			bean.setSemester("first");

			model.update(bean);
		} catch (DuplicateRecordException | ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void testDelete() {

		TimetableModel model = new TimetableModel();
		TimetableBean bean;
		try {
			bean = model.findByPk(1l);
			model.delete(bean);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void testSearch() {

		TimetableModel model = new TimetableModel();
		TimetableBean bean = new TimetableBean();

		try {
			List list = model.search(bean, 0, 0);
			Iterator<TimetableBean> it = list.iterator();
			while (it.hasNext()) {
				bean = it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getCourseId());
				System.out.println(bean.getCourseName());
			}
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
