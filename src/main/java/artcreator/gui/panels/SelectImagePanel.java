package artcreator.gui.panels;

import artcreator.gui.Controller;

import javax.swing.*;

public class SelectImagePanel extends JPanel {
    Controller controller;

    public SelectImagePanel(Controller controller) {
        this.controller = controller;
        setLayout(null);
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 940, 600);

        JButton btn = new JButton("Select Image");
        btn.setActionCommand("SELECT_IMAGE");
        btn.setBounds(940/2, 600/2, 30, 50);
        btn.addActionListener(this.controller);
        panel.add(btn);

        this.add(panel);
    }


}
