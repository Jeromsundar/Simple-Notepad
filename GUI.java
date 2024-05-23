import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI implements ActionListener {
    JFrame window;
    
    //texrx area
    JTextArea textArea;
    JScrollPane scrollPane;
    boolean wordWrapOn =false;
    
    //to menu bar
    JMenuBar menuBar;
    JMenu menuFile, menuEdit, menuFormat, menuColour;
   
    //file menu
    JMenuItem iNew,iOpen, iSave, iSaveAs, iExit;
   
    //edit menu
    JMenuItem iUndo, iRedo;
    //formate menu
    JMenuItem iwrap, iFontArial, iFontCSMS, iFontTINR, iFontSize8, iFontSize12, iFontSize14, iFontSize16, iFontSize20, iFontSize24;
    JMenu menuFont, menuFontSize;

    //color menu
    JMenuItem iColor1,iColor2,iColor3;

    // Assuming Function_File is a class, you need to specify its type and variable name.
    Function_File File = new Function_File(this);
    Function_Format formate =new Function_Format(this);
    Function_Color color=new Function_Color(this);
    Function_Edit Edit=new Function_Edit(this);

    KeyHandler kHandler =new KeyHandler(this);

    UndoManager um= new UndoManager();
    public static void main(String[] args) {
        new GUI();
    }

    public GUI() {
        createwindow();
        createTextarea();
        createMenubar();
        createFilemenu();
        createEditMenu();
        createFomatMenu();
        createColorMenu();

        formate.selectedFont="Arial";
        formate.createFont(8);
        formate.wordWrap();
        color.changeColor("White");
        window.setVisible(true);
    }

    public void createwindow() {
        window = new JFrame("Notepad");
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void createTextarea() {
        textArea = new JTextArea();
        textArea.setFont(formate.arial);

        textArea.addKeyListener(kHandler);

        textArea.getDocument().addUndoableEditListener(new UndoableEditListener() {
            public void undoableEditHappened(UndoableEditEvent e){
                um.addEdit(e.getEdit());
            }
        });
        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        window.add(scrollPane);
    }

    public void createMenubar() {
        menuBar = new JMenuBar();
        window.setJMenuBar(menuBar);

        menuFile = new JMenu("File");
        menuBar.add(menuFile);

        menuEdit = new JMenu("Edit");
        menuBar.add(menuEdit);

        menuFormat = new JMenu("Format");
        menuBar.add(menuFormat);

        menuColour = new JMenu("Colour");
        menuBar.add(menuColour);
    }

    public void createFilemenu() {
        iNew = new JMenuItem("New");
        iNew.addActionListener(this);
        iNew.setActionCommand("New"); // Correcting the setting of action command
        menuFile.add(iNew);

        iOpen = new JMenuItem("Open");
        iOpen.addActionListener(this);
        iOpen.setActionCommand("Open");
        menuFile.add(iOpen);

        iSave = new JMenuItem("Save");
        iSave.addActionListener(this);
        iSave.setActionCommand("Save");
        menuFile.add(iSave);

        iSaveAs = new JMenuItem("SaveAs");
        iSaveAs.addActionListener(this);
        iSaveAs.setActionCommand("SaveAs");
        menuFile.add(iSaveAs);

        iExit = new JMenuItem("Exit");
        iExit.addActionListener(this);
        iExit.setActionCommand("Exit");
        menuFile.add(iExit);
    }
    public void createEditMenu(){
        iUndo=new JMenuItem("Undo");
        iUndo.addActionListener(this);
        iUndo.setActionCommand("Undo");
        menuEdit.add(iUndo);

        iRedo=new JMenuItem("Redo");
        iRedo.addActionListener(this);
        iRedo.setActionCommand("Redo");
        menuEdit.add(iRedo);
    }
    public void createFomatMenu(){
        iwrap = new JMenuItem("WWord Wrap:Off");
        iwrap.addActionListener(this);
        iwrap.setActionCommand("Word Wrap");
        menuFormat.add(iwrap);

        menuFont =new JMenu("Font");
        menuFormat.add(menuFont);

        iFontArial =new JMenuItem("Arial");
        iFontArial.addActionListener(this);
        iFontArial.setActionCommand("Arial");
        menuFont.add(iFontArial);

        iFontCSMS =new JMenuItem("Comic San MS");
        iFontCSMS.addActionListener(this);
        iFontCSMS.setActionCommand("Comic San MS");
        menuFont.add(iFontCSMS);

        iFontTINR =new JMenuItem("Times New Roman");
        iFontTINR.addActionListener(this);
        iFontTINR.setActionCommand("Times New Roman");
        menuFont.add(iFontTINR);

        menuFontSize = new JMenu("Font Size");
        menuFormat.add(menuFontSize);

        iFontSize8 = new JMenuItem("8");
        iFontSize8.addActionListener(this);
        iFontSize8.setActionCommand("size8");
        menuFontSize.add(iFontSize8);

        iFontSize12 = new JMenuItem("12");
        iFontSize12.addActionListener(this);
        iFontSize12.setActionCommand("size12");
        menuFontSize.add(iFontSize12);

        iFontSize14 = new JMenuItem("14");
        iFontSize14.addActionListener(this);
        iFontSize14.setActionCommand("size14");
        menuFontSize.add(iFontSize14);

        iFontSize16 = new JMenuItem("16");
        iFontSize16.addActionListener(this);
        iFontSize16.setActionCommand("size16");
        menuFontSize.add(iFontSize16);

        iFontSize20 = new JMenuItem("20");
        iFontSize20.addActionListener(this);
        iFontSize20.setActionCommand("size20");
        menuFontSize.add(iFontSize20);

        iFontSize24 = new JMenuItem("24");
        iFontSize24.addActionListener(this);
        iFontSize24.setActionCommand("size24");
        menuFontSize.add(iFontSize24);
    }
    public void createColorMenu(){
        iColor1=new JMenuItem("White");
        iColor1.addActionListener(this);
        iColor1.setActionCommand("White");
        menuColour.add(iColor1);

        iColor2=new JMenuItem("Black");
        iColor2.addActionListener(this);
        iColor2.setActionCommand("Black");
        menuColour.add(iColor2);

        iColor3=new JMenuItem("Blue");
        iColor3.addActionListener(this);
        iColor3.setActionCommand("Blue");
        menuColour.add(iColor3);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "New":
                // Assuming file is an object of Function_File and newfile is a method
                File.newfile();
                break;
            case "Open":
                File.open();
                break;
            case "SaveAs":
                File.SaveAs();
                break;
            case "Save":
                File.Save();
                break;
            case "Exit":
                File.Exit();
                break;
            case "Undo": Edit.undo();
             break;
             case "Redo": Edit.redo();
             break;
            case "Word Wrap":
                formate.wordWrap();
                break;
            case "Arial":
                formate.setFont(command);
                break;
            case "Comic Sans MS":
                formate.setFont(command);
                break;
            case "Times New Roman":
                formate.setFont(command);
                break;
            case "size8":
                formate.createFont(8);
                break;
            case "size12":
                formate.createFont(12);
                break;
            case "size14":
                formate.createFont(14);
                break;
            case "size16":
                formate.createFont(16);
                break;
            case "size120":
                formate.createFont(20);
                break;
            case "size24":
                formate.createFont(24);
                break;
            case "White":
                color.changeColor(command);
                break;
            case "Black":
                color.changeColor(command);
                break;
            case "Blue":
                color.changeColor(command);
                break;    

        }
    }
   
}
