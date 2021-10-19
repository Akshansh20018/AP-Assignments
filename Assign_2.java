import java.util.*;
import java.io.*;

interface lec_mat {
    void disp();
}

class lec_slide implements lec_mat {
    private String title;
    private int count;
    private String[] slides;
    private Date date;
    private String uploader;

    lec_slide(String n, int c, String[] arr, String u) {
        this.title = n;
        this.count = c;
        this.slides = new String[c];
        this.date = new Date();
        this.uploader = u;
        for (int i = 0; i < c; i++) {
            slides[i] =arr[i];
        }
    }
    
    String getTitle() {
        return title;
    }

    int getCount() {
        return count;
    }

    String getSlides(int i) {
        return slides[i];
    }

    Date getDate() {
        return date;
    }

    String getUploader() {
        return uploader;
    }

    @Override
    public void disp() {
        System.out.println("Title: "+ title);
        for(int j=0; j<count; j++) {
            System.out.println("Slide "+(j+1)+": "+ slides[j]);
        }
        System.out.println("Number of slides: "+ count);
        System.out.println("Date of upload: "+ date);
        System.out.println("Uploaded by: "+ uploader);
        System.out.println("");
    }
}

class lec_vid implements lec_mat{
    private String title;
    private String file_name;
    private Date date;
    private String uploader;

    lec_vid(String n, String c, String u) {
        this.title = n;
        this.file_name = c;
        this.date = new Date();
        this.uploader = u;
    }

    String getTitle() {
        return title;
    }

    String getFileName() {
        return file_name;
    }

    Date getDate() {
        return date;
    }

    String getUploader() {
        return uploader;
    }

    @Override
    public void disp() {
        System.out.println("Title of video: "+ title);
        System.out.println("Video file: "+ file_name);
        System.out.println("Date of upload: "+ date);
        System.out.println("Uploaded by: "+ uploader);
        System.out.println("");
    }
}

interface assessment {
    void setAssessment(String str, int n, ArrayList<student> students);
    void disp();
    String getProblem();
    int getSol();
    void addSubmission(submission sub);
    void gradeSubmissions(Scanner scan, instructor ins);
    void closeAssessment(ArrayList<student> students);
    // void closeAssessment();
}

class assignment implements assessment{
    private String problem_statement;
    private int marks;
    private ArrayList<student> assigned_to= new ArrayList<student>();
    private ArrayList<submission> submissions= new ArrayList<submission>();

    assignment() {}

    @Override
    public void setAssessment(String str, int n, ArrayList<student> students) {
        problem_statement= str;
        marks= n;
        int len= students.size();
        for(int i=0; i<len; i++) {
            student temp= students.get(i);
            assigned_to.add(temp);
            temp.add_assessments(this);
        }
    }

    @Override
    public void closeAssessment(ArrayList<student> students) {
        int len= students.size();
        for(int i=0; i<len; i++) {
            student temp= students.get(i);
            temp.clo_assessments(this);
        }
    }

    @Override
    public void disp() {
        System.out.println("Assignment: "+ problem_statement+" Max Marks: "+ marks);
    }

    @Override
    public String getProblem() {
        return problem_statement;
    }

    @Override
    public int getSol() {
        System.out.println("Enter filename of assignment: ");
        return 0;
    }

    @Override
    public void addSubmission(submission sub) {
        submissions.add(sub);
    }

    @Override
    public void gradeSubmissions(Scanner scan, instructor ins) {
        int len = submissions.size();
        int count= 0;
        for(int i=0; i<len; i++) {
            submission temp= submissions.get(i);
            if(temp.getGrade()==-1) {
                System.out.println(count+". "+temp.getStudent().getName());
                count++;
            }
        }
        if(count==0) {
            System.out.println("No ungraded submissions");
            return;
        }
        System.out.println("Choose ID from these ungraded submissions");
        int checking=  Integer.parseInt(scan.nextLine());
        submission active_sub=null;
        for(int i=0; i<len; i++) {
            submission temp= submissions.get(i);
            if(temp.getGrade()==-1) {
                if(checking==0) {
                    active_sub= temp;
                    break;
                }
                checking--;
            }
        }
        System.out.println("Submission: "+ active_sub.getAnswer());
        System.out.println("Max Marks: "+ marks);
        System.out.println("Marks scored: ");
        int temp_grade=  Integer.parseInt(scan.nextLine());
        active_sub.setGrade(temp_grade);
        active_sub.setGrader(ins);
    }
}

class quiz implements assessment{
    private String question;
    private int marks=1;
    private ArrayList<student> assigned_to= new ArrayList<student>();
    private ArrayList<submission> submissions= new ArrayList<submission>();

    @Override
    public void setAssessment(String str, int n, ArrayList<student> students) {
        question= str;
        int len= students.size();
        for(int i=0; i<len; i++) {
            assigned_to.add(students.get(i));
            students.get(i).add_assessments(this);
        }
    }

    @Override
    public void closeAssessment(ArrayList<student> students) {
        int len= students.size();
        for(int i=0; i<len; i++) {
            student temp= students.get(i);
            temp.clo_assessments(this);
        }
    }

    @Override
    public void disp() {
        System.out.println("Question: "+ question);
    }

    @Override
    public String getProblem() {
        return question;
    }
    @Override
    public int getSol() {
        System.out.println(question);
        return 1;
    }

    @Override
    public void addSubmission(submission sub) {
        submissions.add(sub);
    } 

    @Override
    public void gradeSubmissions(Scanner scan, instructor ins) {
        int len = submissions.size();
        int count= 0;
        for(int i=0; i<len; i++) {
            submission temp= submissions.get(i);
            if(temp.getGrade()==-1) {
                count++;
                System.out.println(i+". "+temp.getStudent().getName());
            }
        }
        if(count==0) {
            System.out.println("No ungraded submissions");
            return;
        }
        System.out.println("Choose ID from these ungraded submissions");
        int checking=  Integer.parseInt(scan.nextLine());
        submission active_sub=null;
        for(int i=0; i<len; i++) {
            submission temp= submissions.get(i);
            if(temp.getGrade()==-1) {
                if(checking==0) {
                    active_sub= temp;
                    break;
                }
                checking--;
            }
        }
        System.out.println("Submission: "+ active_sub.getAnswer());
        System.out.println("Max Marks: "+ marks);
        System.out.println("Marks scored: ");
        int temp_grade=  Integer.parseInt(scan.nextLine());
        active_sub.setGrade(temp_grade);
        active_sub.setGrader(ins);
    }
}

class submission {
    private String answer;
    private student stud;
    private int grade=-1;
    private instructor grader;

    submission(String str, String nm, student s) {
        answer=str;
        stud= s;
    }

    student getStudent() {
        return stud;
    }

    int getGrade() {
        return grade;
    }

    void setGrade(int g) {
        grade = g;
    }

    String getAnswer() {
        return answer;
    }

    void setGrader(instructor ins) {
        grader= ins;
    }

    void disp() {
        System.out.println("Submission: "+ answer);
        System.out.println("Marks scored: "+ grade);
        System.out.println("Graded by: "+ grader.getName());
    }
}

interface user {
    void add_comm(String str, ArrayList<String> txt, ArrayList<Date> time);
    void view_comm(ArrayList<String> txt, ArrayList<Date> time);
    void display();
    String getName();
}

class instructor implements user {
    private String name;

    instructor(String str) {
        this.name = str;
    }

    @Override
    public void display() {
        System.out.println("");
        System.out.println("Welcome "+name);
        System.out.println("INSTRUCTOR MENU");
        System.out.println("1. Add class material");
        System.out.println("2. Add assessments");
        System.out.println("3. View lecture materials");
        System.out.println("4. View assessments");
        System.out.println("5. Grade assessments");
        System.out.println("6. Close assessments");
        System.out.println("7. View comments");
        System.out.println("8. Add comments");
        System.out.println("9. Logout");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void add_comm(String str, ArrayList<String> txt, ArrayList<Date> time) {
        txt.add(str+" - "+name);
        time.add(new Date());
    }

    @Override
    public void view_comm(ArrayList<String> txt, ArrayList<Date> time) {
        int len= txt.size();
        for (int i=0; i<len; i++) {
            System.out.println(txt.get(i));
            System.out.println(time.get(i));
            System.out.println();
        }
    }
}

class student implements user {
    private String name;
    private ArrayList<assessment> pending= new ArrayList<assessment>();
    private ArrayList<assessment> submitted= new ArrayList<assessment>();
    private ArrayList<submission> grades= new ArrayList<submission>();

    student(String str) {
        name= str;
    }

    @Override
    public void display() {
        System.out.println("");
        System.out.println("Welcome "+name);
        System.out.println("STUDENT MENU");
        System.out.println("1. View lecture materials");
        System.out.println("2. View assessments");
        System.out.println("3. Submit assessments");
        System.out.println("4. View grades");
        System.out.println("5. View comments");
        System.out.println("6. Add comments");
        System.out.println("7. Logout");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void add_comm(String str, ArrayList<String> txt, ArrayList<Date> time) {
        txt.add(str+" - "+name);
        time.add(new Date());
    }

    @Override
    public void view_comm(ArrayList<String> txt, ArrayList<Date> time) {
        int len= txt.size();
        for (int i=0; i<len; i++) {
            System.out.println(txt.get(i));
            System.out.println(time.get(i));
            System.out.println();
        }
    }

    void add_assessments(assessment fresh) {
        this.pending.add(fresh);
    }

    void clo_assessments(assessment closed) { // inserted new
        int len= pending.size();
        for(int i=0; i<len; i++) {
            assessment temp= pending.get(i);
            if(temp.getProblem().equals(closed.getProblem())) {
                pending.remove(i);
                break;
            }
        }
    }

    void submit_assessment(Scanner scan) {
        int len= this.pending.size();
        if(len==0) {
            System.out.println("No pending assessments");
            return;
        }
        System.out.println("Pending assessments");
        for(int i=0; i<len; i++) {
            assessment temp=this.pending.get(i);
            System.out.println("ID: "+ i);
            temp.disp();
        }
        System.out.println("Enter ID of assessment: ");
        int choice= Integer.parseInt(scan.nextLine());
        assessment active_assessment=pending.get(choice);
        int type= active_assessment.getSol();
        String answer= scan.nextLine();
        int ans_len= answer.length();
        if((type==0 && ".zip".equals(answer.substring(ans_len-4, ans_len))) || type==1) {
            pending.remove(choice);
            submitted.add(active_assessment);
            submission temp= new submission(answer, this.name, this);
            active_assessment.addSubmission(temp);
            grades.add(temp);
        }
        else {
            System.out.println("Invalid file name!");
        }
    }

    void view_grades() {
        int len= grades.size();
        System.out.println("Graded Submissions");
        for(int i=0; i<len; i++) {
            if(grades.get(i).getGrade()!=-1) {
                grades.get(i).disp();
            }
        } 
        System.out.println("");
        System.out.println("Ungraded Submissions");
        for(int i=0; i<len; i++) {
            if(grades.get(i).getGrade()==-1) {
                System.out.println(grades.get(i).getAnswer());
            }
        } 
    }
}

public class Assign_2 {
    static void display() {
        System.out.println("Welcome to Backpack");
        System.out.println("1. Enter as instructor");
        System.out.println("2. Enter as student");
        System.out.println("3. Exit");
    }
    public static void main(String[] args) {
        //Instructors ArrayList
        ArrayList<instructor> instructor_list = new ArrayList<instructor>();
        instructor_list.add(new instructor("I0"));
        instructor_list.add(new instructor("I1"));

        //Students ArrayList
        ArrayList<student> student_list = new ArrayList<student>();
        student_list.add(new student("S0"));
        student_list.add(new student("S1"));
        student_list.add(new student("S2"));

        //Comments ArrayList
        ArrayList<String> comment_text= new ArrayList<String>();
        ArrayList<Date> comment_time= new ArrayList<Date>();

        //Class Material ArrayList
        ArrayList<lec_slide> lecture_slides= new ArrayList<lec_slide>();
        ArrayList<lec_vid> lecture_recording= new ArrayList<lec_vid>();

        //Assessment ArrayList
        ArrayList<assessment> assessment_open= new ArrayList<assessment>();
        ArrayList<assessment> assessment_closed= new ArrayList<assessment>();
        ArrayList<assessment> assessment_all= new ArrayList<assessment>();

        Scanner scan= new Scanner(System.in);

        while(true) {
            display();
            int ch=  Integer.parseInt(scan.nextLine());

            if(ch==1) {
                System.out.println("Instructors: ");
                int len_ins= instructor_list.size();
                for(int i=0; i<len_ins; i++) {
                    user temp= instructor_list.get(i);
                    System.out.println(i+ " - "+temp.getName());
                }
                int ins_ch=  Integer.parseInt(scan.nextLine());
                instructor instructor_active= instructor_list.get(ins_ch);

                while(true) {
                    instructor_active.display();
                    ins_ch=  Integer.parseInt(scan.nextLine());

                    if(ins_ch==1) {
                        System.out.println("1. Add Lecture Slide");
                        System.out.println("2. Add Lecture Video");
                        int pref=  Integer.parseInt(scan.nextLine());
                        if(pref==1) {
                            System.out.println("Enter topic of slides: ");
                            String topic= scan.nextLine();
                            System.out.println("Enter number of slides: ");
                            int num=  Integer.parseInt(scan.nextLine());
                            String[] content= new String[num];
                            System.out.println("Enter content of slides");
                            for(int i=0; i<num; i++) {
                                System.out.println("Content of slide "+ (i+1)+": ");
                                content[i]=scan.nextLine();
                            }
                            lecture_slides.add(new lec_slide(topic, num, content, instructor_active.getName()));
                        }

                        else if(pref==2) {
                            System.out.println("Enter topic of video: ");
                            String topic= scan.nextLine();
                            System.out.println("Enter filename of video: ");
                            String file_name= scan.nextLine();
                            int filename_len= file_name.length();
                            if(".mp4".equals(file_name.substring(filename_len-4, filename_len))) {
                                lecture_recording.add(new lec_vid(topic, file_name, instructor_active.getName()));
                            }
                            else {
                                System.out.println("Invalid file!");
                            }
                        }
                    }

                    else if(ins_ch==2) {
                        System.out.println("1. Add Assignment");
                        System.out.println("2. Add Quiz");
                        int pref=  Integer.parseInt(scan.nextLine());
                        if(pref==1) {
                            System.out.println("Enter problem statement: ");
                            String problem_statement= scan.nextLine();
                            System.out.println("Enter max  marks: ");
                            int max_marks=  Integer.parseInt(scan.nextLine());
                            assessment temp= new assignment();
                            temp.setAssessment(problem_statement, max_marks, student_list);
                            assessment_open.add(temp);
                            assessment_all.add(temp);
                        }
                        else if(pref==2) {
                            System.out.println("Enter quiz question: ");
                            String problem_statement= scan.nextLine();
                            assessment temp= new quiz();
                            temp.setAssessment(problem_statement, pref, student_list);
                            assessment_open.add(temp);
                            assessment_all.add(temp);
                        }
                    }

                    else if(ins_ch==3) {
                        int len_slide= lecture_slides.size();
                        int len_vid= lecture_recording.size();

                        lec_mat temp;

                        for(int i=0; i<len_slide; i++) {
                            temp= lecture_slides.get(i);
                            temp.disp();
                        }
                        for(int i=0; i<len_vid; i++) {
                            temp= lecture_recording.get(i);
                            temp.disp();
                        }
                    }

                    else if(ins_ch==4) {
                        int len_assessments= assessment_all.size();
                        assessment temp;
                        for(int i=0; i<len_assessments; i++) {
                            temp= assessment_all.get(i);
                            System.out.println("ID "+ i);
                            temp.disp();
                            System.out.println("------------");
                        }
                    }

                    else if(ins_ch==5) {
                        System.out.println("List of assessment:");
                        int len_assessments= assessment_all.size();
                        assessment temp;
                        for(int i=0; i<len_assessments; i++) {
                            temp= assessment_all.get(i);
                            System.out.println("ID "+ i);
                            temp.disp();
                            System.out.println("------------");
                        }
                        int pref=  Integer.parseInt(scan.nextLine());
                        assessment active_assessment= assessment_all.get(pref);
                        active_assessment.gradeSubmissions(scan, instructor_active);
                    }

                    else if(ins_ch==6) {
                        int len_assessments= assessment_open.size();
                        assessment temp;
                        for(int i=0; i<len_assessments; i++) {
                            System.out.println("ID: "+ i);
                            temp= assessment_open.get(i);
                            temp.disp();
                            System.out.println("------------");
                        }
                        int pref=  Integer.parseInt(scan.nextLine());
                        temp= assessment_open.get(pref);
                        assessment_open.remove(pref);
                        assessment_closed.add(temp);
                        temp.closeAssessment(student_list);
                    }

                    else if(ins_ch==7) {
                        instructor_active.view_comm(comment_text, comment_time);
                    }

                    else if(ins_ch==8) {
                        System.out.println("Enter comment: ");
                        String comm_inp= scan.nextLine();
                        instructor_active.add_comm(comm_inp, comment_text, comment_time);
                    }

                    else if(ins_ch==9) {
                        break;
                    }
                }
            }

            else if(ch==2) {
                System.out.println("Students: ");
                int len_stu= student_list.size();
                for(int i=0; i<len_stu; i++) {
                    user temp= student_list.get(i);
                    System.out.println(i+ " - "+temp.getName());
                }
                int stu_ch= Integer.parseInt(scan.nextLine());
                student student_active= student_list.get(stu_ch);

                while(true) {
                    student_active.display();
                    stu_ch= Integer.parseInt(scan.nextLine());

                    if(stu_ch==1) {
                        int len_slide= lecture_slides.size();
                        int len_vid= lecture_recording.size();

                        lec_mat temp;

                        for(int i=0; i<len_slide; i++) {
                            temp= lecture_slides.get(i);
                            temp.disp();
                        }
                        for(int i=0; i<len_vid; i++) {
                            temp= lecture_recording.get(i);
                            temp.disp();
                        }
                    }

                    else if(stu_ch==2) {
                        int len_assessments= assessment_all.size();
                        assessment temp;
                        for(int i=0; i<len_assessments; i++) {
                            temp= assessment_all.get(i);
                            System.out.println("ID "+ i);
                            temp.disp();
                            System.out.println("------------");
                        }
                    }

                    else if(stu_ch==3) {
                        student_active.submit_assessment(scan);
                    }

                    else if(stu_ch==4) {
                        student_active.view_grades();
                    }

                    else if(stu_ch==5) {
                        student_active.view_comm(comment_text, comment_time);
                    }

                    else if(stu_ch==6) {
                        System.out.println("Enter comment: ");
                        String comm_inp= scan.nextLine();
                        student_active.add_comm(comm_inp, comment_text, comment_time);
                    }

                    else if(stu_ch==7) {
                        break;
                    }

                }
            }

            else if(ch==3) {
                break;
            }
        }

        scan.close();
    }
}