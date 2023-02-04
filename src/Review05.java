import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Review05 {

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String selectSql = "SELECT * FROM person WHERE id = ?";

            try ( // try-with-resources
                    Connection con = DriverManager.getConnection(
                            "jdbc:mysql://localhost/kadaidb?useSSL=false&allowPublicKeyRetrieval=true", "root",
                            "Kwnk123_");
                    PreparedStatement pstmt = con.prepareStatement(selectSql)) {
                System.out.print("検索キーワードを入力してください > ");
                int num = keyInNum();
                pstmt.setInt(1, num);
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        String name = rs.getString("name");
                        int age = rs.getInt("age");
                        System.out.println(name + "\n" + age);
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Class ERROR");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("SQL ERROR");
            e.printStackTrace();
        }
    }

    private static int keyInNum() {
        int result = 0;
        try {
            BufferedReader key = new BufferedReader(new InputStreamReader(System.in));
            String line = key.readLine();
            result = Integer.parseInt(line);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return result;
    }

}
