package main;

public class Choice {
	private int choiceId;
	private String choiceNum, choiceMain;
	
	public Choice(int choiceId, String choiceNum, String choiceMain) {
		super();
		this.choiceId = choiceId;
		this.choiceNum = choiceNum;
		this.choiceMain = choiceMain;
	}

	public int getChoiceId() {
		return choiceId;
	}

	public void setChoiceId(int choiceId) {
		this.choiceId = choiceId;
	}

	public String getChoiceNum() {
		return choiceNum;
	}

	public void setChoiceNum(String choiceNum) {
		this.choiceNum = choiceNum;
	}

	public String getChoiceMain() {
		return choiceMain;
	}

	public void setChoiceMain(String choiceMain) {
		this.choiceMain = choiceMain;
	}

	@Override
	public String toString() {
		return "Choice [choiceId=" + choiceId + ", choiceNum=" + choiceNum + ", choiceMain=" + choiceMain + "]";
	}

	
	
	
	
	
	
	
}
