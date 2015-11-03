package org.uwl2owl;

import bean.SystemUser;
import org.argouml.uml.diagram.ui.ActionAddNote;
import restclient.AuthenticationClient;
import soapclient.Concept;
import util.Constants;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;
import java.util.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
/**
 * Created by yang on 12/10/2015.
 */
public class DesignWorkspaceDemo extends JFrame{


    private soapclient.DesignWorkspaceOntology port = null;
    private Concept globalConcept;
    private boolean isLoadbuttonPressed = false;



    private JButton jButtonAnnotateMetric;
    private JButton jButtonAnnotateQuality;
    private JButton jButtonAnnotateValue;
    private JButton jButtonLoadExistingScenario;
    private JButton jButtonSave;
    private JComboBox jComboBoxConcept;
    private JComboBox jComboBoxParentConcept;
    private JComboBox jComboBoxRelationship;
    private JComboBox jComboBoxRelationship2;//add by Yang Lu
    private JComboBox jComboBoxRelationship3;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;//add by Yang Lu
    private JLabel jLabel7;
    private JLabel jLabelInformation;
    private JScrollPane jScrollPane1;
    private JTextArea jTextAreaConceptDescription;
    private JTextField jTextFieldConceptName;

    public DesignWorkspaceDemo() {
        initComponents();
    }

    class ComboItem
    {
        private String key;
        private String value;

        public ComboItem()
        {

        }

        public ComboItem(String key, String value)
        {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString()
        {
            return key;
        }

        public String getKey()
        {
            return key;
        }

        public String getValue()
        {
            return value;
        }
    }


    private void initComponents()
    {
        jComboBoxConcept = new JComboBox();
        jTextFieldConceptName = new JTextField();
        jScrollPane1 = new JScrollPane();
        jTextAreaConceptDescription = new JTextArea();
        jComboBoxRelationship = new JComboBox();
        jComboBoxRelationship2 = new JComboBox();
        jComboBoxRelationship3 = new JComboBox();
        jComboBoxParentConcept = new JComboBox();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel();
        jLabel7 = new JLabel();

        jButtonLoadExistingScenario = new JButton();
        jButtonAnnotateQuality = new JButton();
        jButtonAnnotateMetric = new JButton();
        jButtonAnnotateValue = new JButton();
        jButtonSave = new JButton();
        jLabelInformation = new JLabel();

        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jComboBoxConcept.setModel(new DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTextAreaConceptDescription.setColumns(20);
        jTextAreaConceptDescription.setRows(5);
        jScrollPane1.setViewportView(jTextAreaConceptDescription);

        jComboBoxRelationship.setModel(new DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBoxRelationship2.setModel(new DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxRelationship3.setModel(new DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBoxParentConcept.setModel(new DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setText("Select Scenario:");

        jLabel2.setText("Scenario Name:");

        jLabel3.setText("Scenario Description:");

        jLabel4.setText("Relation:");

        jLabel6.setText("AnnotationOntologyRelation:");
        jLabel7.setText("ChangeOntologyRelation:");
        jLabel5.setText("Parent Element:");

        jButtonLoadExistingScenario.setText("Load Existing Scenario");
        jButtonLoadExistingScenario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButtonLoadExistingScenarioActionPerformed(evt);
            }
        });

        jButtonAnnotateQuality.setText("Annotation Quality");
        jButtonAnnotateQuality.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButtonAnnotateQualityActionPerformed(evt);
            }
        });

        jButtonAnnotateMetric.setText("Annotate Metric");
        jButtonAnnotateMetric.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButtonAnnotateMetricActionPerformed(evt);
            }
        });

        jButtonAnnotateValue.setText("Annotate Value");
        jButtonAnnotateValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButtonAnnotateValueActionPerformed(evt);
            }
        });

        jButtonSave.setText("Save");
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel7, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                                        .addComponent(jComboBoxConcept, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(jTextFieldConceptName, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE))
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(jButtonLoadExistingScenario, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE))
                                                                        .addComponent(jComboBoxRelationship, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jComboBoxRelationship2, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jComboBoxRelationship3, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jComboBoxParentConcept, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(jButtonAnnotateQuality, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                                                                        .addComponent(jButtonAnnotateMetric, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jButtonAnnotateValue, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                        .addComponent(jButtonSave, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(jLabelInformation))
                                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelInformation)
                                .addGap(8, 8, 8)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jComboBoxConcept, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1)
                                        .addComponent(jButtonLoadExistingScenario))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextFieldConceptName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jButtonAnnotateQuality)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jButtonAnnotateMetric)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButtonAnnotateValue)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jComboBoxRelationship, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jComboBoxRelationship2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel6))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jComboBoxRelationship3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel7))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jComboBoxParentConcept, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addComponent(jButtonSave)
                                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();

    }

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        initializeGUI();
        System.out.println("Design Workspace Window opened.");
    }
    private void initializeGUI()
    {
        try
        {
            jComboBoxConcept.removeAllItems();
            jComboBoxRelationship.removeAllItems();

            jComboBoxRelationship2.removeAllItems();
            jComboBoxRelationship3.removeAllItems();
            jComboBoxParentConcept.removeAllItems();


            //note.actionPerformed();
            Constants.ontologyServerURI = "http://aufeef.itu.dk:8080/ArchitectureDesignWorkspace/webresources";

            AuthenticationClient authenticationClient = new AuthenticationClient();
            SystemUser tempSystemUser = (SystemUser) authenticationClient.isSystemUserPresent(SystemUser.class, "test");
            if( !(tempSystemUser.getUserid() != null && tempSystemUser.getUserid().length() > 0) )
            {
                SystemUser systemUser = new SystemUser();
                systemUser.setUserid("test");
                systemUser.setName("testname");
                systemUser.setPassword("pwd");
                tempSystemUser = (SystemUser) authenticationClient.createSystemUser_XML(SystemUser.class, systemUser);
            }
            SystemUser systemUser = new SystemUser();
            systemUser.setUserid("test");
            systemUser.setPassword("pwd");
            tempSystemUser = (SystemUser) authenticationClient.getAccessKey_XML(SystemUser.class, systemUser);

            String userId = "test";
            String authKey = tempSystemUser.getCurrentAuthKey();

            soapclient.DesignOntology service = new soapclient.DesignOntology();
            port = service.getDesignWorkspaceOntologyPort();

            Map<String, Object> req_ctx = ((BindingProvider)port).getRequestContext();
            Map<String, List<String>> headers = new HashMap<String, List<String>>();
            headers.put(Constants.userId, Collections.singletonList(userId));
            headers.put(Constants.authKey, Collections.singletonList(authKey));
            req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);

            ArrayList<String> relationList = (ArrayList) port.getRelationList(Constants.workspaceIdentifier);
            if( relationList != null )
            {
                for( int i = 0 ; i < relationList.size() ; i++ )
                {
                    String itemValue = relationList.get(i);
                    jComboBoxRelationship.addItem(new ComboItem(itemValue,itemValue));
                }
            }

            relationList = (ArrayList) port.getAnnotationOntologyRelationList(Constants.workspaceIdentifier);
            if( relationList != null )
            {
                for( int i = 0 ; i < relationList.size() ; i++ )
                {
                    String itemValue = relationList.get(i);
                    jComboBoxRelationship2.addItem(new ComboItem(itemValue,itemValue));
                }
            }

            relationList = (ArrayList) port.getChangeOntologyRelationList(Constants.workspaceIdentifier);
            if( relationList != null )
            {
                for( int i = 0 ; i < relationList.size() ; i++ )
                {
                    String itemValue = relationList.get(i);
                    jComboBoxRelationship3.addItem(new ComboItem(itemValue,itemValue));
                }
            }



            Concept parentConcept = new Concept();
            parentConcept.setName(Constants.knowledgeIdentifier);
            parentConcept.setDisplayName(Constants.knowledgeIdentifier);
            ArrayList<Concept> parentConceptList = (ArrayList) port.getConceptListOfParents("DesignWorkspace", Constants.knowledgeIdentifier, parentConcept);
            if( parentConceptList != null )
            {
                for( int i = 0 ; i < parentConceptList.size() ; i++ )
                {
                    Concept tempConcept = parentConceptList.get(i);
                    if( tempConcept != null )
                    {
                        jComboBoxParentConcept.addItem(new ComboItem(tempConcept.getName(),tempConcept.getDisplayName()));
                    }
                }
            }


            if( parentConceptList != null )
            {
                for( int i = 0 ; i < parentConceptList.size() ; i++ )
                {
                    Concept tempConcept = parentConceptList.get(i);
                    if( tempConcept != null )
                    {
                        if( tempConcept.getName() != null && !tempConcept.getName().equalsIgnoreCase(Constants.knowledgeIdentifier) )
                            jComboBoxConcept.addItem(new ComboItem(tempConcept.getDisplayName(), tempConcept.getName()));
                    }
                }
            }
        }
        catch(Exception ex)
        {
            System.out.println("Exception in DesignWorkspaceDemo:initializeGUI");
            System.out.println(ex.toString());
        }


    }




    private void jButtonSaveActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        // TODO add your handling code here:
        try
        {
            boolean isAlreadyPresent = false;
            String relation = jComboBoxRelationship.getSelectedItem().toString();
            ComboItem parentItem = new ComboItem();
            parentItem = (ComboItem) jComboBoxParentConcept.getSelectedItem();
            Concept parentConcept = new Concept();
            parentConcept.setName(parentItem.getKey());
            parentConcept.setDisplayName(parentItem.getValue());

            Concept childConcept = new Concept();
            ComboItem conceptComboItem = (ComboItem) jComboBoxConcept.getSelectedItem();
            if( conceptComboItem != null && conceptComboItem.getValue() != null &&
                    conceptComboItem.getValue().length() > 0)
            {
                isAlreadyPresent = true;
            }

            if( isAlreadyPresent && isLoadbuttonPressed )
            {
                childConcept.setName(conceptComboItem.getValue());
                childConcept.setDisplayName(jTextFieldConceptName.getText());
                childConcept.setDescription(jTextAreaConceptDescription.getText());
                if( relation != null && relation.length() > 0 &&
                        childConcept.getName() != null && childConcept.getName().length() > 0 )
                {
                    port.updateConcept(Constants.workspaceIdentifier, Constants.knowledgeIdentifier, childConcept);
                    jLabelInformation.setText("Infoamtion updated.");
                    resetFields();
                    initializeGUI();
                    isAlreadyPresent = false;
                    isLoadbuttonPressed = false;

                }
            }
            else
            {
                childConcept.setName("_PAKME_" + jTextFieldConceptName.getText());
                childConcept.setDisplayName(jTextFieldConceptName.getText());
                childConcept.setDescription(jTextAreaConceptDescription.getText());

                if( relation != null && relation.length() > 0 &&
                        childConcept.getName() != null && childConcept.getName().length() > 0 )
                {
                    port.addConcept(Constants.workspaceIdentifier, Constants.knowledgeIdentifier, childConcept, relation, parentConcept);
                    jLabelInformation.setText("Infoamtion saved.");
                    resetFields();
                    initializeGUI();
                    isAlreadyPresent = false;
                }
            }
        }
        catch(Exception ex)
        {
            System.out.println("Exception in ScenarioGUI:jButtonSaveActionPerformed");
            System.out.println(ex.toString());
        }

    }

    private void jButtonLoadExistingScenarioActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButtonLoadExistingScenarioActionPerformed
        // TODO add your handling code here:

        try {
            ComboItem conceptComboItem = (ComboItem) jComboBoxConcept.getSelectedItem();
            if (conceptComboItem != null && conceptComboItem.getKey() != null &&
                    conceptComboItem.getKey().length() > 0) {
                Concept tempConcept = new Concept();
                tempConcept.setName(conceptComboItem.getValue());
                ArrayList<Concept> globalConceptList =
                        (ArrayList) port.getConcept(Constants.workspaceIdentifier, Constants.knowledgeIdentifier, tempConcept);
                if (globalConceptList != null && globalConceptList.size() > 0)
                    globalConcept = globalConceptList.get(0);
                jTextFieldConceptName.setText(globalConcept.getDisplayName());
                jTextAreaConceptDescription.setText(globalConcept.getDescription());
                isLoadbuttonPressed = true;



            }
        } catch (Exception ex) {
            System.out.println("Exception in DesignWorkspaceDemo:jButtonLoadExistingScenarioActionPerformed");
            System.out.println(ex.toString());
        }
    }

    private void resetFields()
    {
        try
        {
            jTextFieldConceptName.setText("");
            jTextAreaConceptDescription.setText("");
        }
        catch(Exception ex)
        {
            System.out.println("Exception in DesignWorkspaceDemo:resetFields");
            System.out.println(ex.toString());
        }
    }

    private void jButtonAnnotateQualityActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButtonAnnotateQualityActionPerformed
        // TODO add your handling code here:
        try
        {
            String selectedText = jTextAreaConceptDescription.getSelectedText();
            String completeText = jTextAreaConceptDescription.getText();
            String placeholderAnnotation = Constants.qualityAnnotation;
            String replacedString = placeholderAnnotation.replaceFirst(Constants.annotationPlaceholder, selectedText);
            String newCompleteString = completeText.replaceFirst(selectedText, replacedString);
            jTextAreaConceptDescription.setText(newCompleteString);
            System.out.println(selectedText);
        }
        catch(Exception ex)
        {
            System.out.println("Exception in DesignWorkspaceDemo:jButtonAnnotateQualityActionPerformed");
            System.out.println(ex.toString());
        }
    }
    private void jButtonAnnotateMetricActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButtonAnnotateMetricActionPerformed
        // TODO add your handling code here:
        try
        {
            String selectedText = jTextAreaConceptDescription.getSelectedText();
            String completeText = jTextAreaConceptDescription.getText();
            String placeholderAnnotation = Constants.metricAnnotation;
            String replacedString = placeholderAnnotation.replaceFirst(Constants.annotationPlaceholder, selectedText);
            String newCompleteString = completeText.replaceFirst(selectedText, replacedString);
            jTextAreaConceptDescription.setText(newCompleteString);
        }
        catch(Exception ex)
        {
            System.out.println("Exception in DesignWorkspaceDemo:jButtonAnnotateQualityActionPerformed");
            System.out.println(ex.toString());
        }
    }

    private void jButtonAnnotateValueActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButtonAnnotateValueActionPerformed
        // TODO add your handling code here:
        try
        {
            String selectedText = jTextAreaConceptDescription.getSelectedText();
            String completeText = jTextAreaConceptDescription.getText();
            String placeholderAnnotation = Constants.valueAnnotation;
            String replacedString = placeholderAnnotation.replaceFirst(Constants.annotationPlaceholder, selectedText);
            String newCompleteString = completeText.replaceFirst(selectedText, replacedString);
            jTextAreaConceptDescription.setText(newCompleteString);
        }
        catch(Exception ex)
        {
            System.out.println("Exception in DesignWorkspaceDemo:jButtonAnnotateQualityActionPerformed");
            System.out.println(ex.toString());
        }
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DesignWorkspaceDemo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DesignWorkspaceDemo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DesignWorkspaceDemo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DesignWorkspaceDemo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DesignWorkspaceDemo().setVisible(true);
            }
        });
    }

}


