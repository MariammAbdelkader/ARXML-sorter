// to use OOP so we make class called AUTOSAR
public class AUTOSAR implements Comparable<AUTOSAR> {
// data fields
    private String CONTAINER_UUID;
    private String SHORT_NAME;
    private String LONG_NAME;
// no-org constructor
    public AUTOSAR() {

    }
    // srcond constructor
    public AUTOSAR(String id,String sn,String ln)
    {
        this.CONTAINER_UUID=id;
        this.LONG_NAME=ln;
        this.SHORT_NAME=sn;
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
        if ((this.getSHORT_NAME().charAt(getSHORT_NAME().length()-1)) > (o.getSHORT_NAME().charAt(getSHORT_NAME().length()-1))) {
            return 1;

        } else if ((this.getSHORT_NAME().charAt(getSHORT_NAME().length()-1)) <( o.getSHORT_NAME().charAt(getSHORT_NAME().length()-1)))  {
            return -1;
        } else {
            return 0;
        }
    }
}
