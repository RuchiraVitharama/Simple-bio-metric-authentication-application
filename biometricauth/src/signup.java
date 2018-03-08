import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class signup  {
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton Submit;
    public JPanel signuppane;
    public ArrayList<Double> timelist;
    public ArrayList<Character> charlist;
    public signup() {
        timelist=new ArrayList<Double>();
        charlist=new ArrayList<Character>();
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

        Submit.addActionListener(new ActionListener() {
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
                try {
                    JDBCconnection.insertqry(ID,password,timeseq);
                    System.out.println("data inserted");
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}
