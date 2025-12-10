package in.co.rays.proj4.testmodel;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import in.co.rays.proj4.bean.BankBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.BankModel;

public class TestBankModel {

	public static void main(String[] args) throws DatabaseException {
		
	//	testAdd();
		//testUpdate();
		//testFindByaccNo();
		testDelete();
//		
//		BankModel model = new  BankModel();
//		long pk = model.nextPk();
//		System.out.println(pk);
	}
	
	public static void testAdd() {
		
		BankModel model = new BankModel();
		BankBean bean =  new BankBean();
		
		try {
		bean.setAccountNumber("7697687749");
		bean.setAccountHolderName("Ankit rawat");
		bean.setAccountType("saving");
		bean.setBranch("IDFC");
		bean.setBalance(400.00);
		bean.setPhoneNo("8475697367");
		bean.setCreatedBy("lucky");
		bean.setModifiedBy("lucky");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		
			model.add(bean);
		} catch (ApplicationException | DuplicateRecordException e) {
		
			e.printStackTrace();
		}
	}
	
	public static void testUpdate() {
		
		BankModel model = new BankModel();
		try {
			BankBean bean = model.findByPk(1);
			bean.setAccountHolderName("Tomar Lucky");
			model.update(bean);
			
		} catch (ApplicationException | DuplicateRecordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public static void testFindByaccNo() {
		
		BankModel model = new BankModel();
		try {
			BankBean bean = model.findByAccountNumber("123456789");
			
			System.out.println(bean.getAccountHolderName());
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
public static void testDelete() {
		
		BankModel model = new BankModel();
		BankBean bean;
		try {
			bean = model.findByPk(1);
			model.delete(bean);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
}
}
