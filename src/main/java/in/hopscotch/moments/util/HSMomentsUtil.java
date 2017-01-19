package in.hopscotch.moments.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HSMomentsUtil {

    @Value("${com.nstechs.commerce.file.dns}")
    String fileDns;

    public String getImageCDNUrl(String imageURL) {
        StringBuilder builder = new StringBuilder();
        builder.append("https://").append(fileDns).append(imageURL);
        return builder.toString();
    }

}
