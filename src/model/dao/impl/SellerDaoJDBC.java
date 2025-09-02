package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao{

	private Connection conexao;
	
	public SellerDaoJDBC(Connection conexao) {
		this.conexao = conexao;
	}
	
	@Override
	public void insert(Seller obj) {
		// TODO Stub de método gerado automaticamente
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Stub de método gerado automaticamente
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Stub de método gerado automaticamente
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement consulta = null;
		ResultSet resultado = null;
		try {
			consulta = conexao.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE seller.Id = ? ");
			consulta.setInt(1, id);
			resultado = consulta.executeQuery();
			if(resultado.next()) {
				Department dep = new Department();
				dep.setId(resultado.getInt("DepartmentId"));
				dep.setName(resultado.getString("DepName"));
				Seller obj = new Seller();
				obj.setId(resultado.getInt("Id"));
				obj.setName(resultado.getString("Name"));
				obj.setEmail(resultado.getString("Email"));
				obj.setBaseSalary(resultado.getDouble("BaseSalary"));
				obj.setBirthDate(resultado.getDate("BirthDate"));
				obj.setDepartment(dep);
				return obj;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(consulta);
			DB.closeResultSet(resultado);
		}
		
	}

	@Override
	public List<Seller> findAll() {
		// TODO Stub de método gerado automaticamente
		return null;
	}
	
	
	
}
