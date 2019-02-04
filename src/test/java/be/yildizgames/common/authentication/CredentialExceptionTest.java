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

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Grégory Van den Borre
 */
public class CredentialExceptionTest {

    @Nested
    public class Constructor {

        @Test
        public void happyFlow() {
            new CredentialException(new ArrayList<>());
        }

        @Test
        public void withNullParameter() {
            assertThrows(NullPointerException.class, () -> new CredentialException(null));
        }
    }

    @Nested
    public class GetErrors {

        @Test
        public void happyFlow() {
            CredentialException ce = new CredentialException(List.of(
                    AuthenticationError.INVALID_LOGIN_CHAR,
                    AuthenticationError.INVALID_PASS_CHAR));
            assertEquals(2, ce.getErrors().size());
            assertTrue(ce.getErrors().contains(AuthenticationError.INVALID_LOGIN_CHAR));
            assertTrue(ce.getErrors().contains(AuthenticationError.INVALID_PASS_CHAR));
        }

        @Test
        public void ensureImmutable() {
            CredentialException ce = new CredentialException(List.of(
                    AuthenticationError.INVALID_LOGIN_CHAR,
                    AuthenticationError.INVALID_PASS_CHAR));
            assertThrows(UnsupportedOperationException.class, () -> ce.getErrors().remove(AuthenticationError.INVALID_LOGIN_CHAR));
        }

        @Test
        public void ensureCopy() {
            List<AuthenticationError> l = new ArrayList<>();
            l.add(AuthenticationError.INVALID_LOGIN_CHAR);
            l.add(AuthenticationError.INVALID_PASS_CHAR);
            CredentialException ce = new CredentialException(l);
            assertEquals(2, l.size());
            assertEquals(2, ce.getErrors().size());

            l.add(AuthenticationError.LOGIN_TOO_LONG);
            assertEquals(3, l.size());
            assertEquals(2, ce.getErrors().size());
        }
    }

}