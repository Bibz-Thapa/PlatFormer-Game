package gamestates;

import entities.Player;
import levels.LevelManager;
import main.Game;
import ui.PausedOverlay;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Playing extends State implements Statemethods{
    private Player player;
    private LevelManager levelManager;
    private PausedOverlay pausedOverlay;
    private boolean paused = false;


    public Playing(Game game) {
        super(game);
        initClasses();
    }

    private void initClasses() {
        levelManager = new LevelManager(game);
        player = new Player(200, 200,(int)(64*Game.SCALE),(int)(40*Game.SCALE));
        player.loadlvlData(levelManager.getCurrentLevel().getLvlData());
        pausedOverlay = new PausedOverlay(this);
    }




    @Override
    public void update() {
        levelManager.update();
        player.update();

        pausedOverlay.update();

    }

    @Override
    public void draw(Graphics g) {
        levelManager.draw(g);
        player.render(g);
        pausedOverlay.draw(g);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1)
            player.setAttacking(true);

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (paused)
            pausedOverlay.mousePressed(e);

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (paused)
            pausedOverlay.mouseReleased(e);

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (paused)
            pausedOverlay.mouseMoved(e);

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {

            case KeyEvent.VK_A:
                player.setLeft(true);
                break;
            case KeyEvent.VK_D:
                player.setRight(true);
                break;
            case KeyEvent.VK_SPACE:
                player.setJump(true);
                break;
            case KeyEvent.VK_BACK_SPACE:
                Gamestate.state = Gamestate.MENU;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                player.setLeft(false);
                break;
            case KeyEvent.VK_D:
                player.setRight(false);
                break;
            case KeyEvent.VK_SPACE:
                player.setJump(false);
                break;
        }
    }

    public void unpauseGame(){
        paused = false;
    }

    public void windowFocusLost() {
        player.resetDirBooleans();
    }

    public Player getPlayer() {
        return player;
    }
}
