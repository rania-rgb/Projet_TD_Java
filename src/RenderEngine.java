import javax.swing.*;
import java.awt.*;
import java.lang.reflect.*;
import java.util.ArrayList;

public class RenderEngine extends JPanel implements Engine{
    private ArrayList<Displayable> renderList;

    public RenderEngine(JFrame jFrame) {
        renderList = new ArrayList<>();
        jFrame.add(this);
    }

    public void addToRenderList(Displayable displayable){
        if (!renderList.contains(displayable)){
            renderList.add(displayable);
        }
    }

    public void addToRenderList(ArrayList<Displayable> displayable){
        if (!renderList.contains(displayable)){
            renderList.addAll(displayable);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Displayable renderObject:renderList) {
            renderObject.draw(g);
        }
    }

    @Override
    public void update(){

        this.repaint();
    }


}
