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

import static be.yildizgames.common.authentication.AuthenticationTestHelper.LOGIN_EMPTY;
import static be.yildizgames.common.authentication.AuthenticationTestHelper.LOGIN_INVALID;
import static be.yildizgames.common.authentication.AuthenticationTestHelper.LOGIN_OK;
import static be.yildizgames.common.authentication.AuthenticationTestHelper.LOGIN_TOO_LONG;
import static be.yildizgames.common.authentication.AuthenticationTestHelper.LOGIN_TOO_SHORT;
import static be.yildizgames.common.authentication.AuthenticationTestHelper.PASSWORD_EMPTY;
import static be.yildizgames.common.authentication.AuthenticationTestHelper.PASSWORD_INVALID;
import static be.yildizgames.common.authentication.AuthenticationTestHelper.PASSWORD_OK;
import static be.yildizgames.common.authentication.AuthenticationTestHelper.PASSWORD_TOO_LONG;
import static be.yildizgames.common.authentication.AuthenticationTestHelper.PASSWORD_TOO_SHORT;
import static be.yildizgames.common.authentication.AuthenticationTestHelper.givenADefaultAuthenticationChecker;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Grégory Van den Borre
 */

final class AuthenticationCheckerTest {

    @Nested
    class Constructor {

        @Test
        void happyFlow() {
            givenADefaultAuthenticationChecker();
        }

        @Test
        void withNull() {
            assertThrows(AssertionError.class, () -> new SimpleAuthenticationChecker(null));
        }
    }

    @Nested
    class CheckStringString {

        @Test
        void happyFlow() throws CredentialException {
            AuthenticationChecker c = givenADefaultAuthenticationChecker();
            c.check(LOGIN_OK, PASSWORD_OK);
        }

        @Test
        void stringNull() throws CredentialException {
            AuthenticationChecker c = givenADefaultAuthenticationChecker();
            assertEquals(AuthenticationError.LOGIN_EMPTY,
                    assertThrows(CredentialException.class, () -> c.check(null, PASSWORD_OK)).getErrors().get(0));
        }

        @Test
        void passwordNull() throws CredentialException {
            AuthenticationChecker c = givenADefaultAuthenticationChecker();
            assertEquals(AuthenticationError.PASS_EMPTY,
                        assertThrows(CredentialException.class, () -> c.check(LOGIN_OK, null)).getErrors().get(0));
        }

        @Test
        void loginEmpty() {
            AuthenticationChecker c = givenADefaultAuthenticationChecker();
            assertEquals(AuthenticationError.LOGIN_EMPTY,
                    assertThrows(CredentialException.class, () -> c.check(LOGIN_EMPTY, PASSWORD_OK)).getErrors().get(0));

        }

        @Test
        void loginTooShort() {
            AuthenticationChecker c = givenADefaultAuthenticationChecker();
            assertEquals(AuthenticationError.LOGIN_TOO_SHORT,
                    assertThrows(CredentialException.class, () -> c.check(LOGIN_TOO_SHORT, PASSWORD_OK)).getErrors().get(0));

        }

        @Test
        void loginTooLong() {
            AuthenticationChecker c = givenADefaultAuthenticationChecker();
            assertEquals(AuthenticationError.LOGIN_TOO_LONG,
                    assertThrows(CredentialException.class, () -> c.check(LOGIN_TOO_LONG, PASSWORD_OK)).getErrors().get(0));
        }

        @Test
        void passwordTooLong() {
            AuthenticationChecker c = givenADefaultAuthenticationChecker();
            assertEquals(AuthenticationError.PASS_TOO_LONG,
                    assertThrows(CredentialException.class, () -> c.check(LOGIN_OK, PASSWORD_TOO_LONG)).getErrors().get(0));
        }

        @Test
        void passwordEmpty() throws CredentialException {
            AuthenticationChecker c = givenADefaultAuthenticationChecker();
            assertEquals(AuthenticationError.PASS_EMPTY,
                    assertThrows(CredentialException.class, () -> c.check(LOGIN_OK, PASSWORD_EMPTY)).getErrors().get(0));
        }


        @Test
        void passwordTooShort() throws CredentialException {
            AuthenticationChecker c = givenADefaultAuthenticationChecker();
            assertEquals(AuthenticationError.PASS_TOO_SHORT,
                    assertThrows(CredentialException.class, () -> c.check(LOGIN_OK, PASSWORD_TOO_SHORT)).getErrors().get(0));
        }

        @Test
        void loginInvalid() {
            AuthenticationChecker c = givenADefaultAuthenticationChecker();
            assertEquals(AuthenticationError.INVALID_LOGIN_CHAR,
                assertThrows(CredentialException.class, () -> c.check(LOGIN_INVALID, PASSWORD_OK)).getErrors().get(0));
        }

        @Test
        void passwordInvalid() {
            AuthenticationChecker c = givenADefaultAuthenticationChecker();
            assertEquals(AuthenticationError.INVALID_PASS_CHAR,
                    assertThrows(CredentialException.class, () -> c.check(LOGIN_OK, PASSWORD_INVALID)).getErrors().get(0));
        }
    }
}
