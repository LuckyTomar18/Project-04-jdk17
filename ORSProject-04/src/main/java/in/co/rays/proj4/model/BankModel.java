package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.BankBean;
import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class BankModel {

	// Get next primary key
	public Integer nextPk() throws DatabaseException {
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(id) FROM st_bank");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new DatabaseException("Exception in getting next PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk + 1;
	}

	// Add record
	public long add(BankBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		int pk = 0;

		// Check duplicate account number
		BankBean exist = findByAccountNumber(bean.getAccountNumber());
		if (exist != null) {
			throw new DuplicateRecordException("Account Number already exists");
		}

		try {
			pk = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO st_bank VALUES(?, ?, ?, ?, ?, ?, ?, ?, ? ,? ,?)");

			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getAccountNumber());
			pstmt.setString(3, bean.getAccountHolderName());
			pstmt.setString(4, bean.getAccountType());
			pstmt.setString(5, bean.getBranch());
			pstmt.setDouble(6, bean.getBalance());
			pstmt.setString(7, bean.getPhoneNo());
			pstmt.setString(8, bean.getCreatedBy());
			pstmt.setString(9, bean.getModifiedBy());
			pstmt.setTimestamp(10, bean.getCreatedDatetime());
			pstmt.setTimestamp(11, bean.getModifiedDatetime());

			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Add rollback exception: " + ex.getMessage());
			}
			throw new ApplicationException("Exception in adding bank account");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk;
	}

	// Update record
	public void update(BankBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;

		// Duplicate check
		BankBean exist = findByAccountNumber(bean.getAccountNumber());
		if (exist != null && exist.getId() != bean.getId()) {
			throw new DuplicateRecordException("Account number already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE st_bank SET account_number=?, account_holder_name=?, account_type=?, branch=? ,balance=?, phone_no=? ,created_by=?, modified_by=?, created_datetime=?, modified_datetime=? WHERE id=?");

			pstmt.setString(1, bean.getAccountNumber());
			pstmt.setString(2, bean.getAccountHolderName());
			pstmt.setString(3, bean.getAccountType());
			pstmt.setString(4, bean.getBranch());
			pstmt.setDouble(5, bean.getBalance());
			pstmt.setString(6, bean.getPhoneNo());
			pstmt.setString(7, bean.getCreatedBy());
			pstmt.setString(8, bean.getModifiedBy());
			pstmt.setTimestamp(9, bean.getCreatedDatetime());
			pstmt.setTimestamp(10, bean.getModifiedDatetime());
			pstmt.setLong(11, bean.getId());

			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();

		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Update rollback exception: " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating bank account");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	// Delete record
	public void delete(BankBean bean) throws ApplicationException {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM st_bank WHERE id=?");
			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();

			conn.commit();
			pstmt.close();

		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Delete rollback exception: " + ex.getMessage());
			
			}
			throw new ApplicationException("Exception in deleting bank account");
			
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	// Find by Primary Key
	public BankBean findByPk(long pk) throws ApplicationException {
		BankBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM st_bank WHERE id=?");
			pstmt.setLong(1, pk);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new BankBean();

				bean.setId(rs.getLong(1));
				bean.setAccountNumber(rs.getString(2));
				bean.setAccountHolderName(rs.getString(3));
				bean.setAccountType(rs.getString(4));
				bean.setBranch(rs.getString(5));
				bean.setBalance(rs.getDouble(6));
				bean.setPhoneNo(rs.getString(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));
			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return bean;
	}

	// Find by Account Number
	public BankBean findByAccountNumber(String accNo) throws ApplicationException {
		Connection conn = null;
		BankBean bean = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM st_bank WHERE account_number=?");
			pstmt.setString(1, accNo);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new BankBean();
				bean.setId(rs.getLong(1));
				bean.setAccountNumber(rs.getString(2));
				bean.setAccountHolderName(rs.getString(3));
				bean.setAccountType(rs.getString(4));
				bean.setBranch(rs.getString(5));
				bean.setBalance(rs.getDouble(6));
				bean.setPhoneNo(rs.getString(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));
			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			throw new ApplicationException("Exception in finding bank account by account number");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return bean;
	}

	// Search
	public List<BankBean> search(BankBean bean, int pageNo, int pageSize) throws ApplicationException {
		Connection conn = null;
		List<BankBean> list = new ArrayList<>();

		StringBuffer sql = new StringBuffer("SELECT * FROM st_bank WHERE 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id=" + bean.getId());
			}
			if (bean.getAccountNumber() != null && bean.getAccountNumber().length() > 0) {
				sql.append(" AND account_number LIKE '" + bean.getAccountNumber() + "%'");
			}
			if (bean.getAccountHolderName() != null && bean.getAccountHolderName().length() > 0) {
				sql.append(" AND account_holder LIKE '" + bean.getAccountHolderName() + "%'");
			}
			if (bean.getAccountType() != null && bean.getAccountType().length() > 0) {
				sql.append(" AND account_type LIKE '" + bean.getAccountType() + "%'");
			}
		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" LIMIT " + pageNo + ", " + pageSize);
		}

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				BankBean dto = new BankBean();
				dto.setId(rs.getLong(1));
				dto.setAccountNumber(rs.getString(2));
				dto.setAccountHolderName(rs.getString(3));
				dto.setAccountType(rs.getString(4));
				dto.setBranch(rs.getString(5));
				dto.setBalance(rs.getDouble(6));
				dto.setPhoneNo(rs.getString(7));
				dto.setCreatedBy(rs.getString(8));
				dto.setModifiedBy(rs.getString(9));
				dto.setCreatedDatetime(rs.getTimestamp(10));
				dto.setModifiedDatetime(rs.getTimestamp(11));
				list.add(dto);
			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			throw new ApplicationException("Exception in searching bank accounts");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return list;
	}
}
