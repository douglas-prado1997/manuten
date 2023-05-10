package Views;

import Controllers.RepublicController;
import Models.UserModel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.DefaultListModel;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class AddTaskView extends javax.swing.JFrame {
    private RepublicController republicController;
    private ArrayList<UserModel> usersModel;
    
    public void closeTask() {
        this.dispose();;
    }
    
    public void emptyFields() {
        this.usersModel = null;
        this.titleField.setText("");
        this.descriptionArea.setText("");
        this.expiresField.setText("");
    }
    
    public void setUsers(ArrayList<UserModel> usersModel) {
        this.usersModel = usersModel;
        this.setUserList();
    }
    
    public void setUserList() {
        DefaultListModel listModel = new DefaultListModel();
        for (UserModel userModel : this.usersModel) {
            listModel.addElement(userModel.getName());
        }
        this.userList.setModel(listModel);
    }


    public AddTaskView(RepublicController republicController) {
        this.republicController = republicController;
        initComponents();
    }


    @SuppressWarnings("unchecked")
    private void initComponents() {

        contentPanel = new javax.swing.JPanel();
        titleField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        descriptionArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        userList = new javax.swing.JList<>();
        addButton = new javax.swing.JButton();
        expiresField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        contentPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Tarefa"));

        titleField.setBorder(javax.swing.BorderFactory.createTitledBorder("Título"));

        descriptionArea.setColumns(20);
        descriptionArea.setRows(5);
        descriptionArea.setBorder(javax.swing.BorderFactory.createTitledBorder("Descrição"));
        jScrollPane1.setViewportView(descriptionArea);

        userList.setBorder(javax.swing.BorderFactory.createTitledBorder("Responsável"));
        userList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(userList);

        addButton.setBackground(new java.awt.Color(76, 180, 82));
        addButton.setForeground(new java.awt.Color(255, 255, 255));
        addButton.setText("Criar Tarefa");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        expiresField.setBorder(javax.swing.BorderFactory.createTitledBorder("Expiração / Deadline"));

        javax.swing.GroupLayout contentPanelLayout = new javax.swing.GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(titleField, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(expiresField)))
                .addContainerGap())
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addGap(160, 160, 160)
                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(171, Short.MAX_VALUE))
        );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addComponent(titleField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(expiresField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                .addComponent(addButton)
                .addContainerGap())
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
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        try {
            
            String title = this.titleField.getText().trim();
            String description = this.descriptionArea.getText();
            String deadline = this.expiresField.getText();
            int userId = this.userList.getSelectedIndex();

            String userUuid = this.usersModel.get(userId).getUuid().toString();

            if (title.isEmpty() || description.isEmpty() || deadline.isEmpty() || userId == -1 || userUuid.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos corretamente", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                LocalDateTime expiresAt = LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));

                this.republicController.addTask(title, description, userUuid, expiresAt);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Data inválida", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(this, "Dados inválido", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }//GEN-LAST:event_addButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JTextArea descriptionArea;
    private javax.swing.JTextField expiresField;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField titleField;
    private javax.swing.JList<String> userList;
    // End of variables declaration//GEN-END:variables
}
