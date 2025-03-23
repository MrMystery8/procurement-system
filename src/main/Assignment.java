
package main;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;



public class Assignment {


    public static void main(String[] args) {
        
         try {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
           
        Login LoginFrame = new Login();
        LoginFrame.setVisible(true); //loginframe is shown
        LoginFrame.pack(); //sets to preferred size
        LoginFrame.setLocationRelativeTo(null); //centers the opening screen
    }
    
}
