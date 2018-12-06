/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 *  Copyright (c) 2018 Grégory Van den Borre
 *
 *  More infos available: https://www.yildiz-games.be
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

import be.yildizgames.common.exception.implementation.ImplementationException;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Encryption implementation using the BCrypt algorithm.
 * @author Grégory Van den Borre
 */
public class BCryptEncryptionTool implements EncryptionTool {

    private final String salt;

    public BCryptEncryptionTool(String salt) {
        super();
        ImplementationException.throwForNull(salt);
        this.salt = salt;
    }

    public BCryptEncryptionTool() {
        this(BCrypt.gensalt());
    }

    @Override
    public String encrypt(String toEncrypt) {
        ImplementationException.throwForNull(toEncrypt);
        return BCrypt.hashpw(toEncrypt, this.salt);
    }

    @Override
    public boolean check(String encrypted, String clear) {
        ImplementationException.throwForNull(encrypted);
        ImplementationException.throwForNull(clear);
        return BCrypt.checkpw(clear, encrypted);
    }
}
