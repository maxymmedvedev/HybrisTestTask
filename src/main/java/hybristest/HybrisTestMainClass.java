package hybristest;

import implementation.ConnectionManager;
import implementation.JDBCDataAccessor;
import implementation.UserInteraction;

public class HybrisTestMainClass {

	public static void main(String[] args) {
		JDBCDataAccessor dao = new JDBCDataAccessor(ConnectionManager.getConnection());
		UserInteraction ui = new UserInteraction(dao);
		ui.printMainMenu();

	}

}
