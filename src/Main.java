import java.io.IOException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        MembershipController mc = new MembershipController();

        mc.checkMemberStatus();
    }
}