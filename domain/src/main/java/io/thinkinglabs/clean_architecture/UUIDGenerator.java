package io.thinkinglabs.clean_architecture;

import java.util.UUID;

/**
 * @author @tdpauw
 */
public class UUIDGenerator
{

    public String next()
    {
        return UUID.randomUUID().toString();
    }
}
