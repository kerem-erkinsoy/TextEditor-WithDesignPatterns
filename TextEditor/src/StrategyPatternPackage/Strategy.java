package StrategyPatternPackage;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public interface Strategy {
    
    public void doOperation(JTextArea textArea, String fileName,JFrame frame);
    
}
