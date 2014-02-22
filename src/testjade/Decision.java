package testjade;

/**
 * Receive the light value from the Sensor Agent Make a decision(turn on/off)
 * according to the light value and some rules Send the decision to the Action
 * Agent
 */
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class Decision extends Agent {

    @Override
    protected void setup() {
        this.addBehaviour(new decisionbehaviour(this));
    }
}

class decisionbehaviour extends CyclicBehaviour {

    Decision yy;
    String actioncommand = "";
    int mylight;

    public decisionbehaviour(Decision a) {
        yy = a;
    }

    @Override
    public void action() {
        ACLMessage mm = yy.receive();
        if (mm != null) {
            mylight = Integer.parseInt(mm.getContent());
        }
        if (mylight > 300) {
            actioncommand = "off";
        } else {
            actioncommand = "on";
        }
        System.out.println("the current light is:" + mylight + ";" + "the light should be :" + actioncommand);
        ACLMessage mycommand = new ACLMessage(ACLMessage.INFORM);
        mycommand.addReceiver(new AID("actionagent", AID.ISLOCALNAME));
        mycommand.setContent(actioncommand);
        yy.send(mycommand);
        block();
    }
}