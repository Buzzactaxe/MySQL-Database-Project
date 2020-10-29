package DB_Connector;

import model.Contact;

import java.awt.*;
import java.sql.*;
import java.util.Scanner;

public class mySQLDao extends Component {
    private static final String user = "root";
    private static final String password = "29021988lv";
    static String dataBaseName = "contactdbtest";
    String query = null;
    static final String driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/"+dataBaseName+"?autoReconnect=true&useSSL=false";

    Connection mySqlConnection = null;

    PreparedStatement pst = null;
    ResultSet dataSet = null;

    private void dB_Connection() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        query = "SELECT * FROM contactdbtest.contactdata";
        mySqlConnection = DriverManager.getConnection(url, user, password);
        pst = mySqlConnection.prepareStatement(query);
    }

    public void addNew(@org.jetbrains.annotations.NotNull Contact c) {
        try {
            //Connection To DB
            dB_Connection();

            pst = mySqlConnection.prepareStatement("insert into contactdata(name,surname,age)values(?,?,?);");
            pst.setString(1, c.getName());
            pst.setString(2, c.getSurname());
            pst.setInt(3, c.getAge());

            //writes the update to Database
            pst.executeUpdate();
            System.out.println("Was added to Database.");


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void readData() {
        try {
            //Connection To DB
            dB_Connection();

            dataSet = pst.executeQuery();

            while (dataSet.next()) {
                System.out.println("----\n유 \n ID: " + dataSet.getInt("id") + " \n Name: " + dataSet.getString("name") + " \n Surname: " + dataSet.getString("surname") + " \n Age: " + dataSet.getInt("age") + " ");
            }

        } catch (SQLException sq) {
            System.out.println("Database error!");
        } catch (ClassNotFoundException ce) {
            System.out.println("Driver Error!");
        } finally {
            try {
                if (dataSet != null) {
                    dataSet.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (mySqlConnection != null) {
                    mySqlConnection.close();
                }
            } catch (SQLException sq) {
                sq.printStackTrace();
            }
        }
    }

    //This was a test to use String it may turn void later
    public void readDataById(String id) {
        try {
            dB_Connection();
            query = "SELECT * FROM contactdata where id=?";
            pst = mySqlConnection.prepareStatement(query);
            pst.setString(1, id);

            //reads requested query from Database
            dataSet = pst.executeQuery();

            while (dataSet.next()) {
                System.out.println("----\n유 \n ID: " + dataSet.getInt("id") + " " +
                        "\n Name: " + dataSet.getString("name") + " " +
                        "\n Surname: " + dataSet.getString("surname") + " " +
                        "\n Age: " + dataSet.getInt("age") + " " + "\n____ ");
            }

        } catch (SQLException sq) {
            System.out.println("Database error!");
        } catch (ClassNotFoundException ce) {
            System.out.println("Driver Error!");
        } finally {
            try {
                if (dataSet != null) {
                    dataSet.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (mySqlConnection != null) {
                    mySqlConnection.close();
                }
            } catch (SQLException sq) {
                sq.printStackTrace();
            }
        }
    }

    //TODO
    public void deleteDataById(Scanner scanner) {
        try {
            dB_Connection();

            query = "DELETE FROM contactdata WHERE id=?";
            pst = mySqlConnection.prepareStatement(query);
            pst.setString(1, scanner.nextLine());
            //reads requested query from Database
            pst.executeUpdate();

            System.out.println("Contact Deleted");

        } catch (SQLException sq) {
            System.out.println("Database error!");
        } catch (ClassNotFoundException ce) {
            System.out.println("Driver Error!");
        } finally {
            try {
                if (dataSet != null) {
                    dataSet.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (mySqlConnection != null) {
                    mySqlConnection.close();
                }
            } catch (SQLException sq) {
                sq.printStackTrace();
            }
        }
    }public void updateData(Contact c, int idToUpdate) {
        try {
            dB_Connection();
            query = "SELECT * FROM contactdata where id=?";
            pst = mySqlConnection.prepareStatement(query);
            pst.setInt(1, idToUpdate);

            //reads requested query from Database
            dataSet = pst.executeQuery();

            query = "UPDATE contactdata set id=?,name=?,surname=?,age=? where id=?";
            pst = mySqlConnection.prepareStatement(query);
            pst.setInt(1, c.getId());
            pst.setString(2, c.getName());
            pst.setString(3, c.getSurname());
            pst.setInt(4, c.getAge());
            pst.setInt(5, idToUpdate);

            //reads requested query from Database
            pst.executeUpdate();

            while (dataSet.next()) {
                System.out.println("----\n유 " +
                        "\n Name: " + dataSet.getString("name") + " " +
                        "\n Surname: " + dataSet.getString("surname") + " " +
                        "\n Age: " + dataSet.getInt("age") + " " + "\n____ ");
            }

        } catch (SQLException sq) {
            System.out.println("Database error!");
        } catch (ClassNotFoundException ce) {
            System.out.println("Driver Error!");
        } finally {
            try {
                if (dataSet != null) {
                    dataSet.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (mySqlConnection != null) {
                    mySqlConnection.close();
                }
            } catch (SQLException sq) {
                sq.printStackTrace();
            }
        }
    }
}
