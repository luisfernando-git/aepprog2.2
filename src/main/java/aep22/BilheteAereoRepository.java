package aep22;

import java.sql.Connection;
import java.util.List;

public interface BilheteAereoRepository {
	
	void criarTabelaBilheteAereo(Connection connection) throws Exception;
	
	void inserir(Connection connection, BilheteAereo bilhete) throws Exception;
	
	void excluirTodos(Connection connection) throws Exception;
	
	List<BilheteAereo> obter(Connection connection) throws Exception;
	
	void atualizar(Connection connection, BilheteAereo bilhete) throws Exception;	
	
}