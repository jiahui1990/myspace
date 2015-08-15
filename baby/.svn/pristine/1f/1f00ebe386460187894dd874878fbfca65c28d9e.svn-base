package javacommon.dataAdapter;

public class DBContextHolder {
		public static final String ORACLE = "Oracle";     
	    public static final String SQLSERVER = "SQLSERVER";     
	    public static final String MYSQL = "MYSQL";     
	    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();     
	         
	    public static void setDBType(String dbType) {     
	        contextHolder.set(dbType);     
	    }     
	          
	    public static String getDBType() {     
	        return contextHolder.get();     
	    }     
	         
	    public static void clearDBType() {     
	        contextHolder.remove();     
	    }     
}
