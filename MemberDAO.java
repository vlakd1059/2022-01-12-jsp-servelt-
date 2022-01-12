package com.message.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDAO {
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;
	private int cnt;
	private String sql;

	// �⺻������ �־���ϴ� 2����
	// 1. �����ϴ±��
	// 2. ����

	// DB������
	public void connect() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "hr";
			String password = "hr";

			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			// 1. OracleDriver Ŭ������ �ش� ��ġ�� ���°��(ojdbc6.jar ������)
			// �ذ��� : WEB-INF -> lib -> ojdbc6.jar ����

			// 2. OracleDriver ��ΰ� ��Ÿ�ΰ��
			e.printStackTrace();
		} catch (SQLException e) {
			// DB���� ������ ��Ȯ���� ���� ���
			e.printStackTrace();
		}
	}

	// DB�������� -> ���� ���� ������ ��������
	public void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (psmt != null) {
				psmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ȸ�����Ա��
	public int memberJoin(MemberDTO member) {

		connect();
		sql = "insert into web_member values(?,?,?,?)";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, member.getM_email());
			psmt.setString(2, member.getM_pw());
			psmt.setString(3, member.getM_tel());
			psmt.setString(4, member.getM_address());

			cnt = psmt.executeUpdate();

		} catch (SQLException e) {
			// 1. SQL������ �߸� �ۼ��Ǿ��� ���
			// 2. psmt��ü�� �߸��� �ε������� �ۼ����� ���
			// 3. ���̺��� ���� ��� ->ex) sql �ۼ������� ���������ʾƼ� ���̺� ������ x
			e.printStackTrace();
		} finally {
			// try~catch���� ����� �� �ݵ�� ����Ǵ� ����
			close();
		}

		return cnt;
	}

	public MemberDTO memberLogin(String email, String pw) {

		connect();
		sql = "select m_email, m_tel, m_address from web_member where m_email=? and m_pw=? ";

		return null;
	}

}
