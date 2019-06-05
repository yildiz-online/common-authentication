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

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Grégory Van den Borre
 */
public final class CredentialsTest {
    
    @Test
    public void testGetHashedPassword() {
        Credentials c = new Credentials(AuthenticationTestHelper.LOGIN_OK, AuthenticationTestHelper.PASSWORD_OK);
        assertEquals(AuthenticationTestHelper.PASSWORD_OK, c.password);
    }


    @Test
    public void testGetLogin() {
        Credentials c = new Credentials(AuthenticationTestHelper.LOGIN_OK, AuthenticationTestHelper.PASSWORD_OK);
        assertEquals(AuthenticationTestHelper.LOGIN_OK, c.login);
    }


    @Test
    public void testCredentialsLoginNull() {
        assertThrows(NullPointerException.class, () -> new Credentials(null, AuthenticationTestHelper.PASSWORD_OK));
    }

    @Test
    public void testCredentialsPasswordNull() {
        assertThrows(NullPointerException.class, () -> new Credentials(AuthenticationTestHelper.LOGIN_OK, null));
    }

    @Test
    public void testUnchecked() {
        Credentials c = Credentials.unchecked("abc", "def");
        assertEquals("abc", c.login);
        assertEquals("def", c.password);
    }

    /*@Test
    void testEqualsHashcode() {
        Credentials base = Credentials.unchecked("abc", "def");
        Credentials same = Credentials.unchecked("abc", "def");
        Credentials diff = Credentials.unchecked("abc", "ghi");
        BaseTest<Credentials> b = new BaseTest<>(base, same, diff);
        b.all();
    }*/

    @Test
    public void testEqualsDifferentLogin() {
        Credentials base = Credentials.unchecked("abc", "def");
        Credentials diff = Credentials.unchecked("cde", "ghi");
        assertNotEquals(base, diff);
    }

    @Test
    public void testEqualsDifferentPassword() {
        Credentials base = Credentials.unchecked("abc", "def");
        Credentials diff = Credentials.unchecked("abc", "ghi");
        assertNotEquals(base, diff);
    }

}
