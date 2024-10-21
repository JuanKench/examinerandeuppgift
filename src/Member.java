import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Member {
    private String name;
    // unable to parse example numbers to int, contains corrupt characters.
    // work around , keep socialSecurity as String
    private String socialSecurity;
    private Date lastPayment;
    private int signins;
    private ArrayList<String> signinDates;

    public Member() {
        this.signins = 0;
    }

    public Member(String name, String socialSecurity, String lastPayment) throws ParseException {
        this.name = name;
        this.socialSecurity = socialSecurity;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.lastPayment = simpleDateFormat.parse(lastPayment);
        this.signins = 0;
    }

    public Member(String name, String socialSecurity, String lastPayment, int signins) throws ParseException {
        this.name = name;
        this.socialSecurity = socialSecurity;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.lastPayment = simpleDateFormat.parse(lastPayment);
        this.signins = signins;
    }

    public int getSignins() {
        return signins;
    }

    public void incramentSignins() {
        signins++;
    }

    public void setSignins(int signins) {
        this.signins = signins;
    }

    public String getName() {
        return name;
    }

    public String getSocialSecurity() {
        return socialSecurity;
    }

    public Date getLastPayment() {
        return lastPayment;
    }

    public void setLastPayment(String lastPayment) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.lastPayment = simpleDateFormat.parse(lastPayment);
    }

    public void setSigninDates(String signinDates) {
        this.signinDates = new ArrayList<String>(Arrays.asList(signinDates.split(",")));
    }

    public void addSigninDate(Date date) {
        if (this.signinDates == null) {
            this.signinDates = new ArrayList<>();
        }
        this.signinDates.add(date.toString());
    }

    public ArrayList<String> getSigninDates() {
        return signinDates;
    }

    public void setSocialSecurity(String socialSecurity) {
        this.socialSecurity = socialSecurity;
    }

    public void setName(String name) {
        this.name = name;
    }
}
