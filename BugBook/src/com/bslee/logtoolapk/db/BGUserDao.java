package com.bslee.logtoolapk.db;

import java.util.List;

import org.litepal.crud.DataSupport;

import com.bslee.logtoolapk.model.BGUser;

public class BGUserDao {

	/**
	 * 分页查询
	 * 
	 * @param num
	 *            >=1
	 * @param page
	 *            >=1
	 * @return
	 */
	public static List<BGUser> findPage(int num, int page) {
		return DataSupport.limit(num).offset(page * 1 - 1).find(BGUser.class);
	}

	public static List<BGUser> findAll() {
		return DataSupport.findAll(BGUser.class);
	}

	public static boolean clear() {
		return DataSupport.deleteAll(BGUser.class, "") >= 0;
	}

}
