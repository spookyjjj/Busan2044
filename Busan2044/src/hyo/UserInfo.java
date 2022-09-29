package hyo;
import java.util.ArrayList;
import java.util.List;
 //추가할것 맘대로 넣으셈요
public class UserInfo {
	final int FULL_HP = 5; 
	final int FULL_MENTAL = 5; 
	private int hp;
	private int mental;
	private List<Integer> party = new ArrayList<>();
	private double npc_likability;
	private List<Item> inventory = new ArrayList<>();
	//item_id에 따라서 durability, count를 개별로 관리할 수 있게 정보 처리 -> 저장필요한 정보 inventory: list<Item>
//	private map<진행상황, 선택사항>
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getMental() {
		return mental;
	}
	public void setMental(int mental) {
		this.mental = mental;
	}
	public List<Integer> getParty() {
		return party;
	}
	public void setParty(List<Integer> party) {
		this.party = party;
	}
	public double getNpc_likability() {
		return npc_likability;
	}
	public void setNpc_likability(int npc_likability) {
		this.npc_likability = npc_likability;
	}
	public List<Item> getInventory() {
		return inventory;
	}
	public void setInventory(List<Item> inventory) {
		this.inventory = inventory;
	}
}
 //사용예) SaveInfo user1 = new SaveInfo();
