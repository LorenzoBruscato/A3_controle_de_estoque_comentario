package modelo.dao.db;

/**
 *
 *
 */
public class DbException extends RuntimeException {

    /**
     * Construtor da exceção personalizada para erros relacionados ao banco de
     * dados.
     *
     * @param msg Mensagem detalhando a causa da exceção
     */
    public DbException(String msg) {
        super(msg);
    }

}
