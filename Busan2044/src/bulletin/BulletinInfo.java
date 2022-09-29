package bulletin;

public class BulletinInfo {
	private int num;
	private String userId;
	private String achv;
	private String text;
	private int star;
	
	public BulletinInfo(int num, String userId, String achv, String text, int star) {
		super();
		this.num = num;
		this.userId = userId;
		this.achv = achv;
		this.text = text;
		this.star = star;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAchv() {
		return achv;
	}

	public void setAchv(String achv) {
		this.achv = achv;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	@Override
	public String toString() {
		return "BulletinInfo [num=" + num + ", userId=" + userId + ", achv=" + achv + ", text=" + text + ", star="
				+ star + "]";
	}
	
	
	
}
