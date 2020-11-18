package com.increpas.cls.vo;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class BoardVO {

	private int bno, mno, ano, bclick, cnt;
	private String title, body, id, avatar, sdate;
	private Date wdate;
	private Time wtime;
	private ArrayList<String> filekeys;
	private ArrayList<FileVO> list;

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public int getBclick() {
		return bclick;
	}

	public void setBclick(int bclick) {
		this.bclick = bclick;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate() {
		this.sdate = strForm(this.wdate, this.wtime);
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public Date getWdate() {
		return wdate;
	}

	public void setWdate(Date wdate) {
		this.wdate = wdate;
	}

	public Time getWtime() {
		return wtime;
	}

	public void setWtime(Time wtime) {
		this.wtime = wtime;
		setSdate();
	}

	public ArrayList<FileVO> getList() {
		return list;
	}

	public void setList(ArrayList<FileVO> list) {
		this.list = list;
	}

	public ArrayList<String> getFilekeys() {
		return filekeys;
	}

	public void setFilekeys(ArrayList<String> filekeys) {
		this.filekeys = filekeys;
	}

	public String strForm(Date d, Time t) {
		SimpleDateFormat form1 = new SimpleDateFormat("yyyy/MM/dd ");
		SimpleDateFormat form2 = new SimpleDateFormat("HH:mm");
		return form1.format(d) + form2.format(t);
	}

}
