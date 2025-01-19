package business;

/** 
 * Classe que representa o modelo de um autor.
 * Contém os atributos e métodos necessários para manipular os dados de autor.
 */
import model.Autor; 

/** 
 * Classe responsável pela interação com a camada de acesso a dados (DAO).
 */
import dao.AutorDAO;
import java.util.List;

/**
 * @author joaoleal
 */

public class AutorBS {
    AutorDAO autorDAO;
    
    public AutorBS () { 
        autorDAO = new AutorDAO();
    }
    
    /**
     * validarDados é responsável por fazer a validacao dos dados (código e nome) do objeto Autor
     * antes de serem cadastrados no sistema.
     * 
     * @param autor o objeto Autor a ser validado.
     * @throws java.lang.Exception
     */ 
    public void validarDados(Autor autor) throws Exception {
        // Validação do atributo codigo
        if ( autor.getCodigo() <= 0 ) {
            throw new Exception("O campo 'Código' deve ser maior que zero.");
        }
         
        // Validação do atributo nome
        if ( autor.getNome() == null || autor.getNome().trim().isEmpty()) {
            throw new Exception("O campo 'Nome' é obrigatório.");
        }
    }
    
     /**
     * validarDados é responsável por fazer a validação do campo Código para
     * realização da consulta por código.
     * 
     * @param codigo atributo código a ser validado
     * @throws java.lang.Exception
     */     
    public void validarDados(int codigo) throws Exception {
        // Validação do campo código
        if (codigo <= 0) {
            throw new Exception("Não existe código menor ou igual a zero. Por favor informe um número válido.");
        }  
    }
    
    public void validarDados(List<Autor> listaDeAutores) throws Exception {
    // Validação do campo código
        if (listaDeAutores.isEmpty()) {
            throw new Exception("Nenhum autor encontrado.");
        }
  

    }
       
        
    /**
     * Adiciona um novo autor ao sistema após validar seus dados.
     * 
     * Este método realiza a validação dos dados do autor fornecido e,
     * se todos os critérios forem atendidos, delega a inserção ao DAO responsável.
     * 
     * @param autor o objeto Autor que contém os dados a serem cadastrados.
     * @throws java.lang.Exception
     */
    public void adicionarAutor(Autor autor) throws Exception {
        validarDados(autor);
        autorDAO.adicionar(autor);
    }
    
    public Autor consultarAutor (int codigo) throws Exception {
        validarDados(codigo);
        Autor autor = autorDAO.consultarPorCodigo(codigo);
        
        if (autor == null) {
            throw new Exception("Nenhum autor encontrado com o código fornecido.");
        }
        
        return autor;
    }
    
    public List<Autor> consultarTodos() throws Exception {
        List<Autor> listaDeAutores = autorDAO.consultar();
        validarDados(listaDeAutores);
        return listaDeAutores;
    }
    
    public Autor deletarAutor(int codigo) throws Exception {
        validarDados(codigo);
        Autor autor = autorDAO.deletar(codigo);
        return autor;
    }
}
