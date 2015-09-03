package cloud.service;
import cloud.domain.System;

/**
 * Created by swathi on 8/6/2015.
 */
public class SystemService extends GenericService<cloud.domain.System> {
    @Override
    public Class<System> getEntityType()
    {
        return System.class;
    }

}
