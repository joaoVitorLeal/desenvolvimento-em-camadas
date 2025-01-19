package dao;

/** 
 * Interface que contém as credenciais de acesso ao banco de dados.
 * Armazena o driver JDBC, URL, usuário e senha para a conexão.
 */
import config.credenciaisDB; 

/** 
 * Classe que representa o modelo de um autor.
 * Contém os atributos e métodos necessários para manipular os dados de autor.
 */
import model.Autor; 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author joaoleal
 */

public class AutorDAO implements credenciaisDB {
    
    public void adicionar (Autor autor) {
        
        /* Carregando Driver JDBC para o SGBD */
        try {        
            Class.forName(DRV);
            System.out.println( "Driver JDBC carregado" );
            
        } catch ( ClassNotFoundException cnfe ) {
                System.err.println( "Driver JDBC não encontrado:\n" + cnfe.getMessage() );
        }
        
        
        /* Obtendo conexão com o banco de dados */
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println( "Conexao com o banco de dados estabelecida." );
            
        } catch ( SQLException sqle ) {
            System.err.println("Erro na conexao ao Bando de Dados:\n" + sqle.getMessage());
        }
        
        
        /* Objeto para execução de SQL (INSERÇÃO) */
        String sql = "INSERT INTO Autor (codigo, nome, estado) VALUES (?, ?, ?)";    
        try  ( PreparedStatement pstmt = conn.prepareStatement(sql); ) {   
            
            System.out.println("Pronto para execução do SQL.");  
            
            /* Dados do autor */
            int codigo = autor.getCodigo();
            String nome = autor.getNome();
            String estadoSelecionado = autor.getEstado();
               
            /* Definindo dados aos placeholders */
            pstmt.setInt(1, codigo);
            pstmt.setString(2, nome);
            pstmt.setString(3, estadoSelecionado);
            
            /* Executando Comando */
            pstmt.executeUpdate();    
            System.out.println("Dados inseridos."); 
            
        } catch ( SQLException sqle ) {
            System.err.println("Erro no acesso ao banco de dados:\n" + sqle.getMessage());
        }
        

        /* FECHANDO CONEXÃO com o banco de dados */
        
        try {
            if ( conn != null ) {
                conn.close();
                System.out.println("Conexão com o banco de dados encerrada.");
            }     
        } catch ( SQLException sqle ) {
            System.err.println("Erro ao fechar conexão com o banco de dados:\n" + sqle.getMessage());
        }    
    }
    
    /**
     *
     * @param codigo
     * @return
     */
    public Autor consultarPorCodigo (int codigo) {
        Autor autor = null;
        
        /* Carregando Driver JDBC para o SGBD */
        try {        
            Class.forName(DRV);
            System.out.println( "Driver JDBC carregado" );
            
        } catch ( ClassNotFoundException cnfe ) {
                System.err.println( "Driver JDBC não encontrado:\n" + cnfe.getMessage() );
        }
        
        
        /* Obtendo conexão com o banco de dados */
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println( "Conexao com o banco de dados estabelecida." );
            
        } catch ( SQLException sqle ) {
            System.err.println("Erro na conexao ao Bando de Dados:\n" + sqle.getMessage());
        }
        
        
        /* Objeto para execução de SQL (INSERÇÃO) */
        String sql = "SELECT codigo, nome, estado FROM Autor WHERE codigo=?;";    
        try  ( PreparedStatement pstmt = conn.prepareStatement(sql); ) {   
            
            System.out.println("Pronto para execução do SQL.");  
   
            /* Definindo dados aos placeholders */
            pstmt.setInt(1, codigo);
            
            /* Executando consulta */
            try (ResultSet rs = pstmt.executeQuery()) {
                
                if (rs.next()) {
                    autor = new Autor();
                    autor.setCodigo(rs.getInt("codigo"));
                    autor.setNome(rs.getString("nome"));
                    autor.setEstado(rs.getString("estado"));
                }
            }   
            
        } catch ( SQLException sqle ) {
            System.err.println("Erro no acesso ao banco de dados:\n" + sqle.getMessage());
        }
        

        /* FECHANDO CONEXÃO com o banco de dados */
        
        try {
            if ( conn != null ) {
                conn.close();
                System.out.println("Conexão com o banco de dados encerrada.");
            }     
        } catch ( SQLException sqle ) {
            System.err.println("Erro ao fechar conexão com o banco de dados:\n" + sqle.getMessage());
        } 
        return autor;
    } 
    
    /** Consultar todos os dados da tabela. */
    public List<Autor> consultar () {
        List<Autor> listaDeAutores = new ArrayList<>();
        String sql = "SELECT * FROM Autor;";
        
        /* Carregando Driver JDBC para o SGBD */
        try {        
            Class.forName(DRV);
            System.out.println( "Driver JDBC carregado" );
            
        } catch ( ClassNotFoundException cnfe ) {
                System.err.println( "Driver JDBC não encontrado:\n" + cnfe.getMessage() );
        }
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)){

            /* Executando consulta */
            try (ResultSet rs = pstmt.executeQuery()) {
                
                while (rs.next()) {
                    var autor = new Autor();
                    autor.setCodigo(rs.getInt("codigo"));
                    autor.setNome(rs.getString("nome"));
                    autor.setEstado(rs.getString("estado"));
                    listaDeAutores.add(autor);  // Adiciona autor a lista.
                }
            } 
            
        } catch ( SQLException sqle ) {
            System.err.println("Erro no acesso ao banco de dados:\n" + sqle.getMessage());
            sqle.printStackTrace();
        }
        
        return listaDeAutores;
    }    
    
    public Autor deletar (int codigo) {
        /* Carregando Driver JDBC para o SGBD */
        try {        
            Class.forName(DRV);
            System.out.println( "Driver JDBC carregado" );
            
        } catch ( ClassNotFoundException cnfe ) {
                System.err.println( "Driver JDBC não encontrado:\n" + cnfe.getMessage() );
        }
        
        // Obter autor no banco de dados para exibir qual foi o excluido.
        Autor autor = null;
        String sql = "SELECT * FROM Autor WHERE codigo=?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, codigo);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    autor = new Autor();
                    autor.setCodigo(codigo);
                    autor.setNome(rs.getString("nome"));
                    autor.setEstado(rs.getString("estado"));
                } else {
                    // Caso o autor não seja encontrado
                    return null;
                }
            }
            
        } catch (SQLException sqle) {
            System.err.println("Erro no acesso ao banco de dados:\n" + sqle.getMessage());
            sqle.printStackTrace();           
        }
        
        if (autor != null) {
            // Deletar autor do banco de dados caso não seja nulo
            sql = "DELETE FROM Autor WHERE codigo=?;";
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, codigo);
                pstmt.executeUpdate();

            } catch (SQLException sqle) {
                System.err.println("Erro no acesso ao banco de dados:\n" + sqle.getMessage());
                sqle.printStackTrace();            
            }   
        }
        return autor;
    }
}
