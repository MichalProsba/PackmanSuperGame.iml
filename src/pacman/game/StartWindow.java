package pacman.game;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.util.List;

public class StartWindow extends JFrame implements ActionListener {
    // Window
    private static final int WINDOW_SIZE = 500;

    // Alert Window
    private static final int ALERT_WINDOW_X_SIZE = 400;
    private static final int ALERT_WINDOW_Y_SIZE = 200;

    // AlertWindow Ok button
    private static final int OK_BUTTON_WIDTH = 100;
    private static final int OK_BUTTON_HEIGHT = 50;
    private static final int OK_BUTTON_X_POSITION = (ALERT_WINDOW_X_SIZE-OK_BUTTON_WIDTH)/2;
    private static final int OK_BUTTON_Y_POSITION = ((ALERT_WINDOW_Y_SIZE-OK_BUTTON_HEIGHT)/2)+20;

    // Alert Label
    private static final int ALERT_LABEL_WIDTH = 320;
    private static final int ALERT_LABEL_HEIGHT = 50;
    private static final int ALERT_LABEL_X_POSITION = (ALERT_WINDOW_X_SIZE-ALERT_LABEL_WIDTH)/2;
    private static final int ALERT_LABEL_Y_POSITION = 10;

    // Button
    private static final int BUTTON_GAP = 10;
    private static final int BUTTON_WIDTH = 100;
    private static final int BUTTON_HEIGHT = 50;
    private static final int BUTTON_COUNT = 4;
    private static final int BUTTON_X_POSITION = (WINDOW_SIZE-BUTTON_WIDTH)/2;
    private static final int BUTTON_Y_POSITION = 150;

    // Pacman Button
    private static final int PACMAN_BUTTON_WIDTH = 70;
    private static final int PACMAN_BUTTON_HEIGHT = 70;
    private static final int PACMAN_BUTTON_COUNT = 3;
    private static final int PACMAN_BUTTON_X_POSITION = (WINDOW_SIZE - (PACMAN_BUTTON_WIDTH*PACMAN_BUTTON_COUNT))/(PACMAN_BUTTON_COUNT+1);
    private static final int PACMAN_BUTTON_Y_POSITION = 135;
    private static final int PACMAN_BUTTON_Y_GAP = 10;
    private static final int PACMAN_BUTTON_X_GAP = PACMAN_BUTTON_X_POSITION + PACMAN_BUTTON_WIDTH;

    // Speed Button
    private static final int SPEED_BUTTON_WIDTH = 70;
    private static final int SPEED_BUTTON_HEIGHT = 70;
    private static final int SPEED_BUTTON_COUNT = 3;
    private static final int SPEED_BUTTON_X_POSITION = (WINDOW_SIZE - (SPEED_BUTTON_WIDTH*SPEED_BUTTON_COUNT))/(SPEED_BUTTON_COUNT+1);
    private static final int SPEED_BUTTON_Y_POSITION = 360;
    private static final int SPEED_BUTTON_Y_GAP = 10;
    private static final int SPEED_BUTTON_X_GAP = SPEED_BUTTON_X_POSITION + SPEED_BUTTON_WIDTH;

    // Start and Return StartWindow Button
    private static final int SR_BUTTON_WIDTH = 100;
    private static final int SR_BUTTON_HEIGHT = 50;
    private static final int SR_BUTTON_COUNT = 2;
    private static final int SR_BUTTON_X_POSITION = (WINDOW_SIZE - (SR_BUTTON_WIDTH*SR_BUTTON_COUNT))/(SR_BUTTON_COUNT+1);
    private static final int SR_BUTTON_Y_POSITION = 440;
    private static final int SR_BUTTON_Y_GAP = 10;
    private static final int SR_BUTTON_X_GAP = SR_BUTTON_X_POSITION + SR_BUTTON_WIDTH;

    // Map Button
    private static final int MAP_BUTTON_WIDTH = 140;
    private static final int MAP_BUTTON_HEIGHT = 80;
    private static final int MAP_BUTTON_COUNT = 3;
    private static final int MAP_BUTTON_X_POSITION = (WINDOW_SIZE - (MAP_BUTTON_WIDTH*MAP_BUTTON_COUNT))/(MAP_BUTTON_COUNT+1);
    private static final int MAP_BUTTON_Y_POSITION = 240;
    private static final int MAP_BUTTON_Y_GAP = 10;
    private static final int MAP_BUTTON_X_GAP = MAP_BUTTON_X_POSITION + MAP_BUTTON_WIDTH;

    // Title Label
    private static final int LABEL_WIDTH = 80;
    private static final int LABEL_HEIGHT = 40;
    private static final int LABEL_X_POSITION = (WINDOW_SIZE-LABEL_WIDTH)/2;
    private static final int LABEL_Y_POSITION = 10;

    // Stats Label
    private static final int STATS_LABEL_WIDTH = 400;
    private static final int STATS_LABEL_HEIGHT = 20;
    private static final int STATS_LABEL_X_POSITION = (WINDOW_SIZE-STATS_LABEL_WIDTH)/2;
    private static final int STATS_LABEL_Y_POSITION = 30;
    private static final int STATS_LABEL_GAP = 10;

    // Choose Label
    private static final int CHOOSE_LABEL_WIDTH = 400;
    private static final int CHOOSE_LABEL_HEIGHT = 20;
    private static final int CHOOSE_LABEL_COUNT = 4;
    private static final int CHOOSE_LABEL_X_POSITION = (WINDOW_SIZE-CHOOSE_LABEL_WIDTH)/2;
    private static final int CHOOSE_LABEL_Y_POSITION = 10;
    private static final int CHOOSE_LABEL_GAP = 80;

    // Help Label
    private static final int HELP_LABEL_WIDTH = 60;
    private static final int HELP_LABEL_HEIGHT = 20;
    private static final int HELP_LABEL_COUNT = 3;
    private static final int HELP_LABEL_X_POSITION = (WINDOW_SIZE-HELP_LABEL_WIDTH)/2;
    private static final int HELP_LABEL_Y_POSITION = 10;
    private static final int HELP_LABEL_GAP = 80;

    // Help TextArea
    private static final int HELP_TEXTAREA_WIDTH = 300;
    private static final int HELP_TEXTAREA_HEIGHT = 360;
    private static final int HELP_TEXTAREA_X_POSITION = (WINDOW_SIZE-HELP_TEXTAREA_WIDTH)/2;
    private static final int HELP_TEXTAREA_Y_POSITION = ((WINDOW_SIZE-HELP_TEXTAREA_HEIGHT)/2);

    // Nickname TextField
    private static final int NICKNAME_WIDTH = 400;
    private static final int NICKNAME_HEIGHT = 50;
    private static final int NICKNAME_X_POSITION = (WINDOW_SIZE-NICKNAME_WIDTH)/2;
    private static final int NICKNAME_Y_POSITION = 44;

    // Colors
    private static final Color backgroundColor = new Color(0,0,0);
    private static final Color textColor = new Color(255,255,0);
    private static final Color chosenButtonBackgroundColor = new Color(0,72,251);

    // Fonts
    private static final Font menuFont = new Font("Arial", Font.BOLD, 18);
    private static final Font helpFont = new Font("Serif", Font.PLAIN, 16);

    // Character icons
    private static final ImageIcon classic = new ImageIcon("Images/PacmanClassic/left.gif");
    private static final ImageIcon girl = new ImageIcon("Images/PacmanGirl/left.gif");
    private static final ImageIcon boy = new ImageIcon("Images/PacmanBoy/left.gif");

    // Map icons
    private static final ImageIcon map01 = new ImageIcon("Images/Maps/Map01.png");
    private static final ImageIcon map02 = new ImageIcon("Images/Maps/Map02.png");
    private static final ImageIcon map03 = new ImageIcon("Images/Maps/Map03.png");

    // Speed icons
    private static final ImageIcon slow = new ImageIcon("Images/Speed/Slow.jpg");
    private static final ImageIcon medium = new ImageIcon("Images/Speed/Medium.png");
    private static final ImageIcon fast = new ImageIcon("Images/Speed/Fast.png");

    // Titlebar Image
    private static final Image pacmanImage = Toolkit.getDefaultToolkit().getImage("Images/Speed/Medium.PNG");

    // chosen game parameters
    private int chosenCharacter = 0;
    private int chosenMap = 0;
    private int chosenSpeed = 0;

    // Button lists
    private JButton[] characterButtonList = new JButton[PACMAN_BUTTON_COUNT];
    private JButton[] mapButtonList = new JButton[MAP_BUTTON_COUNT];
    private JButton[] speedButtonList = new JButton[SPEED_BUTTON_COUNT];

    // Nickname TextField
    private JTextField nickname = new JTextField("Player");

    // Alert Window
    JFrame alertWindow;

    private StartWindow(){
        CreateContent();
    }

    private void CreateContent(){
        this.getContentPane().removeAll();

        // Create Label
        Label title = new Label("PACMAN");
        title.setBounds(LABEL_X_POSITION,LABEL_Y_POSITION,LABEL_WIDTH,LABEL_HEIGHT);
        title.setFont(menuFont);
        title.setForeground(textColor);

        JButton[] menuButtons = new JButton[4];

        for(int i = 0; i < BUTTON_COUNT; i++){
            JButton button = new JButton();
            button.setBounds(BUTTON_X_POSITION,BUTTON_Y_POSITION+(i*(BUTTON_HEIGHT+BUTTON_GAP)),BUTTON_WIDTH,BUTTON_HEIGHT);
            button.setBackground(backgroundColor);
            button.setForeground(textColor);
            button.setBorderPainted(true);
            button.setFont(menuFont);
            button.addActionListener(this);
            menuButtons[i] = button;
        }

        menuButtons[0].setText("Start");
        menuButtons[1].setText("Stats");
        menuButtons[2].setText("Help");
        menuButtons[3].setText("Exit");

        // Add everything to window
        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);
        contentPane.add(title);
        for(int i = 0; i < BUTTON_COUNT; i++){
            contentPane.add(menuButtons[i]);
        }
        this.revalidate();
        this.repaint();
    }

    private static void CreateGUI(){
        // Create Window
        JFrame startWindow = new StartWindow();
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - WINDOW_SIZE) / 2);
        int y = (int) ((dimension.getHeight() - WINDOW_SIZE) / 2);
        startWindow.setBounds(x, y,WINDOW_SIZE, WINDOW_SIZE);
        startWindow.getContentPane().setBackground(backgroundColor);
        startWindow.setUndecorated (true);
        //startWindow.setShape(new RoundRectangle2D.Double(0, 0, WINDOW_SIZE, WINDOW_SIZE, 50, 50));
        startWindow.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, chosenButtonBackgroundColor));
        startWindow.setIconImage(pacmanImage);
        startWindow.setVisible(true);
    }

    public static void RunGame(){
        CreateGUI();
    }

    private void ShowStats(){
        this.getContentPane().removeAll();

        // Get Stats
        String[] stats = StatisticsService.getStats();

        // Title label
        Label statsTitleLabel = new Label("Statistics");
        statsTitleLabel.setBounds(LABEL_X_POSITION,LABEL_Y_POSITION,LABEL_WIDTH,LABEL_HEIGHT);
        statsTitleLabel.setFont(menuFont);
        statsTitleLabel.setForeground(textColor);
        this.getContentPane().add(statsTitleLabel);

        // Create labels
        for (int i = 0; i < stats.length; i++){
            Label statsLabel = new Label(stats[i]);
            statsLabel.setBounds(STATS_LABEL_X_POSITION,STATS_LABEL_Y_POSITION+((i+1)*(STATS_LABEL_HEIGHT+STATS_LABEL_GAP)),STATS_LABEL_WIDTH,STATS_LABEL_HEIGHT);
            statsLabel.setFont(menuFont);
            statsLabel.setForeground(textColor);
            this.getContentPane().add(statsLabel);
        }

        // Create return button
        JButton button = new JButton("Return");
        button.setBounds(BUTTON_X_POSITION,BUTTON_Y_POSITION+(4*(BUTTON_HEIGHT+BUTTON_GAP)),BUTTON_WIDTH,BUTTON_HEIGHT);
        button.setBackground(backgroundColor);
        button.setForeground(textColor);
        button.setBorderPainted(true);
        button.setFont(menuFont);
        button.addActionListener(this);


        this.getContentPane().add(button);
        this.revalidate();
        this.repaint();
    }

    private void ChooseGameParameters(){
        this.getContentPane().removeAll();

        // Creating labels
        Label[] chooseLabelList = new Label[CHOOSE_LABEL_COUNT];
        for (int i = 0; i < CHOOSE_LABEL_COUNT; i++){
            // Create Label
            Label statsLabel = new Label();
            statsLabel.setBounds(CHOOSE_LABEL_X_POSITION,CHOOSE_LABEL_Y_POSITION+(i*(CHOOSE_LABEL_HEIGHT+CHOOSE_LABEL_GAP)),CHOOSE_LABEL_WIDTH,CHOOSE_LABEL_HEIGHT);
            statsLabel.setFont(menuFont);
            statsLabel.setForeground(textColor);
            chooseLabelList[i] = statsLabel;
        }
        chooseLabelList[0].setText("Type in your Nickname:");
        chooseLabelList[1].setText("Choose your Character:");
        chooseLabelList[2].setText("Choose map:");
        chooseLabelList[3].setText("Choose difficulty:");
        chooseLabelList[3].setBounds(CHOOSE_LABEL_X_POSITION,CHOOSE_LABEL_Y_POSITION+(3*(CHOOSE_LABEL_HEIGHT+CHOOSE_LABEL_GAP))+20,CHOOSE_LABEL_WIDTH,CHOOSE_LABEL_HEIGHT);
        for (int i = 0; i < CHOOSE_LABEL_COUNT; i++){
            this.getContentPane().add(chooseLabelList[i]);
        }

        // Creating nickname TextField
        nickname.setBounds(NICKNAME_X_POSITION,NICKNAME_Y_POSITION,NICKNAME_WIDTH,NICKNAME_HEIGHT);
        nickname.setBackground(backgroundColor);
        nickname.setForeground(textColor);
        nickname.setBorder(new CompoundBorder(new EmptyBorder(3, 3, 4, 4), new LineBorder(chosenButtonBackgroundColor, 2)));
        nickname.setFont(menuFont);
        this.getContentPane().add(nickname);

        // Creating character buttons
        for (int i = 0; i < PACMAN_BUTTON_COUNT; i++){
            JButton button = new JButton();
            button.setBounds(PACMAN_BUTTON_X_POSITION + (i*PACMAN_BUTTON_X_GAP),PACMAN_BUTTON_Y_POSITION,PACMAN_BUTTON_WIDTH,PACMAN_BUTTON_HEIGHT);
            button.setBackground(backgroundColor);
            button.setForeground(textColor);
            button.setBorderPainted(true);
            button.setFont(menuFont);
            button.addActionListener(this);
            characterButtonList[i] = button;
        }
        characterButtonList[0].setIcon(classic);
        characterButtonList[1].setIcon(girl);
        characterButtonList[2].setIcon(boy);
        characterButtonList[0].setActionCommand("Classic");
        characterButtonList[1].setActionCommand("Girl");
        characterButtonList[2].setActionCommand("Boy");
        for (int i = 0; i < PACMAN_BUTTON_COUNT; i++){
            this.getContentPane().add(characterButtonList[i]);
        }

        // Creating map buttons
        for (int i = 0; i < MAP_BUTTON_COUNT; i++){
            JButton button = new JButton();
            button.setBounds(MAP_BUTTON_X_POSITION + (i*MAP_BUTTON_X_GAP),MAP_BUTTON_Y_POSITION,MAP_BUTTON_WIDTH,MAP_BUTTON_HEIGHT);
            button.setBackground(backgroundColor);
            button.setForeground(textColor);
            button.setBorderPainted(true);
            button.setFont(menuFont);
            button.addActionListener(this);
            mapButtonList[i] = button;
        }
        mapButtonList[0].setIcon(map01);
        mapButtonList[1].setIcon(map02);
        mapButtonList[2].setIcon(map03);
        mapButtonList[0].setActionCommand("Map01");
        mapButtonList[1].setActionCommand("Map02");
        mapButtonList[2].setActionCommand("Map03");
        for (int i = 0; i < MAP_BUTTON_COUNT; i++){
            this.getContentPane().add(mapButtonList[i]);
        }

        // Create speed buttons
        for (int i = 0; i < SPEED_BUTTON_COUNT; i++){
            JButton button = new JButton();
            button.setBounds(SPEED_BUTTON_X_POSITION + (i*SPEED_BUTTON_X_GAP),SPEED_BUTTON_Y_POSITION,SPEED_BUTTON_WIDTH,SPEED_BUTTON_HEIGHT);
            button.setBackground(backgroundColor);
            button.setForeground(textColor);
            button.setBorderPainted(true);
            button.setFont(menuFont);
            button.addActionListener(this);
            speedButtonList[i] = button;
        }
        speedButtonList[0].setIcon(slow);
        speedButtonList[1].setIcon(medium);
        speedButtonList[2].setIcon(fast);
        speedButtonList[0].setActionCommand("Slow");
        speedButtonList[1].setActionCommand("Medium");
        speedButtonList[2].setActionCommand("Fast");
        for (int i = 0; i < SPEED_BUTTON_COUNT; i++){
            this.getContentPane().add(speedButtonList[i]);
        }

        // Create text buttons
        JButton[] buttonList = new JButton[SR_BUTTON_COUNT];
        for (int i = 0; i < SR_BUTTON_COUNT; i++){
            JButton button = new JButton();
            button.setBounds(SR_BUTTON_X_POSITION + (i*SR_BUTTON_X_GAP),SR_BUTTON_Y_POSITION,SR_BUTTON_WIDTH,SR_BUTTON_HEIGHT);
            button.setBackground(backgroundColor);
            button.setForeground(textColor);
            button.setBorderPainted(true);
            button.setFont(menuFont);
            button.addActionListener(this);
            buttonList[i] = button;
        }
        buttonList[0].setText("Return");
        buttonList[1].setText("Play");
        for (int i = 0; i < SR_BUTTON_COUNT; i++){
            this.getContentPane().add(buttonList[i]);
        }

        setChosenCharacter("Classic");
        setChosenMap("Map01");
        setChosenSpeed("Medium");

        this.revalidate();
        this.repaint();
    }

    private void createHelp(){
        this.getContentPane().removeAll();

        // Title label
        Label statsTitleLabel = new Label("Help");
        statsTitleLabel.setBounds(HELP_LABEL_X_POSITION,HELP_LABEL_Y_POSITION,HELP_LABEL_WIDTH,HELP_LABEL_HEIGHT);
        statsTitleLabel.setFont(menuFont);
        statsTitleLabel.setForeground(textColor);
        this.getContentPane().add(statsTitleLabel);

        // Create return button
        JButton button = new JButton("Return");
        button.setBounds(BUTTON_X_POSITION,BUTTON_Y_POSITION+(int)(4.5*(BUTTON_HEIGHT+BUTTON_GAP)),BUTTON_WIDTH,BUTTON_HEIGHT);
        button.setBackground(backgroundColor);
        button.setForeground(textColor);
        button.setBorderPainted(true);
        button.setFont(menuFont);
        button.addActionListener(this);
        this.getContentPane().add(button);

        JTextArea textArea = new JTextArea(
                "Controls: \n"+
                "←  -  go left \n" +
                "→  -  go right \n" +
                "↑  -  go up \n" +
                "↓  -  go down \n" +
                "\n" +
                "Logic: \n" +
                "Your goal is to collect all white dots," +
                " before the four ghosts (Blinky, Pinky, Inky and Clyde) can kill you. " +
                "You have 3 lives which are reduced by one everytime you have contact with a ghost.\n\n" +
                "Don't let them get you! :)"
        );
        textArea.setFont(helpFont);
        textArea.setBounds(HELP_TEXTAREA_X_POSITION,HELP_TEXTAREA_Y_POSITION,HELP_TEXTAREA_WIDTH,HELP_TEXTAREA_HEIGHT);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(backgroundColor);
        textArea.setForeground(textColor);
        this.getContentPane().add(textArea);

        this.revalidate();
        this.repaint();
    }

    private void setChosenCharacter(String who){
        try{
            if (who.equals("Classic")) {
                chosenCharacter = 1;
                characterButtonList[0].setBackground(chosenButtonBackgroundColor);
                characterButtonList[1].setBackground(backgroundColor);
                characterButtonList[2].setBackground(backgroundColor);
            }
            else if (who.equals("Girl")) {
                chosenCharacter = 2;
                characterButtonList[0].setBackground(backgroundColor);
                characterButtonList[1].setBackground(chosenButtonBackgroundColor);
                characterButtonList[2].setBackground(backgroundColor);
            }
            else if (who.equals("Boy")) {
                chosenCharacter = 3;
                characterButtonList[0].setBackground(backgroundColor);
                characterButtonList[1].setBackground(backgroundColor);
                characterButtonList[2].setBackground(chosenButtonBackgroundColor);
            }else{
                throw new Exception("No such Character");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void setChosenMap(String which){
        try{
            if (which.equals("Map01")) {
                chosenMap = 1;
                mapButtonList[0].setBackground(chosenButtonBackgroundColor);
                mapButtonList[1].setBackground(backgroundColor);
                mapButtonList[2].setBackground(backgroundColor);
            }
            else if (which.equals("Map02")) {
                chosenMap = 2;
                mapButtonList[0].setBackground(backgroundColor);
                mapButtonList[1].setBackground(chosenButtonBackgroundColor);
                mapButtonList[2].setBackground(backgroundColor);
            }
            else if (which.equals("Map03")) {
                chosenMap = 3;
                mapButtonList[0].setBackground(backgroundColor);
                mapButtonList[1].setBackground(backgroundColor);
                mapButtonList[2].setBackground(chosenButtonBackgroundColor);
            }else{
                throw new Exception("No such Map");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void setChosenSpeed(String which){
        try{
            if (which.equals("Slow")) {
                chosenSpeed = 1;
                speedButtonList[0].setBackground(chosenButtonBackgroundColor);
                speedButtonList[1].setBackground(backgroundColor);
                speedButtonList[2].setBackground(backgroundColor);
            }
            else if (which.equals("Medium")) {
                chosenSpeed = 2;
                speedButtonList[0].setBackground(backgroundColor);
                speedButtonList[1].setBackground(chosenButtonBackgroundColor);
                speedButtonList[2].setBackground(backgroundColor);
            }
            else if (which.equals("Fast")) {
                chosenSpeed = 3;
                speedButtonList[0].setBackground(backgroundColor);
                speedButtonList[1].setBackground(backgroundColor);
                speedButtonList[2].setBackground(chosenButtonBackgroundColor);
            }else{
                throw new Exception("No such Speed");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void startGame() {
        if(!nickname.getText().contains(":")){
            Player currentPlayer = new Player(nickname.getText());
            PacmanGame pac = new PacmanGame(chosenMap, chosenSpeed, chosenCharacter, currentPlayer);
            pac.setVisible(true);
            pac.setTitle("Pacman");
            pac.setSize(1880,1020);
            pac.setIconImage(pacmanImage);
            pac.setDefaultCloseOperation(EXIT_ON_CLOSE);
            pac.setLocationRelativeTo(null);
            this.dispose();
        }else{
            this.setEnabled(false);
            alertWindow = new JFrame("Alert");
            Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
            int x = (int) ((dimension.getWidth() - ALERT_WINDOW_X_SIZE) / 2);
            int y = (int) ((dimension.getHeight() - ALERT_WINDOW_Y_SIZE) / 2);
            alertWindow.setBounds(x, y,ALERT_WINDOW_X_SIZE, ALERT_WINDOW_Y_SIZE);
            alertWindow.getContentPane().setBackground(backgroundColor);
            alertWindow.setUndecorated (true);
            alertWindow.getContentPane().setLayout(null);
            alertWindow.setIconImage(pacmanImage);
            alertWindow.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, chosenButtonBackgroundColor));

            // Create alert label
            Label statsTitleLabel = new Label("You cant use \":\" in your Nickname");
            statsTitleLabel.setBounds(ALERT_LABEL_X_POSITION,ALERT_LABEL_Y_POSITION,ALERT_LABEL_WIDTH,ALERT_LABEL_HEIGHT);
            statsTitleLabel.setFont(menuFont);
            statsTitleLabel.setForeground(textColor);
            alertWindow.getContentPane().add(statsTitleLabel);

            // Create ok button
            JButton okButton = new JButton("Ok");
            okButton.setBounds(OK_BUTTON_X_POSITION,OK_BUTTON_Y_POSITION,OK_BUTTON_WIDTH,OK_BUTTON_HEIGHT);
            okButton.setBackground(backgroundColor);
            okButton.setForeground(textColor);
            okButton.setBorderPainted(true);
            okButton.setFont(menuFont);
            okButton.addActionListener(this);
            alertWindow.getContentPane().add(okButton);

            alertWindow.setVisible(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals("Start")) {
            ChooseGameParameters();
        }
        else if (action.equals("Stats")) {
            ShowStats();
        }
        else if (action.equals("Help")) {
            createHelp();
        }
        else if (action.equals("Exit")) {
            this.dispose();
        }
        else if (action.equals("Return")) {
            CreateContent();
        }
        else if (action.equals("Classic")) {
            setChosenCharacter("Classic");
        }
        else if (action.equals("Girl")) {
            setChosenCharacter("Girl");
        }
        else if (action.equals("Boy")) {
            setChosenCharacter("Boy");
        }
        else if (action.equals("Map01")) {
            setChosenMap("Map01");
        }
        else if (action.equals("Map02")) {
            setChosenMap("Map02");
        }
        else if (action.equals("Map03")) {
            setChosenMap("Map03");
        }
        else if (action.equals("Play")) {
            startGame();
        }
        else if (action.equals("Slow")) {
            setChosenSpeed("Slow");
        }
        else if (action.equals("Medium")) {
            setChosenSpeed("Medium");
        }
        else if (action.equals("Fast")) {
            setChosenSpeed("Fast");
        }
        else if (action.equals("Ok")) {
            alertWindow.dispose();
            this.setEnabled(true);
            this.toFront();
        }
    }
}
