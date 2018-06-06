package notepad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author Asus Group
 */
public class Interface {

    JTextArea t;
    JFrame f;
    Container c;
    JPanel p2 ;
    JPanel p3;
    JMenuBar jmb;
    String fontType = "SansSerif";
    String fontStyle = "PLAIN";
    int fontSize = 14;
    boolean isBold = false;
    boolean isItalic = false;

    public Interface() {
        // ================creat frame=====================
         JFrame f = new JFrame("NotePad");

        //==================create container===============
        Container c = f.getContentPane();
        c.setLayout(new BorderLayout());
        //=================Create panel 3 to add (JmenuBar) and panel2 (Format:Bold,Italic...)
        JPanel p3 = new JPanel(new GridLayout(2, 1));
        c.add(p3, BorderLayout.NORTH);

        // ==================creat components: =============
        final JTextArea t = new JTextArea();
        // add t to container
        c.add(t, BorderLayout.CENTER);

        JMenuBar jmb = new JMenuBar();
        
        // add JmenuBar to panel3
        p3.add(jmb);



        
        //=================Create file , edit , view and help menus=============
        
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");

        JMenu viewMenu = new JMenu("View");
        JMenu HelpMenu = new JMenu("Help");

        // ================add file, edit , view , help menus into jmb=====
        jmb.add(fileMenu);
        jmb.add(editMenu);

        jmb.add(viewMenu);
        jmb.add(HelpMenu);

       

        //================ Action of save in FileMenu ==================
        Action saveAction;
        saveAction = new AbstractAction("SaveAs") {

            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                fileChooser.setDialogTitle("Specify a file to save");

                int userSelection = fileChooser.showSaveDialog(null);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    if (file.exists()) {
                        System.out.println("File already exists");
                        System.exit(1);
                    }
                    PrintWriter output;
                    try {
                        output = new PrintWriter(file);
                        output.print(t.getText());
                        output.close();
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    System.out.println(file.getAbsolutePath());
                }
            }
        };

        saveAction.putValue(Action.MNEMONIC_KEY,
                new Integer(KeyEvent.VK_V));
        KeyStroke saveKey = KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_MASK);

        saveAction.putValue(Action.ACCELERATOR_KEY, saveKey);

        //================ Action of openin File Menu ==================
        Action openAction;
        openAction = new AbstractAction("Open") {

            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser fileChooser1 = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                int returnValue = fileChooser1.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser1.getSelectedFile();
                    try {
                        FileReader reader = new FileReader(selectedFile);
                        t.read(reader, selectedFile);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    System.out.println(selectedFile.getAbsolutePath());
                }
            }
        };

        openAction.putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_O));
        KeyStroke openKey = KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_MASK);
        openAction.putValue(Action.ACCELERATOR_KEY, openKey);

   

//====================== Action of Exit in File Menu =====================================
        Action exitAction;
        exitAction = new AbstractAction("Exit") {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        };

        exitAction.putValue(Action.MNEMONIC_KEY,
                new Integer(KeyEvent.VK_E));
        KeyStroke exitKey = KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_MASK);

        exitAction.putValue(Action.ACCELERATOR_KEY, exitKey);

        JMenuItem exit = new JMenuItem(exitAction);
        
         //=================== FileMenu====================================
        
        JMenuItem open = new JMenuItem(openAction);
        JMenuItem save = new JMenuItem("Save");
        JMenuItem saveAs = new JMenuItem(saveAction);
        JMenuItem pageSetp = new JMenuItem("PageSetp.. ");
        JMenuItem print = new JMenuItem("Print..             Ctrl+P ");

    //====================File Menu ================================
        
        fileMenu.add(open);

        fileMenu.add(save);

        fileMenu.add(saveAs);

        fileMenu.addSeparator();

        fileMenu.add(pageSetp);

        fileMenu.add(print);

        fileMenu.add(exit);

        //==============EditMenu=========================================
        JMenuItem undo = new JMenuItem("Undo                Ctrl+Z");
        JMenuItem cut = new JMenuItem("Cut                  Ctrl+X");
        JMenuItem copy = new JMenuItem("Copy                Ctrl+C");
        JMenuItem paste = new JMenuItem("Paste              Ctrl+V");
        JMenuItem delete = new JMenuItem("Delete            Del");
        JMenuItem find = new JMenuItem("Find                Ctrl+F");
        JMenuItem findNext = new JMenuItem("FindNext        F3");
        JMenuItem replace = new JMenuItem("Replace          Ctrl+H");
        JMenuItem goTo = new JMenuItem("Go To               Ctrl+G");
        JMenuItem selectAll = new JMenuItem("Select All     Ctrl+A");
        JMenuItem time_date = new JMenuItem("Time/Date      F5");

        editMenu.add(undo);

        editMenu.addSeparator();

        editMenu.add(cut);

        editMenu.add(copy);

        editMenu.add(delete);

        editMenu.addSeparator();

        editMenu.add(find);

        editMenu.add(findNext);

        editMenu.add(replace);

        editMenu.add(goTo);

        editMenu.addSeparator();

        editMenu.add(selectAll);

        editMenu.add(time_date);
        //================= Format Panel==========================
        JPanel p2 = new JPanel(new FlowLayout(0, 10, 10));

        final JComboBox sizeCombobox = new JComboBox(new Object[]{"12", "20", "25", "30", "35", "40", "45", "50", "55", "60", "100"});
         final JComboBox fontCombobox = new JComboBox(new Object[]{"SansSerif", "Serif", "Monospaced", "Dialog", "DialogInput", "Slab Serif"});

   //======================Action of BoldButton=================
        Action BoldButtonAction;
        BoldButtonAction = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (isBold) {
                    isBold = false;
                } else {
                    isBold = true;
                }
                if (isBold && isItalic) {
                    t.setFont(new Font(fontType, Font.BOLD + Font.ITALIC, fontSize));
                } else if (isBold) {
                    t.setFont(new Font(fontType, Font.BOLD, fontSize));
                } else if (isItalic) {
                    t.setFont(new Font(fontType, Font.ITALIC, fontSize));
                } else {
                    t.setFont(new Font(fontType, Font.PLAIN, fontSize));
                }
            }
        };

        JButton Boldbutton = new JButton(BoldButtonAction);
        Boldbutton.setIcon(new ImageIcon("C:\\Users\\Asus Group\\Pictures/Bold.png"));

       

      //======================Action of ItalicButton=================
        Action ItalicdButtonAction;
        ItalicdButtonAction = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (isItalic) {
                    isItalic = false;
                } else {
                    isItalic = true;
                }

                if (isBold && isItalic) {
                    t.setFont(new Font(fontType, Font.BOLD + Font.ITALIC, fontSize));
                } else if (isBold) {
                    t.setFont(new Font(fontType, Font.BOLD, fontSize));
                } else if (isItalic) {
                    t.setFont(new Font(fontType, Font.ITALIC, fontSize));
                } else {
                    t.setFont(new Font(fontType, Font.PLAIN, fontSize));
                }

            }
        };

        JButton Italicbutton = new JButton(ItalicdButtonAction);
        Italicbutton.setIcon(new ImageIcon("C:\\Users\\Asus Group\\Pictures/italic.png"));

    //===================Action of fontSize===========================
        Action fontSizeAction;
        fontSizeAction = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                fontSize = Integer.parseInt(sizeCombobox.getSelectedItem().toString());
                if (isBold && isItalic) {
                    t.setFont(new Font(fontType, Font.BOLD + Font.ITALIC, fontSize));
                } else if (isBold) {
                    t.setFont(new Font(fontType, Font.BOLD, fontSize));
                } else if (isItalic) {
                    t.setFont(new Font(fontType, Font.ITALIC, fontSize));
                } else {
                    t.setFont(new Font(fontType, Font.PLAIN, fontSize));
                }

            }
        };

        sizeCombobox.addActionListener(fontSizeAction);
        
    //========================Action of fontType===================

        Action fontTypeSizeAction;
        fontTypeSizeAction = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                fontType = fontCombobox.getSelectedItem().toString();
                if (isBold && isItalic) {
                    t.setFont(new Font(fontType, Font.BOLD + Font.ITALIC, fontSize));
                } else if (isBold) {
                    t.setFont(new Font(fontType, Font.BOLD, fontSize));
                } else if (isItalic) {
                    t.setFont(new Font(fontType, Font.ITALIC, fontSize));
                } else {
                    t.setFont(new Font(fontType, Font.PLAIN, fontSize));
                }
            }
        };

        fontCombobox.addActionListener(fontTypeSizeAction);

   //==== add Boldbutton , Italicbutton , sizeComboBox and fontComboBox to panel 2
        p2.add(Boldbutton);
        p2.add(Italicbutton);
        p2.add(sizeCombobox);
        p2.add(fontCombobox);

    //==== Add panel to panel3
        p3.add(p2);

        //====================ViewMenu==============================
        JCheckBoxMenuItem statusBar = new JCheckBoxMenuItem("Status Bar");

        viewMenu.add(statusBar);

        //========================HelpMenu==========================
        JMenuItem viewHelp = new JMenuItem("View Help");
        JMenuItem aboutNotepad = new JMenuItem("About NotePad");

        HelpMenu.add(viewHelp);

        editMenu.addSeparator();

        HelpMenu.add(aboutNotepad);

        //===================Frame Intialization====================
        f.setSize(600, 600);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        Interface i = new Interface();

    }

}
