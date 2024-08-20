package JavaGameProject.main;
import javax.swing.JFrame;

public class GameWindow {
    private JFrame jframe;

   
    public GameWindow(GamePanel gamePanel) {
        jframe = new JFrame("Chào các em");

        jframe.setSize(700,700);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // xu li hanh dong dong cua so
        jframe.add(gamePanel);
        jframe.setResizable(false); // user không thể thay đổi kích thước 
        jframe.pack(); // đảm bảo các phần tử có chỗ đễ hiển thị
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
 
    }
}
