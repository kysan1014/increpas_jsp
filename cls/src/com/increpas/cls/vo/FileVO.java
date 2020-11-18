package com.increpas.cls.vo;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

public class FileVO {

	private int fno, bno, fcount;
	private long len;
	private String id, oriname, savename, dir, sdate;
	private Date fdate;
	private Time ftime;

	public int getFno() {
		return fno;
	}

	public void setFno(int fno) {
		this.fno = fno;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public int getFcount() {
		return fcount;
	}

	public void setFcount(int fcount) {
		this.fcount = fcount;
	}

	public long getLen() {
		return len;
	}

	public void setLen(long len) {
		this.len = len;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOriname() {
		return oriname;
	}

	public void setOriname(String oriname) {
		this.oriname = oriname;
	}

	public String getSavename() {
		return savename;
	}

	public void setSavename(String savename) {
		this.savename = savename;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getSdate() {
		return sdate;
	}
	public void setSdate() {
		this.sdate = strForm(this.fdate, this.ftime);
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public Date getFdate() {
		return fdate;
	}

	public void setFdate(Date fdate) {
		this.fdate = fdate;
	}

	public Time getFtime() {
		return ftime;
	}

	public void setFtime(Time ftime) {
		this.ftime = ftime;
		setSdate();
	}

	public String strForm(Date d, Time t) {
		SimpleDateFormat form1 = new SimpleDateFormat("yyyy/MM/dd ");
		SimpleDateFormat form2 = new SimpleDateFormat("HH:mm:ss");
		return form1.format(d) + form2.format(t);
	}
	
	@Override
	public String toString() {
		return "FileVO [fno=" + fno + ", bno=" + bno + ", fcount=" + fcount + ", len=" + len + ", id=" + id
				+ ", oriname=" + oriname + ", savename=" + savename + ", dir=" + dir + ", sdate=" + sdate + ", fdate="
				+ fdate + ", ftime=" + ftime + "]";
	}

}
