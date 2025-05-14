
package university.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.*;

public class StudentFeeForm extends JFrame implements ActionListener{
    
    Choice crollno;
    JComboBox cbcourse,cbbranch,cbsemester;
    JLabel labeltotal;
    JButton Update,pay,back;
    
    
    StudentFeeForm(){
        
        
        setSize(900,500);
        setLocation(300,100);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        //Adding 
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/fee.jpg  "));
        Image i2 = i1.getImage().getScaledInstance(350 , 200 , Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l1 = new JLabel(i3);
        l1.setBounds(400, 50, 500, 300);
        add(l1);
        
        ///Search by rollno.
        JLabel lblrollnumber = new JLabel("Select Roll No");
        lblrollnumber.setBounds(40, 60, 150, 20);
        lblrollnumber.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(lblrollnumber);
        
        crollno = new Choice();
        crollno.setBounds(200, 60, 150, 20);
        add(crollno);
        
        try{
            //connecting to the database 
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from student");
            while(rs.next()){
                crollno.add(rs.getString("rollno"));
            }
           
        }catch(Exception e){
            e.printStackTrace();
        }
        
        /// Student Details
       JLabel lblname = new JLabel("Name");
        lblname.setBounds(40, 100, 150, 20);
        lblname.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(lblname);
        
        JLabel labelname = new JLabel();
        labelname.setBounds(200, 100, 150, 20);
        labelname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(labelname);
        
        //Father's Details
        JLabel lblfname = new JLabel("Father's Name");
        lblfname.setBounds(40, 140, 150, 20);
        lblfname.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(lblfname);
        
        JLabel labelfname = new JLabel();
        labelfname.setBounds(200, 140, 150, 20);
        labelfname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(labelfname);
        
        //values
        try{
            Conn c = new Conn();
            String query = "select * from student where rollno = '"+crollno.getSelectedItem()+"'";
             ResultSet rs = c.s.executeQuery(query);
             while(rs.next()){
                labelname.setText(rs.getString("name"));
                labelfname.setText(rs.getString("fname"));
                
             }
               
        }catch(Exception e){
            e.printStackTrace();
        }
        
        crollno.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                try {
                    Conn c = new Conn();
                    String query = "select * from student where rollno='"+crollno.getSelectedItem()+"'";
                    ResultSet rs = c.s.executeQuery(query);
                    while(rs.next()) {
                        labelname.setText(rs.getString("name"));
                        labelfname.setText(rs.getString("fname"));
                        
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        
        //Course Details
        JLabel lblCourse = new JLabel("Course");
        lblCourse.setBounds(40 , 180 , 150 , 20 );
        lblCourse.setFont(new Font("Tahoma",Font.BOLD,16));
        add(lblCourse);
        
        String course[] ={"B.Tech","BE","BCA","BBA","BSc","MSc","MBA","Mcom","Bcom","BA"};
        cbcourse = new JComboBox(course);
        cbcourse.setBounds(200,180,150,20);
        cbcourse.setBackground(Color.WHITE);
        add(cbcourse);
        
        //Branch Details
        JLabel lblbranch = new JLabel("Branch");
        lblbranch.setBounds(40 , 220 , 150 , 20 );
        lblbranch.setFont(new Font("Tahoma",Font.BOLD,16));
        add(lblbranch);
        
        String branch[] ={"Computer Science","Data science","AI & ML","Cyber Security","Cloud Computing"};
        cbbranch = new JComboBox(branch);
        cbbranch.setBounds(200,220,150,20);
        cbbranch.setBackground(Color.WHITE);
        add(cbbranch);
        
        //Semester
        JLabel lblsemester = new JLabel("Semester");
        lblsemester.setBounds(40, 260, 150, 20);
        lblsemester.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(lblsemester);
    
        String semester[] ={"semester1","semester2","semester3","semester4","semester5","semester6","semester7","semester8"}; 
        cbsemester = new JComboBox(semester);
        cbsemester.setBounds(200, 260, 150, 20);
        cbsemester.setBackground(Color.WHITE);
        add(cbsemester);
        
        //Total 
        JLabel lbltotal = new JLabel("Total Payable");
        lbltotal.setBounds(40, 300, 150, 20);
        lbltotal.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(lbltotal);
        
        labeltotal = new JLabel();
        labeltotal.setBounds(200, 300, 150, 20);
        labeltotal.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(labeltotal);
        
        // Update buttons 
        Update = new JButton("Update");
        Update.setBackground(Color.RED);
        Update.setForeground(Color.WHITE);  
        Update.setBounds(30 , 380 , 100 , 25 );
        Update.addActionListener(this);
        add(Update);
        
        // Pay Fee button
        pay = new JButton("Pay Fee");
        pay.setBounds(150, 380 , 100 , 25);
        pay.setBackground(Color.RED);
        pay.setForeground(Color.WHITE);
        pay.addActionListener(this);
        add(pay);
        
        //Back button
        back = new JButton("Back");
        back.setBounds(270, 380 , 100 , 25 );
        back.setBackground(Color.RED);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        
        
        setVisible(true);
        
        
        
    }
    public void actionPerformed(ActionEvent ae){
        
        if(ae.getSource() == Update){
            String course = (String)cbcourse.getSelectedItem();
            String semester = (String)cbsemester.getSelectedItem();
            try{
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery("select * from fee where course = '"+course+"'");
                while(rs.next()){
                    labeltotal.setText(rs.getString(semester));
                    
                    
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }else if(ae.getSource() == pay){
            
        }else{
            setVisible(false);  
        }
        
    }
    
    public static void main(String[] args){
        
        new StudentFeeForm();
    }
    
}
