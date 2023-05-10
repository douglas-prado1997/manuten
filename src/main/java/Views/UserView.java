
package Views;

import Controllers.RepublicController;
import Models.RepublicModel;
import Models.UserModel;


public class UserView extends javax.swing.JFrame {
    private RepublicController republicController;
    private UserModel user;
    private RepublicModel republic;

    public void setUser(UserModel user, RepublicModel republic) {
        this.user = user;
        this.republic = republic;

        this.nameLabel.setText(user.getName());
        this.userScoreLabel.setText("0.0");
        this.feedbackPanel2.setVisible(false);
        
        if (this.republic != null) {
            this.republicLabel2.setText("República: " + this.republic.getName());
        }
    }
    

    public UserView(RepublicController republicController) {
        this.republicController = republicController;
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ContentPanel = new javax.swing.JPanel();
        republicLabel2 = new javax.swing.JLabel();
        feedbackPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        feedbacksTable2 = new javax.swing.JTable();
        viewFeedbackButton2 = new javax.swing.JButton();
        nameLabel = new javax.swing.JLabel();
        userScoreLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        ContentPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Perfil"));

        republicLabel2.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        republicLabel2.setText("República");

        feedbackPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Feedbacks"));

        feedbacksTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Tarefa", "Usuário", "Score", "Criação"
            }
        ));
        jScrollPane3.setViewportView(feedbacksTable2);

        viewFeedbackButton2.setBackground(new java.awt.Color(76, 80, 182));
        viewFeedbackButton2.setForeground(new java.awt.Color(255, 255, 255));
        viewFeedbackButton2.setText("Visualizar Feedback");

        javax.swing.GroupLayout feedbackPanel2Layout = new javax.swing.GroupLayout(feedbackPanel2);
        feedbackPanel2.setLayout(feedbackPanel2Layout);
        feedbackPanel2Layout.setHorizontalGroup(
            feedbackPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(feedbackPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(feedbackPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE)
                    .addGroup(feedbackPanel2Layout.createSequentialGroup()
                        .addComponent(viewFeedbackButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        feedbackPanel2Layout.setVerticalGroup(
            feedbackPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(feedbackPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(viewFeedbackButton2)
                .addContainerGap())
        );

        nameLabel.setText("Nome");
        nameLabel.setBorder(javax.swing.BorderFactory.createTitledBorder("Nome"));

        userScoreLabel.setText("Score");
        userScoreLabel.setBorder(javax.swing.BorderFactory.createTitledBorder("Score"));

        javax.swing.GroupLayout ContentPanelLayout = new javax.swing.GroupLayout(ContentPanel);
        ContentPanel.setLayout(ContentPanelLayout);
        ContentPanelLayout.setHorizontalGroup(
            ContentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ContentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ContentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userScoreLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(feedbackPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(ContentPanelLayout.createSequentialGroup()
                        .addComponent(republicLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(174, 174, 174)))
                .addContainerGap())
        );
        ContentPanelLayout.setVerticalGroup(
            ContentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ContentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nameLabel)
                .addGap(18, 18, 18)
                .addComponent(republicLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(userScoreLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(feedbackPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ContentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ContentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ContentPanel;
    private javax.swing.JPanel feedbackPanel2;
    private javax.swing.JTable feedbacksTable2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel republicLabel2;
    private javax.swing.JLabel userScoreLabel;
    private javax.swing.JButton viewFeedbackButton2;
    // End of variables declaration//GEN-END:variables
}
