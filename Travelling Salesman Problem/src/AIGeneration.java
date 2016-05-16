import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class AIGeneration {

	//public static ArrayList<Node> city = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		int numOfCity = 300;

//		int counter = 0;
//
//		int xLength = (int) Math.sqrt(numOfCity);
//
//		for (int i = 0; counter < numOfCity; i++) {
//			for (int j = 0; j < xLength; j++) {
//				if (counter < numOfCity) {
//					city.add(new Node(i, j));
//					counter++;
//				} else {
//					break;
//				}
//			}
//		}
//
		File file = new File("city.txt");
		// creates the file
		file.createNewFile();
		// creates a FileWriter Object
		FileWriter writer = new FileWriter(file);
		// Writes the content to the file
//
//		writer.write("x y\n");
//		// Print all city (x, y)
//		for (int i = 0; i < city.size(); i++) {
//			Node n = city.get(i);
//			writer.write(n.getX() + " " + n.getY() + "\n");
//		}
//		writer.write("_________________________________________________________________________\n");
//
//		// Calculate Eculidian distance.
//		writer.write("S D Dist\n");
//		for (int i = 0; i < city.size() - 1; i++) {
//			Node n1 = city.get(i);
//			for (int j = i + 1; j < city.size(); j++) {
//				Node n2 = city.get(j);
//				double distX = n1.getX() - n2.getX();
//				double distY = n1.getY() - n2.getY();
//				double dist = Math.sqrt(Math.pow(distX, 2) + Math.pow(distY, 2));
//				writer.write(i + " " + j + " " + dist+"\n");
//			}
//		}
//		writer.flush();
//		writer.close();

		int x = 0;
		int y = 0;

		for (int i = 0; i < numOfCity; i++) {
			
			if(i< numOfCity / 4)
			{
				y++;
			}else if(i < numOfCity / 2)
			{
				x++;
			}else if(i < 3*numOfCity / 4)
			{
				y--;
			}else{
				x--;
			}
			
			writer.write(x + " " + y + "\n");

		}
		
		writer.flush();
		writer.close();
		
	}

}
