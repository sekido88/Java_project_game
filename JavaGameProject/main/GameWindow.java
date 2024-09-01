package JavaGameProject.main;

import java.awt.event.WindowFocusListener;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class GameWindow {
    private JFrame jframe;

    public GameWindow(GamePanel gamePanel) {
        jframe = new JFrame("Chào các em");

        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // xu li hanh dong dong cua so
        jframe.add(gamePanel);
        jframe.setResizable(false); // user không thể thay đổi kích thước
        jframe.pack(); // đảm bảo các phần tử có chỗ đễ hiển thị
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);

        jframe.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowLostFocus(WindowEvent e) { // khi cửa sổ không được focus
                // System.out.println("thoat khoi nha tu");
            }

            @Override
            public void windowGainedFocus(WindowEvent e) { // khi cửa sổ được focus
                // System.out.println("Vao lai nha tu");
            }
        });
    }
}
