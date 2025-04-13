import  javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {

    int frameWidth = 360;
    int frameHeight = 640;

    Image backgroundImg;
    Image burungImg;
    Image PipaBawahImg;
    Image PipaAtasImg;

    int playerStartPosX = frameWidth/8;
    int playerStartPosY = frameHeight/2;
    int playerWidth = 64;
    int playerHeight = 54;

    Player player;

    int pipeStartPosX = frameWidth;

    int pipeStartPosy = 0;

    int pipeWidth = 64;

    int pipeHeight = 512;

    ArrayList<Pipe> pipes;

    Timer pipesCooldown;

    private JLabel scoreLabel;
    private int score = 0;
    private boolean isGameOver = false;
    private ArrayList<Integer> passedPipes = new ArrayList<>();

    int gravity = 1;
    public FlappyBird(){
        setPreferredSize(new Dimension(360, 640));
        setFocusable(true);
        addKeyListener(this);
        setLayout(null);

        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setBounds(10, 10, 150, 30);
        add(scoreLabel);

        backgroundImg = new ImageIcon(getClass().getResource("Assets/background.png")).getImage();
        burungImg = new ImageIcon(getClass().getResource("Assets/bombardino_crocodilo.png")).getImage();
        PipaAtasImg = new ImageIcon(getClass().getResource("Assets/upperPipe.png")).getImage();
        PipaBawahImg = new ImageIcon(getClass().getResource("Assets/lowerPipe.png")).getImage();

        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, burungImg);
        pipes = new ArrayList<Pipe>();
        passedPipes = new ArrayList<Integer>();

        pipesCooldown = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("pipe");
                placePipes();
            }
        });

        pipesCooldown.start();
    }

    public void move()
    {
        player.setVelocityY(player.getVelocityY() + gravity);
        player.setPosY(player.getPosY() + player.getVelocityY());
        player.setPosY(Math.max(player.getPosY(), 0));

        for (int i = 0; i < pipes.size(); i++)
        {
            Pipe pipe = pipes.get(i);
            pipe.setPosX(pipe.getPosX() + pipe.getVelocityX());
        }
    }

    public void placePipes(){
        int min = -pipeHeight + 100;
        int max = -100;
        int randPosY = min + (int)(Math.random() * (max - min));

        int openingSpace = frameHeight / 4;

        Pipe upperPipe = new Pipe(pipeStartPosX, randPosY, pipeWidth, pipeHeight, PipaAtasImg);
        upperPipe.setVelocityX(-5);
        pipes.add(upperPipe);

        Pipe lowerPipe = new Pipe(pipeStartPosX, randPosY + pipeHeight + openingSpace, pipeWidth, pipeHeight, PipaBawahImg);
        lowerPipe.setVelocityX(-5);
        pipes.add(lowerPipe);

        System.out.println("UpperPipe: x=" + pipeStartPosX + " y=" + randPosY);
        System.out.println("LowerPipe: x=" + pipeStartPosX + " y=" + (randPosY + pipeHeight + openingSpace));
    }

    private boolean checkCollision() {
        Rectangle birdHitbox = new Rectangle(player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight());

        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            Rectangle pipeHitbox = new Rectangle(
                    pipe.getPosX(),
                    pipe.getPosY(),
                    pipe.getWidth(),
                    pipe.getHeight()
            );

            if (birdHitbox.intersects(pipeHitbox)) {
                return true;
            }
        }


        if (player.getPosY() + player.getHeight() >= frameHeight) {
            return true;
        }

        return false;
    }

    private void updateScore() {

        for (int i = 0; i < pipes.size(); i += 2) {
            Pipe pipe = pipes.get(i);
            int pipeId = i / 2;

            if (player.getPosX() > pipe.getPosX() + pipe.getWidth() && !passedPipes.contains(pipeId)) {
                passedPipes.add(pipeId);
                score++;
                scoreLabel.setText("Score: " + score);
                System.out.println("Score: " + score);
            }
        }
    }

    private void gameOver() {

        pipesCooldown.stop();

        isGameOver = true;

        JOptionPane.showMessageDialog(this, "Game Over! Score: " + score + "\nPress 'R' to restart", "Flappy Bird", JOptionPane.INFORMATION_MESSAGE);
    }

    private void resetGame() {

        player.setPosX(playerStartPosX);
        player.setPosY(playerStartPosY);
        player.setVelocityY(0);

        score = 0;
        scoreLabel.setText("Score: 0");

        pipes.clear();
        passedPipes.clear();

        isGameOver = false;

        pipesCooldown.start();
    }

    public void draw(Graphics g){
        g.drawImage(backgroundImg, 0, 0, frameWidth, frameHeight, null);

        g.drawImage(player.getImage(), player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight(), null);

        for (int i = 0; i < pipes.size(); i++)
        {
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.getImage(), pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight(), null);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);

        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 10, 30);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!isGameOver) {
            move();
            updateScore();

            if (checkCollision()) {
                gameOver();
            }

            repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (!isGameOver) {
                player.setVelocityY(-10);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_R) {
            if (isGameOver) {
                resetGame();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }
}