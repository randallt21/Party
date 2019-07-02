package partyObjects;
import java.util.*;

public class Person {
	private String friendNum, list;
	private ArrayList<Person> friendList = new ArrayList<Person>();
	
	public Person() {
		friendNum = "";
		list = "";
		friendList = new ArrayList<Person>();
	}
	
	public Person(String num, String l) {
		friendNum = num;
		list = l;
		friendList = new ArrayList<Person>();
	}
	
	public String getNum() {
		return friendNum;
	}
	
	public int getNumOfFriends() {
		return friendList.size();
	}
	
	public char setUp(int i) {
		return list.charAt(i);
	}
	
	public void addConnection(Person p) {
		friendList.add(p);
	}
	
	public Person getConnection(int i) {
		return friendList.get(i);
	}
	
	public void removeConnection(Person person) {
		friendList.remove(person);
	}
	
	public void printFriendList() {
		for(int i=0; i<friendList.size(); i++) {
			System.out.print(friendList.get(i)+ " ");
		}
		System.out.println();
	}
	
	public String toString() {
		return friendNum;
	}
}
