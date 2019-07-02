package partyObjects;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Main {

	public static void main(String[] args) throws IOException {
		int k1=0, k2=0;
		ArrayList<Person> inviteList = new ArrayList<Person>();
		List<String> list = getInput(); //Formats each line as string entity
		for(int i=0; i<list.size(); i++) {
			if(i==0) k1 = Integer.parseInt(list.get(i));
			else if(i==1) k2 = Integer.parseInt(list.get(i));
			else {
				inviteList.add(new Person(Integer.toString(i-1),list.get(i)));
			}
		}
		System.out.println(k1+"    "+k2); 
		int n = inviteList.size();
		char point;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n;j++) {
				point = inviteList.get(i).setUp(j);
				if(point == '1') inviteList.get(i).addConnection(inviteList.get(j));
			}
		}
		for(int i=0; i<n; i++) {
			if(inviteList.get(i).getNumOfFriends() <= k1 || (n - inviteList.get(i).getNumOfFriends()) <= k2) {
				for(int j=0; j<inviteList.get(i).getNumOfFriends(); j++) {
					inviteList.get(i).getConnection(j).removeConnection(inviteList.get(i));
				}
				inviteList.remove(i);
				n = inviteList.size();
				i=-1;
			}
		}
		System.out.println(inviteList.size());
		String[] output = new String[inviteList.size()];
		for (int i=0; i<inviteList.size(); i++) {
			output[i] = inviteList.get(i).getNum();
		}
		list = Arrays.asList(output);				//Output to file
		Charset utf8 = StandardCharsets.UTF_8;
		try {
			Files.write(Paths.get("party.txt"), list, utf8);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static List<String> getInput() throws IOException {
		List<String> list = new ArrayList<String>();
		ZipFile zipFile = new ZipFile("C:\\Users\\Randall_Thornton\\Downloads\\input.zip");
		ZipEntry zipEntry = zipFile.getEntry("description.txt");
		InputStream iStream = zipFile.getInputStream(zipEntry);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(iStream));
			String text = null;

			while ((text = br.readLine()) != null) {
				list.add(text);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				br.close();
			}
		}
		return list;		
	}
}
