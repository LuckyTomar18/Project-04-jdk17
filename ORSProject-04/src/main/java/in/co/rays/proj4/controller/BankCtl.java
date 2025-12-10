package in.co.rays.proj4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj4.bean.BankBean;
import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.BankModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.PropertyReader;
import in.co.rays.proj4.util.ServletUtility;

@WebServlet(name = "BankCtl", urlPatterns = { "/ctl/BankCtl" })
public class BankCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		System.out.println("Bank Ki Validate chali");

		if (DataValidator.isNull(request.getParameter("accountNo"))) {
			request.setAttribute("accountNo", PropertyReader.getValue("error.require", "accountNo"));
			pass = false;
			System.out.println("AccountNo-->" + pass);
		}
		if (DataValidator.isNull(request.getParameter("accountHolderName"))) {
			request.setAttribute("accountHolderName", PropertyReader.getValue("error.require", "accountHolderName"));
			pass = false;
			System.out.println("accountHolderName-->" + pass);
		}
		if (DataValidator.isNull(request.getParameter("accountType"))) {
			request.setAttribute("accountType", PropertyReader.getValue("error.require", "accountType"));
			pass = false;
			System.out.println("accountType-->" + pass);
		}
		if (DataValidator.isNull(request.getParameter("branch"))) {
			request.setAttribute("branch", PropertyReader.getValue("error.require", "branch"));
			pass = false;
			System.out.println("branch-->" + pass);
		}
		if (DataValidator.isNull(request.getParameter("balance"))) {
			request.setAttribute("balance", PropertyReader.getValue("error.require", "balance"));
			pass = false;
			System.out.println("balance-->" + pass);
		}
		if (DataValidator.isNull(request.getParameter("phoneNo"))) {
			request.setAttribute("phoneNo", PropertyReader.getValue("error.require", "phoneNo"));
			pass = false;
		}

		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		BankBean bean = new BankBean();

		System.out.println("Bank ki Populate chali");

		bean.setAccountNumber(DataUtility.getString(request.getParameter("accountNo")));
		bean.setAccountHolderName(DataUtility.getString(request.getParameter("accountHolderName")));
		bean.setAccountType(DataUtility.getString(request.getParameter("accountType")));
		bean.setBranch(DataUtility.getString(request.getParameter("branch")));
		bean.setBalance(DataUtility.getDouble(request.getParameter("balance")));
		bean.setPhoneNo(DataUtility.getString(request.getParameter("phoneNo")));

		populateDTO(bean, request);
		return bean;

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Bank ki Do post Chali");
		BankModel model = new BankModel();
		BankBean bean = (BankBean) populateBean(request);
		System.out.println("Bean AccountNAME--->" + bean.getAccountHolderName());
		try {
			model.add(bean);
			ServletUtility.setBean(bean, request);
			ServletUtility.setSuccessMessage("Bank User Added Successfully", request);
		} catch (DuplicateRecordException e) {
			ServletUtility.setBean(bean, request);
			ServletUtility.setErrorMessage("Account No already exists", request);
		} catch (ApplicationException e) {
			e.printStackTrace();
			return;
		}
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		return ORSView.BANK__VIEW;
	}

}
