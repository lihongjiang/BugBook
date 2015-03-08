package com.bslee.logtoolapk.db;

import java.util.List;

import org.litepal.crud.DataSupport;

import com.bslee.logtoolapk.model.ExceptionBug;

public class ExceptionBugDao {

	
	
	/**
	 * 分页查询  
	 * @param num   >=1
	 * @param page  >=1
	 * @return
	 */
	public static List<ExceptionBug> findPage(int num, int page) {
		return DataSupport.order("createTime desc").limit(num).offset(page * 1-1)
				.find(ExceptionBug.class);
	}

	
	public static List<ExceptionBug> findAll() {
		return DataSupport.order("createTime desc").find(ExceptionBug.class);
	}

	
	public static boolean clear() {
		return DataSupport.deleteAll(ExceptionBug.class, "") >= 0;
	}

}
