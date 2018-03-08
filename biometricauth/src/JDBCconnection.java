import java.sql.*;
import java.util.ArrayList;

public class JDBCconnection {
    private  boolean initiate;
    private  static Connection conn;

    static {
        try {
            conn = getconnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JDBCconnection() throws Exception {
        initiate=false;

    }


    public  static Connection getconnection() throws Exception {
        Connection conn;
        conn = null;

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:test2.db");
            System.out.println("db connected");
            String sql = "CREATE TABLE IF NOT EXISTS users (\n"
                    + "	id VARCHAR(50) PRIMARY KEY,\n"
                    + "	password VARCHAR(50) NOT NULL,\n"
                    + "	timeseq VARCHAR(500)\n"
                    + ");";
            Statement stmt = conn.createStatement();
            // create a new table
            stmt.execute(sql);
            System.out.println("table created");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;

    }

    public  static void insertqry(String equer1,String equery2,String equery3) throws Exception {
        String insertTableSQL = "INSERT INTO users"
                + "(id, password, timeseq) VALUES"
                + "(?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(insertTableSQL);
        preparedStatement.setString(1, equer1);
        preparedStatement.setString(2, equery2);
        preparedStatement.setString(3, equery3);

        preparedStatement.executeUpdate();
    }

    public  static  int extract(String q1,String q2,String q3){
        int state=0;
        try {
            Statement myst = conn.createStatement();
            String newtimeseq="";
            ResultSet myrs= myst.executeQuery("select* from users");
            while(myrs.next()){
                if(myrs.getString("id").equals(q1) && myrs.getString("password").equals(q2)){
                    System.out.println("user and ID found");
                    newtimeseq=myrs.getString("timeseq");
                    state=1;
                    break;
                }


            }
            if(state==1){

                ArrayList<Double> list2=new ArrayList<Double>();
                String times=newtimeseq;
                System.out.println("times:"+times);
                ArrayList<String> lists = getlist(times);
                for(String s:lists){
                    System.out.print(s);
                    double d=Double.parseDouble(s);
                    list2.add(d);
                }
                ArrayList<Double> list3=new ArrayList<Double>();
                String Times=q3;
                ArrayList<String> listss = getlist(q3);
                System.out.println("double list");
                for(String s:listss){
                    double d=Double.parseDouble(s);
                    list3.add(d);

                    System.out.println(d);
                }
                int n=list2.size();
                if(n!=list3.size()){state =3;}

                else{

                    for(int m=0;m<n ;m++){
                        if((list2.get(m)-list3.get(m))>200 || (list3.get(m)-list2.get(m))>200){
                            state=4;
                            break;
                        }
                    }

                }
            }
            return state;
        } catch (SQLException e) {
            e.printStackTrace();
            state=10;
        }
        return state;
    }

    public static ArrayList<String> getlist(String s){
        ArrayList<String> rlist=new ArrayList<String>();
        String word="";
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c!=",".charAt(0)){
                word=word+c;
            }
            else{
                rlist.add(word);
                word="";
            }

        }
        return rlist;
    }

}
