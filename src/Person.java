import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Written by TheSoberRussian on 2/17/15.
 */
public class Person {

    private String firstName;
    private String lastName;
    private String gender;
    private String genderPref;
    private ArrayList<String> qualities;
    private ArrayList<String> answers;
    private ArrayList<Integer> importance;
    private ArrayList<Maker> compatiblePeople;
    public int id;


    public Person(String firstName,String lastName,int id, String gender, String seeking){
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.gender = gender.equals("Male") ? "MALE":"FEMALE";
        String temp = seeking;

        if (temp.equals("Either"))
            genderPref = "BI";
        else if (this.gender.equals("MALE") && temp.equals("Guy"))
            genderPref = "GAY";
        else if (this.gender.equals("MALE") && temp.equals("Girl"))
            genderPref = "STRAIGHT";
        else if (this.gender.equals("FEMALE") && temp.equals("Guy"))
            genderPref = "STRAIGHT";
        else if (this.gender.equals("FEMALE") && temp.equals("Girl"))
            genderPref = "GAY";
    }

    public ArrayList<String> getQualities() {
        return qualities;
    }

    public void setAnswersImportance(String[] array){
        answers = new ArrayList<String>();
        importance = new ArrayList<Integer>();
        qualities = new ArrayList<String>();
        for (int i = 4; i < array.length; i++) {
            if (i % 3 == 1){
                switch (array[i].charAt(0)){
                    case '1': importance.add(0); break;
                    case '2': importance.add(5); break;
                    case '3': importance.add(10); break;
                    case '4': importance.add(50); break;
                    case '5': importance.add(150); break;
                    default: break;
                }
            }
            else if (i % 3 == 2)
                qualities.add(array[i]);
            else
                answers.add(array[i]);

        }
    }

    public String getGender() {
        return gender;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public ArrayList<Integer> getImportance() {
        return importance;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }

    public String getName(){
        return firstName + " " + lastName;
    }

    public String getGenderPref() {
        return genderPref;
    }

    public void matchMake(ArrayList<Person> list){
        compatiblePeople = new ArrayList<Maker>();
        for (Person x: list){
            if (id!=x.id){
                if (genderPref.equals("STRAIGHT")) {
                    if (!gender.equals(x.getGender())) {
                        if (x.getGenderPref().equals("STRAIGHT") || x.getGenderPref().equals("BI")) {
                            Maker temp = new Maker(this, x);
                            temp.Match();
                            compatiblePeople.add(temp);
                        }
                    }
                }else if (genderPref.equals("GAY")) {
                    if (gender.equals(x.getGender())) {
                        if (x.getGenderPref().equals("GAY") || x.getGenderPref().equals("BI")) {
                            Maker temp = new Maker(this, x);
                            temp.Match();
                            compatiblePeople.add(temp);
                        }
                    }
                }else if (genderPref.equals("BI")) {
                    if((x.getGenderPref().equals("STRAIGHT") && !gender.equals(x.getGender())) || (
                            x.getGenderPref().equals("GAY") && gender.equals(x.getGender())) ||
                            (x.getGenderPref().equals("BI"))) {
                        Maker temp = new Maker(this, x);
                        temp.Match();
                        compatiblePeople.add(temp);
                    }
                }
            }
        }
        Collections.sort(compatiblePeople);
    }

    public void results(){
        String out = "";
        for (int i = 0; i < 3 && i < compatiblePeople.size(); i++) {
            System.out.println(compatiblePeople.get(i).toString());
            out+=compatiblePeople.get(i).toString()+"\n";

        }

        try {
            PrintWriter pw = new PrintWriter("results/"+id+".txt");
            pw.print(out);
            pw.close();
        }catch (Exception e){

        }

    }

    @Override
    public String toString() {
        return "Name: " + firstName +" " + lastName +" Gender: " + gender;// + " Orientation: " + genderPref;
    }
}
