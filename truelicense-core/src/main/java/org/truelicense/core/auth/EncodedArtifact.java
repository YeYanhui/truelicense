/*
 * Copyright (C) 2005-2015 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */

package org.truelicense.core.auth;

import java.lang.reflect.Type;
import javax.annotation.concurrent.Immutable;

import org.truelicense.api.auth.Artifactory;
import org.truelicense.api.codec.Codec;
import org.truelicense.spi.io.MemoryStore;
import org.truelicense.api.io.Source;

/**
 * Uses a {@link Codec} to decode an artifact.
 *
 * @author Christian Schlichtherle
 */
@Immutable
final class EncodedArtifact implements Artifactory {

    private final Codec codec;
    private final Source source;

    /**
     * Constructs an encoded artifact.
     *
     * @param codec the codec for decoding the artifact.
     * @param encodedArtifact the encoded representation of the artifact.
     */
    EncodedArtifact(Codec codec, byte[] encodedArtifact) {
        this(codec, source(encodedArtifact));
    }

    /**
     * Constructs an encoded artifact.
     *
     * @param codec the codec for decoding the artifact.
     * @param source the source from where to read the encoded object graph
     *        from.
     */
    EncodedArtifact(final Codec codec, final Source source) {
        this.codec = codec;
        this.source = source;
    }

    private static Source source(final byte[] encodedArtifact) {
        final MemoryStore store = new MemoryStore();
        store.data(encodedArtifact);
        return store;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T decode(Type expected) throws Exception {
        return codec.decode(source, expected);
    }
}