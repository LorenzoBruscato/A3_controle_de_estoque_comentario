package modelo.dao.db;

/**
 * Exceção personalizada para encapsular erros relacionados a operações com o
 * banco de dados.
 *
 * <p>
 * Estende {@link RuntimeException}, permitindo que seja lançada sem
 * obrigatoriedade de captura em blocos <code>try-catch</code>.</p>
 *
 * <p>
 * É utilizada em toda a camada DAO para representar falhas como problemas de
 * conexão, SQL inválido, falhas ao fechar recursos, entre outros.</p>
 *
 * @author Diego
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
