package fer.infobip.project.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
public class HealthController {
    @GetMapping("/health")
    public String health(HttpServletRequest request) {

        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            int port = request.getLocalPort();
            return "Works OK\nHost name: " + inetAddress.getHostName() + ", Host Address: " + inetAddress.getHostAddress() + ", Port: " + port;
        } catch (UnknownHostException e) {
            return "Works OK\nError occured with fetching host:port configuration.";
        }
    }

}