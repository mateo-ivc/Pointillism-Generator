package artcreator.gui;

import artcreator.creator.CreatorFactory;
import artcreator.creator.port.Creator;
import artcreator.gui.panels.EditorPanel;
import artcreator.gui.panels.ImagePanel;
import artcreator.statemachine.StateMachineFactory;
import artcreator.statemachine.port.Observer;
import artcreator.statemachine.port.State;
import artcreator.statemachine.port.Subject;
import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.TooManyListenersException;

public class CreatorFrame extends JFrame implements Observer {

    private static final long serialVersionUID = 1L;

    private transient Creator creator = CreatorFactory.FACTORY.creator();
    private transient Subject subject = StateMachineFactory.FACTORY.subject();
    private transient Controller controller;

    private BufferedImage image;

    private static final int WIDTH = 600;
    private static final int HEIGHT = 500;

    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout);
    private JPanel topPanel = new JPanel(); // New panel for displaying active state
    private JLabel selectImageLabel = new JLabel("Select Image");
    private JLabel parameterLabel = new JLabel("Parameters");


    public CreatorFrame() throws TooManyListenersException {
        super("ArtCreator");
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        this.subject.attach(this);
        this.controller = new Controller(this, subject, creator);
        this.getContentPane().add(this.mainPanel);

        setupTopPanel();
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(topPanel, BorderLayout.NORTH);
        this.getContentPane().add(mainPanel, BorderLayout.CENTER);

        mainPanel.add(selectImagePanel(), "selectImagePanel");
        mainPanel.add(new EditorPanel(this.controller), "parameterPanel");
    }

    @Override
    public void update(State currentState) {
        // Switch panel based on the current state
        switch (currentState) {
            case State.S.SELECT_IMAGE:
                cardLayout.show(mainPanel, "selectImagePanel");
                break;
            case State.S.EDIT_PARAMETERS:
                cardLayout.show(mainPanel, "parameterPanel");

                if (this.controller.getImage() != null) {
                    //n=1 because it's the second panel getting added
                    JPanel editParameterPanel = (JPanel) mainPanel.getComponent(1);

                    ImagePanel imagePanel = (ImagePanel) editParameterPanel.getComponent(0);
                    imagePanel.setImage(this.controller.getImage());
                }
                break;
            default:
                throw new IllegalStateException("Unknown state: " + currentState);
        }
        updateLabels(currentState);
    }

    private void setupTopPanel() {
        topPanel.setLayout(new FlowLayout());
        topPanel.add(selectImageLabel);
        topPanel.add(parameterLabel);
        updateLabels(State.S.SELECT_IMAGE); // Set initial state
    }

    private void updateLabels(State currentState) {
        selectImageLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        parameterLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        switch (currentState) {
            case State.S.EDIT_PARAMETERS:
                parameterLabel.setFont(new Font("Arial", Font.BOLD, 20));
                break;
            case State.S.SELECT_IMAGE:
                selectImageLabel.setFont(new Font("Arial", Font.BOLD, 20));
            default:

        }

    }

    private JPanel selectImagePanel() {
        JPanel panel = new JPanel();
        JButton btn = new JButton("Select Image");
        btn.setActionCommand("SELECT_IMAGE");
        btn.addActionListener(this.controller);
        panel.add(btn);
        return panel;
    }

}
