package com.weirq.db;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.client.HConnectionManager;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;

import com.weirq.vo.Menu;


public class HbaseDB  implements Serializable{
	private static final long serialVersionUID = -7137236230164276653L;
	static HConnection connection;
	
	private static class HbaseDBInstance{
		private static final HbaseDB instance = new HbaseDB();
	}
	public static HbaseDB getInstance() {
		return HbaseDBInstance.instance;
	}
	private HbaseDB() {
		Configuration conf = HBaseConfiguration.create();
		conf.set("hbase.zookeeper.quorum", "h1:9000");
		try {
			connection = HConnectionManager.createConnection(conf);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Object readResolve(){
		return getInstance();
	}
	/**
	 * 获取所有表
	 * @return
	 * @throws Exception
	 */
	public static TableName[] listTable() throws Exception {
		HBaseAdmin admin = new HBaseAdmin(connection);
		TableName[] tableNames = admin.listTableNames();
		admin.close();
		return tableNames;
	}
	/**
	 * 删除所有表
	 */
	public static void deleteAllTable() throws Exception{
		HBaseAdmin admin = new HBaseAdmin(connection);
		TableName[] tableNames = admin.listTableNames();
		for (int i = 0; i < tableNames.length; i++) {
			admin.disableTable(tableNames[i].getNameAsString());
			admin.deleteTable(tableNames[i].getNameAsString());
		}
		admin.close();
	}
	/**
	 * 创建表
	 * @param tableName
	 * @param fams
	 * @throws Exception
	 */
	public static void createTable(String tableName,String[] fams) throws Exception {
		HBaseAdmin admin = new HBaseAdmin(connection);
		if (admin.tableExists(tableName)) {
			admin.disableTable(tableName);
			admin.deleteTable(tableName);
		}
		HTableDescriptor tableDescriptor = new HTableDescriptor(TableName.valueOf(tableName));
		for (int i = 0; i < fams.length; i++) {
			tableDescriptor.addFamily(new HColumnDescriptor(fams[i]));
		}
		admin.createTable(tableDescriptor);
		admin.close();
	}
	public static void delTable(String tableName) throws Exception {
		HBaseAdmin admin = new HBaseAdmin(connection);
		if (admin.tableExists(tableName)) {
			admin.disableTable(tableName);
			admin.deleteTable(tableName);
		}
		admin.close();
	}
	/**
	 * 初始化用户表
	 * @throws Exception
	 */
	public void initTable() throws Exception{
//		String table_gid = "gid";
//		String[] fam_gid = {"gid"};
//		db.createTable(table_gid, fam_gid);
//		
//		String table_id = "id_user";
//		String[] fam_id = {"user"};
//		db.createTable(table_id, fam_id);
//		
//		String table_user = "user_id";
//		String[] fam_user = {"id"};
//		db.createTable(table_user, fam_user);
//		
//		String table_email = "email_user";
//		String[] fam_email = {"user"};
//		db.createTable(table_email, fam_email);
//		
//		db.add(table_gid, "gid", "gid", "gid", (long)0);
		
	}
	
	public void initEmun() throws Exception {
//		String table_emun = "emun";
//		String[] fam_emun = {"emun"};
//		db.createTable(table_emun, fam_emun);
//		HTable table_gid = new HTable(TableName.valueOf("gid"), connection);
//		long id = table_gid.incrementColumnValue(Bytes.toBytes("gid"), Bytes.toBytes("gid"), Bytes.toBytes("gid"), 1);
//		db.add(table_emun, id, "emun", "name", "菜单管理");
//		db.add(table_emun, id, "emun", "url", "/emun/list.do");
//		long id02 = table_gid.incrementColumnValue(Bytes.toBytes("gid"), Bytes.toBytes("gid"), Bytes.toBytes("gid"), 1);
//		db.add(table_emun, id02, "emun", "name", "云盘");
//		db.add(table_emun, id02, "emun", "url", "/cloud/list.do");
	}
	
	public static long getGid() throws Exception {
		HTable table_gid = new HTable(TableName.valueOf("gid"), connection);
		long id = table_gid.incrementColumnValue(Bytes.toBytes("gid"), Bytes.toBytes("gid"), Bytes.toBytes("gid"), 1);
		table_gid.close();
		return id;
	}
//	/**
//	 * 初始化用户
//	 * @throws Exception
//	 */
//	public void initUser() throws Exception{
//		db.add("user_id", "admin", "id", "id", id);
//		db.add("id_user", id, "user", "name", "admin");
//		db.add("id_user", id, "user", "pwd", "336393");
//		db.add("id_user", id, "user", "email", "634623907@qq.com");
//		db.add("email_user", "634623907@qq.com", "user", "userid", id);
//	}
	
	/**
	 * 添加数据
	 * @param tableName
	 * @param rowKey
	 * @param family
	 * @param qualifier
	 * @param value
	 * @throws IOException
	 */
	public static void add(String tableName, String rowKey, String family, String qualifier, String value) throws IOException {
		//连接到table
		HTable table = new HTable(TableName.valueOf(tableName), connection);
		Put put = new Put(Bytes.toBytes(rowKey));
		put.add(Bytes.toBytes(family), Bytes.toBytes(qualifier), Bytes.toBytes(value));
		table.put(put);
		table.close();
	}
	/**
	 * 添加数据
	 * @param tableName
	 * @param rowKey
	 * @param family
	 * @param qualifier
	 * @param value
	 * @throws IOException
	 */
	public static void add(String tableName, Long rowKey, String family, String qualifier, String value) throws IOException {
		//连接到table
		HTable table = new HTable(TableName.valueOf(tableName), connection);
		Put put = new Put(Bytes.toBytes(rowKey));
		put.add(Bytes.toBytes(family), Bytes.toBytes(qualifier), Bytes.toBytes(value));
		table.put(put);
		table.close();
	}
	/**
	 * 添加数据
	 * @param tableName
	 * @param rowKey
	 * @param family
	 * @param qualifier
	 * @param value
	 * @throws IOException
	 */
	public static void add(String tableName, Long rowKey, String family, String qualifier, Long value) throws IOException {
		//连接到table
		HTable table = new HTable(TableName.valueOf(tableName), connection);
		Put put = new Put(Bytes.toBytes(rowKey));
		put.add(Bytes.toBytes(family), Bytes.toBytes(qualifier), Bytes.toBytes(value));
		table.put(put);
		table.close();
	}
	/**
	 * 添加数据
	 * @param tableName
	 * @param rowKey
	 * @param family
	 * @param qualifier
	 * @param value
	 * @throws IOException
	 */
	public static void add(String tableName, String rowKey, String family, String qualifier, Long value) throws IOException {
		//连接到table
		HTable table = new HTable(TableName.valueOf(tableName), connection);
		Put put = new Put(Bytes.toBytes(rowKey));
		put.add(Bytes.toBytes(family), Bytes.toBytes(qualifier), Bytes.toBytes(value));
		table.put(put);
		table.close();
	}
	
	public static void deleteRow(String tableName, String rowKey) throws Exception {
		HTable table = new HTable(TableName.valueOf(tableName), connection);
		Delete delete = new Delete(rowKey.getBytes());
		table.delete(delete);
		table.close();
	}
	
	public static long getIdByUsername(String name) {
		long id = 0;
		try {
			HTable table = new HTable(TableName.valueOf("user_id"), connection);
			Get get = new Get(name.getBytes());
			get.addColumn(Bytes.toBytes("id"), Bytes.toBytes("id"));
			Result rs = table.get(get);
			byte[] value = rs.getValue(Bytes.toBytes("id"), Bytes.toBytes("id"));
			id = Bytes.toLong(value);
			table.close();
		} catch (IOException e) {
			e.printStackTrace();
			return id;
		}
		return id;
	}
	public static boolean checkUsername(String name) {
		try {
			HTable table = new HTable(TableName.valueOf("user_id"), connection);
			Get get = new Get(name.getBytes());
			get.addColumn(Bytes.toBytes("id"), Bytes.toBytes("id"));
			Result rs = table.get(get);
			byte[] value = rs.getValue(Bytes.toBytes("id"), Bytes.toBytes("id"));
			table.close();
			if (value!=null) {
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public static String getUserNameById(long id) {
		String name = null;
		try {
			HTable table = new HTable(TableName.valueOf("id_user"), connection);
			Get get = new Get(Bytes.toBytes(id));
			get.addColumn(Bytes.toBytes("user"), Bytes.toBytes("name"));
			Result rs = table.get(get);
			byte[] value = rs.getValue(Bytes.toBytes("user"), Bytes.toBytes("name"));
			name = Bytes.toString(value);
			table.close();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return name;
	}
	public static String getStringById(String tableName,Long rowKey,String family,String qualifier) {
		String name = null;
		try {
			HTable table = new HTable(TableName.valueOf(tableName), connection);
			Get get = new Get(Bytes.toBytes(rowKey));
			get.addColumn(Bytes.toBytes(family), Bytes.toBytes(qualifier));
			Result rs = table.get(get);
			byte[] value = rs.getValue(Bytes.toBytes(family), Bytes.toBytes(qualifier));
			name = Bytes.toString(value);
			table.close();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return name;
	}
	/**
	 * 通过目录名获取ID
	 * @param name
	 * @return
	 */
	public static long getIdByDirName(String name) {
		long id = 0;
		try {
			HTable table = new HTable(TableName.valueOf("hdfs_name"), connection);
			Get get = new Get(name.getBytes());
			get.addColumn(Bytes.toBytes("id"), Bytes.toBytes("id"));
			Result rs = table.get(get);
			byte[] value = rs.getValue(Bytes.toBytes("id"), Bytes.toBytes("id"));
			id = Bytes.toLong(value);
			table.close();
		} catch (IOException e) {
			e.printStackTrace();
			return id;
		}
		return id;
	}
	
	public static boolean checkEmail(String email) throws Exception {
		HTable table = new HTable(TableName.valueOf("email_user"), connection);
		Get get = new Get(Bytes.toBytes(email));
		get.addColumn(Bytes.toBytes("user"), Bytes.toBytes("userid"));
		Result rs = table.get(get);
		byte[] value = rs.getValue(Bytes.toBytes("user"), Bytes.toBytes("userid"));
		table.close();
		if(value!=null){
			return true;
		}else {
			return false;
		}
	}
	
	public long checkUser(String userName,String pwd) throws Exception {
		long id = getIdByUsername(userName);
		if (id==0) {
			return 0;
		}
		HTable table = new HTable(TableName.valueOf("id_user"), connection);
		Get get = new Get(Bytes.toBytes(id));
		get.addColumn(Bytes.toBytes("user"), Bytes.toBytes("pwd"));
		Result rs = table.get(get);
		byte[] value = rs.getValue(Bytes.toBytes("user"), Bytes.toBytes("pwd"));
		if (pwd.equals(Bytes.toString(value))) {
			table.close();
			return id;
		}
		table.close();
		return 0;
	}
	
	public void queryAll(String tableName) throws Exception {
		HTable table = new HTable(TableName.valueOf(tableName), connection);
		ResultScanner rs = table.getScanner(new Scan());
		for (Result result : rs) {
			System.out.println("rowkey" +result.getRow());
			for (Cell cell : result.rawCells()) {
				System.out.println("family"+new String(cell.getFamilyArray()));
				System.out.println("Qualifier"+new String(cell.getQualifierArray()));
				System.out.println("value"+new String(cell.getValueArray()));
			}
		}
		table.close();
	}
	public void queryAllHDFS(String username) throws Exception {
		HTable table = new HTable(TableName.valueOf("hdfs"), connection);
		ResultScanner rs = table.getScanner(new Scan());
		for (Result result : rs) {
			System.out.println("rowkey" +result.getRow());
			for (Cell cell : result.rawCells()) {
				System.out.println("family"+new String(cell.getFamilyArray()));
				System.out.println("Qualifier"+new String(cell.getQualifierArray()));
				System.out.println("value"+new String(cell.getValueArray()));
			}
		}
		table.close();
	}
	
	public static List<Menu> qureyAllEmun() throws Exception {
		HTable table = new HTable(TableName.valueOf("emun"), connection);
		ResultScanner rs = table.getScanner(new Scan());
		List<Menu> menus = new ArrayList<Menu>();
		Menu m = null;
		for (Result r : rs) {
			m = new Menu();
			byte[] name = r.getValue(Bytes.toBytes("emun"), Bytes.toBytes("name"));
			byte[] url = r.getValue(Bytes.toBytes("emun"), Bytes.toBytes("url"));
			m.setName(Bytes.toString(name));
			m.setUrl(Bytes.toString(url));
			m.setText(Bytes.toString(name));
			menus.add(m);
		}
		table.close();
		return menus;
	}
	
	public static void getAllUserTree(Long id) throws Exception {
		HTable table_hdfs = new HTable(TableName.valueOf("hdfs"), connection);
		HTable table = new HTable(TableName.valueOf("hdfs_cid"), connection);
		Get get = new Get(Bytes.toBytes(id));
		Result rs = table.get(get);
//		for (KeyValue kv : rs.raw()) {
//			System.out.println(Bytes.toLong(kv.getValue()));
//		}
		List<Menu> menus = new ArrayList<Menu>();
		Menu menu = null;
		for (Cell cell : rs.rawCells()) {
			Get get1 = new Get(CellUtil.cloneValue(cell));
			get1.addColumn(Bytes.toBytes("dir"), Bytes.toBytes("name"));
			Result rs1 = table_hdfs.get(get1);
			byte[] value = rs1.getValue(Bytes.toBytes("dir"), Bytes.toBytes("name"));
			String name = Bytes.toString(value);
			
			get1.addColumn(Bytes.toBytes("dir"), Bytes.toBytes("type"));
			Result rs2 = table_hdfs.get(get1);
			byte[] type = rs2.getValue(Bytes.toBytes("dir"), Bytes.toBytes("type"));
			String y = Bytes.toString(type);
			menu = new Menu();
			menu.setId(Bytes.toString(CellUtil.cloneValue(cell)));
			menu.setName(name);
			
//			if ("D".equals(y)) {
//				
//			}else if ("F".equals(y)) {
//				
//			}
		}
//		List<Cell> cells = rs.listCells();
//		if (cells!=null) {
//			for (Cell cell : cells) {
//				System.out.println(Bytes.toLong(cell.getValueArray()));
//			}
//		}
		table.close();
	}
	
	public static void main(String[] args) throws Exception {
		HbaseDB db = new HbaseDB();
		
//		db.listTable();
		
//		db.deleteAllTable();
//		db.initTable();
		
//		db.initUser();
//		db.queryAll("user_id");
//		db.initEmun();
		
//		String table_hdfs = "hdfs";
//		String[] fam_hdfs = {"dir"};
//		db.createTable(table_hdfs, fam_hdfs);
		
//		String table_hdfs_name = "hdfs_name";
//		String[] fam_hdfs_id = {"id"};
//		db.createTable(table_hdfs_name, fam_hdfs_id);
//		
//		String table_hdfs_cid = "hdfs_cid";
//		String[] fam_cid = {"cid"};
//		db.createTable(table_hdfs_cid, fam_cid);
		
//		System.out.println(db.checkEmail("21212121"));
		System.out.println("ok");
	}
}
