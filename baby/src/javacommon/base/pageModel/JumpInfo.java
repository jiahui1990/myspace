package javacommon.base.pageModel;


import javacommon.base.dao.IMsgtipDao;
import javacommon.base.model.Msgtip;
import javacommon.base.model.SystemLog;
import javacommon.base.service.impl.BaseServiceImpl;


/**
 * Title:返回页面对象
 * 
 * @author yf
 * @version Revision: 1.0 Date: 2014/03/17
 */

public class JumpInfo{

	/**
	 * 提示信息Dao
	 */
	private IMsgtipDao msgtipDao;

	public JumpInfo() {
	};
	/**
	 * 返回页面的url地址
	 */
	private String url;
	/**
	 * 返回的提示信息
	 */
	private String info;
	/**
	 * 标识方法正确还是错误
	 */
	private String isSuccess;

	public String getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(String isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	/**
	 * 提示信息编码
	 */
	private String flag;

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.msgtipDao =  BaseServiceImpl.getMsgtipsDao();
		//this.theSystemLogDao =  BaseServiceImpl.getTheSystemLogsDao();
		this.flag = flag;
		Msgtip msg = new Msgtip();
		msg.setName(this.getFlag());
		msg = msgtipDao.findMsgtip(msg);
		if (msg!= null) {
			this.url = msg.getReturnurl();
			this.info = msg.getReturnmsg();
			this.isSuccess=msg.getGlobalVariableByIssuccess().getNumbers();
		}
	}
	/**输入flag参数，systemlog参数，更新输出对象里面的msgTip对象，并且自动保存日志
	 * @param flag
	 * @param systemlog
	 */
	public void setFlag(String flag, SystemLog systemlog) {
		this.msgtipDao =  BaseServiceImpl.getMsgtipsDao();
		//this.theSystemLogDao =  BaseServiceImpl.getTheSystemLogsDao();
		this.flag = flag;
		Update(systemlog);
		
	}

	/**
	 * 根据输入的错误编码转入为统一的错误编码和信息
	 * 
	 * @param errorcode
	 *            错误编码
	 */
	
	private void Update(SystemLog systemlog) {
		
		Msgtip msg = new Msgtip();
		msg.setName(this.getFlag());
		msg = msgtipDao.findMsgtip(msg);
		if (msg!= null) {
			this.url = msg.getReturnurl();
			this.info = msg.getReturnmsg();
			this.isSuccess=msg.getGlobalVariableByIssuccess().getNumbers();
			if (msg.getGlobalVariableByIslog().getNumbers().equals("MDGV17")) {
				//systemlog.setMsgtip(msg);
				//theSystemLogDao.saveSystemLog(systemlog);
			}
		}
	}
}
