package in.co.rays.proj4.testmodel;

import in.co.rays.proj4.bean.MarksheetBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.MarksheetModel;

public class TestMarksheetModel {

	public static void main(String[] args) {

		testAdd();
		 //testUpdate();
		//testDelete();
	}

	public static void testAdd() {

		MarksheetBean bean = new MarksheetBean();
		MarksheetModel model = new MarksheetModel();

		bean.setRollNo("20");
		bean.setStudentId(1);
		bean.setPhysics(50);
		bean.setChemistry(50);
		bean.setMaths(50);

		try {
			model.add(bean);

		} catch (ApplicationException | DuplicateRecordException e) {

			e.printStackTrace();
		}

	}

	public static void testUpdate() {

		MarksheetModel model = new MarksheetModel();

		try {
			MarksheetBean bean = model.findByPk(1l);

			bean.setChemistry(75);
			bean.setMaths(75);
			model.update(bean);
		} catch (DuplicateRecordException | ApplicationException e) {

			e.printStackTrace();
		}
	}

	public static void testDelete() {

		MarksheetModel model = new MarksheetModel();
		try {
			MarksheetBean bean = model.findByPk(1l);
			model.delete(bean);
			System.out.println("delete successfully");
		} catch (ApplicationException e) {

			e.printStackTrace();
		}
	}

	public static void testFindByPk() {

	}
}
