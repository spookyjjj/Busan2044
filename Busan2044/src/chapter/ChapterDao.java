package chapter;

import java.sql.SQLException;
import java.util.List;

import savehere.SaveHere;

public interface ChapterDao {
	   int create(String storyNum, String storyMain, String storyTime, String check) throws SQLException;
	   List<ChapterInfo> read() throws SQLException;
	   ChapterInfo read(int storyNum) throws SQLException;
	   int update(int storyNum, String storyMain, String storyTime, String check) throws SQLException;
	   int delete(int storyNum) throws SQLException;
}
