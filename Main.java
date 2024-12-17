/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Project;

import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import com.formdev.flatlaf.*;
import java.sql.*;
import net.proteanit.sql.*;
import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.event.*;
import java.text.SimpleDateFormat;

/**
 *
 * @author Ramyll
 */
public class Main extends javax.swing.JFrame {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    /**
     * Creates new form Main
     */
    public Main() {
        FlatLightLaf.setup();
        UIManager.put("Button.arc", 15); 
        UIManager.put("Panel.arc", 15); 
        initComponents();
        
        SwingUtilities.invokeLater(() -> {
        // Default and hover colors
            Color defaultColor = Dimension_Table.getBackground();
            Color hoverColor = new Color(255, 200, 200); // Light red for hover effect

            // Add MouseListener for hover effect
            Dimension_Table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    Dimension_Table.setBackground(hoverColor); // Change to hover color
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    Dimension_Table.setBackground(defaultColor); // Revert to default color
                }
            });
        });
        ParentPanel.removeAll();
        ParentPanel.add(Dimension_Table_Screen);
        ParentPanel.repaint();
        ParentPanel.revalidate();
        School_Record.removeAll();
        School_Record.add(sy_semester_screen);
        School_Record.repaint();
        School_Record.revalidate();
        sy_text_selected.setEnabled(false);
        sem_text_selected.setEnabled(false);
        schedule_subject_code_text.setEnabled(false);
        schedule_employeeID_text.setEnabled(false);
        schedule_employeeName_text.setEnabled(false);
        grade_studentNo_text.setEnabled(false);
        grade_studentName_text.setEnabled(false);
        grade_subjectCode_text.setEnabled(false);
        grade_block_text.setEnabled(false);
    }
    
    public void syrefresh(){
         try{
            conn = ConnectionDB.Connect();
            ps = conn.prepareStatement("SELECT syear AS \"School Year\" FROM PLM.SCHOOLYEAR");
            rs = ps.executeQuery();
            sy_table.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void semrefresh(){
         try{
            conn = ConnectionDB.Connect();
            ps = conn.prepareStatement("SELECT semester AS \"Semester\" FROM PLM.SEMESTER");
            rs = ps.executeQuery();
            sem_table.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void college_refresh(){
         try{
            conn = ConnectionDB.Connect();
            ps = conn.prepareStatement(
                "SELECT college_code AS \"College\", " +
                "description AS \"Description\", " +
                "date_opened AS \"Date Opened\", " +
                "date_closed AS \"Date Closed\", " +
                "status AS \"Status\" " +
                "FROM PLM.COLLEGE"
            );
            rs = ps.executeQuery();
            college_table.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            System.out.println(e);
        }
        college_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        college_table.getColumnModel().getColumn(0).setPreferredWidth(50);
        college_table.getColumnModel().getColumn(1).setPreferredWidth(520);
        college_table.getColumnModel().getColumn(2).setPreferredWidth(120);
        college_table.getColumnModel().getColumn(3).setPreferredWidth(120);
        college_table.getColumnModel().getColumn(4).setPreferredWidth(50);
    }
    
    public void course_refresh(){
         try{
            conn = ConnectionDB.Connect();
            ps = conn.prepareStatement(
                "SELECT course_code AS \"Course\", " +
                "description AS \"Description\", " +
                "college_code AS \"College\", " +
                "date_opened AS \"Date Opened\", " +
                "date_closed AS \"Date Closed\", " +
                "status AS \"Status\" " +
                "FROM PLM.COURSE"
            );
            rs = ps.executeQuery();
            course_table.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            System.out.println(e);
        }
        course_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        course_table.getColumnModel().getColumn(0).setPreferredWidth(100);
        course_table.getColumnModel().getColumn(1).setPreferredWidth(405);
        course_table.getColumnModel().getColumn(2).setPreferredWidth(50);
        course_table.getColumnModel().getColumn(3).setPreferredWidth(120);
        course_table.getColumnModel().getColumn(4).setPreferredWidth(120);
        course_table.getColumnModel().getColumn(5).setPreferredWidth(50);
    }
    
    public void student_refresh(){
         try{
            conn = ConnectionDB.Connect();
            ps = conn.prepareStatement(
                "SELECT student_no AS \"Student No.\", " +
                "lastname AS \"Surname\", " +
                "firstname AS \"First Name\", " +
                "email AS \"Email\", " +
                "gender AS \"Gender\", " +
                "course_code AS \"Course\", " +
                "cp_num AS \"Phone No.\", " +
                "address AS \"Address\", " +
                "bday AS \"Birthday\", " +
                "status AS \"Status\", " +
                "date_started AS \"Started\", " +
                "date_graduated AS \"Graduated\" " +
                "FROM PLM.STUDENT"
            );
            rs = ps.executeQuery();
            student_table.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            System.out.println(e);
        }
        student_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        student_table.getColumnModel().getColumn(0).setPreferredWidth(71);
        student_table.getColumnModel().getColumn(1).setPreferredWidth(60);
        student_table.getColumnModel().getColumn(2).setPreferredWidth(70);
        student_table.getColumnModel().getColumn(3).setPreferredWidth(120);
        student_table.getColumnModel().getColumn(4).setPreferredWidth(50);
        student_table.getColumnModel().getColumn(5).setPreferredWidth(90);
        student_table.getColumnModel().getColumn(6).setPreferredWidth(75);
        student_table.getColumnModel().getColumn(7).setPreferredWidth(60);
        student_table.getColumnModel().getColumn(8).setPreferredWidth(75);
        student_table.getColumnModel().getColumn(9).setPreferredWidth(45);
        student_table.getColumnModel().getColumn(10).setPreferredWidth(75);
        student_table.getColumnModel().getColumn(11).setPreferredWidth(75);
    }
    
    public void employee_refresh(){
         try{
            conn = ConnectionDB.Connect();
            ps = conn.prepareStatement(
                "SELECT employee_id AS \"Employee ID\", " +
                "lastname AS \"Surname\", " +
                "firstname AS \"First Name\", " +
                "email AS \"Email\", " +
                "gender AS \"Gender\", " +
                "cp_num AS \"Phone No.\", " +
                "address AS \"Address\", " +
                "bday AS \"Birthday\", " +
                "status AS \"Status\", " +
                "date_started AS \"Started\", " +
                "date_resigned AS \"Resigned\" " +
                "FROM PLM.EMPLOYEE"
            );
            rs = ps.executeQuery();
            employee_table.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            System.out.println(e);
        }
        employee_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        employee_table.getColumnModel().getColumn(0).setPreferredWidth(77);
        employee_table.getColumnModel().getColumn(1).setPreferredWidth(65);
        employee_table.getColumnModel().getColumn(2).setPreferredWidth(70);
        employee_table.getColumnModel().getColumn(3).setPreferredWidth(150);
        employee_table.getColumnModel().getColumn(4).setPreferredWidth(50);
        employee_table.getColumnModel().getColumn(5).setPreferredWidth(80);
        employee_table.getColumnModel().getColumn(6).setPreferredWidth(100);
        employee_table.getColumnModel().getColumn(7).setPreferredWidth(75);
        employee_table.getColumnModel().getColumn(8).setPreferredWidth(45);
        employee_table.getColumnModel().getColumn(9).setPreferredWidth(75);
        employee_table.getColumnModel().getColumn(10).setPreferredWidth(75);
    }
    
    public void subject_refresh(){
         try{
            conn = ConnectionDB.Connect();
            ps = conn.prepareStatement(
                "SELECT subject_code AS \"Subject\", " +
                "description AS \"Description\", " +
                "units AS \"Units\", " +
                "curriculum AS \"Curriculum\", " +
                "college_code AS \"College\", " +
                "status AS \"Status\", " +
                "date_opened AS \"Date Opened\", " +
                "date_closed AS \"Date Closed\" " +
                "FROM PLM.SUBJECT"
            );
            rs = ps.executeQuery();
            subject_table.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            System.out.println(e);
        }
        subject_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        subject_table.getColumnModel().getColumn(0).setPreferredWidth(70);
        subject_table.getColumnModel().getColumn(1).setPreferredWidth(425);
        subject_table.getColumnModel().getColumn(2).setPreferredWidth(50);
        subject_table.getColumnModel().getColumn(3).setPreferredWidth(70);
        subject_table.getColumnModel().getColumn(4).setPreferredWidth(50);
        subject_table.getColumnModel().getColumn(5).setPreferredWidth(45);
        subject_table.getColumnModel().getColumn(6).setPreferredWidth(75);
        subject_table.getColumnModel().getColumn(7).setPreferredWidth(75);
    }
    
    public void schedule_subject_refresh() {
        try {
            conn = ConnectionDB.Connect();
            ps = conn.prepareStatement(
                "SELECT subject_code AS \"Subject\", "
                 + "description AS \"Description\" "
                 + "FROM PLM.SUBJECT"
            );
            rs = ps.executeQuery();
            schedule_subject_table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            System.out.println(e);
        }
        schedule_subject_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        schedule_subject_table.getColumnModel().getColumn(0).setPreferredWidth(70);
        schedule_subject_table.getColumnModel().getColumn(1).setPreferredWidth(370);
    }
    
    public void schedule_employee_refresh() {
        try {
            conn = ConnectionDB.Connect();
            ps = conn.prepareStatement(
                "SELECT employee_id AS \"Employee ID\", "
                 + "lastname || ', ' || firstname AS \"Employee Name\" "
                 + "FROM PLM.EMPLOYEE"
            );
            rs = ps.executeQuery();
            schedule_employee_table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            System.out.println(e);
        }
        schedule_employee_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        schedule_employee_table.getColumnModel().getColumn(0).setPreferredWidth(80);
        schedule_employee_table.getColumnModel().getColumn(1).setPreferredWidth(360);
    }
    
    public void schedule_refresh() {
        try {
            conn = ConnectionDB.Connect();
            ps = conn.prepareStatement(
                "SELECT * "
                + "FROM PLM.V_SUBJECT_SCHEDULE"
            );

            rs = ps.executeQuery();
            schedule_table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            System.out.println(e);
        }
        schedule_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        schedule_table.getColumnModel().getColumn(0).setPreferredWidth(75);
        schedule_table.getColumnModel().getColumn(1).setPreferredWidth(65);
        schedule_table.getColumnModel().getColumn(2).setPreferredWidth(50);
        schedule_table.getColumnModel().getColumn(3).setPreferredWidth(90);
        schedule_table.getColumnModel().getColumn(4).setPreferredWidth(80);
        schedule_table.getColumnModel().getColumn(5).setPreferredWidth(300);
        schedule_table.getColumnModel().getColumn(6).setPreferredWidth(35);
        schedule_table.getColumnModel().getColumn(7).setPreferredWidth(65);
        schedule_table.getColumnModel().getColumn(8).setPreferredWidth(30);
        schedule_table.getColumnModel().getColumn(9).setPreferredWidth(60);
        schedule_table.getColumnModel().getColumn(10).setPreferredWidth(120);
        schedule_table.getColumnModel().getColumn(11).setPreferredWidth(90);
        schedule_table.getColumnModel().getColumn(12).setPreferredWidth(110);
        schedule_table.getColumnModel().getColumn(13).setPreferredWidth(65);
        schedule_table.getColumnModel().getColumn(14).setPreferredWidth(100);
        schedule_table.getColumnModel().getColumn(15).setPreferredWidth(90);
        schedule_table.getColumnModel().getColumn(16).setPreferredWidth(100);
        schedule_table.getColumnModel().getColumn(17).setPreferredWidth(100);
        schedule_table.getColumnModel().getColumn(18).setPreferredWidth(90);
        schedule_table.getColumnModel().getColumn(19).setPreferredWidth(90);
    }
    
    public void grade_student_refresh() {
        try {
            conn = ConnectionDB.Connect();
            ps = conn.prepareStatement(
                "SELECT student_no AS \"Subject\", "
                 + "lastname || ', ' || firstname AS \"Full Name\" "
                 + "FROM PLM.STUDENT"
            );
            rs = ps.executeQuery();
            grade_student_table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            System.out.println(e);
        }
        grade_student_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        grade_student_table.getColumnModel().getColumn(0).setPreferredWidth(70);
        grade_student_table.getColumnModel().getColumn(1).setPreferredWidth(360);
    }
    
    public void grade_subject_refresh() {
        try {
            conn = ConnectionDB.Connect();
            ps = conn.prepareStatement(
                "SELECT ss.subject_code AS \"Subject\", "
                + "ss.block_no AS \"Block No.\", "
                + "s.description AS \"Description\" "
                + "FROM PLM.SUBJECT_SCHEDULE ss "
                + "JOIN PLM.SUBJECT s "
                + "ON ss.subject_code = s.subject_code"
            );
            rs = ps.executeQuery();
            grade_subject_table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            System.out.println(e);
        }
        grade_subject_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        grade_subject_table.getColumnModel().getColumn(0).setPreferredWidth(70);
        grade_subject_table.getColumnModel().getColumn(1).setPreferredWidth(80);
        grade_subject_table.getColumnModel().getColumn(2).setPreferredWidth(360);
    }
    
    
    public void grade_refresh() {
        try {
            conn = ConnectionDB.Connect();
            ps = conn.prepareStatement(
                "SELECT * FROM PLM.V_STUDENT_GRADES"
            );

            rs = ps.executeQuery();
            grade_table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            System.out.println(e);
        }
        grade_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        grade_table.getColumnModel().getColumn(0).setPreferredWidth(75);
        grade_table.getColumnModel().getColumn(1).setPreferredWidth(65);
        grade_table.getColumnModel().getColumn(2).setPreferredWidth(100);
        grade_table.getColumnModel().getColumn(3).setPreferredWidth(120);
        grade_table.getColumnModel().getColumn(4).setPreferredWidth(80);
        grade_table.getColumnModel().getColumn(5).setPreferredWidth(400);
        grade_table.getColumnModel().getColumn(6).setPreferredWidth(80);
        grade_table.getColumnModel().getColumn(7).setPreferredWidth(250);
        grade_table.getColumnModel().getColumn(8).setPreferredWidth(80);
        grade_table.getColumnModel().getColumn(9).setPreferredWidth(50);
        grade_table.getColumnModel().getColumn(10).setPreferredWidth(50);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        navigation = new javax.swing.JPanel();
        Home = new javax.swing.JButton();
        Dimension_Table = new javax.swing.JButton();
        Add_Schedule = new javax.swing.JButton();
        Subject_Schedule = new javax.swing.JButton();
        Record_Grade = new javax.swing.JButton();
        Student_Grade = new javax.swing.JButton();
        ParentPanel = new javax.swing.JPanel();
        Dimension_Table_Screen = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        sy_sem = new javax.swing.JButton();
        college = new javax.swing.JButton();
        course = new javax.swing.JButton();
        student = new javax.swing.JButton();
        employee = new javax.swing.JButton();
        subject = new javax.swing.JButton();
        School_Record = new javax.swing.JPanel();
        sy_semester_screen = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        sy_text = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        sy_table = new javax.swing.JTable();
        sy_add = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        sem_table = new javax.swing.JTable();
        sy_update = new javax.swing.JButton();
        sy_delete = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        sem_text = new javax.swing.JTextField();
        sy_search_text = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        sy_search_button = new javax.swing.JButton();
        sem_search_button = new javax.swing.JButton();
        sem_search_text = new javax.swing.JTextField();
        sy_clear = new javax.swing.JButton();
        sem_add = new javax.swing.JButton();
        sem_update = new javax.swing.JButton();
        sem_delete = new javax.swing.JButton();
        sem_clear = new javax.swing.JButton();
        sy_text_selected = new javax.swing.JTextField();
        sem_text_selected = new javax.swing.JTextField();
        college_screen = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        college_search_text = new javax.swing.JTextField();
        college_search_button = new javax.swing.JButton();
        college_add = new javax.swing.JButton();
        college_update = new javax.swing.JButton();
        college_delete = new javax.swing.JButton();
        college_refresh = new javax.swing.JButton();
        college_code_text = new javax.swing.JTextField();
        college_desc_text = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        college_date_opened = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        college_date_closed = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        college_table = new javax.swing.JTable();
        college_status_cmbx = new javax.swing.JComboBox<>();
        course_screen = new javax.swing.JPanel();
        jLabel70 = new javax.swing.JLabel();
        course_search_text = new javax.swing.JTextField();
        course_search_button = new javax.swing.JButton();
        course_add = new javax.swing.JButton();
        course_update = new javax.swing.JButton();
        course_delete = new javax.swing.JButton();
        course_refresh = new javax.swing.JButton();
        course_code_text = new javax.swing.JTextField();
        course_desc_text = new javax.swing.JTextField();
        jLabel71 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        course_date_opened = new com.toedter.calendar.JDateChooser();
        jLabel77 = new javax.swing.JLabel();
        course_date_closed = new com.toedter.calendar.JDateChooser();
        jLabel78 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        course_table = new javax.swing.JTable();
        course_college_code_cbmx = new javax.swing.JComboBox<>();
        jLabel79 = new javax.swing.JLabel();
        course_status_cmbx = new javax.swing.JComboBox<>();
        student_screen = new javax.swing.JPanel();
        jLabel80 = new javax.swing.JLabel();
        student_search_text = new javax.swing.JTextField();
        student_search_button = new javax.swing.JButton();
        student_add = new javax.swing.JButton();
        student_update = new javax.swing.JButton();
        student_delete = new javax.swing.JButton();
        student_refresh = new javax.swing.JButton();
        student_no_text = new javax.swing.JTextField();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        student_date_graduated = new com.toedter.calendar.JDateChooser();
        jScrollPane12 = new javax.swing.JScrollPane();
        student_table = new javax.swing.JTable();
        student_gender_cmbx = new javax.swing.JComboBox<>();
        jLabel86 = new javax.swing.JLabel();
        student_address_text = new javax.swing.JTextField();
        jLabel87 = new javax.swing.JLabel();
        student_lastname_text = new javax.swing.JTextField();
        student_course_code_cmbx = new javax.swing.JComboBox<>();
        student_phone_text = new javax.swing.JTextField();
        jLabel89 = new javax.swing.JLabel();
        student_date_started = new com.toedter.calendar.JDateChooser();
        jLabel90 = new javax.swing.JLabel();
        student_bday = new com.toedter.calendar.JDateChooser();
        student_email_text = new javax.swing.JTextField();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        student_firstname_text = new javax.swing.JTextField();
        student_status_cmbx = new javax.swing.JComboBox<>();
        employee_screen = new javax.swing.JPanel();
        jLabel105 = new javax.swing.JLabel();
        employee_search_text = new javax.swing.JTextField();
        employee_search_button = new javax.swing.JButton();
        employee_add = new javax.swing.JButton();
        employee_update = new javax.swing.JButton();
        employee_delete = new javax.swing.JButton();
        employee_refresh = new javax.swing.JButton();
        employee_id_text = new javax.swing.JTextField();
        jLabel106 = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        employee_date_resigned = new com.toedter.calendar.JDateChooser();
        jScrollPane14 = new javax.swing.JScrollPane();
        employee_table = new javax.swing.JTable();
        employee_gender_cmbx = new javax.swing.JComboBox<>();
        jLabel110 = new javax.swing.JLabel();
        employee_address_text = new javax.swing.JTextField();
        employee_firstname_text = new javax.swing.JTextField();
        jLabel111 = new javax.swing.JLabel();
        employee_lastname_text = new javax.swing.JTextField();
        employee_phone_text = new javax.swing.JTextField();
        jLabel112 = new javax.swing.JLabel();
        employee_date_started = new com.toedter.calendar.JDateChooser();
        jLabel113 = new javax.swing.JLabel();
        employee_bday = new com.toedter.calendar.JDateChooser();
        employee_email_text = new javax.swing.JTextField();
        jLabel114 = new javax.swing.JLabel();
        jLabel116 = new javax.swing.JLabel();
        jLabel117 = new javax.swing.JLabel();
        employee_status_cmbx = new javax.swing.JComboBox<>();
        subject_screen = new javax.swing.JPanel();
        jLabel115 = new javax.swing.JLabel();
        subject_search_text = new javax.swing.JTextField();
        subject_search_button = new javax.swing.JButton();
        subject_add = new javax.swing.JButton();
        subject_update = new javax.swing.JButton();
        subject_delete = new javax.swing.JButton();
        subject_refresh = new javax.swing.JButton();
        subject_code_text = new javax.swing.JTextField();
        jLabel118 = new javax.swing.JLabel();
        jLabel121 = new javax.swing.JLabel();
        subject_date_closed = new com.toedter.calendar.JDateChooser();
        jScrollPane15 = new javax.swing.JScrollPane();
        subject_table = new javax.swing.JTable();
        subject_date_opened = new com.toedter.calendar.JDateChooser();
        subject_curriculum_text = new javax.swing.JTextField();
        jLabel126 = new javax.swing.JLabel();
        jLabel127 = new javax.swing.JLabel();
        jLabel128 = new javax.swing.JLabel();
        subject_desc_text = new javax.swing.JTextField();
        jLabel119 = new javax.swing.JLabel();
        subject_units_text = new javax.swing.JTextField();
        jLabel129 = new javax.swing.JLabel();
        subject_college_code_cmbx = new javax.swing.JComboBox<>();
        jLabel130 = new javax.swing.JLabel();
        subject_status_cmbx = new javax.swing.JComboBox<>();
        Add_Schedule_Screen = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        schedule_semester_cmbx = new javax.swing.JComboBox<>();
        schedule_sy_cmbx = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        schedule_college_code_cmbx = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        schedule_room_text = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        schedule_add = new javax.swing.JButton();
        schedule_block_text = new javax.swing.JTextField();
        schedule_type_cmbx = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();
        schedule_time_text = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        schedule_next = new javax.swing.JButton();
        schedule_refresh = new javax.swing.JButton();
        schedule_day_cmbx = new javax.swing.JComboBox<>();
        jLabel37 = new javax.swing.JLabel();
        schedule_subject_search_text = new javax.swing.JTextField();
        schedule_subject_search_button = new javax.swing.JButton();
        jScrollPane16 = new javax.swing.JScrollPane();
        schedule_subject_table = new javax.swing.JTable();
        jScrollPane17 = new javax.swing.JScrollPane();
        schedule_employee_table = new javax.swing.JTable();
        schedule_employee_search_text = new javax.swing.JTextField();
        schedule_employee_search_button = new javax.swing.JButton();
        schedule_subject_code_text = new javax.swing.JTextField();
        schedule_employeeID_text = new javax.swing.JTextField();
        schedule_employeeName_text = new javax.swing.JTextField();
        schedule_sequence_cmbx = new javax.swing.JComboBox<>();
        Record_Grade_Screen = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        grade_sem_cmbx = new javax.swing.JComboBox<>();
        grade_sy_cmbx = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        grade_add = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        grade_next = new javax.swing.JButton();
        grade_cmbx = new javax.swing.JComboBox<>();
        grade_block_text = new javax.swing.JTextField();
        schedule_refresh1 = new javax.swing.JButton();
        grade_studentNo_text = new javax.swing.JTextField();
        grade_studentName_text = new javax.swing.JTextField();
        grade_student_search_text = new javax.swing.JTextField();
        grade_student_search_button = new javax.swing.JButton();
        grade_subject_code_text = new javax.swing.JTextField();
        jScrollPane18 = new javax.swing.JScrollPane();
        grade_student_table = new javax.swing.JTable();
        grade_subjectCode_text = new javax.swing.JTextField();
        grade_subject_search_button = new javax.swing.JButton();
        jScrollPane19 = new javax.swing.JScrollPane();
        grade_subject_table = new javax.swing.JTable();
        Subject_Schedule_Screen = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        schedule_search_text = new javax.swing.JTextField();
        schedule_search_button = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        schedule_table = new javax.swing.JTable();
        schedule_search_button1 = new javax.swing.JButton();
        Student_Grade_Screen = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        grade_search_text = new javax.swing.JTextField();
        grade_search_button = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        grade_table = new javax.swing.JTable();
        schedule_search_button2 = new javax.swing.JButton();

        jFormattedTextField1.setText("jFormattedTextField1");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(jTable2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("OOP Project");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(255, 255, 255));
        setLocationByPlatform(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        navigation.setBackground(new java.awt.Color(0, 51, 102));
        navigation.setPreferredSize(new java.awt.Dimension(172, 520));

        Home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/OOP Project/PLM_Seal_2013.png"))); // NOI18N
        Home.setBorderPainted(false);
        Home.setContentAreaFilled(false);
        Home.setFocusPainted(false);
        Home.setPreferredSize(new java.awt.Dimension(518, 519));
        Home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomeActionPerformed(evt);
            }
        });

        Dimension_Table.setBackground(new java.awt.Color(0, 51, 102));
        Dimension_Table.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        Dimension_Table.setForeground(new java.awt.Color(51, 153, 255));
        Dimension_Table.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/OOP Project/database.png"))); // NOI18N
        Dimension_Table.setText("Dimension Table ");
        Dimension_Table.setBorderPainted(false);
        Dimension_Table.setContentAreaFilled(false);
        Dimension_Table.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        Dimension_Table.setOpaque(true);
        Dimension_Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Dimension_TableMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Dimension_TableMouseExited(evt);
            }
        });
        Dimension_Table.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Dimension_TableActionPerformed(evt);
            }
        });

        Add_Schedule.setBackground(new java.awt.Color(0, 51, 102));
        Add_Schedule.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        Add_Schedule.setForeground(new java.awt.Color(51, 153, 255));
        Add_Schedule.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/OOP Project/add.png"))); // NOI18N
        Add_Schedule.setText("Add Schedule ");
        Add_Schedule.setBorderPainted(false);
        Add_Schedule.setContentAreaFilled(false);
        Add_Schedule.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        Add_Schedule.setOpaque(true);
        Add_Schedule.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Add_ScheduleMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Add_ScheduleMouseExited(evt);
            }
        });
        Add_Schedule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Add_ScheduleActionPerformed(evt);
            }
        });

        Subject_Schedule.setBackground(new java.awt.Color(0, 51, 102));
        Subject_Schedule.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        Subject_Schedule.setForeground(new java.awt.Color(51, 153, 255));
        Subject_Schedule.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/OOP Project/calendar.png"))); // NOI18N
        Subject_Schedule.setText("Subject Schedule ");
        Subject_Schedule.setBorderPainted(false);
        Subject_Schedule.setContentAreaFilled(false);
        Subject_Schedule.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        Subject_Schedule.setOpaque(true);
        Subject_Schedule.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Subject_ScheduleMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Subject_ScheduleMouseExited(evt);
            }
        });
        Subject_Schedule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Subject_ScheduleActionPerformed(evt);
            }
        });

        Record_Grade.setBackground(new java.awt.Color(0, 51, 102));
        Record_Grade.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        Record_Grade.setForeground(new java.awt.Color(51, 153, 255));
        Record_Grade.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/OOP Project/pencil.png"))); // NOI18N
        Record_Grade.setText("Record Grade ");
        Record_Grade.setBorderPainted(false);
        Record_Grade.setContentAreaFilled(false);
        Record_Grade.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        Record_Grade.setOpaque(true);
        Record_Grade.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Record_GradeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Record_GradeMouseExited(evt);
            }
        });
        Record_Grade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Record_GradeActionPerformed(evt);
            }
        });

        Student_Grade.setBackground(new java.awt.Color(0, 51, 102));
        Student_Grade.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        Student_Grade.setForeground(new java.awt.Color(51, 153, 255));
        Student_Grade.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/OOP Project/star.png"))); // NOI18N
        Student_Grade.setText("Student's Grade ");
        Student_Grade.setBorderPainted(false);
        Student_Grade.setContentAreaFilled(false);
        Student_Grade.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        Student_Grade.setOpaque(true);
        Student_Grade.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Student_GradeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Student_GradeMouseExited(evt);
            }
        });
        Student_Grade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Student_GradeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout navigationLayout = new javax.swing.GroupLayout(navigation);
        navigation.setLayout(navigationLayout);
        navigationLayout.setHorizontalGroup(
            navigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Student_Grade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Subject_Schedule, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Record_Grade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Add_Schedule, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Dimension_Table, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Home, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        navigationLayout.setVerticalGroup(
            navigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(navigationLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(Home, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Dimension_Table, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Add_Schedule, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Record_Grade, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Subject_Schedule, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Student_Grade, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        ParentPanel.setBackground(new java.awt.Color(0, 102, 102));
        ParentPanel.setPreferredSize(new java.awt.Dimension(930, 520));
        ParentPanel.setLayout(new java.awt.CardLayout());

        Dimension_Table_Screen.setBackground(new java.awt.Color(255, 255, 255));
        Dimension_Table_Screen.setPreferredSize(new java.awt.Dimension(930, 520));

        jLabel1.setFont(new java.awt.Font("Franklin Gothic Heavy", 1, 60)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 102));
        jLabel1.setText("School Record");

        sy_sem.setBackground(new java.awt.Color(0, 51, 102));
        sy_sem.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        sy_sem.setForeground(new java.awt.Color(255, 255, 255));
        sy_sem.setText("S.Y. & Semester");
        sy_sem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sy_semActionPerformed(evt);
            }
        });

        college.setBackground(new java.awt.Color(0, 51, 102));
        college.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        college.setForeground(new java.awt.Color(255, 255, 255));
        college.setText("College");
        college.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                collegeActionPerformed(evt);
            }
        });

        course.setBackground(new java.awt.Color(0, 51, 102));
        course.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        course.setForeground(new java.awt.Color(255, 255, 255));
        course.setText("Course");
        course.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                courseActionPerformed(evt);
            }
        });

        student.setBackground(new java.awt.Color(0, 51, 102));
        student.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        student.setForeground(new java.awt.Color(255, 255, 255));
        student.setText("Student");
        student.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentActionPerformed(evt);
            }
        });

        employee.setBackground(new java.awt.Color(0, 51, 102));
        employee.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        employee.setForeground(new java.awt.Color(255, 255, 255));
        employee.setText("Employee");
        employee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employeeActionPerformed(evt);
            }
        });

        subject.setBackground(new java.awt.Color(0, 51, 102));
        subject.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        subject.setForeground(new java.awt.Color(255, 255, 255));
        subject.setText("Subject");
        subject.setToolTipText("");
        subject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subjectActionPerformed(evt);
            }
        });

        School_Record.setPreferredSize(new java.awt.Dimension(698, 430));
        School_Record.setLayout(new java.awt.CardLayout());

        sy_semester_screen.setBackground(new java.awt.Color(0, 51, 102));

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("School Year:");

        sy_text.setBackground(new java.awt.Color(255, 255, 255));
        sy_text.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        sy_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sy_textActionPerformed(evt);
            }
        });

        sy_table.setBackground(new java.awt.Color(255, 255, 255));
        sy_table.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        sy_table.setForeground(new java.awt.Color(0, 0, 51));
        sy_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        sy_table.setShowGrid(true);
        sy_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sy_tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(sy_table);

        sy_add.setBackground(new java.awt.Color(204, 255, 255));
        sy_add.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        sy_add.setForeground(new java.awt.Color(0, 0, 0));
        sy_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/OOP Project/add5.png"))); // NOI18N
        sy_add.setText("Add");
        sy_add.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        sy_add.setPreferredSize(new java.awt.Dimension(78, 30));
        sy_add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sy_addMouseClicked(evt);
            }
        });

        sem_table.setBackground(new java.awt.Color(255, 255, 255));
        sem_table.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        sem_table.setForeground(new java.awt.Color(0, 0, 0));
        sem_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        sem_table.setShowGrid(true);
        sem_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sem_tableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(sem_table);

        sy_update.setBackground(new java.awt.Color(204, 255, 255));
        sy_update.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        sy_update.setForeground(new java.awt.Color(0, 0, 0));
        sy_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/OOP Project/pencil2.png"))); // NOI18N
        sy_update.setText("Update");
        sy_update.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        sy_update.setPreferredSize(new java.awt.Dimension(100, 30));
        sy_update.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sy_updateMouseClicked(evt);
            }
        });

        sy_delete.setBackground(new java.awt.Color(204, 255, 255));
        sy_delete.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        sy_delete.setForeground(new java.awt.Color(0, 0, 0));
        sy_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/OOP Project/trash-bin (2).png"))); // NOI18N
        sy_delete.setText("Delete");
        sy_delete.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        sy_delete.setPreferredSize(new java.awt.Dimension(96, 30));
        sy_delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sy_deleteMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Semester:");

        sem_text.setBackground(new java.awt.Color(255, 255, 255));
        sem_text.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N

        sy_search_text.setBackground(new java.awt.Color(255, 255, 255));
        sy_search_text.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        sy_search_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sy_search_textActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("School Year");

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Semester");

        sy_search_button.setBackground(new java.awt.Color(204, 255, 255));
        sy_search_button.setForeground(new java.awt.Color(255, 255, 255));
        sy_search_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/OOP Project/magnifying-glass.png"))); // NOI18N
        sy_search_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sy_search_buttonMouseClicked(evt);
            }
        });

        sem_search_button.setBackground(new java.awt.Color(204, 255, 255));
        sem_search_button.setForeground(new java.awt.Color(255, 255, 255));
        sem_search_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/OOP Project/magnifying-glass.png"))); // NOI18N
        sem_search_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sem_search_buttonMouseClicked(evt);
            }
        });
        sem_search_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sem_search_buttonActionPerformed(evt);
            }
        });

        sem_search_text.setBackground(new java.awt.Color(255, 255, 255));
        sem_search_text.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N

        sy_clear.setBackground(new java.awt.Color(204, 255, 255));
        sy_clear.setForeground(new java.awt.Color(255, 255, 255));
        sy_clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/OOP Project/update2.png"))); // NOI18N
        sy_clear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sy_clearMouseClicked(evt);
            }
        });
        sy_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sy_clearActionPerformed(evt);
            }
        });

        sem_add.setBackground(new java.awt.Color(204, 255, 255));
        sem_add.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        sem_add.setForeground(new java.awt.Color(0, 0, 0));
        sem_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/OOP Project/add5.png"))); // NOI18N
        sem_add.setText("Add");
        sem_add.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        sem_add.setPreferredSize(new java.awt.Dimension(78, 30));
        sem_add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sem_addMouseClicked(evt);
            }
        });

        sem_update.setBackground(new java.awt.Color(204, 255, 255));
        sem_update.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        sem_update.setForeground(new java.awt.Color(0, 0, 0));
        sem_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/OOP Project/pencil2.png"))); // NOI18N
        sem_update.setText("Update");
        sem_update.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        sem_update.setPreferredSize(new java.awt.Dimension(100, 30));
        sem_update.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sem_updateMouseClicked(evt);
            }
        });

        sem_delete.setBackground(new java.awt.Color(204, 255, 255));
        sem_delete.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        sem_delete.setForeground(new java.awt.Color(0, 0, 0));
        sem_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/OOP Project/trash-bin (2).png"))); // NOI18N
        sem_delete.setText("Delete");
        sem_delete.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        sem_delete.setPreferredSize(new java.awt.Dimension(96, 30));
        sem_delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sem_deleteMouseClicked(evt);
            }
        });

        sem_clear.setBackground(new java.awt.Color(204, 255, 255));
        sem_clear.setForeground(new java.awt.Color(255, 255, 255));
        sem_clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/OOP Project/update2.png"))); // NOI18N
        sem_clear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sem_clearMouseClicked(evt);
            }
        });

        sy_text_selected.setBackground(new java.awt.Color(204, 204, 204));
        sy_text_selected.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N

        sem_text_selected.setBackground(new java.awt.Color(204, 204, 204));
        sem_text_selected.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N

        javax.swing.GroupLayout sy_semester_screenLayout = new javax.swing.GroupLayout(sy_semester_screen);
        sy_semester_screen.setLayout(sy_semester_screenLayout);
        sy_semester_screenLayout.setHorizontalGroup(
            sy_semester_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sy_semester_screenLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(sy_semester_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(sy_semester_screenLayout.createSequentialGroup()
                        .addGroup(sy_semester_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(sy_add, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sem_add, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(sy_semester_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(sy_semester_screenLayout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addGroup(sy_semester_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sy_semester_screenLayout.createSequentialGroup()
                                        .addComponent(sy_update, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(sy_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sy_semester_screenLayout.createSequentialGroup()
                                        .addComponent(sem_update, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(sem_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(24, 24, 24))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sy_semester_screenLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(sem_search_text, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(sy_semester_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(sy_semester_screenLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(sy_semester_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(sy_clear, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(sem_clear, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(sem_search_button, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(sy_semester_screenLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sy_search_text, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(sy_search_button, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(sy_semester_screenLayout.createSequentialGroup()
                        .addComponent(sy_text, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sy_text_selected))
                    .addGroup(sy_semester_screenLayout.createSequentialGroup()
                        .addComponent(sem_text, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sem_text_selected)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        sy_semester_screenLayout.setVerticalGroup(
            sy_semester_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sy_semester_screenLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(sy_semester_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(sy_semester_screenLayout.createSequentialGroup()
                        .addGroup(sy_semester_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(sy_semester_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(sy_search_text, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(sy_search_button, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addGroup(sy_semester_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(sy_semester_screenLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(sy_semester_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(sy_text, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(sy_text_selected, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(19, 19, 19)
                                .addGroup(sy_semester_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(sy_add, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(sy_update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(sy_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sy_semester_screenLayout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(sy_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(41, 41, 41)
                        .addGroup(sy_semester_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(sem_search_text, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                            .addComponent(sem_search_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(sy_semester_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sem_text_selected, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(sem_text))
                        .addGap(18, 18, 18)
                        .addGroup(sy_semester_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(sy_semester_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(sem_add, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(sem_update, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(sem_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(sem_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(51, 51, 51))
        );

        School_Record.add(sy_semester_screen, "card2");

        college_screen.setBackground(new java.awt.Color(0, 51, 102));

        jLabel23.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("College:");

        college_search_text.setBackground(new java.awt.Color(255, 255, 255));
        college_search_text.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        college_search_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                college_search_textActionPerformed(evt);
            }
        });

        college_search_button.setBackground(new java.awt.Color(204, 255, 255));
        college_search_button.setForeground(new java.awt.Color(255, 255, 255));
        college_search_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/OOP Project/magnifying-glass.png"))); // NOI18N
        college_search_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                college_search_buttonMouseClicked(evt);
            }
        });
        college_search_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                college_search_buttonActionPerformed(evt);
            }
        });

        college_add.setBackground(new java.awt.Color(204, 255, 255));
        college_add.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        college_add.setForeground(new java.awt.Color(0, 0, 0));
        college_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/OOP Project/add5.png"))); // NOI18N
        college_add.setText("Add");
        college_add.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        college_add.setPreferredSize(new java.awt.Dimension(78, 30));
        college_add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                college_addMouseClicked(evt);
            }
        });
        college_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                college_addActionPerformed(evt);
            }
        });

        college_update.setBackground(new java.awt.Color(204, 255, 255));
        college_update.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        college_update.setForeground(new java.awt.Color(0, 0, 0));
        college_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/OOP Project/pencil2.png"))); // NOI18N
        college_update.setText("Update");
        college_update.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        college_update.setPreferredSize(new java.awt.Dimension(100, 30));
        college_update.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                college_updateMouseClicked(evt);
            }
        });
        college_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                college_updateActionPerformed(evt);
            }
        });

        college_delete.setBackground(new java.awt.Color(204, 255, 255));
        college_delete.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        college_delete.setForeground(new java.awt.Color(0, 0, 0));
        college_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/OOP Project/trash-bin (2).png"))); // NOI18N
        college_delete.setText("Delete");
        college_delete.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        college_delete.setPreferredSize(new java.awt.Dimension(96, 30));
        college_delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                college_deleteMouseClicked(evt);
            }
        });

        college_refresh.setBackground(new java.awt.Color(204, 255, 255));
        college_refresh.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        college_refresh.setForeground(new java.awt.Color(0, 0, 0));
        college_refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/OOP Project/update.png"))); // NOI18N
        college_refresh.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        college_refresh.setPreferredSize(new java.awt.Dimension(96, 30));
        college_refresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                college_refreshMouseClicked(evt);
            }
        });
        college_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                college_refreshActionPerformed(evt);
            }
        });

        college_code_text.setBackground(new java.awt.Color(255, 255, 255));
        college_code_text.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N

        college_desc_text.setBackground(new java.awt.Color(255, 255, 255));
        college_desc_text.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("College Code");

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("College Description");

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Status");

        college_date_opened.setDateFormatString("MM/dd/yyyy");
        college_date_opened.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Date Opened");

        college_date_closed.setDateFormatString("MM/dd/yyyy");
        college_date_closed.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N

        jLabel11.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Date Closed");

        college_table.setBackground(new java.awt.Color(255, 255, 255));
        college_table.setFont(new java.awt.Font("Segoe UI Semibold", 1, 10)); // NOI18N
        college_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        college_table.setShowGrid(true);
        college_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                college_tableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(college_table);

        college_status_cmbx.setBackground(new java.awt.Color(255, 255, 255));
        college_status_cmbx.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        college_status_cmbx.setForeground(new java.awt.Color(0, 0, 0));
        college_status_cmbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "I" }));

        javax.swing.GroupLayout college_screenLayout = new javax.swing.GroupLayout(college_screen);
        college_screen.setLayout(college_screenLayout);
        college_screenLayout.setHorizontalGroup(
            college_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(college_screenLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(college_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(college_screenLayout.createSequentialGroup()
                        .addGroup(college_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(college_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(college_date_opened, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(college_code_text, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE))
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(college_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(college_desc_text)
                            .addGroup(college_screenLayout.createSequentialGroup()
                                .addGroup(college_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(college_date_closed, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(college_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel9)
                                    .addComponent(college_status_cmbx, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(college_screenLayout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(college_search_text, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(college_search_button, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(college_add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(college_update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(college_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(college_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(16, 16, 16))
        );
        college_screenLayout.setVerticalGroup(
            college_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(college_screenLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(college_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(college_add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(college_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(college_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(college_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(college_update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(college_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(college_search_button, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(college_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(college_search_text, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(college_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(college_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(college_code_text, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(college_desc_text, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(college_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(college_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(college_screenLayout.createSequentialGroup()
                        .addComponent(college_status_cmbx, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(287, 287, 287))
                    .addGroup(college_screenLayout.createSequentialGroup()
                        .addGroup(college_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(college_date_closed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(college_date_opened, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))))
        );

        School_Record.add(college_screen, "card3");

        course_screen.setBackground(new java.awt.Color(0, 51, 102));

        jLabel70.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(255, 255, 255));
        jLabel70.setText("Course: ");

        course_search_text.setBackground(new java.awt.Color(255, 255, 255));
        course_search_text.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        course_search_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                course_search_textActionPerformed(evt);
            }
        });

        course_search_button.setBackground(new java.awt.Color(204, 255, 255));
        course_search_button.setForeground(new java.awt.Color(255, 255, 255));
        course_search_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/OOP Project/magnifying-glass.png"))); // NOI18N
        course_search_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                course_search_buttonMouseClicked(evt);
            }
        });
        course_search_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                course_search_buttonActionPerformed(evt);
            }
        });

        course_add.setBackground(new java.awt.Color(204, 255, 255));
        course_add.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        course_add.setForeground(new java.awt.Color(0, 0, 0));
        course_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/OOP Project/add5.png"))); // NOI18N
        course_add.setText("Add");
        course_add.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        course_add.setPreferredSize(new java.awt.Dimension(78, 30));
        course_add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                course_addMouseClicked(evt);
            }
        });
        course_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                course_addActionPerformed(evt);
            }
        });

        course_update.setBackground(new java.awt.Color(204, 255, 255));
        course_update.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        course_update.setForeground(new java.awt.Color(0, 0, 0));
        course_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/OOP Project/pencil2.png"))); // NOI18N
        course_update.setText("Update");
        course_update.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        course_update.setPreferredSize(new java.awt.Dimension(100, 30));
        course_update.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                course_updateMouseClicked(evt);
            }
        });
        course_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                course_updateActionPerformed(evt);
            }
        });

        course_delete.setBackground(new java.awt.Color(204, 255, 255));
        course_delete.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        course_delete.setForeground(new java.awt.Color(0, 0, 0));
        course_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/OOP Project/trash-bin (2).png"))); // NOI18N
        course_delete.setText("Delete");
        course_delete.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        course_delete.setPreferredSize(new java.awt.Dimension(96, 30));
        course_delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                course_deleteMouseClicked(evt);
            }
        });

        course_refresh.setBackground(new java.awt.Color(204, 255, 255));
        course_refresh.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        course_refresh.setForeground(new java.awt.Color(0, 0, 0));
        course_refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/OOP Project/update.png"))); // NOI18N
        course_refresh.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        course_refresh.setPreferredSize(new java.awt.Dimension(96, 30));
        course_refresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                course_refreshMouseClicked(evt);
            }
        });
        course_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                course_refreshActionPerformed(evt);
            }
        });

        course_code_text.setBackground(new java.awt.Color(255, 255, 255));
        course_code_text.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N

        course_desc_text.setBackground(new java.awt.Color(255, 255, 255));
        course_desc_text.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N

        jLabel71.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(255, 255, 255));
        jLabel71.setText("Course Code");

        jLabel73.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(255, 255, 255));
        jLabel73.setText("Course Description");

        jLabel75.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(255, 255, 255));
        jLabel75.setText("College Code");

        course_date_opened.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N

        jLabel77.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(255, 255, 255));
        jLabel77.setText("Date Opened");

        course_date_closed.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N

        jLabel78.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(255, 255, 255));
        jLabel78.setText("Date Closed");

        course_table.setBackground(new java.awt.Color(255, 255, 255));
        course_table.setFont(new java.awt.Font("Segoe UI Semibold", 0, 10)); // NOI18N
        course_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        course_table.setShowGrid(true);
        course_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                course_tableMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(course_table);

        course_college_code_cbmx.setBackground(new java.awt.Color(255, 255, 255));
        course_college_code_cbmx.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N

        jLabel79.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(255, 255, 255));
        jLabel79.setText("Status");

        course_status_cmbx.setBackground(new java.awt.Color(255, 255, 255));
        course_status_cmbx.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        course_status_cmbx.setForeground(new java.awt.Color(0, 0, 0));
        course_status_cmbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "I" }));

        javax.swing.GroupLayout course_screenLayout = new javax.swing.GroupLayout(course_screen);
        course_screen.setLayout(course_screenLayout);
        course_screenLayout.setHorizontalGroup(
            course_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(course_screenLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(course_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(course_screenLayout.createSequentialGroup()
                        .addGroup(course_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(course_code_text, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(course_date_opened, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(course_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(course_screenLayout.createSequentialGroup()
                                .addGroup(course_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(course_screenLayout.createSequentialGroup()
                                        .addGroup(course_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(course_desc_text, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, course_screenLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(course_date_closed, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18))
                            .addGroup(course_screenLayout.createSequentialGroup()
                                .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(158, 158, 158)))
                        .addGroup(course_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel75)
                            .addComponent(course_college_code_cbmx, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel79)
                            .addComponent(course_status_cmbx, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(course_screenLayout.createSequentialGroup()
                        .addComponent(jLabel70)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(course_search_text, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(course_search_button, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(course_add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(course_update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(course_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(course_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16))
        );
        course_screenLayout.setVerticalGroup(
            course_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(course_screenLayout.createSequentialGroup()
                .addGroup(course_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(course_screenLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(course_screenLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(course_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(course_search_text, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(course_screenLayout.createSequentialGroup()
                                .addGroup(course_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(course_add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(course_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(course_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(course_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(course_update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(course_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(course_search_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(course_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, course_screenLayout.createSequentialGroup()
                        .addGroup(course_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel73, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel71))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(course_code_text, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, course_screenLayout.createSequentialGroup()
                        .addComponent(jLabel75)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(course_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(course_desc_text, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(course_college_code_cbmx, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(course_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel79, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(course_screenLayout.createSequentialGroup()
                        .addGroup(course_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel77)
                            .addComponent(jLabel78))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(course_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(course_date_closed, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(course_date_opened, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(course_status_cmbx, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        School_Record.add(course_screen, "card3");

        student_screen.setBackground(new java.awt.Color(0, 51, 102));

        jLabel80.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(255, 255, 255));
        jLabel80.setText("Student:");

        student_search_text.setBackground(new java.awt.Color(255, 255, 255));
        student_search_text.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        student_search_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                student_search_textActionPerformed(evt);
            }
        });

        student_search_button.setBackground(new java.awt.Color(204, 255, 255));
        student_search_button.setForeground(new java.awt.Color(255, 255, 255));
        student_search_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/OOP Project/magnifying-glass.png"))); // NOI18N
        student_search_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                student_search_buttonMouseClicked(evt);
            }
        });
        student_search_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                student_search_buttonActionPerformed(evt);
            }
        });

        student_add.setBackground(new java.awt.Color(204, 255, 255));
        student_add.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        student_add.setForeground(new java.awt.Color(0, 0, 0));
        student_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/OOP Project/add5.png"))); // NOI18N
        student_add.setText("Add");
        student_add.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        student_add.setPreferredSize(new java.awt.Dimension(78, 30));
        student_add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                student_addMouseClicked(evt);
            }
        });
        student_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                student_addActionPerformed(evt);
            }
        });

        student_update.setBackground(new java.awt.Color(204, 255, 255));
        student_update.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        student_update.setForeground(new java.awt.Color(0, 0, 0));
        student_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/OOP Project/pencil2.png"))); // NOI18N
        student_update.setText("Update");
        student_update.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        student_update.setPreferredSize(new java.awt.Dimension(100, 30));
        student_update.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                student_updateMouseClicked(evt);
            }
        });

        student_delete.setBackground(new java.awt.Color(204, 255, 255));
        student_delete.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        student_delete.setForeground(new java.awt.Color(0, 0, 0));
        student_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/OOP Project/trash-bin (2).png"))); // NOI18N
        student_delete.setText("Delete");
        student_delete.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        student_delete.setPreferredSize(new java.awt.Dimension(96, 30));
        student_delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                student_deleteMouseClicked(evt);
            }
        });

        student_refresh.setBackground(new java.awt.Color(204, 255, 255));
        student_refresh.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        student_refresh.setForeground(new java.awt.Color(0, 0, 0));
        student_refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/OOP Project/update.png"))); // NOI18N
        student_refresh.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        student_refresh.setPreferredSize(new java.awt.Dimension(96, 30));
        student_refresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                student_refreshMouseClicked(evt);
            }
        });
        student_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                student_refreshActionPerformed(evt);
            }
        });

        student_no_text.setBackground(new java.awt.Color(255, 255, 255));
        student_no_text.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N

        jLabel81.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(255, 255, 255));
        jLabel81.setText("Student No.");

        jLabel82.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(255, 255, 255));
        jLabel82.setText("Last Name");

        jLabel83.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(255, 255, 255));
        jLabel83.setText("Gender");

        jLabel84.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(255, 255, 255));
        jLabel84.setText("Email Adress");

        student_date_graduated.setDateFormatString("MM/dd/yyyy");
        student_date_graduated.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N

        student_table.setBackground(new java.awt.Color(255, 255, 255));
        student_table.setFont(new java.awt.Font("Segoe UI Semibold", 1, 10)); // NOI18N
        student_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        student_table.setShowGrid(true);
        student_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                student_tableMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(student_table);

        student_gender_cmbx.setBackground(new java.awt.Color(255, 255, 255));
        student_gender_cmbx.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        student_gender_cmbx.setForeground(new java.awt.Color(0, 0, 0));
        student_gender_cmbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "M", "F" }));
        student_gender_cmbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                student_gender_cmbxActionPerformed(evt);
            }
        });

        jLabel86.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(255, 255, 255));
        jLabel86.setText("Phone Number");

        student_address_text.setBackground(new java.awt.Color(255, 255, 255));
        student_address_text.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        student_address_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                student_address_textActionPerformed(evt);
            }
        });

        jLabel87.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(255, 255, 255));
        jLabel87.setText("First Name");

        student_lastname_text.setBackground(new java.awt.Color(255, 255, 255));
        student_lastname_text.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N

        student_course_code_cmbx.setBackground(new java.awt.Color(255, 255, 255));
        student_course_code_cmbx.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        student_course_code_cmbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                student_course_code_cmbxActionPerformed(evt);
            }
        });

        student_phone_text.setBackground(new java.awt.Color(255, 255, 255));
        student_phone_text.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N

        jLabel89.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(255, 255, 255));
        jLabel89.setText("Date Started");

        student_date_started.setDateFormatString("MM/dd/yyyy");
        student_date_started.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N

        jLabel90.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(255, 255, 255));
        jLabel90.setText("Birthday");

        student_bday.setDateFormatString("MM/dd/yyyy");
        student_bday.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N

        student_email_text.setBackground(new java.awt.Color(255, 255, 255));
        student_email_text.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        student_email_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                student_email_textActionPerformed(evt);
            }
        });

        jLabel91.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(255, 255, 255));
        jLabel91.setText("Address");

        jLabel92.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel92.setForeground(new java.awt.Color(255, 255, 255));
        jLabel92.setText("Course Code");

        jLabel93.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel93.setForeground(new java.awt.Color(255, 255, 255));
        jLabel93.setText("Date Graduated");

        jLabel104.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel104.setForeground(new java.awt.Color(255, 255, 255));
        jLabel104.setText("Status");

        student_firstname_text.setBackground(new java.awt.Color(255, 255, 255));
        student_firstname_text.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        student_firstname_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                student_firstname_textActionPerformed(evt);
            }
        });

        student_status_cmbx.setBackground(new java.awt.Color(255, 255, 255));
        student_status_cmbx.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        student_status_cmbx.setForeground(new java.awt.Color(0, 0, 0));
        student_status_cmbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "I" }));
        student_status_cmbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                student_status_cmbxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout student_screenLayout = new javax.swing.GroupLayout(student_screen);
        student_screen.setLayout(student_screenLayout);
        student_screenLayout.setHorizontalGroup(
            student_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, student_screenLayout.createSequentialGroup()
                .addGroup(student_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(student_screenLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(student_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane12, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(student_screenLayout.createSequentialGroup()
                                .addGroup(student_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(student_screenLayout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addGroup(student_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(student_screenLayout.createSequentialGroup()
                                                .addGroup(student_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(student_screenLayout.createSequentialGroup()
                                                        .addComponent(jLabel84)
                                                        .addGap(162, 162, 162)
                                                        .addComponent(jLabel91))
                                                    .addGroup(student_screenLayout.createSequentialGroup()
                                                        .addGroup(student_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel81)
                                                            .addComponent(student_no_text, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(student_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel82)
                                                            .addComponent(student_lastname_text, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGap(6, 6, 6)
                                                        .addGroup(student_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel87)
                                                            .addComponent(student_firstname_text, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addGroup(student_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(student_screenLayout.createSequentialGroup()
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(student_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(student_gender_cmbx, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jLabel83))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(student_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel92)
                                                            .addComponent(student_course_code_cmbx, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(student_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(student_phone_text, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jLabel86))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(student_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel90)
                                                            .addComponent(student_bday, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                    .addGroup(student_screenLayout.createSequentialGroup()
                                                        .addGap(39, 39, 39)
                                                        .addComponent(jLabel89))))
                                            .addGroup(student_screenLayout.createSequentialGroup()
                                                .addComponent(jLabel80)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(student_search_text, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(student_search_button, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(student_add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(student_update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(student_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(student_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(student_screenLayout.createSequentialGroup()
                                        .addComponent(student_email_text, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(student_address_text, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(student_date_started, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(student_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel93)
                                            .addGroup(student_screenLayout.createSequentialGroup()
                                                .addComponent(student_date_graduated, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(student_status_cmbx, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(student_screenLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel104)
                        .addGap(28, 28, 28)))
                .addGap(16, 16, 16))
        );
        student_screenLayout.setVerticalGroup(
            student_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(student_screenLayout.createSequentialGroup()
                .addGroup(student_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(student_screenLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(student_screenLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(student_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(student_search_text, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(student_screenLayout.createSequentialGroup()
                                .addGroup(student_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(student_add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(student_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(student_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(student_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(student_update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(student_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(student_search_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(student_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel82, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(student_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel81)
                        .addComponent(jLabel87)
                        .addComponent(jLabel83)
                        .addComponent(jLabel92)
                        .addComponent(jLabel86, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel90)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(student_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(student_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(student_no_text, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(student_lastname_text, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(student_firstname_text, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(student_gender_cmbx, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(student_course_code_cmbx, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(student_phone_text, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(student_bday, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(student_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel89)
                    .addComponent(jLabel91)
                    .addComponent(jLabel84)
                    .addGroup(student_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel93)
                        .addComponent(jLabel104)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(student_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(student_screenLayout.createSequentialGroup()
                        .addGroup(student_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(student_date_graduated, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(student_date_started, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(student_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(student_address_text, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(student_email_text, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(student_status_cmbx, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        School_Record.add(student_screen, "card3");

        employee_screen.setBackground(new java.awt.Color(0, 51, 102));

        jLabel105.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel105.setForeground(new java.awt.Color(255, 255, 255));
        jLabel105.setText("Employee:");

        employee_search_text.setBackground(new java.awt.Color(255, 255, 255));
        employee_search_text.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        employee_search_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employee_search_textActionPerformed(evt);
            }
        });

        employee_search_button.setBackground(new java.awt.Color(204, 255, 255));
        employee_search_button.setForeground(new java.awt.Color(255, 255, 255));
        employee_search_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/OOP Project/magnifying-glass.png"))); // NOI18N
        employee_search_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                employee_search_buttonMouseClicked(evt);
            }
        });
        employee_search_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employee_search_buttonActionPerformed(evt);
            }
        });

        employee_add.setBackground(new java.awt.Color(204, 255, 255));
        employee_add.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        employee_add.setForeground(new java.awt.Color(0, 0, 0));
        employee_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/OOP Project/add5.png"))); // NOI18N
        employee_add.setText("Add");
        employee_add.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        employee_add.setPreferredSize(new java.awt.Dimension(78, 30));
        employee_add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                employee_addMouseClicked(evt);
            }
        });
        employee_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employee_addActionPerformed(evt);
            }
        });

        employee_update.setBackground(new java.awt.Color(204, 255, 255));
        employee_update.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        employee_update.setForeground(new java.awt.Color(0, 0, 0));
        employee_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/OOP Project/pencil2.png"))); // NOI18N
        employee_update.setText("Update");
        employee_update.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        employee_update.setPreferredSize(new java.awt.Dimension(100, 30));
        employee_update.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                employee_updateMouseClicked(evt);
            }
        });

        employee_delete.setBackground(new java.awt.Color(204, 255, 255));
        employee_delete.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        employee_delete.setForeground(new java.awt.Color(0, 0, 0));
        employee_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/OOP Project/trash-bin (2).png"))); // NOI18N
        employee_delete.setText("Delete");
        employee_delete.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        employee_delete.setPreferredSize(new java.awt.Dimension(96, 30));
        employee_delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                employee_deleteMouseClicked(evt);
            }
        });
        employee_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employee_deleteActionPerformed(evt);
            }
        });

        employee_refresh.setBackground(new java.awt.Color(204, 255, 255));
        employee_refresh.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        employee_refresh.setForeground(new java.awt.Color(0, 0, 0));
        employee_refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/OOP Project/update.png"))); // NOI18N
        employee_refresh.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        employee_refresh.setPreferredSize(new java.awt.Dimension(96, 30));
        employee_refresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                employee_refreshMouseClicked(evt);
            }
        });
        employee_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employee_refreshActionPerformed(evt);
            }
        });

        employee_id_text.setBackground(new java.awt.Color(255, 255, 255));
        employee_id_text.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N

        jLabel106.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel106.setForeground(new java.awt.Color(255, 255, 255));
        jLabel106.setText("Employee ID");

        jLabel107.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel107.setForeground(new java.awt.Color(255, 255, 255));
        jLabel107.setText("Last Name");

        jLabel108.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel108.setForeground(new java.awt.Color(255, 255, 255));
        jLabel108.setText("Gender");

        jLabel109.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel109.setForeground(new java.awt.Color(255, 255, 255));
        jLabel109.setText("Email Adress");

        employee_date_resigned.setDateFormatString("MM/dd/yyyy");
        employee_date_resigned.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N

        employee_table.setBackground(new java.awt.Color(255, 255, 255));
        employee_table.setFont(new java.awt.Font("Segoe UI Semibold", 1, 10)); // NOI18N
        employee_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        employee_table.setShowGrid(true);
        employee_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                employee_tableMouseClicked(evt);
            }
        });
        jScrollPane14.setViewportView(employee_table);

        employee_gender_cmbx.setBackground(new java.awt.Color(255, 255, 255));
        employee_gender_cmbx.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        employee_gender_cmbx.setForeground(new java.awt.Color(0, 0, 0));
        employee_gender_cmbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "M", "F" }));
        employee_gender_cmbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employee_gender_cmbxActionPerformed(evt);
            }
        });

        jLabel110.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel110.setForeground(new java.awt.Color(255, 255, 255));
        jLabel110.setText("Phone Number");

        employee_address_text.setBackground(new java.awt.Color(255, 255, 255));
        employee_address_text.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        employee_address_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employee_address_textActionPerformed(evt);
            }
        });

        employee_firstname_text.setBackground(new java.awt.Color(255, 255, 255));
        employee_firstname_text.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N

        jLabel111.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel111.setForeground(new java.awt.Color(255, 255, 255));
        jLabel111.setText("First Name");

        employee_lastname_text.setBackground(new java.awt.Color(255, 255, 255));
        employee_lastname_text.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N

        employee_phone_text.setBackground(new java.awt.Color(255, 255, 255));
        employee_phone_text.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N

        jLabel112.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel112.setForeground(new java.awt.Color(255, 255, 255));
        jLabel112.setText("Date Started");

        employee_date_started.setDateFormatString("MM/dd/yyyy");
        employee_date_started.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N

        jLabel113.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel113.setForeground(new java.awt.Color(255, 255, 255));
        jLabel113.setText("Birthday");

        employee_bday.setDateFormatString("MM/dd/yyyy");
        employee_bday.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N

        employee_email_text.setBackground(new java.awt.Color(255, 255, 255));
        employee_email_text.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        employee_email_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employee_email_textActionPerformed(evt);
            }
        });

        jLabel114.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel114.setForeground(new java.awt.Color(255, 255, 255));
        jLabel114.setText("Address");

        jLabel116.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel116.setForeground(new java.awt.Color(255, 255, 255));
        jLabel116.setText("Date Resigned");

        jLabel117.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel117.setForeground(new java.awt.Color(255, 255, 255));
        jLabel117.setText("Status");

        employee_status_cmbx.setBackground(new java.awt.Color(255, 255, 255));
        employee_status_cmbx.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        employee_status_cmbx.setForeground(new java.awt.Color(0, 0, 0));
        employee_status_cmbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "I" }));
        employee_status_cmbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employee_status_cmbxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout employee_screenLayout = new javax.swing.GroupLayout(employee_screen);
        employee_screen.setLayout(employee_screenLayout);
        employee_screenLayout.setHorizontalGroup(
            employee_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, employee_screenLayout.createSequentialGroup()
                .addGroup(employee_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(employee_screenLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(employee_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane14, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(employee_screenLayout.createSequentialGroup()
                                .addGroup(employee_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(employee_screenLayout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addGroup(employee_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(employee_screenLayout.createSequentialGroup()
                                                .addGroup(employee_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(employee_screenLayout.createSequentialGroup()
                                                        .addGroup(employee_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel106)
                                                            .addComponent(employee_id_text, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(employee_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel107)
                                                            .addComponent(employee_lastname_text, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGap(6, 6, 6)
                                                        .addComponent(jLabel111))
                                                    .addGroup(employee_screenLayout.createSequentialGroup()
                                                        .addComponent(jLabel109)
                                                        .addGap(162, 162, 162)
                                                        .addComponent(jLabel114))
                                                    .addGroup(employee_screenLayout.createSequentialGroup()
                                                        .addGap(292, 292, 292)
                                                        .addComponent(employee_firstname_text, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGroup(employee_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(employee_screenLayout.createSequentialGroup()
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(employee_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(employee_gender_cmbx, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jLabel108))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(employee_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(employee_screenLayout.createSequentialGroup()
                                                                .addComponent(jLabel110)
                                                                .addGap(114, 114, 114)
                                                                .addComponent(jLabel113))
                                                            .addGroup(employee_screenLayout.createSequentialGroup()
                                                                .addComponent(employee_phone_text, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(employee_bday, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                    .addGroup(employee_screenLayout.createSequentialGroup()
                                                        .addGap(39, 39, 39)
                                                        .addComponent(jLabel112))))
                                            .addGroup(employee_screenLayout.createSequentialGroup()
                                                .addComponent(jLabel105, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(employee_search_text, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(employee_search_button, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(employee_add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(employee_update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(employee_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(employee_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(employee_screenLayout.createSequentialGroup()
                                        .addComponent(employee_email_text, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(employee_address_text, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(employee_date_started, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(employee_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel116)
                                            .addGroup(employee_screenLayout.createSequentialGroup()
                                                .addComponent(employee_date_resigned, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(employee_status_cmbx, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(employee_screenLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel117)
                        .addGap(28, 28, 28)))
                .addGap(16, 16, 16))
        );
        employee_screenLayout.setVerticalGroup(
            employee_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(employee_screenLayout.createSequentialGroup()
                .addGroup(employee_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(employee_screenLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel105, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(employee_screenLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(employee_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(employee_search_text, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(employee_screenLayout.createSequentialGroup()
                                .addGroup(employee_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(employee_add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(employee_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(employee_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(employee_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(employee_update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(employee_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(employee_search_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(employee_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel107, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(employee_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel106)
                        .addComponent(jLabel111)
                        .addComponent(jLabel108)
                        .addComponent(jLabel110, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel113)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(employee_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(employee_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(employee_id_text, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(employee_lastname_text, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(employee_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(employee_firstname_text, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(employee_gender_cmbx, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(employee_phone_text, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(employee_bday, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(employee_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel112)
                    .addComponent(jLabel114)
                    .addComponent(jLabel109)
                    .addGroup(employee_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel116)
                        .addComponent(jLabel117)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(employee_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(employee_screenLayout.createSequentialGroup()
                        .addGroup(employee_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(employee_date_resigned, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(employee_date_started, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(employee_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(employee_address_text, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(employee_email_text, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(employee_status_cmbx, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        School_Record.add(employee_screen, "card3");

        subject_screen.setBackground(new java.awt.Color(0, 51, 102));

        jLabel115.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel115.setForeground(new java.awt.Color(255, 255, 255));
        jLabel115.setText("Subject:");

        subject_search_text.setBackground(new java.awt.Color(255, 255, 255));
        subject_search_text.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        subject_search_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subject_search_textActionPerformed(evt);
            }
        });

        subject_search_button.setBackground(new java.awt.Color(204, 255, 255));
        subject_search_button.setForeground(new java.awt.Color(255, 255, 255));
        subject_search_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/OOP Project/magnifying-glass.png"))); // NOI18N
        subject_search_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                subject_search_buttonMouseClicked(evt);
            }
        });
        subject_search_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subject_search_buttonActionPerformed(evt);
            }
        });

        subject_add.setBackground(new java.awt.Color(204, 255, 255));
        subject_add.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        subject_add.setForeground(new java.awt.Color(0, 0, 0));
        subject_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/OOP Project/add5.png"))); // NOI18N
        subject_add.setText("Add");
        subject_add.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        subject_add.setPreferredSize(new java.awt.Dimension(78, 30));
        subject_add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                subject_addMouseClicked(evt);
            }
        });
        subject_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subject_addActionPerformed(evt);
            }
        });

        subject_update.setBackground(new java.awt.Color(204, 255, 255));
        subject_update.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        subject_update.setForeground(new java.awt.Color(0, 0, 0));
        subject_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/OOP Project/pencil2.png"))); // NOI18N
        subject_update.setText("Update");
        subject_update.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        subject_update.setPreferredSize(new java.awt.Dimension(100, 30));
        subject_update.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                subject_updateMouseClicked(evt);
            }
        });

        subject_delete.setBackground(new java.awt.Color(204, 255, 255));
        subject_delete.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        subject_delete.setForeground(new java.awt.Color(0, 0, 0));
        subject_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/OOP Project/trash-bin (2).png"))); // NOI18N
        subject_delete.setText("Delete");
        subject_delete.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        subject_delete.setPreferredSize(new java.awt.Dimension(96, 30));
        subject_delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                subject_deleteMouseClicked(evt);
            }
        });

        subject_refresh.setBackground(new java.awt.Color(204, 255, 255));
        subject_refresh.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        subject_refresh.setForeground(new java.awt.Color(0, 0, 0));
        subject_refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/OOP Project/update.png"))); // NOI18N
        subject_refresh.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        subject_refresh.setPreferredSize(new java.awt.Dimension(96, 30));
        subject_refresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                subject_refreshMouseClicked(evt);
            }
        });
        subject_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subject_refreshActionPerformed(evt);
            }
        });

        subject_code_text.setBackground(new java.awt.Color(255, 255, 255));
        subject_code_text.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N

        jLabel118.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel118.setForeground(new java.awt.Color(255, 255, 255));
        jLabel118.setText("Subject Code");

        jLabel121.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel121.setForeground(new java.awt.Color(255, 255, 255));
        jLabel121.setText("Curriculum");

        subject_date_closed.setDateFormatString("MM/dd/yyyy");
        subject_date_closed.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N

        subject_table.setBackground(new java.awt.Color(255, 255, 255));
        subject_table.setFont(new java.awt.Font("Segoe UI Semibold", 1, 10)); // NOI18N
        subject_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        subject_table.setShowGrid(true);
        subject_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                subject_tableMouseClicked(evt);
            }
        });
        jScrollPane15.setViewportView(subject_table);

        subject_date_opened.setDateFormatString("MM/dd/yyyy");
        subject_date_opened.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N

        subject_curriculum_text.setBackground(new java.awt.Color(255, 255, 255));
        subject_curriculum_text.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        subject_curriculum_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subject_curriculum_textActionPerformed(evt);
            }
        });

        jLabel126.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel126.setForeground(new java.awt.Color(255, 255, 255));
        jLabel126.setText("College Code");

        jLabel127.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel127.setForeground(new java.awt.Color(255, 255, 255));
        jLabel127.setText("Date Closed");

        jLabel128.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel128.setForeground(new java.awt.Color(255, 255, 255));
        jLabel128.setText("Units");

        subject_desc_text.setBackground(new java.awt.Color(255, 255, 255));
        subject_desc_text.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        subject_desc_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subject_desc_textActionPerformed(evt);
            }
        });

        jLabel119.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel119.setForeground(new java.awt.Color(255, 255, 255));
        jLabel119.setText("Subject Description");

        subject_units_text.setBackground(new java.awt.Color(255, 255, 255));
        subject_units_text.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        subject_units_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subject_units_textActionPerformed(evt);
            }
        });

        jLabel129.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel129.setForeground(new java.awt.Color(255, 255, 255));
        jLabel129.setText("Date Opened");

        subject_college_code_cmbx.setBackground(new java.awt.Color(255, 255, 255));
        subject_college_code_cmbx.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N

        jLabel130.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel130.setForeground(new java.awt.Color(255, 255, 255));
        jLabel130.setText("Status");

        subject_status_cmbx.setBackground(new java.awt.Color(255, 255, 255));
        subject_status_cmbx.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        subject_status_cmbx.setForeground(new java.awt.Color(0, 0, 0));
        subject_status_cmbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "I" }));

        javax.swing.GroupLayout subject_screenLayout = new javax.swing.GroupLayout(subject_screen);
        subject_screen.setLayout(subject_screenLayout);
        subject_screenLayout.setHorizontalGroup(
            subject_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subject_screenLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(subject_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 863, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(subject_screenLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(subject_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(subject_screenLayout.createSequentialGroup()
                                .addGroup(subject_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel118)
                                    .addComponent(subject_code_text, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(subject_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(subject_desc_text, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel119))
                                .addGap(18, 18, 18)
                                .addGroup(subject_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel128)
                                    .addComponent(subject_units_text, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(3, 3, 3))
                            .addGroup(subject_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(subject_screenLayout.createSequentialGroup()
                                    .addComponent(jLabel115, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(subject_search_text, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(subject_search_button, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(subject_add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(subject_update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(subject_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(subject_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(subject_screenLayout.createSequentialGroup()
                                    .addComponent(jLabel121)
                                    .addGap(79, 79, 79)
                                    .addGroup(subject_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(subject_college_code_cmbx, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel126))
                                    .addGap(18, 18, 18)
                                    .addGroup(subject_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel130)
                                        .addComponent(subject_status_cmbx, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(subject_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(subject_date_opened, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel129))
                                    .addGap(18, 18, 18)
                                    .addGroup(subject_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel127)
                                        .addComponent(subject_date_closed, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addComponent(subject_curriculum_text, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        subject_screenLayout.setVerticalGroup(
            subject_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subject_screenLayout.createSequentialGroup()
                .addGroup(subject_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(subject_screenLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel115, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(subject_screenLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(subject_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(subject_search_text, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(subject_screenLayout.createSequentialGroup()
                                .addGroup(subject_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(subject_add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(subject_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(subject_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(subject_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(subject_update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(subject_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(subject_search_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(subject_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(subject_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel118)
                        .addComponent(jLabel119, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel128))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(subject_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(subject_desc_text, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(subject_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(subject_code_text, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(subject_units_text, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(subject_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel121)
                    .addComponent(jLabel126)
                    .addComponent(jLabel127)
                    .addComponent(jLabel129)
                    .addComponent(jLabel130))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(subject_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(subject_date_closed, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(subject_date_opened, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(subject_curriculum_text, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(subject_screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(subject_college_code_cmbx, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                        .addComponent(subject_status_cmbx, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        School_Record.add(subject_screen, "card3");

        javax.swing.GroupLayout Dimension_Table_ScreenLayout = new javax.swing.GroupLayout(Dimension_Table_Screen);
        Dimension_Table_Screen.setLayout(Dimension_Table_ScreenLayout);
        Dimension_Table_ScreenLayout.setHorizontalGroup(
            Dimension_Table_ScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Dimension_Table_ScreenLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(Dimension_Table_ScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(college, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(course, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(student, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(employee, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(subject, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sy_sem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(School_Record, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Dimension_Table_ScreenLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(243, 243, 243))
        );
        Dimension_Table_ScreenLayout.setVerticalGroup(
            Dimension_Table_ScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Dimension_Table_ScreenLayout.createSequentialGroup()
                .addGroup(Dimension_Table_ScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Dimension_Table_ScreenLayout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(sy_sem, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(college, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(course, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(student, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(employee, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(subject, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Dimension_Table_ScreenLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(School_Record, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ParentPanel.add(Dimension_Table_Screen, "card2");

        Add_Schedule_Screen.setBackground(new java.awt.Color(255, 255, 255));
        Add_Schedule_Screen.setPreferredSize(new java.awt.Dimension(930, 520));

        jLabel4.setFont(new java.awt.Font("Franklin Gothic Heavy", 1, 60)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 102));
        jLabel4.setText("Add Schedule");

        jPanel1.setBackground(new java.awt.Color(0, 51, 102));

        schedule_semester_cmbx.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        schedule_semester_cmbx.setMaximumRowCount(5);
        schedule_semester_cmbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        schedule_semester_cmbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                schedule_semester_cmbxActionPerformed(evt);
            }
        });

        schedule_sy_cmbx.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        schedule_sy_cmbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                schedule_sy_cmbxActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("College Code");

        schedule_college_code_cmbx.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N

        jLabel19.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Semester");

        jLabel25.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("School Year");

        jLabel27.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Subject Code:");

        jLabel28.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Block No.");

        jLabel29.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Day");

        schedule_room_text.setBackground(new java.awt.Color(255, 255, 255));
        schedule_room_text.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N

        jLabel30.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Time");

        jLabel31.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Room");

        schedule_add.setBackground(new java.awt.Color(204, 255, 255));
        schedule_add.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        schedule_add.setForeground(new java.awt.Color(0, 0, 0));
        schedule_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/OOP Project/add (1).png"))); // NOI18N
        schedule_add.setText("Add  ");
        schedule_add.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        schedule_add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                schedule_addMouseClicked(evt);
            }
        });
        schedule_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                schedule_addActionPerformed(evt);
            }
        });

        schedule_block_text.setBackground(new java.awt.Color(255, 255, 255));
        schedule_block_text.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        schedule_block_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                schedule_block_textActionPerformed(evt);
            }
        });

        schedule_type_cmbx.setBackground(new java.awt.Color(255, 255, 255));
        schedule_type_cmbx.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        schedule_type_cmbx.setForeground(new java.awt.Color(0, 0, 0));
        schedule_type_cmbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "OL", "F2F" }));
        schedule_type_cmbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                schedule_type_cmbxActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Type");

        schedule_time_text.setBackground(new java.awt.Color(255, 255, 255));
        schedule_time_text.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N

        jLabel33.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Sequence No.");

        schedule_next.setBackground(new java.awt.Color(204, 255, 255));
        schedule_next.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        schedule_next.setForeground(new java.awt.Color(0, 0, 0));
        schedule_next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/OOP Project/right-arrow.png"))); // NOI18N
        schedule_next.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        schedule_next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                schedule_nextActionPerformed(evt);
            }
        });

        schedule_refresh.setBackground(new java.awt.Color(204, 255, 255));
        schedule_refresh.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        schedule_refresh.setForeground(new java.awt.Color(0, 0, 0));
        schedule_refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/OOP Project/refresh-page-option.png"))); // NOI18N
        schedule_refresh.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        schedule_refresh.setPreferredSize(new java.awt.Dimension(96, 30));
        schedule_refresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                schedule_refreshMouseClicked(evt);
            }
        });
        schedule_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                schedule_refreshActionPerformed(evt);
            }
        });

        schedule_day_cmbx.setBackground(new java.awt.Color(255, 255, 255));
        schedule_day_cmbx.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        schedule_day_cmbx.setForeground(new java.awt.Color(0, 0, 0));
        schedule_day_cmbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "M", "T", "W", "Th", "F", "S", "Su" }));

        jLabel37.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("Employee ID:");

        schedule_subject_search_text.setBackground(new java.awt.Color(255, 255, 255));
        schedule_subject_search_text.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        schedule_subject_search_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                schedule_subject_search_textActionPerformed(evt);
            }
        });

        schedule_subject_search_button.setBackground(new java.awt.Color(204, 255, 255));
        schedule_subject_search_button.setForeground(new java.awt.Color(255, 255, 255));
        schedule_subject_search_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/OOP Project/magnifying-glass.png"))); // NOI18N
        schedule_subject_search_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                schedule_subject_search_buttonMouseClicked(evt);
            }
        });
        schedule_subject_search_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                schedule_subject_search_buttonActionPerformed(evt);
            }
        });

        schedule_subject_table.setBackground(new java.awt.Color(255, 255, 255));
        schedule_subject_table.setFont(new java.awt.Font("Segoe UI Semibold", 1, 10)); // NOI18N
        schedule_subject_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        schedule_subject_table.setShowGrid(true);
        schedule_subject_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                schedule_subject_tableMouseClicked(evt);
            }
        });
        jScrollPane16.setViewportView(schedule_subject_table);

        schedule_employee_table.setBackground(new java.awt.Color(255, 255, 255));
        schedule_employee_table.setFont(new java.awt.Font("Segoe UI Semibold", 1, 10)); // NOI18N
        schedule_employee_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        schedule_employee_table.setShowGrid(true);
        schedule_employee_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                schedule_employee_tableMouseClicked(evt);
            }
        });
        jScrollPane17.setViewportView(schedule_employee_table);

        schedule_employee_search_text.setBackground(new java.awt.Color(255, 255, 255));
        schedule_employee_search_text.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        schedule_employee_search_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                schedule_employee_search_textActionPerformed(evt);
            }
        });

        schedule_employee_search_button.setBackground(new java.awt.Color(204, 255, 255));
        schedule_employee_search_button.setForeground(new java.awt.Color(255, 255, 255));
        schedule_employee_search_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/OOP Project/magnifying-glass.png"))); // NOI18N
        schedule_employee_search_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                schedule_employee_search_buttonMouseClicked(evt);
            }
        });
        schedule_employee_search_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                schedule_employee_search_buttonActionPerformed(evt);
            }
        });

        schedule_subject_code_text.setBackground(new java.awt.Color(204, 204, 204));
        schedule_subject_code_text.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        schedule_subject_code_text.setForeground(new java.awt.Color(0, 0, 0));
        schedule_subject_code_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                schedule_subject_code_textActionPerformed(evt);
            }
        });

        schedule_employeeID_text.setBackground(new java.awt.Color(204, 204, 204));
        schedule_employeeID_text.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        schedule_employeeID_text.setForeground(new java.awt.Color(0, 0, 0));
        schedule_employeeID_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                schedule_employeeID_textActionPerformed(evt);
            }
        });

        schedule_employeeName_text.setBackground(new java.awt.Color(204, 204, 204));
        schedule_employeeName_text.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        schedule_employeeName_text.setForeground(new java.awt.Color(0, 0, 0));
        schedule_employeeName_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                schedule_employeeName_textActionPerformed(evt);
            }
        });

        schedule_sequence_cmbx.setBackground(new java.awt.Color(255, 255, 255));
        schedule_sequence_cmbx.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        schedule_sequence_cmbx.setForeground(new java.awt.Color(0, 0, 0));
        schedule_sequence_cmbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2" }));
        schedule_sequence_cmbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                schedule_sequence_cmbxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addComponent(schedule_sy_cmbx, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel19)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel28))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(schedule_semester_cmbx, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(schedule_college_code_cmbx, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(schedule_block_text)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(schedule_day_cmbx, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(schedule_time_text, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31)
                            .addComponent(schedule_room_text, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(schedule_sequence_cmbx, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel32)
                            .addComponent(schedule_type_cmbx, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(schedule_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(348, 348, 348)
                                .addComponent(schedule_add, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(schedule_next, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(schedule_subject_code_text)
                                        .addGap(4, 4, 4))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(schedule_subject_search_text)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(schedule_subject_search_button, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(schedule_employee_search_text)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(schedule_employee_search_button, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel37)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(schedule_employeeID_text, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(schedule_employeeName_text))
                                    .addComponent(jScrollPane17, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(37, 37, 37))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel25)
                    .addComponent(jLabel18)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29)
                    .addComponent(jLabel30)
                    .addComponent(jLabel33)
                    .addComponent(jLabel32)
                    .addComponent(jLabel31))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(schedule_semester_cmbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(schedule_sy_cmbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(schedule_college_code_cmbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(schedule_block_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(schedule_day_cmbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(schedule_time_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(schedule_room_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(schedule_type_cmbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(schedule_sequence_cmbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addGap(8, 8, 8))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel37)
                        .addComponent(schedule_employeeID_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(schedule_employeeName_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(schedule_subject_code_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(schedule_subject_search_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(schedule_subject_search_button, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(schedule_employee_search_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(schedule_employee_search_button, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(schedule_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(schedule_add))
                    .addComponent(schedule_next))
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout Add_Schedule_ScreenLayout = new javax.swing.GroupLayout(Add_Schedule_Screen);
        Add_Schedule_Screen.setLayout(Add_Schedule_ScreenLayout);
        Add_Schedule_ScreenLayout.setHorizontalGroup(
            Add_Schedule_ScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Add_Schedule_ScreenLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Add_Schedule_ScreenLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(252, 252, 252))
        );
        Add_Schedule_ScreenLayout.setVerticalGroup(
            Add_Schedule_ScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Add_Schedule_ScreenLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ParentPanel.add(Add_Schedule_Screen, "card2");

        Record_Grade_Screen.setBackground(new java.awt.Color(255, 255, 255));
        Record_Grade_Screen.setPreferredSize(new java.awt.Dimension(930, 520));

        jLabel12.setFont(new java.awt.Font("Franklin Gothic Heavy", 1, 60)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 51, 102));
        jLabel12.setText("Record Grade");

        jPanel5.setBackground(new java.awt.Color(0, 51, 102));
        jPanel5.setPreferredSize(new java.awt.Dimension(1014, 420));

        grade_sem_cmbx.setBackground(new java.awt.Color(255, 255, 255));
        grade_sem_cmbx.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        grade_sem_cmbx.setMaximumRowCount(5);
        grade_sem_cmbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        grade_sem_cmbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grade_sem_cmbxActionPerformed(evt);
            }
        });

        grade_sy_cmbx.setBackground(new java.awt.Color(255, 255, 255));
        grade_sy_cmbx.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        grade_sy_cmbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grade_sy_cmbxActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Semester");

        jLabel26.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("School Year");

        jLabel36.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("Student No. :");

        jLabel40.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("Block No.");

        grade_add.setBackground(new java.awt.Color(204, 255, 255));
        grade_add.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        grade_add.setForeground(new java.awt.Color(0, 0, 0));
        grade_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/OOP Project/add (1).png"))); // NOI18N
        grade_add.setText("Add  ");
        grade_add.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        grade_add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grade_addMouseClicked(evt);
            }
        });
        grade_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grade_addActionPerformed(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setText("Subject Code:");

        jLabel43.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setText("Grade");

        grade_next.setBackground(new java.awt.Color(204, 255, 255));
        grade_next.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        grade_next.setForeground(new java.awt.Color(0, 0, 0));
        grade_next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/OOP Project/right-arrow.png"))); // NOI18N
        grade_next.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        grade_next.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grade_nextMouseClicked(evt);
            }
        });
        grade_next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grade_nextActionPerformed(evt);
            }
        });

        grade_cmbx.setBackground(new java.awt.Color(255, 255, 255));
        grade_cmbx.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        grade_cmbx.setForeground(new java.awt.Color(0, 0, 0));
        grade_cmbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1.00", "1.25", "1.50", "1.75", "2.00", "2.25", "2.50", "2.75", "3.00", "5.00" }));
        grade_cmbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grade_cmbxActionPerformed(evt);
            }
        });

        grade_block_text.setBackground(new java.awt.Color(204, 204, 204));
        grade_block_text.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N

        schedule_refresh1.setBackground(new java.awt.Color(204, 255, 255));
        schedule_refresh1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        schedule_refresh1.setForeground(new java.awt.Color(0, 0, 0));
        schedule_refresh1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/OOP Project/refresh-page-option.png"))); // NOI18N
        schedule_refresh1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        schedule_refresh1.setPreferredSize(new java.awt.Dimension(96, 30));
        schedule_refresh1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                schedule_refresh1MouseClicked(evt);
            }
        });

        grade_studentNo_text.setBackground(new java.awt.Color(204, 204, 204));
        grade_studentNo_text.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        grade_studentNo_text.setForeground(new java.awt.Color(0, 0, 0));

        grade_studentName_text.setBackground(new java.awt.Color(204, 204, 204));
        grade_studentName_text.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        grade_studentName_text.setForeground(new java.awt.Color(0, 0, 0));
        grade_studentName_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grade_studentName_textActionPerformed(evt);
            }
        });

        grade_student_search_text.setBackground(new java.awt.Color(255, 255, 255));
        grade_student_search_text.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        grade_student_search_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grade_student_search_textActionPerformed(evt);
            }
        });

        grade_student_search_button.setBackground(new java.awt.Color(204, 255, 255));
        grade_student_search_button.setForeground(new java.awt.Color(255, 255, 255));
        grade_student_search_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/OOP Project/magnifying-glass.png"))); // NOI18N
        grade_student_search_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grade_student_search_buttonMouseClicked(evt);
            }
        });
        grade_student_search_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grade_student_search_buttonActionPerformed(evt);
            }
        });

        grade_subject_code_text.setBackground(new java.awt.Color(255, 255, 255));
        grade_subject_code_text.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N

        grade_student_table.setBackground(new java.awt.Color(255, 255, 255));
        grade_student_table.setFont(new java.awt.Font("Segoe UI Semibold", 1, 10)); // NOI18N
        grade_student_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        grade_student_table.setShowGrid(true);
        grade_student_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grade_student_tableMouseClicked(evt);
            }
        });
        jScrollPane18.setViewportView(grade_student_table);

        grade_subjectCode_text.setBackground(new java.awt.Color(204, 204, 204));
        grade_subjectCode_text.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        grade_subjectCode_text.setForeground(new java.awt.Color(0, 0, 0));
        grade_subjectCode_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grade_subjectCode_textActionPerformed(evt);
            }
        });

        grade_subject_search_button.setBackground(new java.awt.Color(204, 255, 255));
        grade_subject_search_button.setForeground(new java.awt.Color(255, 255, 255));
        grade_subject_search_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/OOP Project/magnifying-glass.png"))); // NOI18N
        grade_subject_search_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grade_subject_search_buttonMouseClicked(evt);
            }
        });
        grade_subject_search_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grade_subject_search_buttonActionPerformed(evt);
            }
        });

        grade_subject_table.setBackground(new java.awt.Color(255, 255, 255));
        grade_subject_table.setFont(new java.awt.Font("Segoe UI Semibold", 1, 10)); // NOI18N
        grade_subject_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        grade_subject_table.setShowGrid(true);
        grade_subject_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grade_subject_tableMouseClicked(evt);
            }
        });
        jScrollPane19.setViewportView(grade_subject_table);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(schedule_refresh1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(349, 349, 349)
                        .addComponent(grade_add, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(grade_sy_cmbx, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                            .addComponent(jLabel36)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(grade_studentNo_text, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(grade_studentName_text, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                            .addGap(24, 24, 24)
                                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel21)
                                                .addComponent(grade_sem_cmbx, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addComponent(grade_student_search_text, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(grade_student_search_button, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(grade_block_text, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel40))
                                        .addGap(23, 23, 23)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel43)
                                            .addComponent(grade_cmbx, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel42)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(grade_subjectCode_text, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(grade_next, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                            .addComponent(grade_subject_code_text, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(grade_subject_search_button, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(4, 4, 4)))
                        .addGap(56, 56, 56))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jLabel21)
                    .addComponent(jLabel40)
                    .addComponent(jLabel43))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(grade_sy_cmbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(grade_sem_cmbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(grade_block_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(grade_cmbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(grade_studentNo_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42)
                    .addComponent(grade_studentName_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(grade_subjectCode_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(grade_student_search_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(grade_student_search_button, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(grade_subject_code_text, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(grade_subject_search_button, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane18, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(schedule_refresh1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(grade_next)
                    .addComponent(grade_add))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Record_Grade_ScreenLayout = new javax.swing.GroupLayout(Record_Grade_Screen);
        Record_Grade_Screen.setLayout(Record_Grade_ScreenLayout);
        Record_Grade_ScreenLayout.setHorizontalGroup(
            Record_Grade_ScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Record_Grade_ScreenLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(252, 252, 252))
            .addGroup(Record_Grade_ScreenLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Record_Grade_ScreenLayout.setVerticalGroup(
            Record_Grade_ScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Record_Grade_ScreenLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ParentPanel.add(Record_Grade_Screen, "card2");

        Subject_Schedule_Screen.setBackground(new java.awt.Color(255, 255, 255));
        Subject_Schedule_Screen.setPreferredSize(new java.awt.Dimension(930, 520));

        jLabel13.setFont(new java.awt.Font("Franklin Gothic Heavy", 1, 60)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 51, 102));
        jLabel13.setText("Subject Schedule");

        jPanel6.setBackground(new java.awt.Color(0, 51, 102));

        schedule_search_text.setBackground(new java.awt.Color(255, 255, 255));
        schedule_search_text.setFont(new java.awt.Font("Segoe UI Emoji", 1, 16)); // NOI18N

        schedule_search_button.setBackground(new java.awt.Color(204, 255, 255));
        schedule_search_button.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        schedule_search_button.setForeground(new java.awt.Color(0, 0, 0));
        schedule_search_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/OOP Project/magnifying-glass.png"))); // NOI18N
        schedule_search_button.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        schedule_search_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                schedule_search_buttonMouseClicked(evt);
            }
        });

        schedule_table.setBackground(new java.awt.Color(255, 255, 255));
        schedule_table.setFont(new java.awt.Font("Segoe UI Semibold", 1, 10)); // NOI18N
        schedule_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        schedule_table.setShowGrid(true);
        schedule_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                schedule_tableMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(schedule_table);

        schedule_search_button1.setBackground(new java.awt.Color(204, 255, 255));
        schedule_search_button1.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        schedule_search_button1.setForeground(new java.awt.Color(0, 0, 0));
        schedule_search_button1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/OOP Project/left-arrow.png"))); // NOI18N
        schedule_search_button1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        schedule_search_button1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                schedule_search_button1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(schedule_search_text, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(schedule_search_button, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(364, 364, 364)
                        .addComponent(schedule_search_button1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 960, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(schedule_search_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(schedule_search_text, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(schedule_search_button1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Subject_Schedule_ScreenLayout = new javax.swing.GroupLayout(Subject_Schedule_Screen);
        Subject_Schedule_Screen.setLayout(Subject_Schedule_ScreenLayout);
        Subject_Schedule_ScreenLayout.setHorizontalGroup(
            Subject_Schedule_ScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Subject_Schedule_ScreenLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Subject_Schedule_ScreenLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(254, 254, 254))
        );
        Subject_Schedule_ScreenLayout.setVerticalGroup(
            Subject_Schedule_ScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Subject_Schedule_ScreenLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ParentPanel.add(Subject_Schedule_Screen, "card2");

        Student_Grade_Screen.setBackground(new java.awt.Color(255, 255, 255));
        Student_Grade_Screen.setPreferredSize(new java.awt.Dimension(930, 520));

        jLabel14.setFont(new java.awt.Font("Franklin Gothic Heavy", 1, 60)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 51, 102));
        jLabel14.setText("Student's Grade");

        jPanel7.setBackground(new java.awt.Color(0, 51, 102));

        grade_search_text.setBackground(new java.awt.Color(255, 255, 255));
        grade_search_text.setFont(new java.awt.Font("Segoe UI Emoji", 1, 16)); // NOI18N

        grade_search_button.setBackground(new java.awt.Color(204, 255, 255));
        grade_search_button.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        grade_search_button.setForeground(new java.awt.Color(0, 0, 0));
        grade_search_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/OOP Project/magnifying-glass.png"))); // NOI18N
        grade_search_button.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        grade_table.setBackground(new java.awt.Color(255, 255, 255));
        grade_table.setFont(new java.awt.Font("Segoe UI Semibold", 1, 10)); // NOI18N
        grade_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        grade_table.setShowGrid(true);
        jScrollPane8.setViewportView(grade_table);

        schedule_search_button2.setBackground(new java.awt.Color(204, 255, 255));
        schedule_search_button2.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        schedule_search_button2.setForeground(new java.awt.Color(0, 0, 0));
        schedule_search_button2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/OOP Project/left-arrow.png"))); // NOI18N
        schedule_search_button2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        schedule_search_button2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                schedule_search_button2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 960, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(grade_search_text, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(grade_search_button, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(schedule_search_button2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(grade_search_button, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(grade_search_text)
                    .addComponent(schedule_search_button2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Student_Grade_ScreenLayout = new javax.swing.GroupLayout(Student_Grade_Screen);
        Student_Grade_Screen.setLayout(Student_Grade_ScreenLayout);
        Student_Grade_ScreenLayout.setHorizontalGroup(
            Student_Grade_ScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Student_Grade_ScreenLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Student_Grade_ScreenLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addGap(276, 276, 276))
        );
        Student_Grade_ScreenLayout.setVerticalGroup(
            Student_Grade_ScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Student_Grade_ScreenLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ParentPanel.add(Student_Grade_Screen, "card2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(navigation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(ParentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1078, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(navigation, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
            .addComponent(ParentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void Student_GradeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Student_GradeActionPerformed
        ParentPanel.removeAll();
        ParentPanel.add(Student_Grade_Screen);
        ParentPanel.repaint();
        ParentPanel.revalidate();
    }//GEN-LAST:event_Student_GradeActionPerformed

    private void Student_GradeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Student_GradeMouseExited
        Student_Grade.setBackground(new Color(0,51,102));
    }//GEN-LAST:event_Student_GradeMouseExited

    private void Student_GradeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Student_GradeMouseEntered
        Student_Grade.setBackground(Color.white);
    }//GEN-LAST:event_Student_GradeMouseEntered

    private void Subject_ScheduleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Subject_ScheduleActionPerformed
        ParentPanel.removeAll();
        ParentPanel.add(Subject_Schedule_Screen);
        ParentPanel.repaint();
        ParentPanel.revalidate();
    }//GEN-LAST:event_Subject_ScheduleActionPerformed

    private void Subject_ScheduleMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Subject_ScheduleMouseExited
        Subject_Schedule.setBackground(new Color(0,51,102));
    }//GEN-LAST:event_Subject_ScheduleMouseExited

    private void Subject_ScheduleMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Subject_ScheduleMouseEntered
        Subject_Schedule.setBackground(Color.white);
    }//GEN-LAST:event_Subject_ScheduleMouseEntered

    private void Record_GradeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Record_GradeActionPerformed
        ParentPanel.removeAll();
        ParentPanel.add(Record_Grade_Screen);
        ParentPanel.repaint();
        ParentPanel.revalidate();
    }//GEN-LAST:event_Record_GradeActionPerformed

    private void Record_GradeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Record_GradeMouseExited
        Record_Grade.setBackground(new Color(0,51,102));
    }//GEN-LAST:event_Record_GradeMouseExited

    private void Record_GradeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Record_GradeMouseEntered
        Record_Grade.setBackground(Color.white);
    }//GEN-LAST:event_Record_GradeMouseEntered

    private void Add_ScheduleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Add_ScheduleActionPerformed
        ParentPanel.removeAll();
        ParentPanel.add(Add_Schedule_Screen);
        ParentPanel.repaint();
        ParentPanel.revalidate();
    }//GEN-LAST:event_Add_ScheduleActionPerformed

    private void Add_ScheduleMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Add_ScheduleMouseExited
        Add_Schedule.setBackground(new Color(0,51,102));
    }//GEN-LAST:event_Add_ScheduleMouseExited

    private void Add_ScheduleMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Add_ScheduleMouseEntered
        Add_Schedule.setBackground(Color.white);
    }//GEN-LAST:event_Add_ScheduleMouseEntered

    private void Dimension_TableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Dimension_TableActionPerformed
        ParentPanel.removeAll();
        ParentPanel.add(Dimension_Table_Screen);
        ParentPanel.repaint();
        ParentPanel.revalidate();
    }//GEN-LAST:event_Dimension_TableActionPerformed

    private void Dimension_TableMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Dimension_TableMouseExited
        Dimension_Table.setBackground(new Color(0,51,102));
    }//GEN-LAST:event_Dimension_TableMouseExited

    private void Dimension_TableMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Dimension_TableMouseEntered
        Color defaultColor = new Color(0,0,0);
        Dimension_Table.setBackground(defaultColor);
    }//GEN-LAST:event_Dimension_TableMouseEntered

    private void HomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HomeActionPerformed
        
    private void sy_semActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sy_semActionPerformed
        School_Record.removeAll();
        School_Record.add(sy_semester_screen);
        School_Record.repaint();
        School_Record.revalidate();
    }//GEN-LAST:event_sy_semActionPerformed

    private void collegeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_collegeActionPerformed
        School_Record.removeAll();
        School_Record.add(college_screen);
        School_Record.repaint();
        School_Record.revalidate();
    }//GEN-LAST:event_collegeActionPerformed

    private void courseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_courseActionPerformed
        School_Record.removeAll();
        School_Record.add(course_screen);
        School_Record.repaint();
        School_Record.revalidate();
    }//GEN-LAST:event_courseActionPerformed

    private void studentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentActionPerformed
        School_Record.removeAll();
        School_Record.add(student_screen);
        School_Record.repaint();
        School_Record.revalidate();
    }//GEN-LAST:event_studentActionPerformed

    private void employeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employeeActionPerformed
        School_Record.removeAll();
        School_Record.add(employee_screen);
        School_Record.repaint();
        School_Record.revalidate();
    }//GEN-LAST:event_employeeActionPerformed

    private void subjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subjectActionPerformed
        School_Record.removeAll();
        School_Record.add(subject_screen);
        School_Record.repaint();
        School_Record.revalidate();
    }//GEN-LAST:event_subjectActionPerformed

    private void college_search_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_college_search_buttonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_college_search_buttonActionPerformed

    private void college_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_college_refreshActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_college_refreshActionPerformed

    private void sem_search_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sem_search_buttonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sem_search_buttonActionPerformed

    private void college_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_college_addActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_college_addActionPerformed

    private void schedule_block_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_schedule_block_textActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_schedule_block_textActionPerformed

    private void schedule_type_cmbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_schedule_type_cmbxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_schedule_type_cmbxActionPerformed

    private void schedule_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_schedule_nextActionPerformed
        ParentPanel.removeAll();
        ParentPanel.add(Subject_Schedule_Screen);
        ParentPanel.repaint();
        ParentPanel.revalidate();
    }//GEN-LAST:event_schedule_nextActionPerformed

    private void sy_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sy_clearActionPerformed
       
    }//GEN-LAST:event_sy_clearActionPerformed

    private void sy_search_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sy_search_textActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sy_search_textActionPerformed

    private void college_search_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_college_search_textActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_college_search_textActionPerformed

    private void course_search_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_course_search_textActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_course_search_textActionPerformed

    private void course_search_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_course_search_buttonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_course_search_buttonActionPerformed

    private void course_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_course_addActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_course_addActionPerformed

    private void course_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_course_refreshActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_course_refreshActionPerformed

    private void student_search_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_student_search_textActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_student_search_textActionPerformed

    private void student_search_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_student_search_buttonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_student_search_buttonActionPerformed

    private void student_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_student_addActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_student_addActionPerformed

    private void student_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_student_refreshActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_student_refreshActionPerformed

    private void student_address_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_student_address_textActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_student_address_textActionPerformed

    private void course_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_course_updateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_course_updateActionPerformed

    private void student_gender_cmbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_student_gender_cmbxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_student_gender_cmbxActionPerformed

    private void student_course_code_cmbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_student_course_code_cmbxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_student_course_code_cmbxActionPerformed

    private void student_email_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_student_email_textActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_student_email_textActionPerformed

    private void employee_search_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employee_search_textActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_employee_search_textActionPerformed

    private void employee_search_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employee_search_buttonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_employee_search_buttonActionPerformed

    private void employee_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employee_addActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_employee_addActionPerformed

    private void employee_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employee_refreshActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_employee_refreshActionPerformed

    private void employee_gender_cmbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employee_gender_cmbxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_employee_gender_cmbxActionPerformed

    private void employee_address_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employee_address_textActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_employee_address_textActionPerformed

    private void employee_email_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employee_email_textActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_employee_email_textActionPerformed

    private void student_firstname_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_student_firstname_textActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_student_firstname_textActionPerformed

    private void subject_curriculum_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subject_curriculum_textActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subject_curriculum_textActionPerformed

    private void subject_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subject_refreshActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subject_refreshActionPerformed

    private void subject_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subject_addActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subject_addActionPerformed

    private void subject_search_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subject_search_buttonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subject_search_buttonActionPerformed

    private void subject_search_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subject_search_textActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subject_search_textActionPerformed

    private void subject_desc_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subject_desc_textActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subject_desc_textActionPerformed

    private void subject_units_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subject_units_textActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subject_units_textActionPerformed

    private void schedule_semester_cmbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_schedule_semester_cmbxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_schedule_semester_cmbxActionPerformed

    private void schedule_sy_cmbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_schedule_sy_cmbxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_schedule_sy_cmbxActionPerformed

    private void grade_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grade_nextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_nextActionPerformed

    private void grade_sy_cmbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grade_sy_cmbxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_sy_cmbxActionPerformed

    private void grade_sem_cmbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grade_sem_cmbxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_sem_cmbxActionPerformed

    private void grade_cmbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grade_cmbxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_cmbxActionPerformed

    private void sy_addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sy_addMouseClicked
        int respond = JOptionPane.showConfirmDialog(this, "Do you want to add?", "Confirm", JOptionPane.YES_NO_OPTION);

        if (respond == JOptionPane.YES_OPTION) {
            try {
                conn = ConnectionDB.Connect();
                ps = conn.prepareStatement("INSERT INTO PLM.SCHOOLYEAR (syear) VALUES (?)");
                ps.setString(1, sy_text.getText().trim());
                ps.execute();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            JOptionPane.showMessageDialog(this, "Adding of Record is Successful");
            sy_text.setText("");
            sy_text_selected.setText("");
            sy_search_text.setText("");
            syrefresh();
        } else {
            JOptionPane.showMessageDialog(this, "Adding of Record is Aborted");
        }
    }//GEN-LAST:event_sy_addMouseClicked

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        JTableHeader sy_tableHeader = sy_table.getTableHeader();
        sy_tableHeader.setBackground(new Color(204,255,255)); 
        sy_tableHeader.setForeground(Color.BLACK);
        sy_tableHeader.repaint();
        
        JTableHeader sem_tableHeader = sem_table.getTableHeader();
        sem_tableHeader.setBackground(new Color(204,255,255)); 
        sem_tableHeader.setForeground(Color.BLACK);
        sem_tableHeader.repaint();
        
        JTableHeader college_tableHeader = college_table.getTableHeader();
        college_tableHeader.setBackground(new Color(204,255,255)); 
        college_tableHeader.setForeground(Color.BLACK);
        college_tableHeader.repaint();
        
        JTableHeader course_tableHeader = course_table.getTableHeader();
        course_tableHeader.setBackground(new Color(204,255,255)); 
        course_tableHeader.setForeground(Color.BLACK);
        course_tableHeader.repaint();
        
        JTableHeader student_tableHeader = student_table.getTableHeader();
        student_tableHeader.setBackground(new Color(204,255,255)); 
        student_tableHeader.setForeground(Color.BLACK);
        student_tableHeader.repaint();
        
        JTableHeader employee_tableHeader = employee_table.getTableHeader();
        employee_tableHeader.setBackground(new Color(204,255,255)); 
        employee_tableHeader.setForeground(Color.BLACK);
        employee_tableHeader.repaint();
        
        JTableHeader subject_tableHeader = subject_table.getTableHeader();
        subject_tableHeader.setBackground(new Color(204,255,255)); 
        subject_tableHeader.setForeground(Color.BLACK);
        subject_tableHeader.repaint();
        
        JTableHeader schedule_tableHeader = schedule_table.getTableHeader();
        schedule_tableHeader.setBackground(new Color(204,255,255)); 
        schedule_tableHeader.setForeground(Color.BLACK);
        schedule_tableHeader.repaint();
       
        JTableHeader grade_tableHeader = grade_table.getTableHeader();
        grade_tableHeader.setBackground(new Color(204,255,255)); 
        grade_tableHeader.setForeground(Color.BLACK);
        grade_tableHeader.repaint();
        
        JTableHeader schedule_subject_tableHeader = schedule_subject_table.getTableHeader();
        schedule_subject_tableHeader.setBackground(new Color(204,255,255)); 
        schedule_subject_tableHeader.setForeground(Color.BLACK);
        schedule_subject_tableHeader.repaint();
        
        JTableHeader schedule_employee_tableHeader = schedule_employee_table.getTableHeader();
        schedule_employee_tableHeader.setBackground(new Color(204,255,255)); 
        schedule_employee_tableHeader.setForeground(Color.BLACK);
        schedule_employee_tableHeader.repaint();
        
        JTableHeader grade_student_tablesHeader = grade_student_table.getTableHeader();
        grade_student_tablesHeader.setBackground(new Color(204,255,255)); 
        grade_student_tablesHeader.setForeground(Color.BLACK);
        grade_student_tablesHeader.repaint();
        
        JTableHeader grade_subject_tablesHeader = grade_subject_table.getTableHeader();
        grade_subject_tablesHeader.setBackground(new Color(204,255,255)); 
        grade_subject_tablesHeader.setForeground(Color.BLACK);
        grade_subject_tablesHeader.repaint();
        
        syrefresh();
        semrefresh();
        college_refresh();
        course_refresh();
        student_refresh();
        employee_refresh();
        subject_refresh();
        schedule_refresh();
        grade_refresh();
        schedule_subject_refresh();
        schedule_employee_refresh();
        grade_student_refresh();
        grade_subject_refresh();
        
        college_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        college_table.getColumnModel().getColumn(0).setPreferredWidth(50);
        college_table.getColumnModel().getColumn(1).setPreferredWidth(510);
        college_table.getColumnModel().getColumn(2).setPreferredWidth(120);
        college_table.getColumnModel().getColumn(3).setPreferredWidth(120);
        college_table.getColumnModel().getColumn(4).setPreferredWidth(50);
        
        course_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        course_table.getColumnModel().getColumn(0).setPreferredWidth(100);
        course_table.getColumnModel().getColumn(1).setPreferredWidth(405);
        course_table.getColumnModel().getColumn(2).setPreferredWidth(50);
        course_table.getColumnModel().getColumn(3).setPreferredWidth(120);
        course_table.getColumnModel().getColumn(4).setPreferredWidth(120);
        course_table.getColumnModel().getColumn(5).setPreferredWidth(50);
        
        student_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        student_table.getColumnModel().getColumn(0).setPreferredWidth(71);
        student_table.getColumnModel().getColumn(1).setPreferredWidth(60);
        student_table.getColumnModel().getColumn(2).setPreferredWidth(70);
        student_table.getColumnModel().getColumn(3).setPreferredWidth(120);
        student_table.getColumnModel().getColumn(4).setPreferredWidth(50);
        student_table.getColumnModel().getColumn(5).setPreferredWidth(90);
        student_table.getColumnModel().getColumn(6).setPreferredWidth(75);
        student_table.getColumnModel().getColumn(7).setPreferredWidth(60);
        student_table.getColumnModel().getColumn(8).setPreferredWidth(75);
        student_table.getColumnModel().getColumn(9).setPreferredWidth(45);
        student_table.getColumnModel().getColumn(10).setPreferredWidth(75);
        student_table.getColumnModel().getColumn(11).setPreferredWidth(75);
        
        employee_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        employee_table.getColumnModel().getColumn(0).setPreferredWidth(77);
        employee_table.getColumnModel().getColumn(1).setPreferredWidth(65);
        employee_table.getColumnModel().getColumn(2).setPreferredWidth(70);
        employee_table.getColumnModel().getColumn(3).setPreferredWidth(150);
        employee_table.getColumnModel().getColumn(4).setPreferredWidth(50);
        employee_table.getColumnModel().getColumn(5).setPreferredWidth(80);
        employee_table.getColumnModel().getColumn(6).setPreferredWidth(100);
        employee_table.getColumnModel().getColumn(7).setPreferredWidth(75);
        employee_table.getColumnModel().getColumn(8).setPreferredWidth(45);
        employee_table.getColumnModel().getColumn(9).setPreferredWidth(75);
        employee_table.getColumnModel().getColumn(10).setPreferredWidth(75);
        
        subject_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        subject_table.getColumnModel().getColumn(0).setPreferredWidth(70);
        subject_table.getColumnModel().getColumn(1).setPreferredWidth(425);
        subject_table.getColumnModel().getColumn(2).setPreferredWidth(50);
        subject_table.getColumnModel().getColumn(3).setPreferredWidth(70);
        subject_table.getColumnModel().getColumn(4).setPreferredWidth(50);
        subject_table.getColumnModel().getColumn(5).setPreferredWidth(45);
        subject_table.getColumnModel().getColumn(6).setPreferredWidth(75);
        subject_table.getColumnModel().getColumn(7).setPreferredWidth(75);
        
        // Course -> College Combo Box
        try {
          conn = ConnectionDB.Connect();
          ps = conn.prepareStatement("SELECT * FROM PLM.COLLEGE");
          rs = ps.executeQuery();
            try {
                while (rs.next()){
                  String courseCollegeCode = rs.getString("college_code");
                  course_college_code_cbmx.addItem(courseCollegeCode);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        
        // Student -> Course Combo Box
        try {
          conn = ConnectionDB.Connect();
          ps = conn.prepareStatement("SELECT * FROM PLM.COURSE");
          rs = ps.executeQuery();
            try {
                while (rs.next()){
                  String studentCollegeCode = rs.getString("course_code");
                  student_course_code_cmbx.addItem(studentCollegeCode);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        
        // Subject -> College Combo Box
        try {
          conn = ConnectionDB.Connect();
          ps = conn.prepareStatement("SELECT * FROM PLM.COLLEGE");
          rs = ps.executeQuery();
            try {
                while (rs.next()){
                  String studentCollegeCode = rs.getString("college_code");
                  subject_college_code_cmbx.addItem(studentCollegeCode);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        
        // Schedule -> School Year
        try {
          conn = ConnectionDB.Connect();
          ps = conn.prepareStatement("SELECT * FROM PLM.SCHOOLYEAR");
          rs = ps.executeQuery();
            try {
                while (rs.next()){
                  String schedule_sy = rs.getString("syear");
                  schedule_sy_cmbx.addItem(schedule_sy);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        
        // Schedule -> Semester
        try {
            conn = ConnectionDB.Connect();
            ps = conn.prepareStatement("SELECT * FROM PLM.SEMESTER");
            rs = ps.executeQuery();
            try {
                while (rs.next()) {
                    String schedule_sem = rs.getString("semester");
                    schedule_semester_cmbx.addItem(schedule_sem);
                }
            } catch (Exception e) {
                System.out.println("Error processing result set: " + e);
            } finally {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            }
        } catch (Exception e) {
            System.out.println("Database connection error: " + e);
        }
        
        // Schedule -> College Code
        try {
          conn = ConnectionDB.Connect();
          ps = conn.prepareStatement("SELECT * FROM PLM.COLLEGE");
          rs = ps.executeQuery();
            try {
                while (rs.next()){
                  String schedule_collegeCode = rs.getString("college_code");
                  schedule_college_code_cmbx.addItem(schedule_collegeCode);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
                  
        // Grade -> School Year
        try {
          conn = ConnectionDB.Connect();
          ps = conn.prepareStatement("SELECT * FROM PLM.SCHOOLYEAR");
          rs = ps.executeQuery();
            try {
                while (rs.next()){
                  String grade_sy = rs.getString("syear");
                  grade_sy_cmbx.addItem(grade_sy);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        
        // Grade -> Semester
        try {
          conn = ConnectionDB.Connect();
          ps = conn.prepareStatement("SELECT * FROM PLM.SEMESTER");
          rs = ps.executeQuery();
            try {
                while (rs.next()){
                  String grade_sem = rs.getString("semester");
                  grade_sem_cmbx.addItem(grade_sem);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        

        

    }//GEN-LAST:event_formWindowActivated

    private void sy_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sy_tableMouseClicked
        int row = sy_table.getSelectedRow();
        sy_text.setText(sy_table.getModel().getValueAt(row, 0).toString());
        sy_text_selected.setText(sy_table.getModel().getValueAt(row, 0).toString());
    }//GEN-LAST:event_sy_tableMouseClicked

    private void sy_updateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sy_updateMouseClicked
        int respond = JOptionPane.showConfirmDialog(this, "Do you want to update?","Confirm",JOptionPane.YES_NO_OPTION);
        
        if (respond == JOptionPane.YES_OPTION){
            try{
                conn = ConnectionDB.Connect();
                ps = conn.prepareStatement("UPDATE PLM.SCHOOLYEAR SET syear = '"+sy_text.getText().trim()+ "'WHERE syear = '"+sy_text_selected.getText().trim()+"' ");
                ps.execute();
            }catch(Exception e){
                System.out.println();
            }
            JOptionPane.showMessageDialog(this, "Updating of Record is Successful");   
        }
        else{
            JOptionPane.showMessageDialog(this, "Updating of Record is Aborted");
        }
    }//GEN-LAST:event_sy_updateMouseClicked

    private void sy_deleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sy_deleteMouseClicked
        int respond = JOptionPane.showConfirmDialog(this, "Do you want to delete?","Confirm",JOptionPane.YES_NO_OPTION);
        
        if (respond == JOptionPane.YES_OPTION){
            try{
                conn = ConnectionDB.Connect();
                ps = conn.prepareStatement("DELETE PLM.SCHOOLYEAR WHERE syear = '" + sy_text.getText().trim()+"' ");
                ps.execute();
            }catch(Exception e){
                System.out.println();
            }
            JOptionPane.showMessageDialog(this, "Deleting of Record is Successful");
            syrefresh();
        }
        else{
            JOptionPane.showMessageDialog(this, "Deleting of Record is Aborted");
        }
    }//GEN-LAST:event_sy_deleteMouseClicked

    private void sy_clearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sy_clearMouseClicked
        sy_text.setText("");
        sy_text_selected.setText("");
        sy_search_text.setText("");
        syrefresh();
    }//GEN-LAST:event_sy_clearMouseClicked

    private void sy_search_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sy_search_buttonMouseClicked
        try (Connection conn = ConnectionDB.Connect()) {
            String searchInput = sy_search_text.getText().trim();

            if (searchInput.isEmpty()) {
                System.out.println("Search input is empty.");
                return;
            }
            searchInput = "%" + searchInput + "%";
            String query = "SELECT syear AS \"School Year\" "
                         + "FROM PLM.SCHOOLYEAR "
                         + "WHERE syear LIKE ?";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, searchInput);

                try (ResultSet rs = ps.executeQuery()) {
                    sy_table.setModel(DbUtils.resultSetToTableModel(rs));
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }//GEN-LAST:event_sy_search_buttonMouseClicked

    private void sem_addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sem_addMouseClicked
        int respond = JOptionPane.showConfirmDialog(this, "Do you want to add?", "Confirm", JOptionPane.YES_NO_OPTION);

        if (respond == JOptionPane.YES_OPTION) {
            try {
                conn = ConnectionDB.Connect();
                ps = conn.prepareStatement("INSERT INTO PLM.SEMESTER (semester) VALUES (?)");
                ps.setString(1, sem_text.getText().trim());
                ps.execute();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            JOptionPane.showMessageDialog(this, "Adding of Record is Successful");
            sem_text.setText("");
            sem_text_selected.setText("");
            sem_search_text.setText("");
            semrefresh();
        } else {
            JOptionPane.showMessageDialog(this, "Adding of Record is Aborted");
        }
    }//GEN-LAST:event_sem_addMouseClicked

    private void sem_clearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sem_clearMouseClicked
        sem_text.setText("");
        sem_text_selected.setText("");
        sem_search_text.setText("");
        semrefresh();
    }//GEN-LAST:event_sem_clearMouseClicked

    private void sem_updateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sem_updateMouseClicked
        int respond = JOptionPane.showConfirmDialog(this, "Do you want to update?","Confirm",JOptionPane.YES_NO_OPTION);
        
        if (respond == JOptionPane.YES_OPTION){
            try{
                conn = ConnectionDB.Connect();
                ps = conn.prepareStatement("UPDATE PLM.SEMESTER SET semester = '"+sem_text.getText().trim()+ "'WHERE semester = '"+sem_text_selected.getText().trim()+"' ");
                ps.execute();
            }catch(Exception e){
                System.out.println();
            }
            JOptionPane.showMessageDialog(this, "Updating of Record is Successful");   
        }
        else{
            JOptionPane.showMessageDialog(this, "Updating of Record is Aborted");
        }
    }//GEN-LAST:event_sem_updateMouseClicked

    private void sem_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sem_tableMouseClicked
        int row = sem_table.getSelectedRow();
        sem_text.setText(sem_table.getModel().getValueAt(row, 0).toString());
        sem_text_selected.setText(sem_table.getModel().getValueAt(row, 0).toString());
    }//GEN-LAST:event_sem_tableMouseClicked

    private void sem_deleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sem_deleteMouseClicked
        int respond = JOptionPane.showConfirmDialog(this, "Do you want to delete?","Confirm",JOptionPane.YES_NO_OPTION);
        
        if (respond == JOptionPane.YES_OPTION){
            try{
                conn = ConnectionDB.Connect();
                ps = conn.prepareStatement("DELETE PLM.SEMESTER WHERE semester = '" + sem_text.getText().trim()+"' ");
                ps.execute();
            }catch(Exception e){
                System.out.println();
            }
            JOptionPane.showMessageDialog(this, "Deleting of Record is Successful");
            semrefresh();
        }
        else{
            JOptionPane.showMessageDialog(this, "Deleting of Record is Aborted");
        }
    }//GEN-LAST:event_sem_deleteMouseClicked

    private void sem_search_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sem_search_buttonMouseClicked
        try (Connection conn = ConnectionDB.Connect()) {
            String searchInput = sem_search_text.getText().trim();

            if (searchInput.isEmpty()) {
                System.out.println("Search input is empty.");
                return;
            }

            searchInput = "%" + searchInput + "%";

            String query = "SELECT semester AS \"Semester\" "
                         + "FROM PLM.SEMESTER "
                         + "WHERE semester LIKE ?";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, searchInput);

                try (ResultSet rs = ps.executeQuery()) {
                    sem_table.setModel(DbUtils.resultSetToTableModel(rs));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_sem_search_buttonMouseClicked

    private void sy_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sy_textActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sy_textActionPerformed

    private void college_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_college_updateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_college_updateActionPerformed

    private void employee_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employee_deleteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_employee_deleteActionPerformed

    private void college_addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_college_addMouseClicked
        int respond = JOptionPane.showConfirmDialog(this, "Do you want to add?", "Confirm", JOptionPane.YES_NO_OPTION);

        if (respond == JOptionPane.YES_OPTION) {
            try {
                conn = ConnectionDB.Connect();
                String dateOpened = new SimpleDateFormat("MM/dd/yyyy").format(college_date_opened.getDate());
                String dateClosed = new SimpleDateFormat("MM/dd/yyyy").format(college_date_closed.getDate());
                ps = conn.prepareStatement(
                    "INSERT INTO PLM.COLLEGE "
                    + "(college_code, description, date_opened, date_closed, status) "
                    + "VALUES ("
                    + "'" + college_code_text.getText().trim()
                    + "','" + college_desc_text.getText().trim()
                    + "', '" + dateOpened
                    + "', '" + dateClosed
                    + "', '" + college_status_cmbx.getSelectedItem()
                    + "'   ) "
                );

                ps.execute();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            JOptionPane.showMessageDialog(this, "Adding of Record is Successful");
            college_code_text.setText("");
            college_desc_text.setText("");
            college_date_opened.setDate(null);
            college_date_closed.setDate(null);
            college_refresh();
        } else {
            JOptionPane.showMessageDialog(this, "Adding of Record is Aborted");
        }
    }//GEN-LAST:event_college_addMouseClicked

    private void college_refreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_college_refreshMouseClicked
        college_code_text.setText("");
        college_desc_text.setText("");
        college_date_opened.setDate(null);
        college_date_closed.setDate(null);
        college_search_text.setText("");
        college_refresh();
    }//GEN-LAST:event_college_refreshMouseClicked

    private void college_updateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_college_updateMouseClicked
        int respond = JOptionPane.showConfirmDialog(this, 
            "Do you want to update the record? College Code (" + college_code_text.getText().trim() + ")",
            "Confirm", JOptionPane.YES_NO_OPTION);
        
        if (respond == JOptionPane.YES_OPTION) {
            try {
                conn = ConnectionDB.Connect();

                java.sql.Date dateOpened = new java.sql.Date(college_date_opened.getDate().getTime());
                java.sql.Date dateClosed = new java.sql.Date(college_date_closed.getDate().getTime());

                ps = conn.prepareStatement("UPDATE PLM.COLLEGE SET description = ?, date_opened = ?, date_closed = ?, status = ? WHERE college_code = ?");

                ps.setString(1, college_desc_text.getText().trim());
                ps.setDate(2, dateOpened);
                ps.setDate(3, dateClosed);
                ps.setString(4, college_status_cmbx.getSelectedItem().toString());
                ps.setString(5, college_code_text.getText().trim());

                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "Updating of Record is Successful");

                college_code_text.setText("");
                college_desc_text.setText("");
                college_date_opened.setDate(null);
                college_date_closed.setDate(null);

                college_refresh();

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error updating the record: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Updating of Record is Aborted");
        }
    }//GEN-LAST:event_college_updateMouseClicked

    private void college_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_college_tableMouseClicked
        int row = college_table.getSelectedRow();
        college_code_text.setText(college_table.getModel().getValueAt(row, 0).toString());
        college_desc_text.setText(college_table.getModel().getValueAt(row, 1).toString());
        
        try {
            SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            String dateHolder = college_table.getModel().getValueAt(row, 2).toString();
            college_date_opened.setDate(DateFormat.parse(dateHolder));
        } catch (Exception e) {
            System.out.println(e);
        }
        
        try {
            SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            String dateHolder = college_table.getModel().getValueAt(row, 3).toString();
            college_date_closed.setDate(DateFormat.parse(dateHolder));
        } catch (Exception e) {
            System.out.println(e);
        }
        
        try {
            String strCollegeStatus = college_table.getModel().getValueAt(row, 4).toString();
            ComboBoxModel<String> model = college_status_cmbx.getModel();
            int index = -1;
            for (int i = 0; i < model.getSize(); i++) {
                if (model.getElementAt(i).equalsIgnoreCase(strCollegeStatus)) {
                    index = i;
                    break;
                }
            }
            if (index != -1) 
                college_status_cmbx.setSelectedIndex(index);
            
        } catch (Exception e) {
            System.out.println("Error setting combo box value: " + e);
        }

    }//GEN-LAST:event_college_tableMouseClicked

    private void college_deleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_college_deleteMouseClicked
        int respond = JOptionPane.showConfirmDialog(this, 
                "Do you want to delete the record? College Code (" + college_code_text.getText().trim() + ")",
                "Confirm",JOptionPane.YES_NO_OPTION);
        
        if (respond == JOptionPane.YES_OPTION){
            try{
                conn = ConnectionDB.Connect();
                ps = conn.prepareStatement("DELETE PLM.COLLEGE WHERE college_code = '" + college_code_text.getText().trim()+"' ");
                ps.execute();
            }catch(Exception e){
                System.out.println();
            }
            JOptionPane.showMessageDialog(this, "Deleting of Record is Successful");
            college_code_text.setText("");
            college_desc_text.setText("");
            college_date_opened.setDate(null);
            college_date_closed.setDate(null);
        }
        else{
            JOptionPane.showMessageDialog(this, "Deleting of Record is Aborted");
        }
    }//GEN-LAST:event_college_deleteMouseClicked

    private void college_search_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_college_search_buttonMouseClicked
        try (Connection conn = ConnectionDB.Connect()) {
            String searchInput = college_search_text.getText().trim();

            if (searchInput.isEmpty()) {
                System.out.println("Search input is empty.");
                return;
            }

            searchInput = "%" + searchInput + "%";

            String query = "SELECT college_code AS \"College\", "
                         + "description AS \"Description\", "
                         + "date_opened AS \"Date Opened\", "
                         + "date_closed AS \"Date Closed\", "
                         + "status AS \"Status\" "
                         + "FROM PLM.COLLEGE "
                         + "WHERE college_code LIKE ? "
                         + "OR description LIKE ? "
                         + "OR date_opened LIKE ? "
                         + "OR date_closed LIKE ? "
                         + "OR status LIKE ?";

            try (PreparedStatement ps = conn.prepareStatement(query)) {

                ps.setString(1, searchInput);
                ps.setString(2, searchInput);
                ps.setString(3, searchInput);
                ps.setString(4, searchInput);
                ps.setString(5, searchInput);

                try (ResultSet rs = ps.executeQuery()) {
                    college_table.setModel(DbUtils.resultSetToTableModel(rs));
                }
            }
            
            college_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            college_table.getColumnModel().getColumn(0).setPreferredWidth(50);
            college_table.getColumnModel().getColumn(1).setPreferredWidth(510);
            college_table.getColumnModel().getColumn(2).setPreferredWidth(120);
            college_table.getColumnModel().getColumn(3).setPreferredWidth(120);
            college_table.getColumnModel().getColumn(4).setPreferredWidth(50);
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }//GEN-LAST:event_college_search_buttonMouseClicked

    private void course_addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_course_addMouseClicked
        int respond = JOptionPane.showConfirmDialog(this, "Do you want to add?", "Confirm", JOptionPane.YES_NO_OPTION);

        if (respond == JOptionPane.YES_OPTION) {
            try {
                conn = ConnectionDB.Connect();
                String dateOpened = new SimpleDateFormat("MM/dd/yyyy").format(course_date_opened.getDate());
                String dateClosed = new SimpleDateFormat("MM/dd/yyyy").format(course_date_closed.getDate());
                ps = conn.prepareStatement(
                    "INSERT INTO PLM.COURSE "
                    + "(course_code, description, college_code, date_opened, date_closed, status) "
                    + "VALUES ("
                    + "'" + course_code_text.getText().trim()
                    + "','" + course_desc_text.getText().trim()
                    + "', '" + course_college_code_cbmx.getSelectedItem()
                    + "', '" + dateOpened
                    + "', '" + dateClosed
                    + "', '" + course_status_cmbx.getSelectedItem()
                    + "'   ) "
                );

                ps.execute();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            JOptionPane.showMessageDialog(this, "Adding of Record is Successful");
            course_code_text.setText("");
            course_desc_text.setText("");
            course_date_opened.setDate(null);
            course_date_closed.setDate(null);
            course_refresh();
        } else {
            JOptionPane.showMessageDialog(this, "Adding of Record is Aborted");
        }
    }//GEN-LAST:event_course_addMouseClicked

    private void course_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_course_tableMouseClicked
        int row = course_table.getSelectedRow();
        course_code_text.setText(course_table.getModel().getValueAt(row, 0).toString());
        course_desc_text.setText(course_table.getModel().getValueAt(row, 1).toString());
        
        try {
            String strCollegeCode = course_table.getModel().getValueAt(row, 2).toString();
            ComboBoxModel<String> model = course_college_code_cbmx.getModel();
            int index = -1;
            for (int i = 0; i < model.getSize(); i++) {
                if (model.getElementAt(i).equalsIgnoreCase(strCollegeCode)) {
                    index = i;
                    break;
                }
            }
            if (index != -1) 
                course_college_code_cbmx.setSelectedIndex(index);
            
        } catch (Exception e) {
            System.out.println("Error setting combo box value: " + e);
        }
        
        try {
            SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            String dateHolder = course_table.getModel().getValueAt(row, 3).toString();
            course_date_opened.setDate(DateFormat.parse(dateHolder));
        } catch (Exception e) {
            System.out.println(e);
        }
        
        try {
            SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            String dateHolder = course_table.getModel().getValueAt(row, 4).toString();
            course_date_closed.setDate(DateFormat.parse(dateHolder));
        } catch (Exception e) {
            System.out.println(e);
        }
        
        try {
            String strCollegeStatus = course_table.getModel().getValueAt(row, 5).toString();
            ComboBoxModel<String> model = course_status_cmbx.getModel();
            int index = -1;
            for (int i = 0; i < model.getSize(); i++) {
                if (model.getElementAt(i).equalsIgnoreCase(strCollegeStatus)) {
                    index = i;
                    break;
                }
            }
            if (index != -1) 
                course_status_cmbx.setSelectedIndex(index);
            
        } catch (Exception e) {
            System.out.println("Error setting combo box value: " + e);
        }
    }//GEN-LAST:event_course_tableMouseClicked

    private void course_updateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_course_updateMouseClicked
        int respond = JOptionPane.showConfirmDialog(this, 
            "Do you want to update the record? Course Code (" + course_code_text.getText().trim() + ")",
            "Confirm", JOptionPane.YES_NO_OPTION);
        
        if (respond == JOptionPane.YES_OPTION) {
            try {
                conn = ConnectionDB.Connect();

                java.sql.Date dateOpened = new java.sql.Date(course_date_opened.getDate().getTime());
                java.sql.Date dateClosed = new java.sql.Date(course_date_closed.getDate().getTime());

                ps = conn.prepareStatement("UPDATE PLM.COURSE SET description = ?, college_code = ?, date_opened = ?, date_closed = ?, status = ? WHERE course_code = ?");

                ps.setString(1, course_desc_text.getText().trim());
                ps.setString(2, course_college_code_cbmx.getSelectedItem().toString());
                ps.setDate(3, dateOpened);
                ps.setDate(4, dateClosed);
                ps.setString(5, course_status_cmbx.getSelectedItem().toString());
                ps.setString(6, course_code_text.getText().trim());

                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "Updating of Record is Successful");

                course_code_text.setText("");
                course_desc_text.setText("");
                course_date_opened.setDate(null);
                course_date_closed.setDate(null);
                course_refresh();

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error updating the record: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Updating of Record is Aborted");
        }
    }//GEN-LAST:event_course_updateMouseClicked

    private void course_deleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_course_deleteMouseClicked
        int respond = JOptionPane.showConfirmDialog(this, 
                "Do you want to delete the record? Course Code (" + course_code_text.getText().trim() + ")",
                "Confirm",JOptionPane.YES_NO_OPTION);
        
        if (respond == JOptionPane.YES_OPTION){
            try{
                conn = ConnectionDB.Connect();
                ps = conn.prepareStatement("DELETE PLM.COURSE WHERE course_code = '" + course_code_text.getText().trim()+"' ");
                ps.execute();
            }catch(Exception e){
                System.out.println();
            }
            JOptionPane.showMessageDialog(this, "Deleting of Record is Successful");
            course_code_text.setText("");
            course_desc_text.setText("");
            course_date_opened.setDate(null);
            course_date_closed.setDate(null);
        }
        else{
            JOptionPane.showMessageDialog(this, "Deleting of Record is Aborted");
        }
    }//GEN-LAST:event_course_deleteMouseClicked

    private void student_addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_student_addMouseClicked
        int respond = JOptionPane.showConfirmDialog(this, "Do you want to add?", "Confirm", JOptionPane.YES_NO_OPTION);

        if (respond == JOptionPane.YES_OPTION) {
            try {
                conn = ConnectionDB.Connect();
                String birthday = new SimpleDateFormat("MM/dd/yyyy").format(student_bday.getDate());
                String dateStarted = new SimpleDateFormat("MM/dd/yyyy").format(student_date_started.getDate());
                String dateGraduated = new SimpleDateFormat("MM/dd/yyyy").format(student_date_graduated.getDate());
                ps = conn.prepareStatement(
                    "INSERT INTO PLM.STUDENT "
                    + "(student_no, lastname, firstname, email, gender, course_code, cp_num, address, bday, status, date_started, date_graduated) "
                    + "VALUES ("
                    + "'" + student_no_text.getText().trim()
                    + "','" + student_lastname_text.getText().trim()
                    + "','" + student_firstname_text.getText().trim()
                    + "','" + student_email_text.getText().trim()
                    + "', '" + student_gender_cmbx.getSelectedItem()
                    + "', '" + student_course_code_cmbx.getSelectedItem()
                    + "','" + student_phone_text.getText().trim()
                    + "','" + student_address_text.getText().trim()
                    + "', '" + birthday
                    + "', '" + student_status_cmbx.getSelectedItem()
                    + "', '" + dateStarted
                    + "', '" + dateGraduated
                    + "'   ) "
                );

                ps.execute();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            JOptionPane.showMessageDialog(this, "Adding of Record is Successful");
            student_no_text.setText("");
            student_lastname_text.setText("");
            student_firstname_text.setText("");
            student_email_text.setText("");
            student_phone_text.setText("");
            student_address_text.setText("");
            student_bday.setDate(null);
            student_date_started.setDate(null);
            student_date_graduated.setDate(null);
            student_refresh();
        } else {
            JOptionPane.showMessageDialog(this, "Adding of Record is Aborted");
        }
    }//GEN-LAST:event_student_addMouseClicked

    private void course_refreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_course_refreshMouseClicked
        course_code_text.setText("");
        course_desc_text.setText("");
        course_date_opened.setDate(null);
        course_date_closed.setDate(null);
        course_search_text.setText("");
        course_refresh();
    }//GEN-LAST:event_course_refreshMouseClicked

    private void student_refreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_student_refreshMouseClicked
        student_no_text.setText("");
        student_lastname_text.setText("");
        student_firstname_text.setText("");
        student_email_text.setText("");
        student_phone_text.setText("");
        student_address_text.setText("");
        student_bday.setDate(null);
        student_date_started.setDate(null);
        student_date_graduated.setDate(null);
        student_search_text.setText("");
        student_refresh();
    }//GEN-LAST:event_student_refreshMouseClicked

    private void student_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_student_tableMouseClicked
        int row = student_table.getSelectedRow();
        student_no_text.setText(student_table.getModel().getValueAt(row, 0).toString());
        student_lastname_text.setText(student_table.getModel().getValueAt(row, 1).toString());
        student_firstname_text.setText(student_table.getModel().getValueAt(row, 2).toString());
        student_email_text.setText(student_table.getModel().getValueAt(row, 3).toString());
        
        try {
            String strStudentGender = student_table.getModel().getValueAt(row, 4).toString();
            ComboBoxModel<String> model = student_gender_cmbx.getModel();
            int index = -1;
            for (int i = 0; i < model.getSize(); i++) {
                if (model.getElementAt(i).equalsIgnoreCase(strStudentGender)) {
                    index = i;
                    break;
                }
            }
            if (index != -1) 
                student_gender_cmbx.setSelectedIndex(index);
            
        } catch (Exception e) {
            System.out.println("Error setting combo box value: " + e);
        }
        
        try {
            String strStudentCourse = student_table.getModel().getValueAt(row, 5).toString();
            ComboBoxModel<String> model = student_course_code_cmbx.getModel();
            int index = -1;
            for (int i = 0; i < model.getSize(); i++) {
                if (model.getElementAt(i).equalsIgnoreCase(strStudentCourse)) {
                    index = i;
                    break;
                }
            }
            if (index != -1) 
                student_course_code_cmbx.setSelectedIndex(index);
            
        } catch (Exception e) {
            System.out.println("Error setting combo box value: " + e);
        }
        
        student_phone_text.setText(student_table.getModel().getValueAt(row, 6).toString());
        student_address_text.setText(student_table.getModel().getValueAt(row, 7).toString());
        
        try {
            SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            String dateHolder = student_table.getModel().getValueAt(row, 8).toString();
            student_bday.setDate(DateFormat.parse(dateHolder));
        } catch (Exception e) {
            System.out.println(e);
        }
        
        try {
            String strCollegeStatus = student_table.getModel().getValueAt(row, 9).toString();
            ComboBoxModel<String> model = student_status_cmbx.getModel();
            int index = -1;
            for (int i = 0; i < model.getSize(); i++) {
                if (model.getElementAt(i).equalsIgnoreCase(strCollegeStatus)) {
                    index = i;
                    break;
                }
            }
            if (index != -1) 
                student_status_cmbx.setSelectedIndex(index);
            
        } catch (Exception e) {
            System.out.println("Error setting combo box value: " + e);
        }
        
        try {
            SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            String dateHolder = student_table.getModel().getValueAt(row, 10).toString();
            student_date_started.setDate(DateFormat.parse(dateHolder));
        } catch (Exception e) {
            System.out.println(e);
        }
        
        try {
            SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            String dateHolder = student_table.getModel().getValueAt(row, 11).toString();
            student_date_graduated.setDate(DateFormat.parse(dateHolder));
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }//GEN-LAST:event_student_tableMouseClicked

    private void student_updateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_student_updateMouseClicked
        int respond = JOptionPane.showConfirmDialog(this, 
            "Do you want to update the record? Student No. (" + student_no_text.getText().trim() + ")",
            "Confirm", JOptionPane.YES_NO_OPTION);
        
        if (respond == JOptionPane.YES_OPTION) {
            try {
                conn = ConnectionDB.Connect();
                
                java.sql.Date bday = new java.sql.Date(student_bday.getDate().getTime());
                java.sql.Date dateStarted = new java.sql.Date(student_date_started.getDate().getTime());
                java.sql.Date dateGraduated = new java.sql.Date(student_date_graduated.getDate().getTime());

                ps = conn.prepareStatement("UPDATE PLM.STUDENT "
                    + "SET lastname = ?, firstname = ?, email = ?, gender = ?, "
                    + "course_code = ?, cp_num = ?, address = ?, bday = ?, status = ?, "
                    + "date_started = ?, date_graduated = ? WHERE student_no = ?");

                ps.setString(1, student_lastname_text.getText().trim());
                ps.setString(2, student_firstname_text.getText().trim());
                ps.setString(3, student_email_text.getText().trim());
                ps.setString(4, student_gender_cmbx.getSelectedItem().toString());
                ps.setString(5, student_course_code_cmbx.getSelectedItem().toString());
                ps.setString(6, student_phone_text.getText().trim());
                ps.setString(7, student_address_text.getText().trim());
                ps.setDate(8, bday);
                ps.setString(9, student_status_cmbx.getSelectedItem().toString());
                ps.setDate(10, dateStarted);
                ps.setDate(11, dateGraduated);
                ps.setString(12, student_no_text.getText().trim());

                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "Updating of Record is Successful");

                student_no_text.setText("");
                student_lastname_text.setText("");
                student_firstname_text.setText("");
                student_email_text.setText("");
                student_phone_text.setText("");
                student_address_text.setText("");
                student_bday.setDate(null);
                student_date_started.setDate(null);
                student_date_graduated.setDate(null);
                student_refresh();

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error updating the record: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Updating of Record is Aborted");
        }
    }//GEN-LAST:event_student_updateMouseClicked

    private void student_deleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_student_deleteMouseClicked
        int respond = JOptionPane.showConfirmDialog(this, 
                "Do you want to delete the record? Student No. (" + student_no_text.getText().trim() + ")",
                "Confirm",JOptionPane.YES_NO_OPTION);
        
        if (respond == JOptionPane.YES_OPTION){
            try{
                conn = ConnectionDB.Connect();
                ps = conn.prepareStatement("DELETE PLM.STUDENT WHERE student_no = '" + student_no_text.getText().trim()+"' ");
                ps.execute();
            }catch(Exception e){
                System.out.println();
            }
            JOptionPane.showMessageDialog(this, "Deleting of Record is Successful");
            student_no_text.setText("");
            student_lastname_text.setText("");
            student_firstname_text.setText("");
            student_email_text.setText("");
            student_phone_text.setText("");
            student_address_text.setText("");
            student_bday.setDate(null);
            student_date_started.setDate(null);
            student_date_graduated.setDate(null);
            student_refresh();
        }
        else{
            JOptionPane.showMessageDialog(this, "Deleting of Record is Aborted");
        }
    }//GEN-LAST:event_student_deleteMouseClicked

    private void employee_addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_employee_addMouseClicked
        int respond = JOptionPane.showConfirmDialog(this, "Do you want to add?", "Confirm", JOptionPane.YES_NO_OPTION);

        if (respond == JOptionPane.YES_OPTION) {
            try {
                conn = ConnectionDB.Connect();
                String birthday = new SimpleDateFormat("MM/dd/yyyy").format(employee_bday.getDate());
                String dateStarted = new SimpleDateFormat("MM/dd/yyyy").format(employee_date_started.getDate());
                String dateResigned = new SimpleDateFormat("MM/dd/yyyy").format(employee_date_resigned.getDate());
                ps = conn.prepareStatement(
                    "INSERT INTO PLM.EMPLOYEE "
                    + "(employee_id, lastname, firstname, email, gender, cp_num, address, bday, status, date_started, date_resigned) "
                    + "VALUES ("
                    + "'" + employee_id_text.getText().trim()
                    + "','" + employee_lastname_text.getText().trim()
                    + "','" + employee_firstname_text.getText().trim()
                    + "','" + employee_email_text.getText().trim()
                    + "', '" + employee_gender_cmbx.getSelectedItem()
                    + "','" + employee_phone_text.getText().trim()
                    + "','" + employee_address_text.getText().trim()
                    + "', '" + birthday
                    + "', '" + employee_status_cmbx.getSelectedItem()
                    + "', '" + dateStarted
                    + "', '" + dateResigned
                    + "'   ) "
                );

                ps.execute();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            JOptionPane.showMessageDialog(this, "Adding of Record is Successful");
            employee_id_text.setText("");
            employee_lastname_text.setText("");
            employee_firstname_text.setText("");
            employee_email_text.setText("");
            employee_phone_text.setText("");
            employee_address_text.setText("");
            employee_bday.setDate(null);
            employee_date_started.setDate(null);
            employee_date_resigned.setDate(null);
            employee_refresh();
        } else {
            JOptionPane.showMessageDialog(this, "Adding of Record is Aborted");
        }
    }//GEN-LAST:event_employee_addMouseClicked

    private void employee_refreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_employee_refreshMouseClicked
        employee_id_text.setText("");
        employee_lastname_text.setText("");
        employee_firstname_text.setText("");
        employee_email_text.setText("");
        employee_phone_text.setText("");
        employee_address_text.setText("");
        employee_bday.setDate(null);
        employee_date_started.setDate(null);
        employee_date_resigned.setDate(null);
        employee_search_text.setText("");
        employee_refresh();
    }//GEN-LAST:event_employee_refreshMouseClicked

    private void employee_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_employee_tableMouseClicked
        int row = employee_table.getSelectedRow();
        employee_id_text.setText(employee_table.getModel().getValueAt(row, 0).toString());
        employee_lastname_text.setText(employee_table.getModel().getValueAt(row, 1).toString());
        employee_firstname_text.setText(employee_table.getModel().getValueAt(row, 2).toString());
        employee_email_text.setText(employee_table.getModel().getValueAt(row, 3).toString());
        
        try {
            String strEmployeeGender = employee_table.getModel().getValueAt(row, 4).toString();
            ComboBoxModel<String> model = employee_gender_cmbx.getModel();
            int index = -1;
            for (int i = 0; i < model.getSize(); i++) {
                if (model.getElementAt(i).equalsIgnoreCase(strEmployeeGender)) {
                    index = i;
                    break;
                }
            }
            if (index != -1) 
                employee_gender_cmbx.setSelectedIndex(index);
            
        } catch (Exception e) {
            System.out.println("Error setting combo box value: " + e);
        }
        
        employee_phone_text.setText(employee_table.getModel().getValueAt(row, 5).toString());
        employee_address_text.setText(employee_table.getModel().getValueAt(row, 6).toString());
        
        try {
            SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            String dateHolder = employee_table.getModel().getValueAt(row, 7).toString();
            employee_bday.setDate(DateFormat.parse(dateHolder));
        } catch (Exception e) {
            System.out.println(e);
        }
        
        try {
            String strCollegeStatus = employee_table.getModel().getValueAt(row, 8).toString();
            ComboBoxModel<String> model = employee_status_cmbx.getModel();
            int index = -1;
            for (int i = 0; i < model.getSize(); i++) {
                if (model.getElementAt(i).equalsIgnoreCase(strCollegeStatus)) {
                    index = i;
                    break;
                }
            }
            if (index != -1) 
                employee_status_cmbx.setSelectedIndex(index);
            
        } catch (Exception e) {
            System.out.println("Error setting combo box value: " + e);
        }
        
        try {
            SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            String dateHolder = employee_table.getModel().getValueAt(row, 9).toString();
            employee_date_started.setDate(DateFormat.parse(dateHolder));
        } catch (Exception e) {
            System.out.println(e);
        }
        
        try {
            SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            String dateHolder = employee_table.getModel().getValueAt(row, 10).toString();
            employee_date_resigned.setDate(DateFormat.parse(dateHolder));
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_employee_tableMouseClicked

    private void employee_updateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_employee_updateMouseClicked
        int respond = JOptionPane.showConfirmDialog(this, 
            "Do you want to update the record? Employee ID (" + employee_id_text.getText().trim() + ")",
            "Confirm", JOptionPane.YES_NO_OPTION);
        
        if (respond == JOptionPane.YES_OPTION) {
            try {
                conn = ConnectionDB.Connect();
                
                java.sql.Date bday = new java.sql.Date(employee_bday.getDate().getTime());
                java.sql.Date dateStarted = new java.sql.Date(employee_date_started.getDate().getTime());
                java.sql.Date dateGraduated = new java.sql.Date(employee_date_resigned.getDate().getTime());

                ps = conn.prepareStatement("UPDATE PLM.EMPLOYEE "
                    + "SET lastname = ?, firstname = ?, email = ?, gender = ?, "
                    + "cp_num = ?, address = ?, bday = ?, status = ?, "
                    + "date_started = ?, date_resigned = ? WHERE employee_id = ?");

                ps.setString(1, employee_lastname_text.getText().trim());
                ps.setString(2, employee_firstname_text.getText().trim());
                ps.setString(3, employee_email_text.getText().trim());
                ps.setString(4, employee_gender_cmbx.getSelectedItem().toString());
                ps.setString(5, employee_phone_text.getText().trim());
                ps.setString(6, employee_address_text.getText().trim());
                ps.setDate(7, bday);
                ps.setString(8, employee_status_cmbx.getSelectedItem().toString());
                ps.setDate(9, dateStarted);
                ps.setDate(10, dateGraduated);
                ps.setString(11, employee_id_text.getText().trim());

                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "Updating of Record is Successful");

                employee_id_text.setText("");
                employee_lastname_text.setText("");
                employee_firstname_text.setText("");
                employee_email_text.setText("");
                employee_phone_text.setText("");
                employee_address_text.setText("");
                employee_bday.setDate(null);
                employee_date_started.setDate(null);
                employee_date_resigned.setDate(null);
                employee_refresh();

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error updating the record: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Updating of Record is Aborted");
        }
    }//GEN-LAST:event_employee_updateMouseClicked

    private void employee_deleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_employee_deleteMouseClicked
        int respond = JOptionPane.showConfirmDialog(this, 
                "Do you want to delete the record? Employee ID (" + employee_id_text.getText().trim() + ")",
                "Confirm",JOptionPane.YES_NO_OPTION);
        
        if (respond == JOptionPane.YES_OPTION){
            try{
                conn = ConnectionDB.Connect();
                ps = conn.prepareStatement("DELETE PLM.EMPLOYEE WHERE employee_id = '" + employee_id_text.getText().trim()+"' ");
                ps.execute();
            }catch(Exception e){
                System.out.println();
            }
            JOptionPane.showMessageDialog(this, "Deleting of Record is Successful");
            employee_id_text.setText("");
            employee_lastname_text.setText("");
            employee_firstname_text.setText("");
            employee_email_text.setText("");
            employee_phone_text.setText("");
            employee_address_text.setText("");
            employee_bday.setDate(null);
            employee_date_started.setDate(null);
            employee_date_resigned.setDate(null);
            employee_refresh();
        }
        else{
            JOptionPane.showMessageDialog(this, "Deleting of Record is Aborted");
        }
    }//GEN-LAST:event_employee_deleteMouseClicked

    private void subject_addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_subject_addMouseClicked
        int respond = JOptionPane.showConfirmDialog(this, "Do you want to add?", "Confirm", JOptionPane.YES_NO_OPTION);

        if (respond == JOptionPane.YES_OPTION) {
            try {
                conn = ConnectionDB.Connect();
                String dateOpened = new SimpleDateFormat("MM/dd/yyyy").format(subject_date_opened.getDate());
                String dateClosed = new SimpleDateFormat("MM/dd/yyyy").format(subject_date_closed.getDate());
                ps = conn.prepareStatement(
                    "INSERT INTO PLM.SUBJECT "
                    + "(subject_code, description, units, curriculum, college_code, status, date_opened, date_closed) "
                    + "VALUES ("
                    + "'" + subject_code_text.getText().trim()
                    + "','" + subject_desc_text.getText().trim()
                    + "','" + Integer.parseInt(subject_units_text.getText().trim())
                    + "','" + subject_curriculum_text.getText().trim()
                    + "', '" + subject_college_code_cmbx.getSelectedItem()
                    + "','" + subject_status_cmbx.getSelectedItem()
                    + "', '" + dateOpened
                    + "', '" + dateClosed
                    + "'   ) "
                );

                ps.execute();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            JOptionPane.showMessageDialog(this, "Adding of Record is Successful");
            subject_code_text.setText("");
            subject_desc_text.setText("");
            subject_units_text.setText("");
            subject_curriculum_text.setText("");
            subject_date_opened.setDate(null);
            subject_date_closed.setDate(null);
            subject_refresh();
        } else {
            JOptionPane.showMessageDialog(this, "Adding of Record is Aborted");
        }
    }//GEN-LAST:event_subject_addMouseClicked

    private void subject_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_subject_tableMouseClicked
        int row = subject_table.getSelectedRow();
        subject_code_text.setText(subject_table.getModel().getValueAt(row, 0).toString());
        subject_desc_text.setText(subject_table.getModel().getValueAt(row, 1).toString());
        subject_units_text.setText(subject_table.getModel().getValueAt(row, 2).toString());
        subject_curriculum_text.setText(subject_table.getModel().getValueAt(row, 3).toString());
        
        try {
            String strSubjectCollege = subject_table.getModel().getValueAt(row, 4).toString();
            ComboBoxModel<String> model = subject_college_code_cmbx.getModel();
            int index = -1;
            for (int i = 0; i < model.getSize(); i++) {
                if (model.getElementAt(i).equalsIgnoreCase(strSubjectCollege)) {
                    index = i;
                    break;
                }
            }
            if (index != -1) 
                subject_college_code_cmbx.setSelectedIndex(index);
            
        } catch (Exception e) {
            System.out.println("Error setting combo box value: " + e);
        }
        
        try {
            String strSubjectStatus = subject_table.getModel().getValueAt(row, 5).toString();
            ComboBoxModel<String> model = subject_status_cmbx.getModel();
            int index = -1;
            for (int i = 0; i < model.getSize(); i++) {
                if (model.getElementAt(i).equalsIgnoreCase(strSubjectStatus)) {
                    index = i;
                    break;
                }
            }
            if (index != -1) 
                subject_status_cmbx.setSelectedIndex(index);
            
        } catch (Exception e) {
            System.out.println("Error setting combo box value: " + e);
        }
        
        try {
            SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            String dateHolder = subject_table.getModel().getValueAt(row, 6).toString();
            subject_date_opened.setDate(DateFormat.parse(dateHolder));
        } catch (Exception e) {
            System.out.println(e);
        }
        
        try {
            SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            String dateHolder = subject_table.getModel().getValueAt(row, 7).toString();
            subject_date_closed.setDate(DateFormat.parse(dateHolder));
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_subject_tableMouseClicked

    private void subject_refreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_subject_refreshMouseClicked
        subject_code_text.setText("");
        subject_desc_text.setText("");
        subject_units_text.setText("");
        subject_curriculum_text.setText("");
        subject_date_opened.setDate(null);
        subject_date_closed.setDate(null);
        subject_search_text.setText("");
        subject_refresh();
    }//GEN-LAST:event_subject_refreshMouseClicked

    private void subject_updateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_subject_updateMouseClicked
        int respond = JOptionPane.showConfirmDialog(this, 
            "Do you want to update the record? Subject Code (" + subject_code_text.getText().trim() + ")",
            "Confirm", JOptionPane.YES_NO_OPTION);
        
        if (respond == JOptionPane.YES_OPTION) {
            try {
                conn = ConnectionDB.Connect();
                
                java.sql.Date dateOpened = new java.sql.Date(subject_date_opened.getDate().getTime());
                java.sql.Date dateClosed = new java.sql.Date(subject_date_closed.getDate().getTime());

                ps = conn.prepareStatement("UPDATE PLM.SUBJECT "
                    + "SET description = ?, units = ?, curriculum = ?, college_code = ?, "
                    + "status = ?, date_opened = ?, date_closed = ? WHERE subject_code = ?");

                ps.setString(1, subject_desc_text.getText().trim());
                ps.setInt(2, Integer.parseInt(subject_units_text.getText().trim()));
                ps.setString(3, subject_curriculum_text.getText().trim());
                ps.setString(4, subject_college_code_cmbx.getSelectedItem().toString());
                ps.setString(5, subject_status_cmbx.getSelectedItem().toString());
                ps.setDate(6, dateOpened);
                ps.setDate(7, dateClosed);
                ps.setString(8, subject_code_text.getText().trim());
                
                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "Updating of Record is Successful");

                subject_code_text.setText("");
                subject_desc_text.setText("");
                subject_units_text.setText("");
                subject_curriculum_text.setText("");
                subject_date_opened.setDate(null);
                subject_date_closed.setDate(null);
                subject_refresh();

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error updating the record: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Updating of Record is Aborted");
        }
    }//GEN-LAST:event_subject_updateMouseClicked

    private void subject_deleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_subject_deleteMouseClicked
        int respond = JOptionPane.showConfirmDialog(this, 
                "Do you want to delete the record? Subject Code (" + subject_code_text.getText().trim() + ")",
                "Confirm",JOptionPane.YES_NO_OPTION);
        
        if (respond == JOptionPane.YES_OPTION){
            try{
                conn = ConnectionDB.Connect();
                ps = conn.prepareStatement("DELETE PLM.SUBJECT WHERE subject_code = '" + subject_code_text.getText().trim()+"' ");
                ps.execute();
            }catch(Exception e){
                System.out.println();
            }
            JOptionPane.showMessageDialog(this, "Deleting of Record is Successful");
            subject_code_text.setText("");
            subject_desc_text.setText("");
            subject_units_text.setText("");
            subject_curriculum_text.setText("");
            subject_date_opened.setDate(null);
            subject_date_closed.setDate(null);
            subject_refresh();
        }
        else{
            JOptionPane.showMessageDialog(this, "Deleting of Record is Aborted");
        }
    }//GEN-LAST:event_subject_deleteMouseClicked

    private void schedule_refreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_schedule_refreshMouseClicked
        schedule_block_text.setText("");
        schedule_time_text.setText("");
        schedule_room_text.setText("");
        schedule_subject_search_text.setText("");
        schedule_subject_code_text.setText("");
        schedule_employee_search_text.setText("");
        schedule_employeeID_text.setText("");
        schedule_employeeName_text.setText("");
        schedule_refresh();
        schedule_subject_refresh();
        schedule_employee_refresh();
    }//GEN-LAST:event_schedule_refreshMouseClicked

    private void schedule_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_schedule_refreshActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_schedule_refreshActionPerformed

    private void schedule_addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_schedule_addMouseClicked
        int respond = JOptionPane.showConfirmDialog(this, "Do you want to add?", "Confirm", JOptionPane.YES_NO_OPTION);

        if (respond == JOptionPane.YES_OPTION) {
            try {
                conn = ConnectionDB.Connect();
                ps = conn.prepareStatement(
                    "INSERT INTO PLM.SUBJECT_SCHEDULE "
                    + "(syear, semester, college_code, block_no, subject_code, day, time, room, type, sequence_no, employee_id) "
                    + "VALUES ("
                    + "'" + schedule_sy_cmbx.getSelectedItem() + "', "
                    + "'" + schedule_semester_cmbx.getSelectedItem() + "', "
                    + "'" + schedule_college_code_cmbx.getSelectedItem() + "', "
                    + "'" + schedule_block_text.getText().trim() + "', "
                    + "'" + schedule_subject_code_text.getText().trim() + "', "
                    + "'" + schedule_day_cmbx.getSelectedItem() + "', "
                    + "'" + schedule_time_text.getText().trim() + "', "
                    + "'" + schedule_room_text.getText().trim() + "', "
                    + "'" + schedule_type_cmbx.getSelectedItem() + "', "
                    + "'" + schedule_sequence_cmbx.getSelectedItem() + "', "
                    + "'" + schedule_employeeID_text.getText().trim() + "' "
                    + ")"
                );
                            
                ps.execute();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            JOptionPane.showMessageDialog(this, "Adding of Record is Successful");
            schedule_block_text.setText("");
            schedule_time_text.setText("");
            schedule_room_text.setText("");
            schedule_subject_search_text.setText("");
            schedule_subject_code_text.setText("");
            schedule_employee_search_text.setText("");
            schedule_employeeID_text.setText("");
            schedule_employeeName_text.setText("");
            schedule_refresh();
            schedule_subject_refresh();
            schedule_employee_refresh();
        } else {
            JOptionPane.showMessageDialog(this, "Adding of Record is Aborted");
        }
    }//GEN-LAST:event_schedule_addMouseClicked

    private void schedule_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_schedule_tableMouseClicked

    }//GEN-LAST:event_schedule_tableMouseClicked

    private void schedule_search_button1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_schedule_search_button1MouseClicked
        ParentPanel.removeAll();
        ParentPanel.add(Add_Schedule_Screen);
        ParentPanel.repaint();
        ParentPanel.revalidate();
    }//GEN-LAST:event_schedule_search_button1MouseClicked

    private void schedule_search_button2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_schedule_search_button2MouseClicked
        ParentPanel.removeAll();
        ParentPanel.add(Record_Grade_Screen);
        ParentPanel.repaint();
        ParentPanel.revalidate();
    }//GEN-LAST:event_schedule_search_button2MouseClicked

    private void schedule_refresh1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_schedule_refresh1MouseClicked
        grade_block_text.setText("");
        grade_studentNo_text.setText("");
        grade_studentName_text.setText("");
        grade_student_search_text.setText("");
        grade_subject_code_text.setText("");
        grade_subjectCode_text.setText("");
        grade_refresh();
        grade_student_refresh();
        grade_subject_refresh();
    }//GEN-LAST:event_schedule_refresh1MouseClicked

    private void grade_addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grade_addMouseClicked
        int respond = JOptionPane.showConfirmDialog(this, "Do you want to add?", "Confirm", JOptionPane.YES_NO_OPTION);

        if (respond == JOptionPane.YES_OPTION) {
            try {
                conn = ConnectionDB.Connect();
                ps = conn.prepareStatement(
                    "INSERT INTO PLM.GRADES "
                    + "(syear, semester, student_no, subject_code, block_no, grade) "
                    + "VALUES ("
                    + "'" + grade_sy_cmbx.getSelectedItem() + "', "
                    + "'" + grade_sem_cmbx.getSelectedItem() + "', "
                    + "'" + grade_studentNo_text.getText().trim() + "', "
                    + "'" + grade_subjectCode_text.getText().trim() + "', "
                    + "'" + grade_block_text.getText().trim() + "', "
                    + "'" + grade_cmbx.getSelectedItem() + "' "
                    + ")"
                );
                ps.execute();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            JOptionPane.showMessageDialog(this, "Adding of Record is Successful");
            grade_block_text.setText("");
            grade_studentNo_text.setText("");
            grade_studentName_text.setText("");
            grade_student_search_text.setText("");
            grade_subject_code_text.setText("");
            grade_subjectCode_text.setText("");
            grade_refresh();
            grade_student_refresh();
            grade_subject_refresh();
        } else {
            JOptionPane.showMessageDialog(this, "Adding of Record is Aborted");
        }

    }//GEN-LAST:event_grade_addMouseClicked

    private void grade_nextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grade_nextMouseClicked
        ParentPanel.removeAll();
        ParentPanel.add(Student_Grade_Screen);
        ParentPanel.repaint();
        ParentPanel.revalidate();
    }//GEN-LAST:event_grade_nextMouseClicked

    private void course_search_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_course_search_buttonMouseClicked
        try (Connection conn = ConnectionDB.Connect()) {
            String searchInput = course_search_text.getText().trim();

            if (searchInput.isEmpty()) {
                System.out.println("Search input is empty.");
                return;
            }

            searchInput = "%" + searchInput + "%";

            String query = "SELECT course_code AS \"Course\", "
                         + "description AS \"Description\", "
                         + "college_code AS \"College\", "
                         + "date_opened AS \"Date Opened\", "
                         + "date_closed AS \"Date Closed\", "
                         + "status AS \"Status\" "
                         + "FROM PLM.COURSE "
                         + "WHERE course_code LIKE ? "
                         + "OR description LIKE ? "
                         + "OR college_code LIKE ? "
                         + "OR date_opened LIKE ? "
                         + "OR date_closed LIKE ? "
                         + "OR status LIKE ?";

            try (PreparedStatement ps = conn.prepareStatement(query)) {

                ps.setString(1, searchInput);
                ps.setString(2, searchInput);
                ps.setString(3, searchInput);
                ps.setString(4, searchInput);
                ps.setString(5, searchInput);
                ps.setString(6, searchInput);

                try (ResultSet rs = ps.executeQuery()) {
                    course_table.setModel(DbUtils.resultSetToTableModel(rs));
                }
            }
            
            course_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            course_table.getColumnModel().getColumn(0).setPreferredWidth(100);
            course_table.getColumnModel().getColumn(1).setPreferredWidth(405);
            course_table.getColumnModel().getColumn(2).setPreferredWidth(50);
            course_table.getColumnModel().getColumn(3).setPreferredWidth(120);
            course_table.getColumnModel().getColumn(4).setPreferredWidth(120);
            course_table.getColumnModel().getColumn(5).setPreferredWidth(60);
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }//GEN-LAST:event_course_search_buttonMouseClicked

    private void student_status_cmbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_student_status_cmbxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_student_status_cmbxActionPerformed

    private void student_search_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_student_search_buttonMouseClicked
        try (Connection conn = ConnectionDB.Connect()) {
            String searchInput = student_search_text.getText().trim();

            if (searchInput.isEmpty()) {
                System.out.println("Search input is empty.");
                return;
            }

            searchInput = "%" + searchInput + "%";

            String query = "SELECT student_no AS \"Student No.\", "
                         + "lastname AS \"Surname\", "
                         + "firstname AS \"First Name\", "
                         + "email AS \"Email\", "
                         + "gender AS \"Gender\", "
                         + "course_code AS \"Course\", "
                         + "cp_num AS \"Phone No.\", "
                         + "address AS \"Address\", "
                         + "bday AS \"Birthday\", "
                         + "status AS \"Status\", "
                         + "date_started AS \"Started\", "
                         + "date_graduated AS \"Graduated\" "
                         + "FROM PLM.STUDENT "
                         + "WHERE student_no LIKE ? "
                         + "OR lastname LIKE ? "
                         + "OR firstname LIKE ? "
                         + "OR email LIKE ? "
                         + "OR gender LIKE ? "
                         + "OR course_code LIKE ? "
                         + "OR cp_num LIKE ? "
                         + "OR address LIKE ? "
                         + "OR bday LIKE ? "
                         + "OR status LIKE ? "
                         + "OR date_started LIKE ? "
                         + "OR date_graduated LIKE ?";

            try (PreparedStatement ps = conn.prepareStatement(query)) {

                ps.setString(1, searchInput);
                ps.setString(2, searchInput);
                ps.setString(3, searchInput);
                ps.setString(4, searchInput);
                ps.setString(5, searchInput);
                ps.setString(6, searchInput);
                ps.setString(7, searchInput);
                ps.setString(8, searchInput);
                ps.setString(9, searchInput);
                ps.setString(10, searchInput);
                ps.setString(11, searchInput);
                ps.setString(12, searchInput);

                try (ResultSet rs = ps.executeQuery()) {
                    student_table.setModel(DbUtils.resultSetToTableModel(rs));
                }
            }
            
            student_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            student_table.getColumnModel().getColumn(0).setPreferredWidth(71);
            student_table.getColumnModel().getColumn(1).setPreferredWidth(60);
            student_table.getColumnModel().getColumn(2).setPreferredWidth(70);
            student_table.getColumnModel().getColumn(3).setPreferredWidth(120);
            student_table.getColumnModel().getColumn(4).setPreferredWidth(50);
            student_table.getColumnModel().getColumn(5).setPreferredWidth(90);
            student_table.getColumnModel().getColumn(6).setPreferredWidth(75);
            student_table.getColumnModel().getColumn(7).setPreferredWidth(60);
            student_table.getColumnModel().getColumn(8).setPreferredWidth(75);
            student_table.getColumnModel().getColumn(9).setPreferredWidth(45);
            student_table.getColumnModel().getColumn(10).setPreferredWidth(75);
            student_table.getColumnModel().getColumn(11).setPreferredWidth(75);
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }//GEN-LAST:event_student_search_buttonMouseClicked

    private void employee_status_cmbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employee_status_cmbxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_employee_status_cmbxActionPerformed

    private void employee_search_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_employee_search_buttonMouseClicked
        try (Connection conn = ConnectionDB.Connect()) {
            String searchInput = employee_search_text.getText().trim();

            if (searchInput.isEmpty()) {
                System.out.println("Search input is empty.");
                return;
            }

            searchInput = "%" + searchInput + "%";

            String query = "SELECT employee_id AS \"Employee ID\", "
                         + "lastname AS \"Surname\", "
                         + "firstname AS \"First Name\", "
                         + "email AS \"Email\", "
                         + "gender AS \"Gender\", "
                         + "cp_num AS \"Phone No.\", "
                         + "address AS \"Address\", "
                         + "bday AS \"Birthday\", "
                         + "status AS \"Status\", "
                         + "date_started AS \"Started\", "
                         + "date_resigned AS \"Resigned\" "
                         + "FROM PLM.EMPLOYEE "
                         + "WHERE employee_id LIKE ? "
                         + "OR lastname LIKE ? "
                         + "OR firstname LIKE ? "
                         + "OR email LIKE ? "
                         + "OR gender LIKE ? "
                         + "OR cp_num LIKE ? "
                         + "OR address LIKE ? "
                         + "OR bday LIKE ? "
                         + "OR status LIKE ? "
                         + "OR date_started LIKE ? "
                         + "OR date_resigned LIKE ?";

            try (PreparedStatement ps = conn.prepareStatement(query)) {

                ps.setString(1, searchInput);
                ps.setString(2, searchInput);
                ps.setString(3, searchInput);
                ps.setString(4, searchInput);
                ps.setString(5, searchInput);
                ps.setString(6, searchInput);
                ps.setString(7, searchInput);
                ps.setString(8, searchInput);
                ps.setString(9, searchInput);
                ps.setString(10, searchInput);
                ps.setString(11, searchInput);

                try (ResultSet rs = ps.executeQuery()) {
                    employee_table.setModel(DbUtils.resultSetToTableModel(rs));
                }
            }
            employee_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            employee_table.getColumnModel().getColumn(0).setPreferredWidth(77);
            employee_table.getColumnModel().getColumn(1).setPreferredWidth(65);
            employee_table.getColumnModel().getColumn(2).setPreferredWidth(70);
            employee_table.getColumnModel().getColumn(3).setPreferredWidth(150);
            employee_table.getColumnModel().getColumn(4).setPreferredWidth(50);
            employee_table.getColumnModel().getColumn(5).setPreferredWidth(80);
            employee_table.getColumnModel().getColumn(6).setPreferredWidth(100);
            employee_table.getColumnModel().getColumn(7).setPreferredWidth(75);
            employee_table.getColumnModel().getColumn(8).setPreferredWidth(45);
            employee_table.getColumnModel().getColumn(9).setPreferredWidth(75);
            employee_table.getColumnModel().getColumn(10).setPreferredWidth(75);
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }//GEN-LAST:event_employee_search_buttonMouseClicked

    private void subject_search_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_subject_search_buttonMouseClicked
        try (Connection conn = ConnectionDB.Connect()) {
            String searchInput = subject_search_text.getText().trim();

            if (searchInput.isEmpty()) {
                System.out.println("Search input is empty.");
                return;
            }

            searchInput = "%" + searchInput + "%";

            String query = "SELECT subject_code AS \"Subject\", "
                         + "description AS \"Description\", "
                         + "units AS \"Units\", "
                         + "curriculum AS \"Curriculum\", "
                         + "college_code AS \"College\", "
                         + "status AS \"Status\", "
                         + "date_opened AS \"Date Opened\", "
                         + "date_closed AS \"Date Closed\" "
                         + "FROM PLM.SUBJECT "
                         + "WHERE subject_code LIKE ? "
                         + "OR description LIKE ? "
                         + "OR units LIKE ? "
                         + "OR curriculum LIKE ? "
                         + "OR college_code LIKE ? "
                         + "OR status LIKE ? "
                         + "OR date_opened LIKE ? "
                         + "OR date_closed LIKE ?";

            try (PreparedStatement ps = conn.prepareStatement(query)) {

                ps.setString(1, searchInput);
                ps.setString(2, searchInput);
                ps.setString(3, searchInput);
                ps.setString(4, searchInput);
                ps.setString(5, searchInput);
                ps.setString(6, searchInput);
                ps.setString(7, searchInput);
                ps.setString(8, searchInput);
                
                try (ResultSet rs = ps.executeQuery()) {
                    subject_table.setModel(DbUtils.resultSetToTableModel(rs));
                }
            }
            subject_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            subject_table.getColumnModel().getColumn(0).setPreferredWidth(70);
            subject_table.getColumnModel().getColumn(1).setPreferredWidth(425);
            subject_table.getColumnModel().getColumn(2).setPreferredWidth(50);
            subject_table.getColumnModel().getColumn(3).setPreferredWidth(70);
            subject_table.getColumnModel().getColumn(4).setPreferredWidth(50);
            subject_table.getColumnModel().getColumn(5).setPreferredWidth(45);
            subject_table.getColumnModel().getColumn(6).setPreferredWidth(75);
            subject_table.getColumnModel().getColumn(7).setPreferredWidth(75);
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }//GEN-LAST:event_subject_search_buttonMouseClicked

    private void schedule_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_schedule_addActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_schedule_addActionPerformed

    private void schedule_subject_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_schedule_subject_tableMouseClicked
        int row = schedule_subject_table.getSelectedRow();
        schedule_subject_code_text.setText(schedule_subject_table.getModel().getValueAt(row, 0).toString());                                   
    }//GEN-LAST:event_schedule_subject_tableMouseClicked

    private void schedule_employee_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_schedule_employee_tableMouseClicked
        int row = schedule_employee_table.getSelectedRow();
        schedule_employeeID_text.setText(schedule_employee_table.getModel().getValueAt(row, 0).toString());
        schedule_employeeName_text.setText(schedule_employee_table.getModel().getValueAt(row, 1).toString());
    }//GEN-LAST:event_schedule_employee_tableMouseClicked

    private void schedule_subject_search_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_schedule_subject_search_textActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_schedule_subject_search_textActionPerformed

    private void schedule_subject_search_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_schedule_subject_search_buttonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_schedule_subject_search_buttonActionPerformed

    private void schedule_subject_search_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_schedule_subject_search_buttonMouseClicked
       try (Connection conn = ConnectionDB.Connect()) {
            String searchInput = schedule_subject_search_text.getText().trim();

            if (searchInput.isEmpty()) {
                System.out.println("Search input is empty.");
                return;
            }

            searchInput = "%" + searchInput + "%";

            String query = "SELECT subject_code AS \"Subject\", "
                         + "description AS \"Description\" "
                         + "FROM PLM.SUBJECT "
                         + "WHERE subject_code LIKE ? "
                         + "OR description LIKE ? ";

            try (PreparedStatement ps = conn.prepareStatement(query)) {

                ps.setString(1, searchInput);
                ps.setString(2, searchInput);

                try (ResultSet rs = ps.executeQuery()) {
                    schedule_subject_table.setModel(DbUtils.resultSetToTableModel(rs));
                }
            }
            schedule_subject_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            schedule_subject_table.getColumnModel().getColumn(0).setPreferredWidth(70);
            schedule_subject_table.getColumnModel().getColumn(1).setPreferredWidth(370);
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }//GEN-LAST:event_schedule_subject_search_buttonMouseClicked

    private void schedule_employee_search_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_schedule_employee_search_textActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_schedule_employee_search_textActionPerformed

    private void schedule_employee_search_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_schedule_employee_search_buttonMouseClicked
        try (Connection conn = ConnectionDB.Connect()) {
            String searchInput = schedule_employee_search_text.getText().trim();

            if (searchInput.isEmpty()) {
                System.out.println("Search input is empty.");
                return;
            }

            searchInput = "%" + searchInput + "%";

            String query = "SELECT employee_id AS \"Employee ID\", "
                         + "lastname || ', ' || firstname AS \"Employee Name\" "
                         + "FROM PLM.EMPLOYEE "
                         + "WHERE employee_id LIKE ? "
                         + "OR lastname || ', ' || firstname LIKE ? ";

            try (PreparedStatement ps = conn.prepareStatement(query)) {

                ps.setString(1, searchInput);
                ps.setString(2, searchInput);

                try (ResultSet rs = ps.executeQuery()) {
                    schedule_employee_table.setModel(DbUtils.resultSetToTableModel(rs));
                }
            }
            schedule_employee_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            schedule_employee_table.getColumnModel().getColumn(0).setPreferredWidth(80);
            schedule_employee_table.getColumnModel().getColumn(1).setPreferredWidth(360);
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }//GEN-LAST:event_schedule_employee_search_buttonMouseClicked

    private void schedule_employee_search_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_schedule_employee_search_buttonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_schedule_employee_search_buttonActionPerformed

    private void schedule_subject_code_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_schedule_subject_code_textActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_schedule_subject_code_textActionPerformed

    private void schedule_employeeID_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_schedule_employeeID_textActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_schedule_employeeID_textActionPerformed

    private void schedule_employeeName_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_schedule_employeeName_textActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_schedule_employeeName_textActionPerformed

    private void schedule_sequence_cmbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_schedule_sequence_cmbxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_schedule_sequence_cmbxActionPerformed

    private void grade_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grade_addActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_addActionPerformed

    private void schedule_search_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_schedule_search_buttonMouseClicked
        try (Connection conn = ConnectionDB.Connect()) {
            String searchInput = schedule_search_text.getText().trim();

            if (searchInput.isEmpty()) {
                System.out.println("Search input is empty.");
                return;
            }

            searchInput = "%" + searchInput + "%";

            String query = "SELECT * "
                         + "FROM PLM.V_SUBJECT_SCHEDULE "
                         + "WHERE [School Year] LIKE ? "
                         + "OR Semester LIKE ? "
                         + "OR College LIKE ? "
                         + "OR Block LIKE ? "
                         + "OR [Subject Code] LIKE ? "
                         + "OR [Subject Title] LIKE ? "
                         + "OR Units LIKE ? "
                         + "OR Curriculum LIKE ? "
                         + "OR Day LIKE ? "
                         + "OR Time LIKE ? "
                         + "OR [Room Assignment] LIKE ? "
                         + "OR [Class Format] LIKE ? "
                         + "OR [Sequence Number] LIKE ? "
                         + "OR [Faculty ID] LIKE ? "
                         + "OR [Faculty Name] LIKE ? "
                         + "OR [Faculty Status] LIKE ? "
                         + "OR [Faculty Workload] LIKE ? "
                         + "OR [Enrolled Students] LIKE ? "
                         + "OR [Schedule Status] LIKE ? "
                         + "OR [Subject Status] LIKE ? ";

            try (PreparedStatement ps = conn.prepareStatement(query)) {

                ps.setString(1, searchInput);
                ps.setString(2, searchInput);
                ps.setString(3, searchInput);
                ps.setString(4, searchInput);
                ps.setString(5, searchInput);
                ps.setString(6, searchInput);
                ps.setString(7, searchInput);
                ps.setString(8, searchInput);
                ps.setString(9, searchInput);
                ps.setString(10, searchInput);
                ps.setString(11, searchInput);
                ps.setString(12, searchInput);
                ps.setString(13, searchInput);
                ps.setString(14, searchInput);
                ps.setString(15, searchInput);
                ps.setString(16, searchInput);
                ps.setString(17, searchInput);
                ps.setString(18, searchInput);
                ps.setString(19, searchInput);
                ps.setString(20, searchInput);
                
                try (ResultSet rs = ps.executeQuery()) {
                    schedule_table.setModel(DbUtils.resultSetToTableModel(rs));
                }
            }
            schedule_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            schedule_table.getColumnModel().getColumn(0).setPreferredWidth(75);
            schedule_table.getColumnModel().getColumn(1).setPreferredWidth(65);
            schedule_table.getColumnModel().getColumn(2).setPreferredWidth(50);
            schedule_table.getColumnModel().getColumn(3).setPreferredWidth(90);
            schedule_table.getColumnModel().getColumn(4).setPreferredWidth(80);
            schedule_table.getColumnModel().getColumn(5).setPreferredWidth(300);
            schedule_table.getColumnModel().getColumn(6).setPreferredWidth(35);
            schedule_table.getColumnModel().getColumn(7).setPreferredWidth(65);
            schedule_table.getColumnModel().getColumn(8).setPreferredWidth(30);
            schedule_table.getColumnModel().getColumn(9).setPreferredWidth(60);
            schedule_table.getColumnModel().getColumn(10).setPreferredWidth(120);
            schedule_table.getColumnModel().getColumn(11).setPreferredWidth(90);
            schedule_table.getColumnModel().getColumn(12).setPreferredWidth(110);
            schedule_table.getColumnModel().getColumn(13).setPreferredWidth(65);
            schedule_table.getColumnModel().getColumn(14).setPreferredWidth(100);
            schedule_table.getColumnModel().getColumn(15).setPreferredWidth(90);
            schedule_table.getColumnModel().getColumn(16).setPreferredWidth(100);
            schedule_table.getColumnModel().getColumn(17).setPreferredWidth(100);
            schedule_table.getColumnModel().getColumn(18).setPreferredWidth(90);
            schedule_table.getColumnModel().getColumn(19).setPreferredWidth(90);
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }//GEN-LAST:event_schedule_search_buttonMouseClicked

    private void grade_student_search_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grade_student_search_buttonMouseClicked
        try (Connection conn = ConnectionDB.Connect()) {
            String searchInput = grade_student_search_text.getText().trim();

            if (searchInput.isEmpty()) {
                System.out.println("Search input is empty.");
                return;
            }

            searchInput = "%" + searchInput + "%";

            String query = "SELECT student_no AS \"Student No.\", "
                         + "lastname || ', ' || firstname AS \"Student Name\" "
                         + "FROM PLM.STUDENT "
                         + "WHERE student_no LIKE ? "
                         + "OR lastname || ', ' || firstname LIKE ? ";

            try (PreparedStatement ps = conn.prepareStatement(query)) {

                ps.setString(1, searchInput);
                ps.setString(2, searchInput);

                try (ResultSet rs = ps.executeQuery()) {
                    grade_student_table.setModel(DbUtils.resultSetToTableModel(rs));
                }
            }
            grade_student_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            grade_student_table.getColumnModel().getColumn(0).setPreferredWidth(70);
            grade_student_table.getColumnModel().getColumn(1).setPreferredWidth(360);
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }//GEN-LAST:event_grade_student_search_buttonMouseClicked

    private void grade_student_search_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grade_student_search_buttonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_student_search_buttonActionPerformed

    private void grade_student_search_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grade_student_search_textActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_student_search_textActionPerformed

    private void grade_student_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grade_student_tableMouseClicked
        int row = grade_student_table.getSelectedRow();
        grade_studentNo_text.setText(grade_student_table.getModel().getValueAt(row, 0).toString());
        grade_studentName_text.setText(grade_student_table.getModel().getValueAt(row, 1).toString());
    }//GEN-LAST:event_grade_student_tableMouseClicked

    private void grade_subject_search_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grade_subject_search_buttonMouseClicked
        try (Connection conn = ConnectionDB.Connect()) {
            String searchInput = grade_subject_code_text.getText().trim();

            if (searchInput.isEmpty()) {
                System.out.println("Search input is empty.");
                return;
            }

            searchInput = "%" + searchInput + "%";

            String query = "SELECT ss.subject_code AS \"Subject\", "
                            + "ss.block_no AS \"Block No.\", "
                            + "s.description AS \"Description\" "
                            + "FROM PLM.SUBJECT_SCHEDULE ss "
                            + "JOIN PLM.SUBJECT s "
                            + "ON ss.subject_code = s.subject_code "
                            + "WHERE ss.subject_code LIKE ? "
                            + "OR ss.block_no LIKE ? "
                            + "OR s.description LIKE ?";

            try (PreparedStatement ps = conn.prepareStatement(query)) {

                ps.setString(1, searchInput);
                ps.setString(2, searchInput);
                ps.setString(3, searchInput);

                try (ResultSet rs = ps.executeQuery()) {
                    grade_subject_table.setModel(DbUtils.resultSetToTableModel(rs));
                }
            }
            grade_subject_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            grade_subject_table.getColumnModel().getColumn(0).setPreferredWidth(70);
            grade_subject_table.getColumnModel().getColumn(1).setPreferredWidth(80);
            grade_subject_table.getColumnModel().getColumn(2).setPreferredWidth(360);
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }//GEN-LAST:event_grade_subject_search_buttonMouseClicked

    private void grade_subject_search_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grade_subject_search_buttonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_subject_search_buttonActionPerformed

    private void grade_subject_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grade_subject_tableMouseClicked
        int row = grade_subject_table.getSelectedRow();
        grade_subjectCode_text.setText(grade_subject_table.getModel().getValueAt(row, 0).toString());
        grade_block_text.setText(grade_subject_table.getModel().getValueAt(row, 1).toString());
    }//GEN-LAST:event_grade_subject_tableMouseClicked

    private void grade_subjectCode_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grade_subjectCode_textActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_subjectCode_textActionPerformed

    private void grade_studentName_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grade_studentName_textActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_grade_studentName_textActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add_Schedule;
    private javax.swing.JPanel Add_Schedule_Screen;
    private javax.swing.JButton Dimension_Table;
    private javax.swing.JPanel Dimension_Table_Screen;
    private javax.swing.JButton Home;
    private javax.swing.JPanel ParentPanel;
    private javax.swing.JButton Record_Grade;
    private javax.swing.JPanel Record_Grade_Screen;
    private javax.swing.JPanel School_Record;
    private javax.swing.JButton Student_Grade;
    private javax.swing.JPanel Student_Grade_Screen;
    private javax.swing.JButton Subject_Schedule;
    private javax.swing.JPanel Subject_Schedule_Screen;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton college;
    private javax.swing.JButton college_add;
    private javax.swing.JTextField college_code_text;
    private com.toedter.calendar.JDateChooser college_date_closed;
    private com.toedter.calendar.JDateChooser college_date_opened;
    private javax.swing.JButton college_delete;
    private javax.swing.JTextField college_desc_text;
    private javax.swing.JButton college_refresh;
    private javax.swing.JPanel college_screen;
    private javax.swing.JButton college_search_button;
    private javax.swing.JTextField college_search_text;
    private javax.swing.JComboBox<String> college_status_cmbx;
    private javax.swing.JTable college_table;
    private javax.swing.JButton college_update;
    private javax.swing.JButton course;
    private javax.swing.JButton course_add;
    private javax.swing.JTextField course_code_text;
    private javax.swing.JComboBox<String> course_college_code_cbmx;
    private com.toedter.calendar.JDateChooser course_date_closed;
    private com.toedter.calendar.JDateChooser course_date_opened;
    private javax.swing.JButton course_delete;
    private javax.swing.JTextField course_desc_text;
    private javax.swing.JButton course_refresh;
    private javax.swing.JPanel course_screen;
    private javax.swing.JButton course_search_button;
    private javax.swing.JTextField course_search_text;
    private javax.swing.JComboBox<String> course_status_cmbx;
    private javax.swing.JTable course_table;
    private javax.swing.JButton course_update;
    private javax.swing.JButton employee;
    private javax.swing.JButton employee_add;
    private javax.swing.JTextField employee_address_text;
    private com.toedter.calendar.JDateChooser employee_bday;
    private com.toedter.calendar.JDateChooser employee_date_resigned;
    private com.toedter.calendar.JDateChooser employee_date_started;
    private javax.swing.JButton employee_delete;
    private javax.swing.JTextField employee_email_text;
    private javax.swing.JTextField employee_firstname_text;
    private javax.swing.JComboBox<String> employee_gender_cmbx;
    private javax.swing.JTextField employee_id_text;
    private javax.swing.JTextField employee_lastname_text;
    private javax.swing.JTextField employee_phone_text;
    private javax.swing.JButton employee_refresh;
    private javax.swing.JPanel employee_screen;
    private javax.swing.JButton employee_search_button;
    private javax.swing.JTextField employee_search_text;
    private javax.swing.JComboBox<String> employee_status_cmbx;
    private javax.swing.JTable employee_table;
    private javax.swing.JButton employee_update;
    private javax.swing.JButton grade_add;
    private javax.swing.JTextField grade_block_text;
    private javax.swing.JComboBox<String> grade_cmbx;
    private javax.swing.JButton grade_next;
    private javax.swing.JButton grade_search_button;
    private javax.swing.JTextField grade_search_text;
    private javax.swing.JComboBox<String> grade_sem_cmbx;
    private javax.swing.JTextField grade_studentName_text;
    private javax.swing.JTextField grade_studentNo_text;
    private javax.swing.JButton grade_student_search_button;
    private javax.swing.JTextField grade_student_search_text;
    private javax.swing.JTable grade_student_table;
    private javax.swing.JTextField grade_subjectCode_text;
    private javax.swing.JTextField grade_subject_code_text;
    private javax.swing.JButton grade_subject_search_button;
    private javax.swing.JTable grade_subject_table;
    private javax.swing.JComboBox<String> grade_sy_cmbx;
    private javax.swing.JTable grade_table;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel130;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTable jTable2;
    private javax.swing.JPanel navigation;
    private javax.swing.JButton schedule_add;
    private javax.swing.JTextField schedule_block_text;
    private javax.swing.JComboBox<String> schedule_college_code_cmbx;
    private javax.swing.JComboBox<String> schedule_day_cmbx;
    private javax.swing.JTextField schedule_employeeID_text;
    private javax.swing.JTextField schedule_employeeName_text;
    private javax.swing.JButton schedule_employee_search_button;
    private javax.swing.JTextField schedule_employee_search_text;
    private javax.swing.JTable schedule_employee_table;
    private javax.swing.JButton schedule_next;
    private javax.swing.JButton schedule_refresh;
    private javax.swing.JButton schedule_refresh1;
    private javax.swing.JTextField schedule_room_text;
    private javax.swing.JButton schedule_search_button;
    private javax.swing.JButton schedule_search_button1;
    private javax.swing.JButton schedule_search_button2;
    private javax.swing.JTextField schedule_search_text;
    private javax.swing.JComboBox<String> schedule_semester_cmbx;
    private javax.swing.JComboBox<String> schedule_sequence_cmbx;
    private javax.swing.JTextField schedule_subject_code_text;
    private javax.swing.JButton schedule_subject_search_button;
    private javax.swing.JTextField schedule_subject_search_text;
    private javax.swing.JTable schedule_subject_table;
    private javax.swing.JComboBox<String> schedule_sy_cmbx;
    private javax.swing.JTable schedule_table;
    private javax.swing.JTextField schedule_time_text;
    private javax.swing.JComboBox<String> schedule_type_cmbx;
    private javax.swing.JButton sem_add;
    private javax.swing.JButton sem_clear;
    private javax.swing.JButton sem_delete;
    private javax.swing.JButton sem_search_button;
    private javax.swing.JTextField sem_search_text;
    private javax.swing.JTable sem_table;
    private javax.swing.JTextField sem_text;
    private javax.swing.JTextField sem_text_selected;
    private javax.swing.JButton sem_update;
    private javax.swing.JButton student;
    private javax.swing.JButton student_add;
    private javax.swing.JTextField student_address_text;
    private com.toedter.calendar.JDateChooser student_bday;
    private javax.swing.JComboBox<String> student_course_code_cmbx;
    private com.toedter.calendar.JDateChooser student_date_graduated;
    private com.toedter.calendar.JDateChooser student_date_started;
    private javax.swing.JButton student_delete;
    private javax.swing.JTextField student_email_text;
    private javax.swing.JTextField student_firstname_text;
    private javax.swing.JComboBox<String> student_gender_cmbx;
    private javax.swing.JTextField student_lastname_text;
    private javax.swing.JTextField student_no_text;
    private javax.swing.JTextField student_phone_text;
    private javax.swing.JButton student_refresh;
    private javax.swing.JPanel student_screen;
    private javax.swing.JButton student_search_button;
    private javax.swing.JTextField student_search_text;
    private javax.swing.JComboBox<String> student_status_cmbx;
    private javax.swing.JTable student_table;
    private javax.swing.JButton student_update;
    private javax.swing.JButton subject;
    private javax.swing.JButton subject_add;
    private javax.swing.JTextField subject_code_text;
    private javax.swing.JComboBox<String> subject_college_code_cmbx;
    private javax.swing.JTextField subject_curriculum_text;
    private com.toedter.calendar.JDateChooser subject_date_closed;
    private com.toedter.calendar.JDateChooser subject_date_opened;
    private javax.swing.JButton subject_delete;
    private javax.swing.JTextField subject_desc_text;
    private javax.swing.JButton subject_refresh;
    private javax.swing.JPanel subject_screen;
    private javax.swing.JButton subject_search_button;
    private javax.swing.JTextField subject_search_text;
    private javax.swing.JComboBox<String> subject_status_cmbx;
    private javax.swing.JTable subject_table;
    private javax.swing.JTextField subject_units_text;
    private javax.swing.JButton subject_update;
    private javax.swing.JButton sy_add;
    private javax.swing.JButton sy_clear;
    private javax.swing.JButton sy_delete;
    private javax.swing.JButton sy_search_button;
    private javax.swing.JTextField sy_search_text;
    private javax.swing.JButton sy_sem;
    private javax.swing.JPanel sy_semester_screen;
    private javax.swing.JTable sy_table;
    private javax.swing.JTextField sy_text;
    private javax.swing.JTextField sy_text_selected;
    private javax.swing.JButton sy_update;
    // End of variables declaration//GEN-END:variables
}
