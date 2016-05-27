package puyopack;

import java.sql.*;
import java.util.*;
import javax.swing.*;

/**
 * Class which handles DB calls to keep track of high scores
 * 
 * @author Rakesh(19-07-07)
 * 
 */

@SuppressWarnings("unchecked")
public class DBHandler {

	private static Connection connection;

	private PreparedStatement prepStatement;

	private ResultSet resultSet;

	/**
	 * Constructor
	 * 
	 */
	public DBHandler() {
		createConnection();
	}

	/**
	 * Method which creates the DB Connection.
	 * 
	 */
	private void createConnection() {
		if (connection == null) {
			try {
				String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
				String url = "jdbc:odbc:" + "Puyo_Game";
				Class.forName(driver);
				connection = DriverManager.getConnection(url, "puyo", "puyo");
			} catch (Exception ex) {
				JOptionPane
						.showMessageDialog(
								new JFrame(),
								"Please Check whether ODBC\n driver has been configured. \n "
										+ "Then, check whether Puyo_Game.mdb is present.");
			}
		}
	}

	/**
	 * Method to insert the name and score entered by the player.
	 * 
	 * @param name
	 * @param score
	 */
	void insertRecords(String name, int score) {
		try {
			int row_num = 0;
			String query = new String("SELECT MAX(Row_Num) FROM Puyo_Scores");
			prepStatement = connection.prepareStatement(query);
			resultSet = prepStatement.executeQuery();
			while (resultSet.next()) {
				row_num = resultSet.getInt(1);
			}
			resultSet.close();
			prepStatement.close();
			StringBuffer buffer = new StringBuffer();
			buffer.append("INSERT INTO Puyo_Scores ");
			buffer.append("(Row_Num,Name,Score) ");
			buffer.append("VALUES(?,?,?)");
			query = new String(buffer);
			prepStatement = connection.prepareStatement(query);
			prepStatement.setInt(1, row_num + 1);
			prepStatement.setString(2, name);
			prepStatement.setInt(3, score);
			prepStatement.executeUpdate();
			prepStatement.close();
			doHouseKeeping(score);
		} catch (SQLException ex) {

		}
	}

	/**
	 * Method to do house keeping job.
	 * 
	 * @param score
	 */
	private void doHouseKeeping(int score) {
		try {
			int totalRecords = 0;
			String query = new String("SELECT COUNT(Score)FROM Puyo_Scores");
			prepStatement = connection.prepareStatement(query);
			resultSet = prepStatement.executeQuery();
			while (resultSet.next()) {
				totalRecords = resultSet.getInt(1);
			}
			resultSet.close();
			prepStatement.close();
			if (totalRecords > 10) {
				topTenScores(score);
			}
		} catch (SQLException ex) {

		}
	}

	/**
	 * Method that tracks the top ten scores and deletes the 11th record.
	 * 
	 * @param score
	 */
	private void topTenScores(int score) {
		try {
			int minScore = 0;
			int row_num = 0;
			String minQuery = new String("SELECT MIN(Score) FROM Puyo_Scores");
			prepStatement = connection.prepareStatement(minQuery);
			resultSet = prepStatement.executeQuery();
			while (resultSet.next()) {
				minScore = resultSet.getInt(1);
			}
			resultSet.close();
			prepStatement.close();
			if (score < minScore) {
				String query = new String(
						"SELECT MIN(Row_Num) FROM Puyo_Scores WHERE Score=?");
				prepStatement = connection.prepareStatement(query);
				prepStatement.setInt(1, score);
				resultSet = prepStatement.executeQuery();
				while (resultSet.next()) {
					row_num = resultSet.getInt(1);
				}
				resultSet.close();
				prepStatement.close();
				query = new String(
						"DELETE FROM Puyo_Scores WHERE Score=? AND Row_Num=?");
				prepStatement = connection.prepareStatement(query);
				prepStatement.setInt(1, score);
				prepStatement.setInt(2, row_num);
				prepStatement.executeUpdate();
				prepStatement.close();
			} else {
				String query = new String(
						"SELECT MIN(Row_Num) FROM Puyo_Scores WHERE Score=?");
				prepStatement = connection.prepareStatement(query);
				prepStatement.setInt(1, minScore);
				resultSet = prepStatement.executeQuery();
				while (resultSet.next()) {
					row_num = resultSet.getInt(1);
				}
				query = new String(
						"DELETE FROM Puyo_Scores WHERE Score=? AND Row_Num=?");
				prepStatement = connection.prepareStatement(query);
				prepStatement.setInt(1, minScore);
				prepStatement.setInt(2, row_num);
				prepStatement.executeUpdate();
				prepStatement.close();
			}
		} catch (Exception ex) {

		}
	}

	/**
	 * Method that allows the user to view high scores.
	 * 
	 * @return
	 */
	Vector showRecords() {
		Vector rowRecords = new Vector();
		try {
			String query = new String(
					"SELECT Name,Score FROM Puyo_Scores ORDER BY Score DESC");
			prepStatement = connection.prepareStatement(query);
			resultSet = prepStatement.executeQuery();
			while (resultSet.next()) {
				Vector dummy = new Vector();
				dummy.add(resultSet.getString(1));
				dummy.add(new Integer(resultSet.getInt(2)));
				rowRecords.add(dummy);
			}
			resultSet.close();
			prepStatement.close();
		} catch (SQLException ex) {

		}
		return rowRecords;
	}

	/**
	 * Method that reset scores.
	 * 
	 */
	void resetScores() {
		try {
			String query = new String("DELETE FROM Puyo_Scores");
			prepStatement = connection.prepareStatement(query);
			prepStatement.executeUpdate();
			prepStatement.close();
		} catch (Exception ex) {

		}
	}
}
