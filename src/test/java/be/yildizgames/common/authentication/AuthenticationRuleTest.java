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

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AuthenticationRuleTest {

    private static final Pattern NUMBER = Pattern.compile("\\d*");
    private static final Pattern ALPHA = Pattern.compile("[a-z]*");

    @Test
    void testAuthenticationRulesDefault() {
        assertEquals(20, AuthenticationRules.DEFAULT.loginMaxLength);
        assertEquals(20, AuthenticationRules.DEFAULT.passMaxLength);
        assertEquals(5, AuthenticationRules.DEFAULT.passMinLength);
        assertEquals(3, AuthenticationRules.DEFAULT.loginMinLength);
        assertEquals("[a-zA-Z0-9]*", AuthenticationRules.DEFAULT.loginPattern.pattern());
        assertEquals("[a-zA-Z0-9]*", AuthenticationRules.DEFAULT.passPattern.pattern());
    }

    @Test
    void testAuthenticationRules() {
        AuthenticationRules r = new AuthenticationRules(10, 15, 8, 6, NUMBER, ALPHA);
        assertEquals(10, r.loginMaxLength);
        assertEquals(15, r.passMaxLength);
        assertEquals(6, r.passMinLength);
        assertEquals(8, r.loginMinLength);
        assertEquals("\\d*", r.loginPattern.pattern());
        assertEquals("[a-z]*", r.passPattern.pattern());
    }

    @Test
    void testLoginMaxSmallerZero() {
        assertThrows(IllegalArgumentException.class, () -> new AuthenticationRules(-1, 15, 3, 5, NUMBER, ALPHA));
    }

    @Test
    void testLoginMinSmallerZero() {
        assertThrows(IllegalArgumentException.class, () -> new AuthenticationRules(10, 15, -1, 5, NUMBER, ALPHA));
    }

    @Test
    void testPassMinSmallerZero() {
        assertThrows(IllegalArgumentException.class, () -> new AuthenticationRules(10, 15, 8, -1, NUMBER, ALPHA));
    }

    @Test
    void testLoginMaxSmallerMin() {
        assertThrows(IllegalArgumentException.class, () -> new AuthenticationRules(2, 15, 3, 5, NUMBER, ALPHA));
    }

    @Test
    void testPassMaxSmallerMin() {
        assertThrows(IllegalArgumentException.class, () -> new AuthenticationRules(10, 2, 3, 5, NUMBER, ALPHA));
    }

    @Test
    void testNullLoginPattern() {
        assertThrows(NullPointerException.class, () -> new AuthenticationRules(10, 15, 3, 5, null, ALPHA));
    }

    @Test
    void testNullPassPattern() {
        assertThrows(NullPointerException.class, () -> new AuthenticationRules(10, 15, 3, 5, ALPHA, null));
    }
}
