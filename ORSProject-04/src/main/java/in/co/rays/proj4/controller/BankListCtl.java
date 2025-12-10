package in.co.rays.proj4.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj4.bean.BankBean;
import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.model.BankModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.PropertyReader;
import in.co.rays.proj4.util.ServletUtility;

@WebServlet(name = "BankListCtl", urlPatterns = { "/ctl/BankListCtl" })
public class BankListCtl extends BaseCtl {
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		
		BankBean bean = new BankBean();
		
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
		
		BankBean bean = (BankBean) populateBean(request);
		BankModel model = new BankModel();
		
		try {
			List<BankBean> list = model.search(bean, pageNo, pageSize);
			List<BankBean> next = model.search(bean, pageNo + 1, pageSize);
			
			if(list == null || list.isEmpty()) {
				ServletUtility.setErrorMessage("No Record Found", request);
			}
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.setBean(bean, request);
			request.setAttribute("nextListSize", next.size());

			ServletUtility.forward(getView(), request, response);

			
		} catch (ApplicationException e) {
			
			e.printStackTrace();
			ServletUtility.handleException(e, request, response);
			return;
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	

	@Override
	protected String getView() {
		return ORSView.BANK__LIST_VIEW;

	}

}
