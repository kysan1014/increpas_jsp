package com.increpas.cls.sql;

public class MemberSQL {

	public final int SEL_LOGIN_CNT = 1001;
	public final int SEL_ID_CNT = 1002;
	public final int SEL_AVT_ALL = 1003;
	public final int SEL_MEMB_INFO = 1004;
	public final int EDIT_MEMB = 2001;
	public final int DEL_MEMB = 2002;
	public final int ADD_MEMB = 3001;

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
			buff.append("	pw = ? AND ");
			buff.append("	isshow = 'Y' ");
			sql = buff.toString();
			break;
			
		case SEL_ID_CNT:
			
			buff.append(" SELECT ");
			buff.append("	count(*) cnt ");
			buff.append(" FROM ");
			buff.append("	member ");
			buff.append(" WHERE ");
			buff.append("	id = ?");
			sql = buff.toString();
			break;
		
		case SEL_AVT_ALL:
			
			buff.append(" SELECT ");
			buff.append("	ano, afile sname, gen ");
			buff.append(" FROM ");
			buff.append("	avatar");
			sql = buff.toString();
			break;
			
		case SEL_MEMB_INFO:
			
			buff.append(" SELECT ");
			buff.append("	m.mno, m.name, m.mail, m.gen, m.joindate, m.avt, a.afile avatar");
			buff.append(" FROM ");
			buff.append("	member m, avatar a ");
			buff.append(" WHERE ");
			buff.append(" 	m.avt = a.ano AND ");
			buff.append(" 	m.id = ? ");
			sql = buff.toString();
			break;
		
		case EDIT_MEMB:
			
			buff.append("UPDATE ");
			buff.append("	member ");
			buff.append("SET ");
			buff.append("	mail = ?, ");
			buff.append("	avt = ? ");
			buff.append("WHERE ");
			buff.append("	id = ? ");
			break;
			
		case ADD_MEMB:
			
			buff.append(" INSERT INTO ");
			buff.append("	member ");
			buff.append("		(mno, id, pw, name, mail, gen, avt, joindate, isshow) ");
			buff.append(" VALUES ");
			buff.append("	(");
			buff.append("		(SELECT NVL(MAX(mno) + 1, 1001) FROM member), ");
			buff.append("	?, ?, ?, ?, ?, ?, sysdate, 'Y')");
			sql = buff.toString();
			break;
		
		case DEL_MEMB:
			buff.append("UPDATE ");
			buff.append("	member ");
			buff.append("SET ");
			buff.append("	isshow = 'N' ");
			buff.append("WHERE ");
			buff.append("	mno = ? ");
			buff.append("	AND pw = ? ");
			break;
			
		default:
			break;
		}
		return sql;
	}

}
