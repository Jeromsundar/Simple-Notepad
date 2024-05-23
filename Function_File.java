import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Function_File {
    GUI gui;
    String fileName;
    String fileAddress;

    public Function_File(GUI gui) {
        this.gui = gui;
    }

    public void newfile() {
        gui.textArea.setText("");
        gui.window.setTitle("New");
        fileName=null;
        fileAddress=null;
    }

    public void open() {
        FileDialog fd = new FileDialog(gui.window, "open", FileDialog.LOAD);
        fd.setVisible(true);

        if (fd.getFile() != null) {
            fileName = fd.getFile();
            fileAddress = fd.getDirectory();
            gui.window.setTitle(fileName);
        }
        System.out.println ("File address and file Name");
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileAddress + fileName));
            gui.textArea.setText("");
            String line = null;
            while ((line = br.readLine()) != null) {
                gui.textArea.append(line + "\n");
            }
            br.close();

        } catch (IOException e) {
            System.out.println("File Not Opened!");
            e.printStackTrace();
        }
    }

    public void Save(){
        if(fileName==null){
            SaveAs();
        }
        else{
            try{
                FileWriter fw =new FileWriter(fileAddress + fileName);
                fw.write(gui.textArea.getText());
                gui.window.setTitle(fileName);
                fw.close();
            }catch(Exception e){
                System.out.println("Something Wrong!");
            }
        }
     }
    
    public void SaveAs(){
        FileDialog fd =new FileDialog(gui.window,"Save",FileDialog.SAVE);
        fd.setVisible(true);
        if(fd.getFile()!=null)
        {
            fileName = fd.getFile();
            fileAddress = fd.getDirectory();
            gui.window.setTitle(fileName);
        }
        try{
            FileWriter fw =new FileWriter(fileAddress + fileName);
            fw.write(gui.textArea.getText());
            fw.close();
        }catch(Exception e){
            System.out.println("Something Wrong!");
        }
    }
    public void Exit(){
        System.exit(0);
    }
}
