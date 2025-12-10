/*
 * package in.co.rays.proj4.testmodel;
 * 
 * import java.sql.Connection; import java.sql.PreparedStatement; import
 * java.sql.ResultSet; import java.sql.SQLException; import java.sql.Timestamp;
 * import java.text.SimpleDateFormat; import java.util.ArrayList; import
 * java.util.Date; import java.util.Iterator; import java.util.List;
 * 
 * import in.co.rays.proj4.model.RoleModel; import
 * in.co.rays.proj4.utill.JDBCDataSource; import in.rays.co.proj4.bean.RoleBean;
 * import in.rays.co.proj4.exception.ApplicationException; import
 * in.rays.co.proj4.exception.DatabaseException; import
 * in.rays.co.proj4.exception.DuplicateRecordException;
 * 
 * public class TestModel {
 * 
 * public static void main(String[] args) { // testAdd(); // testUpdate(); //
 * testDelete(); // testFindByPk(); //testFindByName(); testSesarch(); }
 * 
 * public static void testAdd() {
 * 
 * RoleBean bean = new RoleBean(); RoleModel model = new RoleModel();
 * bean.setName("hr"); bean.setDescription("hr"); bean.setCreatedBy("admin");
 * bean.setModifiedBy("admin"); bean.setCreatedDatetime(new Timestamp(new
 * Date().getTime())); bean.setModifiedDatetime(new Timestamp(new
 * Date().getTime()));
 * 
 * try { model.add(bean); } catch (SQLException e) { // TODO Auto-generated
 * catch block e.printStackTrace(); } catch (ApplicationException e) { // TODO
 * Auto-generated catch block e.printStackTrace(); }
 * System.out.println("data added"); }
 * 
 * public static void testUpdate() { RoleBean bean = new RoleBean(); RoleModel
 * model = new RoleModel();
 * 
 * bean.setName("lucky"); bean.setDescription("lucky"); bean.setId(1); try {
 * model.update(bean); System.out.println("Updated successfully"); } catch
 * (ApplicationException e) {
 * 
 * e.printStackTrace(); } }
 * 
 * public static void testDelete() { RoleBean bean = new RoleBean(); RoleModel
 * model = new RoleModel();
 * 
 * bean.setId(1); try { model.delete(bean);
 * System.out.println("deleted successfully"); } catch (ApplicationException e)
 * {
 * 
 * e.printStackTrace(); } }
 * 
 * public static void testFindByPk() { RoleModel model = new RoleModel(); try {
 * RoleBean bean = model.findByPk(1); System.out.println(bean.getName());
 * System.out.println(bean.getDescription());
 * System.out.println(bean.getCreatedDatetime());
 * 
 * } catch (ApplicationException e) {
 * 
 * e.printStackTrace(); }
 * 
 * }
 * 
 * public static void testFindByName() { RoleModel model = new RoleModel();
 * 
 * try { RoleBean bean = model.findByName("hr");
 * System.out.println(bean.getName());
 * System.out.println(bean.getDescription()); System.out.println(bean.getId());
 * 
 * } catch (ApplicationException e) { // TODO Auto-generated catch block
 * e.printStackTrace(); }
 * 
 * }
 * 
 * public static void testSesarch() {
 * 
 * try {
 * 
 * RoleBean bean = new RoleBean(); RoleModel model = new RoleModel();
 * bean.setName("hr"); List list = model.search(bean, 0, 0); if (list.size() <
 * 0) { System.out.println("Test search fail"); } Iterator<RoleBean> it =
 * list.iterator(); while (it.hasNext()) { bean = (RoleBean) it.next();
 * System.out.println(bean.getId()); System.out.println(bean.getName());
 * System.out.println(bean.getDescription()); } } catch (ApplicationException e)
 * { // TODO Auto-generated catch block e.printStackTrace(); }
 * 
 * } }
 */