package StrategyPatternPackage;

import java.awt.FileDialog;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.Document;
import javax.swing.undo.UndoManager;


public class NotepadEditor extends JFrame{
    
    private UndoManager undo;
    private String fileName ; // Açılan kaydedilen ya da oluşturulan dosya için string
    private ArrayList<String> wordList; // words.txt'teki sözlük
    
    // Constructor
    
    public NotepadEditor() {
        undo = new UndoManager();
        wordList = new ArrayList<String>();
        initComponents();
        startTitle();
        readWordList();
        addListener();
        setLocation(650, 100);
    }
    
    // Textarea'ya bir listener ekliyoruz.
    private void addListener(){
        Document doc = textArea.getDocument();
        doc.addUndoableEditListener(new UndoableEditListener() {
            public void undoableEditHappened(UndoableEditEvent evt) {
                undo.addEdit(evt.getEdit());
            }
        });
    }
    
    // Programın başlangıçtaki başlığı.
    private void startTitle(){
        setTitle("New File");
    }
    
    // Verilen words.txt içeriğini okumak için bir metot.
    private ArrayList<String> readWordList(){
        try {
            Scanner scanner = new Scanner(new FileInputStream("words.txt"));
        
            while (scanner.hasNextLine()) {             
                String temp=scanner.nextLine();
                wordList.add(temp);
            }
            return wordList;
        } 
        catch (FileNotFoundException ex) {
            Logger.getLogger(NotepadEditor.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    // Editore yazilmis olan texti yazım açısından kontrol etmek icin bir metot.
    private void textChecker(String content){
        content=content.toLowerCase();
         /* wrongWordList hem sözlükte olmayan hem de single transposition
         kuralına uymaayan yani tamamen yanlış kelimeleri tutmak için bir list.*/
        ArrayList<String> wrongWordList = new ArrayList<String>();
        
        // SingleTransposition ile eşleşen kelimeleri tutmak için bir list.
        ArrayList<String> similarWordList = new ArrayList<String>();
        
        /* Proje yönergesinde sadece kelimelerin kontrolu yapılacak diğerleri
            doğru kabul edilecek diye belirtildiği için noktalama işaretleri ve 
            sayılar ayrılıyor geriye sadece kelimeler kalıyor. */
        String [] splitContent = content.split("[\\p{Punct}\\s\\d]+");
        
        // Single Transposition olup olmadığına bakmak için o sınıftan bir nesne oluşturuluyor.
        SingleTransposition singleTransposition = new SingleTransposition();
        
        for (int i = 0; i < splitContent.length; i++) {
            
            /* metnin içindeki kelimelerin wordlistte olup olmadığını kontrol eden
            değişkenler. */
            boolean isExist=false; // sözlükte olup olmadığını tutan bir boolean.
            boolean isSimilar=false; // single t.'a uyup uymadığını tutan boolean.
            // benzer kelimeyi geçici tutmak için bir değişken.
            String similarWord=null;
            
            
            // Listİterator kullanılarak koleksiyon içinde gezinti sağlandı.
            ListIterator listIterator = wordList.listIterator();
            
            while(listIterator.hasNext()){
                // Koleksiyon içindeki bir sonraki elemana geçiliyor.
                String dictWord = (String) listIterator.next();
                if(splitContent[i].equals(dictWord)){
                   content=content.replaceAll(splitContent[i], dictWord);
                    isSimilar=false;
                    isExist=true;
                    break;
                }
                else if (singleTransposition.findSimilar(splitContent[i],dictWord)) {
                    // Single transposition kontrolu yapılıyor.
                        
                    //System.out.println("yanlışı : " + splitContent[i] + " doğrusu: " + wordList.get(j));
                    similarWord=dictWord;
                    isSimilar=true;
                    // düzeltilmiş hali ya da direkt kelimenin kendi varsa isExist = true oluyor.
                    isExist=true;
                }
            }
            
            if(isSimilar){
                /* kontrol edilen kelime transposition'a uymuş ise 
                    yanlışıyla doğrusunu yer değiştiriyoruz.*/
                content=content.replaceAll(splitContent[i], similarWord);
                similarWordList.add(splitContent[i]);
            }
                
            if(!isExist){
                // eğer st'ye uymuyor ve kelime sözlükte yoksa 
                // o kelime yanlış olarak kabul ediliyor
                //System.out.println("işte yanlış olan kelime " + splitContent[i]);
                wrongWordList.add(splitContent[i]);
            }
        }
        // Kontrol edilmiş metin artık textarea'ya geçirilebilir haldedir.
        textArea.setText(content);
        
        // Eğer kelime ST olmuşsa düzeltilen kelime fosforlanıyor.
        
        System.out.println("Yanlış kelimeler: ");
        System.out.println("-------------------");
        Iterator wrongWordIterator = wrongWordList.iterator();
        while(wrongWordIterator.hasNext()){
            System.out.println("+ " + wrongWordIterator.next());
        }
        System.out.println();
        System.out.println("Düzeltilen kelimeler: ");
        System.out.println("++++++++++++++++++++++");
        Iterator fixedWordIterator = similarWordList.iterator();
        while(fixedWordIterator.hasNext()){
            System.out.println("+ " + fixedWordIterator.next());
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        changeFrame = new javax.swing.JFrame();
        jLabel1 = new javax.swing.JLabel();
        findWord = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        replaceWord = new javax.swing.JTextField();
        findButton = new javax.swing.JButton();
        changeButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileBar = new javax.swing.JMenu();
        newFile = new javax.swing.JMenuItem();
        openFile = new javax.swing.JMenuItem();
        saveFile = new javax.swing.JMenuItem();
        closeFile = new javax.swing.JMenuItem();
        editBar = new javax.swing.JMenu();
        undoButton = new javax.swing.JMenuItem();
        removeHlightBtn = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        checkTextBtn = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        findWordBtn = new javax.swing.JMenuItem();

        changeFrame.setTitle("Find and Replace");
        changeFrame.setMinimumSize(new java.awt.Dimension(403, 250));

        jLabel1.setText("Aranacak Kelime: ");

        findWord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findWordActionPerformed(evt);
            }
        });

        jLabel2.setText("Yerine yazılacak kelime:");

        replaceWord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                replaceWordActionPerformed(evt);
            }
        });

        findButton.setText("Find");
        findButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findButtonActionPerformed(evt);
            }
        });

        changeButton.setText("Replace");
        changeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout changeFrameLayout = new javax.swing.GroupLayout(changeFrame.getContentPane());
        changeFrame.getContentPane().setLayout(changeFrameLayout);
        changeFrameLayout.setHorizontalGroup(
            changeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(changeFrameLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(changeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, changeFrameLayout.createSequentialGroup()
                        .addComponent(findButton)
                        .addGap(15, 15, 15)))
                .addGap(63, 63, 63)
                .addGroup(changeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(changeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(findWord)
                        .addComponent(replaceWord, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE))
                    .addComponent(changeButton))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        changeFrameLayout.setVerticalGroup(
            changeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(changeFrameLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(changeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(findWord, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(changeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(replaceWord, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(changeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(findButton)
                    .addComponent(changeButton))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        textArea.setColumns(20);
        textArea.setRows(5);
        textArea.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        textArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textAreaKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(textArea);
        Document doc = textArea.getDocument();
        final UndoManager undo = new UndoManager();
        doc.addUndoableEditListener(new UndoableEditListener() {
            public void undoableEditHappened(UndoableEditEvent evt) {
                undo.addEdit(evt.getEdit());
            }
        });

        /*textArea.getActionMap().put("Undo", new AbstractAction("Undo") {
            public void actionPerformed(ActionEvent evt) {
                try {
                    if (undo.canUndo()) {
                        undo.undo();
                    }
                } catch (CannotUndoException e) {
                }
            }
        });
        textArea.getInputMap().put(KeyStroke.getKeyStroke("control Z"), "Undo");
        */

        /*textArea.getDocument().addUndoableEditListener(new UndoableEditListener() {
            public void undoableEditHappened(UndoableEditEvent e) {
                undo.addEdit(e.getEdit());
            }
        });
        */

        fileBar.setText("File");
        fileBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileBarActionPerformed(evt);
            }
        });

        newFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        newFile.setText("New File");
        newFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newFileActionPerformed(evt);
            }
        });
        fileBar.add(newFile);

        openFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        openFile.setText("Open File");
        openFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFileActionPerformed(evt);
            }
        });
        fileBar.add(openFile);

        saveFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        saveFile.setText("Save File");
        saveFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveFileActionPerformed(evt);
            }
        });
        fileBar.add(saveFile);

        closeFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        closeFile.setText("Close File");
        closeFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeFileActionPerformed(evt);
            }
        });
        fileBar.add(closeFile);

        jMenuBar1.add(fileBar);

        editBar.setText("Edit");

        undoButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        undoButton.setText("Undo");
        undoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                undoButtonActionPerformed(evt);
            }
        });
        editBar.add(undoButton);

        removeHlightBtn.setText("Remove Highlight");
        removeHlightBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeHlightBtnActionPerformed(evt);
            }
        });
        editBar.add(removeHlightBtn);

        jMenuBar1.add(editBar);

        jMenu2.setText("Check");

        checkTextBtn.setText("Text Check");
        checkTextBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkTextBtnActionPerformed(evt);
            }
        });
        jMenu2.add(checkTextBtn);

        jMenuBar1.add(jMenu2);

        jMenu1.setText("Find");

        findWordBtn.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        findWordBtn.setText("Find Replace Word");
        findWordBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findWordBtnActionPerformed(evt);
            }
        });
        jMenu1.add(findWordBtn);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 751, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 791, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeFileActionPerformed
        // Dosyanın kapatılması için bir metot.
        System.exit(0);
    }//GEN-LAST:event_closeFileActionPerformed

    private void openFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openFileActionPerformed
        // Kullanıcıdan açılacak dosyanın yolunu almak için bir metot.
        // Aşağıda da filedialog ile dosya yolu alınıyor.
        FileDialog fileDialog = new FileDialog(this, "Open Text", FileDialog.LOAD);
        fileDialog.setVisible(true);
        
        if (fileDialog.getFile()!=null) {
            fileName = fileDialog.getDirectory() + fileDialog.getFile();
        }
        
        Context context = new Context(new FileOpen());
        context.executeStrategy(textArea, fileName, this);
    }//GEN-LAST:event_openFileActionPerformed

    private void saveFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveFileActionPerformed
        
        // Dosya kaydetme işlemi yapılıyor.
        FileDialog fileDialog = new FileDialog(this, "Save File", FileDialog.SAVE);
        fileDialog.setVisible(true);
        
        if (fileDialog.getFile()!=null) {
            fileName = fileDialog.getDirectory() + fileDialog.getFile();
        }
        
        Context context= new Context((new FileSave()));
        context.executeStrategy(textArea, fileName, this);
        
        
        
    }//GEN-LAST:event_saveFileActionPerformed

    private void fileBarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileBarActionPerformed
    }//GEN-LAST:event_fileBarActionPerformed

    private void newFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newFileActionPerformed
        
        // Yeni dosya oluşturma işlemi.
        
        Context context = new Context(new FileNew());
        context.executeStrategy(textArea, fileName, this);
        
    }//GEN-LAST:event_newFileActionPerformed

    private void undoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_undoButtonActionPerformed
        Invoker invoker = new Invoker();
        TextDocument textDocument = new TextDocument(textArea, undo);
        TextUndo docUndo = new TextUndo (textDocument);
        invoker.setCommand(docUndo);
        invoker.executeCommand();

    }//GEN-LAST:event_undoButtonActionPerformed

    private void findWordBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findWordBtnActionPerformed
        // Metin içinde kelime arama için ekstra bir pencerede işlemler yapılacak 
        changeFrame.setVisible(true);
        changeFrame.setLocation(800,200);
        changeFrame.setSize(600,700);
    }//GEN-LAST:event_findWordBtnActionPerformed

    private void removeHlightBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeHlightBtnActionPerformed
        HighLighterClass.removeHighlights(textArea);
    }//GEN-LAST:event_removeHlightBtnActionPerformed

    private void findWordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findWordActionPerformed
        // Aranacak kelimenin girildiği yer
    }//GEN-LAST:event_findWordActionPerformed

    private void replaceWordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_replaceWordActionPerformed
        // Değiştirilecek olan kelimenin girildiği yer
    }//GEN-LAST:event_replaceWordActionPerformed

    private void findButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findButtonActionPerformed
        // Edittext'in olduğu yerlerin boş bırakılmaması kontrolü
        if (!findWord.getText().equals(null) || !findWord.getText().matches("\\s*") || findWord.getText().equals("")) {
            if (textArea.getText().contains(findWord.getText())) {
                // Eğer aranılan varsa sarı fosforlanıyor.
                
                try {
                    HighLighterClass.highlight(textArea, findWord.getText(),HighLighterClass.findHighlightPainter);
                } 
                catch (Exception ex) {
                    Logger.getLogger(NotepadEditor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                JOptionPane.showMessageDialog(changeFrame,"'"+findWord.getText()+"'  not found!","Alert",JOptionPane.WARNING_MESSAGE);  
            }
        }
        else{
            JOptionPane.showMessageDialog(changeFrame,"Invalid syntax for find text area","Alert",JOptionPane.WARNING_MESSAGE);  
        }
        
    }//GEN-LAST:event_findButtonActionPerformed

    private void changeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeButtonActionPerformed
        
        // Replace işlemi burda yapılıyor.
        
        String text_ici=textArea.getText();
        // Null ve boşluk kontrolü yapılıyor.
        if(findWord.getText().equals("") || findWord.getText().equals(null) || findWord.getText().matches("\\s*") 
                || replaceWord.getText().equals(null) || replaceWord.getText().equals("") || replaceWord.getText().matches("\\s*")) {
            JOptionPane.showMessageDialog(changeFrame,"Kutucuklar boş bırakılamaz!","Alert",JOptionPane.WARNING_MESSAGE); 
        }
        else if (!textArea.getText().contains(findWord.getText())){
            JOptionPane.showMessageDialog(changeFrame,"'"+findWord.getText()+"'  not found!","Alert",JOptionPane.WARNING_MESSAGE); 
        }
        else{
            textArea.setText(text_ici.replaceAll(findWord.getText(), replaceWord.getText()));
        }
        
    }//GEN-LAST:event_changeButtonActionPerformed

    private void textAreaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textAreaKeyTyped
        /* TextArea'ya herhangi bir yazım olduğunda metnin görüntüsünün karışmaması için
        bir tuş basımında higlighterlar kaldırılıyor.*/
        HighLighterClass.removeHighlights(textArea);
    }//GEN-LAST:event_textAreaKeyTyped

    private void checkTextBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkTextBtnActionPerformed
        // Fosforlu alan varsa siliyoruz karışıklık olmaması için
        HighLighterClass.removeHighlights(textArea);
        String content=textArea.getText();
        
        // Metin sözlükteki kelimelere göre test ediliyor.
        textChecker(content);
    }//GEN-LAST:event_checkTextBtnActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NotepadEditor().setVisible(true);
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton changeButton;
    private javax.swing.JFrame changeFrame;
    private javax.swing.JMenuItem checkTextBtn;
    private javax.swing.JMenuItem closeFile;
    private javax.swing.JMenu editBar;
    private javax.swing.JMenu fileBar;
    private javax.swing.JButton findButton;
    private javax.swing.JTextField findWord;
    private javax.swing.JMenuItem findWordBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem newFile;
    private javax.swing.JMenuItem openFile;
    private javax.swing.JMenuItem removeHlightBtn;
    private javax.swing.JTextField replaceWord;
    private javax.swing.JMenuItem saveFile;
    private javax.swing.JTextArea textArea;
    private javax.swing.JMenuItem undoButton;
    // End of variables declaration//GEN-END:variables
}
