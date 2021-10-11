import java.util.*;
import java.io.*;
//5th ka b option handle karna hai

class vaccines {
    private String name;
    private int doses, gap= 0;
    vaccines(int n, String str, int t) {
        name= str;
        doses= n;
        gap= t;
        display();
    }

    int getDoses() {
        return this.doses;
    }

    String getName() {
        return this.name;
    }

    int getGap() {
        return this.gap;
    }

    void display() {
        System.out.print("Vaccine Name: " + name + ", Number of Doses: " + doses + ", Gap Between Doses: " + gap);
    }
}

class hospital {
    private String name;
    private int pin, id;
    private static int cnt=100000;
    hospital(String str, int p) {
        name= str;
        pin= p;
        id= cnt;
        cnt++;
        display();
    }

    private ArrayList<slot> lst= new ArrayList<slot>();
    
    String getName() {
        return this.name;
    }

    int getId() {
        return this.id;
    }

    int getPin() {
        return this.pin;
    }

    ArrayList<slot> getSlots() {
        return this.lst;
    }

    void add_slot(int d, int q, String t) {
        slot temp= new slot(d, q, t);
        lst.add(temp);
        slot_add_conf(d, q, t);
    }

    void slot_add_conf(int d, int q, String t) {
        System.out.println("Slot added by Hospital "+ id +" for Day: "+ d +", Available Quantity: "+ q +" of Vaccine "+ t);
    }

    void disp_slot() {
        int l= lst.size();
        slot temp;
        for(int i=0; i<l; i++) {
            temp=lst.get(i);
            System.out.println("Day: "+temp.getDay()+" Vaccine: "+ temp.getType()+ " Available Qty: "+temp.getQuan());
        }
    }

    int disp_sell(int t, String str, int d) {
        if(t==0) {
            int l= lst.size();
            int count=0;
            slot temp;
            for(int i=0; i<l; i++) {
                temp=lst.get(i);
                if(temp.getQuan()!=0 && temp.getDay()>=d) {
                    count++;
                    System.out.println(i+"-> "+"Day: "+temp.getDay()+ " Available Qty: "+temp.getQuan()+" Vaccine: "+ temp.getType());
                }
            }
            if(count==0) {
                System.out.println("No slots available");
                return 0;
            }
            return 1;
        }

        else {
            int l= lst.size();
            int count=0;
            slot temp;
            for(int i=0; i<l; i++) {
                temp=lst.get(i);
                // System.out.println("Something: "+ temp.getQuan()+ " "+ temp.getDay()+" "+ d);
                if(temp.getType().equals(str) && temp.getQuan()!=0 && temp.getDay()>=d) {
                    count++;
                    System.out.println(i+"-> "+"Day: "+temp.getDay()+ " Available Qty: "+temp.getQuan()+" Vaccine: "+ temp.getType());
                }
            }
            if(count==0) {
                System.out.println("No slots available");
                return 0;
            }
            return 1;
        }
    }

    boolean slot_vacc(String str) {
        int l= lst.size();
        slot temp;
        for(int i=0; i<l; i++) {
            temp=lst.get(i);
            if(temp.getType().equals(str) && temp.getQuan()!=0) {
                return true;
            }
        }
        return false;
    }

    slot slot_upd(int n, int t, String str, int d) {
        if(t==0) {
            int l= lst.size();
            slot temp=null;
            for(int i=0; i<l; i++) {
                temp=lst.get(i);
                if(temp.getQuan()!=0 && temp.getDay()>=d) {
                    // System.out.println("n: "+n);
                    // System.out.println("n_type: "+temp.type);
                    if(n==0) {
                        temp.setQuan(temp.getQuan()-1);
                        return temp;
                    }
                    else {
                        n--;
                    }
                }
            }
        }
        else {
            int l= lst.size();
            slot temp;
            for(int i=0; i<l; i++) {
                temp=lst.get(i);
                if(temp.getType().equals(str) && temp.getQuan()!=0 && temp.getDay()>=d) {
                    if(n==0) {
                        temp.setQuan(temp.getQuan()-1);
                        return temp;
                    }
                    else {
                        n--;
                    }
                }
            }
        }
        System.out.println("Invalid slot entered");
        return null;
    }

    void display() {
        System.out.print("Hospital Name: " + name + ", Pincode: " + pin + ", Unique ID: " + id);
    }

    void vacc_check() {
    }
}

class citizen {
    private String name, uid;
    private int age, status= -1;
    private vaccines vac;                                               
    private int due_date;                                               
    citizen(String str, int ag, String u) {
        name = str;
        age = ag;
        uid = u;
        status = 0;
        due_date = 0;
        display();
    }

    String getName() {
        return this.name;
    }

    String getUid() {
        return this.uid;
    }

    int getAge() {
        return this.age;
    }

    int getStatus() {
        return this.status;
    }

    int getDueDate() {
        return this.due_date;
    }

    vaccines getVac() {
        return this.vac;
    }

    void setStatus(int s) {
        this.status = s;
    }

    void setDueDate(int d) {
        this.due_date= d;
    }

    void setVac(vaccines v) {
        this.vac= v;
    }

    void vaccinated() {
        System.out.println(name+" vaccinated with "+ vac.getName());
    }

    void display() {
        System.out.print("Citizen Name: " + name + ", Age: " + age + ", Unique ID: " + uid);
    }

    void status_disp() {
        if(status==0) {
            System.out.println("Citizen REGISTERED");
        }
        else {
            if(status<vac.getDoses()) {
                System.out.println("PARTIALLY VACCINATED");
                System.out.println("Vaccine Given: "+ vac.getName());
                System.out.println("Number of Doses given: "+ status);
                System.out.println("Next Dose due date: "+ due_date);
            }
            else {
                System.out.println("FULLY VACCINATED");
                System.out.println("Vaccine Given: "+ vac.getName());
                System.out.println("Number of Doses given: "+ status);
            }
        }
    }
}

class slot {
    private int day, quan;
    private String type;
    slot(int d, int q, String t) {
        // num= n;
        day= d;
        quan = q;
        type= t;
    }

    int getDay() {
        return this.day;
    }

    int getQuan() {
        return this.quan;
    }

    String getType() {
        return this.type;
    }

    void setDay(int d) {
        day= d;
    }

    void setQuan(int q) {
        quan= q;
    }

    void setType(String s) {
        type= s;
    }
    // void display() {
    //     System.out.print("Slot added by Hospital " + id + " for Day " + day + ", Available Quantity: " + quan + " of Vaccine " + type);
    // }
}

public class Assign_1 {
    static void display_menu() {
        System.out.println("\n---------------------------------");
        System.out.println("MENU");
        System.out.println("---------------------------------");
        System.out.println("1. Add Vaccine");
        System.out.println("2. Register Hospital");
        System.out.println("3. Register Citizen");
        System.out.println("4. Add slots for Vaccination");
        System.out.println("5. Book slots for Vaccination");
        System.out.println("6. List all slot for a hospital");
        System.out.println("7. Check Vaccination Status");
        System.out.println("8. Exit");
        System.out.println("---------------------------------");
    }

    public static void main(String[] args) {

        System.out.println("\nCoWin Portal initialized....");

        int ch;

        ArrayList<vaccines> vacc= new ArrayList<vaccines>();
        ArrayList<hospital> hosp= new ArrayList<hospital>();
        ArrayList<citizen> cit= new ArrayList<citizen>();
 
        Scanner scan= new Scanner(System.in);
        while(true) {
            display_menu();
            ch= scan.nextInt();

            if(ch==1) {
                System.out.println("Vaccine Name: ");
                String str= scan.next();
                System.out.println("Number of Doses: ");
                int doses= scan.nextInt();
                int gap=0;
                if(doses==1) {
                }
                else {
                    System.out.println("Gap Between Doses: ");
                    gap= scan.nextInt();
                }
                vacc.add(new vaccines(doses, str, gap));
            }

            else if(ch==2) {
                System.out.println("Hospital Name: ");
                String name= scan.next();
                System.out.println("PinCode: ");
                int pin= scan.nextInt();
                hosp.add(new hospital(name, pin));
            }

            else if(ch==3) {
                System.out.println("Citizen Name: ");
                String name= scan.next();
                System.out.println("Age: ");
                int age= scan.nextInt();
                System.out.println("Unique ID: ");
                String uid= scan.next();
                if(age<18) {
                    System.out.println("Only above 18 allowed");
                }
                else {
                    cit.add(new citizen(name, age, uid));
                }
            }

            else if(ch==4) {
                System.out.println("Hospital ID: ");
                int uid= scan.nextInt();
                int l1= hosp.size();
                int l2= vacc.size();

                hospital temp=null; // Finding the hospital element
                for(int i=0; i<l1; i++) {
                    temp= hosp.get(i);
                    if(temp.getId()==uid) {
                        break;
                    }
                    if(i==l1-1) {
                        System.out.println("No such hospital found!");
                        temp=null;
                    }
                }

                if(l1==0) {
                    System.out.println("No such hospital found!");
                }

                if(temp!=null) {
                    System.out.println("Enter the number of Slots to be added: ");
                    int n= scan.nextInt();
                    for(int i=0; i<n; i++) {
                        System.out.println("Enter Day Number: ");
                        int d= scan.nextInt();

                        System.out.println("Enter Quantity: ");
                        int q= scan.nextInt();

                        System.out.println("Select Vaccine");

                        vaccines vacc_temp;// Printing vaccine list
                        for(int j=0; j<l2; j++) {
                            vacc_temp= vacc.get(j);
                            System.out.println(j+". " + vacc_temp.getName());
                        }

                        int t= scan.nextInt();
                        if(t>=0 && t<l2) {
                            vacc_temp= vacc.get(t);
                            String name= vacc_temp.getName();

                            temp.add_slot(d, q, name);
                        }
                        else {
                            System.out.println("Invalid vaccine selected!");
                        }
                    }
                }
            }

            else if(ch==5) {                 //Update part B of it
                System.out.println("Enter patient Unique ID");
                String cit_uid= scan.next();
                citizen cit_temp=null;
                int l1= cit.size();
                for(int i=0;i<l1;i++) {
                    cit_temp= cit.get(i);
                    if(cit_temp.getUid().equals(cit_uid)) {
                        break;
                    }
                    if(i==l1-1) {
                        System.out.println("No such patient found!");
                        cit_temp=null;
                    }
                }

                if(l1==0) {
                    System.out.println("No such patient found!");
                }

                if(cit_temp!=null) {
                    int cit_stat= cit_temp.getStatus();
                    String cit_vacc= "";
                    if(cit_stat!=0) {
                        vaccines temp2_vac= cit_temp.getVac();
                        cit_vacc= temp2_vac.getName();
                    }

                    System.out.println("1. Search by area");
                    System.out.println("2. Search by Vaccine");
                    System.out.println("3. Exit");
                    System.out.println("Enter option: ");
                    int ch_2= scan.nextInt();

                    if(ch_2==1) {               // Searching by Pin
                        System.out.println("Enter PinCode: ");
                        int pc= scan.nextInt();

                        int l= hosp.size();
                        hospital temp=null;
                        for(int i=0; i<l; i++) {
                            temp= hosp.get(i);
                            if(temp.getPin()==pc) {
                                System.out.println(temp.getId()+" "+temp.getName());
                            }
                        }

                        int rt_slot=0;
                        System.out.println("Enter Hospital ID");
                        int hosp_uid= scan.nextInt();
                        for(int i=0; i<l; i++) {
                            temp= hosp.get(i);
                            if(temp.getId()==hosp_uid) {
                                rt_slot=temp.disp_sell(cit_stat, cit_vacc, cit_temp.getDueDate());
                                break;
                            }
                        }

                        if(rt_slot==1) {
                            System.out.println("Choose Slot: ");
                            int ch_3= scan.nextInt();
                            slot temp_slot= temp.slot_upd(ch_3, cit_stat, cit_vacc, cit_temp.getDueDate());
                            // System.out.println("Hello: "+ temp_slot.type);

                            if(temp_slot!=null) {
                                vaccines temp_vac=null;
                                int l_vac= vacc.size();
                                for(int i=0; i<l_vac; i++) {
                                    temp_vac=vacc.get(i);
                                    // System.out.println("Testing: "+ temp_vac.getName());
                                    if(temp_vac.getName().equals(temp_slot.getType())) {
                                        // System.out.println("hello");
                                        break;
                                    }
                                }

                                System.out.println(temp_vac.getName());
                                if(cit_temp.getStatus()==0) {
                                    cit_temp.setStatus(1);
                                    cit_temp.setVac(temp_vac);
                                    cit_temp.setDueDate(temp_slot.getDay()+temp_vac.getGap());
                                }
                                else {
                                    cit_temp.setStatus(cit_temp.getStatus()+1);
                                    cit_temp.setDueDate(temp_slot.getDay()+temp_vac.getGap());
                                }
                                cit_temp.vaccinated();
                            }
                        }
                    }

                    else if(ch_2==2) {              //Searching by Vaccine
                        System.out.println("Enter Vaccine name: ");
                        String vacc_name= scan.next();

                        int l_hosp= hosp.size();
                        hospital temp_hosp= null;
                        ArrayList<hospital> lst_hosp= new ArrayList<hospital>();

                        for(int i=0;i<l_hosp;i++) {
                            temp_hosp= hosp.get(i);                     //Continue from here
                            if(temp_hosp.slot_vacc(vacc_name)==true) {
                                lst_hosp.add(temp_hosp);
                            }
                        }

                        int l_ch= lst_hosp.size();
                        for(int i=0;i<l_ch;i++) {
                            temp_hosp=lst_hosp.get(i);
                            System.out.println(temp_hosp.getName()+" "+temp_hosp.getId());
                        }

                        System.out.println("Enter hospital id: ");
                        int id_ch= scan.nextInt();
                        for(int i=0;i<l_ch;i++) {
                            temp_hosp=lst_hosp.get(i);
                            // System.out.println("ID Comp: " + temp_hosp.getId()+" "+ id_ch);
                            if(temp_hosp.getId()==id_ch) {
                                break;
                            }
                        }

                        // System.out.println("hello: "+ cit_temp.getDueDate());
                        int rt_slot=temp_hosp.disp_sell(1, vacc_name, cit_temp.getDueDate());
                        // System.out.println("hello: "+rt_slot);
                        if(rt_slot==1) {
                            System.out.println("Choose Slot: ");
                            int ch_3= scan.nextInt();
                            slot temp_slot= temp_hosp.slot_upd(ch_3, cit_stat, cit_vacc, cit_temp.getDueDate());

                            if(temp_slot!=null) {
                                vaccines temp_vac=null;
                                int l_vac= vacc.size();
                                for(int i=0; i<l_vac; i++) {
                                    temp_vac=vacc.get(i);
                                    if(temp_vac.getName().equals(temp_slot.getType())) {
                                        break;
                                    }
                                }

                                if(cit_temp.getStatus()==0) {
                                    cit_temp.setStatus(cit_temp.getStatus()+1);
                                    cit_temp.setVac(temp_vac);
                                    cit_temp.setDueDate(temp_slot.getDay()+temp_vac.getGap());
                                }
                                else {
                                    cit_temp.setStatus(cit_temp.getStatus()+1);
                                    cit_temp.setDueDate(temp_slot.getDay()+temp_vac.getGap());
                                }
                                cit_temp.vaccinated();
                            }
                        }
                    }

                    else if(ch_2==3) {
                    }
                }
            }

            else if(ch==6) {
                System.out.println("Enter Hospital Id: ");
                int uid= scan.nextInt();
                int l=hosp.size();
                hospital temp;
                for(int i=0; i<l; i++) {
                    temp=hosp.get(i);
                    if(temp.getId()==uid) {
                        temp.disp_slot();
                        break;
                    }
                    if(i==l-1) {
                        System.out.println("No such hospital found!");
                    }
                }

                if(l==0) {
                    System.out.println("No such hospital found!");
                }
            }

            else if(ch==7) {
                System.out.println("Enter Patient ID: ");
                String cit_uid = scan.next();
                int l= cit.size();
                citizen temp;
                for(int i=0;i<l;i++) {
                    temp= cit.get(i);
                    if(temp.getUid().equals(cit_uid)) {
                        temp.status_disp();
                        break;
                    }
                    if(i==l-1) {
                        System.out.println("No such patient found!");
                    }
                }

                if(l==0) {
                    System.out.println("No such patient found!");
                }
            }

            else if(ch==8) {
                break;
            }

            else {
                System.out.println("Please enter a valid choice!");
            }
        }
        scan.close();
    }
}