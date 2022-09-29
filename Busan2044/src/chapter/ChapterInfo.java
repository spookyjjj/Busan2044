package chapter;

public class ChapterInfo {
	private int StoryId;
	private String StoryNum;
	private String StoryMain;
	private String StoryTime;
	private String check;
	public ChapterInfo(String storyNum, String storyMain, String storyTime, String check) {
		super();
		StoryNum = storyNum;
		StoryMain = storyMain;
		StoryTime = storyTime;
		this.check = check;
	}
	public ChapterInfo(int storyId, String storyNum, String storyMain, String storyTime, String check) {
		super();
		StoryId = storyId;
		StoryNum = storyNum;
		StoryMain = storyMain;
		StoryTime = storyTime;
		this.check = check;
	}
	public ChapterInfo() {
		super();
	}
	public int getStoryId() {
		return StoryId;
	}
	public void setStoryId(int storyId) {
		StoryId = storyId;
	}
	public String getStoryNum() {
		return StoryNum;
	}
	public void setStoryNum(String storyNum) {
		StoryNum = storyNum;
	}
	public String getStoryMain() {
		return StoryMain;
	}
	public void setStoryMain(String storyMain) {
		StoryMain = storyMain;
	}
	public String getStoryTime() {
		return StoryTime;
	}
	public void setStoryTime(String storyTime) {
		StoryTime = storyTime;
	}
	public String getCheck() {
		return check;
	}
	public void setCheck(String check) {
		this.check = check;
	}
	@Override
	public String toString() {
		return "ChapterInfo [StoryId=" + StoryId + ", StoryNum=" + StoryNum + ", StoryMain=" + StoryMain + ", check="
				+ check + "]";
	}
	
	
}
