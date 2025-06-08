package modelo.dao.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.dao.db.DbException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import modelo.Categoria;
import modelo.Categoria.Embalagem;
import modelo.Categoria.Tamanho;
import modelo.Produto;
import modelo.Registro;
import modelo.dao.ProdutoDao;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 *
 *
 * @author Lorenzo
 */
public class ProdutoDaoJDBC implements ProdutoDao {

    private Connection conn; // Conexão com o banco de dados

    /**
     * Constrói um ProdutoDaoJDBC com a conexão especificada.
     *
     * @param conn Objeto Connection para comunicação com o banco de dados
     * @throws IllegalArgumentException Se a conexão for nula
     */
    public ProdutoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    /**
     * Busca um produto no banco pelo seu ID.
     *
     * @param id Identificador único do produto.
     * @return Produto encontrado ou null caso não exista.
     */
    @Override
    public Produto procurarProdutoPorId(Integer id) {
        String sql = "SELECT * FROM produto WHERE id = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {

            st.setInt(1, id);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return instanciarProduto(rs);
                }
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

        return null;
    }

    /**
     * Insere um novo produto no banco de dados.
     *
     * O ID gerado automaticamente é atribuído ao objeto Produto.
     *
     * @param obj Produto a ser cadastrado.
     */
    @Override
    public void cadastrarProduto(Produto obj) {
        String sql = "INSERT INTO produto "
                + "(nome, preco_unitario, unidade, quantidade_estoque, quantidade_minima, quantidade_maxima, categoria) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, obj.getNome());
            st.setDouble(2, obj.getPreco());
            st.setString(3, obj.getUnidade());
            st.setInt(4, obj.getQuantidade());
            st.setInt(5, obj.getQuantidadeMinima());
            st.setInt(6, obj.getQuantidadeMaxima());
            st.setString(7, obj.getCategoria().getNome());

            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet rs = st.getGeneratedKeys()) {
                    if (rs.next()) {
                        int id = rs.getInt(1);
                        obj.setId(id);
                    }
                }
            } else {
                throw new DbException("Erro inesperado! Nenhuma linha afetada.");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    /**
     * Atualiza os dados de um produto existente no banco.
     *
     * @param obj Produto com dados atualizados.
     */
    @Override
    public void atualizarProduto(Produto obj) {
        String sql
                = "UPDATE produto SET "
                + "nome = ?, "
                + "preco_unitario = ?, "
                + "unidade = ?, "
                + "quantidade_estoque = ?, "
                + "quantidade_minima = ?, "
                + "quantidade_maxima = ?, "
                + "categoria = ? "
                + "WHERE id = ?";

        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, obj.getNome());
            st.setDouble(2, obj.getPreco());
            st.setString(3, obj.getUnidade());
            st.setInt(4, obj.getQuantidade());
            st.setInt(5, obj.getQuantidadeMinima());
            st.setInt(6, obj.getQuantidadeMaxima());
            st.setString(7, obj.getCategoria().getNome());
            st.setInt(8, obj.getId());

            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    /**
     * Atualiza o nome da categoria de todos os produtos que possuam a categoria
     * antiga.
     *
     * Útil para manter consistência após alteração de nome de categoria.
     *
     * @param nomeNovo Novo nome da categoria.
     * @param nomeAntigo Nome antigo da categoria.
     */
    @Override
    public void atualizarProdutoCategoria(String nomeNovo, String nomeAntigo) {
        String sql = "UPDATE produto SET categoria = ? WHERE categoria = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, nomeNovo);
            st.setString(2, nomeAntigo);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    /**
     * Remove um produto do banco pelo seu ID.
     *
     * Antes da exclusão, verifica se o produto existe. Após a exclusão,
     * registra a operação na tabela de registros para histórico de
     * movimentações.
     *
     * @param objId ID do produto a ser deletado.
     */
    @Override
    public void deletarProdutoPorId(int objId) {
        // 1. Buscar o produto antes de excluir
        Produto produto = procurarProdutoPorId(objId);
        if (produto == null) {
            throw new DbException("Produto não encontrado para exclusão.");
        }

        // 2. Excluir o produto
        String sql = "DELETE FROM produto WHERE id = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, objId);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Erro ao deletar produto: " + e.getMessage());
        }

        // 3. Criar o registro da exclusão, incluindo o status
        String sqlr = "INSERT INTO registro (data, tipo, quantidade, movimentacao, status) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement st = conn.prepareStatement(sqlr)) {
            java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
            st.setDate(1, sqlDate);
            st.setString(2, produto.getNome());
            st.setInt(3, produto.getQuantidade());
            st.setString(4, Registro.Movimentacao.NENHUM.name()); // EXCLUIDO no enum
            st.setString(5, Registro.Status.DELETADO.name()); // Pode ajustar o status padrão aqui
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Erro ao registrar exclusão: " + e.getMessage());
        }
    }

    /**
     * Recupera todos os produtos do banco de dados, realizando um join com a
     * tabela de categorias.
     *
     * Cada produto é instanciado junto com sua categoria correspondente,
     * garantindo que as categorias sejam reutilizadas na lista para evitar
     * duplicação.
     *
     * @return Lista de produtos com suas categorias associadas.
     * @throws DbException se ocorrer erro na consulta ao banco.
     */
    @Override
    public List<Produto> resgatarProdutos() {
        List<Produto> lista = new ArrayList<>();
        Map<Integer, Categoria> map = new HashMap<>();

        String sql = "SELECT produto.*, categoria.id AS categoria_id, categoria.nome AS categoria_nome, "
                + "categoria.tamanho, categoria.embalagem "
                + "FROM produto "
                + "INNER JOIN categoria ON produto.categoria = categoria.nome "
                + "ORDER BY produto.nome";

        try (PreparedStatement st = conn.prepareStatement(sql); ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                int categoriaId = rs.getInt("categoria_id");

                Categoria cat = map.get(categoriaId);
                if (cat == null) {
                    cat = instanciarCategoria(rs, categoriaId);
                    map.put(categoriaId, cat);
                }

                Produto prod = instanciarProduto(rs);
                lista.add(prod);
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

        return lista;
    }

    /**
     * Busca um produto no banco de dados com base no nome, categoria e unidade
     * especificados.
     *
     * Este método é usado para verificar a existência de um produto com uma
     * combinação específica de nome, categoria e unidade. Isso é útil para
     * evitar duplicações de produtos com a mesma identidade lógica.
     *
     * @param nome o nome do produto a ser buscado (exato, sensível a
     * maiúsculas/minúsculas).
     * @param categoria a categoria à qual o produto pertence.
     * @param unidade a unidade de medida do produto (ex: "Kg", "L", "Un").
     *
     * @return o {@code Produto} correspondente se encontrado; caso contrário,
     * {@code null}.
     *
     * @throws DbException se ocorrer um erro de SQL durante a operação de
     * busca.
     */
    @Override
    public Produto procurarProdutoPorNomeCategoriaUnidade(String nome, Categoria categoria, String unidade) {
        String sql = "SELECT produto.*, categoria.id AS categoria_id, categoria.nome AS categoria_nome, "
                + "categoria.tamanho, categoria.embalagem "
                + "FROM produto "
                + "INNER JOIN categoria ON produto.categoria = categoria.nome "
                + "WHERE produto.nome = ? AND produto.categoria = ? AND produto.unidade = ?";

        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, nome);
            st.setString(2, categoria.getNome());
            st.setString(3, unidade);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    Categoria cat = instanciarCategoria(rs, rs.getInt("categoria_id"));
                    return instanciarProduto(rs);
                }
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

        return null;
    }

    /**
     * Remove todos os produtos associados a uma determinada categoria pelo
     * nome.
     *
     * Este método executa uma operação de exclusão na tabela de produtos,
     * eliminando todos os registros cuja categoria corresponda ao nome
     * informado.
     *
     * @param nomeCategoria nome da categoria cujos produtos serão removidos
     * @throws DbException se ocorrer algum erro durante a execução do comando
     * SQL
     */
    @Override
    public void removerPorNomeCategoria(String nomeCategoria) {
        String sql = "DELETE FROM produto WHERE categoria = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, nomeCategoria);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    /**
     * Aumenta o preço unitário de todos os produtos em um determinado
     * percentual.
     *
     * O percentual deve ser informado como valor positivo para representar o
     * aumento. Exemplo: 10 para aumento de 10%.
     *
     * @param percentual Percentual de aumento aplicado a todos os produtos.
     * @throws DbException se ocorrer erro durante a atualização dos preços.
     */
    @Override
    public void aumentarTodosPrecos(double percentual
    ) {
        String sql = "UPDATE produto SET preco_unitario = preco_unitario * (1 + ? / 100)";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setDouble(1, percentual);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    /**
     * Aumenta o preço unitário dos produtos de uma categoria específica em um
     * percentual.
     *
     * Permite ajuste direcionado do preço de produtos agrupados por categoria.
     *
     * @param percentual Percentual de aumento aplicado aos produtos da
     * categoria.
     * @param categoria Nome da categoria cujos produtos terão preço reajustado.
     * @throws DbException se ocorrer erro durante a atualização dos preços.
     */
    @Override
    public void aumentarPrecoPorCategoria(double percentual, String categoria
    ) {
        String sql = "UPDATE produto SET preco_unitario = preco_unitario * (1 + ? / 100) WHERE categoria = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setDouble(1, percentual);
            st.setString(2, categoria);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

    }

    /**
     * Diminui o preço unitário de todos os produtos em um determinado
     * percentual.
     *
     * O percentual deve ser informado como valor positivo para representar a
     * redução. Exemplo: 5 para desconto de 5%.
     *
     * @param percentual Percentual de redução aplicado a todos os produtos.
     * @throws DbException se ocorrer erro durante a atualização dos preços.
     */
    @Override
    public void diminuirTodosPrecos(double percentual
    ) {
        String sql = "UPDATE produto SET preco_unitario = preco_unitario * (1 - ? / 100)";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setDouble(1, percentual);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    /**
     * Reduz o preço unitário dos produtos de uma categoria específica em um
     * percentual.
     *
     * O percentual deve ser informado como valor positivo para representar a
     * redução. Exemplo: 5 para desconto de 5%.
     *
     * @param percentual Percentual de redução aplicado aos produtos da
     * categoria.
     * @param categoria Nome da categoria cujos produtos terão o preço reduzido.
     * @throws DbException Caso ocorra erro durante a atualização no banco de
     * dados.
     */
    @Override
    public void diminuirPrecoPorCategoria(double percentual, String categoria
    ) {
        String sql = "UPDATE produto SET preco_unitario = preco_unitario * (1 - ? / 100) WHERE categoria = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setDouble(1, percentual);
            st.setString(2, categoria);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    /**
     * Gera um relatório Excel contendo a lista de preços de todos os produtos.
     *
     * O arquivo será salvo no caminho especificado, com a planilha nomeada
     * conforme o parâmetro. Se o caminho informado for uma pasta, o arquivo
     * será criado dentro dela com o nome da planilha.
     *
     * @param caminhoArquivoSaidaExcel Caminho completo ou pasta onde o arquivo
     * Excel será salvo.
     * @param nomePlanilha Nome da planilha dentro do arquivo Excel.
     * @throws DbException Caso ocorra erro na consulta SQL ou na geração do
     * arquivo.
     */
    @Override
    public void gerarRelatorioListaDePrecoExcel(String caminhoArquivoSaidaExcel, String nomePlanilha
    ) {
        System.out.println("Tentando salvar arquivo em: " + caminhoArquivoSaidaExcel);

        // Força extensão e nome do arquivo se for só pasta
        if (!caminhoArquivoSaidaExcel.toLowerCase().endsWith(".xlsx")) {
            if (caminhoArquivoSaidaExcel.endsWith("\\") || caminhoArquivoSaidaExcel.endsWith("/")) {
                caminhoArquivoSaidaExcel = String.format("%s%s.xlsx", caminhoArquivoSaidaExcel, nomePlanilha);
            } else {
                caminhoArquivoSaidaExcel = String.format("%s\\%s.xlsx", caminhoArquivoSaidaExcel, nomePlanilha);
            }
        }

        String sql = "SELECT nome, preco_unitario, unidade, categoria FROM produto ORDER BY nome ASC";

        try (PreparedStatement st = conn.prepareStatement(sql); ResultSet rs = st.executeQuery(); Workbook workBook = new XSSFWorkbook()) {

            // Garante que a pasta existe
            File arquivo = new File(caminhoArquivoSaidaExcel);
            File diretorio = arquivo.getParentFile();
            if (diretorio != null && !diretorio.exists()) {
                diretorio.mkdirs();
            }

            FileOutputStream fileOut = new FileOutputStream(arquivo);
            Sheet sheet = workBook.createSheet(nomePlanilha);

            String[] colunas = {"Nome", "Preço Unitário", "Unidade", "Categoria"};
            Row header = sheet.createRow(0);
            for (int i = 0; i < colunas.length; i++) {
                header.createCell(i).setCellValue(colunas[i]);
            }

            int rowNum = 1;
            while (rs.next()) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(rs.getString("nome"));
                row.createCell(1).setCellValue(rs.getDouble("preco_unitario"));
                row.createCell(2).setCellValue(rs.getString("unidade"));
                row.createCell(3).setCellValue(rs.getString("categoria"));
            }

            for (int i = 0; i < colunas.length; i++) {
                sheet.autoSizeColumn(i);
            }

            workBook.write(fileOut);
            fileOut.close();
            workBook.close();

            JOptionPane.showMessageDialog(null, "Relatório gerado com sucesso:\n" + caminhoArquivoSaidaExcel);

        } catch (SQLException e) {
            throw new DbException("Erro SQL: " + e.getMessage());
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Arquivo não pode ser criado:\n" + e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao escrever o arquivo:\n" + e.getMessage());
        }
    }

    /**
     * Gera um relatório Excel do balanço físico-financeiro do estoque.
     *
     * O relatório contém o nome dos produtos, quantidade em estoque, preço
     * unitário e o valor total por produto (quantidade * preço). Também
     * apresenta o total geral do estoque.
     *
     * O arquivo será salvo no caminho especificado, criando a planilha com o
     * nome fornecido. Se o caminho informado for uma pasta, o arquivo será
     * criado dentro dela com o nome da planilha.
     *
     * @param caminhoArquivoSaidaExcel Caminho completo ou pasta onde o arquivo
     * Excel será salvo.
     * @param nomePlanilha Nome da planilha dentro do arquivo Excel.
     * @throws DbException Caso ocorra erro na consulta SQL ou na geração do
     * arquivo.
     */
    @Override
    public void gerarRelatorioBalancoFisicoFinanceiroExcel(String caminhoArquivoSaidaExcel, String nomePlanilha
    ) {
        System.out.println("Tentando salvar arquivo em: " + caminhoArquivoSaidaExcel);

        // Força extensão e nome do arquivo se for só pasta
        if (!caminhoArquivoSaidaExcel.toLowerCase().endsWith(".xlsx")) {
            if (caminhoArquivoSaidaExcel.endsWith("\\") || caminhoArquivoSaidaExcel.endsWith("/")) {
                caminhoArquivoSaidaExcel = String.format("%s%s.xlsx", caminhoArquivoSaidaExcel, nomePlanilha).trim();
            } else {
                caminhoArquivoSaidaExcel = String.format("%s\\%s.xlsx", caminhoArquivoSaidaExcel, nomePlanilha).trim();
            }
        }

        String sql = "SELECT nome, preco_unitario, quantidade_estoque FROM produto ORDER BY nome ASC";

        try (PreparedStatement st = conn.prepareStatement(sql); ResultSet rs = st.executeQuery(); Workbook workBook = new XSSFWorkbook()) {

            // Garante que a pasta existe
            File arquivo = new File(caminhoArquivoSaidaExcel);
            File diretorio = arquivo.getParentFile();
            if (diretorio != null && !diretorio.exists()) {
                diretorio.mkdirs();
            }

            FileOutputStream fileOut = new FileOutputStream(arquivo);
            Sheet sheet = workBook.createSheet(nomePlanilha);

            String[] colunas = {"Nome", "Quantidade em Estoque", "Preço Unitário", "Valor Total"};
            Row header = sheet.createRow(0);
            for (int i = 0; i < colunas.length; i++) {
                header.createCell(i).setCellValue(colunas[i]);
            }

            int rowNum = 1;
            double valorTotalEstoque = 0.0;

            while (rs.next()) {
                String nome = rs.getString("nome");
                int quantidade = rs.getInt("quantidade_estoque");
                double precoUnitario = rs.getDouble("preco_unitario");
                double valorTotalProduto = quantidade * precoUnitario;
                valorTotalEstoque += valorTotalProduto;

                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(nome);
                row.createCell(1).setCellValue(quantidade);
                row.createCell(2).setCellValue(precoUnitario);
                row.createCell(3).setCellValue(valorTotalProduto);
            }

            // Linha final: total do estoque
            Row totalRow = sheet.createRow(rowNum);
            totalRow.createCell(2).setCellValue("TOTAL:");
            totalRow.createCell(3).setCellValue(valorTotalEstoque);

            for (int i = 0; i < colunas.length; i++) {
                sheet.autoSizeColumn(i);
            }

            workBook.write(fileOut);
            fileOut.close();
            workBook.close();

            JOptionPane.showMessageDialog(null, "Relatório gerado com sucesso:\n" + caminhoArquivoSaidaExcel);

        } catch (SQLException e) {
            throw new DbException("Erro SQL: " + e.getMessage());
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Arquivo não pode ser criado:\n" + e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao escrever o arquivo:\n" + e.getMessage());
        }
    }

    /**
     * Gera um relatório Excel listando produtos cujo estoque está abaixo da
     * quantidade mínima.
     *
     * O arquivo será salvo no caminho informado, criando a planilha com o nome
     * especificado. Caso o caminho seja apenas uma pasta, o arquivo será criado
     * dentro dela com o nome da planilha.
     *
     * Cada linha contém: nome do produto, quantidade mínima e quantidade em
     * estoque.
     *
     * @param caminhoArquivoSaidaExcel Caminho ou pasta para salvar o arquivo
     * Excel.
     * @param nomePlanilha Nome da planilha dentro do arquivo Excel.
     * @throws DbException Em caso de erro ao consultar os dados no banco.
     */
    @Override
    public void gerarRelatorioListaDePrecoAbaixoDaQuantidadeMinimaExcel(String caminhoArquivoSaidaExcel, String nomePlanilha
    ) {
        System.out.println("Tentando salvar arquivo em: " + caminhoArquivoSaidaExcel);

        // Força extensão e nome do arquivo se for só pasta
        if (!caminhoArquivoSaidaExcel.toLowerCase().endsWith(".xlsx")) {
            if (caminhoArquivoSaidaExcel.endsWith("\\") || caminhoArquivoSaidaExcel.endsWith("/")) {
                caminhoArquivoSaidaExcel = String.format("%s%s.xlsx", caminhoArquivoSaidaExcel, nomePlanilha).trim();
            } else {
                caminhoArquivoSaidaExcel = String.format("%s\\%s.xlsx", caminhoArquivoSaidaExcel, nomePlanilha).trim();
            }
        }

        String sql = "SELECT nome, quantidade_minima, quantidade_estoque FROM produto WHERE quantidade_estoque < quantidade_minima ORDER BY nome ASC";

        try (PreparedStatement st = conn.prepareStatement(sql); ResultSet rs = st.executeQuery(); Workbook workBook = new XSSFWorkbook()) {
            // Garante que a pasta existe
            File arquivo = new File(caminhoArquivoSaidaExcel);
            File diretorio = arquivo.getParentFile();
            if (diretorio != null && !diretorio.exists()) {
                diretorio.mkdirs();
            }
            FileOutputStream fileOut = new FileOutputStream(arquivo);
            Sheet sheet = workBook.createSheet(nomePlanilha);

            String[] colunas = {"Nome", "Quantidade Mínima", "Quantidade Estoque"};
            Row header = sheet.createRow(0);
            for (int i = 0; i < colunas.length; i++) {
                header.createCell(i).setCellValue(colunas[i]);
            }
            int rowNum = 1;

            while (rs.next()) {
                String nome = rs.getString("nome");
                int quantidadeMinima = rs.getInt("quantidade_minima");
                int quantidadeEmEstoque = rs.getInt("quantidade_estoque");

                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(nome);
                row.createCell(1).setCellValue(quantidadeMinima);
                row.createCell(2).setCellValue(quantidadeEmEstoque);
            }

            for (int i = 0; i < colunas.length; i++) {
                sheet.autoSizeColumn(i);
            }

            workBook.write(fileOut);
            fileOut.close();
            workBook.close();

            JOptionPane.showMessageDialog(null, "Relatório gerado com sucesso:\n" + caminhoArquivoSaidaExcel);
        } catch (SQLException e) {
            throw new DbException("Erro SQL: " + e.getMessage());
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Arquivo não pode ser criado:\n" + e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao escrever o arquivo:\n" + e.getMessage());
        }
    }

    /**
     * Gera um relatório Excel listando produtos cujo estoque está acima da
     * quantidade máxima.
     *
     * O arquivo será salvo no caminho informado, criando a planilha com o nome
     * especificado. Caso o caminho seja apenas uma pasta, o arquivo será criado
     * dentro dela com o nome da planilha.
     *
     * Cada linha contém: nome do produto, quantidade máxima e quantidade em
     * estoque.
     *
     * @param caminhoArquivoSaidaExcel Caminho ou pasta para salvar o arquivo
     * Excel.
     * @param nomePlanilha Nome da planilha dentro do arquivo Excel.
     * @throws DbException Em caso de erro ao consultar os dados no banco.
     */
    @Override
    public void gerarRelatorioListaDePrecoAcimaDaQuantidadeMaximaExcel(String caminhoArquivoSaidaExcel, String nomePlanilha
    ) {
        System.out.println("Tentando salvar arquivo em: " + caminhoArquivoSaidaExcel);

        // Força extensão e nome do arquivo se for só pasta
        if (!caminhoArquivoSaidaExcel.toLowerCase().endsWith(".xlsx")) {
            if (caminhoArquivoSaidaExcel.endsWith("\\") || caminhoArquivoSaidaExcel.endsWith("/")) {
                caminhoArquivoSaidaExcel = String.format("%s%s.xlsx", caminhoArquivoSaidaExcel, nomePlanilha).trim();
            } else {
                caminhoArquivoSaidaExcel = String.format("%s\\%s.xlsx", caminhoArquivoSaidaExcel, nomePlanilha).trim();
            }
        }

        String sql = "SELECT nome, quantidade_maxima, quantidade_estoque FROM produto WHERE quantidade_estoque > quantidade_maxima ORDER BY nome ASC";

        try (PreparedStatement st = conn.prepareStatement(sql); ResultSet rs = st.executeQuery(); Workbook workBook = new XSSFWorkbook()) {
            // Garante que a pasta existe
            File arquivo = new File(caminhoArquivoSaidaExcel);
            File diretorio = arquivo.getParentFile();
            if (diretorio != null && !diretorio.exists()) {
                diretorio.mkdirs();
            }
            FileOutputStream fileOut = new FileOutputStream(arquivo);
            Sheet sheet = workBook.createSheet(nomePlanilha);

            String[] colunas = {"Nome", "Quantidade Máxima", "Quantidade Estoque"};
            Row header = sheet.createRow(0);
            for (int i = 0; i < colunas.length; i++) {
                header.createCell(i).setCellValue(colunas[i]);
            }
            int rowNum = 1;

            while (rs.next()) {
                String nome = rs.getString("nome");
                int quantidadeMinima = rs.getInt("quantidade_maxima");
                int quantidadeEmEstoque = rs.getInt("quantidade_estoque");

                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(nome);
                row.createCell(1).setCellValue(quantidadeMinima);
                row.createCell(2).setCellValue(quantidadeEmEstoque);
            }

            for (int i = 0; i < colunas.length; i++) {
                sheet.autoSizeColumn(i);
            }

            workBook.write(fileOut);
            fileOut.close();
            workBook.close();

            JOptionPane.showMessageDialog(null, "Relatório gerado com sucesso:\n" + caminhoArquivoSaidaExcel);
        } catch (SQLException e) {
            throw new DbException("Erro SQL: " + e.getMessage());
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Arquivo não pode ser criado:\n" + e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao escrever o arquivo:\n" + e.getMessage());
        }
    }

    /**
     * Gera um relatório Excel com a quantidade de produtos por categoria.
     *
     * O arquivo será salvo no caminho informado, criando a planilha com o nome
     * especificado. Caso o caminho seja apenas uma pasta, o arquivo será criado
     * dentro dela com o nome da planilha.
     *
     * Cada linha contém: nome da categoria e quantidade de produtos associados
     * a ela.
     *
     * @param caminhoArquivoSaidaExcel Caminho ou pasta para salvar o arquivo
     * Excel.
     * @param nomePlanilha Nome da planilha dentro do arquivo Excel.
     * @throws DbException Em caso de erro ao consultar os dados no banco.
     */
    @Override
    public void gerarRelatorioListaProdutoPorCategoriaExcel(String caminhoArquivoSaidaExcel, String nomePlanilha
    ) {
        System.out.println("Tentando salvar arquivo em: " + caminhoArquivoSaidaExcel);

        // Força extensão e nome do arquivo se for só pasta
        if (!caminhoArquivoSaidaExcel.toLowerCase().endsWith(".xlsx")) {
            if (caminhoArquivoSaidaExcel.endsWith("\\") || caminhoArquivoSaidaExcel.endsWith("/")) {
                caminhoArquivoSaidaExcel = String.format("%s%s.xlsx", caminhoArquivoSaidaExcel, nomePlanilha).trim();
            } else {
                caminhoArquivoSaidaExcel = String.format("%s\\%s.xlsx", caminhoArquivoSaidaExcel, nomePlanilha).trim();
            }
        }

        String sql = "SELECT c.nome AS nome_categoria, COUNT(p.id) AS quantidade_produtos FROM categoria c LEFT JOIN produto p ON p.categoria = c.nome GROUP BY c.nome ORDER BY c.nome ASC";

        try (PreparedStatement st = conn.prepareStatement(sql); ResultSet rs = st.executeQuery(); Workbook workBook = new XSSFWorkbook()) {
            // Garante que a pasta existe
            File arquivo = new File(caminhoArquivoSaidaExcel);
            File diretorio = arquivo.getParentFile();
            if (diretorio != null && !diretorio.exists()) {
                diretorio.mkdirs();
            }
            FileOutputStream fileOut = new FileOutputStream(arquivo);
            Sheet sheet = workBook.createSheet(nomePlanilha);

            String[] colunas = {"Categoria", "Quantidade Produtos"};
            Row header = sheet.createRow(0);
            for (int i = 0; i < colunas.length; i++) {
                header.createCell(i).setCellValue(colunas[i]);
            }
            int rowNum = 1;

            while (rs.next()) {
                String nomecategoria = rs.getString("nome_categoria");
                int quantidadeDeProdutos = rs.getInt("quantidade_produtos");

                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(nomecategoria);
                row.createCell(1).setCellValue(quantidadeDeProdutos);

            }

            for (int i = 0; i < colunas.length; i++) {
                sheet.autoSizeColumn(i);
            }

            workBook.write(fileOut);
            fileOut.close();
            workBook.close();

            JOptionPane.showMessageDialog(null, "Relatório gerado com sucesso:\n" + caminhoArquivoSaidaExcel);
        } catch (SQLException e) {
            throw new DbException("Erro SQL: " + e.getMessage());
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Arquivo não pode ser criado:\n" + e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao escrever o arquivo:\n" + e.getMessage());
        }
    }

    /**
     * Gera um relatório de movimentação em formato Excel (.xlsx) com dados
     * extraídos da tabela "registro". O relatório contém colunas como ID, Data,
     * Tipo, Quantidade, Movimentação e Status, ordenados por data ascendente.
     *
     * @param caminhoArquivoSaidaExcel Caminho completo ou diretório onde o
     * arquivo Excel será salvo. Caso seja apenas o diretório, o arquivo será
     * salvo com o nome da planilha e extensão .xlsx.
     * @param nomePlanilha Nome da planilha dentro do arquivo Excel e, se
     * necessário, usado para formar o nome do arquivo.
     */
    @Override
    public void gerarRelatorioMovimentacaoExcel(String caminhoArquivoSaidaExcel, String nomePlanilha) {
        System.out.println("Tentando salvar arquivo em: " + caminhoArquivoSaidaExcel);

        // Garante extensão e nome do arquivo
        if (!caminhoArquivoSaidaExcel.toLowerCase().endsWith(".xlsx")) {
            if (caminhoArquivoSaidaExcel.endsWith("\\") || caminhoArquivoSaidaExcel.endsWith("/")) {
                caminhoArquivoSaidaExcel = String.format("%s%s.xlsx", caminhoArquivoSaidaExcel, nomePlanilha).trim();
            } else {
                caminhoArquivoSaidaExcel = String.format("%s\\%s.xlsx", caminhoArquivoSaidaExcel, nomePlanilha).trim();
            }
        }

        String sql = "SELECT id, data, tipo, quantidade, movimentacao, status FROM registro ORDER BY data ASC";

        try (
                PreparedStatement st = conn.prepareStatement(sql); ResultSet rs = st.executeQuery(); Workbook workBook = new XSSFWorkbook()) {
            File arquivo = new File(caminhoArquivoSaidaExcel);
            File diretorio = arquivo.getParentFile();
            if (diretorio != null && !diretorio.exists()) {
                diretorio.mkdirs();
            }

            FileOutputStream fileOut = new FileOutputStream(arquivo);
            Sheet sheet = workBook.createSheet(nomePlanilha);

            String[] colunas = {"ID", "Data", "Tipo", "Quantidade", "Movimentação", "Status"};
            Row header = sheet.createRow(0);
            for (int i = 0; i < colunas.length; i++) {
                header.createCell(i).setCellValue(colunas[i]);
            }

            int rowNum = 1;

            while (rs.next()) {
                Row row = sheet.createRow(rowNum++);

                row.createCell(0).setCellValue(rs.getInt("id"));
                row.createCell(1).setCellValue(rs.getDate("data").toString());
                row.createCell(2).setCellValue(rs.getString("tipo"));
                row.createCell(3).setCellValue(rs.getInt("quantidade"));
                row.createCell(4).setCellValue(rs.getString("movimentacao"));
                row.createCell(5).setCellValue(rs.getString("status"));
            }

            for (int i = 0; i < colunas.length; i++) {
                sheet.autoSizeColumn(i);
            }

            workBook.write(fileOut);
            fileOut.close();
            workBook.close();

            JOptionPane.showMessageDialog(null, "Relatório gerado com sucesso:\n" + caminhoArquivoSaidaExcel);

        } catch (SQLException e) {
            throw new DbException("Erro SQL: " + e.getMessage());
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Arquivo não pode ser criado:\n" + e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao escrever o arquivo:\n" + e.getMessage());
        }
    }

    /**
     * Gera um relatório em formato Word (.docx) contendo a lista de preços dos
     * produtos, com colunas Nome, Preço Unitário, Unidade e Categoria,
     * ordenados pelo nome do produto.
     *
     * @param caminhoArquivoSaidaDoc Caminho completo ou diretório onde o
     * arquivo Word será salvo. Caso seja apenas o diretório, o arquivo será
     * salvo com o nome especificado e extensão .docx.
     * @param nomeArquivoDoc Nome do arquivo Word a ser criado, se necessário
     * usado para formar o nome do arquivo.
     */
    @Override
    public void gerarRelatorioListaDePrecoDoc(String caminhoArquivoSaidaDoc, String nomeArquivoDoc
    ) {
        System.out.println("Tentando salvar arquivo em: " + caminhoArquivoSaidaDoc);

        if (!caminhoArquivoSaidaDoc.toLowerCase().endsWith(".docx")) {
            if (caminhoArquivoSaidaDoc.endsWith("\\") || caminhoArquivoSaidaDoc.endsWith("/")) {
                caminhoArquivoSaidaDoc = String.format("%s%s.docx", caminhoArquivoSaidaDoc, nomeArquivoDoc).trim();
            } else {
                caminhoArquivoSaidaDoc = String.format("%s\\%s.docx", caminhoArquivoSaidaDoc, nomeArquivoDoc).trim();
            }
        }

        String sql = "SELECT nome, preco_unitario, unidade, categoria FROM produto ORDER BY nome ASC";

        try (PreparedStatement st = conn.prepareStatement(sql); ResultSet rs = st.executeQuery(); XWPFDocument document = new XWPFDocument()) {

            // Cria diretório se não existir
            File arquivo = new File(caminhoArquivoSaidaDoc);
            File diretorio = arquivo.getParentFile();
            if (diretorio != null && !diretorio.exists()) {
                diretorio.mkdirs();
            }

            // Cria título do documento
            XWPFParagraph titulo = document.createParagraph();
            XWPFRun runTitulo = titulo.createRun();
            runTitulo.setText("Relatório de Lista de Preços");
            runTitulo.setBold(true);
            runTitulo.setFontSize(16);
            titulo.setAlignment(ParagraphAlignment.CENTER);

            // Espaço
            document.createParagraph();

            // Cria tabela
            XWPFTable table = document.createTable();

            // Cabeçalho
            XWPFTableRow header = table.getRow(0);
            header.getCell(0).setText("Nome");
            header.addNewTableCell().setText("Preço Unitário");
            header.addNewTableCell().setText("Unidade");
            header.addNewTableCell().setText("Categoria");

            // Preenche a tabela com dados
            while (rs.next()) {
                XWPFTableRow row = table.createRow();
                row.getCell(0).setText(rs.getString("nome"));
                row.getCell(1).setText(String.format("R$ %.2f", rs.getDouble("preco_unitario")));
                row.getCell(2).setText(rs.getString("unidade"));
                row.getCell(3).setText(rs.getString("categoria"));
            }

            // Salva o arquivo
            try (FileOutputStream out = new FileOutputStream(arquivo)) {
                document.write(out);
                JOptionPane.showMessageDialog(null, "Relatório gerado com sucesso:\n" + caminhoArquivoSaidaDoc);
            }

        } catch (SQLException e) {
            throw new DbException("Erro SQL: " + e.getMessage());
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Arquivo não pode ser criado:\n" + e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao escrever o arquivo:\n" + e.getMessage());
        }
    }

    /**
     * Gera um relatório em formato Word (.docx) do balanço físico-financeiro
     * dos produtos, exibindo o nome, preço unitário, quantidade em estoque e
     * valor total por produto, além do valor total de todo o estoque.
     *
     * @param caminhoArquivoSaidaDoc Caminho completo ou diretório onde o
     * arquivo Word será salvo. Caso seja apenas o diretório, o arquivo será
     * salvo com o nome especificado e extensão .docx.
     * @param nomeArquivoDoc Nome do arquivo Word a ser criado, se necessário
     * usado para formar o nome do arquivo.
     */
    @Override
    public void gerarRelatorioBalancoFisicoFinanceiroDOC(String caminhoArquivoSaidaDoc, String nomeArquivoDoc
    ) {
        System.out.println("Tentando salvar arquivo em: " + caminhoArquivoSaidaDoc);

        if (!caminhoArquivoSaidaDoc.toLowerCase().endsWith(".docx")) {
            if (caminhoArquivoSaidaDoc.endsWith("\\") || caminhoArquivoSaidaDoc.endsWith("/")) {
                caminhoArquivoSaidaDoc = String.format("%s%s.docx", caminhoArquivoSaidaDoc, nomeArquivoDoc).trim();
            } else {
                caminhoArquivoSaidaDoc = String.format("%s\\%s.docx", caminhoArquivoSaidaDoc, nomeArquivoDoc).trim();
            }
        }

        String sql = "SELECT nome, preco_unitario, quantidade_estoque FROM produto ORDER BY nome ASC";

        try (PreparedStatement st = conn.prepareStatement(sql); ResultSet rs = st.executeQuery(); XWPFDocument document = new XWPFDocument()) {

            // Cria diretório se não existir
            File arquivo = new File(caminhoArquivoSaidaDoc);
            File diretorio = arquivo.getParentFile();
            if (diretorio != null && !diretorio.exists()) {
                diretorio.mkdirs();
            }

            // Título
            XWPFParagraph titulo = document.createParagraph();
            titulo.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun runTitulo = titulo.createRun();
            runTitulo.setBold(true);
            runTitulo.setFontSize(16);
            runTitulo.setText("Relatório de Balanço Físico-Financeiro");

            document.createParagraph(); // Espaço

            // Cria tabela
            XWPFTable table = document.createTable();

            // Cabeçalho
            XWPFTableRow header = table.getRow(0);
            header.getCell(0).setText("Nome");
            header.addNewTableCell().setText("Preço Unitário");
            header.addNewTableCell().setText("Quantidade em Estoque");
            header.addNewTableCell().setText("Valor Total");

            double valorTotalEstoque = 0.0;

            // Dados
            while (rs.next()) {
                String nome = rs.getString("nome");
                int quantidade = rs.getInt("quantidade_estoque");
                double precoUnitario = rs.getDouble("preco_unitario");
                double valorTotalProduto = quantidade * precoUnitario;
                valorTotalEstoque += valorTotalProduto;

                XWPFTableRow row = table.createRow();
                row.getCell(0).setText(nome);
                row.getCell(1).setText(String.format("R$ %.2f", precoUnitario));
                row.getCell(2).setText(String.valueOf(quantidade));
                row.getCell(3).setText(String.format("R$ %.2f", valorTotalProduto));
            }

            // Linha total
            XWPFTableRow totalRow = table.createRow();
            totalRow.getCell(2).setText("TOTAL:");
            totalRow.getCell(3).setText(String.format("R$ %.2f", valorTotalEstoque));

            // Centraliza texto em todas as células
            table.getRows().forEach(row
                    -> row.getTableCells().forEach(cell
                            -> cell.getParagraphs().forEach(p -> p.setAlignment(ParagraphAlignment.CENTER)))
            );

            // Salva o documento
            try (FileOutputStream out = new FileOutputStream(arquivo)) {
                document.write(out);
                JOptionPane.showMessageDialog(null, "Relatório gerado com sucesso:\n" + caminhoArquivoSaidaDoc);
            }

        } catch (SQLException e) {
            throw new DbException("Erro SQL: " + e.getMessage());
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Arquivo não pode ser criado:\n" + e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao escrever o arquivo:\n" + e.getMessage());
        }
    }

    /**
     * Gera um relatório em formato Word (.docx) contendo a lista de produtos
     * cuja quantidade em estoque está abaixo da quantidade mínima definida. O
     * relatório exibe nome, quantidade mínima e quantidade em estoque,
     * ordenados por nome.
     *
     * @param caminhoArquivoSaidaDoc Caminho completo ou diretório onde o
     * arquivo Word será salvo. Caso seja apenas o diretório, o arquivo será
     * salvo com o nome informado e extensão .docx.
     * @param nomeArquivoDoc Nome do arquivo Word a ser criado, usado para
     * formar o nome do arquivo caso necessário.
     */
    @Override
    public void gerarRelatorioListaDePrecoAbaixoDaQuantidadeMinimaDoc(String caminhoArquivoSaidaDoc, String nomeArquivoDoc
    ) {
        System.out.println("Tentando salvar arquivo em: " + caminhoArquivoSaidaDoc);

        if (!caminhoArquivoSaidaDoc.toLowerCase().endsWith(".docx")) {
            if (caminhoArquivoSaidaDoc.endsWith("\\") || caminhoArquivoSaidaDoc.endsWith("/")) {
                caminhoArquivoSaidaDoc = String.format("%s%s.docx", caminhoArquivoSaidaDoc, nomeArquivoDoc).trim();
            } else {
                caminhoArquivoSaidaDoc = String.format("%s\\%s.docx", caminhoArquivoSaidaDoc, nomeArquivoDoc).trim();
            }
        }

        String sql = "SELECT nome, quantidade_minima, quantidade_estoque FROM produto WHERE quantidade_estoque < quantidade_minima ORDER BY nome ASC";

        try (PreparedStatement st = conn.prepareStatement(sql); ResultSet rs = st.executeQuery(); XWPFDocument document = new XWPFDocument()) {

            // Cria diretório se não existir
            File arquivo = new File(caminhoArquivoSaidaDoc);
            File diretorio = arquivo.getParentFile();
            if (diretorio != null && !diretorio.exists()) {
                diretorio.mkdirs();
            }

            // Cria título do documento
            XWPFParagraph titulo = document.createParagraph();
            XWPFRun runTitulo = titulo.createRun();
            runTitulo.setText("Relatório de Lista de Preços Abaixo da Quantidade Mínima");
            runTitulo.setBold(true);
            runTitulo.setFontSize(16);
            titulo.setAlignment(ParagraphAlignment.CENTER);

            // Espaço
            document.createParagraph();

            // Cria tabela
            XWPFTable table = document.createTable();

            // Cabeçalho
            XWPFTableRow header = table.getRow(0);
            header.getCell(0).setText("Nome");
            header.addNewTableCell().setText("Quantidade Mínima");
            header.addNewTableCell().setText("Quantidade em Estoque");

            // Preenche a tabela com dados
            while (rs.next()) {
                XWPFTableRow row = table.createRow();
                row.getCell(0).setText(rs.getString("nome"));
                row.getCell(1).setText(rs.getString("quantidade_minima"));
                row.getCell(2).setText(rs.getString("quantidade_estoque"));
            }

            // Salva o arquivo
            try (FileOutputStream out = new FileOutputStream(arquivo)) {
                document.write(out);
                JOptionPane.showMessageDialog(null, "Relatório gerado com sucesso:\n" + caminhoArquivoSaidaDoc);
            }

        } catch (SQLException e) {
            throw new DbException("Erro SQL: " + e.getMessage());
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Arquivo não pode ser criado:\n" + e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao escrever o arquivo:\n" + e.getMessage());
        }
    }

    /**
     * Gera um relatório em formato Word (.docx) contendo a lista de produtos
     * cuja quantidade em estoque está acima da quantidade máxima definida. O
     * relatório exibe nome, quantidade máxima e quantidade em estoque,
     * ordenados por nome.
     *
     * @param caminhoArquivoSaidaDoc Caminho completo ou diretório onde o
     * arquivo Word será salvo. Caso seja apenas o diretório, o arquivo será
     * salvo com o nome informado e extensão .docx.
     * @param nomeArquivoDoc Nome do arquivo Word a ser criado, usado para
     * formar o nome do arquivo caso necessário.
     */
    @Override
    public void gerarRelatorioListaDePrecoAcimaDaQuantidadeMaximaDoc(String caminhoArquivoSaidaDoc, String nomeArquivoDoc
    ) {
        System.out.println("Tentando salvar arquivo em: " + caminhoArquivoSaidaDoc);

        if (!caminhoArquivoSaidaDoc.toLowerCase().endsWith(".docx")) {
            if (caminhoArquivoSaidaDoc.endsWith("\\") || caminhoArquivoSaidaDoc.endsWith("/")) {
                caminhoArquivoSaidaDoc = String.format("%s%s.docx", caminhoArquivoSaidaDoc, nomeArquivoDoc).trim();
            } else {
                caminhoArquivoSaidaDoc = String.format("%s\\%s.docx", caminhoArquivoSaidaDoc, nomeArquivoDoc).trim();
            }
        }

        String sql = "SELECT nome, quantidade_maxima, quantidade_estoque FROM produto WHERE quantidade_estoque > quantidade_maxima ORDER BY nome ASC";

        try (PreparedStatement st = conn.prepareStatement(sql); ResultSet rs = st.executeQuery(); XWPFDocument document = new XWPFDocument()) {

            // Cria diretório se não existir
            File arquivo = new File(caminhoArquivoSaidaDoc);
            File diretorio = arquivo.getParentFile();
            if (diretorio != null && !diretorio.exists()) {
                diretorio.mkdirs();
            }

            // Cria título do documento
            XWPFParagraph titulo = document.createParagraph();
            XWPFRun runTitulo = titulo.createRun();
            runTitulo.setText("Relatório de Lista de Preços Abaixo da Quantidade Máxima");
            runTitulo.setBold(true);
            runTitulo.setFontSize(16);
            titulo.setAlignment(ParagraphAlignment.CENTER);

            // Espaço
            document.createParagraph();

            // Cria tabela
            XWPFTable table = document.createTable();

            // Cabeçalho
            XWPFTableRow header = table.getRow(0);
            header.getCell(0).setText("Nome");
            header.addNewTableCell().setText("Quantidade Máxima");
            header.addNewTableCell().setText("Quantidade em Estoque");

            // Preenche a tabela com dados
            while (rs.next()) {
                XWPFTableRow row = table.createRow();
                row.getCell(0).setText(rs.getString("nome"));
                row.getCell(1).setText(rs.getString("quantidade_maxima"));
                row.getCell(2).setText(rs.getString("quantidade_estoque"));
            }

            // Salva o arquivo
            try (FileOutputStream out = new FileOutputStream(arquivo)) {
                document.write(out);
                JOptionPane.showMessageDialog(null, "Relatório gerado com sucesso:\n" + caminhoArquivoSaidaDoc);
            }

        } catch (SQLException e) {
            throw new DbException("Erro SQL: " + e.getMessage());
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Arquivo não pode ser criado:\n" + e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao escrever o arquivo:\n" + e.getMessage());
        }
    }

    /**
     * Gera um relatório em formato Word (.docx) contendo a quantidade de
     * produtos agrupados por categoria. O relatório exibe o nome da categoria e
     * a quantidade de produtos associados, ordenados por nome da categoria.
     *
     * @param caminhoArquivoSaidaDoc Caminho completo ou diretório onde o
     * arquivo Word será salvo. Caso seja apenas o diretório, o arquivo será
     * salvo com o nome informado e extensão .docx.
     * @param nomeArquivoDoc Nome do arquivo Word a ser criado, usado para
     * formar o nome do arquivo caso necessário.
     */
    @Override
    public void gerarRelatorioListaProdutoPorCategoriaDoc(String caminhoArquivoSaidaDoc, String nomeArquivoDoc
    ) {
        System.out.println("Tentando salvar arquivo em: " + caminhoArquivoSaidaDoc);

        if (!caminhoArquivoSaidaDoc.toLowerCase().endsWith(".docx")) {
            if (caminhoArquivoSaidaDoc.endsWith("\\") || caminhoArquivoSaidaDoc.endsWith("/")) {
                caminhoArquivoSaidaDoc = String.format("%s%s.docx", caminhoArquivoSaidaDoc, nomeArquivoDoc).trim();
            } else {
                caminhoArquivoSaidaDoc = String.format("%s\\%s.docx", caminhoArquivoSaidaDoc, nomeArquivoDoc).trim();
            }
        }

        String sql = "SELECT c.nome AS nome_categoria, COUNT(p.id) AS quantidade_produtos FROM categoria c LEFT JOIN produto p ON p.categoria = c.nome GROUP BY c.nome ORDER BY c.nome ASC";

        try (PreparedStatement st = conn.prepareStatement(sql); ResultSet rs = st.executeQuery(); XWPFDocument document = new XWPFDocument()) {

            // Cria diretório se não existir
            File arquivo = new File(caminhoArquivoSaidaDoc);
            File diretorio = arquivo.getParentFile();
            if (diretorio != null && !diretorio.exists()) {
                diretorio.mkdirs();
            }

            // Cria título do documento
            XWPFParagraph titulo = document.createParagraph();
            XWPFRun runTitulo = titulo.createRun();
            runTitulo.setText("Relatório de Lista de Produtos por Categoria");
            runTitulo.setBold(true);
            runTitulo.setFontSize(16);
            titulo.setAlignment(ParagraphAlignment.CENTER);

            // Espaço
            document.createParagraph();

            // Cria tabela
            XWPFTable table = document.createTable();

            // Cabeçalho
            XWPFTableRow header = table.getRow(0);
            header.getCell(0).setText("Categoria");
            header.addNewTableCell().setText("Quantidade Produtos");

            // Preenche a tabela com dados
            while (rs.next()) {
                XWPFTableRow row = table.createRow();
                row.getCell(0).setText(rs.getString("nome_categoria"));
                row.getCell(1).setText(rs.getString("quantidade_produtos"));
            }

            // Salva o arquivo
            try (FileOutputStream out = new FileOutputStream(arquivo)) {
                document.write(out);
                JOptionPane.showMessageDialog(null, "Relatório gerado com sucesso:\n" + caminhoArquivoSaidaDoc);
            }

        } catch (SQLException e) {
            throw new DbException("Erro SQL: " + e.getMessage());
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Arquivo não pode ser criado:\n" + e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao escrever o arquivo:\n" + e.getMessage());
        }
    }

    /**
     * Gera um relatório de movimentação de produtos no formato DOCX. O arquivo
     * será salvo no caminho especificado, com o nome fornecido. Caso o caminho
     * não termine com ".docx", a extensão será adicionada automaticamente. O
     * relatório inclui uma tabela com os dados de movimentação do banco,
     * ordenados por data.
     *
     * @param caminhoArquivoSaidaDoc Caminho da pasta ou arquivo onde o
     * documento será salvo. Pode ser um diretório (com ou sem barra no final)
     * ou caminho completo.
     * @param nomeArquivoDoc Nome do arquivo a ser criado (sem extensão).
     * @throws DbException Em caso de erro na consulta SQL.
     */
    @Override
    public void gerarRelatorioMovimentacaoDoc(String caminhoArquivoSaidaDoc, String nomeArquivoDoc) {
        System.out.println("Tentando salvar arquivo em: " + caminhoArquivoSaidaDoc);

        // Ajusta o nome do arquivo se necessário
        if (!caminhoArquivoSaidaDoc.toLowerCase().endsWith(".docx")) {
            if (caminhoArquivoSaidaDoc.endsWith("\\") || caminhoArquivoSaidaDoc.endsWith("/")) {
                caminhoArquivoSaidaDoc = String.format("%s%s.docx", caminhoArquivoSaidaDoc, nomeArquivoDoc).trim();
            } else {
                caminhoArquivoSaidaDoc = String.format("%s\\%s.docx", caminhoArquivoSaidaDoc, nomeArquivoDoc).trim();
            }
        }

        String sql = "SELECT id, data, tipo, quantidade, movimentacao, status FROM registro ORDER BY data ASC";

        try (
                PreparedStatement st = conn.prepareStatement(sql); ResultSet rs = st.executeQuery(); XWPFDocument document = new XWPFDocument()) {
            File arquivo = new File(caminhoArquivoSaidaDoc);

            XWPFParagraph titulo = document.createParagraph();
            titulo.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun runTitulo = titulo.createRun();
            runTitulo.setText("Relatório de Movimentações de Produtos");
            runTitulo.setBold(true);
            runTitulo.setFontSize(16);

            document.createParagraph(); // espaço

            XWPFTable table = document.createTable();

            // Cabeçalho
            XWPFTableRow header = table.getRow(0);
            header.getCell(0).setText("ID");
            header.addNewTableCell().setText("Data");
            header.addNewTableCell().setText("Tipo");
            header.addNewTableCell().setText("Quantidade");
            header.addNewTableCell().setText("Movimentação");
            header.addNewTableCell().setText("Status");

            // Dados
            while (rs.next()) {
                XWPFTableRow row = table.createRow();
                row.getCell(0).setText(String.valueOf(rs.getInt("id")));
                row.getCell(1).setText(rs.getDate("data").toString());
                row.getCell(2).setText(rs.getString("tipo"));
                row.getCell(3).setText(String.valueOf(rs.getInt("quantidade")));
                row.getCell(4).setText(rs.getString("movimentacao"));
                row.getCell(5).setText(rs.getString("status"));
            }

            try (FileOutputStream out = new FileOutputStream(arquivo)) {
                document.write(out);
                JOptionPane.showMessageDialog(null, "Relatório gerado com sucesso:\n" + caminhoArquivoSaidaDoc);
            }

        } catch (SQLException e) {
            throw new DbException("Erro SQL: " + e.getMessage());
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Arquivo não pode ser criado:\n" + e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao escrever o arquivo:\n" + e.getMessage());
        }
    }

    /**
     * Gera um relatório de lista de preços dos produtos no formato PDF. O
     * arquivo será salvo no caminho especificado, com o nome fornecido. Caso o
     * caminho não termine com ".pdf", a extensão será adicionada
     * automaticamente. O relatório inclui uma tabela com os produtos ordenados
     * por nome. Se o espaço na página acabar, cria nova página e adiciona
     * cabeçalho novamente.
     *
     * @param caminhoArquivoSaidaPDF Caminho da pasta ou arquivo onde o PDF será
     * salvo. Pode ser um diretório (com ou sem barra no final) ou caminho
     * completo.
     * @param nomeArquivoPDF Nome do arquivo PDF a ser criado (sem extensão).
     * @throws DbException Em caso de erro na consulta SQL.
     */
    @Override
    public void gerarRelatorioListaDePrecoPDF(String caminhoArquivoSaidaPDF, String nomeArquivoPDF
    ) {
        System.out.println("Tentando salvar arquivo em: " + caminhoArquivoSaidaPDF);

        if (!caminhoArquivoSaidaPDF.toLowerCase().endsWith(".pdf")) {
            if (caminhoArquivoSaidaPDF.endsWith("\\") || caminhoArquivoSaidaPDF.endsWith("/")) {
                caminhoArquivoSaidaPDF = String.format("%s%s.pdf", caminhoArquivoSaidaPDF, nomeArquivoPDF).trim();
            } else {
                caminhoArquivoSaidaPDF = String.format("%s\\%s.pdf", caminhoArquivoSaidaPDF, nomeArquivoPDF).trim();
            }
        }

        String sql = "SELECT nome, preco_unitario, unidade, categoria FROM produto ORDER BY nome ASC";

        PDPageContentStream content = null;

        try (PreparedStatement st = conn.prepareStatement(sql); ResultSet rs = st.executeQuery(); PDDocument document = new PDDocument()) {

            File arquivo = new File(caminhoArquivoSaidaPDF);
            File diretorio = arquivo.getParentFile();
            if (diretorio != null && !diretorio.exists()) {
                diretorio.mkdirs();
            }

            PDFont fonte = PDType1Font.HELVETICA;
            PDFont fonteNegrito = PDType1Font.HELVETICA_BOLD;
            float fontSize = 11;
            float leading = 15;
            float margin = 50;
            float yStart = 750;
            float y = yStart;

            PDPage page = new PDPage();
            document.addPage(page);
            content = new PDPageContentStream(document, page);

            // Título
            content.beginText();
            content.setFont(fonteNegrito, 14);
            content.newLineAtOffset(margin, y);
            content.showText("Relatório de Lista de Preços");
            content.endText();
            y -= leading * 2;

            // Cabeçalhos
            content.setFont(fonteNegrito, fontSize);
            escreverLinha(content, y, margin, new float[]{0, 200, 300, 380}, new String[]{"Nome", "Preço", "Unidade", "Categoria"});
            y -= leading;

            content.setFont(fonte, fontSize);

            while (rs.next()) {
                if (y <= 50) {
                    content.close();
                    page = new PDPage();
                    document.addPage(page);
                    content = new PDPageContentStream(document, page);
                    y = yStart;

                    // Cabeçalho novamente na nova página
                    content.setFont(fonteNegrito, fontSize);
                    escreverLinha(content, y, margin, new float[]{0, 200, 300, 380}, new String[]{"Nome", "Preço", "Unidade", "Categoria"});
                    y -= leading;
                    content.setFont(fonte, fontSize);
                }

                String nome = rs.getString("nome");
                String preco = String.format("R$ %.2f", rs.getDouble("preco_unitario"));
                String unidade = rs.getString("unidade");
                String categoria = rs.getString("categoria");

                escreverLinha(content, y, margin, new float[]{0, 200, 300, 380}, new String[]{nome, preco, unidade, categoria});
                y -= leading;
            }

            content.close();
            document.save(caminhoArquivoSaidaPDF);
            JOptionPane.showMessageDialog(null, "Relatório gerado com sucesso:\n" + caminhoArquivoSaidaPDF);

        } catch (SQLException e) {
            throw new DbException("Erro SQL: " + e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar PDF:\n" + e.getMessage());
        }
    }

    /**
     * Gera um relatório de balanço físico-financeiro dos produtos no formato
     * PDF. O arquivo será salvo no caminho especificado, com o nome fornecido.
     * Caso o caminho não termine com ".pdf", a extensão será adicionada
     * automaticamente. O relatório inclui nome, quantidade em estoque, preço
     * unitário e valor total por produto, além de um total geral ao final. Se o
     * espaço na página acabar, cria nova página e adiciona cabeçalho novamente.
     *
     * @param caminhoArquivoSaidaPDF Caminho da pasta ou arquivo onde o PDF será
     * salvo. Pode ser um diretório (com ou sem barra no final) ou caminho
     * completo.
     * @param nomeArquivo Nome do arquivo PDF a ser criado (sem extensão).
     * @throws DbException Em caso de erro na consulta SQL.
     */
    @Override
    public void gerarRelatorioBalancoFisicoFinanceiroPDF(String caminhoArquivoSaidaPDF, String nomeArquivo
    ) {
        System.out.println("Tentando salvar arquivo em: " + caminhoArquivoSaidaPDF);

        if (!caminhoArquivoSaidaPDF.toLowerCase().endsWith(".pdf")) {
            if (caminhoArquivoSaidaPDF.endsWith("\\") || caminhoArquivoSaidaPDF.endsWith("/")) {
                caminhoArquivoSaidaPDF = String.format("%s%s.pdf", caminhoArquivoSaidaPDF, nomeArquivo).trim();
            } else {
                caminhoArquivoSaidaPDF = String.format("%s\\%s.pdf", caminhoArquivoSaidaPDF, nomeArquivo).trim();
            }
        }

        String sql = "SELECT nome, preco_unitario, quantidade_estoque FROM produto ORDER BY nome ASC";

        PDPageContentStream content = null;

        try (PreparedStatement st = conn.prepareStatement(sql); ResultSet rs = st.executeQuery(); PDDocument document = new PDDocument()) {

            File arquivo = new File(caminhoArquivoSaidaPDF);
            File diretorio = arquivo.getParentFile();
            if (diretorio != null && !diretorio.exists()) {
                diretorio.mkdirs();
            }

            PDFont fonte = PDType1Font.HELVETICA;
            PDFont fonteNegrito = PDType1Font.HELVETICA_BOLD;
            float fontSize = 11;
            float leading = 15;
            float margin = 50;
            float yStart = 750;
            float y = yStart;

            PDPage page = new PDPage();
            document.addPage(page);
            content = new PDPageContentStream(document, page);

            // Título
            content.beginText();
            content.setFont(fonteNegrito, 14);
            content.newLineAtOffset(margin, y);
            content.showText("Relatório Balanço Físico-Financeiro");
            content.endText();
            y -= leading * 2;

            // Cabeçalhos
            content.setFont(fonteNegrito, fontSize);
            escreverLinha(content, y, margin, new float[]{0, 200, 300, 400}, new String[]{"Nome", "Qtd Estoque", "Preço Unit.", "Valor Total"});
            y -= leading;
            content.setFont(fonte, fontSize);

            double valorTotalEstoque = 0.0;

            while (rs.next()) {
                if (y <= 50) {
                    content.close();
                    page = new PDPage();
                    document.addPage(page);
                    content = new PDPageContentStream(document, page);
                    y = yStart;

                    // Cabeçalho novamente
                    content.setFont(fonteNegrito, fontSize);
                    escreverLinha(content, y, margin, new float[]{0, 200, 300, 400}, new String[]{"Nome", "Qtd Estoque", "Preço Unit.", "Valor Total"});
                    y -= leading;
                    content.setFont(fonte, fontSize);
                }

                String nome = rs.getString("nome");
                int qtd = rs.getInt("quantidade_estoque");
                double preco = rs.getDouble("preco_unitario");
                double total = qtd * preco;
                valorTotalEstoque += total;

                escreverLinha(content, y, margin, new float[]{0, 200, 300, 400},
                        new String[]{nome, String.valueOf(qtd), String.format("R$ %.2f", preco), String.format("R$ %.2f", total)});
                y -= leading;
            }

            // Total geral
            y -= leading;
            content.setFont(fonteNegrito, fontSize);
            escreverLinha(content, y, margin, new float[]{0, 300}, new String[]{"TOTAL ESTOQUE:", String.format("R$ %.2f", valorTotalEstoque)});

            content.close();
            document.save(caminhoArquivoSaidaPDF);
            JOptionPane.showMessageDialog(null, "Relatório gerado com sucesso:\n" + caminhoArquivoSaidaPDF);

        } catch (SQLException e) {
            throw new DbException("Erro SQL: " + e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar PDF:\n" + e.getMessage());
        }
    }

    /**
     * Gera um relatório em PDF com a lista de produtos cujo estoque está abaixo
     * da quantidade mínima. O relatório é salvo no caminho especificado, e
     * inclui uma tabela com os campos: Nome do produto, Quantidade mínima e
     * Quantidade em estoque.
     *
     * Caso o caminho fornecido não termine com ".pdf", a extensão será
     * automaticamente adicionada. Se o diretório informado não existir, será
     * criado. O relatório suporta múltiplas páginas, adicionando cabeçalho
     * sempre que necessário.
     *
     * @param caminhoArquivoSaidaPDF Caminho do diretório ou caminho completo
     * onde o arquivo PDF será salvo. Pode terminar ou não com barra ou
     * extensão.
     * @param nomeArquivo Nome do arquivo a ser gerado (sem a extensão).
     * @throws DbException Caso ocorra erro na consulta ao banco de dados.
     */
    @Override
    public void gerarRelatorioListaDePrecoAbaixoDaQuantidadeMinimaPDF(String caminhoArquivoSaidaPDF, String nomeArquivo
    ) {
        System.out.println("Tentando salvar arquivo em: " + caminhoArquivoSaidaPDF);

        if (!caminhoArquivoSaidaPDF.toLowerCase().endsWith(".pdf")) {
            if (caminhoArquivoSaidaPDF.endsWith("\\") || caminhoArquivoSaidaPDF.endsWith("/")) {
                caminhoArquivoSaidaPDF = String.format("%s%s.pdf", caminhoArquivoSaidaPDF, nomeArquivo).trim();
            } else {
                caminhoArquivoSaidaPDF = String.format("%s\\%s.pdf", caminhoArquivoSaidaPDF, nomeArquivo).trim();
            }
        }

        String sql = "SELECT nome, quantidade_minima, quantidade_estoque FROM produto WHERE quantidade_estoque < quantidade_minima ORDER BY nome ASC";

        PDPageContentStream content = null;

        try (PreparedStatement st = conn.prepareStatement(sql); ResultSet rs = st.executeQuery(); PDDocument document = new PDDocument()) {

            File arquivo = new File(caminhoArquivoSaidaPDF);
            File diretorio = arquivo.getParentFile();
            if (diretorio != null && !diretorio.exists()) {
                diretorio.mkdirs();
            }

            PDFont fonte = PDType1Font.HELVETICA;
            PDFont fonteNegrito = PDType1Font.HELVETICA_BOLD;
            float fontSize = 11;
            float leading = 15;
            float margin = 50;
            float yStart = 750;
            float y = yStart;

            PDPage page = new PDPage();
            document.addPage(page);
            content = new PDPageContentStream(document, page);

            // Título
            content.beginText();
            content.setFont(fonteNegrito, 14);
            content.newLineAtOffset(margin, y);
            content.showText("Relatório de Produtos Abaixo da Quantidade Mínima");
            content.endText();
            y -= leading * 2;

            // Cabeçalhos
            content.setFont(fonteNegrito, fontSize);
            escreverLinha(content, y, margin, new float[]{0, 250, 400}, new String[]{"Nome", "Qtd Mínima", "Qtd Estoque"});
            y -= leading;
            content.setFont(fonte, fontSize);

            while (rs.next()) {
                if (y <= 50) {
                    content.close();
                    page = new PDPage();
                    document.addPage(page);
                    content = new PDPageContentStream(document, page);
                    y = yStart;

                    content.setFont(fonteNegrito, fontSize);
                    escreverLinha(content, y, margin, new float[]{0, 250, 400}, new String[]{"Nome", "Qtd Mínima", "Qtd Estoque"});
                    y -= leading;
                    content.setFont(fonte, fontSize);
                }

                String nome = rs.getString("nome");
                int qtdMin = rs.getInt("quantidade_minima");
                int qtdEstoque = rs.getInt("quantidade_estoque");

                escreverLinha(content, y, margin, new float[]{0, 250, 400},
                        new String[]{nome, String.valueOf(qtdMin), String.valueOf(qtdEstoque)});
                y -= leading;
            }

            content.close();
            document.save(caminhoArquivoSaidaPDF);
            JOptionPane.showMessageDialog(null, "Relatório gerado com sucesso:\n" + caminhoArquivoSaidaPDF);

        } catch (SQLException e) {
            throw new DbException("Erro SQL: " + e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar PDF:\n" + e.getMessage());
        }
    }

    /**
     * Gera um relatório em PDF com a lista de produtos cujo estoque está acima
     * da quantidade máxima. O relatório é salvo no caminho especificado, e
     * inclui uma tabela com os campos: Nome do produto, Quantidade máxima e
     * Quantidade em estoque.
     *
     * Caso o caminho fornecido não termine com ".pdf", a extensão será
     * automaticamente adicionada. Se o diretório informado não existir, será
     * criado. O relatório suporta múltiplas páginas, adicionando cabeçalho
     * sempre que necessário.
     *
     * @param caminhoArquivoSaidaPDF Caminho do diretório ou caminho completo
     * onde o arquivo PDF será salvo. Pode terminar ou não com barra ou
     * extensão.
     * @param nomeArquivo Nome do arquivo a ser gerado (sem a extensão).
     * @throws DbException Caso ocorra erro na consulta ao banco de dados.
     */
    @Override
    public void gerarRelatorioListaDePrecoAcimaDaQuantidadeMaximaPDF(String caminhoArquivoSaidaPDF, String nomeArquivo
    ) {
        System.out.println("Tentando salvar arquivo em: " + caminhoArquivoSaidaPDF);

        System.out.println("Tentando salvar arquivo em: " + caminhoArquivoSaidaPDF);

        if (!caminhoArquivoSaidaPDF.toLowerCase().endsWith(".pdf")) {
            if (caminhoArquivoSaidaPDF.endsWith("\\") || caminhoArquivoSaidaPDF.endsWith("/")) {
                caminhoArquivoSaidaPDF = String.format("%s%s.pdf", caminhoArquivoSaidaPDF, nomeArquivo).trim();
            } else {
                caminhoArquivoSaidaPDF = String.format("%s\\%s.pdf", caminhoArquivoSaidaPDF, nomeArquivo).trim();
            }
        }

        String sql = "SELECT nome, quantidade_maxima, quantidade_estoque FROM produto WHERE quantidade_estoque > quantidade_maxima ORDER BY nome ASC";

        PDPageContentStream content = null;

        try (PreparedStatement st = conn.prepareStatement(sql); ResultSet rs = st.executeQuery(); PDDocument document = new PDDocument()) {

            File arquivo = new File(caminhoArquivoSaidaPDF);
            File diretorio = arquivo.getParentFile();
            if (diretorio != null && !diretorio.exists()) {
                diretorio.mkdirs();
            }

            PDFont fonte = PDType1Font.HELVETICA;
            PDFont fonteNegrito = PDType1Font.HELVETICA_BOLD;
            float fontSize = 11;
            float leading = 15;
            float margin = 50;
            float yStart = 750;
            float y = yStart;

            PDPage page = new PDPage();
            document.addPage(page);
            content = new PDPageContentStream(document, page);

            // Título
            content.beginText();
            content.setFont(fonteNegrito, 14);
            content.newLineAtOffset(margin, y);
            content.showText("Relatório de Produtos Abaixo da Quantidade Máxima");
            content.endText();
            y -= leading * 2;

            // Cabeçalho
            content.setFont(fonteNegrito, fontSize);
            escreverLinha(content, y, margin, new float[]{0, 250, 400}, new String[]{"Nome", "Qtd Máxima", "Qtd Estoque"});
            y -= leading;
            content.setFont(fonte, fontSize);

            while (rs.next()) {
                if (y <= 50) {
                    content.close();
                    page = new PDPage();
                    document.addPage(page);
                    content = new PDPageContentStream(document, page);
                    y = yStart;

                    content.setFont(fonteNegrito, fontSize);
                    escreverLinha(content, y, margin, new float[]{0, 250, 400}, new String[]{"Nome", "Qtd Máxima", "Qtd Estoque"});
                    y -= leading;
                    content.setFont(fonte, fontSize);
                }

                String nome = rs.getString("nome");
                int qtdMax = rs.getInt("quantidade_maxima");
                int qtdEstoque = rs.getInt("quantidade_estoque");

                escreverLinha(content, y, margin, new float[]{0, 250, 400},
                        new String[]{nome, String.valueOf(qtdMax), String.valueOf(qtdEstoque)});
                y -= leading;
            }

            content.close();
            document.save(caminhoArquivoSaidaPDF);
            JOptionPane.showMessageDialog(null, "Relatório gerado com sucesso:\n" + caminhoArquivoSaidaPDF);

        } catch (SQLException e) {
            throw new DbException("Erro SQL: " + e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar PDF:\n" + e.getMessage());
        }
    }

    /**
     * Gera um relatório em formato PDF com a quantidade de produtos por
     * categoria. O relatório é gerado a partir de uma consulta SQL que agrupa
     * os produtos por categoria e apresenta a quantidade de produtos em cada
     * uma. O arquivo PDF é salvo no caminho informado, com o nome especificado.
     *
     * @param caminhoArquivoSaidaPDF Caminho absoluto ou relativo onde o arquivo
     * PDF será salvo. Caso não termine com ".pdf", será adicionado
     * automaticamente.
     * @param nomeArquivo Nome do arquivo (sem extensão) que será usado para
     * salvar o PDF, caso o caminho termine com uma barra ou contra barra.
     *
     * @throws DbException se ocorrer um erro ao executar a consulta SQL.
     */
    @Override
    public void gerarRelatorioListaProdutoPorCategoriaPDF(String caminhoArquivoSaidaPDF, String nomeArquivo
    ) {
        System.out.println("Tentando salvar arquivo em: " + caminhoArquivoSaidaPDF);

        if (!caminhoArquivoSaidaPDF.toLowerCase().endsWith(".pdf")) {
            if (caminhoArquivoSaidaPDF.endsWith("\\") || caminhoArquivoSaidaPDF.endsWith("/")) {
                caminhoArquivoSaidaPDF = String.format("%s%s.pdf", caminhoArquivoSaidaPDF, nomeArquivo).trim();
            } else {
                caminhoArquivoSaidaPDF = String.format("%s\\%s.pdf", caminhoArquivoSaidaPDF, nomeArquivo).trim();
            }
        }

        String sql = "SELECT c.nome AS nome_categoria, COUNT(p.id) AS quantidade_produtos "
                + "FROM categoria c LEFT JOIN produto p ON p.categoria = c.nome "
                + "GROUP BY c.nome ORDER BY c.nome ASC";

        PDPageContentStream content = null;

        try (PreparedStatement st = conn.prepareStatement(sql); ResultSet rs = st.executeQuery(); PDDocument document = new PDDocument()) {

            File arquivo = new File(caminhoArquivoSaidaPDF);
            File diretorio = arquivo.getParentFile();
            if (diretorio != null && !diretorio.exists()) {
                diretorio.mkdirs();
            }

            PDFont fonte = PDType1Font.HELVETICA;
            PDFont fonteNegrito = PDType1Font.HELVETICA_BOLD;
            float fontSize = 11;
            float leading = 15;
            float margin = 50;
            float yStart = 750;
            float y = yStart;

            PDPage page = new PDPage();
            document.addPage(page);
            content = new PDPageContentStream(document, page);

            // Título
            content.beginText();
            content.setFont(fonteNegrito, 14);
            content.newLineAtOffset(margin, y);
            content.showText("Relatório de Produtos por Categoria");
            content.endText();
            y -= leading * 2;

            // Cabeçalho
            content.setFont(fonteNegrito, fontSize);
            escreverLinha(content, y, margin, new float[]{0, 400}, new String[]{"Categoria", "Qtd Produtos"});
            y -= leading;
            content.setFont(fonte, fontSize);

            while (rs.next()) {
                if (y <= 50) {
                    content.close();
                    page = new PDPage();
                    document.addPage(page);
                    content = new PDPageContentStream(document, page);
                    y = yStart;

                    content.setFont(fonteNegrito, fontSize);
                    escreverLinha(content, y, margin, new float[]{0, 400}, new String[]{"Categoria", "Qtd Produtos"});
                    y -= leading;
                    content.setFont(fonte, fontSize);
                }

                String categoria = rs.getString("nome_categoria");
                int qtdProdutos = rs.getInt("quantidade_produtos");

                escreverLinha(content, y, margin, new float[]{0, 400},
                        new String[]{categoria, String.valueOf(qtdProdutos)});
                y -= leading;
            }

            content.close();
            document.save(caminhoArquivoSaidaPDF);
            JOptionPane.showMessageDialog(null, "Relatório gerado com sucesso:\n" + caminhoArquivoSaidaPDF);

        } catch (SQLException e) {
            throw new DbException("Erro SQL: " + e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar PDF:\n" + e.getMessage());
        }
    }

    /**
     * Gera um relatório em formato PDF contendo os registros de movimentações
     * no estoque. O relatório inclui informações como ID, data da movimentação,
     * tipo (entrada/saída), quantidade movimentada, descrição da movimentação e
     * status do estoque. O conteúdo é obtido a partir da tabela "registro" no
     * banco de dados e salvo como um arquivo PDF no caminho especificado.
     *
     * @param caminhoArquivoSaidaPDF Caminho onde o arquivo PDF será salvo. Se
     * não terminar com ".pdf", a extensão será adicionada automaticamente.
     * @param nomeArquivoPDF Nome do arquivo (sem extensão) a ser utilizado se o
     * caminho fornecido terminar com "/" ou "\\".
     *
     * @throws DbException se houver erro ao acessar ou consultar o banco de
     * dados.
     */
    @Override
    public void gerarRelatorioMovimentacaoPDF(String caminhoArquivoSaidaPDF, String nomeArquivoPDF) {
        System.out.println("Tentando salvar arquivo em: " + caminhoArquivoSaidaPDF);

        if (!caminhoArquivoSaidaPDF.toLowerCase().endsWith(".pdf")) {
            if (caminhoArquivoSaidaPDF.endsWith("\\") || caminhoArquivoSaidaPDF.endsWith("/")) {
                caminhoArquivoSaidaPDF = String.format("%s%s.pdf", caminhoArquivoSaidaPDF, nomeArquivoPDF).trim();
            } else {
                caminhoArquivoSaidaPDF = String.format("%s\\%s.pdf", caminhoArquivoSaidaPDF, nomeArquivoPDF).trim();
            }
        }

        String sql = "SELECT id, data, tipo, quantidade, movimentacao, status FROM registro ORDER BY data ASC";

        try (
                PreparedStatement st = conn.prepareStatement(sql); ResultSet rs = st.executeQuery(); PDDocument document = new PDDocument()) {
            File arquivo = new File(caminhoArquivoSaidaPDF);
            File diretorio = arquivo.getParentFile();
            if (diretorio != null && !diretorio.exists()) {
                diretorio.mkdirs();
            }

            PDFont fonte = PDType1Font.HELVETICA;
            PDFont fonteNegrito = PDType1Font.HELVETICA_BOLD;
            float fontSize = 11, leading = 15, margin = 50;
            float yStart = 750, y = yStart;

            PDPage page = new PDPage();
            document.addPage(page);
            PDPageContentStream content = new PDPageContentStream(document, page);

            // Título
            content.beginText();
            content.setFont(fonteNegrito, 14);
            content.newLineAtOffset(margin, y);
            content.showText("Relatório de Movimentação");
            content.endText();
            y -= leading * 2;

            // Cabeçalho
            content.setFont(fonteNegrito, fontSize);
            escreverLinha(content, y, margin, new float[]{0, 80, 160, 260, 360, 460},
                    new String[]{"ID", "Data", "Tipo", "Quantidade", "Movimentação", "Status Estoque"});
            y -= leading;
            content.setFont(fonte, fontSize);

            while (rs.next()) {
                if (y <= 50) {
                    content.close();
                    page = new PDPage();
                    document.addPage(page);
                    content = new PDPageContentStream(document, page);
                    y = yStart;

                    content.setFont(fonteNegrito, fontSize);
                    escreverLinha(content, y, margin, new float[]{0, 80, 160, 260, 360, 460},
                            new String[]{"ID", "Data", "Tipo", "Quantidade", "Movimentação", "Status Estoque"});
                    y -= leading;
                    content.setFont(fonte, fontSize);
                }

                escreverLinha(content, y, margin, new float[]{0, 80, 160, 260, 360, 460}, new String[]{
                    String.valueOf(rs.getInt("id")),
                    rs.getDate("data").toString(),
                    rs.getString("tipo"),
                    String.valueOf(rs.getInt("quantidade")),
                    rs.getString("movimentacao"),
                    rs.getString("status")
                });
                y -= leading;
            }

            content.close();
            document.save(caminhoArquivoSaidaPDF);
            JOptionPane.showMessageDialog(null, "Relatório gerado com sucesso:\n" + caminhoArquivoSaidaPDF);

        } catch (SQLException e) {
            throw new DbException("Erro SQL: " + e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar PDF:\n" + e.getMessage());
        }
    }

    /**
     * Escreve uma linha de texto no conteúdo do PDF, posicionando cada texto de
     * acordo com os deslocamentos especificados.
     *
     * Cada string do array {@code textos} será escrita em uma posição
     * horizontal relativa a {@code margin} + {@code xOffsets[i]} e vertical
     * {@code y}. Este método é utilizado para compor tabelas ou linhas com
     * múltiplas colunas no PDF.
     *
     * @param content Objeto {@link PDPageContentStream} usado para desenhar no
     * PDF.
     * @param y Posição vertical (eixo Y) onde a linha será escrita.
     * @param margin Margem esquerda da página.
     * @param xOffsets Vetor com deslocamentos horizontais para cada coluna de
     * texto.
     * @param textos Vetor de textos a serem exibidos na linha.
     *
     * @throws IOException Se ocorrer erro ao escrever no PDF.
     */
    private void escreverLinha(PDPageContentStream content, float y, float margin, float[] xOffsets, String[] textos) throws IOException {
        for (int i = 0; i < textos.length; i++) {
            content.beginText();
            content.newLineAtOffset(margin + xOffsets[i], y);
            content.showText(textos[i]);
            content.endText();
        }
    }

    /**
     * Cria e retorna uma instância da classe {@link Produto} a partir dos dados
     * de um {@link ResultSet}.
     *
     * Os campos esperados no {@code ResultSet} são: {@code id}, {@code nome},
     * {@code preco_unitario}, {@code unidade}, {@code quantidade_estoque},
     * {@code quantidade_minima}, {@code quantidade_maxima}, e {@code categoria}
     * (nome da categoria como {@link String}).
     *
     * @param rs ResultSet contendo os dados do produto.
     * @return Objeto {@link Produto} totalmente populado com os dados do
     * ResultSet.
     * @throws SQLException Se ocorrer erro ao acessar os dados do ResultSet.
     */
    private Produto instanciarProduto(ResultSet rs) throws SQLException {
        Produto prod = new Produto();
        prod.setId(rs.getInt("id"));
        prod.setNome(rs.getString("nome"));
        prod.setPreco(rs.getDouble("preco_unitario"));
        prod.setUnidade(rs.getString("unidade"));
        prod.setQuantidade(rs.getInt("quantidade_estoque"));
        prod.setQuantidadeMinima(rs.getInt("quantidade_minima"));
        prod.setQuantidadeMaxima(rs.getInt("quantidade_maxima"));

        Categoria cat = new Categoria();
        cat.setNome(rs.getString("categoria"));
        prod.setCategoria(cat);

        return prod;
    }

    /**
     * Cria e retorna uma instância da classe {@link Categoria} a partir dos
     * dados de um {@link ResultSet}.
     *
     * Os campos esperados no {@code ResultSet} são: {@code categoria_nome}
     * (nome da categoria), {@code tamanho} (valor do enum {@link Tamanho}) e
     * {@code embalagem} (valor do enum {@link Embalagem}). O ID da categoria é
     * fornecido separadamente como parâmetro.
     *
     * @param rs ResultSet contendo os dados da categoria.
     * @param categoriaId ID da categoria a ser atribuído ao objeto
     * {@link Categoria}.
     * @return Objeto {@link Categoria} populado com os dados do ResultSet.
     * @throws SQLException Se ocorrer erro ao acessar os dados do ResultSet.
     */
    private Categoria instanciarCategoria(ResultSet rs, int categoriaId) throws SQLException {
        Categoria cat = new Categoria();
        cat.setId(categoriaId);
        cat.setNome(rs.getString("categoria_nome"));
        cat.setTamanho(Tamanho.valueOf(rs.getString("tamanho").toUpperCase()));
        cat.setEmbalagem(Embalagem.valueOf(rs.getString("embalagem").toUpperCase()));
        return cat;
    }

}
