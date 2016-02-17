package test;
import java.util.*;
import java.io.*;
import com.adventnet.nms.util.*;
public class ReplaceUserName{
        static String path = "";
        static File[] files ;
        static Vector<String> treeXmlFiles = new Vector<String>();
        public static void main(String[] s){
                try{

                        File dir = new File(PureUtils.rootDir+"users");
                        path = PureUtils.rootDir;
                        displayDirectoryContents(dir);
                        System.err.println("Sleeping for 10 seconds to check");
                        Thread.sleep(10000);
                        for(int i=0; i<treeXmlFiles.size(); i++){
                                File newfile = new File("example.xml");
                                String fileName = treeXmlFiles.get(i);
                                File file = new File(fileName);
                                System.err.println("fileName : "+fileName);
                                String userName = fileName.replaceAll(path,"").replaceAll("users", "").replaceAll("/","").replaceAll("Tree.xml", "");
                                String pattern = " USERNAME=\""+userName+"\"";
                                System.err.println("pattern:"+pattern+"##");
                                String replace = "";
                                BufferedReader br = new BufferedReader(new FileReader(fileName));
                                BufferedWriter output = new BufferedWriter(new FileWriter(newfile));
                                String line;

                                while ((line = br.readLine()) != null) {
                                        line = line.replaceAll(pattern,replace);
                                        output.write(line+"\n");
                                }
                                br.close();
                                output.close();
                                file.delete();
                                File source = new File("example.xml");
                                File dest = new File(fileName);
                                InputStream is = null;
                                OutputStream os = null;
                                is = new FileInputStream(source);
                                os = new FileOutputStream(dest);
                                byte[] buffer = new byte[1024];
                                int length;
                                while ((length = is.read(buffer)) > 0) {
                                        os.write(buffer, 0, length);
                                }
                                is.close();
                                os.close();
                                newfile.delete();
                        }
                }
                catch(Exception exp){
                        exp.printStackTrace();
                }
        }
        public static void displayDirectoryContents(File dir) {
                try {
                         files = dir.listFiles();
                        for (File file : files) {
                                if (file.isDirectory()) {
                                        displayDirectoryContents(file);
                                } else {
                                        if(file.getCanonicalPath().indexOf("Tree.xml") != -1){
                                                System.err.println("fileName in displayDirectoryContents : "+file.getCanonicalPath());
                                                treeXmlFiles.add(file.getCanonicalPath());
                                        }
                                }
                        }
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }
}
