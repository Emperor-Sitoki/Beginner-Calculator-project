import java.awt.*;
import java.awt.event.*;
//Graphics and event handling

import java.util.Arrays;
//Using these for buttons

import javax.swing.*;
import javax.swing.border.LineBorder;
//Modifying border of buttons


public class Calculator {
    int boardWidth = 360;
    int boardHeight = 540;

    //Colors on calculator
    Color customBlack = new Color(33, 33, 31);
    Color customSoftGreen = new Color(128, 207, 141);
    Color customDarkGreen = new Color(74, 135, 93);
    Color customGrey = new Color(89, 89, 81);

    String[] buttonsValues = {
            "AC", "+/-", "%", "/",
            "7", "8", "9", "x",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "0", ".", "sqrt", "="
    };
    String[] rightSymbols = {"/", "x", "-", "+", "="};
    String[] topSymbols = {"AC", "+/-", "%"};


    JFrame frame = new JFrame("Calculator");
    JLabel displayLabel = new JLabel();//for the Text
    JPanel displayPanel = new JPanel();
    JPanel buttonsPanel = new JPanel(); //For buttons

    String A = null;
    String B = null;
    String operator = null;

    Calculator(){
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());

        //Label
        displayLabel.setBackground(customBlack);
        displayLabel.setForeground(Color.GRAY);//Text color
        displayLabel.setFont(new Font("Arial", Font.PLAIN, 80));
        displayLabel.setHorizontalAlignment(JLabel.RIGHT);
        displayLabel.setText("0");
        displayLabel.setOpaque(true);


        //Panel to add label or text
        displayPanel.setLayout(new BorderLayout());
        displayPanel.add(displayLabel);
        frame.add(displayPanel, BorderLayout.NORTH);



        //For buttons
        buttonsPanel.setLayout(new GridLayout(5, 4));
        buttonsPanel.setBackground(customBlack);
        frame.add(buttonsPanel);

        for (int i = 0; i < buttonsValues.length; i++){
            JButton button = new JButton();
            String buttonValue = buttonsValues[i];
            button.setFont(new Font("Arial", Font.PLAIN, 30));
            button.setText(buttonValue);
            button.setFocusable(false);
            button.setBorder(new LineBorder(customBlack));

            if (Arrays.asList(topSymbols).contains(buttonValue)){
                button.setBackground(customDarkGreen);
                button.setForeground(customSoftGreen);
            }
            else if (Arrays.asList(rightSymbols).contains(buttonValue)){
                button.setBackground(customGrey);
                button.setForeground(customBlack);
            }
            else {
                button.setBackground(customSoftGreen);
                button.setForeground(customDarkGreen);
            }

            buttonsPanel.add(button);


            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton button = (JButton) e.getSource();
                    String buttonValue = button.getText();

                    if (Arrays.asList(rightSymbols).contains(buttonValue)){
                        if (buttonValue == "=") {
                            if (A != null){
                                B = displayLabel.getText();

                            }
                                if(operator == "+"){
                                    double numA = Double.parseDouble(A);
                                    double numB = Double.parseDouble(B);
                                    displayLabel.setText(makeNonDecimal(numA + numB));
                                    A = null;
                                    B = null;
                                    operator = null;
                                }

                            if(operator == "-"){
                                double numA = Double.parseDouble(A);
                                double numB = Double.parseDouble(B);
                                displayLabel.setText(makeNonDecimal(numA - numB));
                                A = null;
                                B = null;
                                operator = null;
                            }
                            if(operator == "x"){
                                double numA = Double.parseDouble(A);
                                double numB = Double.parseDouble(B);
                                displayLabel.setText(makeNonDecimal(numA * numB));
                                A = null;
                                B = null;
                                operator = null;
                            }
                            if(operator == "/"){
                                double numA = Double.parseDouble(A);
                                double numB = Double.parseDouble(B);

                                if (numB == 0){
                                    //I left o on screen so user knows it doesn't work
                                    displayLabel.setText("0");
                                }
                                else {
                                    displayLabel.setText(makeNonDecimal(numA / numB));
                                }
                                A = null;
                                B = null;
                                operator = null;
                            }
                        }
                        else if ("+-x/".contains(buttonValue)){
                            if(operator == null){
                                A = displayLabel.getText();
                                displayLabel.setText("0");
                                B = "0";
                            }

                            operator = buttonValue;
                        }

                    }
                    else if (Arrays.asList(topSymbols).contains(buttonValue)){
                        if (buttonValue == "AC"){
                            if (!displayLabel.getText().equals("0")){
                                displayLabel.setText("0");
                            }
                        }else if (buttonValue == "+/-"){
                            double numDisplay = Double.parseDouble(displayLabel.getText());
                            numDisplay *= -1;
                            displayLabel.setText(makeNonDecimal(numDisplay));
                        }else if(buttonValue == "%"){
                            double numDisplay = Double.parseDouble(displayLabel.getText());
                            numDisplay /= 100;
                            displayLabel.setText(makeNonDecimal(numDisplay));
                        }

                    }

                    else if(buttonValue.contains("sqrt")){
                        double number = Double.parseDouble(displayLabel.getText());
                        Double sqrtNum = Math.sqrt(number);
                        String StringSqrtNum = Double.toString(sqrtNum);
                        displayLabel.setText(StringSqrtNum);

                    }
                    else {
                        if (buttonValue == "."){
                            if(!displayLabel.getText().contains(buttonValue)){
                                displayLabel.setText(displayLabel.getText() + buttonValue);
                            }
                        }

                        else if ("0123456789".contains(buttonValue)){
                            if (displayLabel.getText() == "0"){
                                displayLabel.setText(buttonValue);
                            }
                            else {
                                displayLabel.setText(displayLabel.getText() + buttonValue);
                            }
                        }

                    }
                }
            });
        }
    }

    String makeNonDecimal(double num){
        if (num % 1 == 0){
            return Integer.toString((int)num);
        }
        return Double.toString(num);


    }
}
