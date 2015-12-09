package com.hanains.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hanains.mysite.vo.BoardVo;
import com.hanains.mysite.vo.GuestBookVo;



public class BoardDAO {

	private static final String ORCLE_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String CONNECT_DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String DB_ID = "webdb";
	private static final String DB_PASSWORD = "webdb";
	
	private static final String INSERT_BOARD_QUERY = " insert "
												   + " into board "
												   + " values ( board_no_seq.nextval, ?, ?, ?, 0, SYSDATE )";
	
	private static final String SELECT_BOARD_QUERY = "select a.no, a.title, a.member_no, b.name "
													+ "as member_name, a.view_cnt, to_char(a.reg_date, 'yyyy-mm-dd hh:mi:ss')"
													+ "from board a, member b where a.member_no = b.no order by a.reg_date desc;";
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			// Load Driver(Class Dynamic Loading)
			Class.forName(ORCLE_DRIVER);

			// Connect DB
			String url = CONNECT_DB_URL;
			conn = DriverManager.getConnection(url, DB_ID, DB_PASSWORD);

		} catch (ClassNotFoundException e) {
			System.out.println("[error] Fail Diver loading :" + e);
		}

		return conn;
	}
	
	public List<BoardVo> getList() {

		List<BoardVo> list = new ArrayList<BoardVo>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			// Create Statement
			stmt = conn.createStatement();

			// Execute SQL
			rs = stmt.executeQuery(SELECT_BOARD_QUERY); 
											// executeUpdate
			while (rs.next()) {
				int index = 1;
				Long no = rs.getLong(index++);
				String title = rs.getString(index++);
				Long memberNo = rs.getLong(index++);
				String memberName = rs.getString(index++);
				Long viewCount = rs.getLong(index++);
				String date = rs.getString(index);

				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setMemberNo(memberNo);
				vo.setViewCount(viewCount);
				vo.setRegDate(date);
				
				list.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("[error] SQL :" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	
	public void insert(BoardVo vo) {

		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			// Ready Statement
			pstmt = conn.prepareStatement(INSERT_BOARD_QUERY);

			// binding
			int index = 1;
			pstmt.setString(index++, vo.getTitle());
			pstmt.setString(index++, vo.getContent());
			pstmt.setLong(index, vo.getNo());

			// Execute SQL
			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("[error] SQL :" + e);
		} finally {

			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
	
	
}
