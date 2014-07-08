package com.weirq.db;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

import com.weirq.vo.Menu;

public class HdfsDB {

	private static final String ROOT = "/";
	static FileSystem fs;

	private static class HdfsDBInstance {
		private static final HdfsDB instance = new HdfsDB();
	}

	public static HdfsDB getInstance() {
		return HdfsDBInstance.instance;
	}

	private HdfsDB() {
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://h1:9000");
		try {
			fs = FileSystem.get(conf);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void upload(String filePath, String dir) throws Exception {
		InputStream in = new BufferedInputStream(new FileInputStream(filePath));
		OutputStream out = fs.create(new Path(ROOT + dir), new Progressable() {

			@Override
			public void progress() {
				System.out.println("ok");
			}
		});
		IOUtils.copyBytes(in, out, 4096, true);
	}

	public void mkdir(String dir) throws Exception {
		if (!fs.exists(new Path(ROOT+dir))) {
			fs.mkdirs(new Path(ROOT+dir));
		}
	}

	public void queryAll(String dir) throws Exception {
		FileStatus[] files = fs.listStatus(new Path(ROOT + dir));
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				System.out.println(files[i].getPath().getName() + "目录");
				System.out.println(files[i].getPath() + "目录");
				queryAll(files[i].getPath().toString());
			} else if (files[i].isFile()) {
				System.out.println(files[i].getPath().getName() + "文件");
			}
		}
	}
	static List<Menu> menus = new ArrayList<Menu>();
	public static void visitPath(String path) throws IOException {
		Menu menu = null;
		Path inputDir = new Path(path);
		FileStatus[] inputFiles = fs.listStatus(inputDir);
		if (inputFiles.length>0) {
			for (int i = 0; i < inputFiles.length; i++) {
				menu = new Menu();
				if (inputFiles[i].isDirectory()) {
					menu.setPname(inputFiles[i].getPath().getName());
					menus.add(menu);
					visitPath(inputFiles[i].getPath().toString());
				} else {
					menu.setName(inputFiles[i].getPath().getName());
//					menus.add(menu);
//					System.out.println(inputFiles[i].getPath().getName() + ",len:" + inputFiles[i].getLen());
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		HdfsDB hdfsDB = new HdfsDB();
//		hdfsDB.mkdir(ROOT+"weir/qq");

		// String path = "C://Users//Administrator//Desktop//jeeshop-jeeshop-master.zip";
		// hdfsDB.upload(path, "weir/"+"jeeshop.zip");
		// hdfsDB.queryAll(ROOT);
		hdfsDB.visitPath("hdfs://h1:9000/weir");
		for (Menu menu : menus) {
			System.out.println(menu.getName());
			System.out.println(menu.getPname());
		}
		System.out.println("ok");
	}
}
