import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class NewMain {

   
    
public static void main(String[] args) {
        try {
            System.out.println("Enter name of file");
          Scanner input = new Scanner(System.in);
          File file = new File(input.nextLine());
            
            if (!(file.getPath().endsWith(".arxml"))) {
                throw new NotVaildAutosarFileException("Invalid file extension");
            }
            
            if (file.length() == 0) {
                throw new EmptyAutosarFileException ("Warning: Empty file");
            }
            FileInputStream inputStream = new FileInputStream(file);
            int d; // read returns int
            StringBuilder stringBuilder = new StringBuilder();
            while ((d = inputStream.read()) != -1) {
                stringBuilder.append((char) d);

            }
            String data = stringBuilder.toString();
            Scanner sc= new Scanner (data);
            boolean before = true;
            StringBuilder b = new StringBuilder();
            StringBuilder a = new StringBuilder();
            ArrayList<AUTOSAR> containers=new ArrayList<AUTOSAR>();
            while (sc.hasNextLine()){
                String line=sc.nextLine();
                if (line.contains("<CONTAINER")){
                    String CONTAINER_UUID=line.substring(line.indexOf("UUID=\""), line.indexOf("\">"));
                    String short_name_tag=sc.nextLine();
                    String short_name=short_name_tag.substring(short_name_tag.indexOf("<SHORT-NAME>"), short_name_tag.indexOf("</SHORT-NAME>"));
                    String long_name_tag=sc.nextLine();
                    String long_name=long_name_tag.substring(long_name_tag.indexOf("<LONG-NAME>"), long_name_tag.indexOf("</LONG-NAME>"));
                    
                    AUTOSAR new_one=new AUTOSAR(CONTAINER_UUID,short_name,long_name);
                   containers.add(new_one);
                    before = false;
                    line = sc.nextLine();
                    
                            
                }
                else {
                    if (before)
                        b.append((line + '\n'));
                    else
                        a.append((line + '\n'));
                }
            }
            Collections.sort(containers);
            String outputName=file.getName().substring(0, file.getName().indexOf("."))+"_mod.arxml";
            FileOutputStream output = new FileOutputStream(outputName);
           output.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>".getBytes());
           output.write("\n<AUTOSAR>\n".getBytes());
           for (int i=0;i<containers.size();i++){
              output.write(containers.get(i).toString().getBytes());
              
           }
           output.write("</AUTOSAR>".getBytes());
            

        }
        catch(FileNotFoundException e){
            e=new FileNotFoundException ("File Not Found");
            
        }
        catch (NotVaildAutosarFileException e){
            
        }
        catch (EmptyAutosarFileException  e){
            
        }
        catch (IOException e){
            e = new IOException("Warning i/o Exception");
        }
    }
}

class NotVaildAutosarFileException extends Exception {

    public NotVaildAutosarFileException(String message) {
        System.out.println(message);
    }
    
    
}
 class EmptyAutosarFileException  extends Exception{
    public EmptyAutosarFileException (String message)
    {
        System.out.println(message);
    }
}

