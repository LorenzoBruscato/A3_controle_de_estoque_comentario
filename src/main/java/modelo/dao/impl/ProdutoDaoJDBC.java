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
 * Implementação JDBC da interface ProdutoDao para operações de banco de dados
 * relacionadas a produtos. Esta classe gerencia todas as operações CRUD para
 * produtos, incluindo registro de movimentações.
 *
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
     * Busca um produto pelo seu ID.
     *
     * @param id ID do produto a ser buscado
     * @return Objeto Produto correspondente ao ID, ou null se não encontrado
     * @throws DbException Se ocorrer um erro de banco de dados
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
     * Insere um novo produto no banco de dados e registra a movimentação.
     *
     * @param obj Produto a ser cadastrado
     * @throws DbException Se ocorrer um erro de banco de dados ou nenhuma linha
     * for afetada
     * @throws IllegalArgumentException Se o produto for nulo
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

                        String sqlRegistro = "INSERT INTO registro (data, tipo, quantidade, movimentacao, status) VALUES (?, ?, ?, ?, ?)";

                        try (PreparedStatement str = conn.prepareStatement(sqlRegistro)) {
                            str.setDate(1, new java.sql.Date(System.currentTimeMillis()));
                            str.setString(2, obj.getNome());  // Passa o id do produto
                            str.setInt(3, obj.getQuantidade());
                            str.setString(4, Registro.Movimentacao.ENTRADA.name());
                            str.setString(5, Registro.Status.ADICIONADO.name());
                            str.executeUpdate();
                        }
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
     * Atualiza um produto existente no banco de dados e registra a
     * movimentação.
     *
     * @param obj Produto com os dados atualizados
     * @param reg Registro contendo informações sobre a movimentação
     * @throws DbException Se ocorrer um erro de banco de dados
     * @throws IllegalArgumentException Se o produto ou registro forem nulos
     */
    @Override
    public void atualizarProduto(Produto obj, Registro reg) {
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

        // Inserir registro da movimentação com status
        String sqlr = "INSERT INTO registro (data, tipo, quantidade, movimentacao, status) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement st = conn.prepareStatement(sqlr)) {
            java.sql.Date sqlDate = new java.sql.Date(reg.getData().getTime());
            st.setDate(1, sqlDate);
            st.setString(2, reg.getTipoDoProduto().getNome());
            st.setInt(3, reg.getQuantidade());
            st.setString(4, reg.getMovimentacao().name());
            st.setString(5, reg.getStatus().name());  // Adicionado status aqui
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    /**
     * Atualiza a categoria de todos os produtos que possuem a categoria antiga.
     *
     * @param nomeNovo Nome da nova categoria
     * @param nomeAntigo Nome da categoria a ser substituída
     * @throws DbException Se ocorrer um erro de banco de dados
     * @throws IllegalArgumentException Se qualquer dos nomes for nulo ou vazio
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
     * Remove um produto do banco de dados e registra a exclusão.
     *
     * @param objid ID do produto a ser removido
     * @throws DbException Se o produto não for encontrado ou ocorrer um erro de
     * banco de dados
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
     * Recupera todos os produtos do banco de dados com suas categorias
     * associadas.
     *
     * @return Lista de todos os produtos ordenados por nome
     * @throws DbException Se ocorrer um erro de banco de dados
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
     *
     * @param percentual
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
     * Aumenta o preço de todos os produtos pelo percentual especificado.
     *
     * @param percentual Percentual de aumento (ex: 10 para 10%)
     * @throws DbException Se ocorrer um erro de banco de dados
     * @throws IllegalArgumentException Se o percentual for negativo
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
     * Diminui o preço de todos os produtos pelo percentual especificado.
     *
     * @param percentual Percentual de redução (ex: 10 para 10%)
     * @throws DbException Se ocorrer um erro de banco de dados
     * @throws IllegalArgumentException Se o percentual for negativo
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
     *
     * @param percentual
     * @param categoria
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
     * Gera um relatório em Excel com a lista de preços de todos os produtos.
     *
     * @param caminhoArquivoSaidaExcel Caminho onde o arquivo será salvo (pode
     * incluir ou não a extensão .xlsx)
     * @param nomePlanilha Nome da planilha que será criada no arquivo Excel
     * @throws DbException Se ocorrer um erro ao acessar o banco de dados
     * @throws IllegalArgumentException Se os parâmetros forem nulos ou vazios
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
     * Gera um relatório em Excel com o balanço físico-financeiro do estoque
     * (quantidade e valor total por produto).
     *
     * @param caminhoArquivoSaidaExcel Caminho onde o arquivo será salvo
     * @param nomePlanilha Nome da planilha no arquivo Excel
     * @throws DbException Se ocorrer erro no banco de dados
     * @throws IOException Se ocorrer erro ao escrever o arquivo
     *
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
     * Gera um relatório em Excel com os produtos que estão abaixo da quantidade
     * mínima em estoque.
     *
     * @param caminhoArquivoSaidaExcel Caminho para salvar o arquivo Excel
     * @param nomePlanilha Nome da planilha no arquivo
     * @throws DbException Se ocorrer erro na consulta SQL
     * @throws FileNotFoundException Se não conseguir criar o arquivo
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
     * Gera um relatório em Excel com os produtos que estão acima da quantidade
     * máxima em estoque.
     *
     * @param caminhoArquivoSaidaExcel Caminho completo ou diretório para salvar
     * o arquivo
     * @param nomePlanilha Nome da planilha Excel
     * @throws DbException Para erros de banco de dados
     * @throws IOException Para erros de arquivo
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
     * Gera um relatório em Excel agrupando produtos por categoria com contagem.
     *
     * @param caminhoArquivoSaidaExcel Local de saída do arquivo (auto-completa
     * extensão se necessário)
     * @param nomePlanilha Nome da aba no Excel
     * @throws DbException Para erros SQL
     * @throws IOException Para erros de I/O
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
     * Gera um relatório em Excel com todas as movimentações registradas no
     * sistema.
     *
     * @param caminhoArquivoSaidaExcel Caminho para o arquivo de saída
     * @param nomePlanilha Nome da planilha no arquivo Excel
     * @throws DbException Se ocorrer erro no banco de dados
     * @throws FileNotFoundException Se o arquivo não puder ser criado
     * @throws IOException Se ocorrer erro ao escrever no arquivo
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
     * Gera um relatório em formato DOCX (Word) com a lista de preços de todos
     * os produtos.
     *
     * @param caminhoArquivoSaidaDoc Caminho onde o arquivo será salvo (pode
     * incluir ou não a extensão .docx)
     * @param nomeArquivoDoc Nome do documento que será criado
     * @throws DbException Se ocorrer um erro ao acessar o banco de dados
     * @throws IOException Se ocorrer erro ao escrever o arquivo
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
     * Gera um relatório em DOCX com o balanço físico-financeiro do estoque.
     *
     * @param caminhoArquivoSaidaDoc Caminho para o arquivo de saída
     * @param nomeArquivoDoc Nome do documento Word
     * @throws DbException Para erros de banco de dados
     * @throws IOException Para erros de arquivo
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
     * Gera um relatório em DOCX com produtos abaixo do estoque mínimo.
     *
     * @param caminhoArquivoSaidaDoc Caminho para salvar o documento
     * @param nomeArquivoDoc Nome do arquivo Word
     * @throws DbException Se a consulta SQL falhar
     * @throws IOException Se ocorrer erro de I/O
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
     * Gera um relatório em DOCX com produtos acima do estoque máximo.
     *
     * @param caminhoArquivoSaidaDoc Caminho de saída do documento
     * @param nomeArquivoDoc Nome do arquivo Word
     * @throws DbException Para erros SQL
     * @throws IOException Para erros de arquivo
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
     * Gera um relatório em DOCX agrupando produtos por categoria.
     *
     * @param caminhoArquivoSaidaDoc Local para salvar o documento
     * @param nomeArquivoDoc Nome do arquivo DOCX
     * @throws DbException Para erros de banco de dados
     * @throws IOException Para erros de escrita
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
     * Gera um relatório em DOCX com o histórico de movimentações de produtos.
     *
     * @param caminhoArquivoSaidaDoc Caminho completo ou diretório para salvar
     * @param nomeArquivoDoc Nome do documento Word
     * @throws DbException Para erros na consulta
     * @throws IOException Para erros no arquivo
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
     * Gera um relatório PDF com a lista de preços de todos os produtos.
     *
     * @param caminhoArquivoSaidaPDF Caminho onde o PDF será salvo
     * (auto-completa extensão .pdf se necessário)
     * @param nomeArquivoPDF Nome base do arquivo PDF (usado se caminho for
     * diretório)
     * @throws DbException Se ocorrer erro na consulta SQL
     * @throws IOException Se ocorrer erro na geração do PDF
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
     * Gera um relatório PDF com o balanço físico-financeiro do estoque.
     *
     * @param caminhoArquivoSaidaPDF Caminho de saída para o arquivo PDF
     * @param nomeArquivo Nome do arquivo PDF
     * @throws DbException Para erros de banco de dados
     * @throws IOException Para erros de escrita do PDF
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
     * Gera um relatório PDF com produtos abaixo do estoque mínimo.
     *
     * @param caminhoArquivoSaidaPDF Local para salvar o PDF
     * @param nomeArquivo Nome do arquivo PDF
     * @throws DbException Se a consulta SQL falhar
     * @throws IOException Se ocorrer erro ao gerar PDF
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
     * Gera um relatório PDF com produtos acima do estoque máximo.
     *
     * @param caminhoArquivoSaidaPDF Caminho para o arquivo PDF
     * @param nomeArquivo Nome do arquivo PDF
     * @throws DbException Para erros SQL
     * @throws IOException Para erros de arquivo
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
     * Gera um relatório PDF agrupando produtos por categoria.
     *
     * @param caminhoArquivoSaidaPDF Caminho de saída do PDF
     * @param nomeArquivo Nome do arquivo PDF
     * @throws DbException Para erros de banco de dados
     * @throws IOException Para erros de geração de PDF
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
     * Gera um relatório PDF com o histórico de movimentações de estoque.
     *
     * @param caminhoArquivoSaidaPDF Caminho completo ou diretório para salvar
     * @param nomeArquivoPDF Nome do arquivo PDF
     * @throws DbException Para erros na consulta
     * @throws IOException Para erros no arquivo
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
     * Método auxiliar para escrever uma linha formatada no PDF.
     *
     * @param content Fluxo de conteúdo do PDF
     * @param y Posição vertical inicial
     * @param margin Margem esquerda
     * @param xOffsets Array de offsets horizontais para cada coluna
     * @param textos Array de textos para cada coluna
     * @throws IOException Se ocorrer erro ao escrever no PDF
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
     * Instancia um objeto Produto a partir de um ResultSet.
     *
     * @param rs ResultSet com os dados do produto
     * @return Objeto Produto populado
     * @throws SQLException Se ocorrer erro ao acessar os dados
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
     * Instancia um objeto Categoria a partir de um ResultSet.
     *
     * @param rs ResultSet com os dados da categoria
     * @param categoriald ID da categoria
     * @return Objeto Categoria populado
     * @throws SQLException Se ocorrer erro ao acessar os dados
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
