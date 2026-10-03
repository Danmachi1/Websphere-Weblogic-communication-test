import com.example.filenet.security.SecurityManager;

public class SecurityManagerRegressionTest {
    public static void main(String[] args) {
        String[] users = {"admin", "user", "unknown", "", null};
        String[] actions = {"READ", "WRITE", "DELETE", "read", "", null};
        int failures = 0;
        int count = 0;
        for (String user : users) {
            for (String action : actions) {
                boolean expected = ("admin".equals(user) && ("READ".equals(action) || "WRITE".equals(action)))
                    || ("user".equals(user) && "READ".equals(action));
                boolean actual = SecurityManager.hasPermission(user, action);
                count++;
                if (actual != expected) {
                    failures++;
                    System.err.println("FAIL user=" + user + " action=" + action);
                }
            }
        }
        System.out.println((count - failures) + "/" + count + " permission cases passed");
        if (failures != 0) throw new AssertionError(failures + " permission failures");
    }
}
