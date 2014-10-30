import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class ReadNames {

	public ReadNames(int size, String saveFile) {
		PrintWriter out;
		
		String workingDir = System.getProperty("user.dir");
		ArrayList<File> files = new ArrayList<File>(0);
		files = listFile(workingDir, files);
		
//		ArrayList<String> strings = new ArrayList<String>(0);
		Object[] data = new Object[files.size()];
		for (int i = 0; i < files.size(); i++) {
			ArrayList<String> strings = new ArrayList<String>(0);
			strings = readFiles(files.get(i), strings);
			data[i] = strings;
		}
		
		try {
			out = new PrintWriter(saveFile);

			for (int i = 0; i < size; i++) {
				out.print("\n");
				for(int j=0; j<data.length; j++) {
					@SuppressWarnings("unchecked")
					ArrayList<String> arrayList = (ArrayList<String>)data[j];
					out.print(arrayList.get(randInt(0,arrayList.size()-1)));
					if(j==2) out.print(" "+randInt(1, 120));
					out.print(";");
				}
				
			}
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
//		new ReadNames();
		new GUI();
	}

	public ArrayList<String> readFiles(File file, ArrayList<String> strings) {
		
		BufferedReader br = null;
		String line = "";
//		String cvsSplitBy = ",";
		
		if(file.exists())
		try {

			br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null) {
				
				strings.add(line);
//				// use comma as separator
//				String[] country = line.split(cvsSplitBy);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println("Done");
		return strings;
	}
	
	public ArrayList<File> listFile(String pathname, final ArrayList<File> intFiles) {
	    File f = new File(pathname);
	    File[] listfiles = f.listFiles();
	    for (int i = 0; i < listfiles.length; i++) {
	        
	    	if (listfiles[i].isDirectory()) {
	            File[] internalFile = listfiles[i].listFiles();
	            for (int j = 0; j < internalFile.length; j++) {
//	                System.out.println(internalFile[j]);
//                	System.out.println("int: "+internalFile[j].getAbsolutePath());
	                if(internalFile[j].getAbsolutePath().contains(".txt")) {
	                
	                	intFiles.add(internalFile[j]);
	                
	                }
	                if (internalFile[j].isDirectory()) {
	                    String name = internalFile[j].getAbsolutePath();
	                    listFile(name, intFiles);
	                }

	            }
	        } else {
	        
//            	System.out.println("int: "+listfiles[i].getAbsolutePath());
                if(listfiles[i].getAbsolutePath().contains(".txt")) {
                
                	intFiles.add(listfiles[i]);
                
                }
                
	        	System.out.println(listfiles[i]);
	        }
	    }
	    return intFiles;
	}
	
	public static int randInt(int min, int max) {

	    // NOTE: Usually this should be a field rather than a method
	    // variable so that it is not re-seeded every call.
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
}
