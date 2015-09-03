package cloud.oid.config;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.introspector.BeanAccess;

import java.io.InputStream;

/**
 * Created by swathi on 8/12/2015.
 */
public class OidLoader {

    public OIDConfiguration getOidConfiguration() {

        InputStream inputStream = this.getClass().getResourceAsStream("/switchOids.yaml");
        Yaml yaml = new Yaml();
        yaml.setBeanAccess(BeanAccess.FIELD);
        return yaml.loadAs(inputStream, OIDConfiguration.class);
    }
}


