package notepad2;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class NotePad2 implements WindowListener, ActionListener {

    Frame f, f1;
    Panel p, p1;
    TextArea ta, taa;
    Button[] b;
    Label l, l1;
    FileDialog fd, fd1;
    ScrollPane scrol = new ScrollPane(0);

    public NotePad2() {
        f = new Frame("NotePade");
        f1 = new Frame("searched from dictionary");
        p = new Panel();
        p1 = new Panel();
        ta = new TextArea("");
        taa = new TextArea("");
        b = new Button[10];
        l = new Label("NotePad Application", Label.CENTER);
        l1 = new Label("what u want to search", Label.CENTER);
        fd = new FileDialog(fd);
        fd1 = new FileDialog(fd1);
    }

    private void all() {
        frame();
        penel();
        textArea();
        button();
        label();
    }

    public void frame() {
        f.setVisible(true);
        f.setBounds(80, 10, 1000, 600);
        f.setBackground(Color.WHITE);
        f.setLayout(new BorderLayout(0, 5));
        f.addWindowListener(this);
    }
    
    void penel(){
        p.setLayout(new GridLayout(1, 6, 10, 0));
        p.setBackground(Color.LIGHT_GRAY);
        f.add(p, BorderLayout.NORTH);
    }
    
    void button(){
        for (int i = 0; i < b.length; i++) {
            switch (i) {
                case 0:
                    p.add(b[i] = new Button("clear"));
                    break;
                case 1:
                    p.add(b[i] = new Button("save"));
                    break;
                case 2:
                    p.add(b[i] = new Button("open"));
                    break;
                case 3:
                    p.add(b[i] = new Button("search"));
                    break;
                case 4:
                    p.add(b[i] = new Button("exit"));
                    break;
                case 5:
                    default:
                        break;
            }
            if (i < 4) {
                b[i].setBackground(Color.DARK_GRAY);
                b[i].setForeground(Color.WHITE);
                b[i].setActionCommand("" + i);
                b[i].addActionListener(this);
            } else if(i==4){
                b[i].setBackground(Color.DARK_GRAY);
                b[i].setForeground(Color.RED);
                b[i].setActionCommand("" + i);
                b[i].addActionListener(this);
            }
        }
    }
    
    public void textArea() {
        f.add(ta);
        ta.setBackground(Color.DARK_GRAY);
        ta.setForeground(Color.WHITE);
        f.addWindowListener(this);  
    }

    public void label() {
        l.setBackground(Color.DARK_GRAY);
        f.setForeground(Color.LIGHT_GRAY);
        f.add(l, BorderLayout.PAGE_END);
    }
    
    public void actionPerformed(ActionEvent ae) {
            switch (ae.getActionCommand()) {
                case "0":
                    ta.setText("");
                    break;
                case "1":
                    String s="55555", s2, directory, s8;
                    String[] s1;
                    s1 = ta.getText().split("\\n");
                    fd1.setVisible(true);
                    File file1 = new File(""+fd1.getDirectory()+"", ""+fd1.getFile()+"");
                    try {
                        PrintWriter out = new PrintWriter(new FileWriter(file1));
                        for (int j = 0; j < s1.length; j++) {
                            out.print(s1[j]);
                            out.println();
                        }
                        out.close();
                        
                    } catch (Exception e) {
                        System.out.println(""+e);
                    }
                    break;
                case "2":
                    String s3, s4="", s5;
                    fd.setVisible(true);
                    File file = new File(""+fd.getDirectory()+"", ""+fd.getFile()+"");
                    try {
                        
                        BufferedReader in = new BufferedReader(new FileReader(file));
                        
                        s3 = in.readLine();
                        while (s3 != null) {                            
                            s4 = s4+s3+"\n";
                            s3 = in.readLine();
                        }
                        in.close();
                        ta.setText(s4);
                        
                    } catch (Exception e) {
                        System.out.println(""+e);
                    }
                    break;
                case "3":
                    fd.setVisible(true);
                    f1.setVisible(true);
                    f1.setBounds(590, 10, 200, 200);
                    f1.setBackground(Color.WHITE);
                    f1.setLayout(new BorderLayout(0, 5));
                    f1.addWindowListener(this);
                    p1.setLayout(new GridLayout(1, 6, 10, 0));
                    p1.setBackground(Color.LIGHT_GRAY);
                    f1.add(p1, BorderLayout.NORTH);
                    //f1.add(p);
                    f1.add(taa);
                    taa.setBackground(Color.DARK_GRAY);
                    taa.setForeground(Color.WHITE);
                    
                    
                    
                    break;
                case "4":
                    System.exit(0);
                    break;
                case "5":
                default:
                    break;
            }
    }


    
    
    
    public static void main(String[] args) {
        NotePad2 c = new NotePad2();
        c.all();
    }
    
    

    public void windowOpened(WindowEvent we) {}
    public void windowClosing(WindowEvent we) {
        if (!we.getComponent().equals(f1)) {
            System.exit(0);
        }
        else {
            f1.setVisible(false);
        }
        
    }
    public void windowClosed(WindowEvent we) {}
    public void windowIconified(WindowEvent we) {}
    public void windowDeiconified(WindowEvent we) {}
    public void windowActivated(WindowEvent we) {}
    public void windowDeactivated(WindowEvent we) {}

}
