package testjade;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

/*
*   directly attached to an environmental device or sensor
*   get ambient light value every 5 seconds and
*   send it to the Decision Agent
*/

public class Sensor extends Agent {

    @Override
    protected void setup() {
        this.addBehaviour(new sensorbehaviour(this));
    }
}

class sensorbehaviour extends TickerBehaviour {

    Sensor xx;
    int light = (int) (Math.random() * 1000 + 1);

    ;
public sensorbehaviour(Sensor a) {
        super(a, 5000);
        xx = a;
    }

    @Override
    protected void onTick() {
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(new AID("decision", AID.ISLOCALNAME));
        msg.setContent(String.valueOf(light));
        xx.send(msg);
        light = (int) (Math.random() * 1000 + 1);
    }
}