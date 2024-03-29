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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Grégory Van den Borre
 */
class BCryptEncryptionToolTest {

    private static final String SALT = "$2a$10$7.PJwtiY7Y2heDKA7AoVF.";

    private static final String CLEAR = "testtest";

    private static final String ENCRYPTED = "$2a$10$7.PJwtiY7Y2heDKA7AoVF.tZnqlnekLweOYrV0qf3WOGiQ6nlKEk.";

    @Nested
    class Constructor {

        @Test
        void happyFlow() {
            EncryptionTool enc = new BCryptEncryptionTool("azerty");
            Assertions.assertNotNull(enc);
        }

        @Test
        void nullParameter() {
            Assertions.assertThrows(NullPointerException.class, () -> new BCryptEncryptionTool(null));
        }
    }

    @Nested
    class Encrypt {

        @Test
        void happyFlow() {
            EncryptionTool enc = new BCryptEncryptionTool(SALT);
            String result = enc.encrypt(CLEAR);
            assertEquals(ENCRYPTED, result);
        }

        @Test
        void nullParameter() {
            EncryptionTool enc = new BCryptEncryptionTool(SALT);
            Assertions.assertThrows(NullPointerException.class, () -> enc.encrypt(null));
        }
    }

    @Nested
    class Check {

        @Test
        void happyFlow() {
            EncryptionTool enc = new BCryptEncryptionTool();
            assertTrue(enc.check(enc.encrypt(CLEAR), CLEAR));
        }

        @Test
        void nullEncrypted() {
            EncryptionTool enc = new BCryptEncryptionTool();
            Assertions.assertThrows(NullPointerException.class, () -> enc.check(null, CLEAR));
        }

        @Test
        void nullClear() {
            EncryptionTool enc = new BCryptEncryptionTool();
            Assertions.assertThrows(NullPointerException.class, () -> enc.check(ENCRYPTED, null));
        }
    }
}
