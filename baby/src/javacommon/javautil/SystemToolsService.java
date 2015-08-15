package javacommon.javautil;

public class SystemToolsService {
	/**
	 * 功能：取得业务的名称 输入参数： 返回值：业务名称
	 * 
	 * @return String
	 * @roseuid 538F226000EC
	 */
	public String getActionName() {
		return null;
	}

	/**
	 * 功能：取得本地IP 输入参数： 返回值：IP地址 关联函数： 作者：
	 * 
	 * @return String
	 * @roseuid 538F2295031A
	 */
	public String getLocalIP() {
		return null;
	}

	/**
	 * 功能：将数据导成Excel表 输入参数：小编也不知道要输入什么，自己添加吧 返回值：MsgTip 关联函数： 作者：
	 * 
	 * @return String
	 * @roseuid 538F22F30216
	 */
	public String toExcel() {
		return null;
	}

	/**
	 * 任务的佣金提取方法
	 * @param money 任务的实际成交金额
	 * @return 输出参数 任务的佣金
	 */
	public Double calculateMoney(Double money) {
		money = money * 0.1;
		return money;
	}
	
}
