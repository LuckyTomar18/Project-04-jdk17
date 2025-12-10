package in.co.rays.proj4.testmodel;

import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.bean.StudentBean;
import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.CollegeModel;
import in.co.rays.proj4.model.RoleModel;
import in.co.rays.proj4.model.UserModel;

public class TestCollegeModel {

	public static void main(String[] args) {

		// testAdd();
		// testUpdate();
		// testpk();
		testSearch();
	}

	public static void testAdd() {

		CollegeModel model = new CollegeModel();
		CollegeBean bean = new CollegeBean();
		bean.setName("admin");
		bean.setAddress("Gwalior");

		bean.setCity("Gwalior");
		bean.setState("UP");
		bean.setPhoneNo("7697687749");
		try {
			long pk = model.add(bean);
			model.findByPk(pk);
		} catch (ApplicationException | DuplicateRecordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void testUpdate() {

		CollegeModel model = new CollegeModel();
		try {
			CollegeBean bean = model.findByPk(1l);

			bean.setName("Sage");

			model.update(bean);

		} catch (ApplicationException | DuplicateRecordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void testpk() {

		CollegeModel model = new CollegeModel();
		try {
			CollegeBean bean = model.findByPk(2l);
			System.out.println(bean.getName());
			System.out.println(bean.getCity());

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testSearch() {

		CollegeModel model = new CollegeModel();
		CollegeBean bean = new CollegeBean();
		bean.setName("IPS");

		try {
			List list = model.search(bean, 0, 0);
			Iterator<CollegeBean> it = list.iterator();
			while (it.hasNext()) {
				bean = (CollegeBean) it.next();
				System.out.println(bean.getName());
				System.out.println(bean.getCity());
			}
		} catch (ApplicationException e) {

			e.printStackTrace();
		}

	}
	public static void testDelete() {
		CollegeModel model = new CollegeModel();
		
		try {
			CollegeBean bean = model.findByPk(1l);
			model.delete(bean);
		} catch (ApplicationException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
}
