import java.io.*;
import java.util.ArrayList;
import java.util.Date;


public class PTFileWriter {

    public void writeToFile(Member member) {
        try (
            BufferedWriter writer = new BufferedWriter(new FileWriter("src\\PTFile.txt", true))
        ) {
            boolean memberExists = false;
            ArrayList<Member> members = getPTList();
            for (Member m : members) {
                if (m.getSocialSecurity().equals(member.getSocialSecurity())) {
                    memberExists = true;
                    m.incramentSignins();
                    m.addSigninDate(new Date());
                }
            }
            if (!memberExists) {
                member.incramentSignins();
                member.addSigninDate(new Date());
                members.add(member);
            }
            for (Member m : members ) {
                writer.write(m.getSocialSecurity() + ", " + m.getName() + ", " + m.getSignins());
                writer.newLine();
                for (int i = 0 ; i < m.getSigninDates().size() ; i++) {
                    writer.write(m.getSigninDates().get(i));
                    if (i != m.getSigninDates().size() - 1) {
                        writer.write(",");

                    }
                }
                writer.newLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public ArrayList<Member> getPTList() {
        try (
                BufferedReader br = new BufferedReader(new FileReader("src\\PTFile.txt"))
        ) {
            String line;
            ArrayList<Member> members = new ArrayList<>();
            Member member = new Member();
            while ((line = br.readLine()) != null) {
                if(member.getSigninDates() != null){
                    member = new Member();
                }
                if(line.contains(", ")) {
                    String[] arr = line.split(", ");
                    member.setSocialSecurity(arr[0]);
                    member.setName(arr[1]);
                    member.setSignins(Integer.parseInt(arr[2]));
                }
                else if(!line.isEmpty()) {
                    member.setSigninDates(line);
                }
                if(member.getSigninDates() != null) {
                    members.add(member);
                }
            }
            return members;


        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

}
