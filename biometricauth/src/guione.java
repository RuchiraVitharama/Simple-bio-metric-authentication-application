import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class guione {
    private JButton Login;
    private JButton Signup;
    private JPanel panel1;





    public static void main(String[] args) throws Exception{
        JFrame frame = new JFrame("guione");
        frame.setContentPane(new guione().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }



    public guione() throws Exception{



        Signup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signup guisignup = new signup();
                JFrame frame = new JFrame();

                //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setContentPane(guisignup.signuppane);
                //frame.setSize(1000,1000);
                frame.pack();
                frame.setVisible(true);
            }
        });

        Login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login guilogin = new login();
                JFrame frame = new JFrame();

                //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setContentPane(guilogin.loginpane);
                //frame.setSize(1000,1000);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}
