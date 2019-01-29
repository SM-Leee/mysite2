package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.mysite.vo.BoardVo;


public class BoardDao {

	public boolean update(BoardVo boardVo) {
		boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql = "update board set title=?, contents=? where no="+boardVo.getNo();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, boardVo.getTitle());
			pstmt.setString(2, boardVo.getContents());
			
			int count = pstmt.executeUpdate();
			
			result = count == 1;
			
		} catch (SQLException e) {
			System.out.println("Error : "+e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public boolean updateInsert(BoardVo boardVo) {
		boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			
			String sql = "update board set order_no = (order_no+1) where group_no="+boardVo.getGroup_no()+" and order_no>="+ boardVo.getOrder_no();
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			
			pstmt.close();
			
			sql ="insert into board values(null,?,?, now(),0,?,?,?,"+boardVo.getUser_no()+")";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, boardVo.getTitle());
			pstmt.setString(2, boardVo.getContents());
			pstmt.setInt(3, boardVo.getGroup_no());
			pstmt.setInt(4, boardVo.getOrder_no());
			pstmt.setInt(5, boardVo.getDepth());
			
			
			int count = pstmt.executeUpdate();
			
			result = count == 1;
			
		} catch (SQLException e) {
			System.out.println("Error : "+e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public boolean delete(BoardVo boardVo) {
		boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			
			String sql ="delete from board where no=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, boardVo.getNo());
			
			int count = pstmt.executeUpdate();
			
			result = count == 1;
			
		} catch (SQLException e) {
			System.out.println("Error : "+e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public boolean insert(BoardVo boardVo) {
		boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			
			String sql = "select max(group_no)+1 from board";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				Long max_no = rs.getLong(1);
				
				boardVo.setMax_no(max_no);				
			}
			pstmt.close();
			
			sql ="insert into board values(null,?,?, now(),0,"+boardVo.getMax_no()+",1,0,"+boardVo.getUser_no()+")";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, boardVo.getTitle());
			pstmt.setString(2, boardVo.getContents());
			
			int count = pstmt.executeUpdate();
			
			result = count == 1;
			
		} catch (SQLException e) {
			System.out.println("Error : "+e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	public BoardVo get(int group_no, int order_no) {
		BoardVo boardVo = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			
			String sql = "update board set hit = (hit+1) where group_no="+group_no+" and order_no="+order_no;
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			
			pstmt.close();
			
			sql ="select no, title, contents, user_no from board where group_no="+group_no+" and order_no="+ order_no;
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String contents = rs.getString(3);	
				Long user_no = rs.getLong(4);
				
				boardVo = new BoardVo();
				boardVo.setNo(no);
				boardVo.setTitle(title);
				boardVo.setContents(contents);
				boardVo.setUser_no(user_no);
						
			}
			
			
		} catch (SQLException e) {
			System.out.println("Error : "+e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(rs != null) {
					rs.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return boardVo;
	}
	public List<BoardVo> getList() {
		List<BoardVo> list = new ArrayList<BoardVo>();
				
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			
			String sql = "select c.* from(" + 
					" select a.*,  @rownum := @rownum+1 as rownum, floor(((@rownum-1)/5)+1) as page from " + 
					" (select a.no, a.title, a.contents, a.write_date, a.hit, a.group_no, a.order_no, a.depth,a.user_no ,b.name " + 
					"			from board a, user b " + 
					"				where a.user_no = b.no order by group_no desc, order_no asc) a, (select @rownum:=0) b) c where page =1";
			//String sql ="select a.no, a.title, a.contents, a.write_date, a.hit, a.group_no, a.order_no, a.depth,a.user_no ,b.name from board a, user b where a.user_no = b.no order by group_no desc, order_no asc";
//			sql = "select a.*"+
//							" from (select a.no, a.title, a.contents, a.write_date, a.hit, a.group_no, a.order_no, a.depth,a.user_no ,b.name"+
//								" from board a, user b"+ 
//									" where a.user_no = b.no order by group_no desc, order_no asc) a"+
//									" limit 0,5";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String contents = rs.getString(3);
				String write_date = rs.getString(4);
				int hit = rs.getInt(5);
				int group_no = rs.getInt(6);
				int order_no = rs.getInt(7);
				int depth = rs.getInt(8);
				Long user_no = rs.getLong(9);
				String user_name = rs.getString(10);
				Long rownum = rs.getLong(11);
				Long paging = rs.getLong(12);
				
				
				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContents(contents);
				vo.setWrite_date(write_date);
				vo.setHit(hit);
				vo.setGroup_no(group_no);
				vo.setOrder_no(order_no);
				vo.setDepth(depth);
				vo.setUser_no(user_no);
				vo.setUser_name(user_name);
				vo.setRownum(rownum);
				vo.setPaging(paging);
				
				list.add(vo);
				
			}
			
			
		} catch (SQLException e) {
			System.out.println("Error : "+e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(rs != null) {
					rs.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	private Connection getConnection() throws SQLException{
		Connection conn = null;
		try {
			// 1. JDBC Driver(MYSQL) 로딩
			Class.forName("com.mysql.jdbc.Driver");
			//pripertiy -> build path를 등록해줘야된다.

			// 2. 연결하기 ( jdbc:연결할database://ip:port/database이름 ) port번호는 생략가능하다.
			// url과 id와 password를 같이 입력해준다. (Connection 객체 얻어오기)
			String url = "jdbc:mysql://localhost:3306/webdb";
			conn = DriverManager.getConnection(url,"webdb","webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패 : "+e);
		}
		return conn;
	}
}
