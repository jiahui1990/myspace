/**
* Title:管理员模块数据处理类 
* Description: 两个数相加
* Copyright: Copyright (c) 2011
* Company:川大计算机学院 
* @author 张三
* @version Revision: 1.7 Date: 2007/07/08
*/

package javacommon.base.dao.impl;

import javacommon.base.dao.BaseDao;
import javacommon.base.dao.IMsgtipDao;
import javacommon.base.model.Msgtip;

/**
 * Title:MsgtipDao实现类
 * @author yufei
 * @version Revision: 1.0 Date: 2014/03/19
 */

public class MsgtipDaoImpl extends BaseDao<Msgtip> implements IMsgtipDao{
	@Override
	public Msgtip findMsgtip(Msgtip name) {
		// 取hql语句
		String hql = "FROM Msgtip o where o.name=:name";
		params.clear();
		params.put("name", name.getName());
		return super.get(hql, params);
	}

}
