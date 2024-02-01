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
        panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(button);
        panel.add(label);

        // frame outside borders
        frame = new JFrame();
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("JUST CLICK IT");

        // Add a full-screen button
        JButton fullScreenButton = new JButton("IMMERSIVE MODE");
        fullScreenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleFullScreen();
            }
        });
        panel.add(fullScreenButton);

        // Set the frame to full-screen mode
        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();
        if (graphicsDevice.isFullScreenSupported()) {
            frame.setUndecorated(true); // Remove window decorations
            frame.setSize(graphicsDevice.getDisplayMode().getWidth(), graphicsDevice.getDisplayMode().getHeight());
            graphicsDevice.setFullScreenWindow(frame);
        } else {
            System.err.println("Full-screen mode not supported");
            frame.setSize(800, 600); // Set a default size if full-screen mode is not supported
        }

        frame.setVisible(true);
    }

    // click script
    public void actionPerformed(ActionEvent e) {
        count++;
        label.setText("Number of clicks:  " + count);
    }

    // Toggle full-screen mode
    private void toggleFullScreen() {
        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();

        if (frame.isUndecorated()) {
            // Switch to windowed mode
            frame.dispose();
            frame.setUndecorated(false);
            frame.setSize(800, 600); // Set a default size for windowed mode
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        } else if (graphicsDevice.isFullScreenSupported()) {
            // Switch to full-screen mode
            frame.dispose();
            frame.setUndecorated(true);
            frame.setSize(graphicsDevice.getDisplayMode().getWidth(), graphicsDevice.getDisplayMode().getHeight());
            graphicsDevice.setFullScreenWindow(frame);
        } else {
            System.err.println("Full-screen mode not supported");
        }
    }

    public static void main(String[] args) {
        new ClickCount();
    }
}
