package PE1;

import PE1.Game;
import PE1.PlaySound;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
    private static JFrame frame;
    private static JPanel mainPanel;
    private static PlaySound playSound;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            frame = new JFrame("Automation");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(440, 520);
            frame.setLocationRelativeTo(null);

            mainPanel = new JPanel();
            placeMainMenuComponents(mainPanel);
            frame.add(mainPanel);
            frame.setVisible(true);

            playSound = new PlaySound(); 
            playSound.playBackgroundMusic("src/Background_Assets/bg.wav");
        });
    }

    /* Main menu panel for automation and game options */
    public static void placeMainMenuComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Main Menu");
        titleLabel.setFont(titleLabel.getFont().deriveFont(40f));
        titleLabel.setBounds(110, 20, 400, 40);
        panel.add(titleLabel);

        JButton automationButton = new JButton("Automation");
        automationButton.setBounds(20, 100, 390, 40);
        panel.add(automationButton);
        automationButton.addActionListener(e -> {
            panel.removeAll();
            placeAutomationComponents(panel);
            panel.revalidate();
            panel.repaint();
            playSound.playEffect("src/Background_Assets/button.wav"); 
        });

        JButton gameButton = new JButton("Game");
        gameButton.setBounds(20, 160, 390, 40);
        panel.add(gameButton);
        gameButton.addActionListener(e -> {
            Game game = new Game();
            game.setFrame();
            playSound.playEffect("src/Background_Assets/button.wav"); 
        });

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(20, 220, 390, 40);
        panel.add(exitButton);
        exitButton.addActionListener(e -> {
            System.exit(0);
            playSound.playEffect("src/Background_Assets/button.wav");
        });
    }

    /* Automation panel */
    public static void placeAutomationComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Choose Automation");
        titleLabel.setFont(titleLabel.getFont().deriveFont(40f));
        titleLabel.setBounds(23, 20, 400, 40);
        panel.add(titleLabel);

        JButton calculateHypotenuseButton = new JButton("Calculate Hypotenuse and Adjacent Side");
        calculateHypotenuseButton.setBounds(20, 100, 390, 40);
        panel.add(calculateHypotenuseButton);
        calculateHypotenuseButton.addActionListener(e -> {
            panel.removeAll();
            placeFirstAutomationPanel(panel, "hypotenuse");
            panel.revalidate();
            panel.repaint();
            playSound.playEffect("src/Background_Assets/button.wav");
        });

        JButton calculateAdjacentButton = new JButton("Calculate Adjacent Side and Short Side");
        calculateAdjacentButton.setBounds(20, 160, 390, 40);
        panel.add(calculateAdjacentButton);
        calculateAdjacentButton.addActionListener(e -> {
            panel.removeAll();
            placeFirstAutomationPanel(panel, "adjacent");
            panel.revalidate();
            panel.repaint();
            playSound.playEffect("src/Background_Assets/button.wav");
        });

        JButton backButton = new JButton("Back");
        backButton.setBounds(20, 220, 390, 40);
        panel.add(backButton);
        backButton.addActionListener(e -> {
            panel.removeAll();
            placeMainMenuComponents(panel);
            panel.revalidate();
            panel.repaint();
            playSound.playEffect("src/Background_Assets/button.wav");
        });
    }

    public static void placeFirstAutomationPanel(JPanel panel, String calculationType) {
    panel.setLayout(null);

    JLabel label1;
    if (calculationType.equals("hypotenuse")) {
        label1 = new JLabel("Input the value of the short side:");
    } else {
        label1 = new JLabel("Input the value of the hypotenuse:");
    }

    label1.setFont(label1.getFont().deriveFont(15f));
    label1.setBounds(40, 20, 400, 40);
    panel.add(label1);

    JTextField userInputField = new JTextField();
    userInputField.setBounds(40, 60, 200, 25);
    panel.add(userInputField);

    JButton solveButton = new JButton("Solve");
    solveButton.setBounds(260, 60, 150, 25);
    panel.add(solveButton);
    playSound.playEffect("src/Background_Assets/button.wav");

    JButton backButton = new JButton("Back");
    backButton.setBounds(20, 100, 390, 40);
    panel.add(backButton);
    backButton.addActionListener(e -> {
        panel.removeAll();
        placeAutomationComponents(panel);
        panel.revalidate();
        panel.repaint();
        playSound.playEffect("src/Background_Assets/button.wav");
    });

    solveButton.addActionListener(e -> {
        String userInput = userInputField.getText();
        float sideValue = Float.parseFloat(userInput);
        playSound.playEffect("src/Background_Assets/button.wav");
        
        if (calculationType.equals("hypotenuse")) {
            // Calculate hypotenuse and adjacent side
            float sideb = sideValue * 2;
            float sidec = (float) (sideValue * Math.sqrt(3));

            String resultMessage = "The hypotenuse is 2s, which is " + sideb + "\n"
                    + "The adjacent side is s multiplied by the square root of 3, which is " + sidec + "\n";

            float sidesum = (sideValue * sideValue) + (sidec * sidec);
            float sidesumm = (sideb * sideb);
            
            JOptionPane.showMessageDialog(null, resultMessage, "Result", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Calculate adjacent side and short side
float sideb = (float) (sideValue / 2);
float sidec = sideb * (float) Math.sqrt(3);

String resultMessage = "The shorter side is hypotenuse/2, which is " + sideb + "\n"
        + "The adjacent side is shorter side multiplied by the square root of 3, which is " + sidec;

            JOptionPane.showMessageDialog(null, resultMessage, "Result", JOptionPane.INFORMATION_MESSAGE);
        }
    });
}
}