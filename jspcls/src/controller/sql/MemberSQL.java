package controller.sql;

public class MemberSQL {

	public final int SEL_LOGIN_CNT = 1001;

	public String getSQL(int code) {
		String sql = "";
		StringBuffer buff = new StringBuffer();
		switch (code) {

		case SEL_LOGIN_CNT:

			buff.append(" SELECT ");
			buff.append("	count(*) cnt ");
			buff.append(" FROM ");
			buff.append("	member ");
			buff.append(" WHERE ");
			buff.append("	id = ? AND ");
			buff.append("	pw = ?");
			sql = buff.toString();
			break;

		default:
			break;
		}
		return sql;
	}

}
