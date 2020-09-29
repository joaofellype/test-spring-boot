package com.testejdbc.testejdbc;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.testejdbc.testejdbc.dao.SaleDao;
import com.testejdbc.testejdbc.model.Sale;

class SalesDAOTest {
	
	private SaleDao dao;
	@BeforeEach
	void setUp() throws Exception {
		
		DriverManagerDataSource datasource = new DriverManagerDataSource();
		datasource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		datasource.setUsername("SYSTEM");
		datasource.setPassword("123456");
		datasource.setDriverClassName("oracle.jdbc.OracleDriver");
		
		dao = new SaleDao(new JdbcTemplate(datasource));
	}

	@Test
	void testList() {
		
		List<Sale> listSale =  dao.list();
		
		assertTrue(listSale.isEmpty());
 	}

	@Test
	void testGet() {
		
		int id = 2;
		
		Sale sale = dao.get(id);
		
		assertNotNull(sale);
	}
	

	@Test
	void testSave() {
		Sale sale = new Sale("Coca",1,6.55f);
		dao.save(sale);
	}

	@Test
	void testUpdate() {
		
		Sale sale = new Sale();
		sale.setId(2);
		sale.setItem("Feijao");
		sale.setQuantity(5);
		sale.setAmount(25);
		
		dao.update(sale);
		
	}

	@Test
	void testDelete() {
		
		dao.delete(2);
	}

}
