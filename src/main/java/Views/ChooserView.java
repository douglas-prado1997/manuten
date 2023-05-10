package Views;

import Controllers.RepublicController;


public class ChooserView extends javax.swing.JFrame {
    private RepublicController republicController;
    
    public void closeView() {
        this.dispose();
    }
    
  
    public ChooserView(RepublicController republicController) {
        this.republicController = republicController;
        initComponents();
    }


    @SuppressWarnings("unchecked")
    private void initComponents() {

        contentPanel = new javax.swing.JPanel();
        createRepublicPanel = new javax.swing.JPanel();
        createNameRepublicField = new javax.swing.JTextField();
        createRepublicPasswordField = new javax.swing.JPasswordField();
        createNewRepublicPasswordField = new javax.swing.JPasswordField();
        createRepublicButton = new javax.swing.JButton();
        enterRepublicPanel = new javax.swing.JPanel();
        enterNameRepublicField = new javax.swing.JTextField();
        enterPasswordRepublicField = new javax.swing.JPasswordField();
        enterREpublicButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        contentPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Seja bem vindo ao RepTasks"));

        createRepublicPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Criar República"));

        createNameRepublicField.setBorder(javax.swing.BorderFactory.createTitledBorder("Nome da República"));

        createRepublicPasswordField.setBorder(javax.swing.BorderFactory.createTitledBorder("Senha"));

        createNewRepublicPasswordField.setBorder(javax.swing.BorderFactory.createTitledBorder("Confirmar Senha"));

        createRepublicButton.setBackground(new java.awt.Color(76, 180, 82));
        createRepublicButton.setForeground(new java.awt.Color(255, 255, 255));
        createRepublicButton.setText("Criar República");
        createRepublicButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createRepublicButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout createRepublicPanelLayout = new javax.swing.GroupLayout(createRepublicPanel);
        createRepublicPanel.setLayout(createRepublicPanelLayout);
        createRepublicPanelLayout.setHorizontalGroup(
            createRepublicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createRepublicPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(createRepublicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(createNameRepublicField)
                    .addComponent(createRepublicPasswordField)
                    .addComponent(createNewRepublicPasswordField)
                    .addGroup(createRepublicPanelLayout.createSequentialGroup()
                        .addComponent(createRepublicButton, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 355, Short.MAX_VALUE)))
                .addContainerGap())
        );
        createRepublicPanelLayout.setVerticalGroup(
            createRepublicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createRepublicPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(createNameRepublicField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(createRepublicPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(createNewRepublicPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(createRepublicButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        enterRepublicPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Entrar em República"));

        enterNameRepublicField.setBorder(javax.swing.BorderFactory.createTitledBorder("Nome da República"));

        enterPasswordRepublicField.setBorder(javax.swing.BorderFactory.createTitledBorder("Senha da República"));

        enterREpublicButton.setBackground(new java.awt.Color(76, 180, 82));
        enterREpublicButton.setForeground(new java.awt.Color(255, 255, 255));
        enterREpublicButton.setText("Entrar na República");
        enterREpublicButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterREpublicButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout enterRepublicPanelLayout = new javax.swing.GroupLayout(enterRepublicPanel);
        enterRepublicPanel.setLayout(enterRepublicPanelLayout);
        enterRepublicPanelLayout.setHorizontalGroup(
            enterRepublicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(enterRepublicPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(enterRepublicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(enterNameRepublicField)
                    .addComponent(enterPasswordRepublicField)
                    .addGroup(enterRepublicPanelLayout.createSequentialGroup()
                        .addComponent(enterREpublicButton, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        enterRepublicPanelLayout.setVerticalGroup(
            enterRepublicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(enterRepublicPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(enterNameRepublicField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(enterPasswordRepublicField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(enterREpublicButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout contentPanelLayout = new javax.swing.GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(enterRepublicPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(createRepublicPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(createRepublicPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(enterRepublicPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }

    private void createRepublicButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String name = this.createNameRepublicField.getText();
        String password = String.valueOf(this.createRepublicPasswordField.getPassword());
        String passwordConfirm = String.valueOf(this.createNewRepublicPasswordField.getPassword());
        
        this.republicController.createRepublic(name, password, passwordConfirm);
    }

    private void enterREpublicButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String name = this.enterNameRepublicField.getText();
        String password = String.valueOf(this.enterPasswordRepublicField.getPassword());
        
        this.republicController.enterRepublic(name, password);
    }


    private javax.swing.JPanel contentPanel;
    private javax.swing.JTextField createNameRepublicField;
    private javax.swing.JPasswordField createNewRepublicPasswordField;
    private javax.swing.JButton createRepublicButton;
    private javax.swing.JPanel createRepublicPanel;
    private javax.swing.JPasswordField createRepublicPasswordField;
    private javax.swing.JTextField enterNameRepublicField;
    private javax.swing.JPasswordField enterPasswordRepublicField;
    private javax.swing.JButton enterREpublicButton;
    private javax.swing.JPanel enterRepublicPanel;

}
