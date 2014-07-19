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
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.QualifierFilter;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.apache.hadoop.hbase.util.Bytes;

import com.weirq.util.DateUtil;
import com.weirq.util.SiteUrl;
import com.weirq.vo.FileSystemVo;
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
		conf.set("hbase.zookeeper.quorum", SiteUrl.readUrl("host"));
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
	
	public static long getGid() throws Exception {
		HTable table_gid = new HTable(TableName.valueOf("gid"), connection);
		long id = table_gid.incrementColumnValue(Bytes.toBytes("gid"), Bytes.toBytes("gid"), Bytes.toBytes("gid"), 1);
		table_gid.close();
		return id;
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
	/**
	 * 根据row删除数据
	 * @param tableName
	 * @param rowKey
	 * @throws Exception
	 */
	public static void deleteRow(String tableName, String[] rowKey) throws Exception {
		HTable table = new HTable(TableName.valueOf(tableName), connection);
		List<Delete> list = new ArrayList<Delete>();
		for (int i = 0; i < rowKey.length; i++) {
			Delete delete = new Delete(Bytes.toBytes(Long.valueOf(rowKey[i])));
			list.add(delete);
		}
		table.delete(list);
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
		}
		table.close();
	}
	
	public static List<FileSystemVo> getFile(String dir) throws Exception {
		HTable fileTable = new HTable(TableName.valueOf("filesystem"), connection);
		Scan scan = new Scan();
		Filter filter = new QualifierFilter(CompareOp.LESS_OR_EQUAL, new SubstringComparator(dir));
		scan.setFilter(filter);
		ResultScanner rs = fileTable.getScanner(scan);
		List<FileSystemVo> fs = new ArrayList<FileSystemVo>();
		FileSystemVo f = null;
		for (Result r : rs) {
			Cell cellName = r.getColumnLatestCell(Bytes.toBytes("files"), Bytes.toBytes("name"));
			Cell cellPdir = r.getColumnLatestCell(Bytes.toBytes("files"), Bytes.toBytes("pdir"));
			Cell cellType = r.getColumnLatestCell(Bytes.toBytes("files"), Bytes.toBytes("type"));
			Cell cellSize = r.getColumnLatestCell(Bytes.toBytes("files"), Bytes.toBytes("size"));
			f = new FileSystemVo();
			f.setId(Bytes.toLong(r.getRow()));
			f.setDir(dir);
			f.setName(Bytes.toString(CellUtil.cloneValue(cellName)));
			if (cellSize!=null) {
				f.setSize(Bytes.toString(CellUtil.cloneValue(cellSize)));
			}
			if(cellPdir!=null){
				f.setPdir(Bytes.toString(CellUtil.cloneValue(cellPdir)));
			}
			if (cellType!=null) {
				f.setType(Bytes.toString(CellUtil.cloneValue(cellType)));
			}
			f.setDate(DateUtil.longToString("yyyy-MM-dd HH:mm", cellName.getTimestamp()));
			fs.add(f);
		}
		fileTable.close();
		return fs;
	}
	public static void delByDir(String dir) throws Exception {
		HTable fileTable = new HTable(TableName.valueOf("filesystem"), connection);
		Scan scan = new Scan();
		Filter filter = new QualifierFilter(CompareOp.LESS_OR_EQUAL, new BinaryComparator(Bytes.toBytes(dir)));
		scan.setFilter(filter);
		ResultScanner rs = fileTable.getScanner(scan);
		for (Result r : rs) {
			fileTable.delete(new Delete(r.getRow()));
		}
		fileTable.close();
	}
	
	public static void main(String[] args) throws Exception {
//		HbaseDB db = new HbaseDB();
		
		System.out.println("ok");
	}
}
