import com.github.cage.Cage;
import com.github.cage.GCage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * created by dailf on 2018/5/10
 *
 * @author dailf
 */
    public class QuickStart {
        public static void main(String[] args) throws IOException {
            Cage cage = new GCage();

            OutputStream os = new FileOutputStream("captcha.jpg", false);
            try {
                cage.draw(cage.getTokenGenerator().next(), os);
            } finally {
                os.close();
            }
        }
    }

