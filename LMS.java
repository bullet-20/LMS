import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;

public class LMS extends JFrame implements ActionListener {
    private static final Course[] COURSES = {
      new Course("Computer Programming", Arrays.asList(
          "What is a programming language?",
          "What is the difference between a compiler and an interpreter?",
          "What is object-oriented programming?"
      )),
      new Course("English for Academic and Professional Purposes", Arrays.asList(
          "What are the characteristics of academic writing?",
          "What is the difference between an argumentative and an expository essay?",
          "What are some strategies for effective academic reading?"
      )),
      new Course("EMTEC or Empowerment Technologies", Arrays.asList(
          "What is digital literacy and why is it important?",
          "What are the different types of digital technologies?",
          "How can technology be used for social empowerment?"
      )),
      new Course("Practical Research", Arrays.asList(
          "What is the scientific method and how is it applied in research?",
          "What are the different types of research designs?",
          "How can research be used to inform decision-making?"
      )),
      new Course("Physical Education and Health", Arrays.asList(
          "What are the benefits of regular physical activity?",
          "What are the components of physical fitness?",
          "How can healthy lifestyle choices improve overall well-being?"
      )),
      new Course("Statistics and Probability", Arrays.asList(
          "What are the basic principles of probability theory?",
          "What are some common statistical measures and how are they calculated?",
          "How can statistical analysis be used to make informed decisions?"
      )),
      new Course("Pagbasa at Pagsusuri ng Ibat Ibang Teksto Tungo sa Pananaliksik", Arrays.asList(
          "What are the different types of texts and how are they analyzed?",
          "What is critical thinking and how can it be applied to reading and analysis?",
          "How can research skills be applied to reading and analysis of different texts?"
      )),
      new Course("English for Reading and Writing", Arrays.asList(
          "What are the principles of effective reading comprehension?",
          "How can writing skills be developed through practice and feedback?",
          "What are some strategies for effective academic writing?"
      ))
  };

	
    private JTextField nameField;
    private JComboBox<String> gradeLevelComboBox;
    private JTextField strandField;
    private JButton continueButton1;
    private JPanel coursesPanel;
    private JButton continueButton2;
    private JPanel selectedCoursesPanel;
    private JButton showAvailableCoursesButton;
    private Student student;
    private String gradeLevel;

	    
    public LMS() {
        setTitle("LMS");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
        // Create the first panel for the registration form.
        JPanel formPanel = new JPanel(new GridLayout(0, 2));
        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(150, 30));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Grade level:"));
        gradeLevelComboBox = new JComboBox<>(new String[] {"Grade 11", "Grade 12"});
        formPanel.add(gradeLevelComboBox);
        formPanel.add(new JLabel("Strand/Major:"));
        strandField = new JTextField();
        strandField.setPreferredSize(new Dimension(300, 30));
        formPanel.add(strandField);
        continueButton1 = new JButton("Continue");
        continueButton1.addActionListener(this);
        formPanel.add(continueButton1);
	
        setContentPane(formPanel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == continueButton1) {
            //save the information from the registration form.
            String name = nameField.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your name.");
                return;
            }
			
			//set the gradeLevel variable to the selected item in the gradeLevelComboBox
			gradeLevel = (String) gradeLevelComboBox.getSelectedItem();
            
            String strand = strandField.getText().trim();
            if (strand.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your strand/major.");
                return;
            }

            student = new Student(name, gradeLevel, strand, COURSES);


            // Create the course selection panel
            coursesPanel = new JPanel(new GridLayout(0, 1));
            for (Course course : COURSES) {
                JButton button = new JButton(course.getName());
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Display the questions for the selected course
                    selectedCoursesPanel.removeAll();
                    for (String question : course.getQuestions()) {
                        JLabel label = new JLabel(question);
                        selectedCoursesPanel.add(label);
                    }
                    selectedCoursesPanel.revalidate();
                    selectedCoursesPanel.repaint();
                }
            });
            coursesPanel.add(button);
        }

        continueButton2 = new JButton("Continue");
        continueButton2.addActionListener(this);
        coursesPanel.add(continueButton2);

        // Switch to the course selection panel
        setContentPane(coursesPanel);
        revalidate();


        } else if (e.getSource() == continueButton2) {
            // Collect the selected courses
            selectedCoursesPanel = new JPanel(new GridLayout(0, 1));

            // Add a label to display the selected course name
            JLabel courseLabel = new JLabel("No course selected");
            selectedCoursesPanel.add(courseLabel);

            // Add a label to separate the course name from the questions
            selectedCoursesPanel.add(new JLabel("Questions:"));

            // Switch to the course selection panel
            setContentPane(selectedCoursesPanel);
            revalidate();


            // Show a button to view the available courses
            showAvailableCoursesButton = new JButton("Show available courses");
            showAvailableCoursesButton.addActionListener(this);
            JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            topPanel.add(showAvailableCoursesButton);
            JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.add(topPanel, BorderLayout.NORTH);
            mainPanel.add(selectedCoursesPanel, BorderLayout.CENTER);
            setContentPane(mainPanel);
            revalidate();

        } else if (e.getSource() == showAvailableCoursesButton) {
            // Show a dialog with the available courses
            JDialog dialog = new JDialog(this, "Available Courses", true);
            JPanel panel = new JPanel(new GridLayout(0, 1));
            for (String course : COURSES) {
                boolean selected = false;
                for (Component c : selectedCoursesPanel.getComponents()) {
                    if (c instanceof JLabel && course.equals(((JLabel) c).getText())) {
                        selected = true;
                        break;
                    }
                }
                if (!selected) {
                    JCheckBox checkbox = new JCheckBox(course);
                    panel.add(checkbox);
                }
            }

            JButton closeButton = new JButton("Close");
            closeButton.addActionListener(e2 -> dialog.dispose());

            JButton addCoursesButton = new JButton("Add Selected Courses");
            addCoursesButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Display the questions for the selected course
                    courseLabel.setText(course.getName());
                    selectedCoursesPanel.removeAll();
                    for (String question : course.getQuestions()) {
                        JLabel label = new JLabel(question);
                        selectedCoursesPanel.add(label);
                    }
                    selectedCoursesPanel.revalidate();
                    selectedCoursesPanel.repaint();
                }
            });


            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            buttonPanel.add(addCoursesButton);
            buttonPanel.add(closeButton);
            panel.add(buttonPanel);
            dialog.setContentPane(panel);
            dialog.pack();
            dialog.setLocationRelativeTo(this);
            dialog.setVisible(true);
        }
    }

    public static void main(String[] args) {
        new LMS();
    }
}