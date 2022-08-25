package model;

public class TemporaryEmail {

    private String tempEmailAddress;
    private String tempEmailWebInterfaceURL;
    private static TemporaryEmail tempEmail;

    private TemporaryEmail() {}

    public static TemporaryEmail getTempEmail() {
        if (tempEmail == null) {
            tempEmail = new TemporaryEmail();
        }
        return tempEmail;
    }

    public static String getTempEmailAddress() {
        return getTempEmail().tempEmailAddress;
    }

    public static String getTempEmailWebInterfaceURL() {
        return getTempEmail().tempEmailWebInterfaceURL;
    }

    public static void setTempEmail(String tempEmailAddress) {
        getTempEmail().tempEmailAddress = tempEmailAddress;
        getTempEmail().tempEmailWebInterfaceURL = "https://YOPmail.com?" + tempEmailAddress;
    }
}
