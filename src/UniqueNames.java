import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by Avishay on 4/26/2016.
 */
public class UniqueNames {
    public static Map<String, List<String>> nickNames = new HashMap<>();

    public static int countUniqueNames(String billFirstName, String billLastName, String shipFirstName, String shipLastName, String billNameOnCard) {
        String fBillFirstName = billFirstName.toLowerCase(); // had to do those to avoid any problems with search in database / identifying actual people
        String fBillLastName = billLastName.toLowerCase();   // f means final, to avoid modifying the original parameters, perhaps we'll need them, who knows?
        String fShipFirstName = shipFirstName.toLowerCase();
        String fShipLastName = shipLastName.toLowerCase();
        String fBillNameOnCard = billNameOnCard.toLowerCase();

        // the whole section takes out the middle names from the input and puts them at the array below, names are modified so they won't contain any middle names.
        String[] middleNames = {"", "", ""}; // 0 is billing name, 1 is shipping name, 2 is name on card
                                             // type - 0 = firstname, 1 = lastname, 2 = fullname
        fBillFirstName = updateMiddleNames(fBillFirstName, 0, 0, middleNames);
        fBillLastName = updateMiddleNames(fBillLastName , 1, 0, middleNames);
        fShipFirstName = updateMiddleNames(fShipFirstName, 0, 1, middleNames);
        fShipLastName = updateMiddleNames(fShipLastName, 1, 1, middleNames);
        fBillNameOnCard = updateMiddleNames(fBillNameOnCard, 2, 2, middleNames);

        int count = 1;
        if (!isSameFirstName(fBillFirstName, fShipFirstName) || !isSameLastName(fBillLastName, fShipLastName) || !equalMiddleNames(middleNames[0], middleNames[1]))
            ++count;
        if ((!isNameOnCard(fBillFirstName, fBillLastName, fBillNameOnCard) && !isNameOnCard(fShipFirstName, fShipLastName, fBillNameOnCard)) || (!equalMiddleNames(middleNames[0], middleNames[2]) || !equalMiddleNames(middleNames[1], middleNames[2])))
            ++count;
        return count;
    }

    public static String updateMiddleNames(String name, int type, int arrayIndex, String[] arr) { // type - 0 = firstname, 1 = lastname, 2 = fullname
        String[] names = name.split(" ");

        if (type != 2) // if it's not a card
            name = names[type == 0 ? 0 : names.length - 1];

        for (int i = (type != 1) ? 1 : 0; i < names.length - ((type != 0) ? 1 : 0); ++i)
            arr[arrayIndex] += names[i] + " ";

        return name;
    }

    public static boolean equalMiddleNames(String a, String b) { // If no middle name is found, it can be related as an equal to any middle name
        if ((a.equals("") && !b.equals("")) || !a.equals("") && b.equals(""))
            return true;
        return a.equals(b);
    }

    public static boolean isNameOnCard(String firstName, String lastName, String cardName) { // checks if a first name
        return containsSameFirst(cardName, firstName) && containsSameLast(cardName, lastName);
    }

    public static boolean containsSameFirst(String full, String first) {
        return Stream.of(full.split(" ")).anyMatch(str -> isSameFirstName(str, first));
    }

    public static boolean containsSameLast(String full, String last) {
        return Stream.of(full.split(" ")).anyMatch(str -> isSameLastName(str, last));
    }

    public static boolean isSameFirstName(String a, String b) {
        return isTypoOrIdentical(a, b) || isANicknameOf(a, b);
    }

    public static boolean isSameLastName(String a, String b) {
        return isTypoOrIdentical(a, b);
    }

    public static boolean isANicknameOf(String nickname, String original) { // TAKE NOTE that 'original' is the first(index) name on a line, a nickname might be any of the names at the same line
        //loadNames(); // added to apply tests, uncomment if you want to test
        if (nickname.equals(original))
            return true;
        try {
            List<String> nicks = nickNames.get(original);
            return nicks.stream().anyMatch(str -> str.equals(nickname));
        } catch (Exception e) {
            // name was not found in the database, it is unique.
        }
        return false;
    }

    public static boolean isTypoOrIdentical(String a, String b) {
        return getHammingDistance(a, b) <= 2; // can't be certain, but this is the farthest I'll go for a typo
    }

    public static int getHammingDistance(String a, String b) { // calculated the difference of letter changes that needs to be done between two strings
        char[] A = a.toCharArray();                            // for example -
        char[] B = b.toCharArray();                            // "bloh" and "gluh" have a distance of 2, since you'll need to change 'b' and 'o' to reach the second string.

        int shortestLength = Math.min(a.length(), b.length());
        int longestLength = Math.max(a.length(), b.length());

        int distance = 0;
        for (int i = 0; i < shortestLength; ++i) {
            distance += (A[i] == B[i]) ? 0 : 1;
        }

        distance += longestLength - shortestLength;

        return distance;
    }

    // DONT FORGET TO CALL THIS FUNCTION SOMEWHERE
    public static void loadNames() { // load all names and nicknames as key:value of String:StringList into nickNames
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("namesfile.txt"));
            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] names = line.split(",");
                List<String> nicks = new ArrayList<>();
                for (int i = 1; i < names.length; ++i)
                    nicks.add(names[i]);
                nickNames.put(names[0], nicks);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        loadNames();
        // so far it does nothing with the names, however I added the above function at "isANicknameOf" to check the tests, uncomment it there if you wish to run the tests.
    }

}
