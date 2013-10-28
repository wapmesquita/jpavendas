package br.com.dextraining.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JFrame;

import org.hsqldb.util.DatabaseManagerSwing;

public class DataBaseManager {

    public static void runManager(String ds) {
        String title = "Database Manager";
        if (ds != null) {
            title += " " + ds;
        }
        DatabaseManagerSwing manager = new DatabaseManagerSwing(new JFrame(title));
        manager.main();

        if (ds != null) {
            try {
            	Class.forName("org.hsqldb.jdbcDriver");
            	Connection con = DriverManager.getConnection(ds, "sa", "");
                manager.connect(con);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
            manager.start();
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String ds = (args.length > 0 ? args[0] : null);
        runManager(ds);
    }

    public static void start(final String ds) {
        Runnable runnable = new Runnable() {

            public void run() {
                runManager(ds);
            }

        };
        Thread thread = new Thread(runnable, "DatabaseManager");
        thread.start();
    }
	
}
