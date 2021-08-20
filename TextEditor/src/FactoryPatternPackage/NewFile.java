package FactoryPatternPackage;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class NewFile implements FileOperations{
    
    @Override
    public void fileWorks(JTextArea textArea, String fileName,JFrame frame) {
        textArea.setText("");
        frame.setTitle("New File");
    }
}
