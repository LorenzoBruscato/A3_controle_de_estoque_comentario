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
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class ProdutoDaoJDBC implements ProdutoDao {

    private Connection conn;

    public ProdutoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

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
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                    String sqlRegistro = "INSERT INTO registro (data, tipo, quantidade, movimentacao) VALUES(?,?,?,?)";
                    try (PreparedStatement str = conn.prepareStatement(sqlRegistro)) {
                        str.setDate(1, new java.sql.Date(System.currentTimeMillis()));
                        str.setString(2, obj.getNome());
                        str.setInt(3, obj.getQuantidade());
                        str.setString(4, "ENTRADA");
                        str.executeUpdate();
                    }
                }
            } else {
                throw new DbException("Unexpected error! No rows affected");
            }
            
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

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

    @Override
    public void deletarProdutoPorId(int objId) {
        String sql
                = "DELETE FROM produto "
                + "WHERE id = ?";

        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, objId);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

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

                Produto prod = instanciarProduto(rs, cat);
                lista.add(prod);
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

        return lista;
    }

    @Override
    public void aumentarTodosPrecos(double percentual) {
        String sql = "UPDATE produto SET preco_unitario = preco_unitario * (1 + ? / 100)";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setDouble(1, percentual);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void diminuirTodosPrecos(double percentual) {
        String sql = "UPDATE produto SET preco_unitario = preco_unitario * (1 - ? / 100)";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setDouble(1, percentual);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void aumentarPrecoPorCategoria(double percentual, String categoria) {
        String sql = "UPDATE produto SET preco_unitario = preco_unitario * (1 + ? / 100) WHERE categoria = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setDouble(1, percentual);
            st.setString(2, categoria);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void diminuirPrecoPorCategoria(double percentual, String categoria) {
        String sql = "UPDATE produto SET preco_unitario = preco_unitario * (1 - ? / 100) WHERE categoria = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setDouble(1, percentual);
            st.setString(2, categoria);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void gerarRelatorioListaDePrecoExcel(String caminhoArquivoSaidaExcel, String nomePlanilha) {
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

    @Override
    public void gerarRelatorioBalancoFisicoFinanceiroExcel(String caminhoArquivoSaidaExcel, String nomePlanilha) {
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

    @Override
    public void gerarRelatorioListaDePrecoAbaixoDaQuantidadeMinimaExcel(String caminhoArquivoSaidaExcel, String nomePlanilha) {
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

    @Override
    public void gerarRelatorioListaDePrecoAbaixoDaQuantidadeMaximaExcel(String caminhoArquivoSaidaExcel, String nomePlanilha) {
        System.out.println("Tentando salvar arquivo em: " + caminhoArquivoSaidaExcel);

        // Força extensão e nome do arquivo se for só pasta
        if (!caminhoArquivoSaidaExcel.toLowerCase().endsWith(".xlsx")) {
            if (caminhoArquivoSaidaExcel.endsWith("\\") || caminhoArquivoSaidaExcel.endsWith("/")) {
                caminhoArquivoSaidaExcel = String.format("%s%s.xlsx", caminhoArquivoSaidaExcel, nomePlanilha).trim();
            } else {
                caminhoArquivoSaidaExcel = String.format("%s\\%s.xlsx", caminhoArquivoSaidaExcel, nomePlanilha).trim();
            }
        }

        String sql = "SELECT nome, quantidade_maxima, quantidade_estoque FROM produto WHERE quantidade_estoque < quantidade_maxima ORDER BY nome ASC";

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

    @Override
    public void gerarRelatorioListaProdutoPorCategoriaExcel(String caminhoArquivoSaidaExcel, String nomePlanilha) {
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

    @Override
    public void gerarRelatorioListaDePrecoDoc(String caminhoArquivoSaidaDoc, String nomeArquivoDoc) {
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

    @Override
    public void gerarRelatorioBalancoFisicoFinanceiroDOC(String caminhoArquivoSaidaDoc, String nomeArquivoDoc) {
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

    @Override
    public void gerarRelatorioListaDePrecoAbaixoDaQuantidadeMinimaDoc(String caminhoArquivoSaidaDoc, String nomeArquivoDoc) {
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

    @Override
    public void gerarRelatorioListaDePrecoAbaixoDaQuantidadeMaximaDoc(String caminhoArquivoSaidaDoc, String nomeArquivoDoc) {
        System.out.println("Tentando salvar arquivo em: " + caminhoArquivoSaidaDoc);

        if (!caminhoArquivoSaidaDoc.toLowerCase().endsWith(".docx")) {
            if (caminhoArquivoSaidaDoc.endsWith("\\") || caminhoArquivoSaidaDoc.endsWith("/")) {
                caminhoArquivoSaidaDoc = String.format("%s%s.docx", caminhoArquivoSaidaDoc, nomeArquivoDoc).trim();
            } else {
                caminhoArquivoSaidaDoc = String.format("%s\\%s.docx", caminhoArquivoSaidaDoc, nomeArquivoDoc).trim();
            }
        }

        String sql = "SELECT nome, quantidade_maxima, quantidade_estoque FROM produto WHERE quantidade_estoque < quantidade_maxima ORDER BY nome ASC";

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

    @Override
    public void gerarRelatorioListaProdutoPorCategoriaDoc(String caminhoArquivoSaidaDoc, String nomeArquivoDoc) {
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

    @Override
    public void gerarRelatorioListaDePrecoPDF(String caminhoArquivoSaidaPDF, String nomeArquivoPDF) {
        System.out.println("Tentando salvar arquivo em: " + caminhoArquivoSaidaPDF);

        // Ajusta nome do arquivo
        if (!caminhoArquivoSaidaPDF.toLowerCase().endsWith(".pdf")) {
            if (caminhoArquivoSaidaPDF.endsWith("\\") || caminhoArquivoSaidaPDF.endsWith("/")) {
                caminhoArquivoSaidaPDF = String.format("%s%s.pdf", caminhoArquivoSaidaPDF, nomeArquivoPDF).trim();
            } else {
                caminhoArquivoSaidaPDF = String.format("%s\\%s.pdf", caminhoArquivoSaidaPDF, nomeArquivoPDF).trim();
            }
        }

        String sql = "SELECT nome, preco_unitario, unidade, categoria FROM produto ORDER BY nome ASC";

        try (PreparedStatement st = conn.prepareStatement(sql); ResultSet rs = st.executeQuery(); PDDocument document = new PDDocument()) {

            // Cria diretório se não existir
            File arquivo = new File(caminhoArquivoSaidaPDF);
            File diretorio = arquivo.getParentFile();
            if (diretorio != null && !diretorio.exists()) {
                diretorio.mkdirs();
            }

            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
                contentStream.setLeading(14.5f);
                contentStream.newLineAtOffset(50, 750);

                contentStream.showText("Relatório de Lista de Preços");
                contentStream.newLine();
                contentStream.newLine();

                // Cabeçalho da tabela
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.showText(String.format("%-30s %-15s %-10s %-15s", "Nome", "Preço", "Unidade", "Categoria"));
                contentStream.newLine();

                // Dados
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                while (rs.next()) {
                    String linha = String.format(
                            "%-30s %-15s %-10s %-15s",
                            rs.getString("nome"),
                            String.format("R$ %.2f", rs.getDouble("preco_unitario")),
                            rs.getString("unidade"),
                            rs.getString("categoria")
                    );
                    contentStream.showText(linha);
                    contentStream.newLine();
                }

                contentStream.endText();
            }
            // Salva o arquivo
            document.save(caminhoArquivoSaidaPDF);
            JOptionPane.showMessageDialog(null, "Relatório gerado com sucesso:\n" + caminhoArquivoSaidaPDF);

        } catch (SQLException e) {
            throw new DbException("Erro SQL: " + e.getMessage());
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Arquivo não pode ser criado:\n" + e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao escrever o arquivo:\n" + e.getMessage());
        }
    }

    private Produto instanciarProduto(ResultSet rs, Categoria cat) throws SQLException {
        Produto prod = new Produto();
        prod.setId(rs.getInt("id"));
        prod.setNome(rs.getString("nome"));
        prod.setPreco(rs.getDouble("preco_unitario"));
        prod.setUnidade(rs.getString("unidade"));
        prod.setQuantidade(rs.getInt("quantidade_estoque"));
        prod.setQuantidadeMinima(rs.getInt("quantidade_minima"));
        prod.setQuantidadeMaxima(rs.getInt("quantidade_maxima"));
        prod.setCategoria(cat);
        return prod;
    }

    private Categoria instanciarCategoria(ResultSet rs, int categoriaId) throws SQLException {
        Categoria cat = new Categoria();
        cat.setId(categoriaId);
        cat.setNome(rs.getString("categoria_nome"));
        cat.setTamanho(Tamanho.valueOf(rs.getString("tamanho").toUpperCase()));
        cat.setEmbalagem(Embalagem.valueOf(rs.getString("embalagem").toUpperCase()));
        return cat;
    }

    private Registro instanciarRegistro(ResultSet rs, Produto produto) throws SQLException {
        Registro reg = new Registro();
        reg.setId(rs.getInt("id"));
        reg.setData(rs.getDate("data"));
        reg.setTipoDoProduto(produto); // Produto passado como parâmetro
        reg.setQuantidade(rs.getInt("quantidade"));
        reg.setMovimentacao(Registro.Movimentacao.valueOf(rs.getString("tipo").toUpperCase()));
        return reg;
    }
}
