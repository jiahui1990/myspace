package com.scu.utils;

public class Consts {
	public static final String FIELD_PPC = "碰碰船";
	public static final String FIELD_DDC = "电动船";
	public static final String FIELD_JTC = "脚踏船";
	public static final String FIELD_QT = "汽艇";
	
	public static final String STR_CARD_NOT_USED = "NU";
	
	public static final int MSG_CARD_INTERVAL_NOT_ENOUGH_OF_ANSHI = 16;
	public static final int MSG_CARD_INTERVAL_NOT_ENOUGH_OF_ANCI = 17;
	public static final int MSG_TICKETID_ERROR = 18;
	public static final int MSG_CARD_BEGIN_SUCCESS = 19;
	public static final int MSG_CARD_END_SUCCESS = 20;
	public static final int MSG_CARD_OTHER_NOT_END = 21;
	public static final int MSG_CARD_END_DUPLICATE = 22;
	public static final int MSG_CARD_NOT_KAITONG = 23;
	public static final int MSG_CARD_COST_BY_CI_SUCCESS = 24;
	public static final int MSG_CARD_DULPLICATE_ERROR = 25;
	public static final int MSG_CARD_MONEY_NOT_ENOUGH = 26;
	public static final int MSG_DB_ERROR = 27;
	public static final int MSG_FIELD_ERROR = 28;
	public static final int MSG_READ_CARD_ERROR = 29;
	public static final int MSG_QUERY_CARD_INFO = 30;
	public static final int MSG_MULTIPLAY_WAIT_OF_JISHI = 31;
	public static final int MSG_MULTIPLAY_WAIT_OF_ANCI = 32;
	
	//public static final int MSG_QINGCHANG_SUCCESS = 25;
	
	public static final String SQL_JIFEI = 
				"SELECT dbo.Play_Detail_Minite.id as id, dbo.Play_Detail_Minite.ticket_ID as ticketId"
			+ 		", dbo.Play_Detail_Minite.field as field"
			+ 		", fieldNum = 0"
			+ 		", dbo.Play_Detail_Minite.ticket_date_begin as ticketDateBegin"
			+ 		", dbo.Play_Detail_Minite.ticket_date_end as ticketDateEnd"
			+		", msg='0',playTime='0'"
			+		", dbo.Field_Money_Xihua.per_money + CEILING((CAST(dbo.Play_Detail_Minite.mini AS float)- dbo.Field_Money_Xihua.per_time) / dbo.Field_Money_Xihua.extra_time"
			+ 			"* SIGN((CAST(dbo.Play_Detail_Minite.mini AS float) - dbo.Field_Money_Xihua.per_time) / dbo.Field_Money_Xihua.extra_time + ABS((CAST(dbo.Play_Detail_Minite.mini AS float)"
			+ 			"- dbo.Field_Money_Xihua.per_time) / dbo.Field_Money_Xihua.extra_time))) * dbo.Field_Money_Xihua.extra_money AS total"
			+		", dbo.Play_Detail_Minite.mini as mini"
			+ 	" FROM dbo.Play_Detail_Minite"
			+ 	 	" INNER JOIN dbo.Field_Money_Xihua ON dbo.Play_Detail_Minite.field = dbo.Field_Money_Xihua.field"
			+    	" LEFT OUTER JOIN dbo.Sale_Ticket_Form ON dbo.Play_Detail_Minite.ticket_ID = dbo.Sale_Ticket_Form.ticket_ID"
			+	" WHERE dbo.Play_Detail_Minite.ticket_ID=? and dbo.Play_Detail_Minite.field=? order by dbo.Play_Detail_Minite.id desc";
	
}
