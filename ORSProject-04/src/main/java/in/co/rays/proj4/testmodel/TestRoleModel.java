package in.co.rays.proj4.testmodel;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.RoleModel;

public class TestRoleModel {

	public static void main(String[] args) {
		// testAdd();
		 //testUpdate();
		 //testDelete();
		 testFindByPk();
		//testFindByName();
		//testSesarch();
	}

	public static void testAdd()  {

		RoleBean bean = new RoleBean();
		RoleModel model = new RoleModel();
		bean.setName("HR");
		bean.setDescription("HR");
		bean.setCreatedBy("HR");
		bean.setModifiedBy("HR");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		try {
		long pk = 	model.add(bean);
			RoleBean addedBean = model.findByPk(pk);
			if(addedBean == null) {
				System.out.println("Test add fail");
			}
		} catch (ApplicationException | DuplicateRecordException | SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	public static void testUpdate() {
		
		RoleModel model = new RoleModel();
		try {
		RoleBean bean = model.findByPk(5L);
			bean.setName("Manager");
			
			
			model.update(bean);
			
		} catch (ApplicationException | DuplicateRecordException e) {
			e.printStackTrace();
		}
}

	public static void testDelete() {
		RoleBean bean = new RoleBean();
		RoleModel model = new RoleModel();

		bean.setId(5);
		try {
			model.delete(bean);
			System.out.println("deleted successfully");
		} catch (ApplicationException e) {

			e.printStackTrace();
		}
	}

	public static void testFindByPk() {
		RoleModel model = new RoleModel();
		try {
			RoleBean bean = model.findByPk(1);
			System.out.println(bean.getName());
			System.out.println(bean.getDescription());
			System.out.println(bean.getCreatedDatetime());

		} catch (ApplicationException e) {

			e.printStackTrace();
		}

	}

	public static void testFindByName() {
		RoleModel model = new RoleModel();

		try {
			RoleBean bean = model.findByName("hr");
			System.out.println(bean.getName());
			System.out.println(bean.getDescription());
			System.out.println(bean.getId());

		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void testSesarch() {

		try {

			RoleBean bean = new RoleBean();
			RoleModel model = new RoleModel();
			
			List list = model.search(bean, 0, 0);
			if (list.size() < 0) {
				System.out.println("Test search fail");
			}
			Iterator<RoleBean> it = list.iterator();
			while (it.hasNext()) {
				bean = (RoleBean) it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getName());
				System.out.println(bean.getDescription());
			}
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
