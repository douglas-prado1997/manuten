
import Controllers.RepublicController;
import Controllers.SignInController;
import Views.RepublicView;
import java.util.UUID;
import utils.Migration;



public class App {
    public static void main(String[] args) {
        Migration migration = new Migration();
        migration.run();
        
        SignInController republic = new SignInController();
        republic.view();
    }
}
