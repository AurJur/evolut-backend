package lt.tyz.evolut.utils;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Component
public class Props {

    private Properties props;

    public Props() throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("application.properties");
//        inputStream = new FileInputStream("src\\main\\resources\\application.properties");
        properties.load(inputStream);
        this.props = properties;
    }

    public String getProperty(String key) {
        return this.props.getProperty(key);
    }
}
