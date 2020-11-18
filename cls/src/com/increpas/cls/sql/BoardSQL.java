package com.increpas.cls.sql;

public class BoardSQL {

	public final int SEL_BOARD_LIST = 1001;
	public final int SEL_MEMB_LIST = 1002;
	public final int SEL_BOARD_CNT = 1003;
	
	public final int ADD_BOARD = 3001;
	public final int ADD_FILE = 3002;

	public String getSQL(int code) {
		StringBuffer buff = new StringBuffer();
		
		switch (code) {
		
		case SEL_BOARD_LIST:
			buff.append("SELECT ");
			buff.append("    rno, bno, id, title, bdate, bclick, NVL(cnt, 0) cnt ");
			buff.append("FROM ");
			buff.append("    ( ");
			buff.append("    	SELECT ");
			buff.append("        	rownum rno, b.* ");
			buff.append("    	FROM ");
			buff.append("        	( ");
			buff.append("            	SELECT ");
			buff.append("                	bno, id, title, bdate, bclick ");
			buff.append("            	FROM ");
			buff.append("                	board, member ");
			buff.append("            	WHERE ");
			buff.append("                	mno = bmno ");
			buff.append("                	AND bisshow = 'Y' ");
			buff.append("            	order by ");
			buff.append("                	bdate DESC ");
			buff.append("        	 ) b ");
			buff.append("        ), ");
			buff.append("    (SELECT ");
			buff.append("        fbno, 	COUNT(*) cnt ");
			buff.append("    FROM ");
			buff.append("        fileinfo ");
			buff.append("    GROUP BY ");
			buff.append("        fbno) ");
			buff.append("WHERE ");
			buff.append("    bno = fbno(+) ");
			buff.append("    AND rno BETWEEN ? AND ? ");
			buff.append("ORDER BY ");
			buff.append("    rno ");
			break;
			
		case SEL_MEMB_LIST:
			buff.append("SELECT ");
			buff.append("	id ");
			buff.append("FROM ");
			buff.append("	member ");
			buff.append("WHERE ");
			buff.append("	isshow = 'Y' ");
			break;
		
		case SEL_BOARD_CNT:
			buff.append(" select ");
			buff.append(" 	count(*) cnt ");
			buff.append(" from ");
			buff.append(" 	board ");
			buff.append(" where ");
			buff.append(" 	bisshow = 'Y' ");
			break;
			
		case ADD_BOARD:
			buff.append(" insert into ");
			buff.append("	board (bno, title, body, bmno) ");
			buff.append(" values ( ");
			buff.append("	(select nvl(max(bno) + 1, 10001) from board), ");
			buff.append("	?, ");
			buff.append("	?, ");
			buff.append("	(select mno from member where id = ?) ");
			buff.append(" ) ");
			break;

		case ADD_FILE:
			buff.append("INSERT INTO ");
			buff.append("    fileinfo(fno, fbno, dir, oriname, savename, len) ");
			buff.append("VALUES( ");
			buff.append("    (SELECT NVL(MAX(fno) + 1, 1000001) FROM fileinfo), ");
			buff.append("    ( ");
			buff.append("        SELECT ");
			buff.append("            MAX(bno) ");
			buff.append("        FROM ");
			buff.append("            board , member ");
			buff.append("        WHERE ");
			buff.append("            bmno = mno ");
			buff.append("            AND id = ? ");
			buff.append("    ), '/img/upload/' , ? , ? , ? ");
			buff.append(") ");
			break;

		default:
			break;
		}
		return buff.toString();
	}

}
