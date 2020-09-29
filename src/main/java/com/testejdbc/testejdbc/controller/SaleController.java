package com.testejdbc.testejdbc.controller;

import java.util.List;

import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.HttpHeadResponseDecorator;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testejdbc.testejdbc.dao.SaleDao;
import com.testejdbc.testejdbc.model.Sale;

@RestController
@RequestMapping("/metodos")

public class SaleController {

	
	private static final String RestModel = null;
	@Autowired
	private SaleDao dao;
	 
	@GetMapping
	public List<Sale> getList(){
		
		List<Sale> listSale = dao.list();
		return listSale;
	}
	@PostMapping 
	public ResponseEntity save(Sale sale) {
		if(sale.getItem() ==null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dados vazzios");
		}
		try {
			dao.save(sale);
		    return  ResponseEntity.status(HttpStatus.CREATED).body("criado");	
		}catch(Exception er) {
			 ResponseEntity.status(HttpStatus.BAD_REQUEST).body("erro");	

		}
		return null;

	}
	
	@GetMapping("/{id}")
	public Sale get(@PathVariable int id) {
		
		Sale sale = dao.get(id);
		return sale;
	}
	@PutMapping("/{id}")
	public Sale update( Sale sale,@PathVariable int id) {
		sale.setId(id);
		dao.update(sale);
		
		return sale;
	}
	@DeleteMapping("/{id}")
	public String delete(@PathVariable int id) {
		
		dao.delete(id);
		return "Deletado";
	}
}
