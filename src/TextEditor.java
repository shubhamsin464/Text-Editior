import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    JFrame frame;

    JMenuBar MenuBar;

    JMenu file,edit;

    //File MenuItems
    JMenuItem newFile,openFile,saveFile;

    //edit menu Items
    JMenuItem cut, copy,paste,selectAll,close;

    JTextArea jTextArea;

    TextEditor(){
        //Intialize the frame
         frame = new JFrame();
        //Intialize the menu bar
        MenuBar = new JMenuBar();
        //Intialize text area
        jTextArea = new JTextArea();
        //Intialize menus
        file = new JMenu("File");
        edit = new JMenu("Edit");

        //Intialize File menu items
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");
        //Adding Action Listener to file menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        //Add menu items to file
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //Intialize Edit Menu Items;
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("select All");
        close = new JMenuItem("close");
        //Adding action Listener in edit Menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);
        //Add edit menu items to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        //Add menus to menubar
        MenuBar.add(file);
        MenuBar.add(edit);

       // set menubar to frame
        frame.setJMenuBar(MenuBar);
        //Create content pane
        JPanel panel= new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        //Add text area to panel
        panel.add(jTextArea,BorderLayout.CENTER);
        //Create Scroll Pane
        JScrollPane scrollPane = new JScrollPane(jTextArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        // Add Scroll pane to panel
        panel.add(scrollPane);
        //Add panel to frame
        frame.add(panel);
//        //adding text area to frame
//        frame.add(jTextArea);
          //Setting the frame
          frame.setBounds(100,100,400,400);
          frame.setTitle("Text Editor");
          frame.setVisible(true);
          frame.setLayout(null);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource()==cut){
             jTextArea.cut();
           }
        if(actionEvent.getSource()==copy){
             jTextArea.copy();
        }
        if(actionEvent.getSource()==paste){
             jTextArea.paste();
        }
        if(actionEvent.getSource()==selectAll){
             jTextArea.selectAll();
        }
        if(actionEvent.getSource()==close){
            System.exit(0);
        }
        if(actionEvent.getSource()==openFile){
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);
            //If we have clicked the open button
            if(chooseOption==fileChooser.APPROVE_OPTION){
                //Getting selected file
                File file = fileChooser.getSelectedFile();
                //Getting the path of selected file
                String filePath = file.getPath();
                try{
                    //Initialize file reader
                    FileReader fileReader = new FileReader(filePath);
                    //Initialize Buffered Reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "",output="";
                    while((intermediate =bufferedReader.readLine())!=null){
                        output+=intermediate+"\n";
                    }
                    jTextArea.setText(output);
                }
                catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource()==saveFile){
            //Choose the file
            JFileChooser fileChooser = new JFileChooser();
            //Get Choose option from file chooser
            int chooseOption = fileChooser.showSaveDialog(null);
            //Check if we clicked on save
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                //Create a new file directory with the choosen file
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                    //Intialize fileWriter
                    FileWriter fileWriter = new FileWriter(file);
                    //Intialize Bufferd Writer
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    //write contents of text area to a file
                    jTextArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource()==newFile){
            TextEditor newTextEditor = new TextEditor();
        }
    }
    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
        System.out.println("Hello world!");
    }
}