import java.io.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class MembershipController {


    public MembershipController() {

    }

    public ArrayList<Member> getMembersFromName(String name) {
        ArrayList<Member> members = getList();
        ArrayList<Member> returnMembers = new ArrayList<Member>();
        for(Member member : members) {
            if(member.getName().equals(name)) {
                returnMembers.add(member);
            }
        }
        if(returnMembers.isEmpty()) {
            throw new MissingMemberException("Whomp whomp " + name + " not found");
        }
        return returnMembers;
    }

    public Member getMemberFromSocialSecurity(String socialSecurity) {
        ArrayList<Member> listOfMembers = getList();
        for (Member member : listOfMembers) {
            if (member.getSocialSecurity().equals(socialSecurity)) {
                return member;
            }
        }
        throw new MissingMemberException("Whomp whomp Social Security Not Found");
    }

    // requirement is no dynamic input data in test, therefore date is a parameter
    public Map<Member, Boolean> getHasPayed(ArrayList<Member> members, Date fromDate) {
        long yearInMillieSec = 31556952000L; // year in milliseconds
        Map<Member, Boolean> out = new HashMap<>();
        for (Member m : members) {
            out.put(m, fromDate.getTime() - yearInMillieSec < m.getLastPayment().getTime());
        }
        return out;
    }

    public ArrayList<Member> getList() {
        try (
                BufferedReader br = new BufferedReader(new FileReader("src\\medlemar.txt"));
        ) {
            String line;
            ArrayList<Member> members = new ArrayList<Member>();
            Member member = new Member();
            while ((line = br.readLine()) != null) {
                if(member.getLastPayment() != null){
                    member = new Member();
                }
                if(line.contains(", ")) {
                    String[] arr = line.split(", ");
                    member.setSocialSecurity(arr[0]);
                    member.setName(arr[1]);
                }
                else if(!line.isEmpty()) {
                    member.setLastPayment(line);
                }
                if(member.getLastPayment() != null) {
                    members.add(member);
                }
            }
            return members;


        }catch (IOException e){
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
}