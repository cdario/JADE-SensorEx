/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testjade;

/**
 *
 * @author cesar
 */
import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Action extends Agent {

    ConvertToBlack iii;

    @Override
    protected void setup() {
        iii = new ConvertToBlack(this);
        Behaviour loop = new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage msg = receive();
                String mystr = "off";
                if (msg != null) {
                    mystr = msg.getContent();
                }
                if (mystr.equals("off")) {
                    ImageIcon imageIcon = new ImageIcon("c://x11.jpg");
                    Image image = imageIcon.getImage();
                    iii.changing(image);
                    iii.setMytitle("OFF");
                    iii.repaint();
                }
                if (mystr.equals("on")) {
                    ImageIcon imageIcon = new ImageIcon("c://x11.jpg");
                    Image image = imageIcon.getImage();
                    iii.setImgOriginal(image);
                    iii.setMytitle("ON");
                    iii.repaint();
                }
                block();
            }
        };
        this.addBehaviour(loop);
    }
}
