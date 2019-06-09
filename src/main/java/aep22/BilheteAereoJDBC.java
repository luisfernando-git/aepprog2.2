package aep22;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BilheteAereoJDBC implements BilheteAereoRepository {

	private Connection conn;
	
	public BilheteAereoJDBC(Connection conn) {
		this.conn = conn;
	}

	public void criarTabelaBilheteAereo(Connection connection) throws Exception {
		String sql = "create table if not exists bilheteAereo ("
				+ "id integer not null primary key,"
				+ "voo integer not null,"
				+ "origem varchar(255) not null,"
				+ "destino varchar(255) not null"
				+ "data date not null"
				+ ")";
		
		Statement statement = connection.createStatement();
		statement.execute(sql);
		statement.close();		
	}
	
	public void inserir(Connection connection, BilheteAereo bilhete) throws Exception {
		String sql = "insert into bilheteAereo (id, voo, origem, destino, data) values (?,?,?,?,?)";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, bilhete.getId());
		statement.setInt(2, bilhete.getVoo());
		statement.setString(3, bilhete.getOrigem());
		statement.setString(4, bilhete.getDestino());
		statement.setDate(5, new java.sql.Date(bilhete.getData().getTime()));
		
		statement.execute();
		statement.close();
	}
	
	public void excluirTodos(Connection connection) throws Exception {
		String sql = "delete from bilheteAereo";
		Statement statement = connection.createStatement();
		statement.execute(sql);
		statement.close();		
	}
	
	public List<BilheteAereo> obter(Connection connection) throws Exception {
		List<BilheteAereo> bilhetes = new ArrayList<>();
		
		String sql = "select id, voo, origem, destino, data from bilheteAereo";
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		while (resultSet.next())  {
			Integer id = resultSet.getInt("id");
			Integer voo = resultSet.getInt("voo");
			String origem = resultSet.getString("origem");
			String destino = resultSet.getString("destino");
			Date data = new java.util.Date(resultSet.getDate("data").getTime());
			BilheteAereo bilhete = new BilheteAereo(id, voo, origem, destino, data);
			bilhetes.add(bilhete);
		}
		resultSet.close();
		statement.close();
		
		return bilhetes;
	}
	
	public void atualizar(Connection connection, BilheteAereo bilhete) throws Exception {
		String sql = "update bilheteAereo set voo = ?, origem = ?, destino = ?,  data = ? where id = ?";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, bilhete.getId());
		statement.setInt(2, bilhete.getVoo());
		statement.setString(3, bilhete.getOrigem());
		statement.setString(4, bilhete.getDestino());
		statement.setDate(5, new java.sql.Date(bilhete.getData().getTime()));
		
		statement.execute();
		statement.close();
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
}
