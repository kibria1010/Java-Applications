package notepad1;


import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class NotePad1 implements WindowListener, ActionListener {

    Frame f;
    Panel p;
    TextArea ta;
    Button[] b;
    Label l;
    FileDialog fd, fd1;
    ScrollPane scrol = new ScrollPane(0);

    public NotePad1() {
        f = new Frame("Calculator");
        p = new Panel();
        ta = new TextArea("Txt Area");
        b = new Button[10];
        l = new Label("NotePad Application", Label.CENTER);
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
        f.setBounds(800, 10, 500, 400);
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
                    p.add(b[i] = new Button("spaleCheack"));
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
        NotePad1 c = new NotePad1();
        c.all();
    }

    public void windowOpened(WindowEvent we) {}
    public void windowClosing(WindowEvent we) {
        System.exit(0);
    }
    public void windowClosed(WindowEvent we) {}
    public void windowIconified(WindowEvent we) {}
    public void windowDeiconified(WindowEvent we) {}
    public void windowActivated(WindowEvent we) {}
    public void windowDeactivated(WindowEvent we) {}

}
