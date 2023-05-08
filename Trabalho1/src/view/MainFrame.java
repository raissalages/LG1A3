package view;

import java.awt.*;

import javax.swing.*;

import model.Emprestimo;
import model.Livro;
import model.TipoUsuario;
import model.Usuario;
import dao.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EtchedBorder;
import java.util.List;


public class MainFrame extends JFrame {

    private JFrame copyMainFrame;
    private static final long serialVersionUID = 1L;
    private Container contentPane;
    private Container baseContentPane;

    private JPanel header;
    private JLabel lblLogo;
    private JScrollPane scrollPane;
    private JComboBox<?> cbAtributos;
    private JPanel panelRodape;
    private JTable tabela;
    private JButton btnBuscar;
    private JTextField textFieldProcurar;

    private ImageIcon icon = new ImageIcon("images/icon.png");
    private Font globalFont = new Font("Arial", Font.PLAIN, 14);
    private Font globalSmallFont = new Font("Arial", Font.PLAIN, 12);
    private Color background = new Color(230, 233, 240);
    private Color focusLightBlue = new Color(193, 207, 230);
    private Color formLightGray = new Color(195, 195, 195);
    private boolean funcionarioSelecionado;
    private boolean adminSelecionado;
    private String senhaFuncionario = "1";
    private String senhaAdmin = "2";

    private LivroDao livroDao = new LivroDao();
    private UsuarioDao usuarioDao = new UsuarioDao();
    private EmprestimoDao emprestimoDao = new EmprestimoDao();

    private String[] atributosLivro =  {"Titulo", "Autor", "Editora", "Ano", "Exemplares"};
    private String[] atributosUsuario = {"Nome", "CPF", "Endereco", "Email", "Tipo"};
    private String[] atributosEmprestimo = {"Livro", "Usuario", "Data_Emprestimo", "Data_Devolução"};
    private String[][] livrosArray = null;
    private String[][] usuariosArray = null;
    private String[][] emprestimosArray = null;
    private List<Livro> listaLivros;
    private List<Usuario> listaUsuarios;
    private List<Emprestimo> listaEmprestimos;

    private int alturaDaJanela = 800;
    private int larguraDaJanela = 1200;
    private int grossuraDaBordaDaJanela = 16;
    private int distanciaElementos = 10;
    private int btnBuscarGrossura = 89;
    private int cbStributoLivroGrossura = 150;
    private TipoUsuario tipoSecao;

    public MainFrame(){
        //TODO contentPane.revalidate();
        copyMainFrame = this;

        contentPane = getContentPane();
        contentPane.setFont(globalFont);

        contentPane = gerarLayoutPrincipal();

        contentPane = gerarContainerDeLogin();
        //contentPane = gerarContainerDeSignIn();
        //contentPane = gerarContainerDeCliente();
        //contentPane = gerarContainerDeFuncionario();
        //contentPane = gerarContainerDeAdmin();

        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = graphicsEnvironment.getDefaultScreenDevice();
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
        int x = ((int)rect.getMaxX() - larguraDaJanela)/2;
        int y = ((int)rect.getMaxY() - alturaDaJanela)/2;
        this.setLocation(x, y);

        setTitle("LogoBiblioteca");
        this.setVisible(true);
        this.setSize(larguraDaJanela,alturaDaJanela);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(icon.getImage());
    }

    private Container gerarLayoutPrincipal(){
        baseContentPane = contentPane;

        lblLogo = new JLabel("LogoBiblioteca");
        lblLogo.setBounds(0, 7, 300, 29);
        lblLogo.setFont(new Font("Cambria", Font.PLAIN, 24));
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        header = new JPanel();

        baseContentPane.setBackground(background);
        baseContentPane.setLayout(null);

        header.setBackground(focusLightBlue);
        header.setBounds(0, 0, larguraDaJanela - grossuraDaBordaDaJanela, 45);
        baseContentPane.add(header);
        header.setLayout(null);

        header.add(lblLogo);

        return baseContentPane;
    }

    private void gerarGui(TipoUsuario tipoUsuario) {
        tipoSecao = tipoUsuario;
        contentPane.removeAll();
        switch(tipoUsuario) {
            case CLIENTE:  contentPane = gerarContainerDeCliente(); break;
            case FUNCIONARIO: contentPane = gerarContainerDeFuncionario(); break;
            case ADMIN: contentPane = gerarContainerDeAdmin(); break;
            case USUARIO: //contentPane = gerarErro("foi passado um Usuario base para gerarGui()"); break;
        }
        contentPane.revalidate();
        contentPane.repaint();
    }

    //Abas
    private JButton gerarAbaLivrosNaoInteragivel(){

        JButton btnGuiaLivros = new JButton("Livros");

        btnGuiaLivros.setFocusable(false);
        btnGuiaLivros.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        btnGuiaLivros.setFont(globalFont);
        btnGuiaLivros.setBackground(background);
        btnGuiaLivros.setBounds(60, 64, 110, 23);

        return btnGuiaLivros;
    }

    private JButton gerarAbaLivros() {
        JButton btnGuiaLivros = new JButton("Livros");
        btnGuiaLivros.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                contentPane.remove(scrollPane);
                gerarTabelaDeLivros();
                gerarScrollPane(tabela);
                contentPane.add(scrollPane);
                contentPane.revalidate();
                contentPane.repaint();
            }
        });
        btnGuiaLivros.setFocusable(false);
        btnGuiaLivros.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        btnGuiaLivros.setFont(globalFont);
        btnGuiaLivros.setBackground(background);
        btnGuiaLivros.setBounds(60, 64, 110, 23);

        return btnGuiaLivros;
    }

    private JButton gerarAbaUsuarios() {
        JButton btnGuiaUsuarios = new JButton("Usuarios");
        btnGuiaUsuarios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                contentPane.remove(scrollPane);
                gerarTabelaDeUsuarios();
                gerarScrollPane(tabela);
                contentPane.add(scrollPane);
                contentPane.revalidate();
                contentPane.repaint();
            }
        });
        btnGuiaUsuarios.setFocusable(false);
        btnGuiaUsuarios.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        btnGuiaUsuarios.setFont(globalFont);
        btnGuiaUsuarios.setBackground(background);
        btnGuiaUsuarios.setBounds(276, 64, 110, 23);

        return btnGuiaUsuarios;
    }

    private JButton gerarAbaEmprestimos() {
        JButton btnGuiaEmprestimos = new JButton("Emprestimos");

        btnGuiaEmprestimos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                contentPane.remove(scrollPane);
                gerarTabelaDeEmprestimos();
                gerarScrollPane(tabela);
                contentPane.add(scrollPane);
                contentPane.revalidate();
                contentPane.repaint();
            }
        });
        btnGuiaEmprestimos.setFocusable(false);
        btnGuiaEmprestimos.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        btnGuiaEmprestimos.setFont(globalFont);
        btnGuiaEmprestimos.setBackground(background);
        btnGuiaEmprestimos.setBounds(168, 64, 110, 23);

        return btnGuiaEmprestimos;
    }

    //Container de Usuario
    private Container gerarContainerDeCliente() {
        Container guiClienteContentPane = gerarContainerDeTabela();

        gerarTabelaDeLivros();
        gerarScrollPane(tabela);
        guiClienteContentPane.add(scrollPane);
        if(panelRodape != null) {
            contentPane.remove(panelRodape);
        }
        gerarRodapeVazio();
        contentPane.add(panelRodape);
        guiClienteContentPane.add(gerarAbaLivrosNaoInteragivel());
        return guiClienteContentPane;
    }

    private Container gerarContainerDeFuncionario() {
        Container guiContainerDeFuncionario = gerarContainerDeTabela();

        gerarTabelaDeLivros();
        gerarScrollPane(tabela);
        guiContainerDeFuncionario.add(scrollPane);

        guiContainerDeFuncionario.add(gerarAbaLivros());
        guiContainerDeFuncionario.add(gerarAbaEmprestimos());

        return guiContainerDeFuncionario;
    }

    private Container gerarContainerDeAdmin(){
        Container guiContainerDeAdmin = gerarContainerDeTabela();

        gerarTabelaDeLivros();
        gerarScrollPane(tabela);
        guiContainerDeAdmin.add(scrollPane);

        guiContainerDeAdmin.add(gerarAbaLivros());
        guiContainerDeAdmin.add(gerarAbaEmprestimos());
        guiContainerDeAdmin.add(gerarAbaUsuarios());

        return guiContainerDeAdmin;
    }

    //Tabelas
    private void gerarTabelaDeLivros() {
        tabela = new JTable();

        limparListenersBotaoBusca();

        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(textFieldProcurar.getText().isBlank()) {
                    listaLivros = livroDao.ordenarLivros(atributosLivro[cbAtributos.getSelectedIndex()]);
                }else {

                    listaLivros = livroDao.filtrarLivros(atributosLivro[cbAtributos.getSelectedIndex()], textFieldProcurar.getText().intern());
                }
                System.out.println(textFieldProcurar.getText());
                livrosArray = Utilities.parseLivroListParaStringArray(listaLivros);

                contentPane.remove(scrollPane);
                tabela.setModel(new DefaultTableModel(livrosArray, atributosLivro));
                gerarScrollPane(tabela);
                contentPane.add(scrollPane);
                contentPane.revalidate();
                contentPane.repaint();
            }
        });

        if(cbAtributos != null) {
            contentPane.remove(cbAtributos);
        }
        cbAtributos = gerarSeletorDeAtributos(atributosLivro);
        contentPane.add(cbAtributos);

        if(panelRodape != null) {
            contentPane.remove(panelRodape);
        }
        gerarRodapeDeLivro();
        contentPane.add(panelRodape);

        listaLivros = livroDao.retornarLivros();
        livrosArray = Utilities.parseLivroListParaStringArray(listaLivros);

        tabela.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        tabela.setModel(new DefaultTableModel(livrosArray, atributosLivro));
	    /*
	    tabela.getColumnModel().getColumn(0).setPreferredWidth(400);
	    tabela.getColumnModel().getColumn(1).setPreferredWidth(400);
	    tabela.getColumnModel().getColumn(2).setPreferredWidth(200);
	    tabela.getColumnModel().getColumn(3).setPreferredWidth(45);
	    tabela.getColumnModel().getColumn(4).setPreferredWidth(66);
	    */
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.setFont(globalSmallFont);
        tabela.setBounds(10, 126, 864, 424);
        tabela.setRowHeight(30);
    }

    private void gerarTabelaDeEmprestimos() {
        tabela = new JTable();

        limparListenersBotaoBusca();

        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(textFieldProcurar.getText().isBlank()) {
                    listaEmprestimos = emprestimoDao.ordenarEmprestimos(atributosUsuario[cbAtributos.getSelectedIndex()]);
                }else {
                    listaEmprestimos = emprestimoDao.filtrarEmprestimos(atributosEmprestimo[cbAtributos.getSelectedIndex()], textFieldProcurar.getText());
                }

                emprestimosArray = Utilities.parseEmprestimoListParaStringArray(listaEmprestimos);

                contentPane.remove(scrollPane);
                tabela.setModel(new DefaultTableModel(emprestimosArray, atributosEmprestimo));
                gerarScrollPane(tabela);
                contentPane.add(scrollPane);
                contentPane.revalidate();
                contentPane.repaint();
            }
        });

        if(cbAtributos != null) {
            contentPane.remove(cbAtributos);
        }
        cbAtributos = gerarSeletorDeAtributos(atributosEmprestimo);
        contentPane.add(cbAtributos);

        if(panelRodape != null) {
            contentPane.remove(panelRodape);
        }
        gerarRodapeDeEmprestimo();
        contentPane.add(panelRodape);

        listaEmprestimos = emprestimoDao.retornarEmprestimos();
        emprestimosArray = Utilities.parseEmprestimoListParaStringArray(listaEmprestimos) ;

        tabela.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        tabela.setModel(new DefaultTableModel(emprestimosArray, atributosEmprestimo));
	    /*
	    tabela.getColumnModel().getColumn(0).setPreferredWidth(500);
	    tabela.getColumnModel().getColumn(1).setPreferredWidth(500);
	    tabela.getColumnModel().getColumn(2).setPreferredWidth(150);
	    tabela.getColumnModel().getColumn(3).setPreferredWidth(150);
	    */
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.setFont(globalSmallFont);
        tabela.setBounds(10, 126, 864, 424);
        tabela.setRowHeight(30);
    }

    private void gerarTabelaDeUsuarios(){
        tabela = new JTable();

        limparListenersBotaoBusca();

        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(textFieldProcurar.getText().isBlank()) {
                    listaUsuarios = usuarioDao.ordenarUsuarios(atributosUsuario[cbAtributos.getSelectedIndex()]);
                }else {
                    listaUsuarios = usuarioDao.filtrarUsuarios(atributosUsuario[cbAtributos.getSelectedIndex()], textFieldProcurar.getText());
                }

                usuariosArray = Utilities.parseUsuarioListParaStringArray(listaUsuarios);

                contentPane.remove(scrollPane);
                tabela.setModel(new DefaultTableModel(usuariosArray, atributosUsuario));
                gerarScrollPane(tabela);
                contentPane.add(scrollPane);
                contentPane.revalidate();
                contentPane.repaint();
            }
        });

        if(cbAtributos != null) {
            contentPane.remove(cbAtributos);
        }
        cbAtributos = gerarSeletorDeAtributos(atributosUsuario);
        contentPane.add(cbAtributos);

        if(panelRodape != null) {
            contentPane.remove(panelRodape);
        }
        gerarRodapeDeUsuario();
        contentPane.add(panelRodape);

        listaUsuarios = usuarioDao.retornarUsuarios();
        usuariosArray = Utilities.parseUsuarioListParaStringArray(listaUsuarios) ;

        tabela.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        tabela.setModel(new DefaultTableModel(usuariosArray, atributosUsuario)); //{"Nome", "CPF", "Endereço", "Email", "Tipo"};
	    /*
	    tabela.getColumnModel().getColumn(0).setPreferredWidth(300);
	    tabela.getColumnModel().getColumn(1).setPreferredWidth(300);
	    tabela.getColumnModel().getColumn(2).setPreferredWidth(300);
	    tabela.getColumnModel().getColumn(3).setPreferredWidth(300);
	    tabela.getColumnModel().getColumn(4).setPreferredWidth(100);
	    */
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.setFont(globalSmallFont);
        tabela.setBounds(10, 126, 864, 424);
        tabela.setRowHeight(30);
    }

    private Container gerarContainerDeTabela() {
        Container tabelaContentPane = gerarLayoutPrincipal();

        JButton btnLogOut = new JButton("Log Out");
        JSeparator separator = new JSeparator();
        textFieldProcurar = new JTextField();
        btnBuscar = new JButton("Buscar");

        btnLogOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                contentPane.removeAll();
                contentPane = gerarContainerDeLogin();
                contentPane.revalidate();
                contentPane.repaint();
            }
        });
        btnLogOut.setFont(globalFont);
        btnLogOut.setBackground(focusLightBlue);
        btnLogOut.setBorder(null);
        btnLogOut.setBounds(larguraDaJanela - 120, 11, 89, 23);
        header.add(btnLogOut);

        separator.setBounds(0, 85, larguraDaJanela - grossuraDaBordaDaJanela, 10);
        tabelaContentPane.add(separator);

        //tabelaContentPane.add(gerarSeletorDeAtributos(atributos));

        textFieldProcurar.setFont(globalFont);
        textFieldProcurar.setBounds(10, 95, larguraDaJanela - btnBuscarGrossura - grossuraDaBordaDaJanela - (4*distanciaElementos) - cbStributoLivroGrossura, 20);
        tabelaContentPane.add(textFieldProcurar);
        textFieldProcurar.setColumns(10);

        btnBuscar.setBackground(focusLightBlue);
        btnBuscar.setBounds(larguraDaJanela - btnBuscarGrossura - grossuraDaBordaDaJanela - 10, 94, btnBuscarGrossura, 23);
        btnBuscar.setFont(globalFont);
        tabelaContentPane.add(btnBuscar);

        return tabelaContentPane;
    }

    //Alterar componentes globais
    private void gerarScrollPane(JTable table){
        scrollPane = new JScrollPane(table);

        scrollPane.setFont(globalFont);
        scrollPane.setBounds(10, 126, larguraDaJanela - (2 * distanciaElementos) - grossuraDaBordaDaJanela, alturaDaJanela - grossuraDaBordaDaJanela - 126 - 33 - 50);

    }

    private JComboBox gerarSeletorDeAtributos(String[] atributos) {
        cbAtributos = new JComboBox();

        cbAtributos.setModel(new DefaultComboBoxModel(atributos));
        cbAtributos.setBackground(focusLightBlue);
        cbAtributos.setBorder(null);
        cbAtributos.setBounds(larguraDaJanela - btnBuscarGrossura - grossuraDaBordaDaJanela - (2*distanciaElementos) - cbStributoLivroGrossura, 95, cbStributoLivroGrossura, 20);
        cbAtributos.setFont(globalFont);

        return cbAtributos;
    }

    private void gerarRodapeVazio(){
        panelRodape = new JPanel();
        panelRodape.setBackground(Color.WHITE);
        panelRodape.setBounds(10, 701, larguraDaJanela - grossuraDaBordaDaJanela - 2 * distanciaElementos, 48);
        panelRodape.setLayout(null);
    }

    private void gerarRodapeDeLivro() {
        panelRodape = new JPanel();
        JButton btnReservar = new JButton("Reservar Selecionado");
        JButton btnNovoLivro = new JButton("Novo Livro");
        JButton btnDesativar = new JButton("Desativar Selecionado");
        btnDesativar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(tabela.getSelectedRow() != -1) {
                    if(JOptionPane.showConfirmDialog(copyMainFrame, "Tem certeza que deseja desativar este livro? \n                (Esta ação é irreversivel)") == 0) {
                        livroDao.desativarLivro(listaLivros.get(tabela.getSelectedRow()));
                        atualisarTabelaLivros();
                    }
                }
            }
        });
        btnReservar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(tabela.getSelectedRow());
                if(tabela.getSelectedRow() != -1) {
                    String cpf = JOptionPane.showInputDialog(copyMainFrame, "Insira o CPF do usuario que deseja reservar este Livro");
                    if(cpf != null) {
                        if(usuariosArray == null) {
                            listaUsuarios = usuarioDao.retornarUsuarios();
                        }
                        for(Usuario usuario : listaUsuarios) {
                            if(usuario.getCpf().intern() == cpf.intern()) {
                                System.out.println(listaLivros.get(tabela.getSelectedRow()).getTitulo());
                                System.out.println(usuario.getNome());
                                emprestimoDao.inserirEmprestimo(new Emprestimo(listaLivros.get(tabela.getSelectedRow()), usuario));
                                break;
                            }
                        }
                    }

                }
            }
        });
        btnNovoLivro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextField titulo = new JTextField();
                JTextField autor = new JTextField();
                JTextField editora = new JTextField();
                JTextField ano = new JTextField();
                JTextField exemplarios = new JTextField();
                Object[] message = {
                        "Titulo", titulo,
                        "Autor", autor,
                        "Editora", editora,
                        "Ano", ano,
                        "Exemplarios", exemplarios
                };

                int option = JOptionPane.showConfirmDialog(null, message, "Registrar Livro", JOptionPane.OK_CANCEL_OPTION);
                if(titulo.getText().intern() != "" && autor.getText().intern() != "" && editora.getText().intern() != "" && ano.getText().intern() != "" && exemplarios.getText().intern() != "") {

                    System.out.println("error");
                    if (option == JOptionPane.OK_OPTION) {
                        livroDao.inserirLivro(new Livro(
                                titulo.getText(),
                                autor.getText(),
                                editora.getText(),
                                Integer.parseInt(ano.getText()),
                                Integer.parseInt(exemplarios.getText())));

                        System.out.println(Integer.parseInt(exemplarios.getText()));

                        atualisarTabelaLivros();
                    }
                }
            }
        });
        panelRodape.setBackground(Color.WHITE);
        panelRodape.setBounds(distanciaElementos, 701, larguraDaJanela - grossuraDaBordaDaJanela - 2 * distanciaElementos, 48);
        panelRodape.setLayout(null);

        btnReservar.setBounds(distanciaElementos, 11, 200, 23);
        btnReservar.setFont(globalFont);
        panelRodape.add(btnReservar);

        btnNovoLivro.setBounds(larguraDaJanela - grossuraDaBordaDaJanela - (3*distanciaElementos) - 200, 11, 200, 23);
        btnNovoLivro.setFont(globalFont);
        panelRodape.add(btnNovoLivro);

        btnDesativar.setBounds(((larguraDaJanela - 200)/2) - grossuraDaBordaDaJanela, 11, 200, 23);
        btnDesativar.setFont(globalFont);
        panelRodape.add(btnDesativar);
    }

    private void gerarRodapeDeEmprestimo() {
        panelRodape = new JPanel();
        JButton btnDevolver = new JButton("Devolver Selecionado");

        btnDevolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(tabela.getSelectedRow() != -1) {
                    emprestimoDao.devolver(listaEmprestimos.get(tabela.getSelectedRow()));

                    atualisarTabelaEmprestimos();
                }
            }
        });

        panelRodape.setBackground(Color.WHITE);
        panelRodape.setBounds(distanciaElementos, 701, larguraDaJanela - grossuraDaBordaDaJanela - 2 * distanciaElementos, 48);
        panelRodape.setLayout(null);

        btnDevolver.setBounds(distanciaElementos, 11, 200, 23);
        btnDevolver.setFont(globalFont);
        panelRodape.add(btnDevolver);
    }

    private void gerarRodapeDeUsuario(){
        panelRodape = new JPanel();
        JButton btnEditar = new JButton("Editar Selecionado");
        JButton btnDesativar = new JButton("Desativar Selecionado");

        btnDesativar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(tabela.getSelectedRow() != -1) {
                    if(JOptionPane.showConfirmDialog(copyMainFrame, "Tem certeza que deseja desativar este usuário? \n                (Esta ação é irreversivel)") == 0) {
                        usuarioDao.desativarUsuario(listaUsuarios.get(tabela.getSelectedRow()));

                        atualisarTabelaUsuarios();
                    }
                }
            }
        });
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(tabela.getSelectedRow() != -1) {
                    JTextField nome = new JTextField();
                    JTextField cpf = new JTextField();
                    JTextField endereco = new JTextField();
                    JTextField email = new JTextField();
                    JPasswordField senha = new JPasswordField();

                    Object[] message = {
                            "Nome", nome,
                            "Cpf", cpf,
                            "Endereco", endereco,
                            "Email", email,
                            "Senha", senha
                    };

                    int option = JOptionPane.showConfirmDialog(null, message, "Registrar Livro", JOptionPane.OK_CANCEL_OPTION);
                    if (option == JOptionPane.OK_OPTION) {
                        Usuario usuario = listaUsuarios.get(tabela.getSelectedRow());
                        String senhaString = new String(senha.getPassword());
                        usuarioDao.modificarUsuario(usuario, new Usuario(
                                nome.getText(),
                                cpf.getText(),
                                endereco.getText(),
                                email.getText(),
                                senhaString,
                                usuario.getTipoUsuario()
                        ));
                        atualisarTabelaUsuarios();
                    }
                }
            }
        });
        panelRodape.setBackground(Color.WHITE);
        panelRodape.setBounds(distanciaElementos, 701, larguraDaJanela - grossuraDaBordaDaJanela - 2 * distanciaElementos, 48);
        panelRodape.setLayout(null);

        btnEditar.setFont(globalFont);
        btnEditar.setBounds(distanciaElementos, 11, 200, 23);
        panelRodape.add(btnEditar);

        btnDesativar.setFont(globalFont);
        btnDesativar.setBounds(larguraDaJanela - grossuraDaBordaDaJanela - (3*distanciaElementos) - 200, 11, 200, 23);
        panelRodape.add(btnDesativar);
    }

    //Update Tables
    private void atualisarTabelaLivros(){
        listaLivros = livroDao.retornarLivros();
        livrosArray = Utilities.parseLivroListParaStringArray(listaLivros);
        contentPane.remove(scrollPane);
        tabela.setModel(new DefaultTableModel(livrosArray, atributosLivro));
        gerarScrollPane(tabela);
        contentPane.add(scrollPane);
        contentPane.revalidate();
        contentPane.repaint();
    }

    private void atualisarTabelaUsuarios() {
        listaUsuarios = usuarioDao.retornarUsuarios();
        usuariosArray = Utilities.parseUsuarioListParaStringArray(listaUsuarios);
        contentPane.remove(scrollPane);
        tabela.setModel(new DefaultTableModel(usuariosArray, atributosUsuario));
        gerarScrollPane(tabela);
        contentPane.add(scrollPane);
        contentPane.revalidate();
        contentPane.repaint();
    }

    private void atualisarTabelaEmprestimos() {
        listaEmprestimos = emprestimoDao.retornarEmprestimos();
        emprestimosArray = Utilities.parseEmprestimoListParaStringArray(listaEmprestimos);
        contentPane.remove(scrollPane);
        tabela.setModel(new DefaultTableModel(emprestimosArray, atributosEmprestimo));
        gerarScrollPane(tabela);
        contentPane.add(scrollPane);
        contentPane.revalidate();
        contentPane.repaint();
    }

    private void limparListenersBotaoBusca(){
        for(ActionListener listener : btnBuscar.getActionListeners()) {
            btnBuscar.removeActionListener(listener);
        }
    }

	/*
	Deprecate functions

	private void gerarFormBaseComCancel(){
		generalForm = gerarFormBase();
		JButton btnCancel = new JButton("Cancelar");

		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		btnCancel.setBackground(background);
		btnCancel.setFont(globalFont);
		btnCancel.setBounds(10, generalForm.getHeight() - distanciaElementos - 20, 100, 20);
		generalForm.add(btnCancel);
	}

	//Forms
	private void gerarConfirmarDesativarLivro(){
		gerarFormBaseComCancel();
		JPanel form = generalForm;
		JLabel lblPergunta = new JLabel("Tem certeza que deseja deletar este livro?");
		JButton btnDesativar = new JButton("Desativar");
		JPanel panelInfoLivro = new JPanel();
		JLabel lblInforLivro = new JLabel();

		btnDesativar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				livroDao.desativarLivro(listaLivros.get(tabela.getSelectedRow()));
				//DEsativarLivro
			}
		});

		lblPergunta.setBounds(0, distanciaElementos * 10, generalForm.getWidth(), 20);
		lblPergunta.setHorizontalAlignment(SwingConstants.CENTER);
		lblPergunta.setFont(globalFont);

		btnDesativar.setBounds(generalForm.getWidth() - 100 - distanciaElementos, generalForm.getHeight() - distanciaElementos - 20, 100, 20);
		btnDesativar.setBackground(background);
		btnDesativar.setFont(globalFont);

		panelInfoLivro.setBackground(background);
		panelInfoLivro.setFont(globalFont);
		panelInfoLivro.setBounds(3 * distanciaElementos, distanciaElementos * 15, generalForm.getWidth() - 6 * distanciaElementos, 100);

		lblInforLivro.setText(livrosArray[tabela.getSelectedRow()][0]);
		//lblInforLivro.setText(livrosArray[1][0]);//placeholder
		lblInforLivro.setFont(globalFont);
		lblInforLivro.setBounds(4 * distanciaElementos, distanciaElementos * 15 + 40, generalForm.getWidth() - 8 * distanciaElementos, 20);
		lblInforLivro.setHorizontalAlignment(SwingConstants.CENTER);

		generalForm.add(lblInforLivro);
		generalForm.add(panelInfoLivro);
		generalForm.add(btnDesativar);
		generalForm.add(lblPergunta);
	}
	*/

    private JPanel gerarFormBase() {
        int formWidth = 500;
        int formHeight = 400;
        JPanel form = new JPanel();

        form.setBackground(formLightGray);
        form.setBounds((larguraDaJanela - formWidth)/2, (alturaDaJanela - formHeight)/2, formWidth, formHeight); //900*600 (900 - 500) /2 = 200
        form.setLayout(null);

        return form;
    }

    private Container gerarContainerDeLogin() {

        Container loginContentPane = gerarLayoutPrincipal();
        JPanel loginForm = gerarFormBase();;

        JLabel lblLogIn  = new JLabel("     Log In");;
        JLabel lblEmail = new JLabel("Email");
        JLabel lblSenha = new JLabel("Senha");
        JTextField emailTextField = new JTextField();
        JPasswordField senhaTextField = new JPasswordField();
        JPanel panelSenha = new JPanel();
        JPanel panelEmail = new JPanel();
        JButton btnEntrar = new JButton("Entrar");
        JButton btnCriarConta = new JButton("Criar Conta");

        lblLogIn.setFont(globalFont);
        lblLogIn.setBounds(10, 11, 480, 14);
        loginForm.add(lblLogIn);

        panelEmail.setBackground(formLightGray);
        panelEmail.setBounds(10, 92, 480, 50);
        loginForm.add(panelEmail);
        panelEmail.setLayout(null);

        emailTextField.setBounds(69, 15, 411, 20);
        panelEmail.add(emailTextField);
        emailTextField.setColumns(10);

        lblEmail.setBounds(10, 20, 56, 14);
        panelEmail.add(lblEmail);
        lblEmail.setFont(globalFont);

        panelSenha.setBackground(formLightGray);
        panelSenha.setBounds(10, 145, 480, 50);
        loginForm.add(panelSenha);
        panelSenha.setLayout(null);

        lblSenha.setBounds(10, 18, 49, 18);
        panelSenha.add(lblSenha);
        lblSenha.setFont(globalFont);

        senhaTextField.setBounds(69, 15, 411, 20);
        panelSenha.add(senhaTextField);
        senhaTextField.setColumns(10);

        btnEntrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String senha = new String(senhaTextField.getPassword());
                boolean sessaoValida = usuarioDao.validarSecao(emailTextField.getText(), senha.intern());
                TipoUsuario tipoUsuario =  usuarioDao.retornarTipoUsuario(emailTextField.getText(), senha.intern());

                if(sessaoValida) {
                    gerarGui(tipoUsuario);
                }
            }
        });

        btnEntrar.setBackground(formLightGray);
        btnEntrar.setVerticalAlignment(SwingConstants.TOP);
        btnEntrar.setFont(globalFont);
        btnEntrar.setBounds(401, 366, 89, 23);
        loginForm.add(btnEntrar);

        btnCriarConta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                contentPane.removeAll();
                contentPane = gerarContainerDeSignIn();
                contentPane.revalidate();
                contentPane.repaint();
                funcionarioSelecionado = false;
                adminSelecionado = false;
            }
        });
        btnCriarConta.setBackground(formLightGray);
        btnCriarConta.setVerticalAlignment(SwingConstants.TOP);
        btnCriarConta.setFont(globalFont);
        btnCriarConta.setBounds(10, 366, 114, 23);
        loginForm.add(btnCriarConta);

        loginContentPane.add(loginForm);

        return loginContentPane;
    }

    private Container gerarContainerDeSignIn() {
        Container signInContentPane = gerarLayoutPrincipal();
        JPanel signInForm = gerarFormBase();

        JPanel panelNome;
        JLabel lblNome;
        JTextField textFieldNome;
        JPanel panelCpf;
        JLabel lblCpf;
        JTextField textFieldCpf;
        JPanel panelEndereco;
        JLabel lblEndereco;
        JTextField textFieldEndereco;
        JPanel panelEmail;
        JLabel lblEmail;
        JTextField textFieldEmail;
        JPanel panelSenha;
        JLabel lblSenha;
        JPasswordField passwordFieldSenha;
        JPasswordField passwordFieldId;
        JPanel panelDecidirID = new JPanel();
        JRadioButton rdbtnFuncionario = new JRadioButton("Funcionario");
        JRadioButton rdbtnAdmin = new JRadioButton("Admin");
        JLabel lblTipoUsuario = new JLabel("Tipo de Usuário");
        JPanel panelId = new JPanel();
        JLabel lblIdType = new JLabel("ID Funcionario");

        JLabel lblCriarConta;
        ButtonGroup rdButtonGroup = new ButtonGroup();

        lblCriarConta = new JLabel("     Criar Conta");
        lblCriarConta.setFont(globalFont);
        lblCriarConta.setBounds(10, 11, 480, 14);
        signInForm.add(lblCriarConta);

        {
            panelNome = new JPanel();
            panelNome.setBackground(formLightGray);
            panelNome.setBounds(10, 50, 480, 45);
            signInForm.add(panelNome);
            panelNome.setLayout(null);
            {
                lblNome = new JLabel("Nome");
                lblNome.setFont(globalFont);
                lblNome.setBounds(0, 15, 60, 14);
                panelNome.add(lblNome);
            }
            {
                textFieldNome = new JTextField();
                textFieldNome.setBounds(70, 12, 410, 20);
                panelNome.add(textFieldNome);
                textFieldNome.setColumns(10);
            }
        }
        {
            panelCpf = new JPanel();
            panelCpf.setLayout(null);
            panelCpf.setBackground(formLightGray);
            panelCpf.setBounds(10, 95, 480, 45);
            signInForm.add(panelCpf);
            {
                lblCpf = new JLabel("CPF");
                lblCpf.setFont(globalFont);
                lblCpf.setBounds(0, 15, 60, 14);
                panelCpf.add(lblCpf);
            }
            {
                textFieldCpf = new JTextField();
                textFieldCpf.setColumns(10);
                textFieldCpf.setBounds(70, 12, 410, 20);
                panelCpf.add(textFieldCpf);
            }
        }
        {
            panelEndereco = new JPanel();
            panelEndereco.setLayout(null);
            panelEndereco.setBackground(formLightGray);
            panelEndereco.setBounds(10, 140, 480, 45);
            signInForm.add(panelEndereco);
            {
                lblEndereco = new JLabel("Endereço");
                lblEndereco.setFont(globalFont);
                lblEndereco.setBounds(0, 15, 68, 14);
                panelEndereco.add(lblEndereco);
            }
            {
                textFieldEndereco = new JTextField();
                textFieldEndereco.setColumns(10);
                textFieldEndereco.setBounds(70, 12, 410, 20);
                panelEndereco.add(textFieldEndereco);
            }
        }
        {
            panelEmail = new JPanel();
            panelEmail.setLayout(null);
            panelEmail.setBackground(formLightGray);
            panelEmail.setBounds(10, 185, 480, 45);
            signInForm.add(panelEmail);
            {
                lblEmail = new JLabel("E-mail");
                lblEmail.setFont(globalFont);
                lblEmail.setBounds(0, 15, 60, 14);
                panelEmail.add(lblEmail);
            }
            {
                textFieldEmail = new JTextField();
                textFieldEmail.setColumns(10);
                textFieldEmail.setBounds(70, 12, 410, 20);
                panelEmail.add(textFieldEmail);
            }
        }
        {
            panelSenha = new JPanel();
            panelSenha.setLayout(null);
            panelSenha.setBackground(formLightGray);
            panelSenha.setBounds(10, 230, 480, 45);
            signInForm.add(panelSenha);
            {
                lblSenha = new JLabel("Senha");
                lblSenha.setFont(globalFont);
                lblSenha.setBounds(0, 15, 61, 14);
                panelSenha.add(lblSenha);
            }

            passwordFieldSenha = new JPasswordField();
            passwordFieldSenha.setBounds(70, 12, 410, 20);
            panelSenha.add(passwordFieldSenha);
        }

        panelDecidirID.setLayout(null);
        panelDecidirID.setBackground(formLightGray);
        panelDecidirID.setBounds(10, 275, 480, 45);
        signInForm.add(panelDecidirID);

        rdbtnFuncionario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(funcionarioSelecionado) {
                    panelId.setVisible(false);
                    rdButtonGroup.clearSelection();
                    funcionarioSelecionado = false;
                    adminSelecionado = false;
                } else {
                    lblIdType.setText("ID Funcionario");
                    panelId.setVisible(true);
                    adminSelecionado = false;
                    funcionarioSelecionado = true;
                }
            }
        });
        rdbtnFuncionario.setBackground(formLightGray);
        rdbtnFuncionario.setVerticalAlignment(SwingConstants.TOP);
        rdbtnFuncionario.setFont(new Font("Arial", Font.PLAIN, 14));
        rdbtnFuncionario.setBounds(109, 11, 150, 23);
        panelDecidirID.add(rdbtnFuncionario);

        rdbtnAdmin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(adminSelecionado) {
                    panelId.setVisible(false);
                    rdButtonGroup.clearSelection();
                    funcionarioSelecionado = false;
                    adminSelecionado = false;
                } else {
                    lblIdType.setText("ID Admin");
                    panelId.setVisible(true);
                    funcionarioSelecionado = false;
                    adminSelecionado = true;
                }
            }
        });
        rdbtnAdmin.setBackground(formLightGray);
        rdbtnAdmin.setFont(new Font("Arial", Font.PLAIN, 14));
        rdbtnAdmin.setBounds(261, 12, 150, 23);
        panelDecidirID.add(rdbtnAdmin);

        rdButtonGroup.add(rdbtnAdmin);
        rdButtonGroup.add(rdbtnFuncionario);

        lblTipoUsuario.setFont(globalFont);
        lblTipoUsuario.setBounds(0, 19, 103, 14);
        panelDecidirID.add(lblTipoUsuario);

        panelId.setLayout(null);
        panelId.setBackground(formLightGray);
        panelId.setBounds(10, 320, 480, 45);
        panelId.setVisible(false);
        signInForm.add(panelId);

        lblIdType.setFont(globalFont);
        lblIdType.setBounds(0, 15, 96, 14);
        panelId.add(lblIdType);

        {
            passwordFieldId = new JPasswordField();
            passwordFieldId.setBounds(106, 12, 374, 20);
            panelId.add(passwordFieldId);
            passwordFieldId.setColumns(10);
        }

        {
            JButton btnLogIn = new JButton("Log In");
            btnLogIn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    contentPane.removeAll();
                    contentPane = gerarContainerDeLogin();
                    contentPane.revalidate();
                    contentPane.repaint();
                    funcionarioSelecionado = false;
                    adminSelecionado = false;
                }
            });
            btnLogIn.setVerticalAlignment(SwingConstants.TOP);
            btnLogIn.setFont(globalFont);
            btnLogIn.setBounds(10, 366, 89, 23);
            btnLogIn.setBackground(formLightGray);
            signInForm.add(btnLogIn);
        }
        {
            JButton btnEnviar = new JButton("Enviar");
            btnEnviar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String senha = new String(passwordFieldId.getPassword());

                    Usuario usuario = new Usuario(
                            textFieldNome.getText(),
                            textFieldCpf.getText(),
                            textFieldEndereco.getText(),
                            textFieldEmail.getText(),
                            senha.intern(),
                            TipoUsuario.USUARIO);
                    if(funcionarioSelecionado && senha.intern() == senhaFuncionario.intern()) {
                        gerarGui(TipoUsuario.FUNCIONARIO);
                        usuario.setTipoUsuario(TipoUsuario.FUNCIONARIO);
                    } else if(adminSelecionado && senha.intern() == senhaAdmin.intern()) {
                        gerarGui(TipoUsuario.ADMIN);
                        usuario.setTipoUsuario(TipoUsuario.ADMIN);
                    }else {
                        gerarGui(TipoUsuario.CLIENTE);
                        usuario.setTipoUsuario(TipoUsuario.CLIENTE);
                    }
                    usuarioDao.inserirUsuario(usuario);
                }
            });
            btnEnviar.setVerticalAlignment(SwingConstants.TOP);
            btnEnviar.setFont(globalFont);
            btnEnviar.setBounds(401, 366, 89, 23);
            btnEnviar.setBackground(formLightGray);
            signInForm.add(btnEnviar);
        }

        signInContentPane.add(signInForm);
        return signInContentPane;
    }
}