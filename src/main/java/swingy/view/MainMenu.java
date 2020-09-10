package swingy.view;

import swingy.controller.ActionEngine;
import swingy.model.*;
import swingy.view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MainMenu extends JFrame implements ActionListener{

    private JPanel          Mainb;
    private JPanel          Maintitle;
    private JLabel          title;
    private JPanel          Mainbtns;
    private JButton         loadGame, newGame;
    private ActionEngine    game;
    BufferedImage myPicture = ImageIO.read(new File("src\\main\\java\\swingy\\Loli.png"));
    JLabel picLabel = new JLabel(new ImageIcon(myPicture));
    JLabel picLabel2 = new JLabel(new ImageIcon(myPicture));


    public MainMenu(ActionEngine gameEngine) throws IOException {

        this.game = gameEngine;
        this.setTitle("World of Anime");
        this.setSize(450, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(new GridLayout());


        Mainb = new JPanel();
        Maintitle = new JPanel();
        Mainbtns = new JPanel();
        title = new JLabel("World Of Anime");
        loadGame = new JButton("Load Game");
        newGame = new JButton("New Game");

        setLayout(new GridLayout(3, 1));
        Mainb.setLayout(new GridLayout(1,1));
        Maintitle.setLayout(new GridLayout(1, 3));
        Mainbtns.setLayout(new GridLayout(2, 3));

        title.setHorizontalAlignment(SwingConstants.CENTER);

        Maintitle.add(picLabel);
        Maintitle.add(title);
        Maintitle.add(picLabel2);
        Mainbtns.add(new JLabel(" "));
        Mainbtns.add(newGame);
        Mainbtns.add(new JLabel(" "));
        Mainbtns.add(new JLabel(" "));
        Mainbtns.add(loadGame);
        Mainbtns.add(new JLabel(" "));
        this.add(Maintitle);
        this.add(Mainbtns);
        Mainb.add(new JLabel(" "));
        Mainb.add(new JLabel(" "));
        this.add(Mainb);

        loadGame.addActionListener(this);
        newGame.addActionListener(this);


        this.setResizable(false);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object ae = e.getSource();

        if (ae.equals(this.newGame)){
            System.out.println("NewGame selected");
            setVisible(false); //you can't see me!
            dispose(); //Destroy the JFrame object
            new CharCreation(this.game);
        }
        else if (ae.equals(this.loadGame)){
            System.out.println("loading Game");
            if (game.getGameData().checkLoad()) {
                game.getGameData().loadHero();
                game.init();
                setVisible(false); //you can't see me!
                dispose(); //Destroy the JFrame object
                new MapFrame(game);
            }
        }
    }

}
