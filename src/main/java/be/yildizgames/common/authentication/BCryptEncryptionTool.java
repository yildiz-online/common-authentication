/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 *  Copyright (c) 2019 Grégory Van den Borre
 *
 *  More infos available: https://engine.yildiz-games.be
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 *  documentation files (the "Software"), to deal in the Software without restriction, including without
 *  limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 *  of the Software, and to permit persons to whom the Software is furnished to do so,
 *  subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all copies or substantial
 *  portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 *  WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 *  OR COPYRIGHT  HOLDERS BE LIABLE FOR ANY CLAIM,
 *  DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE  SOFTWARE.
 *
 */

package be.yildizgames.common.authentication;

import org.mindrot.jbcrypt.BCrypt;

import java.util.Objects;

/**
 * Encryption implementation using the BCrypt algorithm.
 * @author Grégory Van den Borre
 */
public class BCryptEncryptionTool implements EncryptionTool {

    /**
     * Salt.
     */
    private final String salt;

    /**
     * Create a new instance using a provided salt.
     * @param salt Salt to use.
     */
    public BCryptEncryptionTool(String salt) {
        super();
        Objects.requireNonNull(salt);
        this.salt = salt;
    }

    /**
     * Create a new instance generating a salt.
     */
    public BCryptEncryptionTool() {
        this(BCrypt.gensalt());
    }

    @Override
    public final String encrypt(final String toEncrypt) {
        Objects.requireNonNull(toEncrypt);
        return BCrypt.hashpw(toEncrypt, this.salt);
    }

    @Override
    public final boolean check(final String encrypted, final String clear) {
        Objects.requireNonNull(encrypted);
        Objects.requireNonNull(clear);
        return BCrypt.checkpw(clear, encrypted);
    }
}
