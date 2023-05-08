package dao;

import java.sql.PreparedStatement;

public class GenericDao {
    private PreparedStatement comando; // Atributo usado para preparar e executar instru��es SQL.
    /**
     * @param instrucaoSql Instrução SQL a ser executada.
     * @param parametros Valores dos campos da instrução SQL. As reticências no tipo Object
     * indicam que "parametros" pode receber um número variável de argumentos Object.
     */
    protected String inserir(String instrucaoSql, Object... parametros) {
    	try {
    		String excecao = ConnectionDatabase.conectarBd();
    		if (excecao == null) {
                comando = ConnectionDatabase.getConexaoBd().prepareStatement(instrucaoSql);

    	        for (int i = 0; i < parametros.length; i++)

    	        	comando.setObject(i + 1, parametros[i]);

    	        comando.execute();

    	        comando.close();
                ConnectionDatabase.getConexaoBd().close();
    		} else
        		return excecao;
        } catch (Exception exception) {
            return "Tipo de Exceção: " + exception.getClass().getSimpleName() + "\nMensagem: " + exception.getMessage();
        }
        return null;
    }
}
