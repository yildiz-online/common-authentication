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

/**
 * @author Grégory Van den Borre
 */
public class AccountTest {

    public static Account givenAnAccount() {
        return new Account("01", "me", "myPass", "me@me.com", 100);
    }

    @Nested
    public class Constructor {

        @Test
        public void happyFlow() {
            Account account = new Account("01", "myLogin", "myPassword", "myEmail", 10);
            Assertions.assertEquals("01", account.getId());
            Assertions.assertEquals("myLogin", account.getLogin());
            Assertions.assertEquals("myPassword", account.getPassword());
            Assertions.assertEquals("myEmail", account.getEmail());
            Assertions.assertEquals(10, account.getLastConnectionDate());
        }

        @Test
        public void nullId() {
            Assertions.assertThrows(NullPointerException.class, () -> new Account(null,"myLogin", "myPassword", "myEmail", 10));
        }

        @Test
        public void nullLogin() {
            Assertions.assertThrows(NullPointerException.class, () -> new Account("01",null, "myPassword", "myEmail", 10));
        }

        @Test
        public void nullPassword() {
            Assertions.assertThrows(NullPointerException.class, () -> new Account("01","myLogin", null, "myEmail", 10));
        }

        @Test
        public void nullEmail() {
            Assertions.assertThrows(NullPointerException.class, () -> new Account("01","myLogin", "myPassword", null, 10));
        }

    }

}
