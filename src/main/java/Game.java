import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {

    private Terminal terminal;
    private Screen screen;
    private KeyStroke key;

    private int x = 10;
    private int y = 10;


    public Game() throws IOException {
        terminal = new DefaultTerminalFactory().createTerminal();
        screen = new TerminalScreen(terminal);

    }

    private void draw() throws IOException {
        screen.clear();
        screen.setCharacter(x, y, new TextCharacter('X'));
        screen.refresh();
    }

    public void run(){
        while(true) {
            try{
                draw();
                if (KeyType.EOF == screen.readInput().getKeyType())
                    break;
                processKey(screen.readInput());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void processKey(KeyStroke key) throws IOException {
        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')
            screen.close();
        else {
            switch (key.getKeyType()) {
                case ArrowUp:
                    moveIt(0, 1);
                    break;
                case ArrowDown:
                    moveIt(0, -1);
                    break;
                case ArrowLeft:
                    moveIt(-1, 0);
                    break;
                case ArrowRight:
                    moveIt(1, 0);
                    break;
            }
        }
    }

    private void moveIt(int x, int y) {
        this.x = this.x + x;
        this.y = this.y + y;
    }
}
