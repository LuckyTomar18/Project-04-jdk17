package in.co.rays.proj4.testmodel;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.RoleModel;
import in.co.rays.proj4.model.UserModel;


public class TestUserModel {

	public static void main(String[] args) throws ParseException {

		 //testAdd();
		//testUpdate();
		testDelete();
		//testAuthenticate();
	}

	public static void testAdd() throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		UserModel model = new UserModel();
		UserBean bean = new UserBean();

		try {
			bean.setFirstName("amit");
			bean.setLastName("sir");
			bean.setLogin("amit@gmail.com");
			bean.setPassword("123456");

			bean.setDob(sdf.parse("2005-01-18"));
			bean.setMobileNo("7697687749");
			bean.setRoleId(1);
			bean.setGender("male");
			bean.setCreatedBy("lucky");
			bean.setModifiedBy("lucky");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

			long pk = model.add(bean);
			UserBean addedBean = model.findByPk(pk);

			if (addedBean == null) {
				System.out.println("Test add fail");
			}
		} catch (ApplicationException e) {

			e.printStackTrace();
		} catch (DuplicateRecordException e) {

			e.printStackTrace();
		}

	}

	public static void testUpdate() {

		UserModel model = new UserModel();

		try {
			UserBean bean = model.findByPk(1l);

			bean.setLogin("lucky1801@gmail.com");
			bean.setRoleId(2);
			System.out.println("Data updated successfully");
			model.update(bean);

		} catch (DuplicateRecordException | ApplicationException e) {

			e.printStackTrace();
		}
	}
	
	public static void testDelete() {
		UserModel model = new UserModel();
		
		try {
			UserBean bean = model.findByPk(2l);
			model.delete(bean);
		} catch (ApplicationException e) {
			
			e.printStackTrace();
		}
		
		
	}

	public static void testAuthenticate() {
		
		UserModel model = new UserModel();
		
		try {
			UserBean bean = model.authenticate("lucky@gmail.com", "123456");
		System.out.println(bean.getFirstName());
		System.out.println(bean.getLastName());
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
	public static void testSesarch() {

		try {

			UserBean bean = new UserBean();
			UserModel model = new UserModel();
			
			List list = model.search(bean, 0, 0);
			if (list.size() < 0) {
				System.out.println("Test search fail");
			}
			Iterator<UserBean> it = list.iterator();
			while (it.hasNext()) {
				bean = (UserBean) it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getFirstName());
				System.out.println(bean.getLastName());
			}
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
