package savehere;

import java.sql.SQLException;

public class SaveInfo {
	private SaveHere save;
	private SaveHereDao SHD = new SaveHereDaoImpl();
	
	public static void main(String[] args) {
		SaveInfo s = new SaveInfo();
		
		try {
			System.out.println(s.SHD.read().get(0).getUserId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
