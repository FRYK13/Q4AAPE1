package PE1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import PE1.PlaySound;  // Import the PlaySound class

public class Game {
    JFrame frame;
    JLabel background;
    JButton button1;
    PlaySound playSound;  // Declare PlaySound instance
    PlaySound backgroundMusic;  // Declare PlaySound instance for background music

    public Game() {
        frame = new JFrame("Game");
        button1 = new JButton("Let's Play");
        background = new JLabel();
        playSound = new PlaySound();  // Initialize PlaySound instance for button sound
        backgroundMusic = new PlaySound();  // Initialize PlaySound instance for background music
    }

    public void setFrame() {
        frame.setLayout(new GraphPaperLayout(new Dimension(26, 20)));

        // background1
        background = new JLabel(new ImageIcon("src/Background_Assets/bg1.png"));
        frame.add(background, new Rectangle(0, 0, 27, 21));

        frame.add(button1, new Rectangle(10, 17, 6, 2));

        // button logic
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SecondFrame secondFrame = new SecondFrame();
                playSound.playEffect("src/Background_Assets/button.wav");  // Play sound effect for button click
            }
        });

        // Play background music
         backgroundMusic.playBackgroundMusic("src/Background_Assets/bg.wav");  // Play background music in loop

        frame.setSize(832, 640);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    class SecondFrame {
        JFrame frame;

        // buttons
        JButton first_choice;
        JButton second_choice;
        JButton third_choice;
        JButton fourth_choice;

        // background
        JLabel background;
        JLabel question;

        // game
        String[] questions;
        String[][] choices;

        // logic
        int[] correct_answers;
        int question_index;

        JToggleButton help;

        int correct_count = 0;
        int wrong_count = 0;
        int mistakes = 0;

        // labels
        JLabel correct_label;
        JLabel wrong_label;

        public SecondFrame() {
            // list of questions
            questions = new String[] {
                "1",
                "2",
                "3",
                "4",
                "5"
            };

            // list of choices
            choices = new String[][] {
                {"5 units", "10 units", "15 units", "25 units"},
                {"15 units", "100 units", "50 units", "25 units"},
                {"30 degrees", "60 degrees", "90 degrees", "180 degrees"}, 
                {"1:√2:√3", "1:2:√3", "1:√3:2", "1:1:√2"}, 
                {"5 units", "5√3 units", "10√2 units", "10√3 units"} 
            };

            // answers
            correct_answers = new int[] {1, 3, 0, 1, 0}; // correct answers
            question_index = 0;

            // frame
            frame = new JFrame("Game");
            frame.setSize(832, 640);
            frame.setLayout(new GraphPaperLayout(new Dimension(26, 20)));
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLocationRelativeTo(null);

            // question placement
            question = new JLabel(questions[question_index]);
            question.setHorizontalAlignment(SwingConstants.CENTER);
            question.setForeground(Color.WHITE);
            frame.add(question, new Rectangle(0, 9, 26, 2));

            // choices button
            first_choice = new JButton(choices[question_index][0]);
            second_choice = new JButton(choices[question_index][1]);
            third_choice = new JButton(choices[question_index][2]);
            fourth_choice = new JButton(choices[question_index][3]);

            // change
            first_choice.addActionListener(new ChoiceListener());
            second_choice.addActionListener(new ChoiceListener());
            third_choice.addActionListener(new ChoiceListener());
            fourth_choice.addActionListener(new ChoiceListener());

            // background
            background = new JLabel();
            background.setIcon(new ImageIcon("src/Background_Assets/bg" + (question_index + 2) + ".png"));
            background.setBounds(0, 0, 832, 640);

            // button placement
            frame.add(first_choice, new Rectangle(4, 16, 4, 2));
            frame.add(second_choice, new Rectangle(9, 16, 4, 2));
            frame.add(third_choice, new Rectangle(14, 16, 4, 2));
            frame.add(fourth_choice, new Rectangle(19, 16, 4, 2));

            frame.add(background);
            frame.setVisible(true);

            correct_label = new JLabel("Correct:");
            frame.add(correct_label, new Rectangle(0, 0, 4, 2));
            correct_label.setForeground(Color.GREEN);
            frame.add(correct_label);

            wrong_label = new JLabel("Wrong:");
            frame.add(wrong_label, new Rectangle(6, 0, 4, 2));
            wrong_label.setForeground(Color.RED);

            // game mechanics
            help = new JToggleButton("Help");
            frame.add(help, new Rectangle(24, 0, 2, 1));
            help.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (help.isSelected()) {
                        JOptionPane.showMessageDialog(null, "Answer this quiz. In order for you to win, you must not have 3 mistakes or higher.");
                    }
                }
            });
        }

        // 2nd frame logic
        private class ChoiceListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound.playEffect("src/Background_Assets/button.wav");  // Play sound effect
                
                JButton button = (JButton) e.getSource();
                String choice = button.getText();
                if (choice.equals(choices[question_index][correct_answers[question_index]])) {
                    // correct answer
                    correct_count++;
                    JOptionPane.showMessageDialog(null, "Correct!");
                } else {
                    // wrong answer
                    wrong_count++;
                    mistakes++;
                    JOptionPane.showMessageDialog(null, "Incorrect!");
                }

                // move to the next question
                question_index++;
                if (question_index < questions.length) {
                    // update the question and choices
                    question.setText(questions[question_index]);
                    first_choice.setText(choices[question_index][0]);
                    second_choice.setText(choices[question_index][1]);
                    third_choice.setText(choices[question_index][2]);
                    fourth_choice.setText(choices[question_index][3]);

                    // update the background
                    background.setIcon(new ImageIcon("src/Background_Assets/bg" + (question_index + 2) + ".png"));
                } else {
                    // end of the quiz
                    if (mistakes < 3) {
                        JOptionPane.showMessageDialog(null, "Quiz completed! You got " + correct_count + " correct answers.");
                    } else {
                        JOptionPane.showMessageDialog(null, "You lose! You made " + mistakes + " mistakes.");
                    }
                    frame.dispose();
                }

                // update the labels
                correct_label.setText("Correct: " + correct_count);
                wrong_label.setText("Wrong: " + wrong_count);
            }
        }
    }

    public static void main(String[] args) {
        Game main = new Game();
        main.setFrame();
    }
}