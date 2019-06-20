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

import be.yildizgames.common.model.PlayerId;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Grégory Van den Borre
 */
public class TokenTest {

    @Nested
    public class Authenticated {

        @Test
        public void happyFlow() {
            Token t = Token.authenticated(PlayerId.valueOf(6), 12, 10);
            assertEquals(PlayerId.valueOf(6), t.getId());
            assertEquals(12, t.getAuthenticationTime());
            assertEquals(10, t.getKey());
            assertEquals(Token.Status.AUTHENTICATED, t.getStatus());
            assertTrue(t.isAuthenticated());
        }

        @Test
        public void withNullPlayer() {
            assertThrows(AssertionError.class, () -> Token.authenticated(null, 0, 10));
        }

        @Test
        public void withNegativeTime() {
            assertThrows(IllegalArgumentException.class, () -> Token.authenticated(PlayerId.valueOf(6), -1, 10));
        }
    }

    @Nested
    public class AuthenticationFailed {

        @Test
        public void happyFlow() {
            Token t = Token.authenticationFailed();
            assertEquals(PlayerId.WORLD, t.getId());
            assertEquals(0, t.getAuthenticationTime());
            assertEquals(-1, t.getKey());
            assertEquals(Token.Status.NOT_AUTHENTICATED, t.getStatus());
            assertFalse(t.isAuthenticated());
        }
    }

    @Nested
    public class Banned {

        @Test
        public void happyFlow() {
            Token t = Token.banned();
            assertEquals(PlayerId.WORLD, t.getId());
            assertEquals(0, t.getAuthenticationTime());
            assertEquals(-1, t.getKey());
            assertEquals(Token.Status.BANNED, t.getStatus());
            assertFalse(t.isAuthenticated());
        }
    }

    @Nested
    public class NotFound {

        @Test
        public void happyFlow() {
            Token t = Token.notFound();
            assertEquals(PlayerId.WORLD, t.getId());
            assertEquals(0, t.getAuthenticationTime());
            assertEquals(-1, t.getKey());
            assertEquals(Token.Status.NOT_FOUND, t.getStatus());
            assertFalse(t.isAuthenticated());
        }
    }

    @Nested
    public class Any {

        @Test
        public void happyFlow() {
            Token t = Token.any(PlayerId.valueOf(6), 10, Token.Status.NOT_AUTHENTICATED);
            assertEquals(PlayerId.valueOf(6), t.getId());
            assertEquals(0, t.getAuthenticationTime());
            assertEquals(10, t.getKey());
            assertEquals(Token.Status.NOT_AUTHENTICATED, t.getStatus());
        }

        @Test
        public void withNullPlayer() {
            assertThrows(AssertionError.class, () -> Token.any(null, 10, Token.Status.AUTHENTICATED));
        }

        @Test
        public void withNullStatus() {
            assertThrows(AssertionError.class, () -> Token.any(PlayerId.valueOf(6), 10, null));
        }

    }

    @Nested
    public class HashCode {

        @Test
        public void isSame() {
            Token t = Token.authenticated(PlayerId.valueOf(6), 12, 10);
            Token t2 = Token.authenticated(PlayerId.valueOf(6), 12, 10);
            Token t3 = Token.authenticated(PlayerId.valueOf(6), 25, 10);
            assertEquals(t.hashCode(), t2.hashCode());
            assertEquals(t.hashCode(), t3.hashCode());
        }

        @Test
        public void isNotSame() {
            Token t = Token.authenticated(PlayerId.valueOf(6), 12, 10);
            Token t2 = Token.authenticated(PlayerId.valueOf(7), 12, 10);
            Token t3 = Token.authenticated(PlayerId.valueOf(6), 12, 12);
            assertNotEquals(t.hashCode(), t2.hashCode());
            assertNotEquals(t.hashCode(), t3.hashCode());
        }
    }

    @Nested
    public class Equals {

        @Test
        public void isSameObject() {
            Token t = Token.authenticated(PlayerId.valueOf(6), 12, 10);
            assertEquals(t, t);
        }

        @Test
        public void isSame() {
            Token t = Token.authenticated(PlayerId.valueOf(6), 12, 10);
            Token t2 = Token.authenticated(PlayerId.valueOf(6), 12, 10);
            assertEquals(t, t2);
        }

        @Test
        public void withDifferentPlayer() {
            Token t = Token.authenticated(PlayerId.valueOf(6), 12, 10);
            Token t2 = Token.authenticated(PlayerId.valueOf(7), 12, 10);
            assertNotEquals(t, t2);
        }

        @Test
        public void withDifferentTime() {
            Token t = Token.authenticated(PlayerId.valueOf(6), 12, 10);
            Token t2 = Token.authenticated(PlayerId.valueOf(6), 25, 10);
            assertEquals(t, t2);
        }

        @Test
        public void withDifferentKey() {
            Token t = Token.authenticated(PlayerId.valueOf(6), 12, 10);
            Token t2 = Token.authenticated(PlayerId.valueOf(6), 12, 11);
            assertNotEquals(t, t2);
        }

        @Test
        public void withNull() {
            Token t = Token.authenticated(PlayerId.valueOf(6), 12, 10);
            assertNotEquals(null, t);
        }

        @Test
        public void withOtherType() {
            Token t = Token.authenticated(PlayerId.valueOf(6), 12, 10);
            assertNotEquals("ok", t);
        }

        @Test
        public void withDifferentStatus() {
            Token t = Token.any(PlayerId.valueOf(6), 10, Token.Status.AUTHENTICATED);
            Token t2 = Token.any(PlayerId.valueOf(6), 10, Token.Status.NOT_AUTHENTICATED);
            assertNotEquals(t, t2);
        }
    }


}