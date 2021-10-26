import java.util.*;
import java.io.*;

class player {
    private String name;
    private int score, pos;
    private floora flr;
    private dice die;
    private map mp;
    private int fin;

    player(Scanner scan, String n, map m) {
        name = n;
        mp= m;
        score = 0;
        flr= null;
        pos= -1;
        die= new dice(2);
        fin= m.getLen();
    }

    public void play(Scanner scan) {
        start(scan);
        while(pos!=fin) {
            move(scan);
        }
        System.out.println("Game over");
        System.out.println(name+ " accumulated "+ score+ " points");
    }

    public void start(Scanner scan) {
        scan.nextLine();
        while(roll(scan)!=1) {
            System.out.println("Game cannot start until you get 1");
        }
        pos=0;
        flr= mp.getFlr(0);
        score+=1;
        System.out.println("Player position Floor-" + pos);
        System.out.println(name+" "+flr.disp());
        System.out.println("Total points "+ score);
    }

    public void move(Scanner scan) {
        int temp= roll(scan);
        if(pos+temp<=fin) {
            pos+= temp;
            flr_upd(temp);
            System.out.println("Player position Floor-" + pos);
            System.out.println(name+" "+flr.disp());
            System.out.println("Total points "+ score);

            int jp= flr.getJump();
            if(jp!=0) {
                pos+=jp;
                flr_upd(jp);
                System.out.println("Player position Floor-" + pos);
                System.out.println(name+" "+flr.disp());
                System.out.println("Total points "+ score);
                }
        }
        else {
            System.out.println("Player cannot move");
        }
    }

    public int roll(Scanner scan) {
        System.out.println("Hit Enter to roll the dice: ");
        scan.nextLine();
        die.roll();
        System.out.println("Dice gave "+ die.getFaceValue());
        return die.getFaceValue();
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public floora getFlr() {
        return flr;
    }
    
    public void setScore(int s) {
        score = s;
    }

    public void flr_upd(int p) {
        flr= mp.getFlr(pos);
        score+=flr.getPoint();
    }
}

class dice {
    private final int num_face;
    private int face_value;

    dice(int n) {
        num_face = n;
    }

    public void roll() {
        Random rand = new Random();
        int curr_face= rand.nextInt(num_face);
        setFaceValue(curr_face+1);
    }

    private void setFaceValue(int val) {
        if(val<=num_face) {
            face_value = val;
        }
    }

    public int getFaceValue() {
        return face_value;
    }

    public int getNumFaces() {
        return num_face;
    }

    public String toString() {
        return "number of Faces " + num_face +" current face value "+ face_value;
    }
}

class map {
    private int len;
    ArrayList<floora> lst= new ArrayList<floora>();

    map(int n, ArrayList<floora> some) {
        len=n;
        for(int i=0; i<=len; i++) {
            lst.add(some.get(i));
        }
    }

    int getLen() {
        return len;
    }

    floora getFlr(int i) {
        return lst.get(i);
    }
}

class floora {
    private String type;
    private int point;
    private int jump;

    floora(int p, String t, int j) {
        point = p;
        type = t;
        jump = j;
    }

    String disp() {
        return "has reached an empty floor";
    }

    int getPoint() {
        return point;
    }

    String getType() {
        return type;
    }

    int getJump() {
        return jump;
    }
}

class snake extends floora {
    int jump_val= -4;

    snake() {
        super(-2, "Snake", -4);
    }

    @Override
    String disp() {
        return "You has reached a snake level";
    }
}

class cobra extends floora {
    int jump_val= -8;

    cobra() {
        super(-4, "King Cobra", -8);
    }

    @Override
    String disp() {
        return "You has reached the King Cobra level";
    }
}

class ladder extends floora {
    int jump_val= 4;

    ladder() {
        super(2, "Ladder", 4);
    }

    @Override
    String disp() {
        return "You has reached a ladder level";
    }
}

class elevator extends floora {
    int jump_val= 8;

    elevator() {
        super(4, "Elevator", 8);
    }

    @Override
    String disp() {
        return "You has reached an Elevator level";
    }
}

public class Assign_3 {
    public static void main(String[] args) {
        Scanner scan= new Scanner(System.in);
        ArrayList<floora> lst= new ArrayList<floora>();
        ArrayList<floora> lst1=new ArrayList<floora>();
        lst.add(new floora(1, "Empty", 0));
        lst.add(new floora(1, "Empty", 0));
        lst.add(new elevator());
        lst.add(new floora(1, "Empty", 0));
        lst.add(new floora(1, "Empty", 0));
        lst.add(new snake());
        lst.add(new floora(1, "Empty", 0));
        lst.add(new floora(1, "Empty", 0));
        lst.add(new ladder());
        lst.add(new floora(1, "Empty", 0));
        lst.add(new floora(1, "Empty", 0));
        lst.add(new cobra());
        lst.add(new floora(1, "Empty", 0));
        lst.add(new floora(1, "Empty", 0));

        map m= new map(13, lst);
        map m1= null;
        int map_pref=1;

        System.out.println("Would you like to create a new map?");
        System.out.println("Enter 1 for yes");
        System.out.println("Enter 2 for no");
        int ch= Integer.parseInt(scan.nextLine());
        if(ch==1) {
            System.out.println("Enter number of floors: ");
            int n= Integer.parseInt(scan.nextLine());
            for(int i=0; i<=n; i++) {
                System.out.println("Floor "+i+" will be: ");
                System.out.println("1. Empty floor");
                System.out.println("2. Ladder floor");
                System.out.println("3. Snake floor");
                System.out.println("4. Elevator floor");
                System.out.println("5. King Cobra floor");
                int ch1= Integer.parseInt(scan.nextLine());
                if(ch1==1) {
                    lst1.add(new floora(1, "Empty", 0));
                }
                else if(ch1==2) {
                    lst1.add(new ladder());
                }
                else if(ch1==3) {
                    lst1.add(new snake());
                }
                else if(ch1==4) {
                    lst1.add(new elevator());
                }
                else if(ch1==5) {
                    lst1.add(new cobra());
                }
            }

            m1= new map(n, lst1);

            System.out.println("Choose map:");
            System.out.println("1. Default Map");
            System.out.println("2. Created Map");

            map_pref= Integer.parseInt(scan.nextLine());
        }

        System.out.println("Enter the player name and hit enter:");
        String nm= scan.next();
        player temp;
        
        if(map_pref==2) {
            temp= new player(scan, nm, m1);
        }
        else {
            temp= new player(scan, nm, m);
        }

        temp.play(scan);
        scan.close();
    }
}