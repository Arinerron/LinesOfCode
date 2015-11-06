package com.arinerron.utils.linesofcode;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Display extends javax.swing.JFrame {
    
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea ln;   

    public Display(String number) {
        initComponents();
        ln.setText(number);
        setLocationRelativeTo(null);
        setTitle("Lines of Code");
        setVisible(true);
    }

    @SuppressWarnings("unchecked")                      
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        ln = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Lines - 0");
        setAlwaysOnTop(true);
        setResizable(false);

        ln.setEditable(false);
        ln.setBackground(new java.awt.Color(204, 204, 204));
        ln.setColumns(20);
        ln.setFont(new java.awt.Font("Phosphate", 0, 18));
        ln.setRows(5);
        jScrollPane1.setViewportView(ln);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
        );

        getAccessibleContext().setAccessibleName("Lines of Code");
        getAccessibleContext().setAccessibleDescription("Lines of Code");

        pack();
    }              
}
