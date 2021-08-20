package StrategyPatternPackage;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class FileNew implements Strategy{

    @Override
    public void doOperation(JTextArea textArea, String fileName,JFrame frame) {
        textArea.setText("");
        frame.setTitle("New File");
    }
    
    
}
