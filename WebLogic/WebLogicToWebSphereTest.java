import java.net.HttpURLConnection;
import java.net.URL;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Hashtable;

/**
 * This test script verifies the communication between WebLogic (client) and WebSphere (FileNet host).
 * It performs two key tests:
 * 1. A REST API call to WebSphere to check if FileNet is accessible.
 * 2. An EJB lookup to check if WebLogic can retrieve a remote object from WebSphere.
 */
public class WebLogicToWebSphereTest {
    
    public static void main(String[] args) {
        // WebSphere host and endpoints for testing
        String fileNetRestUrl = "https://<WebSphere-Host>:9443/FileNet/CMIS"; // FileNet REST API endpoint
        String ejbJndiName = "ejb/FileNetService"; // WebSphere EJB lookup name
        
        // Step 1: Test REST API connectivity from WebLogic to WebSphere
        testRestConnection(fileNetRestUrl);
        
        // Step 2: Test EJB lookup from WebLogic to WebSphere
        testEjbConnection(ejbJndiName);
    }
    
    /**
     * Tests if WebLogic can successfully connect to WebSphere's FileNet REST API.
     * @param restUrl The REST API endpoint hosted on WebSphere.
     */
    public static void testRestConnection(String restUrl) {
        try {
            URL url = new URL(restUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                System.out.println("Successfully connected to FileNet REST API on WebSphere.");
            } else {
                System.out.println("Failed to connect to FileNet REST API. HTTP Response Code: " + responseCode);
            }
        } catch (Exception e) {
            System.out.println("REST API Connection Failed: " + e.getMessage());
        }
    }
    
    /**
     * Tests if WebLogic can perform an EJB lookup from WebSphere.
     * @param jndiName The JNDI name of the remote EJB service hosted on WebSphere.
     */
    public static void testEjbConnection(String jndiName) {
        try {
            // WebSphere-specific JNDI properties
            Hashtable<String, String> env = new Hashtable<>();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.ibm.websphere.naming.WsnInitialContextFactory");
            env.put(Context.PROVIDER_URL, "iiop://<WebSphere-Host>:2809"); // Adjust port if needed
            
            Context ctx = new InitialContext(env);
            Object ejbRef = ctx.lookup(jndiName);
            
            if (ejbRef != null) {
                System.out.println("Successfully connected to WebSphere EJB: " + jndiName);
            } else {
                System.out.println("EJB lookup returned null for: " + jndiName);
            }
        } catch (Exception e) {
            System.out.println("EJB Lookup Failed: " + e.getMessage());
        }
    }
}
