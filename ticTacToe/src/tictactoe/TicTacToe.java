package tictactoe;

import java.awt.*;
import java.awt.event.*;

public class TicTacToe implements WindowListener, ActionListener {

    public static int flag = 0, count = 0, flag1 = 0, c = 0, a1 = 0, a2 = 0, a3 = 0;
    public static String player, Player;
    Frame f;
    Panel p1, p2;
    TextArea ta;
    Button[] b1, b2;
    MenuBar mb;
    Menu m1;
    Thread t;

    public TicTacToe() {
        f = new Frame("Tic Tac Toi");
        p1 = new Panel();
        p2 = new Panel();
        ta = new TextArea("hmmmmmmmmmmm");
        b1 = new Button[15];
        b2 = new Button[9];
        mb = new MenuBar();
        m1 = new Menu("File");
        t = new Thread();
    }

    public void all() {
        frame();
        panel();
        setlayout();
        textArea();
        button();
        PanelPage();

        startt();
    }

    void frame() {
        f.setVisible(true);
        f.setBackground(Color.CYAN);
        f.setBounds(800, 20, 400, 400);

        f.addWindowListener(this);

        //mb.add(m1);
        //f.setMenuBar(mb);
    }

    void panel() {
        p1.setBackground(Color.GRAY);
        p2.setBackground(Color.BLUE);
    }

    void setlayout() {
        f.setLayout(new BorderLayout());
        p1.setLayout(new BorderLayout());
        p2.setLayout(new GridLayout(3, 3, 0, 0));
    }

    void textArea() {
        ta.setBackground(Color.black);
        ta.setForeground(Color.WHITE);
    }

    void button() {

        for (int i = 0; i < b1.length; i++) {
            b1[i] = new Button("Tic Tac Toi");
            b1[i].setForeground(Color.white);
            b1[i].setBackground(Color.getHSBColor(200, 200, 200));
        }
        for (int i = 0; i < b2.length; i++) {
            b2[i] = new Button("X");
            b2[i].addActionListener(this);

        }
        b1[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //f.remove(b1[0]);
                p1.add(p2, BorderLayout.CENTER);
                //f.add(ta, BorderLayout.PAGE_END);
                b1[0].setLabel("Play Again..!");
                f.add(b1[0], BorderLayout.PAGE_END);
                for (int i = 0; i < b2.length; i++) {
                    b2[i].setLabel("X");
                    b2[i].setBackground(Color.LIGHT_GRAY);
                    b2[i].setActionCommand("" + i);
                }
                b1[2].setLabel("Tic Tac Toi");
                b1[2].setBackground(Color.GRAY);
                ta.setText("");
                flag = 0;
                count = 0;
            }
        });

    }

    void PanelPage() {

        p1.add(b1[0], BorderLayout.CENTER);
        p1.add(b1[1], BorderLayout.PAGE_START);
        p1.add(b1[2], BorderLayout.PAGE_END);
        p1.add(b1[3], BorderLayout.BEFORE_LINE_BEGINS);
        p1.add(b1[4], BorderLayout.AFTER_LINE_ENDS);

        for (int i = 0; i < b2.length; i++) {
            p2.add(b2[i]);
        }

    }

    void startt() {
        f.add(p1, BorderLayout.CENTER);
        b1[0].setLabel("PLAY...!!!");
        int flag2 = 1, flag3 = 0;
        while (true) {
            int r, g, b;
            r = ((int) (Math.random() * 100));
            g = ((int) (Math.random() * 100));
            b = ((int) (Math.random() * 100));
            Color c = new Color(r, g, b);
            if ((b1[0].getLabel().endsWith("!"))) {
                b1[0].setBackground(c);
            }
            if (flag2 == 1) {
                for (int i = 0; i < b2.length; i++) {
                    if ((b2[i].getLabel().equals("X")) && (!(ta.getText().endsWith("!")))) {
                        b2[i].setBackground(Color.LIGHT_GRAY);
                    }
                }
                flag2 = 0;
            } else {
                for (int i = 0; i < b2.length; i++) {
                    if ((b2[i].getLabel().equals("X")) && (!(ta.getText().endsWith("!")))) {
                        b2[i].setBackground(Color.GRAY);
                    }
                }
                flag2 = 1;
            }
            if (flag3 % 2 == 0) {
                flag3++;
                continue;
            }

            if ((ta.getText().endsWith("!"))) {
                if (flag2 == 0) {
                    if (!(Player.equals(""))) {
                        b2[a1].setBackground(Color.white);
                        b2[a2].setBackground(Color.white);
                        b2[a3].setBackground(Color.white);
                    }
                    b1[2].setBackground(Color.white);
                } else if (Player.equals("p1")) {

                    b2[a1].setBackground(Color.MAGENTA);
                    b2[a2].setBackground(Color.MAGENTA);
                    b2[a3].setBackground(Color.MAGENTA);
                    b1[2].setBackground(Color.MAGENTA);
                } else if (Player.equals("p2")) {
                    b2[a1].setBackground(Color.pink);
                    b2[a2].setBackground(Color.pink);
                    b2[a3].setBackground(Color.pink);
                    b1[2].setBackground(Color.pink);
                } else {
                    b1[2].setBackground(Color.BLUE);
                }
            }

            try {
                t.sleep(500);
            } catch (InterruptedException ex) {

            }
        }
    }

    public static void main(String[] args) {
        TicTacToe p1 = new TicTacToe();
        while (true) {
            p1.all();
        }

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if ((!(ae.getActionCommand().equals("p1") || ae.getActionCommand().equals("p2"))) && ta.getText().equals("")) {
            if (flag % 2 == 0) {
                player = "p1";
                flag = 1;
            } else {
                player = "p2";
                flag = 0;
            }
            count++;
            switch ((ae.getActionCommand())) {
                case "0":
                    if (player.equals("p1")) {
                        b2[0].setBackground(Color.MAGENTA);
                    } else {
                        b2[0].setBackground(Color.PINK);
                    }
                    b2[0].setLabel(player);
                    b2[0].setActionCommand(player);
                    break;
                case "1":
                    if (player.equals("p1")) {
                        b2[1].setBackground(Color.MAGENTA);
                    } else {
                        b2[1].setBackground(Color.PINK);
                    }
                    b2[1].setLabel(player);
                    b2[1].setActionCommand(player);
                    break;
                case "2":
                    if (player.equals("p1")) {
                        b2[2].setBackground(Color.MAGENTA);
                    } else {
                        b2[2].setBackground(Color.PINK);
                    }
                    b2[2].setLabel(player);
                    b2[2].setActionCommand(player);
                    break;
                case "3":
                    if (player.equals("p1")) {
                        b2[3].setBackground(Color.MAGENTA);
                    } else {
                        b2[3].setBackground(Color.PINK);
                    }
                    b2[3].setLabel(player);
                    b2[3].setActionCommand(player);
                    break;
                case "4":
                    if (player.equals("p1")) {
                        b2[4].setBackground(Color.MAGENTA);
                    } else {
                        b2[4].setBackground(Color.PINK);
                    }
                    b2[4].setLabel(player);
                    b2[4].setActionCommand(player);
                    break;
                case "5":
                    if (player.equals("p1")) {
                        b2[5].setBackground(Color.MAGENTA);
                    } else {
                        b2[5].setBackground(Color.PINK);
                    }
                    b2[5].setLabel(player);
                    b2[5].setActionCommand(player);
                    break;
                case "6":
                    if (player.equals("p1")) {
                        b2[6].setBackground(Color.MAGENTA);
                    } else {
                        b2[6].setBackground(Color.PINK);
                    }
                    b2[6].setLabel(player);
                    b2[6].setActionCommand(player);
                    break;
                case "7":
                    if (player.equals("p1")) {
                        b2[7].setBackground(Color.MAGENTA);
                    } else {
                        b2[7].setBackground(Color.PINK);
                    }
                    b2[7].setLabel(player);
                    b2[7].setActionCommand(player);
                    break;
                case "8":
                    if (player.equals("p1")) {
                        b2[8].setBackground(Color.MAGENTA);
                    } else {
                        b2[8].setBackground(Color.PINK);
                    }
                    b2[8].setLabel(player);
                    b2[8].setActionCommand(player);
                    break;
                default:
                    break;
            }
            if (ta.getText().equals("")) {
                cheackWinner(player);
                if ((count % 9 == 0) && ((ta.getText().equals("")))) {
                    ta.setText("Game over...Draw...!");
                    b1[2].setLabel("Game Over...Draw");
                    Player = "";
                } else if (!(ta.getText().equals(""))) {
                    b1[2].setLabel("Game Over..." + Player + " wins");
                }
            }
        }

    }

    void cheackWinner(String Player) {
        this.Player = Player;
        for (int i = 0; i < b2.length; i += 3) {
            if ((b2[i].getLabel().equals(Player)) && (b2[i + 1].getLabel().equals(Player)) && (b2[i + 2].getLabel().equals(Player))) {
                ta.setText(Player + " Win...!");
                c = 1;
                a1 = i;
                a2 = i + 1;
                a3 = i + 2;
            } else if (i == 0) {
                if ((b2[i].getLabel().equals(Player)) && (b2[i + 4].getLabel().equals(Player)) && (b2[i + 8].getLabel().equals(Player))) {
                    ta.setText(Player + " Win...!");
                    c = 1;
                    a1 = i;
                    a2 = i + 4;
                    a3 = i + 8;
                } else if ((b2[2].getLabel().equals(Player)) && (b2[4].getLabel().equals(Player)) && (b2[6].getLabel().equals(Player))) {
                    ta.setText(Player + " Win...!");
                    c = 1;
                    a1 = 2;
                    a2 = 4;
                    a3 = 6;
                } else {
                    for (int j = 0; j < 3; j++) {
                        if ((b2[j].getLabel().equals(Player)) && (b2[j + 3].getLabel().equals(Player)) && (b2[j + 6].getLabel().equals(Player))) {
                            ta.setText(Player + " Win...!");
                            c = 1;
                            a1 = j;
                            a2 = j + 3;
                            a3 = j + 6;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowDeactivated(WindowEvent e) {
    }

}
