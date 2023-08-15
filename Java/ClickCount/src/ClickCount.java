import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClickCount implements ActionListener {
    private int count = 0;
    private JLabel label;
    private JFrame frame;
    private JPanel panel;
    private JButton button;

    public class MyColorfulPanel extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // set background color
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    public ClickCount() {

        // button
        button = new JButton("Click Me");
        button.addActionListener(this);
        button.setBackground(Color.PINK);

        // label
        label = new JLabel("Number of clicks: 0");
        label.setForeground(Color.GREEN);

        // panel
        panel = new JPanel();
        panel = new MyColorfulPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(button);
        panel.add(label);

        // frame outside borders
        frame = new JFrame();
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("THE GUI");
        frame.pack();
        frame.setVisible(true);

    }

    // click script
    public void actionPerformed(ActionEvent e) {
        count++;
        label.setText("Number of clicks:  " + count);
    }

    public static void main(String[] args) {
        new ClickCount();
    }
}
