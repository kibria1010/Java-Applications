package gam;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RaceToSelect implements ActionListener {

    public Frame f;
    public Panel[] panel;
    Thread thread;
    Label label;
    JButton b[];
    JLabel point_player_1[];
    JLabel point_player_2[];
    int flag_to_peek_player = 0;
    int count_player1 = 0;
    int count_player2 = 0;
    int punchFlag;

    public RaceToSelect() {
        f = new Frame("Race To Select");
        panel = new Panel[10];
        thread = new Thread();
        label = new Label("Race To Select");
    }

    public static void main(String[] args) throws Exception {
        RaceToSelect g = new RaceToSelect();
        g.frame();
        g.panel();
        g.addImageCirculary();
    }

    void frame() {
        f.setVisible(true);
        f.setBounds(100, 20, 1000, 1000);
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                f.dispose();
            }
        });
    }

    void panel() {
        for (int i = 0; i < 10; i++) {
            panel[i] = new Panel();
            int r, g, b;
            r = (int) (10 + Math.random() * 100);
            g = (int) (10 + Math.random() * 100);
            b = (int) (10 + Math.random() * 100);
            Color c = new Color(r, g, b);
            panel[i].setBackground(c);
            //panel[i].setBounds(0, 0, 150, 150);
            panel[i].setLayout(new BorderLayout());
        }

        f.setLayout(new GridLayout(3, 0));
        f.add(panel[0]);
        f.add(panel[1]);
        f.add(panel[2]);

        label.setBackground(panel[0].getBackground().brighter());
        label.setAlignment(1);
        label.setFont(new Font("Ajaira", 30, 30));

        //panel[0].setLayout(new GridLayout(3, 0));
        panel[0].add(label, BorderLayout.BEFORE_FIRST_LINE);
        panel[0].add(panel[3], BorderLayout.CENTER);
        panel[3].setBackground(panel[0].getBackground().darker());

        panel[1].setLayout(new GridBagLayout());
        panel[1].add(panel[4]);
        panel[1].add(panel[5]);
        panel[1].add(panel[6]);

        panel[2].setLayout(new GridLayout(0, 2));
        panel[2].add(panel[7]);
        panel[2].add(panel[8]);

        panel[7].setLayout(new GridLayout(3, 4, 0, 0));
        panel[8].setLayout(new GridLayout(3, 4, 0, 0));
        point_player_1 = new JLabel[100];
        point_player_2 = new JLabel[100];

        for (int i = 1; i <= 17; i++) {
            point_player_1[i] = new JLabel();
            if (i > 12) {
                point_player_1[i].setIcon(new ImageIcon(this.getClass().getResource("point_player1/" + "" + i + "" + ".JPG")));
            } else {
                point_player_1[i].setIcon(new ImageIcon(this.getClass().getResource("point_player1/" + "" + i + "" + ".JPG")));
                panel[7].add(point_player_1[i]);
            }
            
            point_player_2[i] = new JLabel();
            if (i > 12) {
                point_player_2[i].setIcon(new ImageIcon(this.getClass().getResource("point_player2/" + "" + i + "" + ".JPG")));
            } else {
                point_player_2[i].setIcon(new ImageIcon(this.getClass().getResource("point_player2/" + "" + i + "" + ".JPG")));
                panel[8].add(point_player_2[i]);
            }

            point_player_1[i].setVisible(false);
            point_player_2[i].setVisible(false);
        }

    }

    private void addImageCirculary() throws Exception {
        b = new JButton[101];
        for (int i = 0; i <= 29; i++) {
            b[i] = new JButton();
            b[i].setIcon(new ImageIcon(this.getClass().getResource("image/" + "" + i + "" + ".JPG")));

            if (i <= 23) {
                b[i].addActionListener(this);
                b[i].setActionCommand(i + "");
            }
        }
        this.panel[4].add(b[24]);
        this.panel[5].add(b[25]);
        this.panel[6].add(b[26]);

        makePunch(b);

        int i = 0;
        b[0].setVisible(true);
        while (true) {

            if (i == 0) {
                b[23].setVisible(false);
                i = 0;
            } else {
                b[i - 1].setVisible(false);
            }

            if (count_player1 == 12 || count_player2 == 12) {
                break;
            }

            this.panel[5].add(b[i], BorderLayout.CENTER);

            if (i == 23) {
                b[0].setVisible(true);
                i = -1;
            } else {
                b[i + 1].setVisible(true);
            }
            Thread.sleep(100);
            i++;
        }
        
        for (int j = 0; j <= 23; j++) {
            this.panel[5].remove(b[i]);
        }
        if (count_player1 == 12) {
            gameOver(point_player_1);
        } else if (count_player2 == 12) {
            gameOver(point_player_2);
        }
    }

    public void gameOver(JLabel[] label) throws InterruptedException {
        int i = 13;
        label[i].setVisible(true);
        while (true) {
            if (i == 13) {
                label[17].setVisible(false);

            } else {
                label[i - 1].setVisible(false);
            }

            panel[5].add(label[i], BorderLayout.CENTER);

            if (i == 17) {
                label[13].setVisible(true);
                i = 12;
            } else {
                label[i + 1].setVisible(true);
            }
            Thread.sleep(100);
            i++;
        }

    }

    private void makePunch(JButton[] b) throws InterruptedException {
        b[25].addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                punchFlag = 1;
            }
        });

        b[28].addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                punchFlag = 1;
            }
        });

        int i = 0;
        while (true) {
            if (i % 2 == 1) {
                b[28].setVisible(false);
                panel[5].add(b[25], BorderLayout.CENTER);
                b[28].setVisible(true);
            } else {
                b[25].setVisible(false);
                panel[5].add(b[28], BorderLayout.CENTER);
                b[25].setVisible(true);
            }
            i++;
            Thread.sleep(50);

            if (punchFlag == 1) {
                insurtImagePlayer1();
                panel[5].remove(b[25]);
                panel[5].remove(b[28]);

                break;
            }
        }

    }

    void insurtImagePlayer1() throws InterruptedException {
        b[24].addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                FileDialog fd = null;
                fd = new FileDialog(fd);
                fd.setVisible(true);
                JLabel l = new JLabel(new ImageIcon(fd.getDirectory() + fd.getFile()));
                panel[4].remove(b[24]);
                panel[4].remove(b[27]);
                panel[4].add(l);

                punchFlag = 2;
            }
        });

        b[27].addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                FileDialog fd = null;
                fd = new FileDialog(fd);
                fd.setVisible(true);
                JLabel l = new JLabel(new ImageIcon(fd.getDirectory() + fd.getFile()));

                panel[4].remove(b[24]);
                panel[4].remove(b[27]);
                panel[4].add(l);
                punchFlag = 2;
            }
        });

        int i = 0;
        while (true) {
            if (i % 2 == 1) {
                b[24].setVisible(false);
                panel[4].add(b[27], BorderLayout.CENTER);
                b[24].setVisible(true);
            } else {
                b[27].setVisible(false);
                panel[4].add(b[24], BorderLayout.CENTER);
                b[27].setVisible(true);
            }
            i++;
            Thread.sleep(50);

            if (punchFlag == 2) {
                insurtImagePlayer2();
                panel[5].remove(b[25]);
                panel[5].remove(b[28]);

                break;
            }

        }

    }

    void insurtImagePlayer2() throws InterruptedException {
        b[26].addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                FileDialog fd = null;
                fd = new FileDialog(fd);
                fd.setVisible(true);
                JLabel l = new JLabel(new ImageIcon(fd.getDirectory() + fd.getFile()));
                panel[6].remove(b[26]);
                panel[6].remove(b[29]);
                panel[6].add(l);

                punchFlag = 3;
            }
        });

        b[29].addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                FileDialog fd = null;
                fd = new FileDialog(fd);
                fd.setVisible(true);
                JLabel l = new JLabel(new ImageIcon(fd.getDirectory() + fd.getFile()));
                panel[6].remove(b[26]);
                panel[6].remove(b[29]);
                panel[6].add(l);
                punchFlag = 3;
            }
        });

        int i = 0;
        while (true) {
            if (i % 2 == 1) {
                b[26].setVisible(false);
                panel[6].add(b[29], BorderLayout.CENTER);
                b[26].setVisible(true);
            } else {
                b[29].setVisible(false);
                panel[6].add(b[26], BorderLayout.CENTER);
                b[29].setVisible(true);
            }
            i++;
            Thread.sleep(50);

            if (punchFlag == 3) {
                panel[5].remove(b[25]);
                panel[5].remove(b[28]);

                break;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        int temp;
        temp = Integer.parseInt(ae.getActionCommand());
        b[temp].addActionListener(null);
        if (temp != 0) {
            if (flag_to_peek_player++ % 2 == 0) {

                if (temp <= 12) {
                    for (int i = 1; i <= 12; i++) {
                        if (temp == i && !point_player_1[temp].isVisible()) {
                            point_player_1[temp].setVisible(true);
                            count_player1++;
                            break;
                        }
                    }
                } else {
                    int j = 11;
                    for (int i = 13; i <= 23; i++, j--) {
                        if (temp == i && !point_player_1[j].isVisible()) {
                            point_player_1[j].setVisible(true);
                            count_player1++;
                            break;
                        }
                    }

                }
            } else {
                if (temp <= 12) {
                    for (int i = 1; i <= 12; i++) {
                        if (temp == i && !point_player_2[temp].isVisible()) {
                            point_player_2[temp].setVisible(true);
                            count_player2++;
                            break;
                        }
                    }
                } else {
                    int j = 11;
                    for (int i = 13; i <= 23; i++, j--) {
                        if (temp == i && !point_player_2[j].isVisible()) {
                            point_player_2[j].setVisible(true);
                            count_player2++;
                            break;
                        }
                    }

                }

            }
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {

        }
    }
}
