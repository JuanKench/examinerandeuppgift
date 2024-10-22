import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        MembershipController mc = new MembershipController();
        PTFileWriter wr = new PTFileWriter();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Ange medlemmens namn: ");
        String name = scanner.nextLine();

        System.out.println("Ange medlemmens personnummer: ");
        String socialSecurity = scanner.nextLine();

        Date paymentDate = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);

        while (paymentDate == null) {
            System.out.println("Ange datum för sista betalning (format: yyyy-MM-dd): ");
            String lastPayment = scanner.nextLine();
            try {
                paymentDate = dateFormat.parse(lastPayment);
            } catch (ParseException e) {
                System.out.println("Fel: Datumet måste vara i formatet yyyy-MM-dd. Försök igen.");
            }
        }
        Member newMember = new Member(name, socialSecurity, dateFormat.format(paymentDate));
        Date currentDate = new Date();
        ArrayList<Member> memberList = new ArrayList<>();
        memberList.add(newMember);
        Map<Member, Boolean> hasPayedMap = mc.getHasPayed(memberList, currentDate);
        boolean hasPaid = hasPayedMap.get(newMember);
        if (!hasPaid) {
            System.out.println("Fel: Medlemmen har inte betalat inom det senaste året.");
        } else {
            wr.writeToFile(newMember);
            System.out.println("Ny medlem har lagts till i systemet och skrivits till filen.");
        }
    }
}