package com.javalec.guestbook.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



import org.apache.ibatis.session.SqlSession;

import com.javalec.guestbook.util.SqlSessionFactoryBean;
import com.javalec.guestbook.vo.GuestBookVO;

public class GuestBookDAO {

	private SqlSession mybatis;
	
	public GuestBookDAO() {
		mybatis = SqlSessionFactoryBean.getSqlSession();
	}
	
	private Connection getConnection() {
		Connection conn = null;
		try {
		
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "scott", "tiger");
		} catch (Exception e) {
			e.printStackTrace();
		
		} 
		return conn;
	}

	public void insert(GuestBookVO vo) {
		mybatis.insert("GuestBookDAO.insert", vo);
	}

	public void delete(GuestBookVO vo) {
		mybatis.delete("GuestBookDAO.delete", vo);

	}

	public GuestBookVO getGuestBook(GuestBookVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//GuestBookVO resultVO = new GuestBookVO();

		try {
			conn = getConnection();
			String sql = "SELECT NO, NAME, CONTENT, PASSWORD, to_char(reg_date, 'yyyy-mm-dd hh:mi:ss') FROM GUESTBOOK WHERE NO=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getNo());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int guestNo = rs.getInt(1);
				String name = rs.getString(2);
				String content = rs.getString(3);
				String password = rs.getString(4);
				String reg_date = rs.getString(5);

				vo.setNo(guestNo);
				vo.setName(name);
				vo.setContent(content);
				vo.setPassword(password);
				vo.setReg_date(reg_date);
			}
		} catch (SQLException e) {

		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return vo;
	}

	public List<GuestBookVO> getGuestBookList() {
		return mybatis.selectList("GuestBookDAO.getList");
	}

	public int update(GuestBookVO vo) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn=getConnection();
			String sql = "UPDATE GUESTBOOK SET NAME=?, CONTENT=?, PASSWORD=?, REG_DATE=SYSDATE WHERE NO=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getPassword());
			pstmt.setInt(4, vo.getNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
	
	public List<GuestBookVO> search(String keyword) {
		List<GuestBookVO> list = new ArrayList<GuestBookVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			String sql = "select no, name, content, password, to_char(reg_date, 'yyyy-mm-dd hh:mi:ss') from guestbook where content like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				GuestBookVO vo = new GuestBookVO();
				int no = rs.getInt(1);
				String name = rs.getString(2);
				String content = rs.getString(3);
				String password = rs.getString(4);
				String reg_date = rs.getString(5);

				vo.setNo(no);
				vo.setName(name);
				vo.setContent(content);
				vo.setPassword(password);
				vo.setReg_date(reg_date);
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public List<GuestBookVO> nameSearch(String keyword) {
		List<GuestBookVO> list = new ArrayList<GuestBookVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			String sql = "select no, name, content, password, to_char(reg_date, 'yyyy-mm-dd hh:mi:ss') from guestbook where name like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				GuestBookVO vo = new GuestBookVO();
				int no = rs.getInt(1);
				String name = rs.getString(2);
				String content = rs.getString(3);
				String password = rs.getString(4);
				String reg_date = rs.getString(5);

				vo.setNo(no);
				vo.setName(name);
				vo.setContent(content);
				vo.setPassword(password);
				vo.setReg_date(reg_date);
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
