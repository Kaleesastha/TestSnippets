/*
 * Main.java
 *
 * Created on May 30, 2005, 6:11 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package packagetodirectory;
import java.io.*;
import java.util.*;

public class Main {

    Properties fileProp = new Properties();
    /** Creates a new instance of Main */
    public Main()
    {
        
    }
    
    private void createPkgStructure(String source , String dest) throws Exception{ 
        File src = new File(source);
        File dst = new File(dest);
        createFiles(src,dst);
        File mappingFile = new File(dst.getPath()+File.separator+"mapping.properties");
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(mappingFile , true));
        fileProp.store(bos,"maps file path to original location");
        bos.flush();
        bos.close();
    }

    private void copyToOriginal(String dest) throws Exception{ 
        File dst = new File(dest);
        File mappingFile = new File(dst.getPath()+File.separator+"mapping.properties");
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(mappingFile));
        fileProp.load(bis);
        bis.close();
        Set keys = fileProp.keySet();
        Iterator keyIterator = keys.iterator();
        while (keyIterator.hasNext())
        {
            String srcPath = (String)keyIterator.next();
            String destPath = fileProp.getProperty(srcPath);
            //System.out.println("srcPath : "+srcPath+" \n destPath : " + destPath);
            copyFile(new File(srcPath), new File(destPath));
        }
        
    }


    ArrayList pkgList = new ArrayList();
    void createFiles(File source , File dest) throws Exception
    {
       
      File[] files = source.listFiles();
      int length = files.length;
      for(int i=0 ; i<length; i++)
      {
          if(files[i].isDirectory())
          {
              createFiles(files[i] , dest);
          }
          else if(files[i].getName().endsWith(".java"))
          {
              String pkgDir = getPackage(files[i]);
              if(pkgDir != null)
              {
                  StringBuffer sbf =new StringBuffer();
                  StringTokenizer tokenizer= new StringTokenizer(pkgDir,".");
                  while (tokenizer.hasMoreTokens())
                  {
                      String token = tokenizer.nextToken();
                      sbf.append(File.separator+token);
                  }
                  String pkgPath = dest.getAbsolutePath()+sbf.toString();
                  if(pkgDir != null && !pkgList.contains(pkgDir))
                  {
                      pkgList.add(pkgDir);
                      System.out.println(pkgPath);
                      createDirectory(new File(pkgPath));
                  }
                  File destFile = new File(pkgPath+File.separator+files[i].getName());
                  fileProp.put(destFile.getCanonicalPath(),files[i].getCanonicalPath());
                  copyFile(files[i] , destFile);
              }
          }
      }
    }

    private void createDirectory(File f) throws Exception
    {
        if(!f.exists())
        {
            if(f.getParentFile().exists())
            {
                f.mkdir();   
                
            }
            else
            {
                createDirectory(f.getParentFile());
                f.mkdir();   
            }
        }
    }

    public  void copyFile(File sourceFile,File destinationFile)
        throws Exception
    {
        FileInputStream sourceFis = null;
        FileOutputStream destinationFos = null;
        try
        {
            
            if(!destinationFile.exists())
            {
                destinationFile.createNewFile();
            }
            sourceFis = new FileInputStream(sourceFile);
            destinationFos = new FileOutputStream(destinationFile);
            int read = 0;
            byte arr[] = new byte[1024];
            while((read = sourceFis.read(arr)) != -1)
            {
                destinationFos.write(arr,0,read);
            }
        }
        finally
        {
            try
            {
                if(sourceFis != null)
                {
                    sourceFis.close();
                }
                if(destinationFos != null)
                {
                    destinationFos.close();
                }
            }
            catch(IOException ie)
            {
                //                 ie.printStackTrace();
            }
        }

    }

    
    private String getPackage(File file) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
         String line = null;
         while ((line = reader.readLine()) != null) 
         {
             line =line.toLowerCase().trim();
             if(line.startsWith("package"))
             {
                 int index = line.indexOf("package");
                 line = line.substring(index+"package".length()).trim();
                 index = line.indexOf(";");
                 line = line.substring(0,index).trim();
                 //System.out.println(line);
                 break;
             }
             
         }
         reader.close();
         return line;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        //new Main("c:/home/Projects/netutils/top/netutils/source/framework", "c:/home/Projects/netutils/top/netutils/source/frameworkiii/");   // TODO code application logic here
        
        Main main = new Main();
        if(args.length > 1)
        {
            main.createPkgStructure(args[0],args[1]);
        }
        else if(args.length == 1)
        {
            main.copyToOriginal(args[0]);
        }
    }
    
}
