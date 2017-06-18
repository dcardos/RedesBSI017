/*
 *  Criado pelos alunos:
 * 
 *  Murilo Pratavieira                      No USP 8123082
 *  Danilo Guarnieri Cardoso                No USP 10442277
 *  Gustavo Cesar Leite De Oliveira Santos  No USP 
 * 
 */

package urna_eletrônica;

public class Sobre extends javax.swing.JDialog {
    public Sobre(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    private void sair(){
        System.exit(0);
    }
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topico = new javax.swing.JLabel();
        nomeProf = new javax.swing.JLabel();
        nomeDisciplina = new javax.swing.JLabel();
        nomeDoAluno2 = new javax.swing.JLabel();
        nomeDoAluno5 = new javax.swing.JLabel();
        nomeDoAluno6 = new javax.swing.JLabel();
        nomeDoAluno8 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gerenciamento de Transmissão");
        setBackground(new java.awt.Color(255, 255, 255));

        topico.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        topico.setText("Criado pelos alunos:");

        nomeProf.setBackground(new java.awt.Color(102, 102, 255));
        nomeProf.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        nomeProf.setText("Prof. Julio Cezar Estrella");

        nomeDisciplina.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        nomeDisciplina.setText("Projeto - Redes de Computadores");

        nomeDoAluno2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        nomeDoAluno2.setText("Universidade de São Paulo");

        nomeDoAluno5.setFont(new java.awt.Font("Tahoma", 2, 20)); // NOI18N
        nomeDoAluno5.setText("Danilo Guarnieri Cardoso");

        nomeDoAluno6.setFont(new java.awt.Font("Tahoma", 2, 20)); // NOI18N
        nomeDoAluno6.setText("Murilo Pratavieira");

        nomeDoAluno8.setFont(new java.awt.Font("Tahoma", 2, 20)); // NOI18N
        nomeDoAluno8.setText("Gustavo C. L. de O. Santos");
        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(167, 167, 167)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(nomeDoAluno8, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nomeDoAluno5, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(topico)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nomeDoAluno2, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(nomeProf)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(nomeDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(nomeDoAluno6, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(223, 223, 223))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(nomeDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(topico, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nomeDoAluno5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nomeDoAluno8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nomeDoAluno6)
                .addGap(45, 45, 45)
                .addComponent(nomeDoAluno2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(nomeProf)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        setBounds(250, 200, 614, 366);
    }// </editor-fold>//GEN-END:initComponents
public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Sobre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sobre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sobre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sobre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Sobre dialog = new Sobre(new javax.swing.JFrame(), true);
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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel nomeDisciplina;
    private javax.swing.JLabel nomeDoAluno2;
    private javax.swing.JLabel nomeDoAluno5;
    private javax.swing.JLabel nomeDoAluno6;
    private javax.swing.JLabel nomeDoAluno8;
    private javax.swing.JLabel nomeProf;
    private javax.swing.JLabel topico;
    // End of variables declaration//GEN-END:variables
}
