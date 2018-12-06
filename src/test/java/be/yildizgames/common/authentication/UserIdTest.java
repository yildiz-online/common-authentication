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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class UserIdTest {

    @Nested
    class Constructor {

        @Test
        void happyFlow() {
            UserId id = new UserId(15);
            Assertions.assertEquals(15, id.value);
        }

        @Test
        void anonymous() {
            Assertions.assertEquals(-1, UserId.ANONYMOUS.value);
        }

        @Test
        void zero() {
            UserId id = new UserId(0);
            Assertions.assertEquals(0, id.value);
        }

        @Test
        void negative() {
            UserId id = new UserId(-5);
            Assertions.assertEquals(-5, id.value);
        }
    }

    @Nested
    class HashCode {

        @Test
        void same() {
            UserId id = new UserId(5);
            UserId id2 = new UserId(5);
            Assertions.assertEquals(id.hashCode(), id2.hashCode());
        }

        @Test
        void notSame() {
            UserId id = new UserId(5);
            UserId id2 = new UserId(6);
            Assertions.assertNotEquals(id.hashCode(), id2.hashCode());
        }
    }

    @Nested
    class Equals {

        @Test
        void equal() {
            UserId id = new UserId(5);
            UserId id2 = new UserId(5);
            Assertions.assertEquals(id, id2);
        }

        @Test
        void notEqual() {
            UserId id = new UserId(5);
            UserId id2 = new UserId(6);
            Assertions.assertNotEquals(id, id2);
        }

        @Test
        void sameInstance() {
            UserId id = new UserId(5);
            Assertions.assertEquals(id, id);
        }

        @Test
        void nullValue() {
            UserId id = new UserId(5);
            Assertions.assertNotEquals(id, null);
        }

        @Test
        void differentType() {
            UserId id = new UserId(5);
            Assertions.assertNotEquals(id, 5);
        }
    }

}
