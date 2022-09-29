package savehere;

import java.sql.SQLException;
import java.util.List;

public interface SaveHereDao {
	
	   List<SaveHere> read() throws SQLException;
	   String read(String userId) throws SQLException;
	   String readEnd(String ending_Name) throws SQLException;

}
