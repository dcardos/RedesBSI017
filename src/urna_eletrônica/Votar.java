/*
 *  Criado pelos alunos:
 * 
 *  Murilo Pratavieira                      No USP 8123082
 *  Danilo Guarnieri Cardoso                No USP 10442277
 *  Gustavo Cesar Leite De Oliveira Santos  No USP 
 * 
 */

package urna_eletrônica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Votar extends javax.swing.JDialog {
                          
    private String sqlVotar = new String();
    private int tableSize;
    private String typedString = "";
    
    // cria a váriavel para criar a conexão com o banco de dados
    Connection conn = null;
    
    public Votar(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();       
    }    
    
    private void sair(){
        System.exit(0);
    }
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        topico = new javax.swing.JLabel();
        nomeDisciplina = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        results2 = new javax.swing.JTextArea();
        selecao1 = new javax.swing.JRadioButton();
        selecao2 = new javax.swing.JRadioButton();
        selecao3 = new javax.swing.JRadioButton();
        selecao4 = new javax.swing.JRadioButton();
        selecao5 = new javax.swing.JRadioButton();
        selecao6 = new javax.swing.JRadioButton();
        selecao7 = new javax.swing.JRadioButton();
        selecao8 = new javax.swing.JRadioButton();
        selecao9 = new javax.swing.JRadioButton();
        selecao10 = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        results = new javax.swing.JTextArea();
        topico1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gerenciamento de Transmissão");
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setModal(true);

        topico.setFont(new java.awt.Font("Tahoma", 3, 25)); // NOI18N
        topico.setText("Escolha o que deseja buscar:");

        nomeDisciplina.setFont(new java.awt.Font("Tahoma", 3, 35)); // NOI18N
        nomeDisciplina.setText("Opção - Busca");

        jButton1.setBackground(new java.awt.Color(102, 153, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jButton1.setText("Buscar");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        results2.setColumns(20);
        results2.setFont(new java.awt.Font("Monospaced", 0, 24)); // NOI18N
        results2.setLineWrap(true);
        results2.setRows(5);
        results2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        results2.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane1.setViewportView(results2);

        buttonGroup1.add(selecao1);
        selecao1.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        selecao1.setText("Busca 1");
        selecao1.setSelectedIcon(new javax.swing.ImageIcon("C:\\Users\\Murilo Pratavieira\\Documents\\NetBeansProjects\\TrabalhoBD\\Radio_Button.png")); // NOI18N
        selecao1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selecao1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(selecao2);
        selecao2.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        selecao2.setText("Busca 2");
        selecao2.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/trabalhobd/Radio_Button.png"))); // NOI18N
        selecao2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selecao2ActionPerformed(evt);
            }
        });

        buttonGroup1.add(selecao3);
        selecao3.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        selecao3.setText("Busca 3");
        selecao3.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/trabalhobd/Radio_Button.png"))); // NOI18N
        selecao3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selecao3ActionPerformed(evt);
            }
        });

        buttonGroup1.add(selecao4);
        selecao4.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        selecao4.setText("Busca 4");
        selecao4.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/trabalhobd/Radio_Button.png"))); // NOI18N
        selecao4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selecao4ActionPerformed(evt);
            }
        });

        buttonGroup1.add(selecao5);
        selecao5.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        selecao5.setText("Busca 5");
        selecao5.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/trabalhobd/Radio_Button.png"))); // NOI18N
        selecao5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selecao5ActionPerformed(evt);
            }
        });

        buttonGroup1.add(selecao6);
        selecao6.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        selecao6.setText("Busca 6");
        selecao6.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/trabalhobd/Radio_Button.png"))); // NOI18N
        selecao6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selecao6ActionPerformed(evt);
            }
        });

        buttonGroup1.add(selecao7);
        selecao7.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        selecao7.setText("Busca 7");
        selecao7.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/trabalhobd/Radio_Button.png"))); // NOI18N
        selecao7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selecao7ActionPerformed(evt);
            }
        });

        buttonGroup1.add(selecao8);
        selecao8.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        selecao8.setText("Busca 8");
        selecao8.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/trabalhobd/Radio_Button.png"))); // NOI18N
        selecao8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selecao8ActionPerformed(evt);
            }
        });

        buttonGroup1.add(selecao9);
        selecao9.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        selecao9.setText("Busca 9");
        selecao9.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/trabalhobd/Radio_Button.png"))); // NOI18N
        selecao9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selecao9ActionPerformed(evt);
            }
        });

        buttonGroup1.add(selecao10);
        selecao10.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        selecao10.setText("Busca 10");
        selecao10.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/trabalhobd/Radio_Button.png"))); // NOI18N
        selecao10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selecao10ActionPerformed(evt);
            }
        });

        results.setColumns(20);
        results.setFont(new java.awt.Font("Monospaced", 2, 25)); // NOI18N
        results.setLineWrap(true);
        results.setRows(5);
        results.setToolTipText("");
        results.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        results.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        results.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jScrollPane2.setViewportView(results);

        topico1.setFont(new java.awt.Font("Tahoma", 3, 25)); // NOI18N
        topico1.setText("Digite o Código:");
        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(topico, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(selecao1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(selecao3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(selecao7, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(selecao5, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(selecao9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(selecao10, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                            .addComponent(selecao8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(selecao6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(selecao4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(selecao2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(9, 9, 9)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 697, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(topico1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jButton1)))
                .addGap(39, 39, 39))
            .addGroup(layout.createSequentialGroup()
                .addGap(512, 512, 512)
                .addComponent(nomeDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(nomeDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(topico, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(selecao1)
                            .addComponent(selecao2))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(selecao3)
                            .addComponent(selecao4))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(selecao5)
                            .addComponent(selecao6))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(selecao7)
                            .addComponent(selecao8))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(selecao10)
                            .addComponent(selecao9)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(topico1)
                        .addGap(44, 44, 44)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        setBounds(250, 200, 1370, 550);
    }// </editor-fold>//GEN-END:initComponents

    /* 
    *  Método criado para o botão "Buscar" onde fará a conexão com o banco através do método "Conectar()"       
    */  
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
                
        conectar();                
        
        results.setLineWrap(true);
        results.setWrapStyleWord(true);                
        
        typedString = results2.getText();
        
        // Confere se o campo para pesquisa está vazio, caso esteja retorna error, caso contrário chama o método Busca
        
        if(typedString.equals(""))
            results.setText("\t\tError! \n**** Digite o código que deseja! ****");        
        else               
            Busca();        
                
    }//GEN-LAST:event_jButton1ActionPerformed

    // Esse métodos estão identificando quais são as buscas que cada seleção irá fazer
    private void selecao1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selecao1ActionPerformed
        if(selecao1.isSelected()){                                                 
            results.setText("Quais emissoras tem o direito de transmissão do vídeo do jogo Y");
        }        
    }//GEN-LAST:event_selecao1ActionPerformed

    private void selecao2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selecao2ActionPerformed
        if(selecao2.isSelected()){                                                 
            results.setText("Quais são os nomes dos atletas e das equipes que estão em um Jogo X");
        }      
    }//GEN-LAST:event_selecao2ActionPerformed

    private void selecao3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selecao3ActionPerformed
        if(selecao3.isSelected()){                                                 
            results.setText("Por quais mídias a emissora X tem direito de transmissão dos vídeos?");
        } 
    }//GEN-LAST:event_selecao3ActionPerformed

    private void selecao4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selecao4ActionPerformed
        if(selecao4.isSelected()){                                                 
            results.setText("Quais jornalistas da emissora X têm direito de fazer reportagem?");
        }
    }//GEN-LAST:event_selecao4ActionPerformed

    private void selecao5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selecao5ActionPerformed
        if(selecao5.isSelected()){                                                 
            results.setText("Na data X quais jornalistas possuem reportagens agendadas? (DDMMYYYY)");
        }
    }//GEN-LAST:event_selecao5ActionPerformed

    private void selecao6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selecao6ActionPerformed
        if(selecao6.isSelected()){                                                 
            results.setText("Qual tipo de deficiência do atleta X?");
        }    
    }//GEN-LAST:event_selecao6ActionPerformed

    private void selecao7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selecao7ActionPerformed
        if(selecao7.isSelected()){                                                 
            results.setText("Em quais países e estados a emissora X tem direito de transmissão?");
        }  
    }//GEN-LAST:event_selecao7ActionPerformed

    private void selecao8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selecao8ActionPerformed
        if(selecao8.isSelected()){                                                 
            results.setText("Quais são atletas que participam de uma determinada equipe?");
        }
    }//GEN-LAST:event_selecao8ActionPerformed

    private void selecao9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selecao9ActionPerformed
        if(selecao9.isSelected()){                                                 
            results.setText("Qual operador está responsável por uma determinada câmera?");
        }
    }//GEN-LAST:event_selecao9ActionPerformed

    private void selecao10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selecao10ActionPerformed
        if(selecao10.isSelected()){                                                 
            results.setText("Quais são as câmeras que estão responsáveis pelo vídeo de um jogo X?");
        }
    }//GEN-LAST:event_selecao10ActionPerformed
                    
    
    public static void main(String args[]) {
                
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Votar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Votar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Votar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Votar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }             
        
        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Votar dialog = new Votar(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
                 
    }        
    
    /*       
    *   Método que irá fazer a conexão com o banco de dados
    *   As variaveis indicam qual o nome do servidor, a porta, e todos os outros detalhes
    *   Estamos usando a conta de um dos criados do projeto.
    */
    
    public void conectar() {
              
        try{                        
            String driverName = "oracle.jdbc.driver.OracleDriver";            
            Class.forName(driverName);              
            String serverName = "grad.icmc.usp.br";
            String serverPort = "15215";
            String sid = "orcl";
            String url = "jdbc:oracle:thin:@" + serverName + ":" + serverPort + ":" + sid;
            String username = "l4165850";
            String password = "l4165850";
            conn = DriverManager.getConnection(url, username, password);
                        
        }
        catch (ClassNotFoundException ex) {
            results.setText("Problema: verifique o driver do banco de dados!");
        } catch (SQLException ex) {
            results.setText("Problema: verifique seu usuário e senha!");
        }        
    }
    
    public void Busca(){
                
        if(selecao1.isSelected()){
            tableSize = 2;
             sqlVotar = "SELECT e.codigo_internacional, e.nome " +
                            "FROM EMISSORA e " +
                            "JOIN CONTRATO c ON e.contrato=c.nro_protocolo " +
                            "JOIN CONTRATO_POSSUI_JOGO j ON j.contrato=c.nro_protocolo AND j.jogo=\'" + typedString +"\'";             
        }
        else if(selecao2.isSelected()){
            tableSize = 2;
            sqlVotar = "SELECT e.cod_inscricao, a.nome " +
                           "FROM EQUIPE e " +
                           "JOIN EQUIPE_PARTICIPA_JOGO j ON j.equipe=e.cod_inscricao AND j.jogo=\'" + typedString + "\' " +
                           "JOIN ATLETA a ON a.equipe=e.cod_inscricao " +
                           "ORDER BY e.cod_inscricao";            
        }
        else if(selecao3.isSelected()){
            tableSize = 1;
            sqlVotar = "SELECT m.tipo " +
                       "FROM MIDIA m " +
                       "JOIN CONTRATO c ON c.nro_protocolo=m.contrato " +
                       "JOIN EMISSORA e ON c.nro_protocolo=e.contrato AND UPPER(e.nome)=UPPER(\'" + typedString + "\')";
        }
        else if(selecao4.isSelected()){
            tableSize = 2;
            sqlVotar = "SELECT j.nro_registro, j.nome " +
                            "FROM REPORTAGEM r " +
                            "JOIN JORNALISTA j ON r.jornalista=j.nro_registro " +
                            "JOIN EMISSORA e ON e.codigo_internacional=j.emissora AND UPPER(e.nome)=UPPER(\'" + typedString +"\')";
        }           
        else if(selecao5.isSelected()){
            tableSize = 4;
            sqlVotar = "SELECT j.nome, r.jornalista, TO_CHAR(r.inicio,'DD/MM/YYYY HH24:MI:SS'), TO_CHAR(r.fim,'DD/MM/YYYY HH24:MI:SS') " +
                           "FROM REPORTAGEM r " +
                           "JOIN JORNALISTA j ON r.jornalista=j.nro_registro AND TO_CHAR(r.inicio,'DDMMYYYY')=\'" + typedString + "\'";
        }           
        else if(selecao6.isSelected()){
            tableSize = 1;
            sqlVotar = "SELECT a.deficiencia " +
                           "FROM ATLETA a " +
                           "WHERE a.nro_inscricao=\'" + typedString + "\'";
        }           
        else if(selecao7.isSelected()){
            tableSize = 2;
            sqlVotar = "SELECT t.pais, a.estado" +
                        " FROM EMISSORA e" +
                        " JOIN MIDIA m ON e.contrato=m.contrato AND UPPER(e.nome)=UPPER(\'" + typedString + "\')" +
                        " JOIN TRANSMISSAO t ON t.midia=m.id" +
                        " LEFT JOIN ESTADO_PAIS a ON a.pais=t.pais" +
                        " ORDER BY t.pais, a.estado";
        }           
        else if(selecao8.isSelected()){
            tableSize = 2;
            sqlVotar = "SELECT a.nome, a.nro_inscricao " +
                            "FROM ATLETA a " +
                            "WHERE a.equipe=\'" + typedString + "\'";
        }          
        else if(selecao9.isSelected()){
            tableSize = 2;
            sqlVotar = "SELECT o.nro_funcionario, o.nome " +
                            "FROM OPERADOR o " +
                            "WHERE o.camera=\'" + typedString + "\'";
        }           
        
        else if(selecao10.isSelected()){
            tableSize = 1;
            sqlVotar = "SELECT v.camera " +
                            "FROM VIDEO v " +
                            "WHERE v.jogo=\'" + typedString + "\'";
        }           
        
        infoBancoDeDados();
    }
    /*
    *
    *   Método onde irá ser adquirido os dados do banco, ou seja, a string resultante com todas as linhas e colunas 
    *   serão salvas em "stringResult", e a String aux será usada para salvar os dados de cada linha.
    *   Dependendo o número de itens que será buscado, o tableSize será diferente. Este variando de 1 à 4 valores considerando os nossos exemplos
    *   
    */
    public void infoBancoDeDados() {
        
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sqlVotar);
                        
            String aux = new String();
            
            String stringResult= new String();
            stringResult = "";
            
            
            if(tableSize == 1){
                while (rs.next()){

                        aux = rs.getString(1) + "\n";      

                        if(stringResult.equals(null))
                            stringResult = aux;
                        else
                            stringResult = stringResult + aux;
                }                
            }
            else if (tableSize == 2){
                while (rs.next()){

                        aux = rs.getString(1) + "\t" + rs.getString(2) +  "\n";

                        if(stringResult.equals(null))
                            stringResult = aux;
                        else
                            stringResult = stringResult + aux;
                }
            }
            else if (tableSize == 3){
                while (rs.next()){
                        
                        aux = rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\n";      
                        
                        if(stringResult.equals(null))
                            stringResult = aux;
                        else
                            stringResult = stringResult + aux;
                }
            }
            else if (tableSize == 4){
                while (rs.next()){
                        aux = rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4) +  "\n";      

                        if(stringResult.equals(null))
                            stringResult =  aux;
                        else
                            stringResult = stringResult + aux;
                }
            }
            
            if(stringResult.equals("")){
                results.setText("\tNenhuma valor encontrado."
                        + "\n\tTente novamente!!");
            }
            else
                results.setText(stringResult);
            
            rs.close();
        } catch(Exception e){
            results.setText("Erro na consulta:\n \'" + sqlVotar + "\'");
        }            
    }
                                       
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel nomeDisciplina;
    private javax.swing.JTextArea results;
    private javax.swing.JTextArea results2;
    private javax.swing.JRadioButton selecao1;
    private javax.swing.JRadioButton selecao10;
    private javax.swing.JRadioButton selecao2;
    private javax.swing.JRadioButton selecao3;
    private javax.swing.JRadioButton selecao4;
    private javax.swing.JRadioButton selecao5;
    private javax.swing.JRadioButton selecao6;
    private javax.swing.JRadioButton selecao7;
    private javax.swing.JRadioButton selecao8;
    private javax.swing.JRadioButton selecao9;
    private javax.swing.JLabel topico;
    private javax.swing.JLabel topico1;
    // End of variables declaration//GEN-END:variables
}
