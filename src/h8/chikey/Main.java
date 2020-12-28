package h8.chikey;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        String[] word =input.split(" ");
        if(word[0].equals("-d")){
            System.out.println(delete(word));
        }if (word[0].equals("-c")){
            System.out.println(add(word));
        }if (word[0].equals("-u")){
            System.out.println(update(word));
        }
        if (word[0].equals("-o")){
            System.out.println(open(word));
        }
        if (word[0].equals("-help")){
            System.out.println("If an error occurs, make sure that the command is entered correctly:");
            System.out.println("-o id : Show the user with the selected id");
            System.out.println("-u id lastname firstname middlename birthday special curse group: Updating user data with the selected id ");
            System.out.println("-c id lastname firstname middlename birthday special curse group: Create user");
            System.out.println("-d id : delete user with the selected id");}
        else {
            System.out.println("Error, please read the abstract (-help)");
        }
    }
    public static String open(String[]word){
        ConnectionUtil conn = new ConnectionUtil();
        String sql = "select * from users WHERE id="+word[1]+"";
        try {
            Statement statement = conn.getConnection().createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()){
                int id = result.getInt(1);
                String lastname =result.getString(2);
                String firstname =result.getString(3);
                String midlname =result.getString(4);
                String birthday =result.getString(5);
                String special =result.getString(6);
                String curse =result.getString(7);
                String grop =result.getString(8);
                System.out.printf(" ID: "+ id +
                                " lastname: "+lastname+
                                " firstname: "+firstname +
                                " middlename: "+midlname+
                                " birthday: "+birthday+
                                " special: "+special+
                                " curse: "+curse+
                                " group: "+grop +" ");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String res = "Complete";
        return res;
    }
    public static String update(String[]word){
        try {ConnectionUtil conn = new ConnectionUtil();
        String sqlUP ="UPDATE users SET lastname=?,firstname=?,midlname=?,birthday=?,special=?,curse=?,grop=? WHERE id="+word[1]+";";
            PreparedStatement statement = conn.getConnection().prepareStatement(sqlUP);
            statement.setString(1,word[2]);
            statement.setString(2,word[3]);
            statement.setString(3,word[4]);
            statement.setString(4,word[5]);
            statement.setString(5,word[6]);
            statement.setString(6,word[7]);
            statement.setString(7,word[8]);
            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String res ="Complete";
        return res;
    }
    public static String add(String[]word){
        ConnectionUtil conn = new ConnectionUtil();
        try {
            String sqlADD = "INSERT INTO users(lastname,firstname,midlname,birthday,special,curse,grop) VALUE(?,?,?,?,?,?,?)";
            PreparedStatement statement = null;
            statement = conn.getConnection().prepareStatement(sqlADD);
                statement.setString(1,word[1]);
                statement.setString(2,word[2]);
                statement.setString(3,word[3]);
                statement.setString(4,word[4]);
                statement.setString(5,word[5]);
                statement.setString(6,word[6]);
                statement.setString(7,word[7]);
                statement.execute();
        } catch (SQLException throwables) { throwables.printStackTrace(); }
            String result = "Complete";
            return result;
    }
    public static String delete(String[] word){
        try {
            ConnectionUtil conn = new ConnectionUtil();
            String sqlDel = "DELETE FROM users WHERE id="+word[1]+";";
            PreparedStatement statement = conn.getConnection().prepareStatement(sqlDel);
            statement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String result = "Complete";
        return result;
    }
}