package main;

public class ChoiceSum {
	private int choiceId;
	private String storyNum, choiceMain, StoryMain;
	private String storyCheck, choiceCheck;

	public ChoiceSum(int choiceId, String storyNum, String choiceMain, String storyMain, String storyCheck,
			String choiceCheck) {
		super();
		this.choiceId = choiceId;
		this.storyNum = storyNum;
		this.choiceMain = choiceMain;
		StoryMain = storyMain;
		this.storyCheck = storyCheck;
		this.choiceCheck = choiceCheck;
	}

	public int getChoiceId() {
		return choiceId;
	}

	public void setChoiceId(int choiceId) {
		this.choiceId = choiceId;
	}

	public String getStoryNum() {
		return storyNum;
	}

	public void setStoryNum(String storyNum) {
		this.storyNum = storyNum;
	}

	public String getChoiceMain() {
		return choiceMain;
	}

	public void setChoiceMain(String choiceMain) {
		this.choiceMain = choiceMain;
	}

	public String getStoryMain() {
		return StoryMain;
	}

	public void setStoryMain(String storyMain) {
		StoryMain = storyMain;
	}

	public String getStoryCheck() {
		return storyCheck;
	}

	public void setStoryCheck(String storyCheck) {
		this.storyCheck = storyCheck;
	}

	public String getChoiceCheck() {
		return choiceCheck;
	}

	public void setChoiceCheck(String choiceCheck) {
		this.choiceCheck = choiceCheck;
	}

	@Override
	public String toString() {
		return "ChoiceSum [choiceId=" + choiceId + ", storyNum=" + storyNum + ", choiceMain=" + choiceMain
				+ ", StoryMain=" + StoryMain + ", storyCheck=" + storyCheck + ", choiceCheck=" + choiceCheck + "]";
	}

}
