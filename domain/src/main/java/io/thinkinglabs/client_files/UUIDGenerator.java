package io.thinkinglabs.client_files;

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
