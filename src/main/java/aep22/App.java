package aep22;

import java.sql.Connection;
import java.util.Date;

public class App {

	public static void main(String[] args) throws Exception {
		Connection conn = null;
		
		BilheteAereoRepository repository = new BilheteAereoJDBC(conn);

		repository.criarTabelaBilheteAereo(conn);
		
		BilheteAereo bilhete = new BilheteAereo(1, 1, "Maringá", "Rio de Janeiro", new Date());
		repository.inserir(conn, bilhete);
		
		repository.obter(conn);
		
		repository.atualizar(conn, bilhete);
		
		repository.excluirTodos(conn);	
	}
}