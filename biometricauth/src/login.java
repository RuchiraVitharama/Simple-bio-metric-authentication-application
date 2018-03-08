import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class login {
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton login;
    public JPanel loginpane;
    public ArrayList<Double> timelist;
    public ArrayList<Character> charlist;

    public login() {
        timelist=new ArrayList<Double>();
        charlist=new ArrayList<Character>();
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ID=textField1.getText();
                String password="";
                for(char d:charlist){password=password+d;}
                String timeseq="";
                double temp=0;
                for(double d:timelist){
                    System.out.println(d);
                    if(temp!=0){
                        double gap=d-temp;
                        timeseq=timeseq+Double.toString(gap)+",";

                    }
                    temp=d;
                }
                int state=JDBCconnection.extract(ID,password,timeseq);
                if(state==0){JOptionPane.showInternalMessageDialog(loginpane,"Incorrect user name password combination");}
                else if(state==3 || state==4){JOptionPane.showInternalMessageDialog(loginpane,"Please insert passwoed again:"+state);}
                else if(state==1){JOptionPane.showInternalMessageDialog(loginpane,"Welcome "+ID);}
                timelist=new ArrayList<Double>();

            }
        });
        passwordField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int a=e.getKeyCode();
                char c=e.getKeyChar();
                charlist.add(c);
            }
        });
        passwordField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                Double time = new Double(System.currentTimeMillis());
                timelist.add(time);
            }
        });
    }
}
