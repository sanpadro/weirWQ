package com.weirq.mvc;

import com.weirq.db.HbaseDB;
import com.weirq.db.HdfsDB;

public class BaseController {

	public HbaseDB db = HbaseDB.getInstance();
	public HdfsDB hdfsDB = HdfsDB.getInstance();
}
