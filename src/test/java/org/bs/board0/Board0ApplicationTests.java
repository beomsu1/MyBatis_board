package org.bs.board0;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
class Board0ApplicationTests {

	@Autowired
	private DataSource dataSource;

	@Test
	void contextLoads() {
	}


	// db 연결 테스트
	@Test
	public void dbConnectionTest(){

		try {
			Connection connection = dataSource.getConnection();

			log.info("------------------------");
			log.info("연결완료----------------");
			log.info("------------------------");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
