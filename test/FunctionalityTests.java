import org.junit.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;

public class FunctionalityTests {

    MembershipController mc = new MembershipController();
    PTFileWriter wr = new PTFileWriter();

    public FunctionalityTests() throws IOException {
    }





    @Test
    public void assertMemberHasPayedFromName() {
        String name = "Greger Ganache";
        ArrayList<Member> members = mc.getMembersFromName(name);
        Date date = new Date();
        date.setTime(new GregorianCalendar(2024, Calendar.NOVEMBER, 21).getTimeInMillis());
        Map<Member, Boolean> hasPayed = mc.getHasPayed(members, date);
        // Multiple members can share name, this ensures all has paid
        for (Boolean b : hasPayed.values()) {
            assertTrue(b);
        }


        //7512166544, Greger Ganache
        //2024-03-23
    }


    @Test
    public void assertMemberHasPayedFromSocialSecurity(){
        String socialSecurity = "7805211234";
        Member member = mc.getMemberFromSocialSecurity(socialSecurity);
        ArrayList<Member> listOfMembers = new ArrayList<>(); // wrap member in ArrayList
        listOfMembers.add(member);
        Date date = new Date();
        date.setTime(new GregorianCalendar(2024, Calendar.NOVEMBER, 21).getTimeInMillis());
        Map<Member, Boolean> hasPayed = mc.getHasPayed(listOfMembers, date);
        for (Boolean b : hasPayed.values()) {
            assertTrue(b);
        }

         //7805211234, Nahema Ninsson
         //  2024-08-04


    }


    @Test
    public void assertNameNotInSystem(){
        String name = "Johan Eriksson";
        assertThrows(MissingMemberException.class, () -> {
            mc.getMembersFromName(name);
        });
    }

    @Test
    public void assertSocialSecurityNotInSystem(){
        String socialSecurity = "55555555";
        assertThrows(MissingMemberException.class, () -> {
            mc.getMemberFromSocialSecurity(socialSecurity);
        });

    }

    @Test
    public void assertMemberHasNotPayedFromName(){
        String name = "Kadine Karlsson";
        ArrayList<Member> members = mc.getMembersFromName(name);
        Date date = new Date();
        date.setTime(new GregorianCalendar(2024, Calendar.NOVEMBER, 21).getTimeInMillis());
        Map<Member, Boolean> hasPayed = mc.getHasPayed(members, date);
        for (Boolean b : hasPayed.values()) {
            assertFalse(b);
        }
        //4604151234, Kadine Karlsson
        //2019-01-09

    }



    @Test
    public void assertMemberHasNotPayedFromSocialSecurity(){
        String socialSecurity = "9902149834";
        Member member = mc.getMemberFromSocialSecurity(socialSecurity);
        ArrayList<Member> listOfMembers = new ArrayList<>(); // wrap member in ArrayList
        listOfMembers.add(member);
        Date date = new Date();
        date.setTime(new GregorianCalendar(2024, Calendar.NOVEMBER, 21).getTimeInMillis());
        Map<Member, Boolean> hasPayed = mc.getHasPayed(listOfMembers, date);
        for (Boolean b : hasPayed.values()) {
            assertFalse(b);
        }

        
        //9902149834, Jicky Juul
        //2023-09-27

    }

    @Test
    public void assertMemberHasSignedInFromSocialSecurity(){
        String socialSecurity = "7608021234";
            Member member = mc.getMemberFromSocialSecurity(socialSecurity);
            ArrayList<Member> listOfMembers = new ArrayList<Member>();
            listOfMembers.add(member);
            Date date = new Date();
            date.setTime(new GregorianCalendar(2024, Calendar.NOVEMBER, 21).getTimeInMillis());
            Map<Member, Boolean> map = mc.getHasPayed(listOfMembers, date);
            for (var e : map.entrySet()){
                if (e.getValue()){
                    int expectedSignins = e.getKey().getSignins();
                    wr.writeToFile(e.getKey());
                    ArrayList<Member> membersAfter = wr.getPTList();
                    for (Member m : membersAfter) {
                        if (m.getSocialSecurity().equals(e.getKey().getSocialSecurity())) {
                            assertNotSame(expectedSignins, m.getSignins());
                        }
                    }
                }
        }


        //7608021234, Diamanda Djedi
        //2024-01-30
    }

    @Test
    public void assertMemberHasSignedInFromName(){
        String name = "Greger Ganache";
        ArrayList<Member> members = mc.getMembersFromName(name);
        Date date = new Date();
        date.setTime(new GregorianCalendar(2024, Calendar.NOVEMBER, 21).getTimeInMillis());
        Map<Member, Boolean> map = mc.getHasPayed(members, date);
        for (var e : map.entrySet()){
            if (e.getValue()){
                int expectedSignins = e.getKey().getSignins();
                wr.writeToFile(e.getKey());
                ArrayList<Member> membersAfter = wr.getPTList();
                for (Member m : membersAfter) {
                    if (m.getSocialSecurity().equals(e.getKey().getSocialSecurity())) {
                        assertNotSame(expectedSignins, m.getSignins());
                    }
                }
            }
        }
        //7512166544, Greger Ganache
        //2024-03-23
    }
}