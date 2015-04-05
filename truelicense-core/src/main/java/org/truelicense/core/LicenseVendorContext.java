/*
 * Copyright (C) 2005-2015 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */

package org.truelicense.core;

import org.truelicense.core.auth.Authentication;
import org.truelicense.core.codec.CodecProvider;
import org.truelicense.core.crypto.Encryption;
import org.truelicense.core.io.Source;
import org.truelicense.core.util.Builder;
import org.truelicense.core.util.Injection;
import org.truelicense.obfuscate.Obfuscate;
import org.truelicense.obfuscate.ObfuscatedString;

import javax.annotation.CheckForNull;

/**
 * A derived context for license vendor applications alias license key tools.
 * Use this context to configure a {@link LicenseVendorManager} with the
 * required parameters.
 * For a demonstration of this API, please use the TrueLicense Maven Archetype
 * to generate a sample project - even if you don't use Maven to build your
 * software product.
 * <p>
 * Applications have no need to implement this interface and should not do so
 * because it may be subject to expansion in future versions.
 *
 * @author Christian Schlichtherle
 */
public interface LicenseVendorContext
extends CodecProvider, LicenseApplicationContext, LicenseProvider {

    /**
     * Returns a builder for
     * {@linkplain LicenseVendorManager license vendor managers}.
     * Call its {@link ManagerBuilder#build} method to obtain a configured
     * license vendor manager.
     */
    ManagerBuilder managerBuilder();

    /**
     * A builder for
     * {@linkplain LicenseVendorManager license vendor managers}.
     * Call its {@link #build} method to obtain a configured license vendor
     * manager.
     *
     * @author Christian Schlichtherle
     */
    interface ManagerBuilder extends Builder<LicenseVendorManager> {

        /**
         * Sets the authentication.
         *
         * @return {@code this}.
         */
        ManagerBuilder authentication(Authentication authentication);

        /**
         * Returns an injection for a key store based authentication.
         * Call its {@link Injection#inject} method to build and inject the
         * configured authentication into this builder and return it.
         * <p>
         * The keystore needs to have a key password configured for the private
         * key entry.
         *
         * @see #authentication(Authentication)
         */
        KsbaInjection<ManagerBuilder> keyStore();

        /**
         * Sets the encryption.
         *
         * @return {@code this}.
         */
        ManagerBuilder encryption(Encryption encryption);

        /**
         * Returns an injection for a password based encryption (PBE).
         * Call its {@link Injection#inject} method to build and inject the
         * configured encryption into this builder and return it.
         *
         * @see #encryption(Encryption)
         */
        PbeInjection<ManagerBuilder> pbe();
    }
}
