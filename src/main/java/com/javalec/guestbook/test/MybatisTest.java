package com.javalec.guestbook.test;

import java.util.List;

import com.javalec.guestbook.dao.GuestBookDAO;
import com.javalec.guestbook.vo.GuestBookVO;

public class MybatisTest {

	public static void main(String[] args) {
		GuestBookDAO dao = new GuestBookDAO();
		List<GuestBookVO> list = dao.getGuestBookList();
		
		for(GuestBookVO gb : list) {
			System.out.println(gb.toString());
		}

	}

}
