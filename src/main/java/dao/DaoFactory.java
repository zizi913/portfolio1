package dao;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DaoFactory {
	
	public static MemberDao createMemberDao() {
		return new MemberDaoImpl(getDataSource());
	}
	
	public static ShiftDao createShiftDao() {
		return new ShiftDaoImpl(getDataSource());
	}
	
	public static ThreadDao createThreadDao() {
		return new ThreadDaoImpl(getDataSource());
	}
	
	private static DataSource getDataSource() {
		InitialContext ctx=null;
		DataSource ds= null;
		try {
			ctx=new InitialContext();
			ds=(DataSource)ctx.lookup("java:comp/env/jdbc/shift_db");
		}catch(NamingException e) {
			try {
				ctx.close();
			}catch(NamingException e1) {
				throw new RuntimeException(e1);
			}
			throw new RuntimeException(e);
		}
		return ds;
	}


}
