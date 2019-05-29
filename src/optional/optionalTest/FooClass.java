package optional.optionalTest;

public class FooClass {

    private String dataString;
    private Integer dataInteger;
    private Specific specific;

    public FooClass(String dataString, Integer dataInteger, Specific specific) {
        this.dataString = dataString;
        this.dataInteger = dataInteger;
        this.specific = specific;
    }

    public String getDataString() {
        return dataString;
    }

    public void setDataString(String dataString) {
        this.dataString = dataString;
    }

    public Integer getDataInteger() {
        return dataInteger;
    }

    public void setDataInteger(Integer dataInteger) {
        this.dataInteger = dataInteger;
    }

    public Specific getSpecific() {
        return specific;
    }

    public void setSpecific(Specific specific) {
        this.specific = specific;
    }
}
