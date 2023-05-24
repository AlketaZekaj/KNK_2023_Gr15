module KNK {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires transitive javafx.graphics;
	requires transitive java.sql;
    requires mysql.connector.j;

    opens Application to javafx.graphics, javafx.fxml;
	exports Application;
	opens Controllerat to javafx.graphics, javafx.fxml;
	exports Controllerat;
	opens Modelet to javafx.graphics, javafx.fxml;
	exports Modelet;
	opens repository to javafx.graphics, javafx.fxml;
	exports repository;
	
	
}
