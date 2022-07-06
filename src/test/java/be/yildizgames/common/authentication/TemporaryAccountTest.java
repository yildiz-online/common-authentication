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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Grégory Van den Borre
 */
class TemporaryAccountTest {

    private static final String LOGIN_OK = "mee";

    private static final String PASSWORD_OK = "myPass";

    private static final String EMAIL_OK = "me@me.com";

    private static final String LANGUAGE_OK = "en";

    private static TemporaryAccount givenATempAccount() {
        return TemporaryAccount.create(LOGIN_OK, PASSWORD_OK, EMAIL_OK, LANGUAGE_OK);
    }

    @Nested
    class Constructor {

        @Test
        void happyFlow() {
            TemporaryAccount ta = givenATempAccount();
            assertEquals(LOGIN_OK, ta.getLogin());
            assertEquals(PASSWORD_OK, ta.getPassword());
            assertEquals(EMAIL_OK, ta.getEmail());
        }

        @Test
        void withLoginNull() {
            assertThrows(TemporaryAccountValidationException.class, () -> TemporaryAccount.create(null, PASSWORD_OK, EMAIL_OK, LANGUAGE_OK));
        }

        @Test
        void withPasswordNull() {
            assertThrows(TemporaryAccountValidationException.class, () -> TemporaryAccount.create(LOGIN_OK, null, EMAIL_OK, LANGUAGE_OK));
        }

        @Test
        void withEmailNull() {
            assertThrows(TemporaryAccountValidationException.class, () -> TemporaryAccount.create(LOGIN_OK, PASSWORD_OK, null, LANGUAGE_OK));
        }
    }

}
