

import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ARXML {

    public static void main(String[] args) {
        try {
            String fileName = args[0];
            File file = new File(fileName);
            if (!(fileName.endsWith(".arxml"))) {
                throw new NotVaildAutosarFileException("Invalid file extension");
            }

            if (file.length() == 0) {
                throw new EmptyAutosarFileException("Warning: Empty file");
            }

            FileInputStream inputStream = new FileInputStream(file);
            int d; // read returns int
            StringBuilder stringBuilder = new StringBuilder();
            while ((d = inputStream.read()) != -1) {
                stringBuilder.append((char) d);

            }
            String data = stringBuilder.toString();
            Scanner sc = new Scanner(data);
            boolean before = true;
            StringBuilder x = new StringBuilder();
            StringBuilder y = new StringBuilder();
            ArrayList<AUTOSAR> containers = new ArrayList<AUTOSAR>();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.contains("<CONTAINER")) {
                    String CONTAINER_UUID = line.substring(line.indexOf("UUID=\""), line.indexOf("\">"));
                    String short_name_tag = sc.nextLine();
                    String short_name = short_name_tag.substring(short_name_tag.indexOf("<SHORT-NAME>"), short_name_tag.indexOf("</SHORT-NAME>"));
                    String long_name_tag = sc.nextLine();
                    String long_name = long_name_tag.substring(long_name_tag.indexOf("<LONG-NAME>"), long_name_tag.indexOf("</LONG-NAME>"));

                    AUTOSAR new_one = new AUTOSAR(CONTAINER_UUID, short_name, long_name);
                    containers.add(new_one);
                    before = false;
                    line = sc.nextLine();

                } else {
                    if (before) {
                        x.append((line + '\n'));
                    } else {
                        y.append((line + '\n'));
                    }
                }
            }
            Collections.sort(containers);
            String outputName = fileName.substring(0, fileName.indexOf(".")) + "_mod.arxml";
            FileOutputStream output = new FileOutputStream(outputName);
            output.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>".getBytes());
            output.write("\n<AUTOSAR>\n".getBytes());
            for (int i = 0; i < containers.size(); i++) {
                output.write(containers.get(i).toString().getBytes());

            }
            output.write("</AUTOSAR>".getBytes());

        } catch (FileNotFoundException e) {
            e = new FileNotFoundException("File Not Found");

        } catch (NotVaildAutosarFileException e) {

        } catch (EmptyAutosarFileException e) {

        } catch (IOException e) {
            e = new IOException("Warning i/o Exception");
        }
    }
}

// to use OOP so we make class called AUTOSAR
class AUTOSAR implements Comparable<AUTOSAR> {
// data fields

    private String CONTAINER_UUID;
    private String SHORT_NAME;
    private String LONG_NAME;
// no-org constructor

    public AUTOSAR() {

    }

    // srcond constructor
    public AUTOSAR(String id, String sn, String ln) {
        this.CONTAINER_UUID = id;
        this.LONG_NAME = ln;
        this.SHORT_NAME = sn;
    }

// getters and setters 
    public String getCONTAINER_UUID() {
        return CONTAINER_UUID;
    }

    public void setCONTAINER_UUID(String cONTAINER_UUID) {
        this.CONTAINER_UUID = cONTAINER_UUID;
    }

    public String getSHORT_NAME() {
        return SHORT_NAME;
    }

    public void setSHORT_NAME(String sHORT_NAME) {
        this.SHORT_NAME = sHORT_NAME;
    }

    public String getLONG_NAME() {
        return LONG_NAME;
    }

    public void setLONG_NAME(String lONG_NAME) {
        this.LONG_NAME = lONG_NAME;
    }

    @Override
    // override toSting method
    public String toString() {
        return "<CONTAINER UUID=" + this.getCONTAINER_UUID() + ">\n"
                + "<SHORT-NAME>" + this.getSHORT_NAME() + "</SHORT-NAME>\n"
                + "<LONG-NAME>" + this.getLONG_NAME() + "</LONG-NAME>\n"
                + "</CONTAINER>";
    }

    @Override
    // override compareTO method
    public int compareTo(AUTOSAR o) {
        if ((this.getSHORT_NAME().charAt(getSHORT_NAME().length() - 1)) > (o.getSHORT_NAME().charAt(getSHORT_NAME().length() - 1))) {
            return 1;

        } else if ((this.getSHORT_NAME().charAt(getSHORT_NAME().length() - 1)) < (o.getSHORT_NAME().charAt(getSHORT_NAME().length() - 1))) {
            return -1;
        } else {
            return 0;
        }
    }
}

class NotVaildAutosarFileException extends Exception {

    public NotVaildAutosarFileException(String message) {
        System.out.println(message);
    }

}

class EmptyAutosarFileException extends Exception {

    public EmptyAutosarFileException(String message) {
        System.out.println(message);
    }
}
