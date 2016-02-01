package com.arinerron.utils.linesofcode;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class LinesOfCode {

    public long i = 0;
    public HashMap MAP = new HashMap();
    public String string = "";

    public static void main(String[] args) {
        LinesOfCode m = new LinesOfCode();
    }

    public LinesOfCode() {
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File(new File(new File(System.getProperty("user.home")), "Desktop"), "Moome")); // start at application current directory
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = fc.showSaveDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File yourFolder = fc.getSelectedFile();
            start(yourFolder);
            log("==========LINES==========");
            printMap(MAP);
            log("\nTOTAL: " + i);
            log("=========================");
            System.out.println(string);
            new Display(string);
        } else {
            log("Exit!");
        }
    }
    
    public void log(String s) {
        string = string + s + "\n";
    }

    public void start(File f) {
        File[] list = f.listFiles();
        for (File c : list) {
            if (!(c.isFile())) {
                start(c);
            } else {
                String[] split = c.getAbsolutePath().split("\\.");
                String ext = "NULL";
                try {ext = split[split.length - 1].toUpperCase();} catch(Exception e){ext = "NULL";}
                if(split.length == 1)
                    ext = "NULL";
                if (!MAP.containsKey(ext)) {
                    MAP.put(ext, countLines(c));
                } else {
                    MAP.put(ext, (((long) MAP.get(ext)) + countLines(c)));
                }
            }
        }
    }

    public long countLines(File filename) {
        InputStream is = null;
        try {
            is = new BufferedInputStream(new FileInputStream(filename));
            try {
                byte[] c = new byte[1024];
                long count = 0;
                long readChars = 0;
                boolean empty = true;
                while ((readChars = is.read(c)) != -1) {
                    empty = false;
                    for (int i = 0; i < readChars; ++i) {
                        if (c[i] == '\n') {
                            ++count;
                        }
                    }
                }
                return (count == 0 && !empty) ? 1 : count;
            } catch (IOException ex) {
                Logger.getLogger(LinesOfCode.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    is.close();
                } catch (IOException ex) {
                    Logger.getLogger(LinesOfCode.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LinesOfCode.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                is.close();
            } catch (IOException ex) {
                Logger.getLogger(LinesOfCode.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return 0;
    }

    public void printMap(Map mp) {
        Iterator it = mp.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            long v = (long)pair.getValue();
            log(pair.getKey() + ": " + v);
            i += v;
            it.remove();
        }
    }
}

class Display extends javax.swing.JFrame {
    
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
